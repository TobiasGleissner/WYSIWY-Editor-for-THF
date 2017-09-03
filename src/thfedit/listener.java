package thfedit;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import java.lang.CharSequence;

public class listener implements ListChangeListener<CharSequence>
{
    @Override
    public void onChanged(Change<? extends CharSequence> c)
    {
        System.out.println("change!");
    }
}
