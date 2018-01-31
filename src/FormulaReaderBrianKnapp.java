import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Knapp
 *
 */
public class FormulaReaderBrianKnapp {
	private String[][] formula;
	private int variableCount;
	private int clauseCount;
	
	public FormulaReaderBrianKnapp() {
		
	}
	
	public void read(String fileName) {
		ArrayList<ArrayList<String>> formulaBuffer = new ArrayList<ArrayList<String>>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			boolean pcnfFound = false;
			while ((line = bufferedReader.readLine()) != null) {
				if (pcnfFound) {
					ArrayList<String> splitLine = new ArrayList<String>();
					splitLine.addAll(Arrays.asList(line.trim().split("\\s+")));
					formulaBuffer.add(splitLine);
				}
				else {
					Pattern p = Pattern.compile("^p\\s+cnf");
					Matcher m = p.matcher(line);
					pcnfFound = m.lookingAt();
					if (pcnfFound) {
						String[] splitPCNF = line.trim().split("\\s+");
						variableCount = Integer.parseInt(splitPCNF[2]);
						clauseCount = Integer.parseInt(splitPCNF[3]);
					}
				}
			}
			fileReader.close();
		}
		catch (IOException e) {
			
		}
		formula = formulaBuffer.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
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
