package prover;

public class SZSOntology {

    public enum SZSDeductiveStatus {SAT,THM,EQV,WTH,TAC,ETH,TAU,CAX,SCA,TCA,CSA,CTH,CEQ,WCT,UNC,ECT,UNS,SCC,UCA,NOC}
    public static SZSDeductiveStatus getStatusFromString(String s){
        switch (s){
            case "Satisfiable": return SZSDeductiveStatus.SAT;
            case "Theorem": return SZSDeductiveStatus.THM;
            case "Equivalent": return SZSDeductiveStatus.EQV;
            case "WeakerTheorem": return SZSDeductiveStatus.WTH;
            case "TautologousConclusion": return SZSDeductiveStatus.TAC;
            case "EquivalentTheorem": return SZSDeductiveStatus.ETH;
            case "Tautology": return SZSDeductiveStatus.TAU;
            case "ContradictoryAxioms": return SZSDeductiveStatus.CAX;
            case "SatisfiableConclusionContradictoryAxioms": return SZSDeductiveStatus.SCA;
            case "TautologousConclusionContradictoryAxioms": return SZSDeductiveStatus.TCA;
            case "CounterSatisfiable": return SZSDeductiveStatus.CSA;
            case "CounterTheorem": return SZSDeductiveStatus.CTH;
            case "CounterEquivalent": return SZSDeductiveStatus.CEQ;
            case "WeakerCounterTheorem": return SZSDeductiveStatus.WCT;
            case "UnsatisfiableConclusion": return SZSDeductiveStatus.UNC;
            case "EquivalentCounterTheorem": return SZSDeductiveStatus.ECT;
            case "Unsatisfiable": return SZSDeductiveStatus.UNS;
            case "SatisfiableCounterConclusionContradictoryAxioms": return SZSDeductiveStatus.SCC;
            case "UnsatisfiableConclusionContradictoryAxioms": return SZSDeductiveStatus.UCA;
            case "NoConsequence": return SZSDeductiveStatus.NOC;
        }
        return null;
    }

}
