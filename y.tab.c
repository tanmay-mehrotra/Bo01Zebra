#ifndef lint
static const char yysccsid[] = "@(#)yaccpar	1.9 (Berkeley) 02/21/93";
#endif

#define YYBYACC 1
#define YYMAJOR 1
#define YYMINOR 9
#define YYPATCH 20130304

#define YYEMPTY        (-1)
#define yyclearin      (yychar = YYEMPTY)
#define yyerrok        (yyerrflag = 0)
#define YYRECOVERING() (yyerrflag != 0)

#define YYPREFIX "yy"

#define YYPURE 0

#line 2 "bzcut.y"

import java.io.*;
import java.util.*;

enum Type {INVALID,INT,STRING};
enum Operator {SCOR,SCAND,LOR,LXOR,LAND,LT,GT,LEQ,GEQ,DEQ,NEQ,PLUS,MINUS,MULT,DIV,MOD,LNOT,UMINUS};

#line 27 "y.tab.c"

/* compatibility with bison */
#ifdef YYPARSE_PARAM
/* compatibility with FreeBSD */
# ifdef YYPARSE_PARAM_TYPE
#  define YYPARSE_DECL() yyparse(YYPARSE_PARAM_TYPE YYPARSE_PARAM)
# else
#  define YYPARSE_DECL() yyparse(void *YYPARSE_PARAM)
# endif
#else
# define YYPARSE_DECL() yyparse(void)
#endif

/* Parameters sent to lex. */
#ifdef YYLEX_PARAM
# define YYLEX_DECL() yylex(void *YYLEX_PARAM)
# define YYLEX yylex(YYLEX_PARAM)
#else
# define YYLEX_DECL() yylex(void)
# define YYLEX yylex()
#endif

/* Parameters sent to yyerror. */
#ifndef YYERROR_DECL
#define YYERROR_DECL() yyerror(const char *s)
#endif
#ifndef YYERROR_CALL
#define YYERROR_CALL(msg) yyerror(msg)
#endif

extern int YYPARSE_DECL();

#define ANYEXCEPTION 257
#define BEXP 258
#define BIT 259
#define BREAK 260
#define CASE 261
#define CATCH 262
#define CLASS 263
#define CONTINUE 264
#define CHARACTER 265
#define DO 266
#define DEFAULT 267
#define ELSE 268
#define EOF 269
#define FINAL 270
#define FALSE 271
#define FOR 272
#define FUNC 273
#define IMPORT 274
#define INT 275
#define IF 276
#define KMAP 277
#define NEW 278
#define NULL 279
#define NL 280
#define PUBLIC 281
#define PACKAGE 282
#define PRIVATE 283
#define PROTECTED 284
#define RETURN 285
#define SWITCH 286
#define STATIC 287
#define STRING 288
#define SCAND 289
#define SCOR 290
#define THIS 291
#define THROW 292
#define THROWS 293
#define TRY 294
#define TRUE 295
#define TRUTHTABLE 296
#define VOID 297
#define WHILE 298
#define INTEGERLITERAL 299
#define CHARACTERLITERAL 300
#define BITLITERAL 301
#define STRINGLITERAL 302
#define BEXPRESSIONLITERAL 303
#define IDENTIFIER 304
#define OR 305
#define XOR 306
#define AND 307
#define LEQ 308
#define GEQ 309
#define DEQ 310
#define NEQ 311
#define NOT 312
#define NOR 313
#define NAND 314
#define XNOR 315
#define YYERRCODE 256
static const short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    4,    7,    7,    8,
    8,   10,    9,    9,    3,   12,   14,   14,   15,   15,
   16,   13,   17,   18,   18,   19,   19,   20,   20,   21,
   22,    5,    5,    5,    5,   27,   27,   27,   23,   23,
   23,   23,   23,   23,   23,   24,   25,   28,   31,   26,
   29,   36,   36,   30,   30,   32,   33,   34,   35,   39,
   39,   40,   11,   41,   41,   37,   37,   44,   44,   44,
   44,   44,   44,   43,   43,   43,   38,   46,   46,   47,
   47,   48,   48,   48,   49,   49,   49,   49,   49,   50,
   51,   51,   51,   51,   52,   52,   52,   53,   53,   53,
   53,   53,   54,   54,   54,   55,   55,   56,   56,   57,
   57,   58,   58,   58,   59,   59,   59,   60,   60,   60,
   61,   61,   62,   62,   42,   45,   45,   45,   45,    6,
    6,    6,    6,    6,    6,    6,
};
static const short yylen[] = {                            2,
    1,    2,    1,    1,    1,    1,    3,    3,    1,    1,
    1,    1,    4,    7,    3,    5,    1,    0,    3,    1,
    2,    1,    3,    1,    0,    2,    1,    1,    1,    2,
    2,    1,    1,    1,    1,    1,    1,    1,    1,    2,
    1,    1,    1,    1,    1,    5,    7,    7,    7,    5,
    5,    0,    1,    1,    1,    2,    2,    3,    3,    1,
    2,    5,    1,    1,    1,    3,    3,    1,    1,    3,
    1,    1,    3,    4,    6,    7,    4,    0,    1,    1,
    3,    2,    2,    1,    1,    2,    2,    1,    3,    4,
    1,    3,    3,    3,    1,    3,    3,    1,    3,    3,
    3,    3,    1,    3,    3,    1,    3,    1,    3,    1,
    3,    1,    3,    3,    1,    3,    3,    1,    3,    3,
    1,    3,    1,    3,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,
};
static const short yydefred[] = {                         0,
  136,  132,  131,    0,    0,    0,    0,  130,    0,  135,
    0,  133,    0,  134,    0,    0,    0,    0,    0,    3,
    4,    5,    6,    0,   39,   32,   33,   34,   35,    0,
   41,   42,   43,   44,   45,   54,   55,    0,   56,   57,
    0,    0,    0,    0,  127,  129,  126,  128,    0,    0,
    0,    0,    0,    0,   53,    0,   65,   71,   63,   64,
    0,    0,   69,   91,   84,   88,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   29,    0,    0,    0,   27,   28,
    0,    2,   12,    0,    9,   10,    0,   40,    0,    0,
    0,   15,   22,    0,    0,    0,   72,   82,   83,   86,
   87,    0,    0,   58,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
    0,   66,    0,    0,    0,   80,    0,    0,    0,   23,
   26,   30,    7,    0,    0,   67,    0,    0,    0,    0,
    0,   70,    0,   89,   92,   94,   93,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   61,    0,    0,
   74,    0,   77,    0,    8,    0,    0,    0,    0,    0,
   20,    0,    0,   46,    0,    0,   37,   38,   90,    0,
   50,    0,    0,   81,    0,    0,   21,   16,    0,    0,
    0,    0,    0,   75,    0,    0,   49,   19,    0,    0,
   47,   62,   76,    0,    0,    0,   14,    0,   51,    0,
   48,
};
static const short yydgoto[] = {                         18,
   19,   20,   21,   22,   23,  198,   94,   95,   96,   97,
  113,   43,  102,  199,  200,  201,   25,   87,   88,   89,
   90,   91,   26,   27,   28,   29,  206,  207,  208,   30,
   31,   32,   33,   34,   35,   56,   57,   58,  139,  140,
   59,   60,  107,   62,   63,  147,  148,   64,   65,   66,
   67,   68,   69,   70,   71,   72,   73,   74,   75,   76,
   77,   78,
};
static const short yysindex[] = {                      1109,
    0,    0,    0,  -44,  -40,  -57,  -75,    0,   19,    0,
  -31,    0,  -99,    0,   32,  -17, 1177,    0, 1109,    0,
    0,    0,    0, -243,    0,    0,    0,    0,    0,   27,
    0,    0,    0,    0,    0,    0,    0,   29,    0,    0,
 -222, -216,  -99,  -31,    0,    0,    0,    0,   30,   22,
   22,   22,   22,   56,    0,   36,    0,    0,    0,    0,
   29,   51,    0,    0,    0,    0,   16,   11,   40, -218,
 -211, -204, -193,  -32,  -90, -119, -166, -165, -129,  -31,
  -31, -149,   38,  -31,    0, -243,   33, 1177,    0,    0,
   76,    0,    0,   -8,    0,    0,   64,    0,  -31,  122,
  124,    0,    0,  129, -121,  -18,    0,    0,    0,    0,
    0,  140,  144,    0,  -19,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,  147, -129,    0,
  150,    0,   97,  100,  104,    0,  157,  160,  161,    0,
    0,    0,    0, -243,  -31,    0,  -31,  -75,  405,   97,
   22,    0,  -31,    0,    0,    0,    0,   16,   16,   11,
   11,   11,   11,   40,   40, -218, -211, -204, -193, -193,
  -32,  -32,  -90,  -90, -119, -166,  -75,    0,  -57,  -31,
    0,  110,    0,  -31,    0,  113,  167,  -94,  170,  168,
    0,  174,  175,    0,    0,  -50,    0,    0,    0,  179,
    0,  133,  -31,    0,  131,  183,    0,    0,  -75,  -31,
  -31,  -57,  -99,    0,  134,  -31,    0,    0,  188,  192,
    0,    0,    0,  141,  405,  405,    0,  -25,    0,  405,
    0,
};
static const short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  185,    0,    0,    0,    0,    0,  126,    0,  252,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   72,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  101,  387,    0,    0,    0,    0,  416,  485,  620,  848,
  905,  938,  952,  769,  472,  -41,  -24,  -16,    0,    0,
    0,    0,    0,  221,    0,    0,    0,  138,    0,    0,
    0,    0,    0,    0,    0,    0,   -2,    0,    0,    0,
    0,    0,    0,    0,    0,   72,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    0,    0,  193,    0,    0,    0,  223,  229,    0,
    0,    0,    0,    0,    0,    0,    0,  249,    0,  130,
    0,    0,    0,    0,    0,    0,    0,  427,  474,  512,
  525,  563,  591,  713,  820,  877,  916,  948,  992, 1012,
 1019, 1024,  888, 1048,  -33,  -11,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  255,
    0,    0,    0,    0,   34,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    5,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
