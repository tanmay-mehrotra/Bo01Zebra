import java.io.*;
import java.util.*;
public class BinExpr extends Expr {
	Expr eleft;
	Expr eright;
	Operator op;
    int line;
	public BinExpr (Expr e1,Expr e2,Operator op1,int line1) {
		super(line1);
		eleft=e1;
		eright=e2;
		op=op1;
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
