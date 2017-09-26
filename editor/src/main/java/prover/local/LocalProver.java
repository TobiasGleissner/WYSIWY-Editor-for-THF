package prover.local;

import exceptions.NameAlreadyInUseException;
import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.Config;
import prover.ProveResult;
import prover.Prover;
import prover.TPTPDefinitions;
import util.RandomString;

import java.io.*;
import java.util.List;

public class LocalProver implements Prover {
    private static List<String> availableProvers;
    private static LocalProver instance;

    private LocalProver(){

    }

    /*
    public static void main(String[] args) throws Exception {
        LocalProver p = LocalProver.getInstance();
        //p.getAvailableProvers().stream().forEach(System.out::println);
        //p.getAvailableProvers().stream().map(s->Config.getLocalProverCommand(s)).forEach(System.out::println);
        String myproblem = "thf(a1,conjecture,$true).";
        String prover = "satallax";
        System.out.println(LocalProver.getInstance().prove(myproblem, "asd",prover,5).toString());
    }
    */

    public List<String> getAvailableProvers(TPTPDefinitions.TPTPDialect dialect){
        return availableProvers;
    }
    public List<String> getAvailableProvers(){
        return availableProvers;
    }


    public static LocalProver getInstance(){
        if (instance == null){
            instance = new LocalProver();
        }
        availableProvers = Config.getLocalProvers();
        return instance;
    }

    public void addProver(String proverName, String command, boolean override) throws NameAlreadyInUseException {
        if (!override && availableProvers.contains(proverName)) throw new NameAlreadyInUseException("Name " + proverName + " is already in use with command " + Config.getLocalProverCommand(proverName));
        Config.setLocalProverCommand(proverName, command);
        if (!availableProvers.contains(proverName)) availableProvers.add(proverName);
        Config.setLocalProvers(availableProvers);
    }

    public void removeProver(String proverName) throws ProverNotAvailableException {
        if (!availableProvers.contains(proverName)) throw new ProverNotAvailableException("prover not available");
        Config.removePreference("prover" + proverName);
        availableProvers.remove(proverName);
        Config.setLocalProvers(availableProvers);
    }

    public ProveResult testTHFProver(String proverCommand) throws ProverNotAvailableException, IOException, ProverResultNotInterpretableException {
        String testProblem = "thf(1,conjecture,$true).";
        return prove(testProblem,proverCommand,5);
    }

    private ProveResult prove(String problem, String proverCommand, int timeLimit) throws IOException, ProverNotAvailableException, ProverResultNotInterpretableException {
        File tempFile = null;
        BufferedWriter writer = null;
        try {
            tempFile = File.createTempFile(Config.name + "_", "_" + RandomString.getRandomString());
            writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write(problem);
            writer.close();
        } catch (IOException e) {
            throw e;
        }

        proverCommand = proverCommand.replace("%f", tempFile.getAbsolutePath());
        proverCommand = proverCommand.replace("%t", Integer.toString(timeLimit));
        String cmdTreeLimitedRun = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        cmdTreeLimitedRun += "../scripts/TreeLimitedRun";
        cmdTreeLimitedRun = cmdTreeLimitedRun
                + " "
                + Integer.toString(timeLimit)
                + " "
                + Integer.toString(timeLimit)
                + " "
                + proverCommand;
        Process proc = Runtime.getRuntime().exec(cmdTreeLimitedRun);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        String stdout = "";
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            stdout = stdout + s + "\n";
        }
        String stderr = "";
        s = null;
        while ((s = stdError.readLine()) != null) {
            stderr += s;
        }

        if (stdout.contains("Usage:")) throw new ProverNotAvailableException("Prover could not be started");

        TPTPDefinitions.SZSDeductiveStatus status = parseSZSStatus(stdout);
        System.out.println(stdout);
        double elapsedTime = parseWC(stdout);
        ProveResult ret = new ProveResult();
        ret.elapsedTime = elapsedTime;
        ret.status = status;
        ret.stdout = stdout;
        ret.stderr = stderr;
        return ret;
    }

    @Override
    public ProveResult prove(String problem, String source, String prover, int timeLimit) throws IOException, ProverNotAvailableException, ProverResultNotInterpretableException {
        String cmdProver = Config.getLocalProverCommand(prover);
        ProveResult r = prove(problem,cmdProver,timeLimit);
        return new ProveResult(problem, source, prover, r.stdout, r.stderr, r.status, r.elapsedTime, timeLimit);
    }

    private double parseCPU(String s){
        int CPUStart = s.indexOf("FINAL WATCH:") + 12;
        int CPUEnd = s.indexOf("CPU");
        return Double.parseDouble(s.substring(CPUStart,CPUEnd).trim());
    }

    private double parseWC(String s){
        int WCStart = s.indexOf("CPU") + 3;
        int WCEnd = s.indexOf("WC");
        return Double.parseDouble(s.substring(WCStart,WCEnd).trim());
    }

    private TPTPDefinitions.SZSDeductiveStatus parseSZSStatus(String s) throws ProverResultNotInterpretableException {
        int statusBeginning = s.indexOf("SZS status");
        if (statusBeginning == -1) throw new ProverResultNotInterpretableException("Could not find SZS Status beginning");
        statusBeginning += 11;
        String status = s.substring(statusBeginning);
        int statusEnd = status.indexOf("\n");
        if (statusEnd == -1) throw new ProverResultNotInterpretableException("Could not find SZS Status ending");
        status = status.substring(0,statusEnd).trim();
        return TPTPDefinitions.getStatusFromString(status);
    }


}
