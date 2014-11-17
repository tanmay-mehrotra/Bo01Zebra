import java.io.*;
import java.util.*;
class Fargumentlist {
	public ArrayList<Fargument> fag;
    public int line;
	public Fargumentlist(ArrayList<Fargument> fag1,int line1)  {
		fag=new ArrayList<Fargument>(fag1);
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

