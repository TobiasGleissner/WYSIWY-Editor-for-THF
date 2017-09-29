package exceptions;

public class ProverResultNotInterpretableException extends Exception{
    private String proverOutput = null;
    public ProverResultNotInterpretableException(String msg,String proverOutput){super(msg); this.proverOutput = proverOutput;}
    public String getProverOutput(){return proverOutput;}
}
