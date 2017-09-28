package parser;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import util.tree.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.function.Predicate;

/**
 * default tree listener implementation
 */
public class DefaultTreeListener implements ParseTreeListener {

    protected Parser parser;
    private Map<String, Integer> rmap;

    private Stack<String> sctx = new Stack<String>();
    private Node nodeptr = null;
    private Node root = null;
    private Predicate<String> filter = null;

    private CommonTokenStream tokens;

    public List<Token> getHiddenTokens() {
        return hiddenTokens;
    }

    private List<Token> hiddenTokens;


    /**
     * constructor
     */
    public DefaultTreeListener(CommonTokenStream tokens) {
        this(x -> !x.isEmpty(),tokens);
    }

    /**
     * construtor
     * @param filter condition that has to hold for every node
     */
    public DefaultTreeListener(Predicate<String> filter, CommonTokenStream tokens) {
        this.sctx.add("S");
        Node root = new Node("root","root");
        this.root = root;
        this.nodeptr = root;
        this.filter = filter;
        this.parser = null;
        this.rmap = null;
        this.tokens = tokens;
        this.hiddenTokens = new ArrayList<>();
        List<Token> ht = tokens.getHiddenTokensToRight(0);
        if (ht != null) this.hiddenTokens.addAll(ht);
    }

    public String getRuleByKey(int key) {

        for (Map.Entry<String, Integer> e : this.rmap.entrySet()) {
            if (e.getValue() == key)
                return e.getKey();
        }
        return null;
    }

    protected void setParser(Parser p) {
        this.parser = p;
        this.rmap = this.parser.getRuleIndexMap();
    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        Node n = new Node(nodeptr, "terminal", terminalNode.getText());
        n.charPositionInLine = terminalNode.getSymbol().getCharPositionInLine();
        n.line = terminalNode.getSymbol().getLine();
        n.startIndex = terminalNode.getSymbol().getStartIndex();
        n.stopIndex = terminalNode.getSymbol().getStopIndex();
        nodeptr.addChild(n);
        List<Token> ht = tokens.getHiddenTokensToRight(terminalNode.getSymbol().getTokenIndex());
        if (ht != null) this.hiddenTokens.addAll(ht);
    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        String rule = this.getRuleByKey(ctx.getRuleIndex());
        if (this.filter.test(rule)) {
            Node n = new Node(nodeptr, rule/*, ctx.getText()*/);
            n.charPositionInLine = ctx.getStart().getCharPositionInLine();
            n.line = ctx.getStart().getLine();
            n.startIndex = ctx.getStart() == null ? 0 : ctx.getStart().getStartIndex();
            n.stopIndex = ctx.getStop() == null ? 0 : ctx.getStop().getStopIndex();
            nodeptr.addChild(n);
            nodeptr = n;
        }
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        String rule = this.getRuleByKey(ctx.getRuleIndex());
        if (this.filter.test(rule)) {
            this.nodeptr = this.nodeptr.getParent();
        }


    }

    public Node getRootNode() {
        return this.root;
    }

    /*
    public Set<Node> getNodes() {
        return this.getNodes();
    }*/


}
