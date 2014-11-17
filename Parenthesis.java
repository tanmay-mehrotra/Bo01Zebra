import java.io.*;
import java.util.*;
class Parenthesis extends Expr {
	public Expr e;
    public int line;
	public Parenthesis (Expr e1,int line1){
        	super(line1);
		e=e1;
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

