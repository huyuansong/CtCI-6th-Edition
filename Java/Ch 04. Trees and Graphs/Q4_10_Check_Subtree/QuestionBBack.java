package Q4_10_Check_Subtree;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;
/**
 *  总结：
        核心思想：通过递归检查大树是否包含子树。首先在大树中查找与子树根节点值相同的节点，然后递归比较两棵树的结构是否完全匹配。
        时间复杂度：O(M * N)，其中 M 和 N 分别是两棵树的节点数量。需要遍历大树并在每个可能的匹配点递归比较子树。
        空间复杂度：O(min(H1, H2))，其中 H1 和 H2 分别是两棵树的高度。递归调用栈的空间复杂度取决于树的高度。
    关键点：
        递归匹配：通过递归比较两棵树的结构，确保子树完全匹配。
        空树处理：空树是任何树的子树，因此需要特殊处理。
 */
public class QuestionBBack {

    /**
     * 检查 t2 是否为 t1 的子树。
     *
     * @param t1 树 t1 的根节点
     * @param t2 树 t2 的根节点
     * @return 如果 t2 是 t1 的子树，则返回 true；否则返回 false
     */
    public static boolean containsTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true; // 空树是任何树的子树
        }
        return subTree(t1, t2); // 调用辅助函数检查子树关系
    }

    /**
     * 检查以 r1 为根的树是否包含以 r2 为根的子树。
     *
     * @param r1 大树的根节点
     * @param r2 子树的根节点
     * @return 如果大树包含子树，则返回 true；否则返回 false
     */
    public static boolean subTree(TreeNode r1, TreeNode r2) {
        if (r1 == null) {
            return false; // 大树为空且子树未找到，返回 false
        } else if (r1.data == r2.data && matchTree(r1, r2)) { // 负责“广度优先”的遍历
            return true; // 如果当前节点值匹配，并且两棵树从该节点开始完全匹配，则返回 true
        }
        // 否则递归检查左子树和右子树，用于检查当前节点是否是子树的根节点，并递归验证其子树结构是否匹配。负责“深度优先”的匹配检查
        return subTree(r1.left, r2) || subTree(r1.right, r2);
    }

    /**
     * 检查以 r1 为根的树是否与以 r2 为根的树完全匹配。
     *
     * @param r1 第一棵树的根节点
     * @param r2 第二棵树的根节点
     * @return 如果两棵树完全匹配，则返回 true；否则返回 false
     */
    public static boolean matchTree(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true; // 两棵树同时为空，说明匹配完成
        } else if (r1 == null || r2 == null) {
            return false; // 其中一棵树为空，另一棵不为空，说明不匹配
        } else if (r1.data != r2.data) {
            return false; // 当前节点值不匹配，说明不匹配
        } else {
            // 递归检查左子树和右子树是否完全匹配，用于在当前节点不匹配的情况下，继续在大树的其他部分（左子树和右子树）中寻找子树。
            return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
        }
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