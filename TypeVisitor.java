import java.io.*;
import java.util.*;
interface TypeVisitor {
	public Type visit (Nodelist n);
	public Type visit (RoutineDeclaration n);
	public Type visit (MultipleDeclaration n);
	public Type visit (Assign n);
	public Type visit (Identifier n);
	public Type visit (IntegerLiteral n);
	public Type visit (StringLiteral n);
	public Type visit (MethodInvocation n);
	public Type visit (FunctionDeclaration n);
	public Type visit (Fargumentlist n);
	public Type visit (Fargument n);
	public Type visit (BlockStatement n);

	public Type visit (BinExpr n);
	public Type visit (UnaryExpr n);
	public Type visit (MethodInvocationExpr n);
	public Type visit (IfStatement n);
	public Type visit (IfElseStatement n);

	public Type visit (WhileLoop n);
	public Type visit (RoutineArrayDeclaration n);
	public Type visit (ArrayLookup n);

	public Type visit (Return n);
	public Type visit (BitLiteral n);
	public Type visit (BexpressionLiteral n);
	public Type visit (TypeCasting n);
	public Type visit (Parenthesis n);

}
