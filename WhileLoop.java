import java.io.*;
import java.util.*;
public class WhileLoop extends StatementNode {
	Expr cond;
	BlockStatement whilestmt;
	int line;
	
	public WhileLoop (Expr e1,BlockStatement stmt1,int line1) {
		cond=e1;	
		whilestmt=stmt1;
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
