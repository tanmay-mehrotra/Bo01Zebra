import java.io.*;
import java.util.*;
public class IfStatement extends StatementNode {
	Expr cond;
	BlockStatement stmt;
	int line;
	public IfStatement (Expr cond1,BlockStatement stmt1,int line1) {
		cond=cond1;
		stmt=stmt1;
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
