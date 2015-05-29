package AST;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

abstract public class Prog{
	abstract public void genC(FileWriter arc, PrintWriter saveArc) throws IOException;
}