static const short yygindex[] = {                         0,
    0,  282,    0,    0,  799,   68,  217,  152,    0,    0,
 1206,    0,    0,    0,    0, -174,   -6,    0,    0,  216,
    0,    0, -128,    0,    0,    0, -156,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   10, 1152,    0,  169,
  -49,    0, 1266,  197,    0,    0,    0,  -12,    0,    0,
   17,    6,   15,  189,  190,  191,   20,   21,   44,  199,
  206,    0,
};
#define YYTABLESIZE 1506
static const short yytable[] = {                        121,
   59,   52,  121,  132,  134,  130,   79,  122,   54,   36,
  122,   50,  210,   51,   39,   36,  123,  121,   40,  123,
  163,   84,   84,   17,  125,  122,   36,  125,   36,  124,
  205,  142,  124,   32,  123,  154,  103,  108,  109,  110,
  111,   11,  125,   81,  228,  105,   82,  124,   13,  156,
  153,  121,  117,  119,   52,  120,   11,  116,   44,  122,
   93,   54,  118,   13,   50,   17,   51,   24,  123,   84,
   52,   80,   83,   83,   42,  100,  125,   54,  238,  239,
   50,  124,   51,  241,   86,   98,   24,  101,   52,   99,
   81,  125,  126,  105,  114,   54,  115,   36,   50,  122,
   51,  121,  127,  165,  166,  167,  205,  205,   68,   68,
  128,  205,   68,   68,   68,   68,   68,   68,   68,  129,
   83,  112,  136,   59,  137,   59,  170,  171,  172,  173,
   68,   68,  138,   68,  152,  168,  169,   72,   72,  174,
  175,   72,   72,   72,   72,   72,   72,   72,  209,  179,
  180,  143,  181,  182,  155,   86,   32,  150,   32,   72,
   72,  157,   72,  158,   68,   68,   73,   73,   36,  159,
   73,   73,   73,   73,   73,   73,   73,  183,  184,  160,
  161,    1,    2,    3,  162,  135,  187,  190,   73,   73,
  189,   73,  191,   72,   72,   68,  192,  193,   36,    8,
  213,   10,    4,  194,  154,  215,    5,  216,    6,  217,
  218,  219,   12,  220,  221,  133,  232,  222,    9,  223,
   14,  226,   73,   73,   72,  224,  233,   11,  235,  127,
  127,   36,  236,  237,  127,  127,   13,  127,  127,  127,
   15,  227,  240,   52,   36,   36,   16,  121,  121,   36,
   25,    1,  127,   73,  127,  122,  122,   59,   59,   59,
   59,   78,   24,   79,   59,  123,   59,   45,   59,   46,
   47,   48,   49,   59,  131,   59,   59,   59,  124,   45,
   53,   46,   47,   48,  106,   59,  127,   31,   59,   18,
   32,   32,   32,   32,   59,   17,   59,   32,   59,   32,
   92,   36,  149,  151,   59,  195,   32,  188,   32,   32,
   32,  164,    1,    2,    3,  176,  127,  177,   32,  178,
   45,   32,   46,   47,   48,  106,    0,   32,    0,   32,
    8,   32,   10,   53,  185,    0,  144,   32,   46,   47,
   48,   49,  186,   12,    0,    0,    0,  123,  124,   53,
    0,   14,    0,    0,   45,    0,   46,   47,   48,   49,
   68,   68,    0,    0,    0,    0,    0,   53,    0,    0,
    0,    0,    0,    0,    0,    0,   68,   68,   68,   68,
   68,   68,   68,    0,   68,   68,   68,    0,    0,   72,
   72,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   72,   72,   72,   72,   72,
   72,   72,    0,   72,   72,   72,    0,    0,   73,   73,
    0,    0,    0,   85,   85,    0,    0,   85,   85,   85,
   85,   85,    0,   85,   73,   73,   73,   73,   73,   73,
   73,    0,   73,   73,   73,   85,   85,    0,   85,    0,
    0,    0,    0,   95,    0,    0,   95,    0,   95,   95,
   95,    0,    0,    0,   96,    0,    0,   96,    0,   96,
   96,   96,    0,    0,   95,   95,    0,   95,    0,   85,
   85,  127,  127,    0,    0,   96,   96,    0,   96,    0,
    0,    0,    0,    0,    0,    0,    0,  127,  127,  127,
  127,  127,  127,  127,    0,  127,  127,  127,   95,   95,
   85,   97,  118,    0,   97,  118,   97,   97,   97,   96,
   96,    0,   98,    0,    0,   98,    0,   17,   98,    0,
  118,    0,   97,   97,    0,   97,    0,    0,    0,   95,
    0,    0,    0,   98,   98,    0,   98,    0,    0,  100,
   96,    0,  100,    0,    0,  100,    0,    0,    0,    0,
    0,    0,   99,    0,  118,   99,   97,   97,   99,    0,
  100,  100,    0,  100,    0,    0,    0,   98,   98,    0,
    0,    0,    0,   99,   99,    0,   99,    0,    0,    0,
    0,    0,    0,    0,    0,  118,    0,   97,    0,    0,
  101,    0,    0,  101,  100,  100,  101,    0,   98,    0,
    0,    0,    0,    0,    0,    0,    0,   99,   99,    0,
    0,  101,  101,    0,  101,    0,    0,    0,  102,    0,
    0,  102,    0,    0,  102,  100,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   99,  102,
  102,    0,  102,    0,    0,  101,  101,  103,    0,    0,
  103,    0,    0,  103,    4,    0,    0,    0,    5,    0,
    6,    0,    0,    0,    0,   85,   85,    0,  103,    0,
  202,    0,    0,  102,  102,    0,  101,    0,    0,   11,
    0,   85,   85,   85,   85,   85,   85,   85,   13,   85,
   85,   85,  203,    0,   95,   95,    0,    0,   16,    0,
    0,    0,  103,  103,  102,   96,   96,    0,    0,    0,
   95,   95,   95,   95,   95,   95,   95,    0,   95,   95,
   95,   96,   96,   96,   96,   96,   96,   96,    0,   96,
   96,   96,    0,  103,    0,    0,    0,    0,    0,    0,
  104,    0,    0,  104,    0,    0,  104,    0,    0,    0,
  118,  118,   97,   97,    0,    0,    0,    0,    0,    0,
    0,  104,    0,   98,   98,    0,  118,    0,   97,   97,
   97,   97,   97,   97,   97,    0,   97,   97,   97,   98,
   98,   98,   98,   98,   98,   98,    0,   98,   98,   98,
  100,  100,    0,    0,   41,  104,  104,    0,    0,  115,
    0,    0,  115,   99,   99,   85,  100,  100,  100,  100,
  100,  100,  100,    0,  100,  100,  100,  115,    0,   99,
   99,   99,   99,   99,   99,   99,  104,   99,   99,   99,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  101,  101,    0,    0,    0,    0,  105,    0,    0,
  105,  115,  115,  105,    0,    0,    0,  101,  101,  101,
  101,  101,  101,  101,    0,  101,  101,  101,  105,  102,
  102,    0,    0,    0,    0,  106,   85,    0,  106,    0,
    0,  106,  115,    0,    0,  102,  102,  102,  102,  102,
  102,  102,    0,  102,  102,  102,  106,    0,  103,  103,
    0,    0,  105,  105,  107,    0,    0,  107,    0,    0,
  107,    0,    0,    0,  103,  103,  103,    0,  119,  103,
  103,  119,  103,  103,  103,  107,    0,    0,    0,    0,
  106,  106,  108,  105,    0,  108,  119,    0,  108,    0,
    0,    0,    0,  109,    0,    0,  109,  204,    0,  109,
    0,    0,    0,  108,    0,    0,    0,    0,    0,  107,
  107,  106,    0,    0,  109,  110,    0,    0,  110,    0,
  119,  110,    0,    0,    0,  111,    0,  211,  111,  112,
    0,  111,  112,    0,    0,  112,  110,  108,  108,    0,
  107,  104,  104,    0,    0,    0,  111,    0,  109,  109,
  112,  119,    0,    0,    0,    0,    0,  104,  104,  104,
  231,    0,  104,  104,    0,  104,  104,  104,  108,  113,
  110,  110,  113,  204,  211,  113,    0,    0,  231,  109,
  111,  111,    0,    0,  112,  112,    0,    0,    0,  114,
  113,    0,  114,    0,    0,  114,    0,  115,  115,  116,
    0,  110,  116,    0,  117,    0,    0,  117,    0,    0,
  114,  111,    0,  115,  115,  112,    0,  116,    0,    0,
    0,    0,  117,    0,  113,  113,    0,    0,  120,    0,
    0,  120,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  114,  114,  120,    0,  105,  105,
    0,  116,  116,    0,    0,  113,  117,  117,    0,    0,
    0,    0,    0,    0,  105,  105,  105,    0,    0,  105,
  105,    0,  105,  105,  105,  114,  106,  106,    0,    0,
  120,    0,  116,    0,    0,    0,    0,  117,    0,    0,
    0,   37,  106,  106,  106,    0,    0,   37,    0,    0,
  106,  106,  106,    0,    0,  107,  107,    0,   37,    0,
   37,  120,    0,    0,    0,    0,  119,  119,    0,    0,
    0,  107,  107,  107,    0,    0,    0,    0,    0,  107,
  107,  107,  119,  108,  108,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  109,  109,    0,    0,    0,  108,
  108,  108,    0,    0,    0,    0,   55,  108,    0,  108,
  109,  109,  109,    0,    0,    0,  110,  110,  109,    0,
  109,   17,    0,    0,    0,    0,  111,  111,    0,   37,
  112,  112,  110,  110,  110,    0,    0,    0,    0,  104,
  110,    0,  111,  111,  111,    0,  112,  112,  112,    0,
  111,    0,    0,    0,    0,   38,    0,    0,    0,    0,
    0,   38,    0,    0,    0,    0,   61,    0,    0,    0,
  113,  113,   38,    0,   38,  141,    0,    0,  145,  146,
    0,    0,    0,    0,    0,    0,  113,  113,  113,   17,
  114,  114,    0,    0,    0,    0,    0,  116,  116,   61,
   37,    0,  117,  117,    0,    0,  114,  114,  114,   61,
    0,    0,    0,  116,  116,    0,    0,    0,  117,  117,
    0,    0,    0,    0,    0,    0,  120,  120,    0,    0,
   37,    0,    0,    0,    0,   61,   61,    0,   61,   61,
    0,    0,  120,   38,    0,    0,    0,    0,    0,    0,
  196,    0,  197,    0,   61,    1,    2,    3,    4,    0,
    0,    0,    5,   37,    6,    0,    0,    0,    0,    0,
    0,    7,    0,    8,    9,   10,   37,   37,    0,    0,
    0,   37,    0,   11,    0,  212,   12,    0,    0,  214,
    0,    0,   13,    0,   14,    0,   15,    0,    0,    0,
    0,    0,   16,    0,    0,    0,    0,    0,  225,    0,
   61,    0,   61,    0,   38,  229,  230,    0,   61,    0,
    0,  234,    0,    1,    2,    3,    4,    0,    0,    0,
    5,    0,    6,    0,    0,    0,    0,    0,    0,    0,
    0,    8,    9,   10,   38,   61,    0,    0,    0,   61,
    0,   11,    0,    0,   12,    0,    0,    0,    0,    0,
   13,    0,   14,    0,   15,    0,    0,    0,   61,    0,
   16,    0,    0,    0,    0,   61,   61,   38,    0,    0,
    0,   61,    0,    0,    0,    0,    0,    0,    0,    0,
   38,   38,    0,    0,    0,   38,
};
static const short yycheck[] = {                         41,
    0,   33,   44,   94,  124,   38,   13,   41,   40,    0,
   44,   43,  187,   45,   59,    6,   41,   59,   59,   44,
   40,   40,   40,  123,   41,   59,   17,   44,   19,   41,
  159,   81,   44,    0,   59,   44,   43,   50,   51,   52,
   53,   44,   59,   61,  219,   64,   64,   59,   44,   99,
   59,   93,   37,   43,   33,   45,   59,   42,   40,   93,
  304,   40,   47,   59,   43,  123,   45,    0,   93,   40,
   33,   40,   91,   91,    7,  298,   93,   40,  235,  236,
   43,   93,   45,  240,   17,   59,   19,  304,   33,   61,
   61,  310,  311,   64,   59,   40,   46,   88,   43,   60,
   45,   62,  314,  116,  117,  118,  235,  236,   37,   38,
  315,  240,   41,   42,   43,   44,   45,   46,   47,  313,
   91,   54,  289,  123,  290,  125,  121,  122,  123,  124,
   59,   60,  262,   62,   59,  119,  120,   37,   38,  125,
  126,   41,   42,   43,   44,   45,   46,   47,  161,  130,
  131,  301,  132,  133,   91,   88,  123,  125,  125,   59,
   60,   40,   62,   40,   93,   94,   37,   38,  159,   41,
   41,   42,   43,   44,   45,   46,   47,  134,  135,  301,
   41,  257,  258,  259,   41,  305,   40,   91,   59,   60,
   41,   62,   93,   93,   94,  124,   93,   41,  189,  275,
   91,  277,  260,   44,   44,   93,  264,   41,  266,  304,
   41,   44,  288,   40,   40,  306,  223,  268,  276,   41,
  296,   91,   93,   94,  124,   93,   93,  285,   41,   37,
   38,  222,   41,   93,   42,   43,  294,   45,   46,   47,
  298,   59,  268,   59,  235,  236,  304,  289,  290,  240,
  125,    0,   60,  124,   62,  289,  290,  257,  258,  259,
  260,   41,  125,   41,  264,  290,  266,  299,  268,  301,
  302,  303,  304,  273,  307,  275,  276,  277,  290,  299,
  312,  301,  302,  303,  304,  285,   94,   59,  288,   41,
  257,  258,  259,  260,  294,   41,  296,  264,  298,  266,
   19,  268,   86,   88,  304,  154,  273,  139,  275,  276,
  277,  115,  257,  258,  259,  127,  124,  128,  285,  129,
  299,  288,  301,  302,  303,  304,   -1,  294,   -1,  296,
  275,  298,  277,  312,  136,   -1,  299,  304,  301,  302,
  303,  304,  137,  288,   -1,   -1,   -1,  308,  309,  312,
   -1,  296,   -1,   -1,  299,   -1,  301,  302,  303,  304,
  289,  290,   -1,   -1,   -1,   -1,   -1,  312,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  305,  306,  307,  308,
  309,  310,  311,   -1,  313,  314,  315,   -1,   -1,  289,
  290,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  305,  306,  307,  308,  309,
  310,  311,   -1,  313,  314,  315,   -1,   -1,  289,  290,
   -1,   -1,   -1,   37,   38,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,  305,  306,  307,  308,  309,  310,
  311,   -1,  313,  314,  315,   59,   60,   -1,   62,   -1,
   -1,   -1,   -1,   38,   -1,   -1,   41,   -1,   43,   44,
   45,   -1,   -1,   -1,   38,   -1,   -1,   41,   -1,   43,
   44,   45,   -1,   -1,   59,   60,   -1,   62,   -1,   93,
   94,  289,  290,   -1,   -1,   59,   60,   -1,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  305,  306,  307,
  308,  309,  310,  311,   -1,  313,  314,  315,   93,   94,
  124,   38,   41,   -1,   41,   44,   43,   44,   45,   93,
   94,   -1,   38,   -1,   -1,   41,   -1,  123,   44,   -1,
   59,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,  124,
   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   38,
  124,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
   -1,   -1,   38,   -1,   93,   41,   93,   94,   44,   -1,
   59,   60,   -1,   62,   -1,   -1,   -1,   93,   94,   -1,
   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  124,   -1,  124,   -1,   -1,
   38,   -1,   -1,   41,   93,   94,   44,   -1,  124,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   94,   -1,
   -1,   59,   60,   -1,   62,   -1,   -1,   -1,   38,   -1,
   -1,   41,   -1,   -1,   44,  124,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  124,   59,
   60,   -1,   62,   -1,   -1,   93,   94,   38,   -1,   -1,
   41,   -1,   -1,   44,  260,   -1,   -1,   -1,  264,   -1,
  266,   -1,   -1,   -1,   -1,  289,  290,   -1,   59,   -1,
  276,   -1,   -1,   93,   94,   -1,  124,   -1,   -1,  285,
   -1,  305,  306,  307,  308,  309,  310,  311,  294,  313,
  314,  315,  298,   -1,  289,  290,   -1,   -1,  304,   -1,
   -1,   -1,   93,   94,  124,  289,  290,   -1,   -1,   -1,
  305,  306,  307,  308,  309,  310,  311,   -1,  313,  314,
  315,  305,  306,  307,  308,  309,  310,  311,   -1,  313,
  314,  315,   -1,  124,   -1,   -1,   -1,   -1,   -1,   -1,
   38,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,
  289,  290,  289,  290,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   -1,  289,  290,   -1,  305,   -1,  305,  306,
  307,  308,  309,  310,  311,   -1,  313,  314,  315,  305,
  306,  307,  308,  309,  310,  311,   -1,  313,  314,  315,
  289,  290,   -1,   -1,    6,   93,   94,   -1,   -1,   41,
   -1,   -1,   44,  289,  290,   17,  305,  306,  307,  308,
  309,  310,  311,   -1,  313,  314,  315,   59,   -1,  305,
  306,  307,  308,  309,  310,  311,  124,  313,  314,  315,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  289,  290,   -1,   -1,   -1,   -1,   38,   -1,   -1,
   41,   93,   94,   44,   -1,   -1,   -1,  305,  306,  307,
  308,  309,  310,  311,   -1,  313,  314,  315,   59,  289,
  290,   -1,   -1,   -1,   -1,   38,   88,   -1,   41,   -1,
   -1,   44,  124,   -1,   -1,  305,  306,  307,  308,  309,
  310,  311,   -1,  313,  314,  315,   59,   -1,  289,  290,
   -1,   -1,   93,   94,   38,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   -1,  305,  306,  307,   -1,   41,  310,
  311,   44,  313,  314,  315,   59,   -1,   -1,   -1,   -1,
   93,   94,   38,  124,   -1,   41,   59,   -1,   44,   -1,
   -1,   -1,   -1,   38,   -1,   -1,   41,  159,   -1,   44,
   -1,   -1,   -1,   59,   -1,   -1,   -1,   -1,   -1,   93,
   94,  124,   -1,   -1,   59,   38,   -1,   -1,   41,   -1,
   93,   44,   -1,   -1,   -1,   38,   -1,  189,   41,   38,
   -1,   44,   41,   -1,   -1,   44,   59,   93,   94,   -1,
  124,  289,  290,   -1,   -1,   -1,   59,   -1,   93,   94,
   59,  124,   -1,   -1,   -1,   -1,   -1,  305,  306,  307,
  222,   -1,  310,  311,   -1,  313,  314,  315,  124,   38,
   93,   94,   41,  235,  236,   44,   -1,   -1,  240,  124,
   93,   94,   -1,   -1,   93,   94,   -1,   -1,   -1,   38,
   59,   -1,   41,   -1,   -1,   44,   -1,  289,  290,   41,
   -1,  124,   44,   -1,   41,   -1,   -1,   44,   -1,   -1,
   59,  124,   -1,  305,  306,  124,   -1,   59,   -1,   -1,
   -1,   -1,   59,   -1,   93,   94,   -1,   -1,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   94,   59,   -1,  289,  290,
   -1,   93,   94,   -1,   -1,  124,   93,   94,   -1,   -1,
   -1,   -1,   -1,   -1,  305,  306,  307,   -1,   -1,  310,
  311,   -1,  313,  314,  315,  124,  289,  290,   -1,   -1,
   93,   -1,  124,   -1,   -1,   -1,   -1,  124,   -1,   -1,
   -1,    0,  305,  306,  307,   -1,   -1,    6,   -1,   -1,
  313,  314,  315,   -1,   -1,  289,  290,   -1,   17,   -1,
   19,  124,   -1,   -1,   -1,   -1,  289,  290,   -1,   -1,
   -1,  305,  306,  307,   -1,   -1,   -1,   -1,   -1,  313,
  314,  315,  305,  289,  290,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  289,  290,   -1,   -1,   -1,  305,
  306,  307,   -1,   -1,   -1,   -1,   11,  313,   -1,  315,
  305,  306,  307,   -1,   -1,   -1,  289,  290,  313,   -1,
  315,  123,   -1,   -1,   -1,   -1,  289,  290,   -1,   88,
  289,  290,  305,  306,  307,   -1,   -1,   -1,   -1,   44,
  313,   -1,  305,  306,  307,   -1,  305,  306,  307,   -1,
  313,   -1,   -1,   -1,   -1,    0,   -1,   -1,   -1,   -1,
   -1,    6,   -1,   -1,   -1,   -1,   11,   -1,   -1,   -1,
  289,  290,   17,   -1,   19,   80,   -1,   -1,   83,   84,
   -1,   -1,   -1,   -1,   -1,   -1,  305,  306,  307,  123,
  289,  290,   -1,   -1,   -1,   -1,   -1,  289,  290,   44,
  159,   -1,  289,  290,   -1,   -1,  305,  306,  307,   54,
   -1,   -1,   -1,  305,  306,   -1,   -1,   -1,  305,  306,
   -1,   -1,   -1,   -1,   -1,   -1,  289,  290,   -1,   -1,
  189,   -1,   -1,   -1,   -1,   80,   81,   -1,   83,   84,
   -1,   -1,  305,   88,   -1,   -1,   -1,   -1,   -1,   -1,
  155,   -1,  157,   -1,   99,  257,  258,  259,  260,   -1,
   -1,   -1,  264,  222,  266,   -1,   -1,   -1,   -1,   -1,
   -1,  273,   -1,  275,  276,  277,  235,  236,   -1,   -1,
   -1,  240,   -1,  285,   -1,  190,  288,   -1,   -1,  194,
   -1,   -1,  294,   -1,  296,   -1,  298,   -1,   -1,   -1,
   -1,   -1,  304,   -1,   -1,   -1,   -1,   -1,  213,   -1,
  155,   -1,  157,   -1,  159,  220,  221,   -1,  163,   -1,
   -1,  226,   -1,  257,  258,  259,  260,   -1,   -1,   -1,
  264,   -1,  266,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  275,  276,  277,  189,  190,   -1,   -1,   -1,  194,
   -1,  285,   -1,   -1,  288,   -1,   -1,   -1,   -1,   -1,
  294,   -1,  296,   -1,  298,   -1,   -1,   -1,  213,   -1,
  304,   -1,   -1,   -1,   -1,  220,  221,  222,   -1,   -1,
   -1,  226,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  235,  236,   -1,   -1,   -1,  240,
};
#define YYFINAL 18
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 315
#if YYDEBUG
static const char *yyname[] = {

"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
"'!'",0,0,0,"'%'","'&'",0,"'('","')'","'*'","'+'","','","'-'","'.'","'/'",0,0,0,
0,0,0,0,0,0,0,0,"';'","'<'","'='","'>'",0,"'@'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,"'['",0,"']'","'^'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,"'{'","'|'","'}'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ANYEXCEPTION","BEXP",
"BIT","BREAK","CASE","CATCH","CLASS","CONTINUE","CHARACTER","DO","DEFAULT",
"ELSE","EOF","FINAL","FALSE","FOR","FUNC","IMPORT","INT","IF","KMAP","NEW",
"NULL","NL","PUBLIC","PACKAGE","PRIVATE","PROTECTED","RETURN","SWITCH","STATIC",
"STRING","SCAND","SCOR","THIS","THROW","THROWS","TRY","TRUE","TRUTHTABLE",
"VOID","WHILE","INTEGERLITERAL","CHARACTERLITERAL","BITLITERAL","STRINGLITERAL",
"BEXPRESSIONLITERAL","IDENTIFIER","OR","XOR","AND","LEQ","GEQ","DEQ","NEQ",
"NOT","NOR","NAND","XNOR",
};
static const char *yyrule[] = {
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
"function_header : type IDENTIFIER '(' formal_parameter_list ')'",
"formal_parameter_list : formal_parameters",
"formal_parameter_list :",
"formal_parameters : formal_parameters ',' formal_parameter",
"formal_parameters : formal_parameter",
"formal_parameter : type IDENTIFIER",
"function_body : block",
"block : '{' block_statements_or_empty '}'",
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
"statement_without_trailing_substatement : do_statement",
"statement_without_trailing_substatement : break_statement",
"statement_without_trailing_substatement : continue_statement",
"statement_without_trailing_substatement : return_statement",
"statement_without_trailing_substatement : try_statement",
"if_then_statement : IF '(' expression ')' statement",
"if_then_else_statement : IF '(' expression ')' statement_matched_if ELSE statement",
"if_then_else_statement_matched_if : IF '(' expression ')' statement_matched_if ELSE statement_matched_if",
"do_statement : DO statement WHILE '(' expression ')' ';'",
"while_statement : WHILE '(' expression ')' statement",
"while_statement_matched_if : WHILE '(' expression ')' statement_matched_if",
"expression_or_empty :",
"expression_or_empty : expression",
"statement_expression : assignment",
"statement_expression : method_invocation",
"break_statement : BREAK ';'",
"continue_statement : CONTINUE ';'",
"return_statement : RETURN expression_or_empty ';'",
"try_statement : TRY block catches",
"catches : catch_clause",
"catches : catches catch_clause",
"catch_clause : CATCH '(' formal_parameter ')' block",
"expression : assignment_expression",
"assignment_expression : conditional_expression",
"assignment_expression : assignment",
"assignment : IDENTIFIER '=' assignment_expression",
"assignment : array_access '=' assignment_expression",
"postfix_expression : IDENTIFIER",
"postfix_expression : literal",
"postfix_expression : '(' expression ')'",
"postfix_expression : method_invocation",
"postfix_expression : array_access",
"postfix_expression : IDENTIFIER '@' BITLITERAL",
"array_access : IDENTIFIER '[' INTEGERLITERAL ']'",
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
"unary_expression_not_plus_minus : postfix_expression '.' postfix_expression",
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
"type : ANYEXCEPTION",

};
#endif

