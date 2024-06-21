// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class YesListaVarDecl extends ListaVarDecl {

    private String varName;
    private ListaZagrada ListaZagrada;
    private ListaVarDecl ListaVarDecl;

    public YesListaVarDecl (String varName, ListaZagrada ListaZagrada, ListaVarDecl ListaVarDecl) {
        this.varName=varName;
        this.ListaZagrada=ListaZagrada;
        if(ListaZagrada!=null) ListaZagrada.setParent(this);
        this.ListaVarDecl=ListaVarDecl;
        if(ListaVarDecl!=null) ListaVarDecl.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListaZagrada!=null) ListaZagrada.accept(visitor);
        if(ListaVarDecl!=null) ListaVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListaZagrada!=null) ListaZagrada.traverseTopDown(visitor);
        if(ListaVarDecl!=null) ListaVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListaZagrada!=null) ListaZagrada.traverseBottomUp(visitor);
        if(ListaVarDecl!=null) ListaVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesListaVarDecl(\n");

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
        buffer.append(") [YesListaVarDecl]");
        return buffer.toString();
    }
}
