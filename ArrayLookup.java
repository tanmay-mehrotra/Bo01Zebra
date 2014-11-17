import java.io.*;
import java.util.*;
public class ArrayLookup extends Expr {
	Identifier id;
	Expr e1;
	Expr e2;
    int line;
	
	public ArrayLookup (Identifier id1,Expr ee1,Expr ee2, int line1) {
		super(line1);
		id = id1;
		e1=ee1;
		e2=ee2;
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
