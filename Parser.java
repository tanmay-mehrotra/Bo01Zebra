//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "bzcut.y"

import java.io.*;
import java.util.*;

enum Type {INVALID,INT,STRING,BIT,BEXP,TRUTHTABLE,KMAP,VOID,STRINGARRAY};
enum Operator {SCOR,SCAND,LOR,LXOR,LAND,LT,GT,LEQ,GEQ,DEQ,NEQ,PLUS,MINUS,MULT,DIV,MOD,LNOT,UMINUS,AND,NAND,NOR,OR,XOR,XNOR,NOT,AT,DOT};

//#line 25 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short BEXP=257;
public final static short BIT=258;
public final static short ELSE=259;
public final static short FUNC=260;
public final static short INT=261;
public final static short IF=262;
public final static short KMAP=263;
public final static short NULL=264;
public final static short NL=265;
public final static short RETURN=266;
public final static short STRING=267;
public final static short SCAND=268;
public final static short SCOR=269;
public final static short TRUE=270;
public final static short TRUTHTABLE=271;
public final static short VOID=272;
public final static short WHILE=273;
public final static short INTEGERLITERAL=274;
public final static short STRINGLITERAL=275;
public final static short BEXPRESSIONLITERAL=276;
public final static short IDENTIFIER=277;
public final static short BITLITERAL=278;
public final static short OR=279;
public final static short XOR=280;
public final static short AND=281;
public final static short LEQ=282;
public final static short GEQ=283;
public final static short DEQ=284;
public final static short NEQ=285;
public final static short NOT=286;
public final static short NOR=287;
public final static short NAND=288;
public final static short XNOR=289;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    4,    7,    7,    8,
    8,   10,    9,    9,    3,   14,   12,   15,   15,   16,
   16,   17,   13,   20,   18,   19,   19,   21,   21,   22,
   22,   23,   24,    5,    5,    5,    5,   29,   29,   29,
   25,   25,   25,   26,   27,   30,   28,   31,   34,   34,
   32,   32,   33,   11,   37,   37,   35,   35,   40,   40,
   42,   40,   40,   40,   40,   39,   39,   39,   36,   43,
   43,   44,   44,   45,   45,   45,   46,   46,   46,   46,
   48,   46,   47,   49,   49,   49,   49,   50,   50,   50,
   51,   51,   51,   51,   51,   52,   52,   52,   53,   53,
   54,   54,   55,   55,   56,   56,   56,   57,   57,   57,
   58,   58,   58,   59,   59,   60,   60,   38,   41,   41,
   41,   41,    6,    6,    6,    6,    6,    6,    6,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    1,    3,    3,    1,    1,
    1,    1,    4,    7,    3,    0,    6,    1,    0,    3,
    1,    2,    1,    0,    4,    1,    0,    2,    1,    1,
    1,    2,    2,    1,    1,    1,    1,    1,    1,    1,
    1,    2,    1,    5,    7,    7,    5,    5,    0,    1,
    1,    1,    3,    1,    1,    1,    3,    3,    1,    1,
    0,    4,    1,    1,    3,    4,    6,    7,    4,    0,
    1,    1,    3,    2,    2,    1,    1,    2,    2,    1,
    0,    4,    4,    1,    3,    3,    3,    1,    3,    3,
    1,    3,    3,    3,    3,    1,    3,    3,    1,    3,
    1,    3,    1,    3,    1,    3,    3,    1,    3,    3,
    1,    3,    3,    1,    3,    1,    3,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
  125,  124,    0,  123,    0,  128,    0,  126,  127,  129,
    0,    0,   24,    0,    0,    3,    4,    5,    6,    0,
   41,   34,   35,   36,   37,    0,   43,   51,   52,    0,
   16,    0,    0,  120,  119,  121,    0,  122,    0,    0,
    0,    0,    0,   50,    0,   56,   63,   54,   55,    0,
   77,   60,   84,   76,   80,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    2,   12,    0,    9,   10,    0,   42,
    0,    0,   15,   23,    0,    0,    0,    0,   64,   74,
   75,   78,   79,    0,    0,   53,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
    0,    0,   72,    0,    0,   31,    0,    0,    0,   29,
   30,    0,    7,    0,    0,   58,    0,    0,    0,    0,
    0,    0,   85,   87,   86,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   69,    0,    0,
   25,   28,   32,    8,    0,    0,    0,    0,   44,    0,
    0,   39,   40,   82,   83,   62,   47,    0,    0,   73,
    0,    0,    0,    0,   21,    0,    0,    0,   67,    0,
    0,   22,   17,    0,    0,    0,   45,   68,    0,   20,
    0,    0,   14,    0,   48,    0,   46,
};
final static short yydgoto[] = {                         14,
   15,   16,   17,   18,   19,   20,   76,   77,   78,   79,
   44,   32,   83,   82,  193,  194,  195,   21,  128,   73,
  129,  130,  131,  132,   22,   23,   24,   25,  181,  182,
  183,   26,   27,   45,   46,   47,   48,   49,   89,   51,
   52,   95,  124,  125,   53,   54,   55,   87,   56,   57,
   58,   59,   60,   61,   62,   63,   64,   65,   66,   67,
};
final static short yysindex[] = {                       967,
    0,    0,  -44,    0,  -21,    0,  -33,    0,    0,    0,
   -4,  -27,    0,    0,  967,    0,    0,    0,    0, -228,
    0,    0,    0,    0,    0,   -1,    0,    0,    0,   18,
    0,  -51,  -33,    0,    0,    0,   37,    0,    5,    5,
    5,    5,  -44,    0,   46,    0,    0,    0,    0,   18,
    0,    0,    0,    0,    0,   66,   -2,  -58, -268, -192,
 -183, -180,  -35,  -88, -115, -154, -142,  -33,  -33, -146,
  -33,  -33,  804,    0,    0,  -26,    0,    0,   42,    0,
  -33, -143,    0,    0,   95, -139,   96,   38,    0,    0,
    0,    0,    0,  104,  -33,    0,    5,    5,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,  105,    0,
   56,   62,    0,  115,  118,    0, -228,   40,  804,    0,
    0,  108,    0, -228,  -33,    0,  128,  -96,   56, -108,
    5,  132,    0,    0,    0,   66,   66,   -2,   -2,   -2,
   -2,  -58,  -58, -268, -192, -183, -180, -180,  -35,  -35,
  -88,  -88, -115, -154,  106,  -33,   89,    0,  -33,  139,
    0,    0,    0,    0,   94,  -44,  149,  150,    0,    0,
  -68,    0,    0,    0,    0,    0,    0,  100,  -33,    0,
  103,  -82,  155,  153,    0,  -33,  -33,  106,    0,  111,
  -33,    0,    0,  -44,  157,  158,    0,    0,  112,    0,
  -96,  -96,    0,  -53,    0,  -96,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,  148,    0,    0,    0,
    0,    0,    0,    0,  208,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   24,    0,    0,    0,
    0,    0,   11,    0,    0,    0,    0,    0,    0,   78,
    0,    0,    0,    0,    0,  141,  374,  349,  665,  769,
  827,  847,  872,  553,  -36,  -19,  -30,    0,    0,    0,
    0,  170,   87,    0,    0,    0,    0,    0,  -24,    0,
    0,    0,    0,    0,    0,    0,    0,   24,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  185,    0,    0,    0,  113,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  116,    0,
    0,    0,    0,    0,    0,  177,  282,  410,  433,  461,
  484,  546,  572,  743,  805,  831,  856,  863,  920,  933,
  749,  940,  -20,  -13,    0,    0,   50,    0,    0,  171,
    0,    0,    0,    0,    0,  190,    0,    0,    0,    1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -12,    0,    0,  206,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  236,    0,    0,  403,   12,  125,  120,    0,    0,
 1112,    0,    0,    0,    0,    0,   51,  225,    0,    0,
    0,  137,    0,    0, -112,    0,    0,    0,  -81,    0,
    0,    0,    0,    0,  377, 1025,  -39,    0, 1123,    0,
    0,    0,    0,    0,  439,    0,    0,    0,  -48,   47,
  -47,  161,  166,  167,  -22,  -38,  -34,  159,  172,    0,
};
final static int YYTABLESIZE=1339;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         41,
   34,  103,  111,  102,  114,  113,   43,  114,  115,   39,
  118,   40,   72,  118,   31,  106,  107,  134,   33,   11,
  115,  116,  114,  115,  116,  180,   13,  117,  118,  120,
  117,   13,  133,   69,   11,   68,   70,   41,  115,  116,
  100,  136,  101,   61,   43,  117,   13,   39,   75,   40,
   61,  146,  147,   61,   94,   61,  114,   80,  152,  153,
   59,   59,  118,   71,   59,   59,   59,   59,   59,   81,
   59,   13,  115,  116,  159,  160,   72,   72,   81,  117,
  161,  162,   59,   59,  127,   59,   66,   66,  157,  158,
   66,   66,   66,   66,   66,  108,   66,   69,  180,  180,
   86,   86,   98,  180,   96,  109,  110,   97,   66,   66,
   66,   66,   99,  117,   64,   64,   59,   59,   64,   64,
   64,   64,   64,   34,   64,   34,  118,   71,   71,  214,
  215,  121,  135,  137,  217,  138,   64,   64,  139,   64,
  127,  140,   66,   66,  141,  165,  166,   59,  148,  149,
  150,  151,   65,   65,  167,  168,   65,   65,   65,   65,
   65,  169,   65,  116,  171,  177,  173,  176,  184,    7,
   64,   64,  186,   66,   65,   65,  178,   65,   88,  189,
   12,   88,  134,   88,   88,   88,  191,  192,  196,  197,
  198,  114,  199,  201,  202,  203,  204,  211,  212,   88,
   88,   64,   88,  208,  213,  216,   49,    1,   65,   65,
   70,   27,    1,    2,   89,  192,    4,   89,    6,   89,
   89,   89,    8,  104,  105,   71,    9,   10,   13,   33,
   19,  114,  114,   88,   88,   89,   89,   26,   89,   65,
   34,   35,   36,   37,   38,  112,   18,  115,  115,  116,
   74,  170,   42,  174,  210,  117,   84,   34,   34,   38,
   34,   34,   34,   34,   88,  172,   34,   34,  154,   89,
   89,   34,   34,   34,  155,  163,  156,   34,   34,   35,
   36,   88,   38,    0,   61,   61,   61,   61,   61,  164,
   42,   59,   59,    0,    0,    0,   61,    0,    0,    0,
   89,    0,   59,   59,   59,   59,   59,   59,   59,    0,
   59,   59,   59,    0,    0,    0,    0,   66,   66,   90,
    0,    0,   90,    0,   90,   90,   90,    0,   66,   66,
   66,   66,   66,   66,   66,    0,   66,   66,   66,    0,
   90,   90,    0,   90,    0,   64,   64,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   64,   64,   64,   64,
   64,   64,   64,    0,   64,   64,   64,    5,    0,    0,
    0,    7,    0,    0,   90,   90,   28,    0,   11,    0,
    0,    0,   12,   65,   65,    0,   96,    0,    0,   96,
    0,   28,   96,    0,   65,   65,   65,   65,   65,   65,
   65,    0,   65,   65,   65,   90,    0,   96,   88,   88,
    0,   91,    0,    0,   91,    0,    0,   91,    0,   88,
   88,   88,   88,   88,   88,   88,    0,   88,   88,   88,
    0,    0,   91,   91,    0,   91,    0,    0,    0,    0,
    0,   96,   96,    0,   89,   89,    0,   93,    0,   28,
   93,    0,    0,   93,    0,   89,   89,   89,   89,   89,
   89,   89,    0,   89,   89,   89,   91,   91,   93,   93,
   92,   93,   96,   92,    0,  126,   92,   90,   91,   92,
   93,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   92,   92,    0,   92,    0,    0,   91,   94,    0,
    0,   94,   93,   93,   94,   28,    0,    0,    0,    0,
    0,    0,    0,    0,   28,    0,    0,    0,    0,   94,
   94,   95,   94,    0,   95,   92,   92,   95,    0,    0,
    0,  126,    0,   93,    0,  143,  144,  145,    0,    0,
  179,   28,   95,   95,    0,   95,    0,    0,    0,   90,
   90,    0,    0,   94,   94,    0,   92,    0,    0,    0,
   90,   90,   90,   90,   90,   90,   90,  187,   90,   90,
   90,    0,    0,    0,   28,    0,   95,   95,    0,  185,
    0,    0,    0,   97,   94,    0,   97,   28,   28,   97,
    0,    0,   28,  111,    0,    0,  111,    0,    0,    0,
  207,    0,    0,    0,   97,    0,    0,   95,    0,   98,
    0,  111,   98,  179,  187,   98,   96,   96,  207,    0,
    0,    0,    0,    0,    0,    0,    0,   96,   96,   96,
   98,    0,   96,   96,    0,   96,   96,   96,   97,   97,
    0,   91,   91,    0,    0,  111,    0,    0,    0,    0,
    0,    0,   91,   91,   91,   91,   91,   91,   91,    0,
   91,   91,   91,    0,   98,   98,    0,    0,    0,   97,
    0,    0,    0,    0,    0,    0,  111,   93,   93,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   93,   93,
   93,   93,   93,   93,   93,   98,   93,   93,   93,    0,
   92,   92,   99,    0,    0,   99,    0,    0,   99,    0,
    0,   92,   92,   92,   92,   92,   92,   92,    0,   92,
   92,   92,    0,   99,    0,    0,    0,    0,   94,   94,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   94,
   94,   94,   94,   94,   94,   94,    0,   94,   94,   94,
    0,   95,   95,    0,    0,    0,    0,   99,   99,    0,
    0,    0,   95,   95,   95,   95,   95,   95,   95,    0,
   95,   95,   95,    0,    0,    0,    0,    0,    0,    0,
  100,    0,    0,  100,    0,    0,  100,    0,   99,  112,
    0,    0,  112,    0,    0,    0,    0,    0,    0,    0,
    0,  100,    0,    0,    0,    0,  101,  112,    0,  101,
    0,    0,  101,   97,   97,    0,    0,    0,    0,    0,
  111,  111,    0,    0,   97,   97,   97,  101,    0,   97,
   97,  111,   97,   97,   97,  100,  100,    0,    0,   98,
   98,  112,  102,    0,    0,  102,    0,    0,  102,    0,
   98,   98,   98,    0,    0,   98,   98,    0,   98,   98,
   98,  101,  101,  102,  103,    0,  100,  103,  104,    0,
  103,  104,  112,    0,  104,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  105,  103,    0,  105,    0,  104,
  105,    0,  101,  106,    0,    0,  106,  102,  102,  106,
  107,    0,    0,  107,    0,  105,  107,    0,    0,    0,
    0,    0,  108,    0,  106,  108,    0,    0,    0,  103,
  103,  107,    0,  104,  104,    0,   13,    0,  102,    0,
  108,    0,   99,   99,    0,    0,    0,    0,    0,  105,
  105,    0,    0,   99,   99,   99,    0,    0,  106,  106,
  103,   99,   99,   99,  104,  107,  107,    0,    0,    0,
  109,    0,    0,  109,  108,  108,    0,    0,    0,    0,
  105,    0,    0,  110,    0,    0,  110,    0,  109,  106,
  113,    0,    0,  113,    0,    0,  107,    0,    0,    0,
    0,  110,    0,    0,    0,  108,    0,    0,  113,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  100,  100,  109,  109,    0,    0,  112,  112,    0,    0,
    0,  100,  100,  100,   29,  110,  110,  112,    0,  100,
  100,  100,  113,    0,    0,    0,  101,  101,    0,   29,
    0,    0,    0,  109,    0,    0,    0,  101,  101,  101,
    0,    0,    0,    0,    0,  101,  110,  101,    0,    0,
    1,    2,    0,  113,    4,    5,    6,    0,    0,    7,
    8,    0,  102,  102,    9,   10,   11,    0,    0,    0,
   12,    0,    0,  102,  102,  102,    0,    0,    0,   13,
    0,  102,    0,  102,  103,  103,    0,   29,  104,  104,
    0,    0,    0,    0,    0,  103,  103,  103,    0,  104,
  104,  104,    0,  103,  105,  105,    0,  104,    0,    0,
    0,    0,   30,  106,  106,  105,  105,  105,    0,   50,
  107,  107,    0,    0,  106,  106,  106,   30,    0,  108,
  108,  107,  107,  107,   85,    0,    0,    0,    0,    0,
  108,  108,    0,   29,    0,   50,    0,    0,    0,    0,
    0,    0,   29,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  119,
    0,    0,  122,  123,    0,    0,    0,  109,  109,   29,
   50,   50,    0,   50,   50,   30,    0,    0,  109,  109,
  110,  110,    0,   50,    0,    0,  142,  113,  113,    0,
    0,  110,  110,    0,    0,    0,    0,   50,  113,    0,
    0,    0,   29,    1,    2,    0,    3,    4,    5,    6,
    0,    0,    7,    8,    0,   29,   29,    9,   10,   11,
   29,    0,    0,   12,    0,    0,  175,    0,    0,    0,
    0,   30,    0,    0,    0,    0,    0,   50,    0,    0,
   30,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  188,    0,    0,
  190,    0,    0,    0,    0,    0,    0,   30,   50,    0,
    0,   50,    0,    0,    0,    0,    0,    0,    0,    0,
  200,    0,    0,    0,    0,    0,    0,  205,  206,    0,
    0,   50,  209,    0,    0,    0,    0,    0,   50,   50,
   30,    0,    0,   50,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   30,   30,    0,    0,    0,   30,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   60,   38,   62,   41,   94,   40,   44,  124,   43,
   41,   45,   40,   44,    3,  284,  285,   44,   40,   44,
   41,   41,   59,   44,   44,  138,  123,   41,   59,   69,
   44,   44,   59,   61,   59,   40,   64,   33,   59,   59,
   43,   81,   45,   33,   40,   59,   59,   43,  277,   45,
   40,  100,  101,   43,   43,   45,   93,   59,  106,  107,
   37,   38,   93,   91,   41,   42,   43,   44,   45,   46,
   47,  123,   93,   93,  113,  114,   40,   40,   61,   93,
  115,  116,   59,   60,   73,   62,   37,   38,  111,  112,
   41,   42,   43,   44,   45,  288,   47,   61,  211,  212,
   64,   64,   37,  216,   59,  289,  287,   42,   59,   60,
   61,   62,   47,  268,   37,   38,   93,   94,   41,   42,
   43,   44,   45,  123,   47,  125,  269,   91,   91,  211,
  212,  278,   91,  277,  216,   41,   59,   60,  278,   62,
  129,   46,   93,   94,   41,   41,   91,  124,  102,  103,
  104,  105,   37,   38,   93,   41,   41,   42,   43,   44,
   45,   44,   47,  279,  125,  262,   59,   40,  277,  266,
   93,   94,   41,  124,   59,   60,  273,   62,   38,   91,
  277,   41,   44,   43,   44,   45,   93,  176,   40,   40,
  259,  280,   93,   91,  277,   41,   44,   41,   41,   59,
   60,  124,   62,   93,   93,  259,   59,    0,   93,   94,
   41,  125,  257,  258,   38,  204,  261,   41,  263,   43,
   44,   45,  267,  282,  283,   41,  271,  272,  123,   59,
   41,  268,  269,   93,   94,   59,   60,  125,   62,  124,
  274,  275,  276,  277,  278,  281,   41,  268,  269,  269,
   15,  127,  286,  134,  204,  269,   32,  257,  258,  259,
  260,  261,  262,  263,  124,  129,  266,  267,  108,   93,
   94,  271,  272,  273,  109,  117,  110,  277,  274,  275,
  276,  277,  278,   -1,  274,  275,  276,  277,  278,  118,
  286,  268,  269,   -1,   -1,   -1,  286,   -1,   -1,   -1,
  124,   -1,  279,  280,  281,  282,  283,  284,  285,   -1,
  287,  288,  289,   -1,   -1,   -1,   -1,  268,  269,   38,
   -1,   -1,   41,   -1,   43,   44,   45,   -1,  279,  280,
  281,  282,  283,  284,  285,   -1,  287,  288,  289,   -1,
   59,   60,   -1,   62,   -1,  268,  269,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,  281,  282,
  283,  284,  285,   -1,  287,  288,  289,  262,   -1,   -1,
   -1,  266,   -1,   -1,   93,   94,    0,   -1,  273,   -1,
   -1,   -1,  277,  268,  269,   -1,   38,   -1,   -1,   41,
   -1,   15,   44,   -1,  279,  280,  281,  282,  283,  284,
  285,   -1,  287,  288,  289,  124,   -1,   59,  268,  269,
   -1,   38,   -1,   -1,   41,   -1,   -1,   44,   -1,  279,
  280,  281,  282,  283,  284,  285,   -1,  287,  288,  289,
   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   93,   94,   -1,  268,  269,   -1,   38,   -1,   73,
   41,   -1,   -1,   44,   -1,  279,  280,  281,  282,  283,
  284,  285,   -1,  287,  288,  289,   93,   94,   59,   60,
   38,   62,  124,   41,   -1,   73,   44,   39,   40,   41,
   42,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   60,   -1,   62,   -1,   -1,  124,   38,   -1,
   -1,   41,   93,   94,   44,  129,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  138,   -1,   -1,   -1,   -1,   59,
   60,   38,   62,   -1,   41,   93,   94,   44,   -1,   -1,
   -1,  129,   -1,  124,   -1,   97,   98,   99,   -1,   -1,
  138,  165,   59,   60,   -1,   62,   -1,   -1,   -1,  268,
  269,   -1,   -1,   93,   94,   -1,  124,   -1,   -1,   -1,
  279,  280,  281,  282,  283,  284,  285,  165,  287,  288,
  289,   -1,   -1,   -1,  198,   -1,   93,   94,   -1,  141,
   -1,   -1,   -1,   38,  124,   -1,   41,  211,  212,   44,
   -1,   -1,  216,   41,   -1,   -1,   44,   -1,   -1,   -1,
  198,   -1,   -1,   -1,   59,   -1,   -1,  124,   -1,   38,
   -1,   59,   41,  211,  212,   44,  268,  269,  216,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,  281,
   59,   -1,  284,  285,   -1,  287,  288,  289,   93,   94,
   -1,  268,  269,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,  279,  280,  281,  282,  283,  284,  285,   -1,
  287,  288,  289,   -1,   93,   94,   -1,   -1,   -1,  124,
   -1,   -1,   -1,   -1,   -1,   -1,  124,  268,  269,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,
  281,  282,  283,  284,  285,  124,  287,  288,  289,   -1,
  268,  269,   38,   -1,   -1,   41,   -1,   -1,   44,   -1,
   -1,  279,  280,  281,  282,  283,  284,  285,   -1,  287,
  288,  289,   -1,   59,   -1,   -1,   -1,   -1,  268,  269,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  279,
  280,  281,  282,  283,  284,  285,   -1,  287,  288,  289,
   -1,  268,  269,   -1,   -1,   -1,   -1,   93,   94,   -1,
   -1,   -1,  279,  280,  281,  282,  283,  284,  285,   -1,
  287,  288,  289,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   38,   -1,   -1,   41,   -1,   -1,   44,   -1,  124,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   -1,   -1,   -1,   -1,   38,   59,   -1,   41,
   -1,   -1,   44,  268,  269,   -1,   -1,   -1,   -1,   -1,
  268,  269,   -1,   -1,  279,  280,  281,   59,   -1,  284,
  285,  279,  287,  288,  289,   93,   94,   -1,   -1,  268,
  269,   93,   38,   -1,   -1,   41,   -1,   -1,   44,   -1,
  279,  280,  281,   -1,   -1,  284,  285,   -1,  287,  288,
  289,   93,   94,   59,   38,   -1,  124,   41,   38,   -1,
   44,   41,  124,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   38,   59,   -1,   41,   -1,   59,
   44,   -1,  124,   38,   -1,   -1,   41,   93,   94,   44,
   38,   -1,   -1,   41,   -1,   59,   44,   -1,   -1,   -1,
   -1,   -1,   41,   -1,   59,   44,   -1,   -1,   -1,   93,
   94,   59,   -1,   93,   94,   -1,  123,   -1,  124,   -1,
   59,   -1,  268,  269,   -1,   -1,   -1,   -1,   -1,   93,
   94,   -1,   -1,  279,  280,  281,   -1,   -1,   93,   94,
  124,  287,  288,  289,  124,   93,   94,   -1,   -1,   -1,
   41,   -1,   -1,   44,   93,   94,   -1,   -1,   -1,   -1,
  124,   -1,   -1,   41,   -1,   -1,   44,   -1,   59,  124,
   41,   -1,   -1,   44,   -1,   -1,  124,   -1,   -1,   -1,
   -1,   59,   -1,   -1,   -1,  124,   -1,   -1,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  268,  269,   93,   94,   -1,   -1,  268,  269,   -1,   -1,
   -1,  279,  280,  281,    0,   93,   94,  279,   -1,  287,
  288,  289,   93,   -1,   -1,   -1,  268,  269,   -1,   15,
   -1,   -1,   -1,  124,   -1,   -1,   -1,  279,  280,  281,
   -1,   -1,   -1,   -1,   -1,  287,  124,  289,   -1,   -1,
  257,  258,   -1,  124,  261,  262,  263,   -1,   -1,  266,
  267,   -1,  268,  269,  271,  272,  273,   -1,   -1,   -1,
  277,   -1,   -1,  279,  280,  281,   -1,   -1,   -1,  123,
   -1,  287,   -1,  289,  268,  269,   -1,   73,  268,  269,
   -1,   -1,   -1,   -1,   -1,  279,  280,  281,   -1,  279,
  280,  281,   -1,  287,  268,  269,   -1,  287,   -1,   -1,
   -1,   -1,    0,  268,  269,  279,  280,  281,   -1,    7,
  268,  269,   -1,   -1,  279,  280,  281,   15,   -1,  268,
  269,  279,  280,  281,   33,   -1,   -1,   -1,   -1,   -1,
  279,  280,   -1,  129,   -1,   33,   -1,   -1,   -1,   -1,
   -1,   -1,  138,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   68,
   -1,   -1,   71,   72,   -1,   -1,   -1,  268,  269,  165,
   68,   69,   -1,   71,   72,   73,   -1,   -1,  279,  280,
  268,  269,   -1,   81,   -1,   -1,   95,  268,  269,   -1,
   -1,  279,  280,   -1,   -1,   -1,   -1,   95,  279,   -1,
   -1,   -1,  198,  257,  258,   -1,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,  211,  212,  271,  272,  273,
  216,   -1,   -1,  277,   -1,   -1,  135,   -1,   -1,   -1,
   -1,  129,   -1,   -1,   -1,   -1,   -1,  135,   -1,   -1,
  138,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  166,   -1,   -1,
  169,   -1,   -1,   -1,   -1,   -1,   -1,  165,  166,   -1,
   -1,  169,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  189,   -1,   -1,   -1,   -1,   -1,   -1,  196,  197,   -1,
   -1,  189,  201,   -1,   -1,   -1,   -1,   -1,  196,  197,
  198,   -1,   -1,  201,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  211,  212,   -1,   -1,   -1,  216,
};
}
final static short YYFINAL=14;
final static short YYMAXTOKEN=289;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'","'&'",null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,"'@'",null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'","'^'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'","'|'","'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"BEXP","BIT","ELSE","FUNC","INT",
"IF","KMAP","NULL","NL","RETURN","STRING","SCAND","SCOR","TRUE","TRUTHTABLE",
"VOID","WHILE","INTEGERLITERAL","STRINGLITERAL","BEXPRESSIONLITERAL",
"IDENTIFIER","BITLITERAL","OR","XOR","AND","LEQ","GEQ","DEQ","NEQ","NOT","NOR",
"NAND","XNOR",
};
final static String yyrule[] = {
"$accept : goal",
"goal : compilation_unit",
"compilation_unit : compilation_unit declaration",
"compilation_unit : declaration",
"declaration : function_declaration",
"declaration : variable_declaration",
"declaration : statement",
"variable_declaration : type variable_declarator ';'",
"variable_declarator : variable_declarator ',' variable_declarator1",
"variable_declarator : variable_declarator1",
"variable_declarator1 : ad",
"variable_declarator1 : vd",
"vd : IDENTIFIER",
"ad : vd '[' expression ']'",
"ad : vd '[' expression ']' '[' expression ']'",
"function_declaration : FUNC function_header function_body",
"$$1 :",
"function_header : type $$1 IDENTIFIER '(' formal_parameter_list ')'",
"formal_parameter_list : formal_parameters",
"formal_parameter_list :",
"formal_parameters : formal_parameters ',' formal_parameter",
"formal_parameters : formal_parameter",
"formal_parameter : type IDENTIFIER",
"function_body : block",
"$$2 :",
"block : '{' $$2 block_statements_or_empty '}'",
"block_statements_or_empty : block_statements",
"block_statements_or_empty :",
"block_statements : block_statements block_statement",
"block_statements : block_statement",
"block_statement : local_variable_declaration_statement",
"block_statement : statement",
"local_variable_declaration_statement : local_variable_declaration ';'",
"local_variable_declaration : type variable_declarator",
"statement : statement_without_trailing_substatement",
"statement : if_then_statement",
"statement : if_then_else_statement",
"statement : while_statement",
"statement_matched_if : statement_without_trailing_substatement",
"statement_matched_if : if_then_else_statement_matched_if",
"statement_matched_if : while_statement_matched_if",
"statement_without_trailing_substatement : block",
"statement_without_trailing_substatement : statement_expression ';'",
"statement_without_trailing_substatement : return_statement",
"if_then_statement : IF '(' expression ')' statement",
"if_then_else_statement : IF '(' expression ')' statement_matched_if ELSE statement",
"if_then_else_statement_matched_if : IF '(' expression ')' statement_matched_if ELSE statement_matched_if",
"while_statement : WHILE '(' expression ')' statement",
"while_statement_matched_if : WHILE '(' expression ')' statement_matched_if",
"expression_or_empty :",
"expression_or_empty : expression",
"statement_expression : assignment",
"statement_expression : method_invocation",
"return_statement : RETURN expression_or_empty ';'",
"expression : assignment_expression",
"assignment_expression : conditional_expression",
"assignment_expression : assignment",
"assignment : IDENTIFIER '=' assignment_expression",
"assignment : array_access '=' assignment_expression",
"postfix_expression : IDENTIFIER",
"postfix_expression : literal",
"$$3 :",
"postfix_expression : '(' $$3 expression ')'",
"postfix_expression : method_invocation",
"postfix_expression : array_access",
"postfix_expression : IDENTIFIER '@' BITLITERAL",
"array_access : IDENTIFIER '[' expression ']'",
"array_access : IDENTIFIER '@' BITLITERAL '[' expression ']'",
"array_access : IDENTIFIER '[' expression ']' '[' expression ']'",
"method_invocation : IDENTIFIER '(' argument_list_opt ')'",
"argument_list_opt :",
"argument_list_opt : argument_list",
"argument_list : expression",
"argument_list : argument_list ',' expression",
"unary_expression : '+' unary_expression",
"unary_expression : '-' unary_expression",
"unary_expression : unary_expression_not_plus_minus",
"unary_expression_not_plus_minus : postfix_expression",
"unary_expression_not_plus_minus : '!' unary_expression",
"unary_expression_not_plus_minus : NOT unary_expression",
"unary_expression_not_plus_minus : cast_expression",
"$$4 :",
"unary_expression_not_plus_minus : IDENTIFIER $$4 '.' IDENTIFIER",
"cast_expression : '(' type ')' unary_expression",
"multiplicative_expression : unary_expression",
"multiplicative_expression : multiplicative_expression '*' unary_expression",
"multiplicative_expression : multiplicative_expression '/' unary_expression",
"multiplicative_expression : multiplicative_expression '%' unary_expression",
"additive_expression : multiplicative_expression",
"additive_expression : additive_expression '+' multiplicative_expression",
"additive_expression : additive_expression '-' multiplicative_expression",
"relational_expression : additive_expression",
"relational_expression : relational_expression '<' additive_expression",
"relational_expression : relational_expression '>' additive_expression",
"relational_expression : relational_expression LEQ additive_expression",
"relational_expression : relational_expression GEQ additive_expression",
"equality_expression : relational_expression",
"equality_expression : equality_expression DEQ relational_expression",
"equality_expression : equality_expression NEQ relational_expression",
"nand_gate_expression : equality_expression",
"nand_gate_expression : nand_gate_expression NAND equality_expression",
"xnor_gate_expression : nand_gate_expression",
"xnor_gate_expression : xnor_gate_expression XNOR nand_gate_expression",
"nor_gate_expression : xnor_gate_expression",
"nor_gate_expression : nor_gate_expression NOR xnor_gate_expression",
"and_expression : nor_gate_expression",
"and_expression : and_expression '&' nor_gate_expression",
"and_expression : and_expression AND nor_gate_expression",
"exclusive_or_expression : and_expression",
"exclusive_or_expression : exclusive_or_expression '^' and_expression",
"exclusive_or_expression : exclusive_or_expression XOR and_expression",
"inclusive_or_expression : exclusive_or_expression",
"inclusive_or_expression : inclusive_or_expression '|' exclusive_or_expression",
"inclusive_or_expression : inclusive_or_expression OR exclusive_or_expression",
"conditional_and_expression : inclusive_or_expression",
"conditional_and_expression : conditional_and_expression SCAND inclusive_or_expression",
"conditional_or_expression : conditional_and_expression",
"conditional_or_expression : conditional_or_expression SCOR conditional_and_expression",
"conditional_expression : conditional_or_expression",
"literal : STRINGLITERAL",
"literal : INTEGERLITERAL",
"literal : BEXPRESSIONLITERAL",
"literal : BITLITERAL",
"type : INT",
"type : BIT",
"type : BEXP",
"type : STRING",
"type : TRUTHTABLE",
"type : KMAP",
"type : VOID",
};

