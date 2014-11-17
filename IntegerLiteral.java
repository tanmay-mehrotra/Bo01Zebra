import java.io.*;
import java.util.*;
class IntegerLiteral extends Literal {
	public int i;
    public int line;
	public IntegerLiteral (int i1,int line1){
		super(line1);	
		i=i1;
		line=line1;
        
	}
/*	public String toString(){
		return ("Integer is "+i);
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

