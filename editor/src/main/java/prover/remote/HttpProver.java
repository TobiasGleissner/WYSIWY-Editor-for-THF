package prover.remote;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import prover.ProveResult;
import prover.Prover;
import prover.TPTPDefinitions;

import java.io.IOException;
import java.util.*;

public class HttpProver implements Prover {

    private static HttpProver instance;
    private Map<TPTPDefinitions.TPTPDialect,ArrayList<String>> availableProvers;

    // for testing purposes only
    /*
    public static void main(String[] args) throws Exception {
        String myproblem = "thf(a1,conjecture,$true).";
        String prover = "Satallax---3.2";
        HttpProver i = HttpProver.getInstance();
        Arrays.stream(TPTPDefinitions.TPTPDialect.values()).forEach(d->{
            System.out.println(d);i.availableProvers.get(d).stream().forEach(System.out::println);});
        System.out.println(HttpProver.getInstance().prove(myproblem, "asd",prover,5).toString());
    }
    */

    /**
     * Constructor substitute.
     * @return only way to get an instance of HttpProver
     * @throws IOException if SystemOnTPTP website could not be reached
     */
    public static HttpProver getInstance() throws IOException {
        if (instance == null){
            instance = new HttpProver();
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
        return availableProvers.get(dialect);
    }

    /**
     * Sends a problem to a remote prover and gets a result.
     * @param problem problem to prove as String
     * @param source meta information of the problem e.g. filename or an url
     * @param prover prover name as string; a list of names for a certain TPTPDefinitions.TPTPDialect
     *               can be retrieved with the method getAvailableProvers
     * @param timeLimit time limit for the proving process in seconds
     * @return ProveResult object containing an SZSStatus and additional information
     * @throws ProverNotAvailableException no connection to the SystemOnTPTP website could be established
     * @throws ProverResultNotInterpretableException the return result of the SystemOnTPTP website could not be interpreted
     */
    public ProveResult prove(String problem, String source, String prover, int timeLimit) throws ProverNotAvailableException, ProverResultNotInterpretableException {
        Hashtable<String,Object> URLParameters = new Hashtable<String,Object>();

        URLParameters.put("NoHTML",new Integer(1));
        URLParameters.put("QuietFlag","-q2");
        URLParameters.put("ProblemSource","UPLOAD");
        URLParameters.put("SubmitButton","RunSelectedSystems");
        URLParameters.put("System___" + prover,prover);
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
            if (status == null) throw new ProverResultNotInterpretableException("Status is not interpretable.");

            int cpuIndex = r.indexOf("CPU =");
            String cpuString = r.substring(cpuIndex + 5).trim();
            nextWhitespace = cpuString.indexOf(" ");
            cpuString = cpuString.substring(0,nextWhitespace);
            double elapsedTime = Double.parseDouble(cpuString);
            return new ProveResult(problem, source, prover, r, status, elapsedTime, timeLimit);
        }
        catch (Exception e){
            throw new ProverResultNotInterpretableException(e.toString());
        }
    }

    /*
     * Retrieves the SystemOnTPTP website and parses all available remote provers which
     * will be stored in availableProvers depending on the dialect listed on the website
     */
    private void loadProvers() throws IOException {
        availableProvers = new HashMap<>();
        Arrays.stream(TPTPDefinitions.TPTPDialect.values()).forEach(dialect -> availableProvers.put(dialect,new ArrayList<>()));
        Document doc = Jsoup.connect(Config.getUrlSystemOnTPTP()).get();
        Elements checkboxes = doc.select("input[type=checkbox]");
        for (Element e : checkboxes){
            if (e.attr("name").startsWith("System___")){
                String description = e.parent().parent().child(e.parent().parent().children().size()-1).text();
                List<TPTPDefinitions.TPTPDialect> dialects = parseDialects(description);
                for (TPTPDefinitions.TPTPDialect d: dialects)availableProvers.get(d).add(e.attr("value"));
            }
        }
    }

    /*
     * Parses a String containing TPTPDefinitions.TPTPSubdialect names
     * and returns a List of TPTPDefinitions.TPTPDialect names containing no duplicates
     */
    private List<TPTPDefinitions.TPTPDialect> parseDialects(String input){
        HashSet<TPTPDefinitions.TPTPDialect> ret = new HashSet<>();
        Arrays.stream(TPTPDefinitions.TPTPSubdialect.values()).forEach(dialect -> {if (input.contains(dialect.name())) ret.add(TPTPDefinitions.subDialectToDialect(dialect));});
        return new ArrayList<>(ret);
    }
}
