import java.io.*;
import java.util.*;
class BexpressionLiteral extends Literal {
	public String s;
    public int line;
	public BexpressionLiteral (String s1,int line1){
		super(line1);	
		s=s1;
		line=line1;
        
	}
	public String getS(){
		return s;
	}
	public String toString(){
		return ("String is "+s);
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