int      yydebug;
int      yynerrs;

int      yyerrflag;
int      yychar;
YYSTYPE  yyval;
YYSTYPE  yylval;

/* define the initial stack-sizes */
#ifdef YYSTACKSIZE
#undef YYMAXDEPTH
#define YYMAXDEPTH  YYSTACKSIZE
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH  500
#endif
#endif

#define YYINITSTACKSIZE 500

typedef struct {
    unsigned stacksize;
    short    *s_base;
    short    *s_mark;
    short    *s_last;
    YYSTYPE  *l_base;
    YYSTYPE  *l_mark;
} YYSTACKDATA;
/* variables for the parser stack */
static YYSTACKDATA yystack;
#line 303 "bzcut.y"

  private BoolZebra lexer;
  Type t,rt;
  Identifier id,id1,fid,ide,ida;
  RoutineDeclaration rd;
  RoutineArrayDeclaration rad;
  MultipleDeclaration md;
  ArrayList<Identifier> idlist=new ArrayList<Identifier>();
  ArrayList<Node> n=new ArrayList<Node> ();
  IntegerLiteral il;
  StringLiteral sl;
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
//  ArrayList<DeclarationNode> decln=new ArrayList<DeclarationNode>();
  DeclarationNode decl;
//  ArrayList<StatementNode> stn=new ArrayList<StatementNode>();
 ArrayList<Node> bst=new ArrayList<Node>();
  ArrayList<Expr> exprlist=new ArrayList<Expr>();
