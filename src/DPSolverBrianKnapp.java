import java.io.File;

/**
 * @author Brian Knapp
 *
 */
public class DPSolverBrianKnapp {
	
    DPSolverBrianKnapp(File filePath) {
		FormulaReaderBrianKnapp formReader = new FormulaReaderBrianKnapp();
		formReader.read(filePath);
		int variables = formReader.getVariableCount();
		int clauses = formReader.getClauseCount();
		int[][] formula = formReader.getFormula();
	}

	public void solve() {

	}

}
