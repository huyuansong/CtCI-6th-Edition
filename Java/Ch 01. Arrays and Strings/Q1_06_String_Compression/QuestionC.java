package Q1_06_String_Compression;

public class QuestionC {	
	public static String compress(String str) {
		int finalLength = countCompression(str);
		if (finalLength >= str.length()) return str;
		
		StringBuffer compressed = new StringBuffer(finalLength); // initialize capacity
		int countConsecutive = 0;
		for (int i = 0; i < str.length(); i++) {
			countConsecutive++;
			
			/* If next character is different than current, append this char to result.*/
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressed.append(str.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressed.toString();
	}

	/**
	 * 计算字符串压缩后的长度。
	 * 遍历字符串，统计连续相同字符的个数，并计算压缩后字符串的总长度。
	 * 压缩规则为：将连续相同的字符替换为该字符及其出现次数。例如，"aaabbc" 压缩后为 "a3b2c1"。
	 *
	 * @param str 待压缩的字符串
	 * @return 压缩后的字符串长度
	 */
	public static int countCompression(String str) {
		int compressedLength = 0; // 初始化压缩后字符串的长度
		int countConsecutive = 0; // 统计连续相同字符的个数

		// 遍历字符串中的每个字符
		for (int i = 0; i < str.length(); i++) {
			countConsecutive++; // 每遇到一个字符，连续计数器加一

			// 如果当前字符是最后一个字符，或者当前字符与下一个字符不同
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				// 将当前字符及其连续次数的长度加到压缩后字符串的总长度中
				compressedLength += 1 + String.valueOf(countConsecutive).length();
				countConsecutive = 0; // 重置连续计数器
			}
		}

		return compressedLength; // 返回压缩后字符串的总长度
	}	
	
	public static void main(String[] args) {
		String str = "aa";
		System.out.println(str);
		System.out.println(compress(str));
	}
}