//  StatementList stl;
  BlockStatement bs,bs1;
  BinExpr bexp;
  UnaryExpr unexp;
  DoWhile dowhile;
  WhileLoop whileloop;
  ArrayLookup alook;
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
int arraydeclflag=0,arraydeclflag2=0;

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
#line 855 "y.tab.c"

#if YYDEBUG
#include <stdio.h>		/* needed for printf */
#endif

#include <stdlib.h>	/* needed for malloc, etc */
#include <string.h>	/* needed for memset */

/* allocate initial stack or double stack size, up to YYMAXDEPTH */
static int yygrowstack(YYSTACKDATA *data)
{
    int i;
    unsigned newsize;
    short *newss;
    YYSTYPE *newvs;

    if ((newsize = data->stacksize) == 0)
        newsize = YYINITSTACKSIZE;
    else if (newsize >= YYMAXDEPTH)
        return -1;
    else if ((newsize *= 2) > YYMAXDEPTH)
        newsize = YYMAXDEPTH;

    i = (int) (data->s_mark - data->s_base);
    newss = (short *)realloc(data->s_base, newsize * sizeof(*newss));
    if (newss == 0)
        return -1;

    data->s_base = newss;
    data->s_mark = newss + i;

    newvs = (YYSTYPE *)realloc(data->l_base, newsize * sizeof(*newvs));
    if (newvs == 0)
        return -1;

    data->l_base = newvs;
    data->l_mark = newvs + i;

    data->stacksize = newsize;
    data->s_last = data->s_base + newsize - 1;
    return 0;
}

