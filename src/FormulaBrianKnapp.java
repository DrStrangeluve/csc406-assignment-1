import java.util.LinkedList;
import java.util.List;

/**
 * @author Brian Knapp
 *
 */
public class FormulaBrianKnapp {
	public int na = 0;
	public final int assignedTrue = 1;
	public final int assignedFalse = -1;
	private int[] assignmentArray;
    private int[][] formula;

    FormulaBrianKnapp() {
		
	}

	public LinkedList<Integer> read(String fileName) {
		FormulaReaderBrianKnapp formulaReaderBrianKnapp = new FormulaReaderBrianKnapp();
		formulaReaderBrianKnapp.read(fileName);
        int variableCount = formulaReaderBrianKnapp.getVariableCount();
        int clauseCount = formulaReaderBrianKnapp.getClauseCount();
		formula = formulaReaderBrianKnapp.getFormula();

        LinkedList<Integer> localFormula = new LinkedList<>();
		for (int i = 0; i < clauseCount; i++) {
            localFormula.add(i);
        }
        localFormula.add(-1);

		assignmentArray = new int[variableCount];
		return localFormula;
	}

	public boolean isFormulaEmpty(LinkedList<Integer> inFormula) {
        return (inFormula.getFirst() == -1);
	}

	private boolean isClauseEmpty(int clauseNo) {
        int[] clause = formula[clauseNo];
        for (int variable : clause) {
            if (assignmentArray[Math.abs(variable)-1] == na) {
                return false;
            }
        }
        return true;
	}

	public boolean hasEmptyClause(LinkedList<Integer> inFormula) {
        List<Integer> tempList = separateClauses(inFormula);
        for (int i : tempList) {
            if (!isClauseEmpty(i)) {
                return false;
            }
        }
        return true;
	}

	private List<Integer> separateClauses(LinkedList<Integer> inFormula) {
        int indexOfFirstSeparator = inFormula.indexOf(-1);
        return inFormula.subList(0, indexOfFirstSeparator);
    }


	public int firstAvailable() {
	    if (contains(assignmentArray, na)){
	        return findFirstOrLastOccurence(assignmentArray, na, true);
        }
        else {
            return findFirstOrLastOccurence(assignmentArray, assignedTrue, false);
        }
	}

	public void assign(int variable, int assignment, LinkedList<Integer> inFormula) {
        assignmentArray[variable] = assignment;
        List<Integer> tempList = separateClauses(inFormula);
        List<Integer> unsatisfiedSublist = tempList.subList(0, tempList.size());
        LinkedList<Integer> satisfiedSublist = new LinkedList<>();

        for (int i : unsatisfiedSublist) {
            if (isClauseSatisfied(i)) {
                satisfiedSublist.add(0, i);
            }
        }
        for (int i : satisfiedSublist) {
            int indexToRemove = unsatisfiedSublist.indexOf(i);
            unsatisfiedSublist.remove(indexToRemove);
        }
        if (satisfiedSublist.size() > 0) {
            satisfiedSublist.add(0,-1);
            unsatisfiedSublist.addAll(satisfiedSublist);
        }
	}

	private boolean isClauseSatisfied(int cno) {
		int[] clause = formula[cno];
		boolean satisfied;
		for (int variable : clause) {
		    if (variable < 0) {
                satisfied = (assignmentArray[Math.abs(variable) - 1] == assignedFalse);
            }
            else {
                satisfied = (assignmentArray[Math.abs(variable) - 1] == assignedTrue);
            }
            if (satisfied) {
		        return true;
            }
        }
        return false;
	}

	public void printAssignment() {
		System.out.print("\nAssignment of Boolean values to variables:");
		int variable = 1;
		for (int i : assignmentArray) {
            System.out.printf("\t%d: %d", variable, i);
            variable++;
        }
	}

	public void printFormula() {
        System.out.print("\nFormula Representation");
        for (int i[]: formula) {
            System.out.print("\n");
            for (int j: i) {
                System.out.printf("\t%d", j);
            }
        }
	}

	public void print(LinkedList<Integer> inFormula) {
        System.out.print("\nFormula:");
        for (int i : inFormula) {
            System.out.printf("\t%d", i);
        }
	}

    private boolean contains(int[] inArray, int toFind) {
        for (int i : inArray) {
            if (i == toFind) {
                return true;
            }
        }
        return false;
    }

	private int findFirstOrLastOccurence(int[] inArray, int toFind, boolean findFirst) {
	    int arrayLength = inArray.length;
	    int first = -1;
	    int last = -1;
	    for (int i = 0; i < arrayLength; i++) {
	        if (inArray[i] != toFind) {
	            continue;
            }
            if (first == -1) {
	            first = i;
            }
            last = i;
        }
        if (findFirst) {
	        return first;
        }
        else {
	        return last;
        }
    }
}
