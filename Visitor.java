import java.io.*;
import java.util.*;
interface Visitor {
	public void visit (Nodelist n);
	public void visit (RoutineDeclaration n);
	public void visit (MultipleDeclaration n);
	public void visit (Assign n);
	public void visit (Identifier n);
	public void visit (IntegerLiteral n);
	public void visit (StringLiteral n);
	public void visit (MethodInvocation n);
	public void visit (FunctionDeclaration n);
	public void visit (Fargumentlist n);
	public void visit (Fargument n);
	public void visit (BlockStatement n);

	public void visit (BinExpr n);
	public void visit (UnaryExpr n);
	public void visit (MethodInvocationExpr n);
	public void visit (IfStatement n);
	public void visit (IfElseStatement n);

	public void visit (WhileLoop n);
	public void visit (RoutineArrayDeclaration n);
	public void visit (ArrayLookup n);

	public void visit (Return n);
	public void visit (BitLiteral n);
	public void visit (BexpressionLiteral n);
	public void visit (TypeCasting n);
	public void visit (Parenthesis n);
}
