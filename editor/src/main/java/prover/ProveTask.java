package prover;

import java.io.IOException;

import javafx.concurrent.Task;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;

import prover.ProveResult;
import prover.Prover;
import prover.LocalProver;

import util.either.Either;
import util.either.Left;
import util.either.Right;

public class ProveTask extends Task<Either<ProveResult,String>>
{
    private Prover.ProverType proverType;
    private String problemWithResolvedIncludes;
    private String proverName;
    private int timeLimit;

    public ProveTask(
        Prover.ProverType proverType,
        String problemWithResolvedIncludes,
        String proverName,
        int timelimit
    )
    {
        this.proverType = proverType;
        this.problemWithResolvedIncludes = problemWithResolvedIncludes;
        this.proverName = proverName;
        this.timeLimit = timeLimit;
    }

    @Override
    protected Either<ProveResult,String> call() throws Exception
    {
        try
        {
            return call_();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Right<>("Internal error when running the prover: " + e);
        }
    }

    protected Either<ProveResult,String> call_() throws Exception
    {
        SystemOnTPTPProver systemOnTPTPProver = null;
        LocalProver localProver = null;

        switch(proverType) {
            case LOCAL_PROVER:
                localProver = LocalProver.getInstance();
                try {
                    return new Left<>(localProver.prove(problemWithResolvedIncludes, proverName, timeLimit));
                } catch (IOException e) {
                    return new Right<>("Could not create a temporary file containing the problem.");
                } catch (ProverNotAvailableException e) {
                    return new Right<>("The selected prover does not exist or is malfunctioning. ProverName='" + proverName + "' ProverType='" + proverType.name() + "'.");
                } catch (ProverResultNotInterpretableException e) {
                    return new Right<>("The selected prover does not exist or is malfunctioning. ProverName='" + proverName + "' ProverType='" + proverType.name() + "'\nErrorMessage='" + e.getMessage() + "'\n ProverOutput='"  + e.getProverOutput() + "'.");
                }
            case SYSTEMONTPTP_DEFAULT_PROVER:
                try {
                    systemOnTPTPProver = SystemOnTPTPProver.getInstance();
                } catch (IOException e) {
                    return new Right<>("Remote provers are not available.");
                }
                try {
                    return new Left<>(systemOnTPTPProver.prove(problemWithResolvedIncludes, proverName, timeLimit));
                } catch (ProverNotAvailableException e) {
                    return new Right<>("The selected prover does not exist or is malfunctioning. ProverName='" + proverName + "' ProverType='" + proverType.name() + "'.");
                } catch (ProverResultNotInterpretableException e) {
                    return new Right<>("The selected prover does not exist or is malfunctioning. ProverName='" + proverName + "' ProverType='" + proverType.name() + "'\nErrorMessage='" + e.getMessage() + "'\n ProverOutput='" + e.getProverOutput() + "'.");
                }
            case SYSTEMONTPTP_CUSTOM_PROVER:
                try {
                    systemOnTPTPProver = SystemOnTPTPProver.getInstance();
                } catch (IOException e) {
                    return new Right<>("Remote provers are not available.");
                }
                String systemOnTPTPProverName = systemOnTPTPProver.getCustomProverSystemOnTPTPName(proverName);
                String proverCommand = systemOnTPTPProver.getCustomProverCommand(proverName);
                try {
                    return new Left<>(systemOnTPTPProver.prove(problemWithResolvedIncludes,systemOnTPTPProverName,proverCommand,timeLimit));
                } catch (ProverNotAvailableException e) {
                    return new Right<>("The selected prover does not exist or is malfunctioning. ProverName='" + proverName + "' SystemOnTPTPProverName='" + systemOnTPTPProverName + "' ProverCommand='" + proverCommand + "' ProverType='" + proverType.name() + "'.");
                } catch (ProverResultNotInterpretableException e) {
                    return new Right<>("The selected prover does not exist or is malfunctioning. ProverName='" + proverName + "' SystemOnTPTPProverName='" + systemOnTPTPProverName + "' ProverCommand='" + proverCommand + "' ProverType='" + proverType.name() + "'\nErrorMessage='" + e.getMessage() + "'\n ProverOutput='" + e.getProverOutput() + "'.");
                }
        };

        return new Right<>("Internal error in ProverWorker.call");
    }
}
