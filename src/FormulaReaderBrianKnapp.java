import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Brian Knapp
 *
 */
public class FormulaReaderBrianKnapp {
	private int[][] formula;
	private int variableCount;
	private int clauseCount;
	
	FormulaReaderBrianKnapp() {
		
	}
	
	public void read(String fileName) {
		try {
			Scanner in = new Scanner(new File(fileName));
			while (in.findInLine("p cnf") == null){
                in.nextLine();
            }
			variableCount = in.nextInt();
			clauseCount = in.nextInt();
			formula = new int[clauseCount][];
			int row = 0;
			int col = 0;
			LinkedList<Integer> tempList = new LinkedList<>();
			while (in.hasNextInt()) {
			    int tempInt = in.nextInt();
			    tempList.add(tempInt);
			    if (tempInt == 0) {
			        formula[row] = new int[col];
			        row++;
			        col = 0;
				}
				else {
			        col++;
                }
			}
			in.close();

			Iterator<Integer> listIter = tempList.iterator();
			row = 0;
			col = 0;
			while (listIter.hasNext()) {
				int tempInt = listIter.next();
				if (tempInt == 0) {
				    row++;
				    col = 0;
                }
                else {
                    formula[row][col] = tempInt;
                    col++;
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
	
	public int[][] getFormula() {
		return formula;
	}
}
