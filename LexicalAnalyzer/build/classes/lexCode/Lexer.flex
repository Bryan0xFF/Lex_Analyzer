package lexCode;
import java_cup.runtime.Symbol;
%%

%class Lexer
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
%column

espacio = [" "|"\t"|"\r"|"\n"]+
String = ("'")[^'\r\n]*("'")
//OperadorLogico = "<"|"<="|">"|">="|"=="|"!="|"&&"|"||"
//OperadorAritmetico = "+"|"-"|"*"|"/"|"%"|"="
//OtroSimbolo = "["|"]"|"[]"|"("|")"|"{"|"}"|"()"|"{}"|"@"|"#"|"##"
%{
    private Symbol symbol(int type, Object value){
       return new Symbol(type, yyline, yycolumn, value);
    }
%}

%%
{espacio} {/*Ignore*/}
TRUNCATE {return new Symbol(sym.Truncate, yychar, yyline, yytext());}
DROP {return new Symbol(sym.Drop, yychar, yyline, yytext());}
DATABASE {return new Symbol(sym.Database, yychar, yyline, yytext());}
USER {return new Symbol(sym.User, yychar, yyline, yytext());}
INDEX {return new Symbol(sym.Index, yychar, yyline, yytext());}
IDENTIFICADORES {return new Symbol(sym.Id, yychar, yyline, yytext());}
VIEW {return new Symbol(sym.View, yychar, yyline, yytext());}
IF {return new Symbol(sym.If, yychar, yyline, yytext());}
EXISTS {return new Symbol(sym.Exists, yychar, yyline, yytext());}
TABLE {return new Symbol(sym.Table, yychar, yyline, yytext());}
REBUILD {return new Symbol(sym.Rebuild, yychar, yyline, yytext());}
CONSTRAINT {return new Symbol(sym.Constraint, yychar, yyline, yytext());}
PRIMARY {return new Symbol(sym.Primary, yychar, yyline, yytext());}
KEY {return new Symbol(sym.Key, yychar, yyline, yytext());}
UNIQUE {return new Symbol(sym.Unique, yychar, yyline, yytext());}
NONCLUSTERED {return new Symbol(sym.Nonclustered, yychar, yyline, yytext());}
ON {return new Symbol(sym.On, yychar, yyline, yytext());}
OF {return new Symbol(sym.Of, yychar, yyline, yytext());}
GO {return new Symbol(sym.Go, yychar, yyline, yytext());}
INT {return new Symbol(sym.INT, yychar, yyline, yytext());}
FLOAT {return new Symbol(sym.FLOAT, yychar, yyline, yytext());}
TRANSACTION {return new Symbol(sym.Tran, yychar, yyline, yytext());}
BEGIN {return new Symbol(sym.Begin, yychar, yyline, yytext());}
END {return new Symbol(sym.End, yychar, yyline, yytext());}
TRAN {return new Symbol(sym.Transaction, yychar, yyline, yytext());}
SAVEPOINT {return new Symbol(sym.Save, yychar, yyline, yytext());}
INTO {return new Symbol(sym.Into, yychar, yyline, yytext());}
VALUES {return new Symbol(sym.Values, yychar, yyline, yytext());}
INSERT {return new Symbol(sym.Insert, yychar, yyline, yytext());}
COMMIT {return new Symbol(sym.Commit, yychar, yyline, yytext());}
ROLLBACK {return new Symbol(sym.Rollback, yychar, yyline, yytext());}
VARCHAR {return new Symbol(sym.VARCHAR, yychar, yyline, yytext());}
BIT {return new Symbol(sym.BIT, yychar, yyline, yytext());}
FOREIGN {return new Symbol(sym.Foreign, yychar, yyline, yytext());}
Bit {return new Symbol(sym.Bit, yychar, yyline, yytext());}
Int {return new Symbol(sym.Int, yychar, yyline, yytext());}
PERCENT {return new Symbol(sym.Percent, yychar, yyline, yytext());}
Float {return new Symbol(sym.Float, yychar, yyline, yytext());}
String {return new Symbol(sym.String, yychar, yyline, yytext());}
ALTER {return new Symbol(sym.Alter, yychar, yyline, yytext());}
COLUMN {return new Symbol(sym.Column, yychar, yyline, yytext());}
SELECT {return new Symbol(sym.Select, yychar, yyline, yytext());}
TOP {return new Symbol(sym.Top, yychar, yyline, yytext());}
COLLATE {return new Symbol(sym.Collate, yychar, yyline, yytext());}
ASC {return new Symbol(sym.Asc, yychar, yyline, yytext());}
AS {return new Symbol(sym.As, yychar, yyline, yytext());}
DESC {return new Symbol(sym.Desc, yychar, yyline, yytext());}
FUNCTION {return new Symbol(sym.Function, yychar, yyline, yytext());}
NOT {return new Symbol(sym.Not, yychar, yyline, yytext());}
NULL {return new Symbol(sym.Null, yychar, yyline, yytext());}
ADD {return new Symbol(sym.Add, yychar, yyline, yytext());}
DISTINCT {return new Symbol(sym.Distinct, yychar, yyline, yytext());}
COUNT {return new Symbol(sym.Count, yychar, yyline, yytext());}
MAX {return new Symbol(sym.Max, yychar, yyline, yytext());}
MIN {return new Symbol(sym.Min, yychar, yyline, yytext());}
SUM {return new Symbol(sym.Sum, yychar, yyline, yytext());}
AVG {return new Symbol(sym.Avg, yychar, yyline, yytext());}
JOIN {return new Symbol(sym.Join, yychar, yyline, yytext());}
LEFT {return new Symbol(sym.Left, yychar, yyline, yytext());}
RIGHT {return new Symbol(sym.Right, yychar, yyline, yytext());}
FROM {return new Symbol(sym.From, yychar, yyline, yytext());}
CREATE {return new Symbol(sym.Create, yychar, yyline, yytext());}
PROC {return new Symbol(sym.Proc, yychar, yyline, yytext());}
PROCEDURE {return new Symbol(sym.Procedure, yychar, yyline, yytext());}
OUT {return new Symbol(sym.Out, yychar, yyline, yytext());}
OUTPUT {return new Symbol(sym.Output, yychar, yyline, yytext());}
OUTER {return new Symbol(sym.Outer, yychar, yyline, yytext());}
INNER {return new Symbol(sym.Inner, yychar, yyline, yytext());}
FULL {return new Symbol(sym.Full, yychar, yyline, yytext());}
GROUP {return new Symbol(sym.Group, yychar, yyline, yytext());}
ORDER {return new Symbol(sym.Order, yychar, yyline, yytext());}
NOT {return new Symbol(sym.Not, yychar, yyline, yytext());}
BY {return new Symbol(sym.By, yychar, yyline, yytext());}
DECLARE {return new Symbol(sym.Declare, yychar, yyline, yytext());}
CURSOR {return new Symbol(sym.Cursor, yychar, yyline, yytext());}
SCROLL {return new Symbol(sym.Scroll, yychar, yyline, yytext());}
RETURNS {return new Symbol(sym.Returns, yychar, yyline, yytext());}
RETURN {return new Symbol(sym.Return, yychar, yyline, yytext());}
READ {return new Symbol(sym.Read, yychar, yyline, yytext());}
Only {return new Symbol(sym.Only, yychar, yyline, yytext());}
UPDATE {return new Symbol(sym.Update, yychar, yyline, yytext());}
FOR {return new Symbol(sym.For, yychar, yyline, yytext());}
"." {return new Symbol(sym.Punto, yychar, yyline, yytext());}
"," {return new Symbol(sym.Coma, yychar, yyline, yytext());}
";" {return new Symbol(sym.Punto_Coma, yychar, yyline, yytext());}
"!" {return new Symbol(sym.Negar, yychar, yyline, yytext());}
"(" {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}
")" {return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}
"*" {return new Symbol(sym.Asterisco, yychar, yyline, yytext());}
"@" {return new Symbol(sym.Arroba, yychar, yyline, yytext());}
"[" {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}
"]" {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}
"=" {return new Symbol(sym.Igual, yychar, yyline, yytext());}
">" {return new Symbol(sym.MayorQue, yychar, yyline, yytext());}
">=" {return new Symbol(sym.MayorIgual, yychar, yyline, yytext());}
"<" {return new Symbol(sym.MenorQue, yychar, yyline, yytext());}
"<=" {return new Symbol(sym.MenorIgual, yychar, yyline, yytext());}
//. {return new Symbol(sym.Error, yychar, yyline, yytext());}
