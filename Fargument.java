import java.io.*;
import java.util.*;
class Fargument {
	public Type t;
	public Identifier id;
    public int line;
	public Fargument (Type t1,Identifier id1,int line1) {
		t=t1;
		id=id1;
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

