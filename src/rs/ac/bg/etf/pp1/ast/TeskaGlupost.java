// generated with ast extension for cup
// version 0.8
// 21/5/2024 19:3:23


package rs.ac.bg.etf.pp1.ast;

public class TeskaGlupost extends ArrayName {

    private String name;

    public TeskaGlupost (String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TeskaGlupost(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TeskaGlupost]");
        return buffer.toString();
    }
}
