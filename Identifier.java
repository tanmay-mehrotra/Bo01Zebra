import java.io.*;
import java.util.*;
class Identifier extends Expr {
	public String s;
    public int line;
	public Identifier (String s1,int line1){
	super(line1);
        s=s1;
        line=line1;
	}
/*	public String toString(){
	return ("Identifier name is "+s);
	}*/
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

