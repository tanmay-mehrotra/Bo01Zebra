import java.io.*;
import java.util.*;
public class IfElseStatement extends StatementNode {
	Expr cond;	
	BlockStatement ifstmt;
	BlockStatement elsestmt;
	int line;
	public IfElseStatement (Expr e1,BlockStatement stmt1,BlockStatement stmt2,int line1) {
		cond=e1;	
		ifstmt=stmt1;
		elsestmt=stmt2;
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
