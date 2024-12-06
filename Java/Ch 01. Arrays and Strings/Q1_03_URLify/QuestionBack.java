package Q1_03_URLify;

import CtCILibrary.AssortedMethods;

public class QuestionBack {
	// Assume string has sufficient free space at the end
	public static void replaceSpaces(char[] str, int trueLength) {
		// 初始化空格计数器spaceCount为0，index用于追踪替换后的索引位置
		int spaceCount = 0, index, i = 0;

		// 遍历字符串数组，直到trueLength位置，统计空格的数量
		for (i = 0; i < trueLength; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}

		// 计算替换空格后字符串的新长度，并将index设置为该新长度的最后一个字符的位置
		// 每个空格会被替换成"%20"，所以需要额外的空间来存储两个额外的字符
		index = trueLength + spaceCount * 2;

		// 如果原始字符串的实际长度小于提供的数组长度，则在trueLength位置放置终止符'\0'
		if (trueLength < str.length) str[trueLength] = '\0';

		// 从字符串的末尾开始向前遍历，逐步处理每个字符。同一个数组，两组不同逻辑的索引 i, index
		for (i = trueLength - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				// 如果当前字符是空格，则将其替换为"%20"
				str[index - 1] = '0'; // index 初始是长度，这里-1
				str[index - 2] = '2';
				str[index - 3] = '%';
				// 更新index以指向下一个需要填充的位置
				index = index - 3;
			} else {
				// 如果当前字符不是空格，则直接将其移动到新的位置。第一轮开始默认是最后一个位置 index-1
				str[index - 1] = str[i];
				// 更新index以指向下一个需要填充的位置
				index--;
			}
		}
	}

	// 不统计字符串末尾的空格，找到最后一个字符的位置
	public static int findLastCharacter(char[] str) {
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] != ' ') {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String str = "Mr John Smith    ";
		char[] arr = str.toCharArray();
		int trueLength = findLastCharacter(arr) + 1;
		replaceSpaces(arr, trueLength);	
		System.out.println("\"" + AssortedMethods.charArrayToString(arr) + "\"");
	}
}
