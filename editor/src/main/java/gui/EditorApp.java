package gui;

import exceptions.NameAlreadyInUseException;
import exceptions.ProverNotAvailableException;
import prover.local.LocalProver;

import java.util.List;

import static gui.Config.prefs;
import static javafx.application.Application.launch;

public class EditorApp {
    public static void main(String[] args) throws NameAlreadyInUseException, ProverNotAvailableException {
        //launch(EditorView.class,args);
        /*
        LocalProver lp = LocalProver.getInstance();
        print();
        List<String> provers = Config.getLocalProvers();
        provers.add("asd2");
        provers.add("asd3");
        Config.setLocalProvers(provers);
        print();*/

        /*
        lp.addProver("name","cmd",false);
        print();
        lp.addProver("name2","cmd2",false);
        print();
        lp.removeProver("name");
        print();*/

    }

    private static void print(){
        System.out.println("=====");
        Config.getLocalProvers().stream().forEach(n-> System.out.println(n));
        LocalProver lp = LocalProver.getInstance();
        lp.getAvailableProvers().stream().forEach(n-> System.out.println(n));
        System.out.println("=====");

    }
}
