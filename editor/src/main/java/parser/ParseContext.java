package parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import util.tree.Node;

import java.util.List;


public class ParseContext {
    private String parseError;
    private Node root;
    private ParserRuleContext parserRuleContext;

    public List<Token> getComments() {
        return comments;
    }

    public void setComments(List<Token> comments) {
        this.comments = comments;
        /*
        comments.stream().forEach(c->{
            System.out.println("toString:"+c);
            System.out.println("getLine:"+c.getLine());
            System.out.println("getStartIndex:" + c.getStartIndex());
            System.out.println("getStopIndex:" + c.getStopIndex());
            System.out.println("getText:"+c.getText());
            System.out.println("============================================");
        });
        */
    }

    private List<Token> comments;

    public ParseContext() {
        parseError = null;
        root = null;
        parserRuleContext = null;
        comments = null;
    }

    public void setParseError(String parseError) {
        this.parseError = parseError;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setParserRuleContext(ParserRuleContext parserRuleContext) {
        this.parserRuleContext = parserRuleContext;
    }

    public boolean hasParseError(){
        return parseError != null;
    }

    public String getParseError() {
        return parseError;
    }

    public Node getRoot() {
        return this.root;
    }

    public ParserRuleContext getParserRuleContext() {
        return parserRuleContext;
    }
}
