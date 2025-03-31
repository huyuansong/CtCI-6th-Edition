package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;
/**
 * 	核心思想：通过递归检查 p 和 q 是否在同一子树中，逐步缩小查找范围，直到找到最近公共祖先。
	时间复杂度：O(N)，其中 N 是树中节点的数量。每次递归调用都会遍历部分树。
	空间复杂度：O(H)，其中 H 是树的高度。递归调用栈的空间复杂度取决于树的高度。
 */
public class QuestionE {

    /**
     * 内部类：用于存储查找结果。
     */
    public static class Result {
        public TreeNode node; // 存储找到的节点
        public boolean isAncestor; // 标识是否找到了最近公共祖先

        /**
         * 构造函数，初始化节点和标识。
         *
         * @param n       找到的节点
         * @param isAnc   是否找到了最近公共祖先
         */
        public Result(TreeNode n, boolean isAnc) {
            node = n;
            isAncestor = isAnc;
        }
    }

    /**
     * 辅助函数：递归查找两个节点 p 和 q 的最近公共祖先（LCA）。
     *
     * @param root 当前子树的根节点
     * @param p    第一个目标节点
     * @param q    第二个目标节点
     * @return 返回包含查找结果的对象
     */
    public static Result commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) { // 如果当前节点为空，则返回空结果
            return new Result(null, false);
        }

        // 如果当前节点既是 p 也是 q，则当前节点就是它们的最近公共祖先
        if (root == p && root == q) {
            return new Result(root, true);
        }

        // 递归检查左子树
        Result rx = commonAncestorHelper(root.left, p, q);
        if (rx.isAncestor) { // 如果在左子树中找到了最近公共祖先，则直接返回
            return rx;
        }

        // 递归检查右子树
        Result ry = commonAncestorHelper(root.right, p, q);
        if (ry.isAncestor) { // 如果在右子树中找到了最近公共祖先，则直接返回
            return ry;
        }

        // 如果 p 和 q 分别位于左右子树，则当前节点就是它们的最近公共祖先
        if (rx.node != null && ry.node != null) {
            return new Result(root, true); // 当前节点即为最近公共祖先
        } 
        // 如果当前节点是 p 或 q，并且在某个子树中找到了另一个节点，则当前节点是最近公共祖先
        else if (root == p || root == q) {
            /* 如果当前节点是 p 或 q，并且在某个子树中找到了另一个节点，
             * 则当前节点是最近公共祖先。 */
            boolean isAncestor = rx.node != null || ry.node != null;
            return new Result(root, isAncestor);
        } 
        // 否则，返回已经找到的节点（如果有的话）
        else {
            return new Result(rx.node != null ? rx.node : ry.node, false);
        }
    }

    /**
     * 查找两个节点 p 和 q 的最近公共祖先（LCA）。
     *
     * @param root 当前子树的根节点
     * @param p    第一个目标节点
     * @param q    第二个目标节点
     * @return 返回最近公共祖先节点，如果没有找到则返回 null
     */
    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Result r = commonAncestorHelper(root, p, q); // 调用辅助函数进行递归查找
        if (r.isAncestor) { // 如果找到了最近公共祖先
            return r.node; // 返回找到的节点
        }
        return null; // 否则返回 null
    }

    /**
     * 主函数：测试查找最近公共祖先的功能。
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        TreeNode root = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        // 查找两个目标节点 n3 和 n7
        TreeNode n3 = root.find(10); // 找到值为 10 的节点
        TreeNode n7 = root.find(6); // 找到值为 6 的节点

        // 查找 n3 和 n7 的最近公共祖先
        TreeNode ancestor = commonAncestor(root, n3, n7);

        // 输出最近公共祖先的值
        if (ancestor != null) {
            System.out.println(ancestor.data);
        } else {
            System.out.println("null");
        }
    }
}