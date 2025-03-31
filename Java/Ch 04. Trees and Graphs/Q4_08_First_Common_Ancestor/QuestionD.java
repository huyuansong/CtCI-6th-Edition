package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;
/**
 * 	核心思想：通过递归检查 p 和 q 是否在同一子树中，逐步缩小查找范围，直到找到最近公共祖先。
	时间复杂度：O(N)，其中 N 是树中节点的数量。每次调用 covers 方法都会遍历部分树。
	空间复杂度：O(H)，其中 H 是树的高度。递归调用栈的空间复杂度取决于树的高度。
 */
public class QuestionD {

    /**
     * 查找两个节点 p 和 q 的最近公共祖先（LCA）。
     *
     * @param root 当前子树的根节点
     * @param p    第一个目标节点
     * @param q    第二个目标节点
     * @return 返回最近公共祖先节点，如果没有找到则返回 null
     */
    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果 p 或 q 不在以 root 为根的子树中，则直接返回 null
        if (!covers(root, p) || !covers(root, q)) { 
            return null;
        }
        
        // 调用辅助函数查找最近公共祖先
        return ancestorHelper(root, p, q);
    }

    /**
     * 辅助函数：递归查找两个节点 p 和 q 的最近公共祖先（LCA）。
     *
     * @param root 当前子树的根节点
     * @param p    第一个目标节点
     * @param q    第二个目标节点
     * @return 返回最近公共祖先节点
     */
    public static TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        // 如果当前节点为空、或者当前节点是 p 或 q，则直接返回当前节点
        if (root == null || root == p || root == q) {
            return root;
        }

        // 检查 p 和 q 是否都在左子树中
        boolean pIsOnLeft = covers(root.left, p);
        // 检查 p 和 q 是否都在右子树中
        boolean qIsOnLeft = covers(root.left, q);

        // 如果 p 和 q 分别位于左右子树，则当前节点就是它们的最近公共祖先
        if (pIsOnLeft != qIsOnLeft) { // 如果 p 和 q 在不同的子树上
            return root; // 当前节点即为最近公共祖先
        }

        // 确定 p 和 q 所在的子树方向
        TreeNode childSide = pIsOnLeft ? root.left : root.right;

        // 递归查找该子树中的最近公共祖先
        return ancestorHelper(childSide, p, q);
    }

    /**
     * 检查以 root 为根的子树中是否包含目标节点 p。
     *
     * @param root 当前子树的根节点
     * @param p    目标节点
     * @return 如果包含目标节点，则返回 true；否则返回 false
     */
    public static boolean covers(TreeNode root, TreeNode p) {
        if (root == null) return false; // 如果当前节点为空，则返回 false
        if (root == p) return true; // 如果当前节点等于目标节点，则返回 true

        // 递归检查左子树和右子树是否包含目标节点
        return covers(root.left, p) || covers(root.right, p);
    }

    /**
     * 主函数：测试查找最近公共祖先的功能。
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        TreeNode root = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        // 查找两个目标节点 n3 和 n7
        TreeNode n3 = root.find(1); // 找到值为 1 的节点
        TreeNode n7 = root.find(7); // 找到值为 7 的节点

        // 查找 n3 和 n7 的最近公共祖先
        TreeNode ancestor = commonAncestor(root, n3, n7);

        // 输出最近公共祖先的值
        System.out.println(ancestor.data);
    }
}