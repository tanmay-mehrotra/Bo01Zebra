import java.io.*;
import java.util.*;
public class UnaryExpr extends Expr {
	Expr eunary;
	Operator op;
        int line;
	public UnaryExpr (Expr e1,Operator op1,int line1) {
		super(line1);
		eunary=e1;
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
