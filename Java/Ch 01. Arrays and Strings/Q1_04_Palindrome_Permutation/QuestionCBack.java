package Q1_04_Palindrome_Permutation;


public class QuestionCBack {
	/* Toggle the ith bit in the integer. */
	public static int toggle(int bitVector, int index) {
		if (index < 0) return bitVector;

		int mask = 1 << index; // 0000 0011
		if ((bitVector & mask) == 0) { // 0000 0010
			bitVector |= mask; // 将 0  位翻转为 1
		} else { // 只要两次出现了相同的位，则结果就不为 0
			bitVector &= ~mask; // 将相同的位翻转为 0
		}
		return bitVector;
	}

	
	/* Create bit vector for string. For each letter with value i,
	 * toggle the ith bit. */
	public static int createBitVector(String phrase) {
		int bitVector = 0;
		for (char c : phrase.toCharArray()) {
			int x = Common.getCharNumber(c);
			bitVector = toggle(bitVector, x);
		}
		return bitVector;
	}
	
	/* Check that at most one bit is set by subtracting one from the 
	 * integer and ANDing it with the original integer. */
	public static boolean checkAtMostOneBitSet(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0; // bitVector 只有一位为 1 时结果才为 0，很优雅
	}
	
	public static boolean isPermutationOfPalindrome(String phrase) {
		int bitVector = createBitVector(phrase);
		return checkAtMostOneBitSet(bitVector);
	}
	
	public static void main(String[] args) {
		String pali = "Rats live on no evil star";
		System.out.println(isPermutationOfPalindrome(pali));
	}
}
