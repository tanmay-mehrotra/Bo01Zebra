import java.io.*;
import java.util.*;
class PrettyPrintVisitor implements Visitor {

	FileWriter outfile=null;
	PrintWriter out=null;
        SymbolTable table;
	ArrayList<ErrorMsg> errors;
	public PrettyPrintVisitor (SymbolTable st,ArrayList<ErrorMsg> errors1){
	errors=errors1;
	table=st;
	try {
	outfile=new FileWriter("Boolzebra.java");
	out=new PrintWriter(outfile);
	}
	catch (IOException e)
	{}
	
	}
	

	
	public void visit (Nodelist n) {
		int[] funcflags=new int[n.nodes.size()];
		table.resetTable();

		table.enterScope();
		out.println("import java.util.Scanner\n;");
		out.println("public class Boolzebra {\n");

		for (int i=0;i<n.nodes.size();i++) {
			
			if (n.nodes.get(i) instanceof FunctionDeclaration)
			{out.print("\t\t");
			n.nodes.get(i).accept(this);
			funcflags[i]=1;

			}
			
		}
		
		
		out.println("\tpublic static void main(String[] args) {");
		
		for (int i=0;i<n.nodes.size();i++) {
			out.print("\t\t");
			if (funcflags[i]==0)
			n.nodes.get(i).accept(this);
		}
		out.println("\t} \n}");
		out.close();
		table.exitScope();


		for (int i=0;i<errors.size();i++)
		{
			System.out.println(errors.get(i).getMsg());
		}

	}
	
	public void visit (RoutineDeclaration n){
				
		if(n.t == Type.KMAP)
		{
		out.print("Kmap");
		out.print(" ");
		n.id.accept(this);
		out.print(" = null");
		out.println(";");
		}
		else if(n.t == Type.BIT)
		{
		out.print("boolean ");
		n.id.accept(this);
		out.println(";");
		out.println(n.id.s+"=false;");
		}
		else if(n.t == Type.BEXP)
		{
		out.print("String ");
		n.id.accept(this);
		out.println(";");
		out.println(n.id.s+"_bz"+"=\"\";");
		}
		else if(n.t == Type.INT)
		{
		out.print("int ");
		n.id.accept(this);
		out.println(";");
		out.println(n.id.s+"_bz"+"=0;");

		}
		else if (n.t!=Type.STRING)
		{String ty=n.t+"";out.print(ty.toLowerCase());
		out.print(" ");
		n.id.accept(this);}
		else if (n.t==Type.STRING)
		{	
			out.print("String");
			out.print(" ");
			n.id.accept(this);
			out.println(";");
			out.println(n.id.s+"_bz"+"=\"\";");
		}
		
		
	}

