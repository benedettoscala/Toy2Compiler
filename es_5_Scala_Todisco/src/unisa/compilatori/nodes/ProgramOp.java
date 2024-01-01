package unisa.compilatori.nodes;


import unisa.compilatori.semantic.visitor.Visitable;

import unisa.compilatori.semantic.symboltable.*;
import unisa.compilatori.semantic.visitor.Visitor;
import javax.swing.tree.DefaultMutableTreeNode;

public class ProgramOp extends DefaultMutableTreeNode implements Visitable {
    private Procedure proc;
    private IterWithoutProcedure iterWithoutProcedure;
    private IterOp iterOp;

    private SymbolTable table;

    public void setTable(SymbolTable table) {
        this.table = table;
    }

    public SymbolTable getTable() {
        return this.table;
}
    public ProgramOp(Procedure proc,
                     IterOp iterWithoutProcedure,
                     IterOp iterOp) {
        //this.proc = proc;
        //this.iterWithoutProcedure = iterWithoutProcedure;
        //iterOp.getProcedures().add(proc);
        this.iterOp = IterOp.mergeIterOps(iterOp,proc, iterWithoutProcedure);

        super.add(this.iterOp);
    }

    public ProgramOp(Procedure proc,
                     IterOp iterOp) {
        this.proc = proc;
        this.iterOp = iterOp;

        super.add(proc);
        super.add(iterOp);
    }

    public Procedure getProc() {
        return proc;
    }

    public void setProc(Procedure proc) {
        this.proc = proc;
    }

    public IterWithoutProcedure getIterWithoutProcedure() {
        return iterWithoutProcedure;
    }

    public void setIterWithoutProcedure(IterWithoutProcedure iterWithoutProcedure) {
        this.iterWithoutProcedure = iterWithoutProcedure;
    }

    public IterOp getIterOp() {
        return iterOp;
    }

    public void setIterOp(IterOp iterOp) {
        this.iterOp = iterOp;
    }

    @Override
    public String toString() {
        return "ProgramOp{" +
                "proc=" + proc +
                ", iterWithoutProcedure=" + iterWithoutProcedure +
                ", iterOp=" + iterOp +
                '}';
    }

    @Override
    public <T> T accept(Visitor<T> visitor) throws RuntimeException {
        return visitor.visit(this);
    }
}
