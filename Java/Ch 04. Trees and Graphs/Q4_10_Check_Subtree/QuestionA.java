package Q4_10_Check_Subtree;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;
/**
 * 	总结：
		核心思想：通过前序遍历将树转换为字符串表示（包括空节点），然后检查子树的字符串是否为大树字符串的子串。
		时间复杂度：O(M + N)，其中 M 和 N 分别是两棵树的节点数量。需要遍历两棵树并进行字符串匹配。
		空间复杂度：O(M + N)，需要额外空间存储两棵树的前序遍历字符串。
	关键点：
		前序遍历：通过前序遍历将树转换为字符串表示，便于后续的子串匹配。
		空节点标识：在字符串中使用 "X" 标识空节点，确保子树匹配的准确性。
 */
public class QuestionA {

    /**
     * 检查 t2 是否为 t1 的子树。
     *
     * @param t1 树 t1 的根节点
     * @param t2 树 t2 的根节点
     * @return 如果 t2 是 t1 的子树，则返回 true；否则返回 false
     */
    public static boolean containsTree(TreeNode t1, TreeNode t2) {
        StringBuilder string1 = new StringBuilder(); // 用于存储树 t1 的前序遍历字符串
        StringBuilder string2 = new StringBuilder(); // 用于存储树 t2 的前序遍历字符串

        getOrderString(t1, string1); // 获取树 t1 的前序遍历字符串
        getOrderString(t2, string2); // 获取树 t2 的前序遍历字符串

        // 如果 string2 是 string1 的子串，则 t2 是 t1 的子树
        return string1.indexOf(string2.toString()) != -1;
    }

    /**
     * 获取树的前序遍历字符串表示（包括空节点）。
     *
     * @param node 当前节点
     * @param sb   存储结果的字符串构建器
     */
    public static void getOrderString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("X"); // 如果当前节点为空，添加空节点标识符 "X"
            return;
        }
        sb.append(node.data); // 添加当前节点的值
        getOrderString(node.left, sb); // 递归处理左子树
        getOrderString(node.right, sb); // 递归处理右子树
    }

    /**
     * 主函数：测试检查子树的功能。
     */
    public static void main(String[] args) {
        // 测试用例 1：t2 是 t1 的子树
        int[] array1 = {1, 2, 1, 3, 1, 1, 5}; // 定义树 t1 的数组表示
        int[] array2 = {2, 3, 1}; // 定义树 t2 的数组表示

        TreeNode t1 = AssortedMethods.createTreeFromArray(array1); // 根据数组创建树 t1
        TreeNode t2 = AssortedMethods.createTreeFromArray(array2); // 根据数组创建树 t2

        if (containsTree(t1, t2)) { // 检查 t2 是否为 t1 的子树
            System.out.println("t2 is a subtree of t1"); // 如果是，则输出相应信息
        } else {
            System.out.println("t2 is not a subtree of t1"); // 否则输出相应信息
        }

        // 测试用例 2：t4 不是 t3 的子树
        int[] array3 = {1, 2, 3}; // 定义树 t3 的数组表示

        TreeNode t3 = AssortedMethods.createTreeFromArray(array1); // 根据数组创建树 t3
        TreeNode t4 = AssortedMethods.createTreeFromArray(array3); // 根据数组创建树 t4

        if (containsTree(t3, t4)) { // 检查 t4 是否为 t3 的子树
            System.out.println("t4 is a subtree of t3"); // 如果是，则输出相应信息
        } else {
            System.out.println("t4 is not a subtree of t3"); // 否则输出相应信息
        }
    }
}