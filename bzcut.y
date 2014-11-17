%{

import java.io.*;
import java.util.*;

enum Type {INVALID,INT,STRING,BIT,BEXP,TRUTHTABLE,KMAP,VOID,STRINGARRAY};
enum Operator {SCOR,SCAND,LOR,LXOR,LAND,LT,GT,LEQ,GEQ,DEQ,NEQ,PLUS,MINUS,MULT,DIV,MOD,LNOT,UMINUS,AND,NAND,NOR,OR,XOR,XNOR,NOT,AT,DOT};

%}

%token BEXP BIT
%token ELSE 
%token FUNC
%token INT IF
%token KMAP
%token NULL NL
%token RETURN
%token STRING SCAND SCOR
%token TRUE TRUTHTABLE
%token VOID
%token WHILE
%token '*'
%token <ival> INTEGERLITERAL 
%token <sval> STRINGLITERAL BEXPRESSIONLITERAL IDENTIFIER BITLITERAL

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

goal : compilation_unit {Nodelist nd=new Nodelist(n,lexer.getLine()+1);ArrayList<ErrorMsg> errors=new ArrayList<ErrorMsg>();SymbolTable symboltable=new SymbolTable();nd.accept(new SymbolTableVisitor(symboltable,errors));nd.accept(new TypeCheckerVisitor(symboltable,fdecl,errors));
if (errors.size()!=0)
{
		for (int i=0;i<errors.size();i++)
		{
			System.out.println(errors.get(i).getMsg());
		}

System.out.println ("Program Aborting....");
System.exit(-1);
}
nd.accept(new PrettyPrintVisitor(symboltable,errors));} ;

compilation_unit : compilation_unit declaration  | declaration ;
declaration : function_declaration {n.add(fd);}| variable_declaration 
{if (arraydeclflag==1)
{
rad=new RoutineArrayDeclaration(t,id,e,null,lexer.getLine()+1);n.add(rad);
}
else if (arraydeclflag2==1)
{
rad=new RoutineArrayDeclaration(t,id,exprlist.get(exprlist.indexOf(e)-1),e,lexer.getLine()+1);n.add(rad);
}
else
{
if (idlist.size()==1)
 {rd=new RoutineDeclaration(t,id,lexer.getLine()+1);n.add(rd);
} 
else 
{ md=new MultipleDeclaration(t,idlist,lexer.getLine()+1);n.add(md); }
} 
idlist.clear();
exprlist.clear();
arraydeclflag=0;
arraydeclflag2=0;
} | statement {n.add(st);};

variable_declaration : type variable_declarator ';' ;

variable_declarator : variable_declarator ',' variable_declarator1 | variable_declarator1;
variable_declarator1: ad | vd;
vd : IDENTIFIER {id=new Identifier($1,lexer.getLine()+1);idlist.add(id);}; 
ad : vd "[" expression "]" {arraydeclflag=1;}| vd "[" expression "]" "[" expression "]" {arraydeclflag2=1;} ;

function_declaration : FUNC function_header function_body {
if (ret.rexpr!=null)
{fd=new FunctionDeclaration(rt,fid,fagl,bs,ret.rexpr,lexer.getLine()+1);fdecl.fdl.add(fd);}
else 
{
fd=new FunctionDeclaration(rt,fid,fagl,bs,null,lexer.getLine()+1);fdecl.fdl.add(fd);
}
fag.clear();
} ;
function_header : type {rt=t;} IDENTIFIER '(' formal_parameter_list ')' {fid=new Identifier($3,lexer.getLine()+1);fagl=new Fargumentlist(fag,lexer.getLine()+1);} ;
formal_parameter_list : formal_parameters | ;
formal_parameters : formal_parameters ',' formal_parameter {fag.add(fg);} | formal_parameter {fag.add(fg);} ;
formal_parameter : type IDENTIFIER {id=new Identifier($2,lexer.getLine()+1);fg=new Fargument(t,id,lexer.getLine()+1);} ;
function_body : block  ;

