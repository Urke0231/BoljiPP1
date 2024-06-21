// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class Array extends Designator {

    private ArrayName ArrayName;
    private ListaNastavaka ListaNastavaka;

    public Array (ArrayName ArrayName, ListaNastavaka ListaNastavaka) {
        this.ArrayName=ArrayName;
        if(ArrayName!=null) ArrayName.setParent(this);
        this.ListaNastavaka=ListaNastavaka;
        if(ListaNastavaka!=null) ListaNastavaka.setParent(this);
    }

    public ArrayName getArrayName() {
        return ArrayName;
    }

    public void setArrayName(ArrayName ArrayName) {
        this.ArrayName=ArrayName;
    }

    public ListaNastavaka getListaNastavaka() {
        return ListaNastavaka;
    }

    public void setListaNastavaka(ListaNastavaka ListaNastavaka) {
        this.ListaNastavaka=ListaNastavaka;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrayName!=null) ArrayName.accept(visitor);
        if(ListaNastavaka!=null) ListaNastavaka.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayName!=null) ArrayName.traverseTopDown(visitor);
        if(ListaNastavaka!=null) ListaNastavaka.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayName!=null) ArrayName.traverseBottomUp(visitor);
        if(ListaNastavaka!=null) ListaNastavaka.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Array(\n");

        if(ArrayName!=null)
            buffer.append(ArrayName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListaNastavaka!=null)
            buffer.append(ListaNastavaka.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Array]");
        return buffer.toString();
    }
}
