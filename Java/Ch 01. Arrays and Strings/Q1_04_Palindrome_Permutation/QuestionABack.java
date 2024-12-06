package Q1_04_Palindrome_Permutation;

public class QuestionABack {
	public static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd = false;
		for (int count : table) {
			if (count % 2 == 1) {
				if (foundOdd) { // 先检查（本轮发现一个奇数，之前已经有一个奇数，则退出），否则再做本轮设置
					return false;
				}
				foundOdd = true;
			}
		}
		return true; //循环结束了没有中途退出，就是没发现两个以上奇数
	}
	
	public static boolean isPermutationOfPalindrome(String phrase) {
		int[] table = Common.buildCharFrequencyTable(phrase);
		return checkMaxOneOdd(table);
	}
	
	public static void main(String[] args) {
		String pali = "Rats live on no evil star";
		System.out.println(isPermutationOfPalindrome(pali));
	}


}
