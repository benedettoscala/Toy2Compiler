package unisa.compilatori.nodes.expr;

import com.sun.jdi.InvalidTypeException;
import unisa.compilatori.Token;
import unisa.compilatori.semantic.visitor.Visitable;
import unisa.compilatori.semantic.visitor.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;

public class ConstOP extends ExprOP implements Visitable {
    private String lessema;
    private String type; //indica di che tipo di costante parliamo

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    /**
     * enum che indica il tipo della costante
     */
    public enum Kind {
        INTEGER, //costante intera
        STRING,  //costante stringa
        BOOLEAN,
        REAL,
    }

    private Kind kind;
    /**
     * @param token è il token che viene passato
     * @param type
     *
     * @throws InvalidTypeException se l'oggetto che viene passato non è un token.
     * */
    public ConstOP(Object token, Kind type) throws InvalidTypeException {
        super(Mode.CONST);
        if (token instanceof Token){
            Token tok = (Token) token;
            this.lessema = tok.getAttribute();
            this.kind = type;
            super.add(new DefaultMutableTreeNode(lessema));
            super.add(new DefaultMutableTreeNode(kind));
        } else {
            throw new InvalidTypeException();
        }

    }

    public ConstOP(Object token, Kind type, Mode mode) throws InvalidTypeException {
        super(mode);
        if (token instanceof Token){
            Token tok = (Token) token;
            this.lessema = tok.getAttribute();
            this.kind = type;
            super.add(new DefaultMutableTreeNode(lessema));
            super.add(new DefaultMutableTreeNode(kind));
        } else {
            throw new InvalidTypeException();
        }

    }

    public String getLessema() {
        return lessema;
    }

    public void setLessema(String lessema) {
        this.lessema = lessema;
    }

    /**
     * restituisce il tipo della costante
     * possibili valori
     * @return
     */
    public Kind getType() {
        return this.kind;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Const{" +
                "lessema='" + lessema + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