block : '{' {bst=new ArrayList<Node>();blockstack.push(bst);exprlist1=new ArrayList<Expr>();exprstack.push(exprlist1);} block_statements_or_empty '}' {blockstack.pop();};
block_statements_or_empty : block_statements {bs=new BlockStatement(blockstack.peek(),lexer.getLine()+1);st=bs;} | {bs=new BlockStatement(null,lexer.getLine()+1);st=bs;} ;
block_statements : block_statements block_statement | block_statement ;
block_statement : local_variable_declaration_statement {bst=blockstack.pop();bst.add(decl);blockstack.push(bst);} | statement {bst=blockstack.pop();bst.add(st);blockstack.push(bst);} ;
local_variable_declaration_statement : local_variable_declaration ';';
local_variable_declaration : type variable_declarator {
if (arraydeclflag==1)
{
decl=new RoutineArrayDeclaration(t,id,e,null,lexer.getLine()+1);
}
else if (arraydeclflag2==1)
{
decl=new RoutineArrayDeclaration(t,id,exprlist.get(exprlist.indexOf(e)-1),e,lexer.getLine()+1);
}
else
{
if (idlist.size()==1)
 {decl=new RoutineDeclaration(t,id,lexer.getLine()+1);} 
else { decl=new MultipleDeclaration(t,idlist,lexer.getLine()+1); }
}
 idlist.clear();
arraydeclflag=0;
arraydeclflag2=0;
} ;

statement :	statement_without_trailing_substatement
	|	if_then_statement {st=ifst;}
	|	if_then_else_statement {st=ifelsest;}
	|	while_statement {st=whileloop;}
	;
statement_matched_if :
		statement_without_trailing_substatement {bs1=bs;}
	|	if_then_else_statement_matched_if {bs1=bs;}
	|	while_statement_matched_if 
	;
statement_without_trailing_substatement :
		block
	|	statement_expression ';'
	|	return_statement {st=ret;}
	;

if_then_statement :
		IF '(' expression ')' statement {ifst=new IfStatement (exprlist.get(exprlist.size()-exprstack.peek().size()-1),bs,lexer.getLine()+1);
int temp=exprlist.size()-exprstack.peek().size()-2;
for (int i=exprlist.size()-1;i>temp;i--) 
{

exprlist.remove(i);
}

exprstack.pop();
if (!exprstack.empty())
{
exprlist2=exprstack.pop();
if (exprlist2.size()!=0)
{
exprlist2.remove(exprlist2.size()-1);
}
exprstack.push(exprlist2);

}

} ;

if_then_else_statement :
		IF '(' expression ')' statement_matched_if 
			ELSE statement {

int temp1=exprlist.size()-exprstack.peek().size()-1;
for (int i=exprlist.size()-1;i>temp1;i--)
{
exprlist.remove(i);
}
exprstack.pop();
ifelsest=new IfElseStatement (exprlist.get(exprlist.size()-exprstack.peek().size()-1),bs1,bs,lexer.getLine()+1);
int temp=exprlist.size()-exprstack.peek().size()-2;
for (int i=exprlist.size()-1;i>temp;i--) 
{

exprlist.remove(i);
}

exprstack.pop();
if (!exprstack.empty())
{
exprlist2=exprstack.pop();
if (exprlist2.size()!=0)
{
exprlist2.remove(exprlist2.size()-1);
}

exprstack.push(exprlist2);
}

}
	;
if_then_else_statement_matched_if :
		IF '(' expression ')' statement_matched_if
			ELSE statement_matched_if {}
	;
while_statement :
		WHILE '(' expression ')' statement {whileloop=new WhileLoop (exprlist.get(exprlist.size()-exprstack.peek().size()-1),bs,lexer.getLine()+1);
int temp=exprlist.size()-exprstack.peek().size()-2;
for (int i=exprlist.size()-1;i>temp;i--) 
{
exprlist.remove(i);
}

exprstack.pop();
if (!exprstack.empty())
{
exprlist2=exprstack.pop();
if (exprlist2.size()!=0)
{
exprlist2.remove(exprlist2.size()-1);
}
exprstack.push(exprlist2);
}


}
	;
while_statement_matched_if :
		WHILE '(' expression ')' statement_matched_if {whileloop=new WhileLoop (exprlist.get(0),bs,lexer.getLine()+1);exprlist.clear();}
	;

expression_or_empty :{e=null;}
	|	expression
	;

statement_expression :
		assignment {st=ass;}
	|	method_invocation {st=mi;}
	;

