package gr.hua.dit.it22023_it22121;

import java.io.*;
import java_cup.runtime.Symbol;

%%

%class Lexer
%unicode
%line
%column
%cup

%eofval{
    return createSymbol(Symbols.EOF);
%eofval}

%{
    private StringBuffer sb = new StringBuffer();

    private Symbol createSymbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol createSymbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}

end_of_line = \r|\n|\r\n
delim =      [ \t\f] | {end_of_line}
ws    =      {delim}+
l     =      [A-Za-z]
d     =      [0-9]
hex   =      {d}|[A-Fa-f]
escape_char = ([ntr0]|\\|\'|\")

Number = {d}+
%%
"false"                                  { return createSymbol(Symbols.T_false); }
"true"                                   { return createSymbol(Symbols.T_true); }
"int"                                    { return createSymbol(Symbols.T_int); }
"byte"                                   { return createSymbol(Symbols.T_byte); }
"proc"                                   { return createSymbol(Symbols.T_proc); }
"reference"                              { return createSymbol(Symbols.T_reference); }
"while"                                  { return createSymbol(Symbols.T_while); }
"if"                                     { return createSymbol(Symbols.T_if);  }
"else"                                   { return createSymbol(Symbols.T_else); }

"+"                                      { return createSymbol(Symbols.T_plus); }
"-"                                      { return createSymbol(Symbols.T_minus); }
"/"                                      { return createSymbol(Symbols.T_div); }
"*"                                      { return createSymbol(Symbols.T_times); }
"%"                                      { return createSymbol(Symbols.T_modulo); }
"&"                                      { return createSymbol(Symbols.T_and); }
"|"                                      { return createSymbol(Symbols.T_or); }
"!"                                      { return createSymbol(Symbols.T_not); }
"=="                                     { return createSymbol(Symbols.T_deq); }
"="                                      { return createSymbol(Symbols.T_eq); }
"!="                                     { return createSymbol(Symbols.T_neq); }
"<"                                      { return createSymbol(Symbols.T_less); }
">"                                      { return createSymbol(Symbols.T_greater); }
"<="                                     { return createSymbol(Symbols.T_less_or_eq); }
">="                                     { return createSymbol(Symbols.T_greater_or_eq); }

"("                                      { return createSymbol(Symbols.T_lpar); }
")"                                      { return createSymbol(Symbols.T_rpar); }
"["                                      { return createSymbol(Symbols.T_lsquare); }
"]"                                      { return createSymbol(Symbols.T_rsquare); }
"{"                                      { return createSymbol(Symbols.T_lsquigly); }
"}"                                      { return createSymbol(Symbols.T_rsquigly); }
","                                      { return createSymbol(Symbols.T_comma); }
":"                                      { return createSymbol(Symbols.T_colon); }
";"                                      { return createSymbol(Symbols.T_semicolon); }

\'({l}|{d}|\\x{hex}{hex}?|\\{escape_char})\' { String x = yytext();
                                            System.out.printf("Found char literal: `%s`\n",yytext());
                                           return createSymbol(Symbols.T_char_literal, x.substring(1 , x.length() - 1));}

\"({l}|{d}|\\x{hex}{hex}?|\\{escape_char})*\" { String x = yytext();
                                           System.out.printf("Found string literal: `%s`\n",yytext());
                                           return createSymbol(Symbols.T_string_literal, x.substring(1 , x.length() - 1));}

{l}({l}|{d}|_)+                           { System.out.printf("Found id literal: `%s`\n",yytext());
                                            return createSymbol(Symbols.T_id, yytext()); }
{Number}                                     {System.out.printf("Found num literal: `%s`\n",yytext());
                                            return createSymbol(Symbols.T_num, Integer.valueOf(yytext())); }


{ws}                                     {}
.*                                       { System.err.printf("!Captured Unexpected string `%s`\n",yytext());}