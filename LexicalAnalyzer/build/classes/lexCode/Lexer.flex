package lexCode;
import static lexCode.Tokens.*;
%%

%class Lexer
%type Tokens
%line
%column

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
public String lexeme;
public int linea;
public int PrimeraColumna;
public int UltimaColumna;
%}

%%
{espacio} {/*Ignore*/}
ADD | EXTERNAL | PROCEDURE | ALL | FETCH | PUBLIC | ALTER | FILE | RAISERROR | REBUILD |
AND | FILLFACTOR | READ | ANY | FOR | READTEXT | AS | FOREIGN | RECONFIGURE |
ASC | FREETEXT | REFERENCES | AUTHORIZATION | FREETEXTTABLE | REPLICATION |
BACKUP | FROM | RESTORE | BEGIN | FULL | RESTRICT | BETWEEN | FUNCTION | RETURN |
BREAK | GOTO | REVERT | BROWSE | GRANT |REVOKE | BULK | GROUP | RIGHT |
BY | HAVING | ROLLBACK | CASCADE | HOLDLOCK | ROWCOUNT |
CASE | IDENTITY | ROWGUIDCOL | CHECK | IDENTITY_INSERT | RULE | 
CHECKPOINT | IDENTITYCOL | SAVE | FILEGROWTH |
CLOSE | IF | SCHEMA | CLUSTERED | IN | SECURITYAUDIT | 
COALESCE | INDEX | SELECT | COLLATE | INNER | SEMANTICKEYPHRASETABLE |
COLUMN | INSERT | SEMANTICSIMILARITYDETAILSTABLE |
COMMIT | INTERSECT | SEMANTICSIMILARITYTABLE |
COMPUTE | INTO | SESSION_USER | CONSTRAINT | IS | SET |
CONTAINS | JOIN | SETUSER | CONTAINSTABLE | KEY | SHUTDOWN |
CONTINUE | KILL | SOME | CONVERT | LEFT | STATISTICS |
CREATE | LIKE | SYSTEM_USER | CROSS | LINENO | TABLE |
CURRENT | LOAD | TABLESAMPLE | CURRENT_DATE | MERGE | TEXTSIZE |
CURRENT_TIME | NATIONAL | THEN | CURRENT_TIMESTAMP | NOCHECK | TO |
CURRENT_USER | NONCLUSTERED | TOP | CURSOR | NOT | TRAN |
DATABASE | NULL | TRANSACTION | DBCC | NULLIF | TRIGGER |
DEALLOCATE | OF | TRUNCATE | DECLARE | OFF | TRY_CONVERT |
DEFAULT | OFFSETS | TSEQUAL | DELETE | ON | UNION | DENY | OPEN | UNIQUE |
DESC | OPENDATASOURCE |UNPIVOT | DISK | OPENQUERY | UPDATE |
DISTINCT | OPENROWSET | UPDATETEXT | DISTRIBUTED | OPENXML | USE |
DOUBLE | OPTION | USER | DROP | OR | VALUES | DUMP | ORDER | VARYING |
ELSE | OUTER | VIEW | END | OVER | WAITFOR | ERRLVL | PERCENT | WHEN |
ESCAPE | PIVOT | WHERE | EXCEPT | PLAN | WHILE | EXEC | PRECISION | WITH |
EXECUTE | PRIMARY | WITHIN GROUP | EXISTS | PRINT | WRITETEXT | EXIT | PROC |
ABSOLUTE | EXEC | OVERLAPS | ACTION | EXECUTE | PAD | ADA | EXISTS | PARTIAL |
ADD | EXTERNAL | PASCAL | ALL | EXTRACT | POSITION |
ALLOCATE | FALSE | PRECISION | ALTER | FETCH | PREPARE |
AND | FIRST | PRESERVE | ANY | FLOAT | PRIMARY | ARE | FOR | PRIOR |
AS | FOREIGN | PRIVILEGES | ASC | FORTRAN | PROCEDURE |
ASSERTION | FOUND | PUBLIC | AT | FROM | READ |
AUTHORIZATION | FULL | REAL | AVG | GET | REFERENCES |
BEGIN | GLOBAL | RELATIVE | BETWEEN | GO | RESTRICT |
BIT | GOTO | REVOKE | BIT_LENGTH | GRANT | RIGHT |
BOTH | GROUP | ROLLBACK | BY | HAVING | ROWS | CASCADE | HOUR | SCHEMA | 
CASCADED | IDENTITY | SCROLL | CASE | IMMEDIATE | SECOND |
CAST | IN | SECTION | CATALOG | INCLUDE | SELECT |
CHAR | INDEX | SESSION | CHAR_LENGTH | INDICATOR | SESSION_USER |
CHARACTER | INITIALLY | SET | CHARACTER_LENGTH | INNER | SIZE |
CHECK | INPUT | SMALLINT | CLOSE | INSENSITIVE | SOME |
COALESCE | INSERT | SPACE | COLLATE | INT | SQL |
COLLATION | INTEGER | SQLCA | COLUMN | INTERSECT | SQLCODE |
COMMIT | INTERVAL | SQLERROR | CONNECT | INTO | SQLSTATE |
CONNECTION | IS | SQLWARNING | CONSTRAINT | ISOLATION | SUBSTRING |
CONSTRAINTS |JOIN | SUM | CONTINUE | KEY | SYSTEM_USER |
CONVERT | LANGUAGE | TABLE | CORRESPONDING | LAST | TEMPORARY |
COUNT | LEADING | THEN | CREATE | LEFT | TIME | int | float | bit | varchar | integer | 
CROSS | LEVEL | TIMESTAMP | CURRENT | LIKE | TIMEZONE_HOUR | Int | Float | Bit | Varchar | Integer |
CURRENT_DATE | LOCAL | TIMEZONE_MINUTE | CURRENT_TIME | LOWER | TO |
CURRENT_TIMESTAMP | MATCH | TRAILING | CURRENT_USER | MAX | TRANSACTION |
CURSOR | MIN | TRANSLATE | DATE | MINUTE | TRANSLATION |
DAY | MODULE | TRIM | DEALLOCATE | MONTH | TRUE | MAXSIZE | FILENAME |
DEC | NAMES | UNION | DECIMAL | NATIONAL | UNIQUE |
DECLARE | NATURAL | UNKNOWN | DEFAULT | NCHAR | UPDATE |
DEFERRABLE | NEXT | UPPER | DEFERRED | NO | USAGE | DELETE | NONE | USER |
DESC | NOT | USING | DESCRIBE | NULL | VALUE | DESCRIPTOR | NULLIF | VALUES |
DIAGNOSTICS | NUMERIC | VARCHAR | DISCONNECT | OCTET_LENGTH | VARYING |
DISTINCT | OF | VIEW | DOMAIN | ON | WHEN | DOUBLE | ONLY | WHENEVER |
DROP | OPEN | WHERE | ELSE | OPTION | WITH | END | OR | WORK |
END-EXEC | ORDER | WRITE | ESCAPE | OUTER | YEAR | EXCEPT | OUTPUT | ZONE |
EXCEPTION {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn +1; UltimaColumna=yycolumn+yylength(); return RESERVADAS;}

{Identificador} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn +1 ; UltimaColumna=yycolumn+yylength(); return IDENTIFICADORES;}
{Bit} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return BIT;}
{Digito} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return INT;}
{Float} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return FLOAT;}
{FloatError1} | {FloatError2} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return FLOATERROR;}
{Simbolo} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return OPERADORES;} 
{ComentarioSimple} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return COMMENT;}
{String} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return STRING;}
{StringError} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return STRINGERROR;}

{ComentarioMultilinea} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return MULTICOMMENT;}
{ComentarioMultilineaError} {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return ERRORCOMMENT;}
 . {lexeme=yytext(); linea=yyline + 1; PrimeraColumna=yycolumn + 1; UltimaColumna=yycolumn+yylength(); return ERROR;}