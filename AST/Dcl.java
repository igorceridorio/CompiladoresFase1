package AST;
import java.util.ArrayList;
import java.lang.String;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dcl extends Prog{

	private ArrayList<String> 	idlist;
	private char				type;

	public Dcl(ArrayList<String> idlist, char type){
		this.idlist = idlist;
		this.type = type;
	}

	public void genC(FileWriter arc, PrintWriter saveArc) throws IOException{
		boolean first = true;
		
		if(type == 'I'){
			saveArc.printf("\tint ");

			for(String s: idlist){
				if(first){
					saveArc.printf(s);
					first = false;
				} else
					saveArc.printf(", " + s);	
			}
			
			saveArc.printf(";\n");
		}

	}

}