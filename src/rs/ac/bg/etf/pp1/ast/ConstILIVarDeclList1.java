// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class ConstILIVarDeclList1 extends ConstILIVarDeclList {

    private ConstILIVarDeclList ConstILIVarDeclList;
    private ConstILIVarDecl ConstILIVarDecl;

    public ConstILIVarDeclList1 (ConstILIVarDeclList ConstILIVarDeclList, ConstILIVarDecl ConstILIVarDecl) {
        this.ConstILIVarDeclList=ConstILIVarDeclList;
        if(ConstILIVarDeclList!=null) ConstILIVarDeclList.setParent(this);
        this.ConstILIVarDecl=ConstILIVarDecl;
        if(ConstILIVarDecl!=null) ConstILIVarDecl.setParent(this);
    }

    public ConstILIVarDeclList getConstILIVarDeclList() {
        return ConstILIVarDeclList;
    }

    public void setConstILIVarDeclList(ConstILIVarDeclList ConstILIVarDeclList) {
        this.ConstILIVarDeclList=ConstILIVarDeclList;
    }

    public ConstILIVarDecl getConstILIVarDecl() {
        return ConstILIVarDecl;
    }

    public void setConstILIVarDecl(ConstILIVarDecl ConstILIVarDecl) {
        this.ConstILIVarDecl=ConstILIVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstILIVarDeclList!=null) ConstILIVarDeclList.accept(visitor);
        if(ConstILIVarDecl!=null) ConstILIVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstILIVarDeclList!=null) ConstILIVarDeclList.traverseTopDown(visitor);
        if(ConstILIVarDecl!=null) ConstILIVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstILIVarDeclList!=null) ConstILIVarDeclList.traverseBottomUp(visitor);
        if(ConstILIVarDecl!=null) ConstILIVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstILIVarDeclList1(\n");

        if(ConstILIVarDeclList!=null)
            buffer.append(ConstILIVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstILIVarDecl!=null)
            buffer.append(ConstILIVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstILIVarDeclList1]");
        return buffer.toString();
    }
}
