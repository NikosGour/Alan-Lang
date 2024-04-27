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

delim =      [ \t\n]
ws    =      {delim}+
l     =      [A-Za-z]
d     =      [0-9]
hex   =      {d}|[A-Fa-f]
escape_char = ([ntr0]|\\|\'|\")

%%

"false"
"true"
"int"
"byte"
"proc"
"reference"
"while"
"if"                                     { return createSymbol(Symbols.T_if);  }
"then"                                   { return createSymbol(Symbols.T_then); }
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

\'({l}|\\x{hex}{hex}?|\\{escape_char})\' { String x = yytext();
                                           return createSymbol(Symbols.T_char_literal, x.substring(1 , x.length() - 1));}
{l}+                                     { return createSymbol(Symbols.T_id, yytext()); }
{d}+                                     { return createSymbol(Symbols.T_num, Integer.valueOf(yytext())); }

{ws}                                     {}
.*                                       { System.out.printf("Captured Unexpected string \"%s\"\n",yytext());}