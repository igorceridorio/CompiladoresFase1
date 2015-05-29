import AST.*;
import java.lang.String;
import java.util.ArrayList;

public class Compiler{

	private char 	token;
	private int  	tokenPos;
	private char	[]input;

	public Program compile(char []p_input){
		input = p_input;
		tokenPos = 0;
		nextToken();

		Program e = prog();
		if(tokenPos != input.length)
			error("Compilation error. Input didn't reach the end");

		return e;
	}

	//prog ::= P pid ';' body '.'
	private Program prog(){
		Body body = null;
		String pid = "";

		if(token == 'P'){

			nextToken();
			pid = pid();

			if(token == ';'){

				nextToken();
				body = body();

				if(token != '.')
					error("Compilation error. Expecting '.'");

			} else
				error("Compilation error. Expecting ';'");

		} else
			error("Compilation error. Expecting 'P'");

		return new Program(pid, body);
	}

	//body ::= [dclpart] compstmt
	private Body body(){
		Dclpart dclpart = null;
		Compstmt compstmt = null;

		if(token == 'V'){
			dclpart = dclpart();
		}
		compstmt = compstmt();

		if(dclpart != null)
			return new Body(dclpart, compstmt);

		return new Body(compstmt);
	}

	//dclpart ::= V dcls
	private Dclpart dclpart(){
		ArrayList<Dcl> dcls = new ArrayList<Dcl>();

		nextToken();
		dcls = dcls();

		return new Dclpart(dcls);
	}

	//dcls ::= dcl {dcl}
	private ArrayList<Dcl> dcls(){
		ArrayList<Dcl> dcls = new ArrayList<Dcl>();

		dcls.add(dcl());

		while(token != 'B'){
			dcls.add(dcl());
		}

		return dcls;
	}

	//dcl ::= idlist ':' type ';'
	private Dcl dcl(){
		Dcl dcl = null;
		ArrayList<String> idlist = new ArrayList<String>();
		char type = '\0';

		idlist = idlist();

		if(token == ':'){
			nextToken();
			type = type();
			
			nextToken();
			if(token != ';')
				error("Compilation error. Expecting ';'");
			else
				nextToken();

		} else 
			error("Compilation error. Expecting ':'");

		return new Dcl(idlist, type);
	}

	//idlist ::= id {',' id}
	private ArrayList<String> idlist(){
		ArrayList<String> idlist = new ArrayList<String>();

		idlist.add(id());

		while(token == ','){
			nextToken();
			idlist.add(id());
		}
		nextToken();

		return idlist;
	}

	//type ::= stdtype
	private char type(){
		return stdtype();
	}

	//stdtype ::= I
	private char stdtype(){
		if(token != 'I')
			error("Compilation error. Unknown type declared");

		return token;
	}

	//compstmt ::= B stmts E
	private Compstmt compstmt(){
		ArrayList<Stmt> stmts = new ArrayList<Stmt>();

		if(token == 'B'){
			
			nextToken();
			stmts = stmts();

			if(token == 'E')
				nextToken();
			else
				error("Compilation error. Expecting 'E'");

		} else
			error("Compilation error. Expecting 'B'");

		return new Compstmt(stmts);
	}

	//stmts ::= stmt {';' stmt} ';'
	private ArrayList<Stmt> stmts(){
		ArrayList<Stmt> stmts = new ArrayList<Stmt>();

		stmts.add(stmt());
		
		if(token == ';'){
			nextToken();

			while(token != 'E'){
				stmts.add(stmt());				

				if(token != ';')
					error("Compilation error. Expecting ';'");
				else if(token == ';')
					nextToken();
			}

		} else
			error("Compilation error. Expecting ';'");

		return stmts;
	}

	//stmt ::= L '(' vblist ')'
	private Stmt stmt(){
		ArrayList<String> vblist = new ArrayList<String>();

		if(token == 'L'){
			nextToken();

			if(token == '('){
				nextToken();
				vblist = vblist();

				if(token == ')')
					nextToken();
				else
					error("Compilation error. Expecting ')'");

			} else
				error("Compilation error. Expecting '('");

		} else
			error("Compilation error. Expecting 'L'");

		return new Stmt(vblist);
	}

	//vblist ::= vbl {',' vbl}
	private ArrayList<String> vblist(){
		ArrayList<String> vblist = new ArrayList<String>();

		vblist.add(vbl());

		while(token == ','){
			nextToken();			
			vblist.add(vbl());
		}

		return vblist;
	}

	//vbl ::= id
	private String vbl(){		
		return id();
	}

	//id ::= letter {letter | digit}
	private String id(){
		String id = "";

		id += letter();
		next();

		while(Character.isLetter(token) || Character.isDigit(token)){
			if(Character.isLetter(token))
				id += letter();
			else if(Character.isDigit(token))
				id += (char)digit();

			next();
		}

		return id;

	}

	//pid ::= letter {letter | digit}
	private String pid(){
		String pid = "";

		pid += letter();
		next();

		while(Character.isLetter(token) || Character.isDigit(token)){
			if(Character.isLetter(token))
				pid += letter();
			else if(Character.isDigit(token))
				pid += (char)digit();

			next();
		}

		return pid;
		
	}

	private int digit(){
		int n = 0;

		if(Character.isDigit(token)){
			n = token;
		} else {
			error("Compilation error. Expecting a digit as input");
		}

		return n;
	}

	private char letter(){
		char c = '\0';

		if(Character.isLetter(token)){
			c = token;
		} else {
			error("Compilation error. Expecting a letter as input");
		}

		return c;
	}

	private void next() {		
		if (tokenPos < input.length) {
			token = input[tokenPos];
			tokenPos++;
		}
    }	

	private void nextToken() {
    	while (tokenPos < input.length && input[tokenPos] == ' ')
    		tokenPos++;

	if(tokenPos < input.length && input[tokenPos] == '{'){
		while(tokenPos < input.length && input[tokenPos] != '}')
			tokenPos++;
		tokenPos++;
		
		while (tokenPos < input.length && input[tokenPos] == ' ')
   			tokenPos++;
	}
	
	if (tokenPos < input.length) {
		token = input[tokenPos];
		tokenPos++;
	} else {
		token = '\0';
	}
    }

	private void error(String errorMsg) {
    	if ( tokenPos == 0 ) 
          tokenPos = 1; 
        else 
          if ( tokenPos >= input.length )
            tokenPos = input.length;
        
        String strInput = new String( input, tokenPos - 1, input.length - tokenPos + 1 );
        String strError = "Error at \"" + strInput + "\"";
        System.out.println( strError );
        System.out.println( errorMsg );
        throw new RuntimeException(strError);
    }

}
