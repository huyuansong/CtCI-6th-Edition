package Introduction;

import CtCILibrary.TreeNode; // 导入TreeNode类，用于创建和操作二叉树节点

public class Traversals {

    /*
     * 访问节点：如果节点不为空，则打印节点数据。
     */
    public static void visit(TreeNode node) {
        if (node != null) { // 检查节点是否为空
            System.out.println(node.data); // 打印节点数据
        }
    }

    /*
     * 中序遍历（In-Order Traversal）：
     * 递归地先访问左子树，再访问当前节点，最后访问右子树。
     */
    public static void inOrderTraversal(TreeNode node) {
        if (node != null) { // 检查节点是否为空
            inOrderTraversal(node.left); // 递归访问左子树
            visit(node); // 访问当前节点
            inOrderTraversal(node.right); // 递归访问右子树
        }
    }

    /*
     * 前序遍历（Pre-Order Traversal）：
     * 递归地先访问当前节点，再访问左子树，最后访问右子树。
     */
    public static void preOrderTraversal(TreeNode node) {
        if (node != null) { // 检查节点是否为空
            visit(node); // 访问当前节点
            inOrderTraversal(node.left); // 递归访问左子树
            inOrderTraversal(node.right); // 递归访问右子树
        }
    }

    /*
     * 后序遍历（Post-Order Traversal）：
     * 递归地先访问左子树，再访问右子树，最后访问当前节点。
     */
    public static void postOrderTraversal(TreeNode node) {
        if (node != null) { // 检查节点是否为空
            inOrderTraversal(node.left); // 递归访问左子树
            inOrderTraversal(node.right); // 递归访问右子树
            visit(node); // 访问当前节点
        }
    }

    /*
     * 主函数：测试二叉树的中序遍历。
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        
        /*
         * 使用TreeNode类提供的静态方法createMinimalBST()，
         * 根据有序数组创建一棵最小高度的二叉搜索树，并返回根节点。
         */
        TreeNode root = TreeNode.createMinimalBST(array);
        
        /*
         * 对生成的二叉树进行中序遍历，并依次打印每个节点的数据。
         * 中序遍历的特点是：对于二叉搜索树，输出结果为升序排列。
         */
        inOrderTraversal(root);
    }
}