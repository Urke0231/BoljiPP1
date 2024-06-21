// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class YesFormalParamDecl extends FormalParamDecl {

    private Type Type;
    private String varName;
    private ListaZagrada ListaZagrada;

    public YesFormalParamDecl (Type Type, String varName, ListaZagrada ListaZagrada) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.ListaZagrada=ListaZagrada;
        if(ListaZagrada!=null) ListaZagrada.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ListaZagrada!=null) ListaZagrada.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ListaZagrada!=null) ListaZagrada.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ListaZagrada!=null) ListaZagrada.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesFormalParamDecl(\n");

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

        buffer.append(tab);
        buffer.append(") [YesFormalParamDecl]");
        return buffer.toString();
    }
}