	public void visit (RoutineArrayDeclaration n){
		
		if (n.t == Type.TRUTHTABLE)
		{
		     out.print("TruthTable");
			
		}
		else if (n.t == Type.KMAP)
		{
		     out.print("Kmap ");
			
		}
		else if (n.t!=Type.STRING)

		{String ty=n.t+"";out.print(ty.toLowerCase());
		out.print("[]");
		if (n.e2!=null)
		{
			out.print("[]");
		}}
		else if (n.t==Type.STRING)
		{	
			out.print("String");
			out.print("[]");
		if (n.e2!=null)
		{
			out.print("[]");
		}
		}
		
		
		out.print(" ");
		n.id.accept(this);
		out.print("= new ");
		if(n.t == Type.TRUTHTABLE)
		{
			if(n.e1 instanceof Identifier)
			{

			Symbol sym=Symbol.getSymbol(((Identifier)n.e1).s);
			Record r=table.lookup(sym);
			if(r != null)
			{
			
			if(r.getType() == Type.BEXP)
			{
			out.print("TruthTable(");
			n.e1.accept(this);
			out.println(");");
			n.id.accept(this);
			out.print(" = ");
			n.id.accept(this);
			out.print(".buildTT()");
			}
			else if(r.getType() == Type.INT)
			{
			out.print("TruthTable(");
			n.e1.accept(this);
			out.print(")");
			}
			}
			}
			else if(!(n.e1 instanceof IntegerLiteral))
			{
			out.print("TruthTable(\"");
			n.e1.accept(this);
			out.println("\");");
			n.id.accept(this);
			out.print(" = ");
			n.id.accept(this);
			out.print(".buildTT()");

			}
			else
			{ 			
			out.print("TruthTable(");
			n.e1.accept(this);
			out.print(")");
			}
		}
		else if(n.t == Type.KMAP)
		{
			out.print("Kmap(");
			if(n.e1 instanceof IntegerLiteral)
			{
			n.e1.accept(this);
			out.print(" , ");
			n.e2.accept(this);
			out.println(")");
			}
			else if(n.e1 instanceof Identifier)
			{
			
			n.e1.accept(this);
			out.println(");");
			n.id.accept(this);
			out.print(" = ");
			n.id.accept(this);
			out.print(".buildKmap()");
			}

			else
			{
			out.print("\"");
			n.e1.accept(this);
			out.println("\");");
			n.id.accept(this);
			out.print(" = ");
			n.id.accept(this);
			out.print(".buildKmap()");
			}

		}
		else if (n.t!=Type.STRING)

		{String ty=n.t+"";out.print(ty.toLowerCase());
		out.print("[");
		n.e1.accept(this);
		out.print("]");
		if (n.e2!=null)
		{
			out.print("[");
			n.e2.accept(this);
			out.print("]");
		}}
		else if (n.t==Type.STRING)
		{	
			out.print("String");
			out.print("[");
		n.e1.accept(this);
		out.print("]");
		if (n.e2!=null)
		{
			out.print("[");
			n.e2.accept(this);
			out.print("]");
		}
		}
		
		
		
		out.println(";");
	}

	
	public void visit (MultipleDeclaration n){
		String init="";
		if(n.t == Type.BIT)
		{
		out.print("boolean");
		init="false";
		
		}
		else if(n.t == Type.BEXP)
		{
		out.print("String");
		init="\"\"";
		
		}		
		else if (n.t==Type.STRING)
		{	
			out.print("String");
			init="\"\"";
		}
		else if (n.t==Type.INT)
		{
			out.print("int");
			init="0";
			System.out.println("INIT is"+init);
			
		}
		else if (n.t!=Type.STRING)

		{String ty=n.t+"";out.print(ty.toLowerCase());}

		out.print(" ");

		for (int i=0;i<n.idlist.size();i++) {
			
			n.idlist.get(i).accept(this);
			if (i!=n.idlist.size()-1) {
			out.print(",");}
		}
		out.println(";");

		for (int i=0;i<n.idlist.size();i++) {
			
			n.idlist.get(i).accept(this);
			out.println("="+init+";");
		}
		
	}

	
	public void visit (FunctionDeclaration n)
	{
		out.print("public static ");
		if(n.ft == Type.BIT)
		{
		out.print("boolean");
		}
		else if(n.ft == Type.BEXP)
		{
		out.print("String");
		}
		else if(n.ft == Type.KMAP)
		{
		out.print("Kmap");
		}
		else if(n.ft == Type.TRUTHTABLE)
		{
		out.print("TruthTable");
		}
		else if (n.ft!=Type.STRING)

		{String ty=n.ft+"";out.print(ty.toLowerCase());}
		else if (n.ft==Type.STRING)
		{	
			out.print("String");
		}
                
		out.print(" ");

		
		n.fid.accept(this);
		table.enterScope();
		n.fagl.accept(this);
		n.fbs.accept(this);
		table.exitScope();
		
	
	}
	public void visit (Return n)
	{
		out.print("return ");
		if (n.rexpr!=null)
		n.rexpr.accept(this);
		out.println(";");
	}

