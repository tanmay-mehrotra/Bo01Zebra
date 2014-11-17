%{

import java.io.*;
import java.util.*;
enum Type {INT,STRING};

interface Visitor {
	public void visit (Nodelist n);
	public void visit (RoutineDeclaration n);
	public void visit (MultipleDeclaration n);
	public void visit (Assign n);
	public void visit (Identifier n);
	public void visit (IntegerLiteral n);

}
interface TypeVisitor {
	public Type visit (Nodelist n);
	public Type visit (RoutineDeclaration n);
	public Type visit (MultipleDeclaration n);
	public Type visit (Assign n);
	public Type visit (Identifier n);
	public Type visit (IntegerLiteral n);

}

class TypeCheckerVisitor implements TypeVisitor {

	//ArrayList<Node> nodes
	public Type visit (Nodelist n) {
		Type nlt=null;
		for (int i=0;i<n.nodes.size();i++) {
			//System.out.println();
			nlt=n.nodes.get(i).accept(this);
		}
		return nlt;
	}
	//Type t
	//Identifier id
	public Type visit (RoutineDeclaration n){
		//System.out.print(n.t);
		//System.out.print(" ");
		n.id.accept(this);
		//Check for duplicate declaration
		//System.out.print(";");
		return n.t;
	}

	//Type t;
	//ArrayList<Identifier> idlist;
	public Type visit (MultipleDeclaration n){
		//System.out.print(n.t+" ");
		for (int i=0;i<n.idlist.size();i++) {
			
			Type mt=n.idlist.get(i).accept(this);
			if (i!=n.idlist.size()-1) {
			//System.out.print(",");
			}
		}
		//System.out.println(";");
		return n.t;
		
	}

	//Identifier id;
	//Expr e;
	public Type visit (Assign n){
		Type idt=n.id.accept(this);
		//System.out.print("=");
		Type et=n.e.accept(this);
		//System.out.println(";");
		if (idt==et)
		{
			System.out.println("Type matched");
			return et;
		}
		else 
		{
			System.out.println("Type Mismatch");
			return null;
		}
	}


//	String s
	public Type visit (Identifier n){
		//System.out.print(n.s);
		return Type.INT;
		//find return type from symbol table
	}

//	int i
	public Type visit (IntegerLiteral n){
		//System.out.print(n.i);
		return Type.INT;
	}


}

class PrettyPrintVisitor implements Visitor {

	//ArrayList<Node> nodes
	public void visit (Nodelist n) {
		for (int i=0;i<n.nodes.size();i++) {
			//System.out.println();
			n.nodes.get(i).accept(this);
		}
	}
	//Type t
	//Identifier id
	public void visit (RoutineDeclaration n){
		System.out.print(n.t);
		System.out.print(" ");
		n.id.accept(this);
		System.out.print(";");
	}

	//Type t;
	//ArrayList<Identifier> idlist;
	public void visit (MultipleDeclaration n){
		System.out.print(n.t+" ");
		for (int i=0;i<n.idlist.size();i++) {
			
			n.idlist.get(i).accept(this);
			if (i!=n.idlist.size()-1) {
			System.out.print(",");}
		}
		System.out.println(";");
		
	}

	//Identifier id;
	//Expr e;
	public void visit (Assign n){
		n.id.accept(this);
		System.out.print("=");
		n.e.accept(this);
		System.out.println(";");
	}


//	String s
	public void visit (Identifier n){
		System.out.print(n.s);
	}

//	int i
	public void visit (IntegerLiteral n){
		System.out.print(n.i);
	}


}
abstract class Program {
	public abstract void accept(Visitor v);
	public abstract Type accept(TypeVisitor v);
}
class Nodelist extends Program {
	public ArrayList<Node> nodes;
	public Nodelist(ArrayList<Node> nodes1){
		nodes=nodes1;
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
	
	
}

abstract class Node extends Program  {
	public abstract void accept(Visitor v);
	public abstract Type accept(TypeVisitor v);
}

abstract class DeclarationNode extends Node {
	public abstract void accept(Visitor v);
	public abstract Type accept(TypeVisitor v);
}
abstract class Expr extends Node {
	public abstract void accept(Visitor v);
	public abstract Type accept(TypeVisitor v);
}
abstract class StatementNode extends Node{
	public abstract void accept(Visitor v);
	public abstract Type accept(TypeVisitor v);
}
class RoutineDeclaration extends Node {
	public Type t;
	public Identifier id;
	public RoutineDeclaration(Type t1,Identifier id1) {
		t=t1;
		id=id1;
	}
/*	public String toString(){
	return ("Type is "+t+"and Identifier name is "+id);
	}*/
	public void accept (Visitor v){
		v.visit(this);
	}
	public Type accept(TypeVisitor v){
		return v.visit(this);
	}

}
/*class DeclarationAssignmentNode extends Node {
	Type t;
	Assign a;
	public DeclarationAssignment(Type t1,Assign a1){
	t=t1;
	a1=a;	
	}
	public String toString(){
	return ("Type is "+t+" and Assignment is "+a);
	}
}*/
class Assign extends StatementNode {
	public Identifier id;
	public Expr e;
	public Assign (Identifier id1,Expr e1) {
		id=id1;
		e=e1;
	}
	public String toString(){
	if (e instanceof IntegerLiteral){
		return ("Identifier is "+id);
	}
	else {
		return ("Doomed!!");
	}}
	public void accept (Visitor v){
		v.visit(this);
	}
	public Type accept(TypeVisitor v){
		return v.visit(this);
	}

}
class Identifier extends Expr {
	public String s;
	public Identifier (String s1){
	s=s1;
	}
/*	public String toString(){
	return ("Identifier name is "+s);
	}*/
	public void accept (Visitor v){
		v.visit(this);
	}
	public Type accept(TypeVisitor v){
		return v.visit(this);
	}

}

class MultipleDeclaration extends DeclarationNode {
	public Type t;
	public ArrayList<Identifier> idlist;
	public MultipleDeclaration (Type t1,ArrayList<Identifier> idlist1) {
	t=t1;
	idlist=idlist1;
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

}

class IntegerLiteral extends Literal {
	public int i;
	public IntegerLiteral (int i1){
		i=i1;
	}
/*	public String toString(){
		return ("Integer is "+i);
	}*/
	public void accept (Visitor v){
		v.visit(this);
	}
	public Type accept(TypeVisitor v){
		return v.visit(this);
	}

}

abstract class Literal extends Postfix_expression{}
abstract class Postfix_expression extends Unary_expression_not_plus_minus {}
abstract class Unary_expression_not_plus_minus extends Unary_expression {}
abstract class Unary_expression extends Multiplicative_expression {}
abstract class Multiplicative_expression extends Additive_expression{}
abstract class Additive_expression extends Relational_expression {}
abstract class Relational_expression extends Equality_expression{}
abstract class Equality_expression extends Nand_gate_expression {}
abstract class Nand_gate_expression extends Xnor_gate_expression {}
abstract class Xnor_gate_expression extends Nor_gate_expression {}
abstract class Nor_gate_expression extends And_expression{}
abstract class And_expression extends Exclusive_or_expression{}
abstract class Exclusive_or_expression extends Inclusive_or_expression{}
abstract class Inclusive_or_expression extends Conditional_and_expression {}
abstract class Conditional_and_expression extends Conditional_or_expression{}
abstract class Conditional_or_expression extends Conditional_expression{}
abstract class Conditional_expression extends Assignment_expression{}
abstract class Assignment_expression extends Expr {}

%}

%token ANYEXCEPTION
%token BEXP BIT BREAK
%token CASE CATCH CLASS CONTINUE CHARACTER
%token DO DEFAULT
%token ELSE EOF
%token FINAL FALSE FOR FUNC
%token IMPORT INT IF
%token KMAP
%token NEW NULL NL
%token PUBLIC PACKAGE PRIVATE PROTECTED
%token RETURN
%token SWITCH STATIC STRING SCAND SCOR
%token THIS THROW THROWS TRY TRUE TRUTHTABLE
%token VOID
%token WHILE
%token '*'
%token <ival> INTEGERLITERAL CHARACTERLITERAL BITLITERAL
%token <sval> STRINGLITERAL BEXPRESSIONLITERAL IDENTIFIER

//http://ee.hawaii.edu/~tep/EE160/Book/chap5/subsection2.1.4.1.html

%right '='

%left SCOR
%left SCAND
%left '|' OR
%left '^' XOR
%left '&' AND
%left '>' '<' LEQ GEQ DEQ NEQ
%left '+' '-'
%left '%' '*' '/'
%right '!' NOT NOR NAND XNOR
%left '.'
%nonassoc '@'


%%

goal : compilation_unit {Nodelist nd=new Nodelist(n);nd.accept(new PrettyPrintVisitor());} ;

compilation_unit : compilation_unit declaration  | declaration ;
declaration : function_declaration | variable_declaration {if (idlist.size()==1) {rd=new RoutineDeclaration(t,id);System.out.println(rd);n.add(rd);} else { md=new MultipleDeclaration(t,idlist);System.out.println(md);n.add(md); }} | statement {System.out.println(ass+ " Wins");n.add(ass);} ;

variable_declaration : type variable_declarator ';' ;

variable_declarator : variable_declarator ',' variable_declarator1 | variable_declarator1;
variable_declarator1: ad | vd | ai | vi ;//vdid1 | vdid "=" vdin;
vd : IDENTIFIER {id=new Identifier($1);System.out.println("Identifier Defined");idlist.add(id);}; 
vi : vd "=" expression ;
ad : vd "[" expression "]" | vd "[" expression "]" "[" expression "]" ;
ai : vd "[" "]" "=" init_array | vd "[" "]" "[" "]" "=" init_array;
//vdid1: vdid1 "[" expression "]" | IDENTIFIER ;
//vdid : vdid "["  "]" | IDENTIFIER ;

vdin : expression | init_array;

init_array :  "{" variable_initializers "," "}"
	|	"{" variable_initializers "}"
	|     "{" "," "}"
	|	"{" "}"
	;
variable_initializers :
		vdin
	|	variable_initializers "," vdin
	;


//variable_assignment : expression ;//IDENTIFIER '=' variable_assignment | IDENTIFIER '=' expression ;


function_declaration : FUNC function_header function_body ;
function_header : type IDENTIFIER '(' formal_parameter_list ')' ;
formal_parameter_list : formal_parameters | ;
formal_parameters : formal_parameters ',' formal_parameter | formal_parameter ;
formal_parameter : type IDENTIFIER ;
function_body : block ;

block : '{' block_statements_or_empty '}' ;
block_statements_or_empty : block_statements | ;
block_statements : block_statements block_statement | block_statement ;
block_statement : local_variable_declaration_statement | statement ;
local_variable_declaration_statement : local_variable_declaration ';';
local_variable_declaration : type variable_declarator ;

statement :	statement_without_trailing_substatement
	|	if_then_statement
	|	if_then_else_statement
//	|	while_statement
	|	for_statement
	;
statement_matched_if :
		statement_without_trailing_substatement
	|	if_then_else_statement_matched_if
//	|	while_statement_no_short_if
	|	for_statement_matched_if
	;
statement_without_trailing_substatement :
		block
	|	empty_statement
	|	statement_expression ';'
//	|	switch_statement
	|	do_statement
	|	break_statement
	|	continue_statement
	|	return_statement
//	|	synchronized_statement
//	|	throw_statement
	|	try_statement
	;
empty_statement :
		';'
	;
if_then_statement :
		IF '(' expression ')' statement
	;
if_then_else_statement :
		IF '(' expression ')' statement_matched_if 
			ELSE statement
	;
if_then_else_statement_matched_if :
		IF '(' expression ')' statement_matched_if
			ELSE statement_matched_if
	;
do_statement :
		DO statement WHILE '(' expression ')' ';'
	;
for_statement :
		FOR '(' for_init_or_empty ';' expression_or_empty ';'
			for_update_or_empty ')' statement
	;
for_statement_matched_if :
		FOR '(' for_init_or_empty ';' expression_or_empty ';'
			for_update_or_empty ')' statement_matched_if
	;
for_init_or_empty :
	|	for_init
	;
for_init :	statement_expression_list
	|	local_variable_declaration
	;
for_update_or_empty :
	|	for_update
	;
for_update :	statement_expression_list
	;

expression_or_empty :
	|	expression
	;
statement_expression_list :
		statement_expression
	|	statement_expression_list ',' statement_expression
	;
statement_expression :
		assignment
	|	method_invocation
	;

break_statement :
		BREAK ';'
	;

continue_statement :
		CONTINUE ';'
	;
return_statement :
		RETURN expression_or_empty ';'
	;
try_statement :
		TRY block catches
	;
catches :	catch_clause
	|	catches catch_clause
	;
catch_clause :
		CATCH '(' formal_parameter ')' block
	;

expression :	assignment_expression ;

assignment_expression :
		conditional_expression {asse=ce;}
//	|	gate_expression
	|	assignment
	;

assignment :	IDENTIFIER '=' assignment_expression {id=new Identifier($1);ass=new Assign(id,asse);System.out.println("Ass found");} | array_access "=" assignment_expression;

postfix_expression : IDENTIFIER | literal {pe=l;} | '(' expression ')' | method_invocation | array_access | IDENTIFIER "@" BITLITERAL;

//idlet : IDENTIFIER | literal ;

array_access : IDENTIFIER "[" expression "]"
	|	IDENTIFIER "@" BITLITERAL "[" expression "]"
	| 	array_access "[" expression "]"
	; 

method_invocation :
		IDENTIFIER '(' argument_list_opt ')'
	;
argument_list_opt :
	|	argument_list
	;
argument_list :
		expression
	|	argument_list ',' expression 
	;

unary_expression :
		'+' unary_expression
	|	'-' unary_expression
	|	unary_expression_not_plus_minus {ue=uenpm;}
	;
unary_expression_not_plus_minus :
		postfix_expression {uenpm=pe;}
	|	'!' unary_expression
	| 	NOT unary_expression
	|	cast_expression
	|	postfix_expression "." postfix_expression
//	|	postfix_operation "." postfix_expression
	;
cast_expression :
		'(' type ')' unary_expression
//	|	'(' expression ')' unary_expression_not_plus_minus
	;

multiplicative_expression :
		unary_expression {me=ue;}
	|	multiplicative_expression '*' unary_expression
	|	multiplicative_expression '/' unary_expression
	|	multiplicative_expression '%' unary_expression
	;
additive_expression :
		multiplicative_expression {ae=me;}
	|	additive_expression '+' multiplicative_expression
	|	additive_expression '-' multiplicative_expression
	;
relational_expression :
		additive_expression {re=ae;}
	|	relational_expression '<' additive_expression
	|	relational_expression '>' additive_expression
	|	relational_expression LEQ additive_expression
	|	relational_expression GEQ additive_expression
	;
equality_expression :
		relational_expression {ee=re;}
	|	equality_expression DEQ relational_expression
	|	equality_expression NEQ relational_expression
	;

nand_gate_expression:
		equality_expression {nge=ee;}
	|	nand_gate_expression NAND equality_expression
	;

xnor_gate_expression :
		nand_gate_expression {xge=nge;}
	|	xnor_gate_expression XNOR nand_gate_expression
	;
nor_gate_expression:
		xnor_gate_expression {norge=xge;}
	|	nor_gate_expression NOR xnor_gate_expression
	;
and_expression :
		nor_gate_expression {ande=norge;}
	|	and_expression '&' nor_gate_expression
	|	and_expression AND nor_gate_expression
	;

exclusive_or_expression :
		and_expression {eoe=ande;}
	|	exclusive_or_expression '^' and_expression
	|	exclusive_or_expression XOR and_expression
	;

inclusive_or_expression :
		exclusive_or_expression {ioe=eoe;}
	|	inclusive_or_expression '|' exclusive_or_expression
	| 	inclusive_or_expression OR exclusive_or_expression
	;
conditional_and_expression :
		inclusive_or_expression {cae=ioe;}
	|	conditional_and_expression SCAND inclusive_or_expression
	;
conditional_or_expression :
		conditional_and_expression {coe=cae;}
	|	conditional_or_expression SCOR conditional_and_expression
	;
conditional_expression :
		conditional_or_expression {ce=coe;}
	;

/*gate_expression:
	or_gate_expression ;

or_gate_expression :
		xor_gate_expression
	|	or_gate_expression OR xor_gate_expression
	;
xor_gate_expression:
		and_gate_expression
	|	xor_gate_expression XOR and_gate_expression
	;
and_gate_expression:
		nor_gate_expression
	|	and_gate_expression AND nor_gate_expression
	;
nor_gate_expression:
		xnor_gate_expression
	|	nor_gate_expression NOR xnor_gate_expression
	;
xnor_gate_expression:
		nand_gate_expression
	|	xnor_gate_expression XNOR nand_gate_expression
	;
nand_gate_expression:
		not_gate_expression
	|	nand_gate_expression NAND not_gate_expression
	;
not_gate_expression :
		boolean_expression
	|	NOT boolean_expression
	;
boolean_expression:
		postfix_expression ;
*/
literal : STRINGLITERAL | INTEGERLITERAL {il=new IntegerLiteral($1);System.out.println("Num Literal"+il);l=il; } | BEXPRESSIONLITERAL | BITLITERAL;
type : INT {t=Type.INT;System.out.println("Type defined");}| BIT | BEXP | STRING | TRUTHTABLE | KMAP | ANYEXCEPTION;
	

%%

  private BoolZebra lexer;
  Type t;
  Identifier id;
  RoutineDeclaration rd;
  MultipleDeclaration md;
  ArrayList<Identifier> idlist=new ArrayList<Identifier>();
  ArrayList<Node> n=new ArrayList<Node> ();
  IntegerLiteral il;
  Assign ass;

Literal l;
Postfix_expression pe;
Unary_expression ue;
Unary_expression_not_plus_minus uenpm;
Multiplicative_expression me;
Additive_expression ae;
Relational_expression re;
Equality_expression ee;
Nand_gate_expression nge;
Xnor_gate_expression xge;
Nor_gate_expression norge;
And_expression ande;
Exclusive_or_expression eoe;
Inclusive_or_expression ioe;
Conditional_and_expression cae;
Conditional_or_expression coe;
Conditional_expression ce;
Assignment_expression asse;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new BoolZebra(r, this);
  }


  static boolean interactive;

  public static void main(String args[]) throws IOException {
    System.out.println("Demo of BoolZebra");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Expression: ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();
    
  }