return_statement :
		RETURN expression_or_empty ';' {ret=new Return(e,lexer.getLine()+1);}
	;
expression :	assignment_expression {exprlist.add(e);if (exprlist1!=null) {exprlist1.add(e);}} 
	;
assignment_expression :
		conditional_expression 
	|	assignment
	;

assignment :	IDENTIFIER '=' assignment_expression {id=new Identifier($1,lexer.getLine()+1);ass=new Assign(id,e,lexer.getLine()+1);} | array_access "=" assignment_expression {ass=new Assign(alook,e,lexer.getLine()+1);};

postfix_expression : IDENTIFIER  {ide = new Identifier($1,lexer.getLine()+1);e=ide;estack.push(e);}| literal {e=l;estack.push(e);}   | '(' {parenflag=1;} expression ')' {par=new Parenthesis(e,lexer.getLine()+1);e=par;parenflag=0;estack.pop();estack.push(e);} | method_invocation {e=miexpr;} | array_access {e=alook;estack.pop();estack.push(e);} | IDENTIFIER "@" BITLITERAL {Identifier idbool=new Identifier($1,lexer.getLine()+1);BitLiteral bl1=new BitLiteral($3,lexer.getLine()+1);bexp=new BinExpr (idbool,bl1,Operator.AT,lexer.getLine()+1);e=bexp;};


array_access : IDENTIFIER "[" expression "]" {ida=new Identifier($1,lexer.getLine()+1);alook=new ArrayLookup(ida,e,null,lexer.getLine()+1);}
	|	IDENTIFIER "@" BITLITERAL "[" expression "]"
	| 	IDENTIFIER "[" expression "]" "[" expression "]" {ida=new Identifier($1,lexer.getLine()+1);alook=new ArrayLookup(ida,exprlist.get(exprlist.indexOf(e)-1),e,lexer.getLine()+1);exprlist.remove(exprlist.indexOf(e)-1);exprlist.remove(exprlist.indexOf(e));if (exprlist1!=null){exprlist1.remove(exprlist1.indexOf(e)-1);exprlist1.remove(exprlist1.indexOf(e));}} 
	; 

method_invocation :
		IDENTIFIER '(' argument_list_opt ')' {id1=new Identifier($1,lexer.getLine()+1);mi=new MethodInvocation(id1,aglist,lexer.getLine()+1);miexpr=new MethodInvocationExpr(id1,aglist,lexer.getLine()+1);aglist.clear();}
	;
argument_list_opt :
	|	argument_list
	;
argument_list :
		expression {aglist.add(e);}
	|	argument_list ',' expression {aglist.add(e);}
	;

unary_expression :
		'+' unary_expression {estack.pop();unexp= new UnaryExpr(e,Operator.PLUS,lexer.getLine()+1);e=unexp;e14=e;estack.push(e);}
	|	'-' unary_expression {estack.pop();unexp= new UnaryExpr(e,Operator.UMINUS,lexer.getLine()+1);e=unexp;e14=e;estack.push(e);}
	|	unary_expression_not_plus_minus {}
	;
unary_expression_not_plus_minus :
		postfix_expression 
	|	'!' unary_expression {estack.pop();unexp=new UnaryExpr(e,Operator.LNOT,lexer.getLine()+1);e=unexp;e14=e;estack.push(e);}
	| 	NOT unary_expression {estack.pop();unexp=new UnaryExpr(e,Operator.NOT,lexer.getLine()+1);e=unexp;e14=e;estack.push(e);}
	|	cast_expression
	|	IDENTIFIER {iddot1=new Identifier($1,lexer.getLine()+1);}  "." IDENTIFIER {Identifier iddot2=new Identifier($4,lexer.getLine()+1);bexp=new BinExpr (iddot1,iddot2,Operator.DOT,lexer.getLine()+1);e=bexp;}
	;
cast_expression :
		'(' type ')' unary_expression {tcast= new TypeCasting(t,e,lexer.getLine()+1);e=tcast;}
	;

