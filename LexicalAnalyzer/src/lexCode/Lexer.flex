package lexCode;
import java_cup.runtime.Symbol;
%%
%class Lexer
%type java_cup.runtime.Symbol
%cup
%full
%line
%column
%char

Letra = [a-zA-Z]+
LetraOguion = [a-zA-Z_]+
Digito = [0-9]+
espacio = [ ,\t,\r,\n]+
Exponente = [eE]
Bit = [0-1]|"NULL"
Signo = [+-]?
Decimal = {Digito}\.{Digito}*
NoDecimal = \.{Digito}+
Identificador = {Letra} ({LetraOguion} | {Digito})*
Float = {Signo} {Decimal} ({Exponente} {Signo} {Digito})?
FloatError1 = {Signo} {NoDecimal} ({Exponente} {Signo} {Digito})?
FloatError2 = {Signo} {Decimal} ({Exponente} {Signo}) | {Signo} {NoDecimal} ({Exponente} {Signo})
OperadorAritmetico = "+"|"-"|"*"|"/"|"%"|"="
OperadorLogico = "<"|"<="|">"|">="|"=="|"!="|"&&"|"||"
SignoDePuntuacion = "!"|";"|","|"."
OtroSimbolo = "["|"]"|"[]"|"("|")"|"{"|"}"|"()"|"{}"|"@"|"#"|"##"
Simbolo = {OperadorAritmetico} | {OperadorLogico} | {SignoDePuntuacion} | {OtroSimbolo}
ComentarioSimple = ("--")[^\r\n]*
ComentarioMultilinea = "/*"~"*/"
ComentarioMultilineaError = "/*"[^\r\n]*
String = ("'")[^'\r\n]*("'")
StringError = ("'")[^'\r\n]*
%{
    private Symbol symbol(int type){
        return new Symbol (type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value){
        return new Symbol (type, yyline, yycolumn, value);
    }
%}
%%
( espacio ) {/*Ignore*/}
( ADD | EXTERNAL | PROCEDURE | ALL | FETCH | PUBLIC | ALTER | FILE | RAISERROR | REBUILD) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( AND | FILLFACTOR | READ | ANY | FOR | READTEXT | AS | FOREIGN | RECONFIGURE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( ASC | FREETEXT | REFERENCES | AUTHORIZATION | FREETEXTTABLE | REPLICATION ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( BACKUP | FROM | RESTORE | BEGIN | FULL | RESTRICT | BETWEEN | FUNCTION | RETURN ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( BREAK | GOTO | REVERT | BROWSE | GRANT |REVOKE | BULK | GROUP | RIGHT ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( BY | HAVING | ROLLBACK | CASCADE | HOLDLOCK | ROWCOUNT ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CASE | IDENTITY | ROWGUIDCOL | CHECK | IDENTITY_INSERT | RULE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CHECKPOINT | IDENTITYCOL | SAVE | FILEGROWTH ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CLOSE | IF | SCHEMA | CLUSTERED | IN | SECURITYAUDIT ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( COALESCE | INDEX | SELECT | COLLATE | INNER | SEMANTICKEYPHRASETABLE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( COLUMN | INSERT | SEMANTICSIMILARITYDETAILSTABLE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( COMMIT | INTERSECT | SEMANTICSIMILARITYTABLE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( COMPUTE | INTO | SESSION_USER | CONSTRAINT | IS | SET ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CONTAINS | JOIN | SETUSER | CONTAINSTABLE | KEY | SHUTDOWN ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CONTINUE | KILL | SOME | CONVERT | LEFT | STATISTICS ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CREATE | LIKE | SYSTEM_USER | CROSS | LINENO | TABLE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CURRENT | LOAD | TABLESAMPLE | CURRENT_DATE | MERGE | TEXTSIZE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CURRENT_TIME | NATIONAL | THEN | CURRENT_TIMESTAMP | NOCHECK | TO ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CURRENT_USER | NONCLUSTERED | TOP | CURSOR | NOT | TRAN ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DATABASE | NULL | TRANSACTION | DBCC | NULLIF | TRIGGER ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DEALLOCATE | OF | TRUNCATE | DECLARE | OFF | TRY_CONVERT ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DEFAULT | OFFSETS | TSEQUAL | DELETE | ON | UNION | DENY | OPEN | UNIQUE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DESC | OPENDATASOURCE |UNPIVOT | DISK | OPENQUERY | UPDATE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DISTINCT | OPENROWSET | UPDATETEXT | DISTRIBUTED | OPENXML | USE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DOUBLE | OPTION | USER | DROP | OR | VALUES | DUMP | ORDER | VARYING ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( ELSE | OUTER | VIEW | END | OVER | WAITFOR | ERRLVL | PERCENT | WHEN ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( ESCAPE | PIVOT | WHERE | EXCEPT | PLAN | WHILE | EXEC | PRECISION | WITH ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( EXECUTE | PRIMARY | WITHIN GROUP | EXISTS | PRINT | WRITETEXT | EXIT | PROC ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( ABSOLUTE | EXEC | OVERLAPS | ACTION | EXECUTE | PAD | ADA | EXISTS | PARTIAL ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( ADD | EXTERNAL | PASCAL | ALL | EXTRACT | POSITION ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( ALLOCATE | FALSE | PRECISION | ALTER | FETCH | PREPARE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( AND | FIRST | PRESERVE | ANY | FLOAT | PRIMARY | ARE | FOR | PRIOR ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( AS | FOREIGN | PRIVILEGES | ASC | FORTRAN | PROCEDURE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( ASSERTION | FOUND | PUBLIC | AT | FROM | READ ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( AUTHORIZATION | FULL | REAL | AVG | GET | REFERENCES ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( BEGIN | GLOBAL | RELATIVE | BETWEEN | GO | RESTRICT ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( BIT | GOTO | REVOKE | BIT_LENGTH | GRANT | RIGHT ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( BOTH | GROUP | ROLLBACK | BY | HAVING | ROWS | CASCADE | HOUR | SCHEMA ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CASCADED | IDENTITY | SCROLL | CASE | IMMEDIATE | SECOND ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CAST | IN | SECTION | CATALOG | INCLUDE | SELECT ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CHAR | INDEX | SESSION | CHAR_LENGTH | INDICATOR | SESSION_USER ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CHARACTER | INITIALLY | SET | CHARACTER_LENGTH | INNER | SIZE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CHECK | INPUT | SMALLINT | CLOSE | INSENSITIVE | SOME ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( COALESCE | INSERT | SPACE | COLLATE | INT | SQL ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( COLLATION | INTEGER | SQLCA | COLUMN | INTERSECT | SQLCODE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( COMMIT | INTERVAL | SQLERROR | CONNECT | INTO | SQLSTATE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CONNECTION | IS | SQLWARNING | CONSTRAINT | ISOLATION | SUBSTRING ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CONSTRAINTS |JOIN | SUM | CONTINUE | KEY | SYSTEM_USER ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CONVERT | LANGUAGE | TABLE | CORRESPONDING | LAST | TEMPORARY ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( COUNT | LEADING | THEN | CREATE | LEFT | TIME | int | float | bit | varchar | integer ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());} 
( CROSS | LEVEL | TIMESTAMP | CURRENT | LIKE | TIMEZONE_HOUR | Int | Float | Bit | Varchar | Integer ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CURRENT_DATE | LOCAL | TIMEZONE_MINUTE | CURRENT_TIME | LOWER | TO ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CURRENT_TIMESTAMP | MATCH | TRAILING | CURRENT_USER | MAX | TRANSACTION ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( CURSOR | MIN | TRANSLATE | DATE | MINUTE | TRANSLATION ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DAY | MODULE | TRIM | DEALLOCATE | MONTH | TRUE | MAXSIZE | FILENAME ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DEC | NAMES | UNION | DECIMAL | NATIONAL | UNIQUE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DECLARE | NATURAL | UNKNOWN | DEFAULT | NCHAR | UPDATE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DEFERRABLE | NEXT | UPPER | DEFERRED | NO | USAGE | DELETE | NONE | USER ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DESC | NOT | USING | DESCRIBE | NULL | VALUE | DESCRIPTOR | NULLIF | VALUES ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DIAGNOSTICS | NUMERIC | VARCHAR | DISCONNECT | OCTET_LENGTH | VARYING ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DISTINCT | OF | VIEW | DOMAIN | ON | WHEN | DOUBLE | ONLY | WHENEVER ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( DROP | OPEN | WHERE | ELSE | OPTION | WITH | END | OR | WORK ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( END-EXEC | ORDER | WRITE | ESCAPE | OUTER | YEAR | EXCEPT | OUTPUT | ZONE ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( EXCEPTION ) {return new Symbol(sym.RESERVADAS, yychar, yyline, yytext());}
( Identificador ) {return new Symbol(sym.IDENTIFICADORES, yychar, yyline, yytext());}
( Bit ) {return new Symbol(sym.BIT, yychar, yyline, yytext());}
( Digito ) {return new Symbol(sym.INT, yychar, yyline, yytext());}
( Float ) {return new Symbol(sym.FLOAT, yychar, yyline, yytext());}
( FloatError1 | FloatError2 ) {return new Symbol(sym.FLOATERROR, yychar, yyline, yytext());}
( Simbolo ) {return new Symbol(sym.OPERADORES, yychar, yyline, yytext());} 
( ComentarioSimple ) {return new Symbol(sym.COMMENT, yychar, yyline, yytext());}
( String ) {return new Symbol(sym.STRING, yychar, yyline, yytext());}
( StringError ) {return new Symbol(sym.STRINGERROR, yychar, yyline, yytext());}
( ComentarioMultilinea ) {/*ignore*/}
( ComentarioMultilineaError ) {return new Symbol(sym.ERRORCOMMENT, yychar, yyline, yytext());}
 ( . ) {return new Symbol(sym.ERROR, yychar, yyline, yytext());}