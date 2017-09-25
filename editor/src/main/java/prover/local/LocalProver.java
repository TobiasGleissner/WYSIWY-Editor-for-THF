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
        if (availableProvers.contains(proverName)) return;
        availableProvers.add(proverName);
        Config.setLocalProvers(availableProvers);
    }

    public void removeProver(String proverName) throws ProverNotAvailableException {
        if (!availableProvers.contains(proverName)) throw new ProverNotAvailableException("prover not available");
        Config.removePreference(proverName);
        availableProvers.remove(proverName);
        Config.setLocalProvers(availableProvers);
    }

    // may return null
    public String getProverCommand(String prover){
        return Config.getLocalProverCommand(prover);
    }

    @Override
    public ProveResult prove(String problem, String source, String prover, int timeLimit) throws IOException, ProverNotAvailableException, ProverResultNotInterpretableException {
        File tempFile = null;
        BufferedWriter writer = null;
        System.out.println(problem);
        try {
            tempFile = File.createTempFile(Config.name + "_", "_" + RandomString.getRandomString());
            writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write(problem);
            writer.close();
        } catch (IOException e) {
            throw e;
        }

        String cmdProver = Config.getLocalProverCommand(prover);
        cmdProver = cmdProver.replace("%f", tempFile.getAbsolutePath());
        cmdProver = cmdProver.replace("%t", Integer.toString(timeLimit));
        String cmdTreeLimitedRun = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        cmdTreeLimitedRun += "../scripts/TreeLimitedRun";
        cmdTreeLimitedRun = cmdTreeLimitedRun
                + " "
                + Integer.toString(timeLimit)
                + " "
                + Integer.toString(timeLimit)
                + " "
                + cmdProver;
        Process proc = Runtime.getRuntime().exec(cmdTreeLimitedRun);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        String stdout = "";
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            stdout = stdout + s + "\n";
        }
        //String stderr = "";
        //while ((s = stdError.readLine()) != null) {
        //    stderr += s;
        //}

        TPTPDefinitions.SZSDeductiveStatus status = parseSZSStatus(stdout);
        double elapsedTime = 0;
        return new ProveResult(problem, source, prover, stdout, status, elapsedTime, timeLimit);
    }

    private TPTPDefinitions.SZSDeductiveStatus parseSZSStatus(String s){
        String status = s.substring(s.indexOf("SZS status") + 11);
        status = status.substring(0,status.indexOf("\n")).trim();
        return TPTPDefinitions.getStatusFromString(status);
    }


}
