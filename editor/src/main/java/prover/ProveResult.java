package prover;

public class ProveResult {
    public String problem;
    public String prover;
    public String stdout;
    public String stderr;
    public TPTPDefinitions.SZSDeductiveStatus status;
    public double wc;
    public double cpu;
    public double timelimit;
    public Prover.ProverType proverType;

    public ProveResult(){}
    public ProveResult(String problem, Prover.ProverType proverType, String prover, String stdout, String stderr, TPTPDefinitions.SZSDeductiveStatus status, double cpu, double wc, double timelimit) {
        this.problem = problem;
        this.proverType = proverType;
        this.prover = prover;
        this.stdout = stdout;
        this.stderr = stderr;
        this.status = status;
        this.wc = wc;
        this.cpu = cpu;
        this.timelimit = timelimit;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ProverType:");
        sb.append(this.proverType.name());
        sb.append("\nProver:");
        sb.append(prover);
        sb.append("\nStatus:");
        sb.append(status.name());
        sb.append("\nCPU:");
        sb.append(cpu);
        sb.append("\nWC:");
        sb.append(wc);
        sb.append("\nTime limit:");
        sb.append(timelimit);
        //sb.append("\nProblem:");
        //sb.append(problem);
        return sb.toString();
    }
}