multiplicative_expression :
		unary_expression {}
	|	multiplicative_expression '*' unary_expression {e14=estack.pop();e13=estack.pop();bexp=new BinExpr (e13,e14,Operator.MULT,lexer.getLine()+1);e=bexp;e13=bexp;estack.push(e);}
	|	multiplicative_expression '/' unary_expression {e14=estack.pop();e13=estack.pop();bexp=new BinExpr (e13,e14,Operator.DIV,lexer.getLine()+1);e=bexp;e13=bexp;estack.push(e);}
	|	multiplicative_expression '%' unary_expression {e14=estack.pop();e13=estack.pop();bexp=new BinExpr (e13,e14,Operator.MOD,lexer.getLine()+1);e=bexp;e13=bexp;estack.push(e);}
	;
additive_expression :
		multiplicative_expression {}
	|	additive_expression '+' multiplicative_expression {e13=estack.pop();e12=estack.pop();bexp=new BinExpr (e12,e13,Operator.PLUS,lexer.getLine()+1);e=bexp;e12=bexp;estack.push(e);} 
	|	additive_expression '-' multiplicative_expression {e13=estack.pop();e12=estack.pop();bexp=new BinExpr (e12,e13,Operator.MINUS,lexer.getLine()+1);e=bexp;e12=bexp;estack.push(e);} 
	;
relational_expression :
		additive_expression {}
	|	relational_expression '<' additive_expression {e12=estack.pop();e11=estack.pop();bexp=new BinExpr (e11,e12,Operator.LT,lexer.getLine()+1);e=bexp;e11=bexp;estack.push(e);}
	|	relational_expression '>' additive_expression {e12=estack.pop();e11=estack.pop();bexp=new BinExpr (e11,e12,Operator.GT,lexer.getLine()+1);e=bexp;e11=bexp;estack.push(e);}
	|	relational_expression LEQ additive_expression {e12=estack.pop();e11=estack.pop();bexp=new BinExpr (e11,e12,Operator.LEQ,lexer.getLine()+1);e=bexp;e11=bexp;estack.push(e);}
	|	relational_expression GEQ additive_expression {e12=estack.pop();e11=estack.pop();bexp=new BinExpr (e11,e12,Operator.GEQ,lexer.getLine()+1);e=bexp;e11=bexp;estack.push(e);}
	;
equality_expression :
		relational_expression {}
	|	equality_expression DEQ relational_expression {e11=estack.pop();e10=estack.pop();bexp=new BinExpr (e10,e11,Operator.DEQ,lexer.getLine()+1);e=bexp;e10=bexp;estack.push(e);}
	|	equality_expression NEQ relational_expression {e11=estack.pop();e10=estack.pop();bexp=new BinExpr (e10,e11,Operator.NEQ,lexer.getLine()+1);e=bexp;e10=bexp;estack.push(e);}
	;

nand_gate_expression:
		equality_expression {}
	|	nand_gate_expression NAND equality_expression {e10=estack.pop();e9=estack.pop();bexp=new BinExpr (e9,e10,Operator.NAND,lexer.getLine()+1);e=bexp;e9=bexp;estack.push(e);}
	;

xnor_gate_expression :
		nand_gate_expression {}
	|	xnor_gate_expression XNOR nand_gate_expression {e9=estack.pop();e8=estack.pop();bexp=new BinExpr (e8,e9,Operator.XNOR,lexer.getLine()+1);e=bexp;e8=bexp;estack.push(e);}
	;
nor_gate_expression:
		xnor_gate_expression {}
	|	nor_gate_expression NOR xnor_gate_expression {e8=estack.pop();e7=estack.pop();bexp=new BinExpr (e7,e8,Operator.NOR,lexer.getLine()+1);e=bexp;e7=bexp;estack.push(e);}
	;
and_expression :
		nor_gate_expression {}
	|	and_expression '&' nor_gate_expression {e7=estack.pop();e6=estack.pop();bexp=new BinExpr (e6,e7,Operator.LAND,lexer.getLine()+1);e=bexp;e6=bexp;estack.push(e);}
	|	and_expression AND nor_gate_expression {e7=estack.pop();e6=estack.pop();bexp=new BinExpr (e6,e7,Operator.AND,lexer.getLine()+1);e=bexp;e6=bexp;estack.push(e);}
	;

