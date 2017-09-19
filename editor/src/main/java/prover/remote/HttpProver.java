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
import prover.SZSOntology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class HttpProver implements Prover {

    private static HttpProver instance;
    public ArrayList<String> availableProvers = new ArrayList<>();

    /*
    // for testing purposes only
    public static void main(String[] args) throws Exception {
        String myproblem = "thf(a1,conjecture,$true).";
        String prover = "Satallax---3.2";
        System.out.println(HttpProver.getInstance().prove(myproblem, "asd",prover,5).toString());
    }
    */

    public static HttpProver getInstance() throws IOException {
        if (instance == null){
            instance = new HttpProver();
            instance.loadProvers();
        }
        return instance;
    }

    public ProveResult prove(String problem, String source, String prover, int timelimit) throws ProverNotAvailableException, ProverResultNotInterpretableException {
        Hashtable<String,Object> URLParameters = new Hashtable<String,Object>();

        URLParameters.put("NoHTML",new Integer(1));
        URLParameters.put("QuietFlag","-q2");
        URLParameters.put("ProblemSource","UPLOAD");
        URLParameters.put("SubmitButton","RunSelectedSystems");
        URLParameters.put("System___" + prover,prover);
        URLParameters.put("TimeLimit___" + prover, timelimit);
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
            SZSOntology.SZSDeductiveStatus status = SZSOntology.getStatusFromString(statusString);
            if (status == null) throw new ProverResultNotInterpretableException("Status is not interpretable.");

            int cpuIndex = r.indexOf("CPU =");
            String cpuString = r.substring(cpuIndex + 5).trim();
            nextWhitespace = cpuString.indexOf(" ");
            cpuString = cpuString.substring(0,nextWhitespace);
            double elapsedTime = Double.parseDouble(cpuString);
            return new ProveResult(problem, source, prover, status, elapsedTime, timelimit);
        }
        catch (Exception e){
            throw new ProverResultNotInterpretableException(e.toString());
        }
    }

    /*
     * Retrieves the SystemOnTPTP website and parses all available remote provers which
     * will be stored in availableProvers
     */
    private void loadProvers() throws IOException {
        availableProvers = new ArrayList<>();
        Document doc = Jsoup.connect(Config.getUrlSystemOnTPTP()).get();
        Elements checkboxes = doc.select("input[type=checkbox]");
        for (Element e : checkboxes){
            if (e.attr("name").startsWith("System___")){
                availableProvers.add(e.attr("value"));
            }
        }
    }
}