	public void visit (Fargumentlist n) {
		
		out.print("(");
		for (int i=0;i<n.fag.size();i++)
		{
			n.fag.get(i).accept(this);
			if (i!=n.fag.size()-1) {
			out.print(",");}
			
		} 
		out.print(")");
	}
	public void visit (Fargument n) {

		
		if(n.t == Type.BIT)
		{
		out.print("boolean");
		}
		else if(n.t == Type.BEXP)
		{
		out.print("String");
		}
		else if(n.t == Type.KMAP)
		{
		out.print("Kmap");
		}
		else if(n.t == Type.TRUTHTABLE)
		{
		out.print("TruthTable");
		}
		else if (n.t!=Type.STRING)

		{String ty=n.t+"";out.print(ty.toLowerCase());}
		else if (n.t==Type.STRING)
		{	
			out.print("String");
		}
		out.print(" ");

		n.id.accept(this);
	}
	public void visit (BlockStatement n) 
	{
		out.print("{\n");
		for (int i=0;i<n.bstmts.size();i++)
		{
			n.bstmts.get(i).accept(this);
		}
		
		out.println("}");
	}


	public void visit (WhileLoop n) {
		out.print("while (");
		n.cond.accept(this);
		table.enterScope();
		out.println(")");
		n.whilestmt.accept(this);
		table.exitScope();
		
	}
	public void visit (IfElseStatement n) {
		out.print("if (");
		n.cond.accept(this);
		table.enterScope();
		out.print(")");
		n.ifstmt.accept(this);
		out.print("else ");
		table.exitScope();
		table.enterScope();
		n.elsestmt.accept(this);
		table.exitScope();
		
		
	}
	public void visit (IfStatement n) {
		out.print("if (");
		n.cond.accept(this);
		table.enterScope();
		out.print(")");
		n.stmt.accept(this);
		table.exitScope();
		
	}
	
