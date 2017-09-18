package prover;

public interface Prover {
    public SZSOntology.SZSStatus getSZSStatus();
    public String getProver();
    public double getElapsedTime();
}
