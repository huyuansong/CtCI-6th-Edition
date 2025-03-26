package Q1_06_String_Compression;

public class QuestionABack {
/**
 * 将字符串进行压缩。压缩方式为：将连续相同的字符替换为该字符和其出现的次数。
 * 如果压缩后的字符串长度不小于原始字符串，则返回原始字符串。
 * 
 * @param str 需要压缩的字符串
 * @return 压缩后的字符串，或者如果压缩无益则返回原始字符串
 */
public static String compressBad(String str) {
    // 初始化一个空字符串，用于存储压缩后的结果
    String compressedString = "";
    // 初始化计数器，用于记录连续相同字符的数量
    int countConsecutive = 0;
    
    // 遍历输入字符串中的每个字符
    for (int i = 0; i < str.length(); i++) {
        // 每遇到一个字符，计数器加1
        countConsecutive++;
        
        /* 
         * 如果当前字符是最后一个字符，或者下一个字符与当前字符不同，
         * 则将当前字符及其计数添加到压缩结果中。
         */
        if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
            // 将当前字符及其连续出现的次数追加到压缩字符串中
            compressedString += "" + str.charAt(i) + countConsecutive;
            // 重置计数器
            countConsecutive = 0;
        }
    }
    
    // 如果压缩后的字符串长度小于原字符串，则返回压缩后的字符串；否则返回原字符串
    return compressedString.length() < str.length() ? compressedString : str;
}

}