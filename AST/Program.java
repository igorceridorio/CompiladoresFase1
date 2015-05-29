package AST;
import java.lang.String;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Program{

	String pid;
	Body body;

	public Program(String pid, Body body){
		this.pid = pid;
		this.body = body;
	}

	public void genC() throws IOException{

		FileWriter arc = new FileWriter(pid + ".c");
		PrintWriter saveArc = new PrintWriter(arc);

		saveArc.printf("#include <stdio.h>\n");
		saveArc.printf("\n");
		saveArc.printf("int main(){\n\n");
		body.genC(arc, saveArc);
		saveArc.printf("\n\treturn 0;\n");
		saveArc.printf("}");

		arc.close();

		System.out.println("Compilation successful! " + pid + ".c file generated.");
	}

}