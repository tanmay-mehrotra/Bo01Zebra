import java.io.*;
import java.util.*;
class TypeCheckerVisitor implements TypeVisitor {


	SymbolTable table;
	FunctionDeclarationList fdecl;
	ArrayList<ErrorMsg> errors;
	public TypeCheckerVisitor (SymbolTable st,FunctionDeclarationList fdecl1,ArrayList<ErrorMsg> errors1){
	table=st;
	fdecl=fdecl1;
	errors=errors1;
	}
		

	public Type visit (Nodelist n) {
		int[] funcflags=new int[n.nodes.size()];
		table.resetTable();

		table.enterScope();
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
		table.exitScope();

		return null;
	}
	public Type visit (RoutineDeclaration n){
		n.id.accept(this);
		return null;
	}

	public Type visit (RoutineArrayDeclaration n){
		Identifier aid=new Identifier("@"+n.id.s,n.id.getLine());
		aid.accept(this);
		return null;		
	}

	public Type visit (MultipleDeclaration n){

		for (int i=0;i<n.idlist.size();i++) {
			

			n.idlist.get(i).accept(this);
		}

		return null;
		
	}
	public Type visit (WhileLoop n) {
		
		Type condt=n.cond.accept(this);
		table.enterScope();
		if (condt!=null && condt!=Type.BIT)
		{
			int line = n.cond.getLine();
                        String errMsg = "Line " + line + ": incompatible types" +
                                "\nfound   : " + condt +
                                "\nrequired: bit";
                        errors.add(new ErrorMsg(errMsg, line));

		}
		n.whilestmt.accept(this);
		table.exitScope();
		return null;
		
	}

	public Type visit (FunctionDeclaration n)
	{
		int line;
		Type ret=n.ft;
		Type retrn;
		Identifier mid=new Identifier("$"+n.fid.s,n.fid.getLine());
		mid.accept(this);
		table.enterScope();
		n.fagl.accept(this);
		n.fbs.accept(this);
		if (n.fexpr!=null)
		{
		retrn=n.fexpr.accept(this);
		line = n.fexpr.getLine();
		}
		else {
		retrn=Type.VOID;
		line = n.getLine()-1;
		}

		if (ret!=null && retrn!=null && ret!=retrn)
		{

			
                        String errMsg = "Line " + line + ": incompatible return types" +
                                "\nfound   : " + retrn +
                                "\nrequired:"+ ret ;
                        errors.add(new ErrorMsg(errMsg, line));
			
			table.exitScope();
			
			return null;

		}

		table.exitScope();
		return null;
	
	}
	public Type visit (Return n)
	{
		Type ret;
		System.out.println("return ");
		if (n.rexpr!=null)
			{ret=n.rexpr.accept(this);
			return ret; }

		return Type.VOID;
	}

	public Type visit (Fargumentlist n) {
		

		for (int i=0;i<n.fag.size();i++)
		{
			n.fag.get(i).accept(this);
			
		} 
		return null;

	}
	public Type visit (Fargument n) {

		
		n.id.accept(this);
		return n.t;
	}


	public Type visit (MethodInvocation n){
		Symbol sym=Symbol.getSymbol("$"+n.id.s);
		Record r=table.lookup(sym);
		FunctionDeclaration fd=null;
		if (r!=null)
		{
			System.out.println("Declaration of this function found");
			for (int i=0;i<fdecl.fdl.size();i++)
			{
				if ((fdecl.fdl.get(i).fid.s).equals(n.id.s))
				{
					fd=fdecl.fdl.get(i);
					break;
				}
			}
			
			Fargumentlist fagl=fd.fagl;
			ArrayList<Expr> exList=n.exList;
			if (fagl.fag.size()!=exList.size())
			{
				int line = n.getLine();
                        	String errMsg = "Line " + line + ": Size of Formal and Argument List do not match!" ;
                                
                        	errors.add(new ErrorMsg(errMsg, line));

				
				return null;
			}
			for (int i=0;i<exList.size();i++)
			{
				Type f=fagl.fag.get(i).t;
				Type a=exList.get(i).accept(this);
				if (f!=null && a!=null && f!=a)
				{
					int line = n.getLine();
                        		String errMsg = "Line " + line + ": Type mismatch between formal paramter and function argument!" ;
                                
                        		errors.add(new ErrorMsg(errMsg, line));

					return null;
				}				
			}
		}		
		else 
		{
			if (n.id.s.equals("input") || n.id.s.equals("output") || n.id.s.equals("size") || n.id.s.equals("rowSize") || n.id.s.equals("columnSize") || n.id.s.equals("compare") || n.id.s.equals("flipVertical") || n.id.s.equals("flipHorizontal") || n.id.s.equals("countVariables") || n.id.s.equals("nameOfVariables"))
			{return null;}
			int line = n.getLine();
                        String errMsg = "Line " + line + ": Declaration of this function NOT found" ;
                                
                        errors.add(new ErrorMsg(errMsg, line));
				
			return null;		
		}
		return null;
	}

