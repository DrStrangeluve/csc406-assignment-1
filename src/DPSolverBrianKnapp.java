import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * @author Brian Knapp
 *
 */
public class DPSolverBrianKnapp {
    public FormulaBrianKnapp formulaBrianKnapp;
    private LinkedList<Integer> formula;
    private LinkedList<Integer> finalFormula;
	
    DPSolverBrianKnapp(String fileName) {
		formulaBrianKnapp = new FormulaBrianKnapp();
        read(fileName);
	}

	private void read(String fileName) {
       formula = formulaBrianKnapp.read(fileName);
       finalFormula = new LinkedList<>();
       finalFormula.addAll(formula);
    }

	public void solve(FormulaBrianKnapp f) {
        f.printFormula();
        if (dpSolver(formula)) {
            System.out.print("\nFormula Satisfiable");
            f.printAssignment();
            f.print(finalFormula);
        }
        else {
            System.out.print("\nFormula Unsatisfiable");
            f.printAssignment();
            f.print(finalFormula);
        }
	}

	private boolean dpSolver(LinkedList<Integer> f) {
//        formulaBrianKnapp.printAssignment();
//        formulaBrianKnapp.print(f);
        finalFormula = f;
        if (formulaBrianKnapp.isFormulaEmpty(f)){
            return true;
        }
        else if (formulaBrianKnapp.hasEmptyClause(f)){
            return false;
        }
        else {
            LinkedList<Integer> recursiveFormula = new LinkedList<>();
            recursiveFormula.addAll(f);
            int variable = formulaBrianKnapp.firstAvailable();
            formulaBrianKnapp.assign(variable, formulaBrianKnapp.assignedTrue, recursiveFormula);
            if (dpSolver(recursiveFormula)) {
                return true;
            }
            else {
                recursiveFormula.clear();
                recursiveFormula.addAll(f);
                formulaBrianKnapp.assign(variable, formulaBrianKnapp.assignedFalse, recursiveFormula);
                if (dpSolver(recursiveFormula)) {
                    return true;
                }
                else {
                    recursiveFormula.clear();
                    recursiveFormula.addAll(f);
                    formulaBrianKnapp.assign(variable, formulaBrianKnapp.na, recursiveFormula);
                    return false;
                }
            }
        }
    }

}
