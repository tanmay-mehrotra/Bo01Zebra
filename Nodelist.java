import java.io.*;
import java.util.*;
class Nodelist extends Program {
	public ArrayList<Node> nodes;
    public int line;
	public Nodelist(ArrayList<Node> nodes1,int line1){
		nodes=nodes1;
        line=line1;
	}
	void additem(Node n){
	nodes.add(n);
	}
	int size(){
		return nodes.size();
	}
/*	public String toString(){
	
	return ("Size is "+size());
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

