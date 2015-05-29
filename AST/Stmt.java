package AST;
import java.util.ArrayList;
import java.lang.String;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Stmt extends Prog{

	private ArrayList<String> 	vblist;

	public Stmt(ArrayList<String> vblist){
		this.vblist = vblist;
	}

	public void genC(FileWriter arc, PrintWriter saveArc) throws IOException{
		saveArc.printf("\tscanf(\"");

		int i = 0;
		for(i = 0; i < (vblist.size() - 1); i++){
			saveArc.printf("%%d ");
		}
		saveArc.printf("%%d");
		saveArc.printf("\",");

		for(i = 0; i < (vblist.size() - 1); i++){
			saveArc.printf(" &" + vblist.get(i) + ',');
		}
		saveArc.printf(" &" + vblist.get(i));
		saveArc.printf(");\n");
	}

}