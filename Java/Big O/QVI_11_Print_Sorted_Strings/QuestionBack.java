package QVI_11_Print_Sorted_Strings;

// 定义一个名为QuestionBack的公共类
public class QuestionBack {

	// 定义一个静态变量numChars，表示字母表中的字符数（26个英文字母）
	public static int numChars = 26;

	// 这是一个重载的方法，用于打印指定长度的所有按字典序排列的字符串
	public static void printSortedStrings(int remaining) {
		// 调用另一个printSortedStrings方法，初始前缀为空字符串
		printSortedStrings(remaining, "");
	}

	// 这个方法递归地构建并打印所有按字典序排列的字符串
	public static void printSortedStrings(int remaining, String prefix) {
		// 如果剩余需要添加的字符数量为0，则检查当前前缀是否按字典序排列
		if (remaining == 0) {
			// 检查prefix是否按字典序排列，如果是则打印出来
			if (isInOrder(prefix)) {
				System.out.println(prefix);
			}
		} else {
			// 否则，遍历每个可能的字符（从'a'到'z'）
			for (int i = 0; i < numChars; i++) {
				// 获取第i个字母
				char c = ithLetter(i);
				// 递归调用printSortedStrings方法，增加新的字符到前缀中
				printSortedStrings(remaining - 1, prefix + c);
			}
		}
	}

	// 这个方法检查给定的字符串是否按字典序排列
	public static boolean isInOrder(String s) {
		// 初始化布尔变量isInOrder为true
		boolean isInOrder = true;
		// 遍历字符串的每个字符（从第二个开始）
		for (int i = 1; i < s.length(); i++) {
			// 获取前一个字符和当前字符在字母表中的位置
			int prev = ithLetter(s.charAt(i - 1));
			int curr = ithLetter(s.charAt(i));
			// 如果前一个字符的位置大于当前字符的位置，则设置isInOrder为false
			if (prev > curr) {
				isInOrder = false;
			}
		}
		// 返回isInOrder的结果
		return isInOrder;
	}

	// 这个方法返回第i个字母（从'a'开始计数）
	public static char ithLetter(int i) {
		// 将整数i转换为对应的字符，并返回
		return (char) (((int) 'a') + i);
	}

	// 主方法，程序的入口点
	public static void main(String[] args) {
		// 调用printSortedStrings方法，打印长度为5的所有按字典序排列的字符串
		printSortedStrings(5);
	}

}