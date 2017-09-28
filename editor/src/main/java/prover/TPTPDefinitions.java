package prover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TPTPDefinitions {

    // TPTP Dialects
    public enum TPTPDialect {THF,TFX,TFF,TCF,FOF,CNF}
    public enum TPTPSubDialect {THF,TH0,TH1,TFX,TFF,TF0,TF1,TCF,FOF,CNF}
    private static Map<TPTPSubDialect,TPTPDialect> tptpSubDialectTPTPDialectMap;
    static{
        tptpSubDialectTPTPDialectMap = new HashMap<>();
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.THF,TPTPDialect.THF);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.TH0,TPTPDialect.THF);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.TH1,TPTPDialect.THF);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.TFX,TPTPDialect.TFX);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.TFF,TPTPDialect.TFF);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.TF0,TPTPDialect.TFF);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.TF1,TPTPDialect.TFF);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.TCF,TPTPDialect.TCF);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.FOF,TPTPDialect.FOF);
        tptpSubDialectTPTPDialectMap.put(TPTPSubDialect.CNF,TPTPDialect.CNF);
    }
    public static TPTPDialect getTPTPDialectFromTPTPSubDialect(TPTPSubDialect subdialect){
        return tptpSubDialectTPTPDialectMap.get(subdialect);
    }
    private static Map<TPTPDialect,List<TPTPSubDialect>> tptpDialectTPTPSubDialectMap;
    static{
        tptpDialectTPTPSubDialectMap = new HashMap<>();
        for (TPTPDialect dialect : TPTPDialect.values()){
            tptpDialectTPTPSubDialectMap.put(dialect, new ArrayList<>());
        }
        for (TPTPSubDialect subDialect : TPTPSubDialect.values()){
            List<TPTPSubDialect> subDialectsList = tptpDialectTPTPSubDialectMap.get(getTPTPDialectFromTPTPSubDialect(subDialect));
            subDialectsList.add(subDialect);

        }
    }
    public static List<TPTPSubDialect> getTPTPSubDialectsFromTPTPDialect(TPTPDialect dialect){
        return tptpDialectTPTPSubDialectMap.get(dialect);
    }

    // SZS Deductive Status
    public enum SZSDeductiveStatus {SAT,THM,EQV,WTH,TAC,ETH,TAU,CAX,SCA,TCA,CSA,CTH,CEQ,WCT,UNC,ECT,UNS,SCC,UCA,NOC}
    private static Map<SZSDeductiveStatus,String> szsDeductiveStatusStringMap;
    static {
        szsDeductiveStatusStringMap = new HashMap<>();
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.SAT, "Satisfiable");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.THM, "Theorem");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.EQV, "Equivalent");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.WTH, "WeakerTheorem");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.TAC, "TautologousConclusion");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.ETH, "EquivalentTheorem");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.TAU, "Tautology");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.CAX, "ContradictoryAxioms");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.SCA, "SatisfiableConclusionContradictoryAxioms");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.TCA, "TautologousConclusionContradictoryAxioms");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.CSA, "CounterSatisfiable");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.CTH, "CounterTheorem");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.CEQ, "CounterEquivalent");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.WCT, "WeakerCounterTheorem");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.UNC, "UnsatisfiableConclusion");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.ECT, "EquivalentCounterTheorem");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.UNS, "Unsatisfiable");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.SCC, "SatisfiableCounterConclusionContradictoryAxioms");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.UCA, "UnsatisfiableConclusionContradictoryAxioms");
        szsDeductiveStatusStringMap.put(SZSDeductiveStatus.NOC, "NoConsequence");
    }
    public static String getStringFromStatus(SZSDeductiveStatus status){
        return szsDeductiveStatusStringMap.get(status);
    }
    private static Map<String,SZSDeductiveStatus> szsStringDeductiveStatusMap;
    static {
        szsStringDeductiveStatusMap = new HashMap<>();
        for (SZSDeductiveStatus status : SZSDeductiveStatus.values()){
            szsStringDeductiveStatusMap.put(szsDeductiveStatusStringMap.get(status).toLowerCase(),status);
        }
    }
    public static SZSDeductiveStatus getStatusFromString(String s){
        return szsStringDeductiveStatusMap.getOrDefault(s.toLowerCase(),null);
    }

}