	public void visit (Assign n){
		int inpflag=0;
		String type="";
		if (n.e instanceof MethodInvocationExpr && ((MethodInvocationExpr)n.e).id.s.equals("input"))
		{
			inpflag=1;
		}
		
		if(n.id==null)
		{
		
			System.out.println("Array Lookup Print");
			if (inpflag!=1)
			n.alook.accept(this);
			Symbol sym=Symbol.getSymbol("@"+n.alook.id.s);
			Record r=table.lookup(sym);

			if (r.getType()==Type.TRUTHTABLE)
			{
			type="truthtable";
			
			out.print(" ");
			n.e.accept(this);
			if (inpflag!=1)
			out.print(")");
			}
			else if(r.getType() == Type.KMAP)
			{
			n.e.accept(this);
			out.print(")");
			}
			else
			{
			if (inpflag!=1)
			out.print("=");
			n.e.accept(this);

			}

		}
		else if (n.alook==null)
		{

		System.out.println("Identifier Print"+n.id.s);
		Symbol sym=Symbol.getSymbol(n.id.s);
		Record r=table.lookup(sym);

		if(r != null)
		{
		if (r.getType()==Type.KMAP)
		{
		
		if (inpflag!=1)
		n.id.accept(this);
		
		}
		else if (r.getType()==Type.INT)
		{
			type="int";
			if (inpflag!=1)
			n.id.accept(this);

		}
		else if (r.getType()==Type.BIT)
		{
			type="bit";
			if (inpflag!=1)
			n.id.accept(this);

		}

		else
		{
			if (inpflag!=1)
			n.id.accept(this);
			
		}
		}
		else
		{
		if (inpflag!=1)
		n.id.accept(this);
		System.out.println("Type BEXP");
		}
		if (n.e instanceof TypeCasting) {
		out.print(" = ");
		out.print(" new Kmap(");
		n.e.accept(this);
		out.println(");");
		n.id.accept(this);
		out.print(" = ");
		n.id.accept(this);
		out.print(".buildKmap()");
		}
		else
		{System.out.println("No Type Casting");
		if (inpflag!=1)
		out.print("=");
		if(r != null)
		{
			if (r.getType()==Type.BEXP)
			{
				
				out.print("\"");
				n.e.accept(this);
				out.print("\"");
			}
			
			else
		{ 
			n.e.accept(this);
		}
		}
		else
		{System.out.println("BEXP Found");
			n.e.accept(this);
		}
		}
		}
		
		out.println(";");
		if (inpflag==1)
		{
			if (n.alook==null)
			{
			if (type.equals("int"))
				out.println(n.id.s+"_bz"+"=Integer.parseInt(inp);");
			else if (type.equals("bit"))
				{
				out.print("if (inp.equals(\"T\")) \n "+n.id.s+"=true; \n else if (inp.equals(\"F\")) \n "+n.id.s+"=false;\n else {System.out.println(\"Wrong Input.Enter T or F. Aborting...\");\n System.exit(0);}");
				
				}
			else
				out.println(n.id.s+"_bz=inp;");	
			}
			else if (n.id==null)
			{
				if (type.equals("truthtable"))
				{	out.print("if (inp.equals(\"T\")) \n ");
					n.alook.accept(this);
					out.print("true); \n else if (inp.equals(\"F\")) \n ");
					n.alook.accept(this);
					out.print("false);");
					
				}
			}						
		}
	}
	public void visit (ArrayLookup n){
		
		Symbol sym=Symbol.getSymbol("@"+n.id.s);
		Record r=table.lookup(sym);
		if (r.getType()==Type.TRUTHTABLE)
		{
		n.id.accept(this);
		out.print(".setSingleResultColumn(");
		n.e1.accept(this);
		out.print(",");	
		}
		else if(r.getType() == Type.KMAP)
		{
			
			n.id.accept(this);
			out.print(".fillKmap(");
			n.e1.accept(this);
			out.print(" , ");
			n.e2.accept(this);
			out.print(",");
		}
		else
		{
			n.id.accept(this);
			out.print("[");
			n.e1.accept(this);
			out.print("]");
			if (n.e2!=null)
		{
			out.print("[");
			n.e2.accept(this);
			out.print("]");
		}
		}
		
		
	}
	public void visit (UnaryExpr n) {
		out.print("( ");
		if (n.op==Operator.PLUS) {
			out.print("+");
		}
		else if (n.op==Operator.UMINUS) {
			out.print("-");
		}
		else if (n.op==Operator.LNOT) {
			out.print("!");
		}
		else if (n.op==Operator.NOT) {
			
			out.print(" NOT ");
			
		}

		n.eunary.accept(this);
		out.print(" )");
		
	
	}
	public void visit (TypeCasting n) {
		
		
		if (n.t == Type.KMAP)
		{
			n.e.accept(this);
						
		}
		else if (n.t!=Type.STRING)

		{out.print("(");
		String ty=n.t+"";out.print(ty.toLowerCase());
		out.print(")");
		n.e.accept(this);}
		else if (n.t==Type.STRING)
		{
			out.print("(");	
			out.print("String");
			out.print(")");
			n.e.accept(this);
		}
		
		
		

	}
	public void visit (Parenthesis n) {
		out.print(" ( ");
		n.e.accept(this);
		out.print(" ) ");
	}
	public void visit (BinExpr n) {
		
			
		switch (n.op)
		{
			case PLUS:
				n.eleft.accept(this);
				out.print("+");
				n.eright.accept(this);
				break;
			case MINUS:
				n.eleft.accept(this);
				out.print("-");
				n.eright.accept(this);
				break;
			case MULT:
				n.eleft.accept(this);
				out.print("*");
				n.eright.accept(this);
				break;
			case DIV:
				n.eleft.accept(this);
				out.print("/");
				n.eright.accept(this);
				break;
			case MOD:
				n.eleft.accept(this);
				out.print("%");
				n.eright.accept(this);
				break;
			case SCOR:
				n.eleft.accept(this);
				out.print("||");
				n.eright.accept(this);
				break;
			case SCAND:
				n.eleft.accept(this);
				out.print("&&");
				n.eright.accept(this);
				break;
			case LOR:
				n.eleft.accept(this);
				out.print("|");
				n.eright.accept(this);
				break;
			case LAND:
				n.eleft.accept(this);
				out.print("&");
				n.eright.accept(this);
				break;
			case LXOR:
				n.eleft.accept(this);
				out.print("^");
				n.eright.accept(this);
				break;
			case LT:
				n.eleft.accept(this);
				out.print("<");
				n.eright.accept(this);
				break;
			case GT:
				n.eleft.accept(this);
				out.print(">");
				n.eright.accept(this);
				break;
			case LEQ:
				n.eleft.accept(this);
				out.print("<=");
				n.eright.accept(this);
				break;
			case GEQ:
				n.eleft.accept(this);
				out.print(">=");
				n.eright.accept(this);
				break;
			case DEQ:
				n.eleft.accept(this);
				out.print("==");
				n.eright.accept(this);
				break;
			case NEQ:
				n.eleft.accept(this);
				out.print("!=");
				n.eright.accept(this);
				break;
			case NOR:
				out.print("( ");
				n.eleft.accept(this);
				out.print(" NOR ");
				n.eright.accept(this);
				out.print(" )");
				break;
			case NAND:
				out.print("( ");
				n.eleft.accept(this);
				out.print(" NAND ");
				n.eright.accept(this);
				out.print(" )");
				break;
			case OR:
				out.print("( ");
				n.eleft.accept(this);
				out.print(" OR ");
				n.eright.accept(this);
				out.print(" )");
				break;
			case AND:
				out.print("( ");
				n.eleft.accept(this);
				out.print(" AND ");
				n.eright.accept(this);
				out.print(" )");
				break;
			case XOR:
				out.print("( ");
				n.eleft.accept(this);
				out.print(" XOR ");
				n.eright.accept(this);
				out.print(" )");
				break;
			case XNOR:
				out.print("( ");
				n.eleft.accept(this);
				out.print(" XNOR ");
				n.eright.accept(this);
				out.print(" )");
				break;
			case DOT:
				n.eright.accept(this);
				out.print(".dotOperator(");
				n.eleft.accept(this);
				out.print(")");
				break;
			case AT:
				n.eleft.accept(this);
				out.print(".atTheRate(");
				n.eright.accept(this);
				out.print(")");
				break;
			default:
		}
		
		
	}


