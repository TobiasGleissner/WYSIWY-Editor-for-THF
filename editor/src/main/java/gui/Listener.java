package gui;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import java.lang.CharSequence;

public class Listener implements ListChangeListener<CharSequence>
{
    @Override
    public void onChanged(Change<? extends CharSequence> c)
    {
        System.out.println("change!");
    }
}
