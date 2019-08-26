package lexCode;
import static lexCode.Tokens.*;
%%
%class Lexer
%type Tokens
%line
%column

Op = "+"|"-"|"*"|"/"|"%"|"<"|"<"|"<="|">"|">="|"="|"=="|"!="|"&&"|"||"|"!"|";"|","|","|"."|"."|"["|"]"|"[]"|"("|")"|"{"|"}"|"()"|"{}"|"@"|"#"|"##"
Letra=[a-zA-Z_]+
Separador2 = [,]
Digito=[0-9]+
SpaceTab = [ ,\t]*
Bit = [01] | "NULL"
Enter = [^\r\n]
Newline = [\r\n]
separador=[ ,\t,\r,\n]+
Words = {Letra} | {Digito}
String = "'" [^'] ~"'"
StringErr1 = "'" ~[^'] ~. 
Exponent=[eE] [\+\-]? [0-9]+
Float1=[0-9]+ \. [0-9]+ {Exponent}?
Float3=[0-9]+ \. {Exponent}?
Float4=[0-9]+ {Exponent}
FloatErr = [+-]? \. [0-9]+ {Exponent}?
Float=( [-]? {Float1} | {Float3} | {Float4} ) [fFdD]? | [0-9]+ [fFDd]
Single   = "--" [^] {Enter}* [\n]?
SingleError = "-" [^] {Enter}* [\n]?
Comun    = "/*"{Letra}*{Digito}*{Op}* ~{Enter}+"*/"| "/" "*"+ "/"
Multiple = "/*"( [^*] | (\*+[^*/]) )*\*+\/
MultipleError = "/*" ~[<<EOF>>] 
%{
public String lexeme;
public int line;
public int column;
%}
%%
{Separador2} {lexeme=yytext(); line=yyline; column=yycolumn; return SEPARADOR;}
{separador} {/*Ignore*/}
{Bit} {lexeme=yytext(); line=yyline; column=yycolumn; return BIT;}
{FloatErr} {lexeme=yytext();line=yyline; column=yycolumn; return FLOATERROR;}
{Float} {lexeme=yytext(); line=yyline; column=yycolumn; return FLOAT;}
{String} {lexeme=yytext(); line=yyline; column=yycolumn; return STRING;}
{Single} {lexeme=yytext(); line=yyline; column=yycolumn; return SINGLECOMMENT;}
{StringErr1} {lexeme=yytext(); line=yyline; column=yycolumn; return STRINGERROR;}
{MultipleError} {lexeme=yytext(); line=yyline; column=yycolumn; return ERRORCOMMENT;}
{Op} {lexeme=yytext(); line=yyline; column=yycolumn; return OPERADORES;}
ADD |
EXTERNAL |
PROCEDURE |
ALL |
FETCH |
PUBLIC |
ALTER |
FILE |
RAISERROR |
AND |
FILLFACTOR |
READ |
ANY |
FOR |
READTEXT |
AS |
FOREIGN |
RECONFIGURE |
ASC |
FREETEXT |
REFERENCES |
AUTHORIZATION |
FREETEXTTABLE |
REPLICATION |
BACKUP |
FROM |
RESTORE |
BEGIN |
FULL |
RESTRICT |
BETWEEN |
FUNCTION |
RETURN |
BREAK |
GOTO |
REVERT |
BROWSE |
GRANT |
REVOKE |
BULK |
GROUP |
RIGHT |
BY |
HAVING |
ROLLBACK |
CASCADE |
HOLDLOCK |
ROWCOUNT |
CASE |
IDENTITY |
ROWGUIDCOL |
CHECK |
IDENTITY_INSERT |
RULE |
CHECKPOINT |
IDENTITYCOL |
SAVE |
CLOSE |
IF |
SCHEMA |
CLUSTERED |
IN |
SECURITYAUDIT |
COALESCE |
INDEX |
SELECT |
COLLATE |
INNER |
SEMANTICKEYPHRASETABLE |
COLUMN |
INSERT |
SEMANTICSIMILARITYDETAILSTABLE |
COMMIT |
INTERSECT |
SEMANTICSIMILARITYTABLE |
COMPUTE |
INTO |
SESSION_USER |
CONSTRAINT |
IS |
SET |
CONTAINS |
JOIN |
SETUSER |
CONTAINSTABLE |
KEY |
SHUTDOWN |
ABSOLUTE |
EXEC |
OVERLAPS |
ACTION |
EXECUTE |
PAD |
ADA |
EXISTS |
PARTIAL |
ADD |
EXTERNAL |
PASCAL |
ALL |
EXTRACT |
POSITION |
ALLOCATE |
FALSE |
PRECISION |
ALTER |
FETCH |
PREPARE |
AND |
FIRST |
PRESERVE |
ANY |
FLOAT |
PRIMARY |
ARE |
FOR |
PRIOR |
AS |
FOREIGN |
PRIVILEGES |
ASC |
FORTRAN |
PROCEDURE |
ASSERTION |
FOUND |
PUBLIC |
AT |
FROM |
READ |
AUTHORIZATION |
FULL |
REAL |
AVG |
GET |
REFERENCES |
BEGIN |
GLOBAL |
RELATIVE |
BETWEEN |
GO |
RESTRICT |
BIT |
GOTO |
REVOKE |
BIT_LENGTH |
GRANT |
RIGHT |
BOTH |
GROUP |
ROLLBACK |
BY |
HAVING |
ROWS |
CASCADE |
HOUR |
SCHEMA |
CASCADED |
IDENTITY |
SCROLL |
CASE |
IMMEDIATE |
SECOND |
CAST |
IN |
SECTION |
CATALOG |
INCLUDE |
SELECT |
CHAR |
INDEX |
SESSION |
CHAR_LENGTH |
INDICATOR |
SESSION_USER |
CHARACTER |
INITIALLY |
SET |
CHARACTER_LENGTH |
INNER |
SIZE |
CHECK |
INPUT |
SMALLINT |
CLOSE |
INSENSITIVE |
SOME |
COALESCE |
INSERT |
SPACE |
COLLATE |
INT |
SQL |
COLLATION |
INTEGER |
SQLCA |
COLUMN |
INTERSECT |
SQLCODE |
COMMIT |
INTERVAL |
SQLERROR |
CONNECT |
INTO |
SQLSTATE |
CONNECTION |
IS |
SQLWARNING |
CONSTRAINT |
ISOLATION |
SUBSTRING |
CONSTRAINTS |
JOIN |
SUM |
CONTINUE |
KEY |
SYSTEM_USER |
CONVERT |
LANGUAGE |
TABLE |
CORRESPONDING |
LAST |
TEMPORARY |
COUNT |
LEADING |
THEN |
CREATE |
LEFT |
TIME |
CROSS |
LEVEL |
TIMESTAMP |
CURRENT |
LIKE |
TIMEZONE_HOUR |
CURRENT_DATE |
LOCAL |
TIMEZONE_MINUTE |
CURRENT_TIME |
LOWER |
TO |
CURRENT_TIMESTAMP |
MATCH |
TRAILING |
CURRENT_USER |
MAX |
TRANSACTION |
CURSOR |
MIN |
TRANSLATE |
DATE |
MINUTE |
TRANSLATION |
DAY |
MODULE |
TRIM |
DEALLOCATE |
MONTH |
TRUE |
DEC |
NAMES |
UNION |
DECIMAL |
NATIONAL |
UNIQUE |
DECLARE |
NATURAL |
UNKNOWN |
DEFAULT |
NCHAR |
UPDATE |
DEFERRABLE |
NEXT |
UPPER |
DEFERRED |
NO |
USAGE |
DELETE |
NONE |
USER |
DESC |
NOT |
USING |
DESCRIBE |
NULL |
VALUE |
DESCRIPTOR |
NULLIF |
VALUES |
DIAGNOSTICS |
NUMERIC |
VARCHAR |
DISCONNECT |
OCTET_LENGTH |
VARYING |
DISTINCT |
OF |
VIEW |
DOMAIN |
ON |
WHEN |
DOUBLE |
ONLY |
WHENEVER |
DROP |
OPEN |
WHERE |
ELSE |
OPTION |
WITH |
END |
OR |
WORK |
END-EXEC |
ORDER |
WRITE |
ESCAPE |
OUTER |
YEAR |
EXCEPT |
OUTPUT |
ZONE |
EXCEPTION |
CONTINUE |
KILL |
SOME |
CONVERT |
LEFT |
STATISTICS |
CREATE |
LIKE |
SYSTEM_USER |
CROSS |
LINENO |
TABLE |
CURRENT |
LOAD |
TABLESAMPLE |
CURRENT_DATE |
MERGE |
TEXTSIZE |
CURRENT_TIME |
NATIONAL |
THEN |
CURRENT_TIMESTAMP |
NOCHECK |
TO |
CURRENT_USER |
NONCLUSTERED |
TOP |
CURSOR |
NOT |
TRAN |
DATABASE |
NULL |
TRANSACTION |
DBCC |
NULLIF |
TRIGGER |
DEALLOCATE |
OF |
TRUNCATE |
DECLARE |
OFF |
TRY_CONVERT |
DEFAULT |
OFFSETS |
TSEQUAL |
DELETE |
ON |
UNION |
DENY |
OPEN |
UNIQUE |
DESC |
OPENDATASOURCE |
UNPIVOT |
DISK |
OPENQUERY |
UPDATE |
DISTINCT |
OPENROWSET |
UPDATETEXT |
DISTRIBUTED |
OPENXML |
USE |
DOUBLE |
OPTION |
USER |
DROP |
OR |
VALUES |
DUMP |
ORDER |
VARYING |
ELSE |
OUTER |
VIEW |
END |
OVER |
WAITFOR |
ERRLVL |
PERCENT |
WHEN |
ESCAPE |
PIVOT |
WHERE |
EXCEPT |
PLAN |
WHILE |
EXEC |
PRECISION |
WITH |
EXECUTE |
PRIMARY |
WITHIN |
GROUP |
EXISTS |
PRINT |
WRITETEXT |
EXIT |
PROC {lexeme=yytext(); line=yyline; column=yycolumn; return RESERVADAS;}
{Letra} ({Letra} | {Digito})* {lexeme=yytext(); line=yyline; column=yycolumn; return IDENTIFICADORES;}
{SingleError} {lexeme=yytext(); line=yyline; column=yycolumn; return ERRORCOMMENT;}
{Multiple} {lexeme=yytext(); line=yyline; column=yycolumn; return MULTICOMMENT;}
("(-"{Digito}+")") | {Digito}+ {lexeme=yytext(); line=yyline; column=yycolumn; return INT;}
 . {line=yyline; column=yycolumn; return ERROR;}