	public Type visit (MethodInvocationExpr n){
		Symbol sym=Symbol.getSymbol("$"+n.id.s);
		Record r=table.lookup(sym);
		FunctionDeclaration fd=null;
		if (r!=null)
		{
			System.out.println("Declaration of this function found");
			for (int i=0;i<fdecl.fdl.size();i++)
			{
				if ((fdecl.fdl.get(i).fid.s).equals(n.id.s))
				{
					fd=fdecl.fdl.get(i);
					break;
				}
			}
			
			Fargumentlist fagl=fd.fagl;
			ArrayList<Expr> exList=n.exList;
			if (fagl.fag.size()!=exList.size())
			{
				int line = n.getLine();
                        	String errMsg = "Line " + line + ": Size of Formal and Argument List do not match!" ;
                                
                        	errors.add(new ErrorMsg(errMsg, line));

				return null;
			}
			for (int i=0;i<exList.size();i++)
			{
				Type f=fagl.fag.get(i).t;
				Type a=exList.get(i).accept(this);
				if (f!=null && a!=null && f!=a)
				{
					int line = n.getLine();
                        		String errMsg = "Line " + line + ": Type mismatch between formal paramter and function argument!" ;
                                
                        		errors.add(new ErrorMsg(errMsg, line));

					return null;
				}				
			}
		}		
		else 
		{
			if (n.id.s.equals("input") || n.id.s.equals("output") || n.id.s.equals("size") || n.id.s.equals("rowSize") || n.id.s.equals("columnSize") || n.id.s.equals("compare") || n.id.s.equals("flipVertical") || n.id.s.equals("flipHorizontal") || n.id.s.equals("countVariables") || n.id.s.equals("nameOfVariables"))
			{return null;}
			int line = n.getLine();
                        String errMsg = "Line " + line + ": Declaration of this function NOT found" ;
                                
                        errors.add(new ErrorMsg(errMsg, line));

			return null;		
		}
		return fd.ft;

	}

	public Type visit (BlockStatement n) 
	{
		for (int i=0;i<n.bstmts.size();i++)
		{
			n.bstmts.get(i).accept(this);
		}
		return null;
	}

	public Type visit (IfElseStatement n) {

		Type condt=n.cond.accept(this);

		table.enterScope();
		if (condt!=null && condt!=Type.BIT)
		{
			int line=n.cond.getLine();
                       String errMsg = "Line " + line + ": incompatible types"+
                                "\nfound   : " + condt +
                                "\nrequired: bit";
                        errors.add(new ErrorMsg(errMsg, line));

		}
		n.ifstmt.accept(this);
		table.exitScope();
		table.enterScope();
		n.elsestmt.accept(this);
		table.exitScope();	
		return null;	
		
	}
	public Type visit (IfStatement n) {
		Type condt=n.cond.accept(this);
		table.enterScope();
		if (condt!=null && condt!=Type.BIT)
		{
			int line=n.cond.getLine();
                       String errMsg = "Line " + line + ": incompatible types"+
                                "\nfound   : " + condt +
                                "\nrequired: bit";
                        errors.add(new ErrorMsg(errMsg, line));

		}
		n.stmt.accept(this);
		table.exitScope();	
		return null;			
	}

