package prover;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.HttpRequest;

import java.io.IOException;
import java.util.*;

public class SystemOnTPTPProver implements Prover {

    private static SystemOnTPTPProver instance;
    private Map<TPTPDefinitions.TPTPSubDialect,List<String>> availableProvers;

    // for testing purposes only
    public static void main(String[] args) throws Exception {
        SystemOnTPTPProver.getInstance();
        //List<TPTPDefinitions.TPTPSubDialect> subDialects = new ArrayList<>();
        //subDialects.add(TPTPDefinitions.TPTPSubDialect.TH0);
        //SystemOnTPTPProver.getInstance().getAvailableProvers(subDialects).forEach(System.out::println);

        /*
        String myproblem = "thf(a1,conjecture,$true).";
        String prover = "Satallax---3.2";
        SystemOnTPTPProver i = SystemOnTPTPProver.getInstance();
        Arrays.stream(TPTPDefinitions.TPTPDialect.values()).forEach(d->{
            System.out.println(d);i.availableProvers.get(d).stream().forEach(System.out::println);});
        System.out.println(SystemOnTPTPProver.getInstance().prove(myproblem, "asd",prover,5).toString());
        */
    }


    /**
     * Constructor substitute.
     * @return only way to get an instance of SystemOnTPTPProver
     * @throws IOException if SystemOnTPTP website could not be reached
     */
    public static SystemOnTPTPProver getInstance() throws IOException {
        if (instance == null){
            instance = new SystemOnTPTPProver();
            instance.loadProvers();
        }
        return instance;
    }

    /**
     * Retrieves a list of available remote provers of a certain TPTP dialect.
     * @param dialect
     * @return SystemOnTPTP provers for the specified dialect as a list of strings
     */
    public List<String> getAvailableProvers(TPTPDefinitions.TPTPDialect dialect){
        return getAvailableProvers(TPTPDefinitions.getTPTPSubDialectsFromTPTPDialect(dialect));
    }

    /**
     * Retrieves a list of available remote provers of a certain TPTP subDialect.
     * @param subDialect
     * @return SystemOnTPTP provers for the specified sub-dialect as a list of strings
     */
    public List<String> getAvailableProvers(TPTPDefinitions.TPTPSubDialect subDialect){
        return availableProvers.get(subDialect);
    }

