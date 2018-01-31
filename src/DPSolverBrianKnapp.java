/**
 * 
 */

/**
 * @author Brian Knapp
 *
 */
public class DPSolverBrianKnapp {
	
	public DPSolverBrianKnapp(String filePath) {
		FormulaReaderBrianKnapp formReader = new FormulaReaderBrianKnapp();
		formReader.read(filePath);
		int variables = formReader.getVariableCount();
		int clauses = formReader.getClauseCount();
		String[][] formula = formReader.getFormula();
		}

	public void solve() {
		//TODO
	}

}
