package gui.fileStructure;

public class Structure {
    public enum StructureElement{CONSTANT,TYPE,AXIOM,CONJECTURE,DEFINITION}
    private static Structure instance;
    private Structure(){}
    public static Structure getInstance(){
        if (instance == null) instance = new Structure();
        return instance;
    }
}
