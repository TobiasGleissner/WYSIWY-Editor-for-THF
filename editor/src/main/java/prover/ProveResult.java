package prover;

public class ProveResult {
    String problem;
    String source;
    String prover;
    SZSOntology.SZSDeductiveStatus status;
    double elapsedTime;
    double timelimit;

    public ProveResult(String problem, String source, String prover, SZSOntology.SZSDeductiveStatus status, double elapsedTime, double timelimit) {
        this.problem = problem;
        this.source = source;
        this.prover = prover;
        this.status = status;
        this.elapsedTime = elapsedTime;
        this.timelimit = timelimit;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Source:");
        sb.append(this.source);
        sb.append("\nProver:");
        sb.append(prover);
        sb.append("\nStatus:");
        sb.append(status.name());
        sb.append("\nElapsed time:");
        sb.append(elapsedTime);
        sb.append("\nTime limit:");
        sb.append(timelimit);
        sb.append("\nProblem:");
        sb.append(problem);
        return sb.toString();
    }
}
