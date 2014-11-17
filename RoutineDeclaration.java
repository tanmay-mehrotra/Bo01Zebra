import java.io.*;
import java.util.*;
class RoutineDeclaration extends DeclarationNode {
	public Type t;
	public Identifier id;
    public int line;
	public RoutineDeclaration(Type t1,Identifier id1,int line1) {
		t=t1;
		id=id1;
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

