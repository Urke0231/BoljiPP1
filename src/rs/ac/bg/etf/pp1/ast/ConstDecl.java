// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String ident;
    private Assignop Assignop;
    private Nesto Nesto;
    private ListaNesto ListaNesto;

    public ConstDecl (Type Type, String ident, Assignop Assignop, Nesto Nesto, ListaNesto ListaNesto) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ident=ident;
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.Nesto=Nesto;
        if(Nesto!=null) Nesto.setParent(this);
        this.ListaNesto=ListaNesto;
        if(ListaNesto!=null) ListaNesto.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident=ident;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public Nesto getNesto() {
        return Nesto;
    }

    public void setNesto(Nesto Nesto) {
        this.Nesto=Nesto;
    }

    public ListaNesto getListaNesto() {
        return ListaNesto;
    }

    public void setListaNesto(ListaNesto ListaNesto) {
        this.ListaNesto=ListaNesto;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(Nesto!=null) Nesto.accept(visitor);
        if(ListaNesto!=null) ListaNesto.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(Nesto!=null) Nesto.traverseTopDown(visitor);
        if(ListaNesto!=null) ListaNesto.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(Nesto!=null) Nesto.traverseBottomUp(visitor);
        if(ListaNesto!=null) ListaNesto.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+ident);
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Nesto!=null)
            buffer.append(Nesto.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListaNesto!=null)
            buffer.append(ListaNesto.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
