# CompiladoresFase1

Projeto de Implementação - Primeira Fase. Disciplina de Compiladores.

### Desenvolvedores: 

408611 - Igor Felipe Ferreira Ceridorio.

438340 - Daniel Ramos Miola.

### Gramática implementada:
```
	- prog ::= P pid ';' body '.'
	
	- body ::= [dclpart] compstmt
	
	- dclpart ::= V dcls
	
	- dcls ::= dcl {dcl}
	
	- dcl ::= idlist ':' type ';'
	
	- idlist ::= id {',' id}
	
	- type ::= stdtype
	
	- stdtype ::= I
	
	- compstmt ::= B stmts E
	
	- stmts ::= stmt {';' stmt} ';'
	
	- stmt ::= L '(' vblist ')'
	
	- vblist ::= vbl {',' vbl}
	
	- vbl ::= id
	
	- id ::= letter {letter | digit}
	
	- pid ::= letter {letter | digit}
```