exclusive_or_expression :
		and_expression 
	|	exclusive_or_expression '^' and_expression {e6=estack.pop();e5=estack.pop();bexp=new BinExpr (e5,e6,Operator.LXOR,lexer.getLine()+1);e=bexp;e5=bexp;estack.push(e);}
	|	exclusive_or_expression XOR and_expression {e6=estack.pop();e5=estack.pop();bexp=new BinExpr (e5,e6,Operator.XOR,lexer.getLine()+1);e=bexp;e5=bexp;estack.push(e);}
	;

inclusive_or_expression :
		exclusive_or_expression 
	|	inclusive_or_expression '|' exclusive_or_expression {e5=estack.pop();e4=estack.pop();bexp=new BinExpr (e4,e5,Operator.LOR,lexer.getLine()+1);e=bexp;e4=bexp;estack.push(e);}
	| 	inclusive_or_expression OR exclusive_or_expression {e5=estack.pop();e4=estack.pop();bexp=new BinExpr (e4,e5,Operator.OR,lexer.getLine()+1);e=bexp;e4=bexp;estack.push(e);}
	;
conditional_and_expression :
		inclusive_or_expression
	|	conditional_and_expression SCAND inclusive_or_expression {e4=estack.pop();e3=estack.pop();bexp=new BinExpr (e3,e4,Operator.SCAND,lexer.getLine()+1);e=bexp;e3=bexp;estack.push(e);}
	;
conditional_or_expression :
		conditional_and_expression
	|	conditional_or_expression SCOR conditional_and_expression {e3=estack.pop();e2=estack.pop();bexp=new BinExpr (e2,e3,Operator.SCOR,lexer.getLine()+1);e=bexp;e2=bexp;estack.push(e);}
	;
conditional_expression :
		conditional_or_expression 
	;

literal : STRINGLITERAL  {sl=new StringLiteral($1,lexer.getLine()+1);l=sl; } | INTEGERLITERAL {il=new IntegerLiteral($1,lexer.getLine()+1);l=il; } | BEXPRESSIONLITERAL {bexpl=new BexpressionLiteral($1,lexer.getLine()+1);l=bexpl;} | BITLITERAL { bl=new BitLiteral($1,lexer.getLine()+1);l=bl;};
type : INT {t=Type.INT;}| BIT {t=Type.BIT;} | BEXP {t=Type.BEXP;} | STRING {t=Type.STRING;}| TRUTHTABLE {t=Type.TRUTHTABLE;} | KMAP {t=Type.KMAP;} | VOID {t=Type.VOID;} ;
	

%%

  private BoolZebra lexer;
  Type t,rt;
  Identifier id,id1,fid,ide,ida,iddot1;
  RoutineDeclaration rd;
  RoutineArrayDeclaration rad;
  MultipleDeclaration md;
  ArrayList<Identifier> idlist=new ArrayList<Identifier>();
  ArrayList<Node> n=new ArrayList<Node> ();
  IntegerLiteral il;
  StringLiteral sl;
  BitLiteral bl;
  BexpressionLiteral bexpl;
  Assign ass;
  MethodInvocation mi;
  MethodInvocationExpr miexpr;
  ArrayList<Expr> aglist=new ArrayList<Expr>();
  Fargumentlist fagl;
  ArrayList<Fargument> fag=new ArrayList<Fargument>();
  Expr e,ex,e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,cond;
  StatementNode st,ifst,elsest,ifelsest;
  Fargument fg;
  FunctionDeclaration fd;

  DeclarationNode decl;

 ArrayList<Node> bst=new ArrayList<Node>();
Stack <ArrayList<Node>> blockstack=new Stack<ArrayList<Node>>(); 
  ArrayList<Expr> exprlist=new ArrayList<Expr>();
  ArrayList<Expr> exprlist1=null,exprlist2=null;
Stack <ArrayList<Expr>> exprstack=new Stack<ArrayList<Expr>>(); 
  Stack<Expr> estack=new Stack<Expr>();
 FunctionDeclarationList fdecl=new FunctionDeclarationList();

  BlockStatement bs,bs1;
  BinExpr bexp;
  UnaryExpr unexp;

  WhileLoop whileloop;
  ArrayLookup alook;
  Return ret;
  TypeCasting tcast;
  Parenthesis par;
  Literal l;
int arraydeclflag=0,arraydeclflag2=0,parenflag=0;

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
    System.out.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new BoolZebra(r, this);
  }


  static boolean interactive;
  static boolean errorFlag= false;

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