//#line 357 "bzcut.y"

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
//#line 777 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 45 "bzcut.y"
{Nodelist nd=new Nodelist(n,lexer.getLine()+1);ArrayList<ErrorMsg> errors=new ArrayList<ErrorMsg>();SymbolTable symboltable=new SymbolTable();nd.accept(new SymbolTableVisitor(symboltable,errors));nd.accept(new TypeCheckerVisitor(symboltable,fdecl,errors));
if (errors.size()!=0)
{
		for (int i=0;i<errors.size();i++)
		{
			System.out.println(errors.get(i).getMsg());
		}

System.out.println ("Program Aborting....");
System.exit(-1);
}
nd.accept(new PrettyPrintVisitor(symboltable,errors));}
break;
case 4:
//#line 59 "bzcut.y"
{n.add(fd);}
break;
case 5:
//#line 60 "bzcut.y"
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
}
break;
case 6:
//#line 80 "bzcut.y"
{n.add(st);}
break;
case 12:
//#line 86 "bzcut.y"
{id=new Identifier(val_peek(0).sval,lexer.getLine()+1);idlist.add(id);}
break;
case 13:
//#line 87 "bzcut.y"
{arraydeclflag=1;}
break;
case 14:
//#line 87 "bzcut.y"
{arraydeclflag2=1;}
break;
case 15:
//#line 89 "bzcut.y"
{
if (ret.rexpr!=null)
{fd=new FunctionDeclaration(rt,fid,fagl,bs,ret.rexpr,lexer.getLine()+1);fdecl.fdl.add(fd);}
else 
{
fd=new FunctionDeclaration(rt,fid,fagl,bs,null,lexer.getLine()+1);fdecl.fdl.add(fd);
}
fag.clear();
}
break;
case 16:
//#line 98 "bzcut.y"
{rt=t;}
break;
case 17:
//#line 98 "bzcut.y"
{fid=new Identifier(val_peek(3).sval,lexer.getLine()+1);fagl=new Fargumentlist(fag,lexer.getLine()+1);}
break;
case 20:
//#line 100 "bzcut.y"
{fag.add(fg);}
break;
case 21:
//#line 100 "bzcut.y"
{fag.add(fg);}
break;
case 22:
//#line 101 "bzcut.y"
{id=new Identifier(val_peek(0).sval,lexer.getLine()+1);fg=new Fargument(t,id,lexer.getLine()+1);}
break;
case 24:
//#line 104 "bzcut.y"
{bst=new ArrayList<Node>();blockstack.push(bst);exprlist1=new ArrayList<Expr>();exprstack.push(exprlist1);}
break;
case 25:
//#line 104 "bzcut.y"
{blockstack.pop();}
break;
case 26:
//#line 105 "bzcut.y"
{bs=new BlockStatement(blockstack.peek(),lexer.getLine()+1);st=bs;}
break;
case 27:
//#line 105 "bzcut.y"
{bs=new BlockStatement(null,lexer.getLine()+1);st=bs;}
break;
case 30:
//#line 107 "bzcut.y"
{bst=blockstack.pop();bst.add(decl);blockstack.push(bst);}
break;
case 31:
//#line 107 "bzcut.y"
{bst=blockstack.pop();bst.add(st);blockstack.push(bst);}
break;
case 33:
//#line 109 "bzcut.y"
{
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
}
break;
case 35:
//#line 130 "bzcut.y"
{st=ifst;}
break;
case 36:
//#line 131 "bzcut.y"
{st=ifelsest;}
break;
case 37:
//#line 132 "bzcut.y"
{st=whileloop;}
break;
case 38:
//#line 135 "bzcut.y"
{bs1=bs;}
break;
case 39:
//#line 136 "bzcut.y"
{bs1=bs;}
break;
case 43:
//#line 142 "bzcut.y"
{st=ret;}
break;
case 44:
//#line 146 "bzcut.y"
{ifst=new IfStatement (exprlist.get(exprlist.size()-exprstack.peek().size()-1),bs,lexer.getLine()+1);
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
break;
case 45:
//#line 170 "bzcut.y"
{

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
break;
case 46:
//#line 202 "bzcut.y"
{}
break;
case 47:
//#line 205 "bzcut.y"
{whileloop=new WhileLoop (exprlist.get(exprlist.size()-exprstack.peek().size()-1),bs,lexer.getLine()+1);
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
break;
case 48:
//#line 227 "bzcut.y"
{whileloop=new WhileLoop (exprlist.get(0),bs,lexer.getLine()+1);exprlist.clear();}
break;
case 49:
//#line 230 "bzcut.y"
{e=null;}
break;
case 51:
//#line 235 "bzcut.y"
{st=ass;}
break;
case 52:
//#line 236 "bzcut.y"
{st=mi;}
break;
case 53:
//#line 240 "bzcut.y"
{ret=new Return(e,lexer.getLine()+1);}
break;
case 54:
//#line 242 "bzcut.y"
{exprlist.add(e);if (exprlist1!=null) {exprlist1.add(e);}}
break;
case 57:
//#line 249 "bzcut.y"
{id=new Identifier(val_peek(2).sval,lexer.getLine()+1);ass=new Assign(id,e,lexer.getLine()+1);}
break;
case 58:
//#line 249 "bzcut.y"
{ass=new Assign(alook,e,lexer.getLine()+1);}
break;
case 59:
//#line 251 "bzcut.y"
{ide = new Identifier(val_peek(0).sval,lexer.getLine()+1);e=ide;estack.push(e);}
break;
case 60:
//#line 251 "bzcut.y"
{e=l;estack.push(e);}
break;
case 61:
//#line 251 "bzcut.y"
{parenflag=1;}
break;
case 62:
//#line 251 "bzcut.y"
{par=new Parenthesis(e,lexer.getLine()+1);e=par;parenflag=0;estack.pop();estack.push(e);}
break;
case 63:
//#line 251 "bzcut.y"
{e=miexpr;}
break;
case 64:
//#line 251 "bzcut.y"
{e=alook;estack.pop();estack.push(e);}
break;
case 65:
//#line 251 "bzcut.y"
{Identifier idbool=new Identifier(val_peek(2).sval,lexer.getLine()+1);BitLiteral bl1=new BitLiteral(val_peek(0).sval,lexer.getLine()+1);bexp=new BinExpr (idbool,bl1,Operator.AT,lexer.getLine()+1);e=bexp;}
break;
case 66:
//#line 254 "bzcut.y"
{ida=new Identifier(val_peek(3).sval,lexer.getLine()+1);alook=new ArrayLookup(ida,e,null,lexer.getLine()+1);}
break;
case 68:
//#line 256 "bzcut.y"
{ida=new Identifier(val_peek(6).sval,lexer.getLine()+1);alook=new ArrayLookup(ida,exprlist.get(exprlist.indexOf(e)-1),e,lexer.getLine()+1);exprlist.remove(exprlist.indexOf(e)-1);exprlist.remove(exprlist.indexOf(e));if (exprlist1!=null){exprlist1.remove(exprlist1.indexOf(e)-1);exprlist1.remove(exprlist1.indexOf(e));}}
break;
case 69:
//#line 260 "bzcut.y"
{id1=new Identifier(val_peek(3).sval,lexer.getLine()+1);mi=new MethodInvocation(id1,aglist,lexer.getLine()+1);miexpr=new MethodInvocationExpr(id1,aglist,lexer.getLine()+1);aglist.clear();}
break;
case 72:
//#line 266 "bzcut.y"
{aglist.add(e);}
break;
case 73:
//#line 267 "bzcut.y"
{aglist.add(e);}
break;
case 74:
//#line 271 "bzcut.y"
{estack.pop();unexp= new UnaryExpr(e,Operator.PLUS,lexer.getLine()+1);e=unexp;e14=e;estack.push(e);}
break;
case 75:
//#line 272 "bzcut.y"
{estack.pop();unexp= new UnaryExpr(e,Operator.UMINUS,lexer.getLine()+1);e=unexp;e14=e;estack.push(e);}
break;
case 76:
//#line 273 "bzcut.y"
{}
break;
case 78:
//#line 277 "bzcut.y"
{estack.pop();unexp=new UnaryExpr(e,Operator.LNOT,lexer.getLine()+1);e=unexp;e14=e;estack.push(e);}
break;
case 79:
//#line 278 "bzcut.y"
{estack.pop();unexp=new UnaryExpr(e,Operator.NOT,lexer.getLine()+1);e=unexp;e14=e;estack.push(e);}
break;
case 81:
//#line 280 "bzcut.y"
{iddot1=new Identifier(val_peek(0).sval,lexer.getLine()+1);}
break;
case 82:
//#line 280 "bzcut.y"
{Identifier iddot2=new Identifier(val_peek(0).sval,lexer.getLine()+1);bexp=new BinExpr (iddot1,iddot2,Operator.DOT,lexer.getLine()+1);e=bexp;}
break;
case 83:
//#line 283 "bzcut.y"
{tcast= new TypeCasting(t,e,lexer.getLine()+1);e=tcast;}
break;
case 84:
//#line 287 "bzcut.y"
{}
break;
case 85:
//#line 288 "bzcut.y"
{e14=estack.pop();e13=estack.pop();bexp=new BinExpr (e13,e14,Operator.MULT,lexer.getLine()+1);e=bexp;e13=bexp;estack.push(e);}
break;
case 86:
//#line 289 "bzcut.y"
{e14=estack.pop();e13=estack.pop();bexp=new BinExpr (e13,e14,Operator.DIV,lexer.getLine()+1);e=bexp;e13=bexp;estack.push(e);}
break;
case 87:
//#line 290 "bzcut.y"
{e14=estack.pop();e13=estack.pop();bexp=new BinExpr (e13,e14,Operator.MOD,lexer.getLine()+1);e=bexp;e13=bexp;estack.push(e);}
break;
case 88:
//#line 293 "bzcut.y"
{}
break;
case 89:
//#line 294 "bzcut.y"
{e13=estack.pop();e12=estack.pop();bexp=new BinExpr (e12,e13,Operator.PLUS,lexer.getLine()+1);e=bexp;e12=bexp;estack.push(e);}
break;
case 90:
//#line 295 "bzcut.y"
{e13=estack.pop();e12=estack.pop();bexp=new BinExpr (e12,e13,Operator.MINUS,lexer.getLine()+1);e=bexp;e12=bexp;estack.push(e);}
break;
case 91:
//#line 298 "bzcut.y"
{}
break;
case 92:
//#line 299 "bzcut.y"
{e12=estack.pop();e11=estack.pop();bexp=new BinExpr (e11,e12,Operator.LT,lexer.getLine()+1);e=bexp;e11=bexp;estack.push(e);}
break;
case 93:
//#line 300 "bzcut.y"
{e12=estack.pop();e11=estack.pop();bexp=new BinExpr (e11,e12,Operator.GT,lexer.getLine()+1);e=bexp;e11=bexp;estack.push(e);}
break;
case 94:
//#line 301 "bzcut.y"
{e12=estack.pop();e11=estack.pop();bexp=new BinExpr (e11,e12,Operator.LEQ,lexer.getLine()+1);e=bexp;e11=bexp;estack.push(e);}
break;
case 95:
//#line 302 "bzcut.y"
{e12=estack.pop();e11=estack.pop();bexp=new BinExpr (e11,e12,Operator.GEQ,lexer.getLine()+1);e=bexp;e11=bexp;estack.push(e);}
break;
case 96:
//#line 305 "bzcut.y"
{}
break;
case 97:
//#line 306 "bzcut.y"
{e11=estack.pop();e10=estack.pop();bexp=new BinExpr (e10,e11,Operator.DEQ,lexer.getLine()+1);e=bexp;e10=bexp;estack.push(e);}
break;
case 98:
//#line 307 "bzcut.y"
{e11=estack.pop();e10=estack.pop();bexp=new BinExpr (e10,e11,Operator.NEQ,lexer.getLine()+1);e=bexp;e10=bexp;estack.push(e);}
break;
case 99:
//#line 311 "bzcut.y"
{}
break;
case 100:
//#line 312 "bzcut.y"
{e10=estack.pop();e9=estack.pop();bexp=new BinExpr (e9,e10,Operator.NAND,lexer.getLine()+1);e=bexp;e9=bexp;estack.push(e);}
break;
case 101:
//#line 316 "bzcut.y"
{}
break;
case 102:
//#line 317 "bzcut.y"
{e9=estack.pop();e8=estack.pop();bexp=new BinExpr (e8,e9,Operator.XNOR,lexer.getLine()+1);e=bexp;e8=bexp;estack.push(e);}
break;
case 103:
//#line 320 "bzcut.y"
{}
break;
case 104:
//#line 321 "bzcut.y"
{e8=estack.pop();e7=estack.pop();bexp=new BinExpr (e7,e8,Operator.NOR,lexer.getLine()+1);e=bexp;e7=bexp;estack.push(e);}
break;
case 105:
//#line 324 "bzcut.y"
{}
break;
case 106:
//#line 325 "bzcut.y"
{e7=estack.pop();e6=estack.pop();bexp=new BinExpr (e6,e7,Operator.LAND,lexer.getLine()+1);e=bexp;e6=bexp;estack.push(e);}
break;
case 107:
//#line 326 "bzcut.y"
{e7=estack.pop();e6=estack.pop();bexp=new BinExpr (e6,e7,Operator.AND,lexer.getLine()+1);e=bexp;e6=bexp;estack.push(e);}
break;
case 109:
//#line 331 "bzcut.y"
{e6=estack.pop();e5=estack.pop();bexp=new BinExpr (e5,e6,Operator.LXOR,lexer.getLine()+1);e=bexp;e5=bexp;estack.push(e);}
break;
case 110:
//#line 332 "bzcut.y"
{e6=estack.pop();e5=estack.pop();bexp=new BinExpr (e5,e6,Operator.XOR,lexer.getLine()+1);e=bexp;e5=bexp;estack.push(e);}
break;
case 112:
//#line 337 "bzcut.y"
{e5=estack.pop();e4=estack.pop();bexp=new BinExpr (e4,e5,Operator.LOR,lexer.getLine()+1);e=bexp;e4=bexp;estack.push(e);}
break;
case 113:
//#line 338 "bzcut.y"
{e5=estack.pop();e4=estack.pop();bexp=new BinExpr (e4,e5,Operator.OR,lexer.getLine()+1);e=bexp;e4=bexp;estack.push(e);}
break;
case 115:
//#line 342 "bzcut.y"
{e4=estack.pop();e3=estack.pop();bexp=new BinExpr (e3,e4,Operator.SCAND,lexer.getLine()+1);e=bexp;e3=bexp;estack.push(e);}
break;
case 117:
//#line 346 "bzcut.y"
{e3=estack.pop();e2=estack.pop();bexp=new BinExpr (e2,e3,Operator.SCOR,lexer.getLine()+1);e=bexp;e2=bexp;estack.push(e);}
break;
case 119:
//#line 352 "bzcut.y"
{sl=new StringLiteral(val_peek(0).sval,lexer.getLine()+1);l=sl; }
break;
case 120:
//#line 352 "bzcut.y"
{il=new IntegerLiteral(val_peek(0).ival,lexer.getLine()+1);l=il; }
break;
case 121:
//#line 352 "bzcut.y"
{bexpl=new BexpressionLiteral(val_peek(0).sval,lexer.getLine()+1);l=bexpl;}
break;
case 122:
//#line 352 "bzcut.y"
{ bl=new BitLiteral(val_peek(0).sval,lexer.getLine()+1);l=bl;}
break;
case 123:
//#line 353 "bzcut.y"
{t=Type.INT;}
break;
case 124:
//#line 353 "bzcut.y"
{t=Type.BIT;}
break;
case 125:
//#line 353 "bzcut.y"
{t=Type.BEXP;}
break;
case 126:
//#line 353 "bzcut.y"
{t=Type.STRING;}
break;
case 127:
//#line 353 "bzcut.y"
{t=Type.TRUTHTABLE;}
break;
case 128:
//#line 353 "bzcut.y"
{t=Type.KMAP;}
break;
case 129:
//#line 353 "bzcut.y"
{t=Type.VOID;}
break;
//#line 1446 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
