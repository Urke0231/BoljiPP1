// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String varName;
    private ListaZagrada ListaZagrada;
    private ListaVarDecl ListaVarDecl;

    public VarDecl (Type Type, String varName, ListaZagrada ListaZagrada, ListaVarDecl ListaVarDecl) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.ListaZagrada=ListaZagrada;
        if(ListaZagrada!=null) ListaZagrada.setParent(this);
        this.ListaVarDecl=ListaVarDecl;
        if(ListaVarDecl!=null) ListaVarDecl.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ListaZagrada getListaZagrada() {
        return ListaZagrada;
    }

    public void setListaZagrada(ListaZagrada ListaZagrada) {
        this.ListaZagrada=ListaZagrada;
    }

    public ListaVarDecl getListaVarDecl() {
        return ListaVarDecl;
    }

    public void setListaVarDecl(ListaVarDecl ListaVarDecl) {
        this.ListaVarDecl=ListaVarDecl;
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
        if(ListaZagrada!=null) ListaZagrada.accept(visitor);
        if(ListaVarDecl!=null) ListaVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ListaZagrada!=null) ListaZagrada.traverseTopDown(visitor);
        if(ListaVarDecl!=null) ListaVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ListaZagrada!=null) ListaZagrada.traverseBottomUp(visitor);
        if(ListaVarDecl!=null) ListaVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ListaZagrada!=null)
            buffer.append(ListaZagrada.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListaVarDecl!=null)
            buffer.append(ListaVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
