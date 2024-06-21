package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.AddopExpr;
import rs.ac.bg.etf.pp1.ast.AddopPlus;
import rs.ac.bg.etf.pp1.ast.Array;
import rs.ac.bg.etf.pp1.ast.Assignment;
import rs.ac.bg.etf.pp1.ast.BOOLConst;
import rs.ac.bg.etf.pp1.ast.CHARConst;
import rs.ac.bg.etf.pp1.ast.ConstDecl;
import rs.ac.bg.etf.pp1.ast.Dekrement;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAsign;
import rs.ac.bg.etf.pp1.ast.FunCall;
import rs.ac.bg.etf.pp1.ast.Inkrement;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MinusTermExpr;
import rs.ac.bg.etf.pp1.ast.MulopCelobrojno;
import rs.ac.bg.etf.pp1.ast.MulopTerm;
import rs.ac.bg.etf.pp1.ast.MulopTimes;
//import rs.ac.bg.etf.pp1.ast.NoListaNastavaka;
import rs.ac.bg.etf.pp1.ast.NoviNiz;
import rs.ac.bg.etf.pp1.ast.NumberConst;
import rs.ac.bg.etf.pp1.ast.ObicanDesignator;
import rs.ac.bg.etf.pp1.ast.PrintStmt;
import rs.ac.bg.etf.pp1.ast.ProcCall;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Range;
import rs.ac.bg.etf.pp1.ast.ReadNesto;
import rs.ac.bg.etf.pp1.ast.ReturnExpr;
import rs.ac.bg.etf.pp1.ast.ReturnNoExpr;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TeskaGlupost;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.Var;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidMethodTypeName;
import rs.ac.bg.etf.pp1.ast.YesListaNastavaka;
import rs.ac.bg.etf.pp1.ast.YesListaNumConst;
import rs.ac.bg.etf.pp1.ast.YesMethodTypeName;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor{
 private int mainPc;
 private boolean incdec=false;
 private boolean jesteNiz=false;
 public int getMainPc() {
	 return mainPc;
 }
 public void visit(ConstDecl cd) {
	 
 }

 public void visit(PrintStmt ps) {
	 // ...,w,value
	 
	 if(
		 ps.getExpr().struct.getKind()==Struct.Array
		 ) {
		 System.out.println("PRINTUJE SE NIZ");
		 Code.put(Code.dup);                 // Duplira veličinu niza na steku
	      //Code.put(Code.newarray);            // Kreira novi niz tipa int
	      //Code.put(1);                        // Tip niza (1 = int)
	      
	      //Code.put(Code.dup);                 // Duplira referencu na niz (da ne bi izgubili referencu na originalni niz)
	     // Code.put(Code.arraylength);         // Uzimamo dužinu niza

	      Code.loadConst(0);                  // Inicijalizujemo brojač na 0

	      int loopStart = Code.pc;            // Početak petlje

	      Code.put(Code.dup2);    // Duplira referencu na niz i trenutni indeks na steku
	      Zadnja2Udesno();
	      Code.put(Code.arraylength);
	      Zadnja2Udesno();
	      Code.putFalseJump(Code.gt, 0);      // Ako je indeks >= dužine niza, izađi iz petlje
	      int exitJump = Code.pc - 2;         // Memorisi lokaciju gde ce biti upisan offset za izlaz iz petlje

	      Code.put(Code.dup2);  // Duplira referencu na niz i trenutni indeks na steku
	      //Code.put(Code.dup);
	      //      Code.put(Code.dup_x1);              // Presloži da referenca na niz bude ispod indeksa
//	      Code.put(Code.aload);               // Učita vrednost sa indeksa niza na stek
//	      Code.put(Code.dup2);                // Duplira referencu na niz i trenutni indeks na steku
	      Code.put(Code.aload); // Upisuje vrednost indeksa na odgovarajuću poziciju u nizu
	      Code.loadConst(5);
	      Code.put(Code.print);
	      Code.loadConst(1);
	      Code.put(Code.add);                 // Uvećaj brojač za 1
	     // Code.put(0);                        // Lokalni indeks
	     // Code.put(1);                        // Vrednost za uvećanje

	      Code.putJump(loopStart);  // Skok na početak petlje

	      Code.fixup(exitJump);               // Popravi skok za izlazak iz petlje

	      Code.put(Code.pop);                 // Uklanja referencu na niz sa steka
	     // Code.put(Code.pop);                 // Uklanja referencu na niz sa steka
	      Code.put(Code.pop);
		 
 }
	 else {
	 if(ps.getListaNumConst() instanceof YesListaNumConst) {
		 YesListaNumConst a=(YesListaNumConst) ps.getListaNumConst();
		 Code.loadConst(a.getN1());
		 if(ps.getExpr().struct== MojTab.charType) {
			 //Code.loadConst(1);
			 Code.put(Code.bprint);
		 }
		 else {
			 //Code.loadConst(5);
			 Code.put(Code.print);
		 }
	 }
	 else {
	 
	 if(ps.getExpr().struct== MojTab.charType) {
		 Code.loadConst(1);
		 Code.put(Code.bprint);
	 }
	 else {
		 Code.loadConst(5);
		 Code.put(Code.print);
	 }
	 }
 }}
 
 public void visit(ReadNesto ps) {
	 // ...,w,value
	
	 if(ps.getDesignator().obj.getType()== MojTab.charType) {
		 //Code.loadConst(1);
		 Code.put(Code.bread);
	 }
	 else {
		// Code.loadConst(5);
		 Code.put(Code.read);
	 }
	 Code.store(ps.getDesignator().obj);
	 }
 
 
 public void visit(MinusTermExpr mt) {
	 
	 Code.loadConst(-1);
	 Code.put(Code.mul);
 }
 
 public void visit(YesMethodTypeName mtn) {
	 if("main".equalsIgnoreCase(mtn.getMethName())) {
		 mainPc=Code.pc;
	 }
	 mtn.obj.setAdr(Code.pc);
	 // Collect arguments and local variables
	 SyntaxNode methodNode=mtn.getParent();
	 
	 VarCounter varCnt=new VarCounter();
	 
	 methodNode.traverseTopDown(varCnt);
	 
	 FormParamCounter fpCnt=new FormParamCounter();
	 methodNode.traverseTopDown(fpCnt);
	 
	 // Generate entry
	 
	 Code.put(Code.enter);
	 Code.put(fpCnt.getCount());
	 Code.put(fpCnt.getCount()+varCnt.getCount());

	 
	 
	 
 }
 public void visit(VoidMethodTypeName mtn) {
	 if("main".equalsIgnoreCase(mtn.getMethName())) {
		 mainPc=Code.pc;
	 }
	 mtn.obj.setAdr(Code.pc);
	 // Collect arguments and local variables
	 SyntaxNode methodNode=mtn.getParent();
	 
	 VarCounter varCnt=new VarCounter();
	 
	 methodNode.traverseTopDown(varCnt);
	 
	 FormParamCounter fpCnt=new FormParamCounter();
	 methodNode.traverseTopDown(fpCnt);
	 
	 // Generate entry
	 
	 Code.put(Code.enter);
	 Code.put(fpCnt.getCount());
	 Code.put(fpCnt.getCount()+varCnt.getCount());

	 
	 
	 
 }
 
 public void visit(MethodDecl md) {
	 Code.put(Code.exit);
	 Code.put(Code.return_);
 }
 
 public void visit(DesignatorAsign da) {
	 Code.store(da.getDesignator().obj);
 }
  public void visit(Var v) {
	  Code.load(v.getDesignator().obj);
  }
  
  public void visit(NumberConst nc) {
		 Obj con = MojTab.insert(Obj.Con,"$",nc.struct);
		 con.setLevel(0);
		 con.setAdr(nc.getN1());
		 
		 Code.load(con);
	 }
	 public void visit(CHARConst nc) {
		 int value=nc.getC1();
		 
		 Obj con = MojTab.insert(Obj.Con,"$",MojTab.charType);
		 con.setAdr(value);
		 
		 
		 Code.load(con);
	 }
	 public void visit(BOOLConst nc) {
		 String value=nc.getB1();
		 int val=0;
		 if( value=="true") {
			 val=1;
		 }
		 Obj con = new Obj(Obj.Con,"$",MojTab.charType,val,0 );
		 
		 
		 Code.load(con);
	 }
  public void visit(NoviNiz nn) {
		 Struct ElemType=MojTab.intType;
		 
		 Code.put(Code.newarray);
		 int wordArray=1;
		 if(ElemType==MojTab.charType) {
			 wordArray=0;
		 }
		 Code.loadConst(wordArray);
	 }
  private void Zadnja3Udesno() {
	  Code.put(Code.dup_x2);
    	Code.put(Code.pop);
  }
  private void Zadnja2Udesno() {
	  Code.put(Code.dup_x1);
    	Code.put(Code.pop);
  }
  public void visit(Range nn) {
	// Code.put(Code.dup);
	  Code.put(Code.dup);                 // Duplira veličinu niza na steku
      Code.put(Code.newarray);            // Kreira novi niz tipa int
      Code.put(1);                        // Tip niza (1 = int)
      
      //Code.put(Code.dup);                 // Duplira referencu na niz (da ne bi izgubili referencu na originalni niz)
     // Code.put(Code.arraylength);         // Uzimamo dužinu niza

      Code.loadConst(0);                  // Inicijalizujemo brojač na 0

      int loopStart = Code.pc;            // Početak petlje

      Code.put(Code.dup2);    // Duplira referencu na niz i trenutni indeks na steku
      Zadnja2Udesno();
      Code.put(Code.arraylength);
      Zadnja2Udesno();
      Code.putFalseJump(Code.gt, 0);      // Ako je indeks >= dužine niza, izađi iz petlje
      int exitJump = Code.pc - 2;         // Memorisi lokaciju gde ce biti upisan offset za izlaz iz petlje

      Code.put(Code.dup2);  // Duplira referencu na niz i trenutni indeks na steku
      Code.put(Code.dup);
      //      Code.put(Code.dup_x1);              // Presloži da referenca na niz bude ispod indeksa
//      Code.put(Code.aload);               // Učita vrednost sa indeksa niza na stek
//      Code.put(Code.dup2);                // Duplira referencu na niz i trenutni indeks na steku
      Code.put(Code.astore);              // Upisuje vrednost indeksa na odgovarajuću poziciju u nizu
      Code.loadConst(1);
      Code.put(Code.add);                 // Uvećaj brojač za 1
     // Code.put(0);                        // Lokalni indeks
     // Code.put(1);                        // Vrednost za uvećanje

      Code.putJump(loopStart);  // Skok na početak petlje

      Code.fixup(exitJump);               // Popravi skok za izlazak iz petlje

      Code.put(Code.pop);                 // Uklanja referencu na niz sa steka
     // Code.put(Code.pop);                 // Uklanja referencu na niz sa steka

  }
	  

 
 public void visit(TeskaGlupost tg) {
	 Code.load(tg.obj);
	// jesteNiz=true;
 }
//public void visit(Array d) {
//	Code.load(d.getArrayName().obj);
//
//		 Obj array=d.obj;
//		 if(incdec) {
//			 Code.put(Code.dup2);
//		 }
//		 if(array.getType()==MojTab.intType) {
//			 Code.put(Code.aload);
//		 }
//		 else {
//			 Code.put(Code.baload);
//		 }
//		 //MojTab.insert(Obj.Elem,"",array.getType());
//
//}
 
 public void visit(FunCall fc) {
	 Obj fo=fc.getDesignator().obj;
	 int offset= fo.getAdr()-Code.pc;
	 Code.put(Code.call);
	 
	 Code.put2(offset);
	 
 }
 public void visit(ProcCall fc) {
	 Obj fo=fc.getDesignator().obj;
	 int offset= fo.getAdr()-Code.pc;
	 Code.put(Code.call);
	 
	 Code.put2(offset);
	 if(fc.getDesignator().obj.getType() != MojTab.noType) {
		 Code.put(Code.pop);
	 }
	 
 }

 public void visit(ReturnExpr re) {
	 Code.put(Code.exit);
	 Code.put(Code.return_);
 }
 public void visit(ReturnNoExpr re) {
	 Code.put(Code.exit);
	 Code.put(Code.return_);
 }
 public void visit(AddopExpr ae) {
	 if(ae.getAddop() instanceof AddopPlus) {
	 Code.put(Code.add);}
	 else {
		 Code.put(Code.sub);
	 }
 }
 public void visit(MulopTerm ae) {
	 if(ae.getMulop() instanceof MulopTimes ) {
		 Code.put(Code.mul);
		 	 
	 }
	 else if(ae.getMulop() instanceof MulopCelobrojno) {
		 Code.put(Code.div);
		 
	 }
	 else {
		 Code.put(Code.rem);
	 }
	}
 public void visit(Inkrement inc) {
	 Designator d=inc.getDesignator();
	// if(d.getListaNastavaka() instanceof NoListaNastavaka) {
		 Obj ob= d.obj;
		 incdec = true;
		 if(ob.getKind()== Obj.Var) {
			 Code.load(ob);}
			 else if(ob.getKind()== Obj.Elem) {
				 Code.put(Code.dup2);
				 Code.load(ob);
			 }
	     incdec = false;

		 Code.loadConst(1);
		 Code.put(Code.add);
		 Code.store(inc.getDesignator().obj);

	 //}
 }
 public void visit(Dekrement inc) {
	 Designator d=inc.getDesignator();
	 //if(d.getListaNastavaka() instanceof NoListaNastavaka) {
		 Obj ob= d.obj;
		 incdec = true;
		 if(ob.getKind()== Obj.Var) {
		 Code.load(ob);}
		 else if(ob.getKind()== Obj.Elem) {
			 Code.put(Code.dup2);
			 Code.load(ob);
		 }
	     incdec = false;		 
	     Code.loadConst(-1);
		 Code.put(Code.add);
		 Code.store(inc.getDesignator().obj);
	// }
 }
 public void visit(ProgName progName) {
     //define global predefined methods
     defineOrdCall();
     defineChrCall();
     defineLenCall();
 }
 private void defineOrdCall() {
     Obj ordObj = MojTab.find("ord");
     ordObj.setAdr(Code.pc);

     Code.put(Code.return_);
 }

 private void defineChrCall() {
     Obj chrObj = MojTab.find("chr");
     chrObj.setAdr(Code.pc);

     Code.put(Code.return_);
 }

 private void defineLenCall() {
     Obj lenObj = MojTab.find("len");
     lenObj.setAdr(Code.pc);

     Code.put(Code.arraylength);
     Code.put(Code.return_);
 }

	 
 

}
