package Q4_04_Check_Balanced;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

public class QuestionBrute {

    /**
     * 获取二叉树的高度。
     *
     * @param root 树的根节点
     * @return 树的高度，如果树为空则返回 -1
     */
    public static int getHeight(TreeNode root) {
        if (root == null) { // 如果当前节点为空，返回 -1（表示高度为 0 的树）
            return -1;
        }
        // 递归获取左子树和右子树的高度，并取较大值加 1
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * 递归判断二叉树是否平衡。
     *
     * @param root 树的根节点
     * @return 如果树是平衡的返回 true，否则返回 false
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) { // 如果当前节点为空，认为它是平衡的
            return true;
        }
        // 计算左右子树的高度差
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) { // 如果高度差的绝对值大于 1，则树不平衡
            return false;
        } else {
            // 递归检查左右子树是否平衡
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    /**
     * 主函数：测试判断二叉树是否平衡的功能。
     */
    public static void main(String[] args) {
        // 创建一个平衡的二叉树
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        TreeNode root = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树
        System.out.println("Root? " + root.data); // 输出根节点的数据
        System.out.println("Is balanced? " + isBalanced(root)); // 判断并输出该树是否平衡

        // 创建一个可能不平衡的二叉树
        TreeNode unbalanced = new TreeNode(10); // 创建一个根节点为 10 的树
        for (int i = 0; i < 10; i++) { // 随机插入 10 个节点
            unbalanced.insertInOrder(AssortedMethods.randomIntInRange(0, 100)); // 插入随机数
        }
        System.out.println("Root? " + unbalanced.data); // 输出根节点的数据
        System.out.println("Is balanced? " + isBalanced(unbalanced)); // 判断并输出该树是否平衡
    }
}