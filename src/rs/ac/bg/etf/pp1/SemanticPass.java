package rs.ac.bg.etf.pp1;

import java.nio.file.AccessDeniedException;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	
	
	int printCallCount = 0;
	public int varDeclCount = 0;
	boolean errorDetected=false;
	boolean returnFound=false;
	Obj currentMethod= null;
	Struct currType=null;
	Logger log = Logger.getLogger(getClass());
	private Obj currTypeObj;
	
	public void error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	private int countDimensions(ListaZagrada listaZagrada) {
        int count = 0;
        while (listaZagrada instanceof YesListaZagrada) {
            count++;
            listaZagrada = ((YesListaZagrada) listaZagrada).getListaZagrada();
        }
        return count;
    }
	private void initializeAdditionalVars(ListaVarDecl listaVarDecl, Struct varType) {
        while (listaVarDecl instanceof YesListaVarDecl) {
            YesListaVarDecl yesListaVarDecl = (YesListaVarDecl) listaVarDecl;
            String varName = yesListaVarDecl.getVarName();
            int dimensions = countDimensions(yesListaVarDecl.getListaZagrada());
            Struct actualType = (dimensions > 0) ? new Struct(Struct.Array, varType) : varType;
            Obj typeNode=MojTab.insert(Obj.Var, varName, actualType);
            info("Deklarisano je "+typeNode.getName()+" tipa "+typeNode.getKind(),null);
            listaVarDecl = yesListaVarDecl.getListaVarDecl();
            varDeclCount++;
        }
    }
	public void visit(VarDecl vardecl){
		varDeclCount++;
		//Obj varNode=MojTab.insert(Obj.Var,vardecl.getVarName(), vardecl.getType().struct);
		
		Struct varType = vardecl.getType().struct;
		
		if (varType == null) {
            error("Tip za promenljivu " + vardecl.getVarName() + " nije definisan.", vardecl);
            varType = MojTab.noType;
        }
        String varName = vardecl.getVarName();
		int dimensions = countDimensions(vardecl.getListaZagrada());
		Struct actualType = (dimensions > 0) ? new Struct(Struct.Array, varType) : varType;
		
        // Inicijalizacija glavne promenljive
        Obj typeNode=MojTab.insert(Obj.Var, varName, actualType);
        info("Deklarisano je "+typeNode.getName()+" tipa "+typeNode.getType(),null);
        // Inicijalizacija dodatnih promenljivih iz ListaVarDecl
        initializeAdditionalVars(vardecl.getListaVarDecl(), varType);
		
	}
	
	
	public void visit(ConstDecl cd) {
		Obj typeNode= MojTab.insert(Obj.Con, cd.getIdent(), cd.getType().struct);
		//typeNode.setAdr();
		if(cd.getNesto() instanceof IntVar) {
			IntVar a=(IntVar) cd.getNesto();
			typeNode.setAdr(a.getValue());
		}
		if(cd.getNesto() instanceof CharVar) {
			CharVar a=(CharVar) cd.getNesto();
			typeNode.setAdr(a.getValue());
		}
		if(cd.getNesto() instanceof BoolVar) {
			BoolVar a=(BoolVar) cd.getNesto();
			int q=0;
			if(a.getValue()=="true") {q=1;}
			typeNode.setAdr(q);
		}
		if(!(cd.getNesto().struct.getKind()==cd.getType().struct.getKind())) {
			error("Greska u ConstDecl, tipovi nisu isti",null);
		}
		if(cd.getListaNesto() instanceof YesListaNesto) {
			YesListaNesto a= (YesListaNesto) cd.getListaNesto();
			while(a.getNesto().struct==cd.getType().struct) {
				if(MojTab.find(a.getA())==MojTab.noObj){
				MojTab.insert(Obj.Con,a.getA(), cd.getType().struct);
			}
				if(a.getListaNesto() instanceof Kraj) {
					break;
				}
				else{a=(YesListaNesto)a.getListaNesto();}
				}
		}
        info("Deklarisano je "+typeNode.getName()+" tipa "+typeNode.getType(),null);

	}
	public void visit(YesFormalParamDecl yfp) {
		String a=yfp.getVarName();
		if (MojTab.currentScope().findSymbol(a) != null) {
			error("Vec postoji",null);
		}
		currentMethod.setLevel(currentMethod.getLevel()+1);
		ListaZagrada q=yfp.getListaZagrada();
		if(q instanceof YesListaZagrada) {
			MojTab.insert(Obj.Var, a, new Struct(Struct.Array,currTypeObj.getType() ));
		}
		else {
			MojTab.insert(Obj.Var, a,currTypeObj.getType());
		}
	}
	public void visit(IntVar nesto) {
		nesto.struct=MojTab.intType;
	}
	public void visit(CharVar nesto) {
		nesto.struct=MojTab.charType;
	}
	public void visit(BoolVar nesto) {
		nesto.struct=MojTab.boolType;
	}
	public void visit(Type type) {
		Obj typeNode=MojTab.find(type.getTypeName());
		if(typeNode == MojTab.noObj) {
			error("Nije Pronadjen tip "+ type.getTypeName(), null);
			currTypeObj= MojTab.noObj;
			return;
		}
		else {
			if(Obj.Type== typeNode.getKind()) {
				type.struct= typeNode.getType();
				currTypeObj= typeNode;
			}
			else {
				error("Greska: Ime "+type.getTypeName()+" ne predstavlja tip!", type);
				currTypeObj= MojTab.noObj;

				type.struct=MojTab.noType;
			}
		}
		currType=type.struct;
	}
    public void visit(PrintStmt print) {
    	Struct t=print.getExpr().struct;
    	if(t!=MojTab.intType || t!=MojTab.charType) {
    		error("Print printuje samo int i char",null);
    	}
		printCallCount++;
	}

    public void visit(ProgName progName) {
    progName.obj=MojTab.insert(Obj.Prog, progName.getName(), MojTab.noType);
    MojTab.openScope();
    }
    
    public void visit(Program program) {
    	MojTab.chainLocalSymbols(program.getProgName().obj);
    	
    	Obj mainObj=MojTab.find("main");
    	
    	if( mainObj== MojTab.noObj) {
    		error("Ne Postoji main",null);
    	}
    	MojTab.closeScope();
    }
    
    public void visit(YesMethodTypeName methodTypeName) {
        String methodName = methodTypeName.getMethName();
        Struct methodType = methodTypeName.getType().struct;

        currentMethod = MojTab.insert(Obj.Meth, methodName, methodType);
        methodTypeName.obj = currentMethod;
        MojTab.openScope();

        info("Obradjen metod " + methodName + " sa povratnim tipom " + methodType.getKind(), methodTypeName);
    }

    // Dodajemo novu posetu za VoidMethodTypeName
    public void visit(VoidMethodTypeName voidMethodTypeName) {
        String methodName = voidMethodTypeName.getMethName();
        Struct methodType = MojTab.noType;

        currentMethod = MojTab.insert(Obj.Meth, methodName, methodType);
        voidMethodTypeName.obj = currentMethod;
        MojTab.openScope();

        info("Obradjen metod " + methodName + " sa povratnim tipom void", voidMethodTypeName);
    }
    public void visit(MethodDecl m) {
    	if(!returnFound && currentMethod.getType()!=MojTab.noType) {
			error("Semanticka greska na liniji " + m.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);

    	}
//    	if(currentMethod.getName()=="ord" && currentMethod.get)
    	MojTab.chainLocalSymbols(currentMethod);
    	MojTab.closeScope();
    	currentMethod=null;
    	returnFound=false;
    }
    //public void 
    public void visit(TeskaGlupost an) {
    	String name= an.getName();
    	an.obj=MojTab.find(name);
    }
    public void visit(ObicanDesignator od) {
    	Obj obj = MojTab.find(od.getName());
		//info("Ulazi se u Designator na liniji "+d.getLine() +"\n"+obj.getKind()+" "+obj.getType(),null);
		if (obj==MojTab.noObj) {
			error("Greska na liniji "+ od.getLine()+ " : ime "+od.getName()+" nije deklarisano! ",null);
		}
		od.obj=obj;
    }
    public void visit(Array d) {
    	
		Obj obj = MojTab.find(d.getArrayName().obj.getName());
		//info("Ulazi se u Designator na liniji "+d.getLine() +"\n"+obj.getKind()+" "+obj.getType(),null);
		if (obj==MojTab.noObj) {
			error("Greska na liniji "+ d.getLine()+ " : ime "+d.getArrayName()+" nije deklarisano! ",null);
		}
		if(d.getListaNastavaka() instanceof YesListaNastavaka) {
			if(((YesListaNastavaka)d.getListaNastavaka()).getExpr().struct!=MojTab.intType)
			{error("indeks nije broj",null);}
			d.obj=MojTab.noObj;
		}
		info("Pristup Elementu Niza "+ d.getArrayName().obj.getName(),null  );
		
		d.obj=new Obj(Obj.Elem,d.getArrayName().obj.getName(),d.getArrayName().obj.getType().getElemType());
		
		
                
               // else {listaNastavaka=(YesListaNastavaka1)listaNastavaka.getListaNastavaka1();}
                
            

       
		}
    
    public void visit(FunCall fc) {
    	Obj func= fc.getDesignator().obj;
    	if(Obj.Meth== func.getKind()) {
    		if(MojTab.noType==func.getType()) {
    			error("NeMoze se koristiti u izrazima jer je void",null);
    			}
    		else {
    		info("Pronadjen poziv funkcije "+func.getName()+" na liniji "+fc.getLine(),null);
    		fc.struct= func.getType();
    	}}
    	else {
    		fc.struct=MojTab.noType;
    	}
    }
    public void visit(FactorTerm t) {
    	t.struct=t.getFactor().struct;
    }
    public void visit(MulopTerm t) {
    	Struct te= t.getFactor().struct;
    	Struct t1=t.getTerm().struct;
//    	info("Ulazi se u AddopExpr na liniji "+ae.getLine()+ "\n" 
//    	+ ae.getTerm().struct.getKind()+" "+ae.getExpr().struct.getKind(),null);
//    	info("te.equals(t) && te == MojTab.intType "+ te.getKind() +" " + t.getKind() + " " +te+" "+MojTab.intType +
//    			"\n rez: " + te.equals(t) +" "+ (te==MojTab.intType),null);
//    	
    	if(!assignableTo(t1, MojTab.intType) || !assignableTo(te, MojTab.intType)) {
    		
    		error("Greska na liniji "+ t.getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
    		t.struct=MojTab.noType;
    	}
    	else {
			
    		t.struct=MojTab.intType;
    	}
    }
    
    public void visit(TermExpr t) {
    	t.struct=t.getTerm().struct;
    	info("Ulazi se u TermExpr na liniji "+t.getLine() +"\n za promenljivu",null);

    	
    }
    public void visit(Range rn) {
    	info("Ulazi se u Range na liniji:" +rn.getLine(), rn);
    	Struct tipExpr=rn.getExpr().struct;
    	if(tipExpr.getKind()!=MojTab.intType.getKind()) {
    		error("Greska na liniji "+ rn.getLine()+" : nekompatibilan expr u range.", null);

    	}
    
    	rn.struct= new Struct(Struct.Array,MojTab.intType);
    }
    public void visit(NoviNiz nn) {
    	info("Ulazi se u NoviNiz", nn);
    	nn.struct= new Struct(Struct.Array, nn.getType().struct);
    }
    public void visit(NoviEl ne) {
    	info("Ulazi se u NoviEl", ne);
    	ne.struct=ne.getType().struct;
    }
    public void visit(UZagradama uz) {
    	uz.struct=uz.getExpr().struct;
    }
    public void visit(MinusTermExpr mte) {
	mte.struct=mte.getTerm().struct;
    }
    
    public void visit(AddopExpr ae) {
    	
    	Struct te= ae.getExpr().struct;
    	Struct t=ae.getTerm().struct;
//    	info("Ulazi se u AddopExpr na liniji "+ae.getLine()+ "\n" 
//    	+ ae.getTerm().struct.getKind()+" "+ae.getExpr().struct.getKind(),null);
//    	info("te.equals(t) && te == MojTab.intType "+ te.getKind() +" " + t.getKind() + " " +te+" "+MojTab.intType +
//    			"\n rez: " + te.equals(t) +" "+ (te==MojTab.intType),null);
//    	
    	if(!assignableTo(t, MojTab.intType) || !assignableTo(te, MojTab.intType)) {
    		
    		error("Greska na liniji "+ ae.getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
    		ae.struct=MojTab.noType;
    	}
    	else {
			
    		ae.struct=MojTab.intType;
    	}
    }
    public void visit(NumberConst nc) {
    	nc.struct=Tab.intType;
    }
    public void visit(CHARConst cc) {
    	cc.struct=Tab.charType;
    }
    public void visit(Var v) {
    	v.struct=v.getDesignator().obj.getType();
    }
    public void visit(ReturnExpr re) {
    	returnFound=true;
    	Struct currMethType= currentMethod.getType();
    	if(!currMethType.compatibleWith(re.getExpr().struct)) {
			error("Greska na liniji " + re.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);

    	}
    }
    private boolean assignableTo(Struct this1,Struct dest) {
        if (this1 == dest) {
            return true; // Identicni tipovi
        }
        
        if (this1.getKind() == Struct.None || dest.getKind() == Struct.None) {
            return false; // Dodeljivanje nije moguce za None tip
        }

        if (this1.getKind() == Struct.Int && dest.getKind() == Struct.Int) {
            return true; // Int moze biti dodeljen Int-u
        }

        if (this1.getKind() == Struct.Char && dest.getKind() == Struct.Char) {
            return true; // Char moze biti dodeljen Char-u
        }

        if (this1.getKind() == Struct.Bool && dest.getKind() == Struct.Bool) {
            return true; // Bool moze biti dodeljen Bool-u
        }

        if (this1.getKind() == Struct.Array && dest.getKind() == Struct.Array) {
            return this1.getElemType().assignableTo(dest.getElemType()); // Nizovi istih osnovnih tipova su dodeljivi
        }
        

        if (this1.getKind() == Struct.None && dest.getKind() == Struct.None) {
            return true; // Void moze biti dodeljen Void-u
        }
        
        return this1.assignableTo(dest); // Ostali slucajevi nisu dodeljivi
    }

    
    
    public void visit(DesignatorAsign assignDesignatorStatement) {
//    	Obj levi=da.getDesignator().obj;
//    	
//    	Struct desni=da.getExpr().struct;
//    	info("levi :" +levi.getType().getKind()+" desni " + desni.getKind(),null);
//    	if(levi.getKind() == Obj.Elem) {
//    		Struct dst= levi.getType();
//    		if(!assignableTo(desni,levi.getType())) {
//        		error("Greska u DA sa gornje strane na liniji " + da.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! "+desni.getKind() +" "+levi.getKind() + " Original fja vraca" + desni.assignableTo(dst)
//        		, null);
//
//    		}
//    	}
//    	
//    	else if(!assignableTo(desni,levi.getType())) {
//    		error("Greska u DA na liniji " + da.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! "+desni.getKind() +" "+levi.getKind() + " Original fja vraca" + desni.assignableTo(levi.getType())
//    		, null);
//    	}
    	 Obj designatorObj = assignDesignatorStatement.getDesignator().obj;

         if (designatorObj == MojTab.noObj) {
             error("Error with designator", assignDesignatorStatement);
             return;
         }

         if ((designatorObj.getKind() != Obj.Var) && (designatorObj.getKind() != Obj.Elem)) {
             error("Left side is not a variable", assignDesignatorStatement);
         }

         Struct leftSideType = designatorObj.getType();
         Struct rightSideType = assignDesignatorStatement.getExpr().struct;

         if (!assignableTo(rightSideType,leftSideType)) {
             error("Right side can't be assigned to left", assignDesignatorStatement);
             error("Right "+ rightSideType.getKind() + " Left "+leftSideType.getKind(),null);
         }


         info("AssignDesignatorStatement visit", assignDesignatorStatement);
    } 
//    public void visit(Inkrement in) {
//    	
//    }
    
}
