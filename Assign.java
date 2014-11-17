import java.io.*;
import java.util.*;
class Assign extends StatementNode {
	public Identifier id;
	public Expr e;
	public ArrayLookup alook;
    public int line;
	public Assign (Identifier id1,Expr e1,int line1) {
		id=id1;
		e=e1;
		alook=null;
        line=line1;
	}

	public Assign (ArrayLookup al,Expr e1,int line1) {
		alook=al;
		e=e1;
		id=null;
        line=line1;
	}

	public String toString(){
	if (e instanceof IntegerLiteral){
		return ("Identifier is "+id);
	}
	else {
		return ("Doomed!!");
	}}
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

