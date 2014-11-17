import java.io.*;
import java.util.*;
class Return extends StatementNode {
	public Expr rexpr;
    public int line;
	public Return (Expr rexpr1,int line1) {
		rexpr=rexpr1;
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