#if YYPURE || defined(YY_NO_LEAKS)
static void yyfreestack(YYSTACKDATA *data)
{
    free(data->s_base);
    free(data->l_base);
    memset(data, 0, sizeof(*data));
}
#else
#define yyfreestack(data) /* nothing */
#endif

#define YYABORT  goto yyabort
#define YYREJECT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR  goto yyerrlab

int
YYPARSE_DECL()
{
    int yym, yyn, yystate;
#if YYDEBUG
    const char *yys;

    if ((yys = getenv("YYDEBUG")) != 0)
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = YYEMPTY;
    yystate = 0;

#if YYPURE
    memset(&yystack, 0, sizeof(yystack));
#endif

    if (yystack.s_base == NULL && yygrowstack(&yystack)) goto yyoverflow;
    yystack.s_mark = yystack.s_base;
    yystack.l_mark = yystack.l_base;
    yystate = 0;
    *yystack.s_mark = 0;

yyloop:
    if ((yyn = yydefred[yystate]) != 0) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = YYLEX) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("%sdebug: state %d, reading %d (%s)\n",
                    YYPREFIX, yystate, yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("%sdebug: state %d, shifting to state %d\n",
                    YYPREFIX, yystate, yytable[yyn]);
#endif
        if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack))
        {
            goto yyoverflow;
        }
        yystate = yytable[yyn];
        *++yystack.s_mark = yytable[yyn];
        *++yystack.l_mark = yylval;
        yychar = YYEMPTY;
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;

    yyerror("syntax error");

    goto yyerrlab;

