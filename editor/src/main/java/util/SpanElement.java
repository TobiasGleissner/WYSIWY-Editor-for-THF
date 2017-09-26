package util;

public class SpanElement {
    
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

}
