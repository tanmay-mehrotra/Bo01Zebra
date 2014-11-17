import java.io.*;
import java.util.*;
class SymbolTableVisitor implements Visitor {

	SymbolTable symtable;
	ArrayList<ErrorMsg> errors;
	public SymbolTableVisitor (SymbolTable st,ArrayList<ErrorMsg> errors1){
			symtable=st;
			errors=errors1;
	}
	

	public void addSymbtabEntry (Symbol s,Record r,int line) {
		try {
			symtable.put(s,r);
		}
		catch(Exception e) {
			String name=s.toString();
			if (name.startsWith("$")) {
				name=name.substring(1); 
			}
			if (name.startsWith("@")) {
				name=name.substring(1); 
			}
			String errMsg="Line" + line + ": " + name + " is already defined";
			errors.add(new ErrorMsg(errMsg,line));
		} 
		
	}

	public void visit (Nodelist n) {
		int[] funcflags=new int[n.nodes.size()];
		symtable.resetTable();


		symtable.enterScope();
		for (int i=0;i<n.nodes.size();i++) {
			
			if (n.nodes.get(i) instanceof FunctionDeclaration)
			{
			n.nodes.get(i).accept(this);
			funcflags[i]=1;
			
			}
			
		}
		
		
		for (int i=0;i<n.nodes.size();i++) {
			
			if (funcflags[i]==0)
			n.nodes.get(i).accept(this);
		}
		symtable.exitScope();

	}
	public void visit (RoutineDeclaration n){
		Symbol sym=Symbol.getSymbol(n.id.s);
		if (n.t==Type.VOID)
		{
			System.out.println("Void is not valid type of Identifier");
		}
		else
		{
		
		Record r=new Record(sym,n.t,0);
		
		addSymbtabEntry(sym,r,n.getLine());	
		}
	}

	public void visit (RoutineArrayDeclaration n){
		int size=0;
		if (n.e1 instanceof IntegerLiteral)
		{
			size=((IntegerLiteral)n.e1).i;
		}
		Symbol sym=Symbol.getSymbol("@"+n.id.s);
		Record r=new Record(sym,n.t,size);
		addSymbtabEntry(sym,r,n.getLine());
		
	}

	public void visit (MultipleDeclaration n){
		for (int i=0;i<n.idlist.size();i++) {
			Symbol sym=Symbol.getSymbol(n.idlist.get(i).s);
			Record r=new Record(sym,n.t,0);
			addSymbtabEntry(sym,r,n.getLine());
		}

		
	}

	
	public void visit (FunctionDeclaration n)
	{
		Symbol sym=Symbol.getSymbol("$"+n.fid.s);
		Record r=new Record(sym,n.ft,0);
		addSymbtabEntry(sym,r,n.fid.getLine());
		symtable.enterScope();
		
		n.fagl.accept(this);
		n.fbs.accept(this);
		symtable.exitScope();

	
	}
	public void visit (Return n)
	{

		if (n.rexpr!=null)
		n.rexpr.accept(this);

	}
	public void visit (Fargumentlist n) {
		
		for (int i=0;i<n.fag.size();i++)
		{
			n.fag.get(i).accept(this);
			
		} 

	}
	public void visit (Fargument n) {
		Symbol sym=Symbol.getSymbol(n.id.s);
		Record r=new Record(sym,n.t,0);
		addSymbtabEntry(sym,r,n.id.getLine());
		n.id.accept(this);
		
	}
	public void visit (BlockStatement n) 
	{
		for (int i=0;i<n.bstmts.size();i++)
		{
			n.bstmts.get(i).accept(this);
		}
		
	}


	public void visit (WhileLoop n) {
		
		n.cond.accept(this);
		symtable.enterScope();
		n.whilestmt.accept(this);
		symtable.exitScope();
		
	}
	public void visit (IfElseStatement n) {

		n.cond.accept(this);

		symtable.enterScope();
		n.ifstmt.accept(this);
		symtable.exitScope();

		symtable.enterScope();
		n.elsestmt.accept(this);
		symtable.exitScope();		
		
	}
	public void visit (IfStatement n) {


		n.cond.accept(this);
		symtable.enterScope();
		n.stmt.accept(this);
		symtable.exitScope();				
	}
	public void visit (Assign n){
		if(n.id==null)
		{

		n.alook.accept(this);
		}
		else if (n.alook==null)
		{

		n.id.accept(this);
		}

		n.e.accept(this);

	}
	public void visit (ArrayLookup n){
		n.id.accept(this);

		n.e1.accept(this);

		if (n.e2!=null)
		{

			n.e2.accept(this);

		}


	}
	public void visit (UnaryExpr n) {
		if (n.op==Operator.PLUS) {

		}
		else if (n.op==Operator.UMINUS) {

		}
		else if (n.op==Operator.LNOT) {

		}
		else if (n.op==Operator.NOT) {

		}

		n.eunary.accept(this);
	
	}
	public void visit (TypeCasting n) {

		if (n.t!=Type.STRING)

		{String ty=n.t+"";
		}
		else if (n.t==Type.STRING)
		{	
			
		}
		
		n.e.accept(this);
		

	}
	public void visit (Parenthesis n) {
		
		n.e.accept(this);
		
	}
	public void visit (BinExpr n) {
		n.eleft.accept(this);
		switch (n.op)
		{
			case PLUS:
				
				break;
			case MINUS:
				
				break;
			case MULT:
				
				break;
			case DIV:
				
				break;
			case MOD:
				
				break;
			case SCOR:
				
				break;
			case SCAND:
				
				break;
			case LOR:
				
				break;
			case LAND:
				
				break;
			case LXOR:
				
				break;
			case LT:
				
				break;
			case GT:
				
				break;
			case LEQ:
				
				break;
			case GEQ:
				
				break;
			case DEQ:
				
				break;
			case NEQ:
				
				break;
			case NOR:
				
				break;
			case NAND:
				
				break;
			case OR:
				
				break;
			case AND:
				
				break;
			case XOR:
				
				break;
			case XNOR:
				
				break;
			case AT:
				break;
			case DOT:
				break;
			default:
		}
		n.eright.accept(this);
		
	}


	public void visit (Identifier n){

	}

	public void visit (IntegerLiteral n){

	}
	public void visit (BitLiteral n){
	}
	public void visit (BexpressionLiteral n){
	}


	public void visit (MethodInvocation n){
		if ((n.id.s).equals("output"))
		{

		}
		else if ((n.id.s).equals("input"))
		{
			String s="Scanner sc = new Scanner(System.in);\n"+"\t\tSystem.out.println(\"Enter the input\");\n"+"\t\tString inp = sc.next";			

		}
		else 
		{n.id.accept(this);}
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {

			}
		}


	}

	public void visit (MethodInvocationExpr n){
		if ((n.id.s).equals("output"))
		{

		}
		else if ((n.id.s).equals("input"))
		{
			String s="Scanner sc = new Scanner(System.in);\n"+"\t\tSystem.out.println(\"Enter the input\");\n"+"\t\tString inp = sc.next";			

		}
		else 
		{n.id.accept(this);}
		for (int i=0;i<n.exList.size();i++) {
			
			n.exList.get(i).accept(this);
			if (i!=n.exList.size()-1) {
			}
		}
	}

	public void visit (StringLiteral n){

	}


}