	public void visit (Identifier n){
		Symbol sym=Symbol.getSymbol(n.s);
		Record r=table.lookup(sym);
		if (r!=null && r.getType()==Type.BIT)
		out.print(n.s);
		else
		out.print(n.s+"_bz");
	}


	public void visit (IntegerLiteral n){
		out.print(n.i);
	}
	public void visit (BitLiteral n){
		if (n.i.equals("T"))
			out.print("true");
		else
			out.print("false");
	}
	public void visit (BexpressionLiteral n){
		out.print("\"");
		out.print(n.s);
		out.print("\"");
	}


	public void visit (MethodInvocation n){
		
		if ((n.id.s).equals("output"))
		{
			out.print("System.out.println");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(");");	
		}
		else if ((n.id.s).equals("bexpValidate"))
		{
			out.print("JavaLib.bexpValidate");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			if(!(n.exList.get(i) instanceof Identifier))
			{
			out.print("\"");
			n.exList.get(i).accept(this);
			out.print("\"");
			}
			else
			{
				n.exList.get(i).accept(this);
			}
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(");");
		}
		else if ((n.id.s).equals("size"))
		{
			
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(".ttsize(");

		out.println(");");
		}
		else if ((n.id.s).equals("rowSize"))
		{
			
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(".rowSize(");

		out.println(");");
		}
		else if ((n.id.s).equals("columnSize"))
		{
			
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(".columnSize(");

		out.println(");");
		}
		else if ((n.id.s).equals("compare"))
		{
			out.print("JavaLib.compare1");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(");");
		}
		else if ((n.id.s).equals("countVariables"))
		{
			out.print("JavaLib.countVariables");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			if(!(n.exList.get(i) instanceof Identifier))
			{
			out.print("\"");
			n.exList.get(i).accept(this);
			out.print("\"");
			}
			else
			{
				n.exList.get(i).accept(this);
			}
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(");");
		}
		else if ((n.id.s).equals("nameOfVariables"))
		{
			out.print("JavaLib.nameOfVariables");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			if(!(n.exList.get(i) instanceof Identifier))
			{
			out.print("\"");
			n.exList.get(i).accept(this);
			out.print("\"");
			}
			else
			{
				n.exList.get(i).accept(this);
			}
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(");");
		}
		else if ((n.id.s).equals("flipVertical"))
		{
			for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
			}
			out.print(".flipVertical(");
			out.println(");");
			
		}
		else if ((n.id.s).equals("flipHorizontal"))
		{
			for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
			}
			out.print(".flipHorizontal(");
			out.println(");");
			
		}
		else if ((n.id.s).equals("input"))
		{
			String s="Scanner sc = new Scanner(System.in);\n"+"\t\tSystem.out.println(\"Enter the input\");\n"+"\t\tString inp = sc.next(";			
			out.print(s);
			out.println(");");
		}
		
		
		else 
		{n.id.accept(this);
		out.print("(");
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(");");
		}
		
	}

	public void visit (MethodInvocationExpr n){
		
		if ((n.id.s).equals("output"))
		{
			out.print("System.out.println");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(")");	
		}
		else if ((n.id.s).equals("bexpValidate"))
		{
			out.print("JavaLib.bexpValidate");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(")");
		}
		else if ((n.id.s).equals("size"))
		{
			
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(".ttsize(");

		out.println(")");
		}
		else if ((n.id.s).equals("rowSize"))
		{
			
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(".rowSize(");

		out.println(")");
		}
		else if ((n.id.s).equals("columnSize"))
		{
			
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(".columnSize(");

		out.println(")");
		}
		else if ((n.id.s).equals("size"))
		{
			out.print("JavaLib.size1");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(")");
		}
		else if ((n.id.s).equals("compare"))
		{
			out.print("JavaLib.compare1");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(");");
		}
		else if ((n.id.s).equals("countVariables"))
		{
			out.print("JavaLib.countVariables");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(")");
		}
		else if ((n.id.s).equals("nameOfVariables"))
		{
			out.print("JavaLib.nameOfVariables");
			out.print("(");
		
		for (int i=0;i<n.exList.size();i++) {
			
			if(!(n.exList.get(i) instanceof Identifier))
			{
			out.print("\"");
			n.exList.get(i).accept(this);
			out.print("\"");
			}
			else
			{
				n.exList.get(i).accept(this);
			}
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.println(")");
		}
		else if ((n.id.s).equals("flipVertical"))
		{
			for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
			}
			out.print(".flipVertical(");
			out.print(")");
			
		}
		else if ((n.id.s).equals("flipHorizontal"))
		{
			for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
			}
			out.print(".flipHorizontal(");
			out.print(")");
			
		}
		else if ((n.id.s).equals("input"))
		{
			String s="Scanner sc = new Scanner(System.in);\n"+"\t\tSystem.out.println(\"Enter the input\");\n"+"\t\tString inp = sc.next(";			
			out.print(s);
			out.print(")");
		}
		
		
		else 
		{n.id.accept(this);
		out.print("(");
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			out.print(",");}
		}

		out.print(")");
		}
		
	}

	public void visit (StringLiteral n){
		out.print("\""+n.s+"\"");
	}


}