	public Type visit (Assign n){
		Type idt=Type.INVALID;
		Symbol sym;
		Record r;
		int sizeleft=0,sizeright=0;
		if(n.id==null)
		{

		idt=n.alook.accept(this);
		}
		else if (n.alook==null)
		{
		sym=Symbol.getSymbol("@"+n.id.s);
		r=table.lookup(sym);
		
		if (r!=null)
		{
		sizeleft=r.getSize();
		sizeright=0;
		}


		idt=n.id.accept(this);
		}
		
		if (n.e instanceof BinExpr)
		{
			if (((BinExpr)n.e).eleft instanceof Identifier && ((BinExpr)n.e).op==Operator.AT)
			{
			Symbol sym1=Symbol.getSymbol("@"+((Identifier)((BinExpr)n.e).eleft).s);
			Record r1=table.lookup(sym1);
			if (r1!=null)
			{
				sizeright=r1.getSize();
				
			}
			}
		}
		Type et=n.e.accept(this);
		
		if (idt!=null && et!=null && !idt.equals(et))
		{
			if (idt==Type.TRUTHTABLE && (et==Type.BIT || et==Type.INT))
			{return null;}
			else if (idt==Type.STRINGARRAY && et==Type.STRING)
			{return null;}
			else if (idt==Type.KMAP && (et==Type.BIT || et==Type.INT))
            		{return null;}
			else
			{
			if (n.id!=null)
			{int line=n.getLine();
	                String errMsg = "Line " + line + ": Type mismatch of identifier : "+ n.id.s;
                        errors.add(new ErrorMsg(errMsg, line));

			 }

			else if (n.alook!=null)
			{
			int line=n.getLine();
                        String errMsg = "Line " + line + ":Type mismatch of identifier : "+ n.alook.id.s;
                        errors.add(new ErrorMsg(errMsg, line));

			
			}	
			return null;
			}
		}
		else if (sizeleft!=sizeright && (sizeright != 0 && sizeleft != 0))
		{
			int line=n.getLine();
			System.out.println("The sizes are "+sizeleft+" "+sizeright);
                        String errMsg = "Line " + line + ":Size of Left and Right Operands do not match : ";
                        errors.add(new ErrorMsg(errMsg, line));

			return null;
		}
		return null;
	
	}
	public Type visit (ArrayLookup n){
		int flag=0;
		Type idt=n.id.accept(this);
		if (idt==null)
		{
			return null;
		}
		Type edt1=n.e1.accept(this);
		if (edt1==null)
		{
			flag=0;
		}
		else if (edt1!=Type.INT)
		{
			flag=0;
			int line=n.getLine();
                        String errMsg = "Line " + line + ":Type Mismatch.Index not of int type";
                        errors.add(new ErrorMsg(errMsg, line));

			
		}
		else
		{
			flag=1;
		}
		if (n.e2!=null)
		{
			Type edt2=n.e2.accept(this);

			if (edt2==null)
			{
				flag=0;
			}
			else if (edt2!=Type.INT)
			{
			flag=0;

				int line=n.getLine();
                        	String errMsg = "Line " + line + ":Type Mismatch.Index 2 not of int type";
                        	errors.add(new ErrorMsg(errMsg, line));

				
			}
			else
			{
				flag=1;
			}
		}

		return idt; 
	}

	public Type visit (UnaryExpr n) {
		Type edt=n.eunary.accept(this);
		if (edt==null)
			return null;
		if (n.op==Operator.PLUS || n.op==Operator.UMINUS) {
			if (edt!=Type.INT)
			{
				int line=n.getLine();
                        	String errMsg = "Line " + line + ":Type Mismatch.Unary expression not of type INT";
                        	errors.add(new ErrorMsg(errMsg, line));

				return null;
			}
			else
			{
				return Type.INT;
			}
		}
		else if (n.op==Operator.LNOT) {
            	if (edt!=Type.BIT)
            	{
                	int line=n.getLine();
                        String errMsg = "Line " + line + ":Type Mismatch.Unary expression not of type Bit";
                        errors.add(new ErrorMsg(errMsg, line));
	                return null;
            	}
            	else
            	{
                	return Type.BIT;
            	}


        	}

		else if (n.op==Operator.NOT) {
			if (edt!=Type.BIT)
			{
				int line=n.getLine();
                        	String errMsg = "Line " + line + ":Type Mismatch.Unary expression not of type Bit";
                        	errors.add(new ErrorMsg(errMsg, line));

				
				return null;
			}
			else
			{
				return Type.BEXP;
			}

		}
		return null;
	
	}

	public Type visit (TypeCasting n) {
		Type edt=n.e.accept(this);
		if (n.t==Type.KMAP && edt==Type.TRUTHTABLE)
		{
			return n.t;
		}
		else
		{
			return null;
		}		

	}
	public Type visit (Parenthesis n) {
		Type edt=n.e.accept(this);
		return edt;

	}

