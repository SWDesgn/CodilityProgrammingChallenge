
public class CodilityAssessment {

	public static void main(String[] args) {
		//test function with A=6, B=4, C=1
		//returns "aabaabababc"
		System.out.println(solution(6, 4, 1));
	}

	private static String solution(int A, int B, int C) {
		// start with an empty string
		String initialString = "";
		// processing is done in recursive function "process"
		String result = process(initialString, A, B, C);
		return result;
	}

	private static String process(String previousOutput, int availableA, int availableB, int availableC) {
		// if no letter available return result
		if (!isLetterAvailable(availableA, availableB, availableC)) {
			return previousOutput;
		}
		// get the next highest available letter(according to availability)
		String nextLetter = getNextLetter(availableA, availableB, availableC);
		// to be safe, check that we have a letter
		if (isLetterEmpty(nextLetter)) {
			return previousOutput;
		}
		// if not diverse, choose another letter
		if (!isDiverse(previousOutput + nextLetter)) {
			nextLetter = getAlternativeLetter(nextLetter, availableA, availableB, availableC);
			// to be safe check that we have a letter
			if (isLetterEmpty(nextLetter)) {
				return previousOutput;
			}
		}
		// check that the letter being used conserves Diversity
		if (isDiverse(previousOutput + nextLetter)) {
			// add the letter to the output string
			previousOutput = previousOutput + nextLetter;
			// don't forget to decrement the counter of the letter used
			if (nextLetter.equals("a")) {
				availableA--;
			} else if (nextLetter.equals("b")) {
				availableB--;
			} else if (nextLetter.equals("c")) {
				availableC--;
			}
			// next iteration
			previousOutput = process(previousOutput, availableA, availableB, availableC);
		}

		return previousOutput;
	}

	private static boolean isDiverse(String s) {
		if (s.contains("aaa")) {
			return false;
		}
		if (s.contains("bbb")) {
			return false;
		}
		if (s.contains("ccc")) {
			return false;
		}
		return true;
	}

	private static String getNextLetter(int A, int B, int C) {
		if (A == 0 && B == 0 && C == 0) {
			return "";
		}
		if (A > B && A > C) {
			return "a";
		}
		if (B > A && B > C) {
			return "b";
		}
		if (C > A && C > B) {
			return "c";
		}
		if (A == B && A == C) {
			return "a";
		}

		if (A == B) {
			return "a";
		} else if (B == C) {
			return "b";
		} else if (A == C) {
			return "a";
		}
		return "";
	}

	private static String getAlternativeLetter(String letter, int A, int B, int C) {
		if (A == 0 && B == 0 && C == 0) {
			return "";
		}

		if (letter.equals("a")) {
			if (B > 0) {
				return "b";
			}
			if (C > 0) {
				return "c";
			}
		} else if (letter.equals("b")) {
			if (C > 0) {
				return "c";
			}
			if (A > 0) {
				return "a";
			}

		} else if (letter.equals("c")) {
			if (A > 0) {
				return "a";
			}
			if (B > 0) {
				return "b";
			}
		}

		return "";
	}

	private static boolean isLetterAvailable(int A, int B, int C) {
		if (A <= 0 && B <= 0 && C <= 0) {
			return false;
		}
		return true;
	}

	private static boolean isLetterEmpty(String letter) {
		if (!letter.equals("")) {
			return false;
		}
		return true;
	}

}
