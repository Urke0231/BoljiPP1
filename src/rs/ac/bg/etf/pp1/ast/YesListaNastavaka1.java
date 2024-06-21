// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class YesListaNastavaka1 extends ListaNastavaka1 {

    private Expr Expr;
    private ListaNastavaka1 ListaNastavaka1;

    public YesListaNastavaka1 (Expr Expr, ListaNastavaka1 ListaNastavaka1) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.ListaNastavaka1=ListaNastavaka1;
        if(ListaNastavaka1!=null) ListaNastavaka1.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public ListaNastavaka1 getListaNastavaka1() {
        return ListaNastavaka1;
    }

    public void setListaNastavaka1(ListaNastavaka1 ListaNastavaka1) {
        this.ListaNastavaka1=ListaNastavaka1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(ListaNastavaka1!=null) ListaNastavaka1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(ListaNastavaka1!=null) ListaNastavaka1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(ListaNastavaka1!=null) ListaNastavaka1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesListaNastavaka1(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListaNastavaka1!=null)
            buffer.append(ListaNastavaka1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesListaNastavaka1]");
        return buffer.toString();
    }
}
