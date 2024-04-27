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
%%

"if"            { return createSymbol(Symbols.T_if);  }
"then"          { return createSymbol(Symbols.T_then); }
"else"          { return createSymbol(Symbols.T_else); }
"begin"         { return createSymbol(Symbols.T_begin); }
"end"           { return createSymbol(Symbols.T_end); }
"for"           { return createSymbol(Symbols.T_for); }
"do"            { return createSymbol(Symbols.T_do); }
"print"         { return createSymbol(Symbols.T_print); }
"let"           { return createSymbol(Symbols.T_let); }
"="             { return createSymbol(Symbols.T_eq); }
"+"             { return createSymbol(Symbols.T_plus); }
"-"             { return createSymbol(Symbols.T_minus); }
"/"             { return createSymbol(Symbols.T_div); }
"*"             { return createSymbol(Symbols.T_times); }
"("             { return createSymbol(Symbols.T_lpar); }
")"             { return createSymbol(Symbols.T_rpar); }

{l}+            { return createSymbol(Symbols.T_id, yytext()); }
{d}+            { return createSymbol(Symbols.T_num, Integer.valueOf(yytext())); }

{ws}            {}
\'.*\n          {}