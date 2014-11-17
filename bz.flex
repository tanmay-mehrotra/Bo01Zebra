/*Lex file for Bo0lZebra */

%%

%line
%char
%column
%class BoolZebra
%unicode
%debug
%byaccj

%{
private Parser yyparser;

public BoolZebra (java.io.Reader r,Parser yyparser)
{
	this(r);
        this.yyparser=yyparser;
}
private StringBuffer string=new StringBuffer();
private StringBuffer string1=new StringBuffer();

public int getLine() {
		return yyline;
	}

%}
LineTerminator=\r|\n|\r\n

InputCharacter=[^\r\n]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"


WhiteSpace={LineTerminator} | [ \t]

Identifier=[:jletter:][:jletterdigit:]*

Bit = "T" | "F"
Integer=0 | [1-9][0-9]*

StringCharacter = [^\r\n\"\\]


%state STRING,BEXP


%%

<YYINITIAL> {

  /* keywords */
  "bexp"			 { return Parser.BEXP; }
  "bit"    	                 { return Parser.BIT; }
  "else"                         { return Parser.ELSE; }
  "func"			 { return Parser.FUNC; }
  "int"                          { return Parser.INT; }
  "if"                           { return Parser.IF; }
  "string"			 { return Parser.STRING;}
  "return"                       { return Parser.RETURN; }
  "void"                         { return Parser.VOID; }
  "while"                        { return Parser.WHILE; }
  "truthtable"			 { return Parser.TRUTHTABLE;}
  "kmap" 			 { return Parser.KMAP;} 
  /* null literal */
  "null"                         { return Parser.NULL; }
  
  
  /* separators */
  "("                            |
  ")"                            |
  "{"                            |
  "}"                            |
  "["                            |
  "]"                            |
  ";"                            |
  ","                            |
  "@"				 |
  "."                            { return (int) yycharat(0); }
  
  /* operators */
  "and"				 { return Parser.AND;}
  "or"				 { return Parser.OR; }
  "xor"				 { return Parser.XOR; }
  "xnor"			 { return Parser.XNOR; }
  "nand"			 { return Parser.NAND; }
  "nor"				 { return Parser.NOR; }
  "not"				 { return Parser.NOT; }
  "="                            |
  ">"                            |
  "<"                            |
  "!"                            |
  "+"                            |
  "-"                            |
  "*"                            |
  "/"                            |
  "&"				 |
  "|"				 |
  "^"				 |
  "%"                            { return (int) yycharat(0); }
  "&&"                           { return Parser.SCAND;}
  "||"                           { return Parser.SCOR;}
  "=="                           { return Parser.DEQ; }
  "<="                           { return Parser.LEQ;}
  ">="                           { return Parser.GEQ;}
  "!="                           { return Parser.NEQ;}
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }


  {Bit}				{ yyparser.yylval =new ParserVal (yytext()); 
				return Parser.BITLITERAL; }
  {Integer} 			{ yyparser.yylval=new ParserVal(Integer.parseInt(yytext()));
				return Parser.INTEGERLITERAL;}
  {Comment}			{  }
  {WhiteSpace}  		{  }
  {Identifier}		         { yyparser.yylval=new ParserVal(yytext());
				return Parser.IDENTIFIER; }

}

<STRING> {
  \"				{ yybegin(YYINITIAL);
				  yyparser.yylval=new ParserVal(string.toString());				
				return Parser.STRINGLITERAL;}
   {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( "\b" ); }
  "\\t"                          { string.append( "\t" ); }
  "\\n"                          { string.append( "\n" ); }

  "\\r"                          { string.append( "\r" ); }
  "\\\""                         { string.append( "\"" ); }
  "\\'"                          { string.append( "\'" ); }

  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}


/* error fallback */
.|\n                             { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }

