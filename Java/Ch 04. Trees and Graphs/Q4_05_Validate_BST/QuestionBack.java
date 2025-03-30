package Q4_05_Validate_BST;

import CtCILibrary.TreeNode;

public class QuestionBack {

    public static Integer lastPrinted = null; // 用于记录上一个访问节点的值

    /**
     * 检查给定的二叉树是否是有效的二叉搜索树（BST）。
     *
     * @param node 树的根节点
     * @return 如果是有效的 BST 返回 true，否则返回 false
     */
    public static boolean checkBST(TreeNode node) {
        return checkBST(node, true); // 调用重载方法，初始时允许左子树等于父节点
    }

    /**
     * 检查给定的二叉树是否是有效的二叉搜索树（BST）。
     *
     * @param n         当前节点
     * @param isLeft    是否为左子树（true 表示左子树，false 表示右子树）
     * @return 如果是有效的 BST 返回 true，否则返回 false
     */
    public static boolean checkBST(TreeNode n, boolean isLeft) {
        if (n == null) { // 如果当前节点为空，认为它是有效的 BST
            return true;
        }

        // 递归检查左子树
        if (!checkBST(n.left, true)) { // 左子树必须满足 BST 性质
            return false;
        }

        // 检查当前节点是否满足 BST 性质
        if (lastPrinted != null) { // 如果 lastPrinted 不为空，说明已经有节点被访问过
            if (isLeft) { // 如果当前节点是左子树
                // 左子树的节点可以等于父节点
                if (n.data < lastPrinted) { // 如果当前节点小于上一个访问的节点，则不是有效的 BST
                    return false;
                }
            } else { // 如果当前节点是右子树
                // 右子树的节点不能等于或小于父节点
                if (n.data <= lastPrinted) { // 如果当前节点小于或等于上一个访问的节点，则不是有效的 BST
                    return false;
                }
            }
        }
        lastPrinted = n.data; // 更新 lastPrinted 为当前节点的值

        // 递归检查右子树
        if (!checkBST(n.right, false)) { // 右子树必须满足 BST 性质
            return false;
        }

        return true; // 如果所有检查都通过，则该树是有效的 BST
    }

    /**
     * 主函数：测试判断二叉树是否是有效 BST 的功能。
     */
    public static void main(String[] args) {
        int[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE}; // 定义一个有序数组
        TreeNode node = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        // 修改树结构以测试不同的场景
        //node.left.data = 5;
        //node.left.right.data = 3;

        System.out.println(checkBST(node)); // 判断并输出该树是否是有效的 BST

        test(); // 运行额外的测试用例
    }

    /**
     * 测试用例：验证不同场景下的二叉树是否是有效的 BST。
     */
    public static void test() {
        TreeNode node;
        boolean condition;

        System.out.println("test cases for equals condition."); // 输出测试用例说明

        /* 
         * 测试用例 1：期望结果为 true
         * 树结构：
         *       2
         *      / \
         *     2   3
         *          \
         *           4
         */
        int[] array2 = {1, 2, 3, 4};
        node = TreeNode.createMinimalBST(array2); // 创建最小高度的二叉搜索树
        node.left.data = 2; // 修改左子树的值为 2
        node.print(); // 打印树结构
        lastPrinted = null; // 重置 lastPrinted
        condition = checkBST(node); // 判断是否是有效的 BST
        System.out.println("should be true: " + condition); // 输出结果

        /* 
         * 测试用例 2：期望结果为 false
         * 树结构：
         *       2
         *      / \
         *     1   2
         *          \
         *           4
         */
        int[] array3 = {1, 2, 3, 4};
        node = TreeNode.createMinimalBST(array3); // 创建最小高度的二叉搜索树
        node.right.data = 2; // 修改右子树的值为 2
        node.print(); // 打印树结构
        lastPrinted = null; // 重置 lastPrinted
        condition = checkBST(node); // 判断是否是有效的 BST
        System.out.println("should be false: " + condition); // 输出结果
    }
}