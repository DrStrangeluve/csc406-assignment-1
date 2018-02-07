import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * @author Brian Knapp
 *
 */
public class DPSolverBrianKnapp {
    public FormulaBrianKnapp formulaBrianKnapp;
    private LinkedList<Integer> formula;
	
    DPSolverBrianKnapp(String fileName) {
		formulaBrianKnapp = new FormulaBrianKnapp();
        read(fileName);
	}

	private void read(String fileName) {
       formulaBrianKnapp.read(fileName);
       formula = formulaBrianKnapp.localFormula;
       formulaBrianKnapp.separateClauses();
    }

	public void solve(FormulaBrianKnapp f) {
        f.printFormula();
        if (dpSolver(formula)) {
            System.out.print("\nFormula Satisfiable");
            f.printAssignment();
            f.print(formula);
        }
        else {
            System.out.print("\nFormula Unsatisfiable");
            f.printAssignment();
            f.print(formula);
        }
	}

	private boolean dpSolver(LinkedList<Integer> f) {
        formulaBrianKnapp.printAssignment();
        formulaBrianKnapp.print(f);
        formulaBrianKnapp.separateClauses();
        if (formulaBrianKnapp.isFormulaEmpty()){
            return true;
        }
        else if (formulaBrianKnapp.hasEmptyClause()){
            return false;
        }
        else {
            int variable = formulaBrianKnapp.firstAvailable();
            formulaBrianKnapp.assign(variable, formulaBrianKnapp.assignedTrue);
            if (dpSolver(f)) {
                return true;
            }
            else {
                f.removeFirstOccurrence(-1);
                formulaBrianKnapp.assign(variable, formulaBrianKnapp.assignedFalse);
                if (dpSolver(f)) {
                    return true;
                }
                else {
                    f.removeFirstOccurrence(-1);
                    formulaBrianKnapp.assign(variable, formulaBrianKnapp.na);
                    return false;
                }
            }
        }
    }

}