	public Type visit (BinExpr n) {
		Type left=n.eleft.accept(this);
		Type right=n.eright.accept(this);
		
		switch (n.op)
		{
			case PLUS:
				
				if (left==null)
				{
					return null;
				}
				else if (left!=Type.INT && left!=Type.STRING)
				{
					int line=n.getLine();
                        		String errMsg = "Line " + line + ":Type Mismatch.Left operand not of int/string type";
                        		errors.add(new ErrorMsg(errMsg, line));

					
					return null;
				}
				else
				{
					if (right==null)
					{
						return null;
					}
					else if (right!=Type.INT && left!=Type.STRING)
					{
						int line=n.getLine();
                        			String errMsg = "Line " + line + ":Type Mismatch.Right operand not of int/String type";
                        			errors.add(new ErrorMsg(errMsg, line));

						
						return null;
					}
					else 
					{
						if (left!=right)
						{
						int line=n.getLine();
                        			String errMsg = "Line " + line + ":Type Mismatch.Both operands not of same type";
                        			errors.add(new ErrorMsg(errMsg, line));
	
						
						return null;
						}
						else
						{
						if (left==Type.INT)
							return Type.INT;
						else
							return Type.STRING;
						}
					}
				
				}
				
				

				
			case MINUS:
				
			case MULT:
				
			case DIV:
				
			case MOD:
				if (left==null)
				{
					return null;
				}
				else if (left!=Type.INT)
				{
					int line=n.getLine();
                       			String errMsg = "Line " + line + ":Type Mismatch.Left operand not of int type";
                       			errors.add(new ErrorMsg(errMsg, line));
		
					
					return null;
				}
				else
				{
					if (right==null)
					{
						return null;
					}
					else if (right!=Type.INT)
					{
						int line=n.getLine();
                        			String errMsg = "Line " + line + ":Type Mismatch.Right operand not of int type";
                        			errors.add(new ErrorMsg(errMsg, line));
		
						
						return null;
					}
					else 
					{
						return Type.INT;
					}
				
				}
				
			case LT:
				
			case GT:
				
			case LEQ:
				
			case GEQ:
				
				if (left==null)
				{
					return null;
				}
				else if (left!=Type.INT)
				{
					int line=n.getLine();
                       			String errMsg = "Line " + line + ":Type Mismatch.Left operand not of int type";
                       			errors.add(new ErrorMsg(errMsg, line));
	
					
					return null;
				}
				else
				{
					if (right==null)
					{
						return null;
					}
					else if (right!=Type.INT)
					{
						int line=n.getLine();
                        			String errMsg = "Line " + line + ":Type Mismatch.Right operand not of int type";
                        			errors.add(new ErrorMsg(errMsg, line));
	
						
						return null;
					}
					else 
					{
						return Type.BIT;
					}
				
				}
				

			case SCOR:
			
				
			case SCAND:
				
			case LOR:
				
			case LAND:
				
			case LXOR:
				if (left==null)
				{
					return null;
				}
				else if (left!=Type.BIT)
				{
					int line=n.getLine();
                       			String errMsg = "Line " + line + ":Type Mismatch.Left operands not of BIT type";
                       			errors.add(new ErrorMsg(errMsg, line));
	
					
					return null;
				}
				else
				{
					if (right==null)
					{
						return null;
					}
					else if (right!=Type.BIT)
					{
						int line=n.getLine();
                        			String errMsg = "Line " + line + ":Type Mismatch.Right operand not of BIT type";
                        			errors.add(new ErrorMsg(errMsg, line));
	
						
						return null;
					}
					else 
					{
						return Type.BIT;
					}
				
				}


			case NOR:
				
			case NAND:
				
			case OR:
				
			case AND:
				
			case XOR:
				
			case XNOR:
				
				if (left==null)
				{
					return null;
				}
				else if (left!=Type.BIT && left!=Type.BEXP)
				{
					int line=n.getLine();
                        		String errMsg = "Line " + line + ":Type Mismatch.Left operand not of BIT type";
                        		errors.add(new ErrorMsg(errMsg, line));
		
		
					return null;
				}
				else
				{
					if (right==null)
					{
						return null;
					}
					else if (right!=Type.BIT && right!=Type.BEXP)
					{
					int line=n.getLine();
                       			String errMsg = "Line " + line + ":Type Mismatch.Right operand not of bit type";
                       			errors.add(new ErrorMsg(errMsg, line));
		
					
						return null;
					}
					else 
					{
						return Type.BEXP;
					}
				
				}
				

			case DEQ:
				
			case NEQ:
				
				if ((left==null || right==null))
				{
					return null;
				}
				else if (left!=right)
				{
					int line=n.getLine();
                       			String errMsg = "Line " + line + ":Type Mismatch.Both operands not of same type";
                       			errors.add(new ErrorMsg(errMsg, line));
	
					
					return null;
				}
				else 
				{
					return Type.BIT;
				}
				
			case AT:

				if (left==null)
				{
					return null;
				}
				else if (left!=Type.TRUTHTABLE)
				{
					int line=n.getLine();
                        		String errMsg = "Line " + line + ":Type Mismatch.Left operand not of Truthtable type";
                        		errors.add(new ErrorMsg(errMsg, line));
	
					
					return null;
				}
				else
				{
					if (right==null)
					{
						return null;
					}
					else if (right!=Type.BIT)
					{
					int line=n.getLine();
                        		String errMsg = "Line " + line + ":Type Mismatch.Right operand not of bit type";
                        		errors.add(new ErrorMsg(errMsg, line));
	
						
						return null;
					}
					else 
					{
						return Type.TRUTHTABLE;
					}
				
				}

			case DOT:
				if (left==null)
				{
					System.out.println("1");
					return null;
				}
				else if (left!=Type.STRINGARRAY)
				{
					int line=n.getLine();
                        		String errMsg = "Line " + line + ":Type Mismatch.Left operand not of String Array type";
                        		errors.add(new ErrorMsg(errMsg, line));
	
					
					return null;
				}
				else
				{
					if (right==null)
					{
						System.out.println("2");
						return null;
					}
					else if (right!=Type.TRUTHTABLE)
					{
					int line=n.getLine();
                        		String errMsg = "Line " + line + ":Type Mismatch.Right operand not of Truthtable type";
                        		errors.add(new ErrorMsg(errMsg, line));

						
						return null;
					}
					else 
					{
						Symbol syma=Symbol.getSymbol("@"+((Identifier)n.eleft).s);
						Symbol symb=Symbol.getSymbol("@"+((Identifier)n.eright).s);
						Record ra=table.lookup(syma);
						Record rb=table.lookup(symb);
						System.out.println("Sizes are :"+ra.getSize()+rb.getSize());	
						if (ra.getSize()!=rb.getSize() && (ra.getSize() != 0 && rb.getSize() != 0))
						{ 
						int line=n.getLine();
	                        		String errMsg = "Line " + line + "Operands should be of samesize";
	                        		errors.add(new ErrorMsg(errMsg, line));
	
						
						return null;}					
						return Type.STRING;
					}
				
				}
				

			default:

		}
		
		return null;
	}

