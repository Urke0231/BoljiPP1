// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class YesListaNesto extends ListaNesto {

    private String a;
    private Nesto Nesto;
    private ListaNesto ListaNesto;

    public YesListaNesto (String a, Nesto Nesto, ListaNesto ListaNesto) {
        this.a=a;
        this.Nesto=Nesto;
        if(Nesto!=null) Nesto.setParent(this);
        this.ListaNesto=ListaNesto;
        if(ListaNesto!=null) ListaNesto.setParent(this);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a=a;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Nesto!=null) Nesto.accept(visitor);
        if(ListaNesto!=null) ListaNesto.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Nesto!=null) Nesto.traverseTopDown(visitor);
        if(ListaNesto!=null) ListaNesto.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Nesto!=null) Nesto.traverseBottomUp(visitor);
        if(ListaNesto!=null) ListaNesto.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesListaNesto(\n");

        buffer.append(" "+tab+a);
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
        buffer.append(") [YesListaNesto]");
        return buffer.toString();
    }
}
