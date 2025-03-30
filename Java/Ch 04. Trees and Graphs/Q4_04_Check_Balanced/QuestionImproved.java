package Q4_04_Check_Balanced;

import CtCILibrary.TreeNode;

public class QuestionImproved {

    /**
     * 检查二叉树的高度，并判断是否平衡。
     *
     * @param root 树的根节点
     * @return 如果树是平衡的，返回树的高度；如果树不平衡，返回 Integer.MIN_VALUE
     */
    public static int checkHeight(TreeNode root) {
        if (root == null) { // 如果当前节点为空，返回 -1（表示高度为 0 的树）
            return -1;
        }

        // 递归检查左子树的高度
        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE; // 如果左子树已经不平衡，直接向上层传递错误信息
        }

        // 递归检查右子树的高度
        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE; // 如果右子树已经不平衡，直接向上层传递错误信息
        }

        // 计算左右子树的高度差
        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // 如果高度差大于 1，则树不平衡，返回错误值
        } else {
            // 如果树平衡，返回当前树的高度
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 判断二叉树是否平衡。
     *
     * @param root 树的根节点
     * @return 如果树是平衡的返回 true，否则返回 false
     */
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE; // 如果高度不是错误值，则树是平衡的
    }

    /**
     * 主函数：测试判断二叉树是否平衡的功能。
     */
    public static void main(String[] args) {
        // 创建一个平衡的二叉树
        int[] array = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        TreeNode root = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        System.out.println("Is balanced? " + isBalanced(root)); // 判断并输出该树是否平衡

        // 向树中插入一个节点，使其可能变得不平衡
        root.insertInOrder(4); // 插入节点 4

        System.out.println("Is balanced? " + isBalanced(root)); // 再次判断并输出该树是否平衡
    }
}