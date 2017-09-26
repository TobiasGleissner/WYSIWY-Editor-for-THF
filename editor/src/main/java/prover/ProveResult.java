package prover;

public class ProveResult {
    public String problem;
    public String source;
    public String prover;
    public String stdout;
    public String stderr;
    public TPTPDefinitions.SZSDeductiveStatus status;
    public double elapsedTime;
    public double timelimit;

    public ProveResult(){}
    public ProveResult(String problem, String source, String prover, String stdout, String stderr, TPTPDefinitions.SZSDeductiveStatus status, double elapsedTime, double timelimit) {
        this.problem = problem;
        this.source = source;
        this.prover = prover;
        this.stdout = stdout;
        this.stderr = stderr;
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
        //sb.append("\nProblem:");
        //sb.append(problem);
        return sb.toString();
    }
}
