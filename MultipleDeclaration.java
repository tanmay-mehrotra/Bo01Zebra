import java.io.*;
import java.util.*;
class MultipleDeclaration extends DeclarationNode {
	public Type t;
	public ArrayList<Identifier> idlist;
    public int line;
	public MultipleDeclaration (Type t1,ArrayList<Identifier> idlist1,int line1) {
	t=t1;
	idlist=new ArrayList<Identifier>(idlist1);
        line=line1;
	
	} 
	void additem(Identifier id1){
	idlist.add(id1);
	}
	int size(){
		return idlist.size();
	}
/*	public String toString(){
	
	return ("Size is "+size()+"Type is "+t+"ArrayList is "+idlist);
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

