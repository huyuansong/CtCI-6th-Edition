package Q1_04_Palindrome_Permutation;

public class QuestionB {	

	// 边遍历边检查
	// 如果一个字符串是回文的，那么它的字符计数中至多只有一个字符出现奇数次
	public static boolean isPermutationOfPalindrome(String phrase) {
		int countOdd = 0;
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1]; // 26个字母
		for (char c : phrase.toCharArray()) {
			int x = Common.getCharNumber(c);
			if (x != -1) {
				table[x]++;
				// 在每一轮填入新元素进查找表时都统计奇数元素的个数 
				// 如果新元素个数是奇数，则countOdd加1，否则减1
				if (table[x] % 2 == 1) { 
					countOdd++;
				} else {
					countOdd--;
				}
			}
		}
		return countOdd <= 1; // 统计奇数元素的个数，最多只能有一个
	}
	
	public static void main(String[] args) {
		String pali = "Ratzs live on no evil starz";
		System.out.println(isPermutationOfPalindrome(pali));
		String pali2 = "Zeus was deified, saw Suez";
		System.out.println(isPermutationOfPalindrome(pali2));
	}


}
