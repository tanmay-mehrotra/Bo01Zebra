import java.io.*;
import java.util.*;
class BlockStatement extends StatementNode {
//	public ArrayList<DeclarationNode> bdecl;
//	public StatementList stl;
	public ArrayList<Node> bstmts;
    public int line;
	public BlockStatement (ArrayList<Node> b1,int line1) {
		bstmts=new ArrayList<Node>(b1);
        line = line1;
		//stl=stl1;
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

