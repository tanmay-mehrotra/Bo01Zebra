import java.io.*;
import java.util.*;
class TypeCasting extends Expr {
	public Type t;
	public Expr e;
    public int line;
	public TypeCasting (Type t1,Expr e1,int line1){
		super(line1);
		t=t1;
		e=e1;
        
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

