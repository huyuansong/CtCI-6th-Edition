package Q4_05_Validate_BST;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

public class QuestionBBack {

    /**
     * 检查给定的二叉树是否是有效的二叉搜索树（BST）。
     *
     * @param n    当前节点
     * @param min  当前节点允许的最小值（可以为 null 表示没有限制）
     * @param max  当前节点允许的最大值（可以为 null 表示没有限制）
     * @return 如果是有效的 BST 返回 true，否则返回 false
     */
    public static boolean checkBST(TreeNode n, Integer min, Integer max) {
        if (n == null) { // 如果当前节点为空，认为它是有效的 BST
            return true;
        }

        // 检查当前节点是否在允许的范围内
        if ((min != null && n.data <= min) || (max != null && n.data > max)) {
            return false; // 如果当前节点不在允许范围内，则不是有效的 BST
        }

        // 递归检查左子树和右子树
        if (!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max)) {
            return false; // 如果左子树或右子树不是有效的 BST，则返回 false
        }

        return true; // 如果所有检查都通过，则该树是有效的 BST
    }

    /**
     * 检查给定的二叉树是否是有效的二叉搜索树（BST）。
     *
     * @param n 树的根节点
     * @return 如果是有效的 BST 返回 true，否则返回 false
     */
    public static boolean checkBST(TreeNode n) {
        return checkBST(n, null, null); // 调用重载方法，初始时没有范围限制
    }

    /**
     * 另一种检查二叉树是否是有效 BST 的方法。
     *
     * @param n 树的根节点
     * @return 如果是有效的 BST 返回 true，否则返回 false
     */
    public static boolean checkBSTAlternate(TreeNode n) {
        return checkBSTAlternate(n, new IntWrapper(0), new IntWrapper(0)); // 使用辅助类 IntWrapper 来传递最小值和最大值
    }

    /**
     * 另一种检查二叉树是否是有效 BST 的方法（递归实现）。
     *
     * @param n    当前节点
     * @param min  当前子树的最小值
     * @param max  当前子树的最大值
     * @return 如果是有效的 BST 返回 true，否则返回 false
     */
    public static boolean checkBSTAlternate(TreeNode n, IntWrapper min, IntWrapper max) {
        /* 
         * 这是一种替代方法，虽然不如第一种方法简洁，但用于测试其他方法的正确性。
         */

        if (n.left == null) { // 如果左子树为空
            min.data = n.data; // 当前节点的值作为最小值
        } else {
            IntWrapper leftMin = new IntWrapper(0); // 左子树的最小值
            IntWrapper leftMax = new IntWrapper(0); // 左子树的最大值
            if (!checkBSTAlternate(n.left, leftMin, leftMax)) { // 递归检查左子树
                return false; // 如果左子树不是有效的 BST，则返回 false
            }
            if (leftMax.data > n.data) { // 如果左子树的最大值大于当前节点的值，则不是有效的 BST
                return false;
            }
            min.data = leftMin.data; // 更新当前子树的最小值
        }

        if (n.right == null) { // 如果右子树为空
            max.data = n.data; // 当前节点的值作为最大值
        } else {
            IntWrapper rightMin = new IntWrapper(0); // 右子树的最小值
            IntWrapper rightMax = new IntWrapper(0); // 右子树的最大值
            if (!checkBSTAlternate(n.right, rightMin, rightMax)) { // 递归检查右子树
                return false; // 如果右子树不是有效的 BST，则返回 false
            }
            if (rightMin.data <= n.data) { // 如果右子树的最小值小于等于当前节点的值，则不是有效的 BST
                return false;
            }
            max.data = rightMax.data; // 更新当前子树的最大值
        }

        return true; // 如果所有检查都通过，则该树是有效的 BST
    }

    /**
     * 创建一个可能不是 BST 的二叉树。
     *
     * @return 创建的二叉树
     */
    public static TreeNode createTestTree() {
        /* 创建一个随机的 BST */
        TreeNode head = AssortedMethods.randomBST(10, -10, 10);

        /* 在 BST 中插入一个元素，可能会破坏 BST 的性质 */
        TreeNode node = head;
        do {
            int n = AssortedMethods.randomIntInRange(-10, 10); // 随机生成一个值
            int rand = AssortedMethods.randomIntInRange(0, 5); // 随机生成一个操作类型
            if (rand == 0) { // 修改当前节点的值
                node.data = n;
            } else if (rand == 1) { // 移动到左子树
                node = node.left;
            } else if (rand == 2) { // 移动到右子树
                node = node.right;
            } else if (rand == 3 || rand == 4) { // 停止操作
                break;
            }
        } while (node != null);

        return head; // 返回创建的二叉树
    }

    /**
     * 主函数：测试判断二叉树是否是有效 BST 的功能。
     */
    public static void main(String[] args) {
        /* 简单测试 -- 创建一棵树 */
        int[] array = {Integer.MIN_VALUE, 3, 5, 6, 10, 13, 15, Integer.MAX_VALUE}; // 定义一个有序数组
        TreeNode node = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        // 修改树结构以测试不同的场景
        // node.left.data = 6; // "破坏" BST 的性质

        node.print(); // 打印树结构
        boolean isBst = checkBST(node); // 判断是否是有效的 BST
        System.out.println(isBst); // 输出结果

        /* 更复杂的测试 -- 创建 100 棵树（有些是 BST，有些不是），并比较不同方法的结果 */
        /*
        for (int i = 0; i < 100; i++) {
            TreeNode head = createTestTree(); // 创建一棵随机的二叉树

            // 比较两种方法的结果
            boolean isBst1 = checkBST(head); // 使用第一种方法判断是否是有效的 BST
            boolean isBst2 = checkBSTAlternate(head); // 使用第二种方法判断是否是有效的 BST

            if (isBst1 != isBst2) { // 如果两种方法的结果不一致
                System.out.println("*********************** ERROR *******************");
                head.print(); // 打印树结构
                break;
            } else {
                System.out.println(isBst1 + " | " + isBst2); // 输出两种方法的结果
                head.print(); // 打印树结构
            }
        }
        */
    }
}