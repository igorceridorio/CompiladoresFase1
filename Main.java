import AST.*;
import java.io.IOException;


public class Main{

	public static void main(String []args) throws IOException{

		//Examples analyzed

		// char []input = "P exemplo01ErroLexico; B L(a) {#} E.".toCharArray();
		// char []input = "P exemplo02Correto; B L(a); {#} E.".toCharArray();
		// char []input = "P exemplo03Correto; B L(a); L(a); {#} E.".toCharArray();
		// char []input = "P exemplo04ErroLexico; B L(a); L(a) {#} E.".toCharArray();
		// char []input = "P 1exemplo05ErroLexico; B L(a); L(a) {#} E.".toCharArray();
		// char []input = "P exemplo06Correto; B L(a, b, c); E.".toCharArray();
		// char []input = "P exemplo07ErroLexico; B L(a, b, c,); E.".toCharArray();
		// char []input = "P exemplo08Correto; V a : I; B L(a); E.".toCharArray();
		// char []input = "P exemplo09Correto; V a : I; b : I; B L(a); E.".toCharArray();
		// char []input = "P exemplo10Correto; V a, b, c : I; B L(a); E.".toCharArray();
		// char []input = "P exemplo12ErroLexico; V a, b, c : I; B L(a); L(b, c); L(c) E.".toCharArray();
		char []input = "P exemplo11Correto; V a, b, c : I; B L(a); L(b, c); L(c); E.".toCharArray();
		
		Compiler compiler = new Compiler();

		Program program = compiler.compile(input);
		program.genC();
	}

}