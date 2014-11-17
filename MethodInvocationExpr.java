import java.io.*;
import java.util.*;
class MethodInvocationExpr extends Expr {
	public Identifier id;
	public ArrayList<Expr> exList;
    public int line;
	public MethodInvocationExpr (Identifier id1,ArrayList<Expr> a1,int line1) {
		super(line1);
		id=id1;
		exList=new ArrayList<Expr>(a1);
		line=line1;
        
	}
	
	public void accept (Visitor v){
		v.visit(this);
	}
	public Type accept(TypeVisitor v){
		return v.visit(this);
	}
    public int getLine () {
        return line;
    }

}

