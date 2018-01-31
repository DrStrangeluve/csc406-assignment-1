import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Brian Knapp
 *
 */
public class FormulaReaderBrianKnapp {
	private String[][] formula;
	private int variableCount;
	private int clauseCount;
	
	FormulaReaderBrianKnapp() {
		
	}
	
	public void read(File fileName) {
		try {
			boolean foundPCNF = false;
			Scanner in = new Scanner(fileName);
			int counter = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (foundPCNF) {
					formula[counter] = line.trim().split("\\s+");
					counter++;
				}
				else {
					if (in.findInLine("p cnf") != null) {
						foundPCNF = true;
						String[] splitPCNF = in.nextLine().trim().split("\\s+");
						variableCount = Integer.parseInt(splitPCNF[0]);
						clauseCount = Integer.parseInt(splitPCNF[1]);
						formula = new String[clauseCount][];
					}
				}
			}
		}
		catch (FileNotFoundException e){
				e.printStackTrace();
		}
	}
	
	public int getVariableCount() {
		return variableCount;
	}
	
	public int getClauseCount() {
		return clauseCount;
	}
	
	public String[][] getFormula() {
		return formula;
	}
}
