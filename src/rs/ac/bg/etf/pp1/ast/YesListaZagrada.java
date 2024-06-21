// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class YesListaZagrada extends ListaZagrada {

    private ListaZagrada ListaZagrada;

    public YesListaZagrada (ListaZagrada ListaZagrada) {
        this.ListaZagrada=ListaZagrada;
        if(ListaZagrada!=null) ListaZagrada.setParent(this);
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
        if(ListaZagrada!=null) ListaZagrada.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListaZagrada!=null) ListaZagrada.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListaZagrada!=null) ListaZagrada.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesListaZagrada(\n");

        if(ListaZagrada!=null)
            buffer.append(ListaZagrada.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesListaZagrada]");
        return buffer.toString();
    }
}
