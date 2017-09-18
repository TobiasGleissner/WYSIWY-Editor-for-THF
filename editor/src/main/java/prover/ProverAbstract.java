package prover;

public abstract class ProverAbstract implements Prover {
    SZSOntology.SZSStatus status = null;
    String prover = null;
    double elapsedTime;

    public SZSOntology.SZSStatus getSZSStatus() {
        return this.status;
    }

    public String getProver(){
        return this.prover;
    }

    public double getElapsedTime(){
        return elapsedTime;
    }

}
