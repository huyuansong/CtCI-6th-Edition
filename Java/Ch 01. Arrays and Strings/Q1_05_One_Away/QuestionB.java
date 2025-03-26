package Q1_05_One_Away;

public class QuestionB {	
/**
 * 判断两个字符串是否可以通过一次编辑（插入、删除或替换一个字符）变得相同。
 * 
 * @param first  第一个字符串
 * @param second 第二个字符串
 * @return 如果两个字符串可以通过一次编辑变得相同，则返回 true；否则返回 false。
 */
public static boolean oneEditAway(String first, String second) {
    // 检查两个字符串的长度差异。如果长度差超过1，则不可能通过一次编辑变得相同，直接返回 false。
    if (Math.abs(first.length() - second.length()) > 1) {
        return false;
    }

    // 确定较短的字符串和较长的字符串，便于后续统一处理。
    String s1 = first.length() < second.length() ? first : second;
    String s2 = first.length() < second.length() ? second : first;

    int index1 = 0; // 较短字符串的索引
    int index2 = 0; // 较长字符串的索引
    boolean foundDifference = false; // 标记是否已经发现过一次差异

    // 遍历两个字符串，直到其中一个字符串结束。
    while (index2 < s2.length() && index1 < s1.length()) {
        // 如果当前字符不相等，则进行以下处理：
        if (s1.charAt(index1) != s2.charAt(index2)) {
            // 如果之前已经发现过一次差异，则说明需要两次或更多次编辑，返回 false。
            if (foundDifference) return false;

            // 标记已经发现了一次差异。
            foundDifference = true;

            // 如果两个字符串长度相等，则认为是替换操作，同时移动较短字符串的指针。
            if (s1.length() == s2.length()) {
                index1++;
            }
        } else {
            // 如果当前字符相等，则同时移动较短字符串和较长字符串的指针。
            index1++;
        }

        // 无论字符是否相等，较长字符串的指针始终向前移动。
        index2++;
    }

    // 如果遍历结束后没有发现超过一次的差异，则返回 true。
    return true;
}
	
	
	
	public static void main(String[] args) {
		String a = "palee";
		String b = "pale";
		boolean isOneEdit1 = oneEditAway(a, b);
		System.out.println(a + ", " + b + ": " + isOneEdit1);

		String c = "pale";
		String d = "pkle";
		boolean isOneEdit2 = oneEditAway(c, d);
		System.out.println(c + ", " + d + ": " + isOneEdit2);
	}

}
