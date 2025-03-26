package Q1_09_String_Rotation;

public class Question {
	public static boolean isSubstring(String big, String small) {
		if (big.indexOf(small) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
/**
 * 判断字符串s2是否是字符串s1的旋转版本。
 * 
 * 参数:
 * String s1 - 原始字符串
 * String s2 - 需要判断的目标字符串
 * 
 * 返回值:
 * boolean - 如果s2是s1的旋转版本，则返回true；否则返回false。
 */
public static boolean isRotation(String s1, String s2) {
    int len = s1.length();
    /* 检查s1和s2是否长度相等且非空 */
    if (len == s2.length() && len > 0) { 
        /* 将s1与自身拼接，形成新的字符串s1s1 */
        String s1s1 = s1 + s1;
        return isSubstring(s1s1, s2); /* 判断s2是否为s1s1的子串 */
    }
    return false; /* 如果长度不相等或为空，直接返回false */
}
	
	public static void main(String[] args) {
		String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean is_rotation = isRotation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + is_rotation);
		}
	}

}