    /**
     * Retrieves a list of available remote provers of certain TPTP subDialects.
     * @param subDialectList
     * @return SystemOnTPTP provers for the specified sub-dialects as a list of strings
     */
    public List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subDialectList){
        List<String> provers = new ArrayList<>();
        for (TPTPDefinitions.TPTPSubDialect d : subDialectList){
            provers.addAll(getAvailableProvers(d));
        }
        return provers;
    }

    /**
     * Sends a problem to a remote prover and gets a result.
     * @param problem problem to prove as String
     * @param prover prover name as string; a list of names for a certain TPTPDefinitions.TPTPDialect
     *               can be retrieved with the method getAvailableProvers
     * @param timeLimit time limit for the proving process in seconds
     * @return ProveResult object containing an SZSStatus and additional information
     * @throws ProverNotAvailableException no connection to the SystemOnTPTP website could be established
     * @throws ProverResultNotInterpretableException the return result of the SystemOnTPTP website could not be interpreted
     */
    public ProveResult prove(String problem, String prover, int timeLimit) throws ProverNotAvailableException, ProverResultNotInterpretableException {
        return prove(problem,prover,null,timeLimit);
    }

        /**
         * Sends a problem to a remote prover and gets a result.
         * @param problem problem to prove as String
         * @param prover prover name as string; a list of names for a certain TPTPDefinitions.TPTPDialect
         *               can be retrieved with the method getAvailableProvers
         * @param command individual remote prover command
         * @param timeLimit time limit for the proving process in seconds
         * @return ProveResult object containing an SZSStatus and additional information
         * @throws ProverNotAvailableException no connection to the SystemOnTPTP website could be established
         * @throws ProverResultNotInterpretableException the return result of the SystemOnTPTP website could not be interpreted
         */
    public ProveResult prove(String problem, String prover, String command, int timeLimit) throws ProverNotAvailableException, ProverResultNotInterpretableException {
        Hashtable<String,Object> URLParameters = new Hashtable<>();

        URLParameters.put("NoHTML",new Integer(1));
        URLParameters.put("QuietFlag","-q2");
        URLParameters.put("ProblemSource","UPLOAD");
        URLParameters.put("SubmitButton","RunSelectedSystems");
        URLParameters.put("System___" + prover,prover);
        if (command != null) URLParameters.put("Command___" + prover,command);
        URLParameters.put("TimeLimit___" + prover, timeLimit);
        URLParameters.put("ProblemSource","FORMULAE");
        URLParameters.put("FORMULAEProblem",problem);

        HttpRequest request = new HttpRequest(Config.getUrlSystemOnTPTPFormReply(),URLParameters);
        try {
            request.sendPost();
        } catch (IOException e) {
            throw new ProverNotAvailableException(e.toString());
        }
        String r = request.getResponse();

        try {
            int saysIndex = r.indexOf("says");
            String statusString = r.substring(saysIndex + 4).trim();
            int nextWhitespace = statusString.indexOf(" ");
            statusString = statusString.substring(0,nextWhitespace);
            TPTPDefinitions.SZSDeductiveStatus status = TPTPDefinitions.getStatusFromString(statusString);
            if (status == null) throw new ProverResultNotInterpretableException("Status is not interpretable.",r);

            int cpuIndex = r.indexOf("CPU =");
            String cpuString = r.substring(cpuIndex + 5).trim();
            nextWhitespace = cpuString.indexOf(" ");
            cpuString = cpuString.substring(0,nextWhitespace);
            double elapsedTime = Double.parseDouble(cpuString);
            return new ProveResult(problem, ProverType.SYSTEMONTPTP_PROVER, prover, r, "",status, elapsedTime, timeLimit);
        }
        catch (Exception e){
            throw new ProverResultNotInterpretableException(e.toString(),r);
        }
    }

    /*
     * Retrieves the SystemOnTPTP website and parses all available remote provers which
     * will be stored in availableProvers depending on the dialect listed on the website
     */
    private void loadProvers() throws IOException {
        availableProvers = new HashMap<>();
        Arrays.stream(TPTPDefinitions.TPTPSubDialect.values()).forEach(dialect -> availableProvers.put(dialect,new ArrayList<>()));
        Document doc = Jsoup.connect(Config.getUrlSystemOnTPTP()).get();
        Elements checkboxes = doc.select("input[type=checkbox]");
        for (Element e : checkboxes){
            if (e.attr("name").startsWith("System___")){
                String description = e.parent().parent().child(e.parent().parent().children().size()-1).text();
                List<TPTPDefinitions.TPTPSubDialect> dialects = parseDialects(description);
                for (TPTPDefinitions.TPTPSubDialect d: dialects)availableProvers.get(d).add(e.attr("value"));
            }
        }
    }

    /*
     * Parses a String containing TPTPDefinitions.TPTPSubDialect names
     * and returns a List of TPTPDefinitions.TPTPDialect names containing no duplicates
     */
    private List<TPTPDefinitions.TPTPSubDialect> parseDialects(String input){
        HashSet<TPTPDefinitions.TPTPSubDialect> ret = new HashSet<>();
        Arrays.stream(TPTPDefinitions.TPTPSubDialect.values()).forEach(dialect ->
        {if (input.contains(dialect.name())) ret.add(dialect);});
        Arrays.stream(TPTPDefinitions.TPTPDialect.values()).forEach(dialect ->
        {if (input.contains(dialect.name())) TPTPDefinitions.getTPTPSubDialectsFromTPTPDialect(dialect).forEach(ret::add);});
        return new ArrayList<>(ret);
    }
}