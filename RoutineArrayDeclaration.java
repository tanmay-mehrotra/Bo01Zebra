import java.io.*;
import java.util.*;
public class RoutineArrayDeclaration extends DeclarationNode {
	public Type t;
	public Identifier id;
	public Expr e1;
	public Expr e2;
    public int line;
	public RoutineArrayDeclaration(Type t1,Identifier id1,Expr ee1,Expr ee2,int line1) {
		t=t1;
		id=id1;
		e1=ee1;
		e2=ee2;
        line=line1;
	}
/*	public String toString(){
	return ("Type is "+t+"and Identifier name is "+id);
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