	public Type visit (Identifier n){
		System.out.print(n.s);
		Symbol sym=Symbol.getSymbol(n.s);
		Symbol syma=Symbol.getSymbol("@"+n.s);
		Record r=table.lookup(sym);
		Record ra=table.lookup(syma);	
		if (r!=null)
		{
			return r.getType();
		}
		else if (ra!=null)
		{
			Type ty=ra.getType();			
			
			if (ty.equals(Type.STRING))
				{System.out.println("Yes");return Type.STRINGARRAY;}
			else if (ty.equals(Type.TRUTHTABLE))
				{System.out.println("Yes");return Type.TRUTHTABLE;}

		}
		else 
		{
			
			if(n.s.startsWith("$")) {
                                n.s = n.s.substring(1);
                        }
			if(n.s.startsWith("@")) {
                                n.s = n.s.substring(1);
                        }
			int line=n.getLine();
			String errMsg="Line" + line + ": " +" Cannot find Symbol: "+n.s;
			errors.add(new ErrorMsg(errMsg,line));

			
			return null;
		}
			
		return null;
	}

	public Type visit (IntegerLiteral n){
		return Type.INT;
	}

	public Type visit (StringLiteral n){
		return Type.STRING;
	}
	public Type visit (BitLiteral n){
		return Type.BIT;
	}
	public Type visit (BexpressionLiteral n){
		return Type.BEXP;
	}

}

