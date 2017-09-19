package prover.remote;

import prover.ProverAbstract;

import java.io.BufferedWriter;
import java.io.File;
import java.util.Hashtable;

public class HttpProver extends ProverAbstract {

    static String url = "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTPFormReply";

    static String myproblem = "thf(a1,conjecture,$true).";

    public static void main(String[] args) throws Exception {
        String prover = "Satallax---3.2";
        prove(prover);

    }

    @Override
    public double getElapsedTime() {
        return 0;
    }

    public static void prove(String prover) throws Exception {
        int intOption;
        char option;
        Hashtable<String,Object> Options = new Hashtable<String,Object>();
        Hashtable<String,Object> URLParameters = new Hashtable<String,Object>();
        File tempFile;
        BufferedWriter fileHandle;
        String line;

        URLParameters.put("NoHTML",new Integer(1));
        URLParameters.put("QuietFlag","-q2");
        URLParameters.put("SubmitButton","RunParallel");
        URLParameters.put("ProblemSource","UPLOAD");
        URLParameters.put("AutoModeTimeLimit",new Integer(300));
        URLParameters.put("AutoMode","-cE");
        URLParameters.put("AutoModeSystemsLimit",new Integer(3));
        URLParameters.put("X2TPTP","");


        // run selected system
        // TODO timelimit
        URLParameters.put("SubmitButton","RunSelectedSystems");
        URLParameters.put("System___" + prover,prover);
        URLParameters.put("TimeLimit___" + prover,
                URLParameters.get("AutoModeTimeLimit"));
        URLParameters.remove("AutoMode");
        URLParameters.remove("AutoModeTimeLimit");
        URLParameters.remove("AutoModeSystemsLimit");

        // set problem
        URLParameters.put("ProblemSource","FORMULAE");
        URLParameters.put("FORMULAEProblem",myproblem);

        // get systems
        /*
        URLParameters.put("SubmitButton","ListSystems");
        URLParameters.put("ListStatus",""); // ????
        URLParameters.remove("AutoMode");
        URLParameters.remove("AutoModeTimeLimit");
        URLParameters.remove("AutoModeSystemsLimit");
        URLParameters.remove("ProblemSource");
        */

        HttpRequest request = new HttpRequest(url,URLParameters);
        request.sendPost();
        System.out.println(request.getResponse());

//----Get format and transform options if specified
        /*
        Getopt g = new Getopt("RemoteSOT",args,"hw:q:t:c:l:s:Sp:a:");
        while((intOption = g.getopt()) != -1) {
            option = (char)intOption;
            if (Options.containsKey(String.valueOf(option))) {
                Options.remove(String.valueOf(option));
            }
            if (option == 'h') {
                System.out.println(
                        "Usage: java RemoteSOT <options> [<File name>]");
                System.out.println("<options> are ...");
                System.out.println("-h            - print this help");
                System.out.println("-w<status>    - list available ATP systems");
                System.out.println("-q<quietness> - control amount of output");
                System.out.println("-t<timelimit> - CPU time limit for system");
                System.out.println("-c<automode>  - one of N, E, S");
                System.out.println("-l<syslimit>  - maximal systems for automode");
                System.out.println("-s<system>    - specified system to use");
                System.out.println("-S            - TPTP format output");
                System.out.println("-p<filename>  - TPTP problem name");
                System.out.println("<File name>   - if not TPTP problem");
                return;
            }
//DEBUG System.out.println(option + " is ==" + Options.get(String.valueOf(option)) + "==");
            if (option == 'S') {
                Options.put(String.valueOf(option),String.valueOf(option));
            } else {
                Options.put(String.valueOf(option),g.getOptarg());
            }
        }
        */

        /*
//----Transfer from Options to URLParameters
//----What systems flag
        if (Options.containsKey("w")) {
            URLParameters.put("SubmitButton","ListSystems");
            URLParameters.put("ListStatus",""); // ????
            URLParameters.remove("AutoMode");
            URLParameters.remove("AutoModeTimeLimit");
            URLParameters.remove("AutoModeSystemsLimit");
            URLParameters.remove("ProblemSource");
        }
//----Quiet flag
        if (Options.containsKey("q")) {
            URLParameters.put("QuietFlag","-q" + Options.get("q"));
        }
//----Time limit
        if (Options.containsKey("t")) {
            URLParameters.put("AutoModeTimeLimit",Options.get("t"));
        }
//----Automode
        if (Options.containsKey("c")) {
            URLParameters.put("AutoMode","-c" + Options.get("c"));
        }
//----Systems limit
        if (Options.containsKey("l")) {
            URLParameters.put("AutoModeSystemsLimit",Options.get("l"));
        }
//----Selected system. Do after time limit as it gets moved across
        if (Options.containsKey("s")) {
            URLParameters.put("SubmitButton","RunSelectedSystems");
            URLParameters.put("System___" + Options.get("s"),Options.get("s"));
            URLParameters.put("TimeLimit___" + Options.get("s"),
                    URLParameters.get("AutoModeTimeLimit"));
            URLParameters.remove("AutoMode");
            URLParameters.remove("AutoModeTimeLimit");
            URLParameters.remove("AutoModeSystemsLimit");
        }
//----TSTP format output request
        if (Options.containsKey("S")) {
            URLParameters.put("X2TPTP","-S");
        }
//----TPTP file name
        if (Options.containsKey("p")) {
            URLParameters.put("ProblemSource","TPTP");
            URLParameters.put("TPTPProblem",(String)Options.get("p"));
        }
//----Password
        if (Options.containsKey("a")) {
            URLParameters.put("CPUPassword",Options.get("a"));
        }

//----Get single file name
        if (URLParameters.containsKey("ProblemSource") &&
                URLParameters.get("ProblemSource").equals("UPLOAD")) {
            if (args.length > g.getOptind() &&
                    !args[g.getOptind()].equals("--")) {
                URLParameters.put("UPLOADProblem",
                        new File(args[g.getOptind()]));
            } else {
//----Read from stdin
                URLParameters.put("UPLOADProblem",System.in);
//TEST URLParameters.remove("ProblemSource");
//TEST URLParameters.put("ProblemSource","FORMULAE");
//TEST URLParameters.put("FORMULAEProblem","fof(a,conjecture,a<=>a).");
            }
        }

        try {
            BufferedReader response = new BufferedReader(new InputStreamReader(
                    ClientHttpRequest.post(new URL(SystemOnTPTPFormReplyURL),URLParameters)));
            String responseLine;
            while ((responseLine = response.readLine()) != null) {
                System.out.println(responseLine);
            }
            response.close();
        } catch (Exception ioe) {
            System.err.println("Exception: " + ioe.getMessage());
        }
        */
    }
}
