package Q1_05_One_Away;

public class QuestionA {

	public static boolean oneEditReplace(String s1, String s2) {
		boolean foundDifference = false;
		for (int i = 0; i < s1.length(); i++) {
			// If there is a difference, make sure that this is the first difference found
			// If there is a second difference, return false
			if (s1.charAt(i) != s2.charAt(i)) { 
				if (foundDifference) { 
					return false;
				}
				
				foundDifference = true; // 找到第一个不同，则标记为true
			}
		}
		return true;
	}
	
	/* Check if you can insert a character into s1 to make s2. s2更长*/
	public static boolean oneEditInsert(String s1, String s2) {
		int index1 = 0;
		int index2 = 0;
		// 两个指针分别指向两个字符串的开头，如果两个字符不同，只移动index2，如果相同，两个指针同时移动
		while (index2 < s2.length() && index1 < s1.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				if (index1 != index2) {
					return false;
				}		
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}	
	
	public static boolean oneEditAway(String first, String second) {
		if (first.length() == second.length()) {
			return oneEditReplace(first, second);
		} else if (first.length() + 1 == second.length()) {
			return oneEditInsert(first, second);
		} else if (first.length() - 1 == second.length()) {
			return oneEditInsert(second, first);
		} 
		return false;
	}
	
	public static void main(String[] args) {
		String a = "pse";
		String b = "pale";
		boolean isOneEdit = oneEditAway(a, b);
		System.out.println(a + ", " + b + ": " + isOneEdit);
	}

}
