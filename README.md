# CompiladoresFase1

Coding Project - First Phase. Compilers course - 2015. Federal University of São Carlos - UFSCar Sorocaba.

### Developers: 

- Igor Felipe Ferreira Ceridório
- Daniel Ramos Miola

### Implemented Grammar:
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
