package unisa.compilatori.nodes;

import unisa.compilatori.nodes.expr.Identifier;
import unisa.compilatori.semantic.visitor.Visitable;
import unisa.compilatori.semantic.visitor.Visitor;
import unisa.compilatori.semantic.symboltable.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.Optional;

public class Procedure extends DefaultMutableTreeNode implements Visitable {
    private Identifier id;
    private ArrayList<CallableParam> procParamDeclList;
    private Body body;

    private SymbolTable table;

    public SymbolTable getTable() {
        return table;
    }

    public void setTable(SymbolTable table) {
        this.table = table;
    }

    public Procedure(Identifier id,
                     ArrayList<CallableParam> procParamDeclList,
                     Body body) {
        this.id = id;
        Optional.of(procParamDeclList).ifPresent(lista -> this.procParamDeclList=lista);

        this.body = body;

        super.add(id);
        Optional.of(procParamDeclList).ifPresent(param -> param.forEach(p->super.add(p)));
        //procParamDeclList.forEach(param -> super.add(param));
        super.add(body);
    }

    public Procedure(Identifier id, Body body) {
        this.id = id;
        this.body = body;

        this.procParamDeclList = new ArrayList<>();
        super.add(id);
        super.add(body);
    }

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public ArrayList<CallableParam> getProcParamDeclList() {
        return procParamDeclList;
    }

    public void setProcParamDeclList(ArrayList<CallableParam> procParamDeclList) {
        this.procParamDeclList = procParamDeclList;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", procParamDeclList=" + procParamDeclList +
                ", body=" + body +
                '}';
    }

    @Override
    public <T> T accept(Visitor<T> visitor) throws RuntimeException {
        return visitor.visit(this);
    }
}
