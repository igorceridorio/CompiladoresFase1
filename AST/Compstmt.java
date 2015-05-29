package AST;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Compstmt extends Prog{

	private ArrayList<Stmt> stmts;

	public Compstmt(ArrayList<Stmt> stmts){
		this.stmts = stmts;
	}

	public void genC(FileWriter arc, PrintWriter saveArc) throws IOException{
		for(Stmt s: stmts)
			s.genC(arc, saveArc); 
	}

}