yyerrlab:
    ++yynerrs;

yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yystack.s_mark]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("%sdebug: state %d, error recovery shifting\
 to state %d\n", YYPREFIX, *yystack.s_mark, yytable[yyn]);
#endif
                if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack))
                {
                    goto yyoverflow;
                }
                yystate = yytable[yyn];
                *++yystack.s_mark = yytable[yyn];
                *++yystack.l_mark = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("%sdebug: error recovery discarding state %d\n",
                            YYPREFIX, *yystack.s_mark);
#endif
                if (yystack.s_mark <= yystack.s_base) goto yyabort;
                --yystack.s_mark;
                --yystack.l_mark;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("%sdebug: state %d, error recovery discards token %d (%s)\n",
                    YYPREFIX, yystate, yychar, yys);
        }
#endif
        yychar = YYEMPTY;
        goto yyloop;
    }

yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("%sdebug: state %d, reducing by rule %d (%s)\n",
                YYPREFIX, yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    if (yym)
        yyval = yystack.l_mark[1-yym];
    else
        memset(&yyval, 0, sizeof yyval);
    switch (yyn)
    {
case 1:
#line 49 "bzcut.y"
	{Nodelist nd=new Nodelist(n);nd.accept(new PrettyPrintVisitor());}
break;
case 4:
#line 52 "bzcut.y"
	{n.add(fd);}
break;
case 5:
#line 53 "bzcut.y"
	{if (arraydeclflag==1)
{
rad=new RoutineArrayDeclaration(t,id,e,null);n.add(rad);
}
else if (arraydeclflag2==1)
{
rad=new RoutineArrayDeclaration(t,id,exprlist.get(0),exprlist.get(1));n.add(rad);
}
else
{
if (idlist.size()==1)
 {rd=new RoutineDeclaration(t,id);n.add(rd);
} 
else 
{ md=new MultipleDeclaration(t,idlist);n.add(md); }
} 
idlist.clear();
exprlist.clear();
arraydeclflag=0;
arraydeclflag2=0;
}
break;
case 6:
#line 73 "bzcut.y"
	{n.add(st);}
break;
case 12:
#line 79 "bzcut.y"
	{id=new Identifier(yystack.l_mark[0].sval);System.out.println("Identifier Defined");idlist.add(id);}
break;
case 13:
#line 80 "bzcut.y"
	{arraydeclflag=1;}
break;
case 14:
#line 80 "bzcut.y"
	{arraydeclflag2=1;}
break;
case 15:
#line 82 "bzcut.y"
	{fd=new FunctionDeclaration(rt,fid,fagl,bs,null);}
break;
case 16:
#line 83 "bzcut.y"
	{rt=t;fid=new Identifier(yystack.l_mark[-3].sval);fagl=new Fargumentlist(fag);}
break;
case 19:
#line 85 "bzcut.y"
	{fag.add(fg);}
break;
case 20:
#line 85 "bzcut.y"
	{fag.add(fg);}
break;
case 21:
#line 86 "bzcut.y"
	{id=new Identifier(yystack.l_mark[0].sval);fg=new Fargument(t,id);}
break;
case 23:
#line 89 "bzcut.y"
	{bst.clear();}
break;
case 24:
#line 90 "bzcut.y"
	{bs=new BlockStatement(bst);st=bs;}
break;
case 25:
#line 90 "bzcut.y"
	{bs=new BlockStatement(null);st=bs;}
break;
case 28:
#line 92 "bzcut.y"
	{bst.add(decl);}
break;
case 29:
#line 92 "bzcut.y"
	{bst.add(st);}
break;
case 31:
#line 94 "bzcut.y"
	{
if (arraydeclflag==1)
{
decl=new RoutineArrayDeclaration(t,id,e,null);
}
else if (arraydeclflag2==1)
{
decl=new RoutineArrayDeclaration(t,id,exprlist.get(exprlist.indexOf(e)-1),e);
}
else
{
if (idlist.size()==1)
 {decl=new RoutineDeclaration(t,id);} 
else { decl=new MultipleDeclaration(t,idlist); }
}
 idlist.clear();
arraydeclflag=0;
arraydeclflag2=0;
}
break;
case 33:
#line 115 "bzcut.y"
	{st=ifst;}
break;
case 34:
#line 116 "bzcut.y"
	{st=ifelsest;}
break;
case 35:
#line 117 "bzcut.y"
	{st=whileloop;}
break;
case 36:
#line 121 "bzcut.y"
	{bs1=bs;}
break;
case 37:
#line 122 "bzcut.y"
	{bs1=bs;}
break;
case 41:
#line 129 "bzcut.y"
	{st=dowhile;}
break;
case 46:
#line 137 "bzcut.y"
	{ifst=new IfStatement (exprlist.get(0),bs);exprlist.clear();}
break;
case 47:
#line 142 "bzcut.y"
	{ifelsest=new IfElseStatement(exprlist.get(0),bs1,bs);exprlist.clear();}
break;
case 49:
#line 149 "bzcut.y"
	{dowhile=new DoWhile(exprlist.get(exprlist.size()-1),bs);System.out.println("Do while ");exprlist.clear();}
break;
case 50:
#line 153 "bzcut.y"
	{whileloop=new WhileLoop (exprlist.get(0),bs);exprlist.clear();}
break;
case 51:
#line 156 "bzcut.y"
	{whileloop=new WhileLoop (exprlist.get(0),bs);exprlist.clear();}
break;
case 54:
#line 164 "bzcut.y"
	{st=ass;}
break;
case 55:
#line 165 "bzcut.y"
	{st=mi;}
break;
case 63:
#line 188 "bzcut.y"
	{exprlist.add(e);System.out.println ("Expression Found");}
break;
case 66:
#line 195 "bzcut.y"
	{id=new Identifier(yystack.l_mark[-2].sval);ass=new Assign(id,e);System.out.println("Ass found");}
break;
case 67:
#line 195 "bzcut.y"
	{ass=new Assign(alook,e);System.out.println("Array access found");}
break;
case 68:
#line 197 "bzcut.y"
	{ide = new Identifier(yystack.l_mark[0].sval);e=ide;}
break;
case 69:
#line 197 "bzcut.y"
	{e=l;}
break;
case 71:
#line 197 "bzcut.y"
	{e=miexpr;}
break;
case 72:
#line 197 "bzcut.y"
	{e=alook;}
break;
case 74:
#line 200 "bzcut.y"
	{ida=new Identifier(yystack.l_mark[-3].sval);alook=new ArrayLookup(ida,e,null);System.out.println("Array lookup found");}
break;
case 76:
#line 202 "bzcut.y"
	{ida=new Identifier(yystack.l_mark[-6].sval);alook=new ArrayLookup(ida,exprlist.get(exprlist.indexOf(e)-1),e);exprlist.remove(exprlist.indexOf(e)-1);exprlist.remove(exprlist.indexOf(e));}
break;
case 77:
#line 206 "bzcut.y"
	{id1=new Identifier(yystack.l_mark[-3].sval);mi=new MethodInvocation(id1,aglist);miexpr=new MethodInvocationExpr(id1,aglist);System.out.println("Method invocation found with identifier :"+id);aglist.clear();}
break;
case 80:
#line 212 "bzcut.y"
	{aglist.add(e);}
break;
case 81:
#line 213 "bzcut.y"
	{aglist.add(e);}
break;
case 82:
#line 217 "bzcut.y"
	{unexp= new UnaryExpr(e,Operator.PLUS);e=unexp;e14=e;}
break;
case 83:
#line 218 "bzcut.y"
	{unexp= new UnaryExpr(e,Operator.UMINUS);e=unexp;e14=e;}
break;
case 84:
#line 219 "bzcut.y"
	{e14=e;}
break;
case 86:
#line 223 "bzcut.y"
	{unexp=new UnaryExpr(e,Operator.LNOT);e=unexp;e14=e;}
break;
case 91:
#line 233 "bzcut.y"
	{e13=e;}
break;
case 92:
#line 234 "bzcut.y"
	{bexp=new BinExpr (e13,e14,Operator.MULT);e=bexp;e13=bexp;}
break;
case 93:
#line 235 "bzcut.y"
	{bexp=new BinExpr (e13,e14,Operator.DIV);e=bexp;e13=bexp;}
break;
case 94:
#line 236 "bzcut.y"
	{bexp=new BinExpr (e13,e14,Operator.MOD);e=bexp;e13=bexp;}
break;
case 95:
#line 239 "bzcut.y"
	{e12=e;}
break;
case 96:
#line 240 "bzcut.y"
	{bexp=new BinExpr (e12,e13,Operator.PLUS);e=bexp;e12=bexp;}
break;
case 97:
#line 241 "bzcut.y"
	{bexp=new BinExpr (e12,e13,Operator.MINUS);e=bexp;e12=bexp;}
break;
case 98:
#line 244 "bzcut.y"
	{e11=e;}
break;
case 99:
#line 245 "bzcut.y"
	{bexp=new BinExpr (e11,e12,Operator.LT);e=bexp;e11=bexp;}
break;
case 100:
#line 246 "bzcut.y"
	{bexp=new BinExpr (e11,e12,Operator.GT);e=bexp;e11=bexp;}
break;
case 101:
#line 247 "bzcut.y"
	{bexp=new BinExpr (e11,e12,Operator.LEQ);e=bexp;e11=bexp;}
break;
case 102:
#line 248 "bzcut.y"
	{bexp=new BinExpr (e11,e12,Operator.GEQ);e=bexp;e11=bexp;}
break;
case 103:
#line 251 "bzcut.y"
	{e10=e;}
break;
case 104:
#line 252 "bzcut.y"
	{bexp=new BinExpr (e10,e11,Operator.DEQ);e=bexp;e10=bexp;}
break;
case 105:
#line 253 "bzcut.y"
	{bexp=new BinExpr (e10,e11,Operator.NEQ);e=bexp;e10=bexp;}
break;
case 106:
#line 257 "bzcut.y"
	{e9=e;}
break;
case 107:
#line 258 "bzcut.y"
	{bexp=new BinExpr (e9,e10,Operator.PLUS);e=bexp;e9=bexp;}
break;
case 108:
#line 262 "bzcut.y"
	{e8=e;}
break;
case 109:
#line 263 "bzcut.y"
	{bexp=new BinExpr (e8,e9,Operator.PLUS);e=bexp;e8=bexp;}
break;
case 110:
#line 266 "bzcut.y"
	{e7=e;}
break;
case 111:
#line 267 "bzcut.y"
	{bexp=new BinExpr (e7,e8,Operator.PLUS);e=bexp;e7=bexp;}
break;
case 112:
#line 270 "bzcut.y"
	{e6=e;}
break;
case 113:
#line 271 "bzcut.y"
	{bexp=new BinExpr (e6,e7,Operator.LAND);e=bexp;e6=bexp;}
break;
case 114:
#line 272 "bzcut.y"
	{bexp=new BinExpr (e6,e7,Operator.PLUS);e=bexp;e6=bexp;}
break;
case 115:
#line 276 "bzcut.y"
	{e5=e;}
break;
case 116:
#line 277 "bzcut.y"
	{bexp=new BinExpr (e5,e6,Operator.LXOR);e=bexp;e5=bexp;}
break;
case 117:
#line 278 "bzcut.y"
	{bexp=new BinExpr (e5,e6,Operator.PLUS);e=bexp;e5=bexp;}
break;
case 118:
#line 282 "bzcut.y"
	{e4=e;}
break;
case 119:
#line 283 "bzcut.y"
	{bexp=new BinExpr (e4,e5,Operator.LOR);e=bexp;e4=bexp;}
break;
case 120:
#line 284 "bzcut.y"
	{bexp=new BinExpr (e4,e5,Operator.PLUS);e=bexp;e4=bexp;}
break;
case 121:
#line 287 "bzcut.y"
	{e3=e;}
break;
case 122:
#line 288 "bzcut.y"
	{bexp=new BinExpr (e3,e4,Operator.SCAND);e=bexp;e3=bexp;}
break;
case 123:
#line 291 "bzcut.y"
	{e2=e;}
break;
case 124:
#line 292 "bzcut.y"
	{bexp=new BinExpr (e2,e3,Operator.SCOR);e=bexp;e2=bexp;}
break;
case 125:
#line 295 "bzcut.y"
	{e1=e;}
break;
case 126:
#line 298 "bzcut.y"
	{sl=new StringLiteral(yystack.l_mark[0].sval);System.out.println("String Literal"+e);l=sl; }
break;
case 127:
#line 298 "bzcut.y"
	{il=new IntegerLiteral(yystack.l_mark[0].ival);System.out.println("Num Literal"+il);l=il; }
break;
case 130:
#line 299 "bzcut.y"
	{t=Type.INT;System.out.println("Type defined");}
break;
case 133:
#line 299 "bzcut.y"
	{t=Type.STRING;System.out.println("Type defined");}
break;
#line 1443 "y.tab.c"
    }
    yystack.s_mark -= yym;
    yystate = *yystack.s_mark;
    yystack.l_mark -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("%sdebug: after reduction, shifting from state 0 to\
 state %d\n", YYPREFIX, YYFINAL);
#endif
        yystate = YYFINAL;
        *++yystack.s_mark = YYFINAL;
        *++yystack.l_mark = yyval;
        if (yychar < 0)
        {
            if ((yychar = YYLEX) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("%sdebug: state %d, reading %d (%s)\n",
                        YYPREFIX, YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("%sdebug: after reduction, shifting from state %d \
to state %d\n", YYPREFIX, *yystack.s_mark, yystate);
#endif
    if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack))
    {
        goto yyoverflow;
    }
    *++yystack.s_mark = (short) yystate;
    *++yystack.l_mark = yyval;
    goto yyloop;

yyoverflow:
    yyerror("yacc stack overflow");

yyabort:
    yyfreestack(&yystack);
    return (1);

yyaccept:
    yyfreestack(&yystack);
    return (0);
}
