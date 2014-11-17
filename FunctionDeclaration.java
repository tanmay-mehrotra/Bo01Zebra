import java.io.*;
import java.util.*;
class FunctionDeclaration extends DeclarationNode {
	public Type ft;
	public Identifier fid;
	public Fargumentlist fagl;
	//public DeclarationNode fdecl;
	public BlockStatement fbs;
	public Expr fexpr;
    public int line;
	public FunctionDeclaration (Type ft1,Identifier fid1,Fargumentlist fagl1,BlockStatement fbs1,Expr fexpr1,int line1) {
		ft=ft1;
		fid=fid1;
		fagl=fagl1;
		//fdecl=fdecl1;
		fbs=fbs1;
		fexpr=fexpr1;
        line = line1;
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

