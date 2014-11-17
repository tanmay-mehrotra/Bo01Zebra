import java.io.*;
import java.util.*;
class BitLiteral extends Literal {
	public String i;
    public int line;
	public BitLiteral (String i1,int line1){
		super(line1);	
		i=i1;
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

