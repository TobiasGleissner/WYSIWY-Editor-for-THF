package util;

import java.util.Comparator;

public class SpanElement implements Comparator<SpanElement>, Comparable<SpanElement> {
    
    int startIndex;
    int endIndex;
    String tag;
    
    public SpanElement(int startIndex, int endIndex, String tag) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.tag = tag;
    }
    
    public int getStartIndex() {
        return startIndex;
    }
    public int getEndIndex() {
        return endIndex;
    }
    public String getTag() {
        return tag;
    }

    @Override
    public int compare(SpanElement arg0, SpanElement arg1) {
        if (arg0.getStartIndex() != arg1.getStartIndex()) 
            return arg0.getStartIndex() - arg1.getStartIndex();
        else if (arg0.getEndIndex() != arg1.getEndIndex())
            return -(arg0.getEndIndex() - arg1.getEndIndex());
        else
            return arg0.getTag().compareTo(arg1.getTag());
    }
    
    public int compareOnlyRange(SpanElement arg0, SpanElement arg1) {
        if (arg0.getStartIndex() != arg1.getStartIndex()) 
            return arg0.getStartIndex() - arg1.getStartIndex();
        else 
            return -(arg0.getEndIndex() - arg1.getEndIndex());
    }

    @Override
    public int compareTo(SpanElement arg0) {
        return compare(this, arg0);
    }
    
    public int compareRangeTo(SpanElement arg0) {
        return compareOnlyRange(this, arg0);
    }
    
    public String toString() {
        return "["+tag+", "+startIndex+", "+endIndex+"]";
    }
}
