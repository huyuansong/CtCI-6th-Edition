package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;
/**
 * 	存在的问题：
		潜在问题：如果 p 或 q 不在树中，或者其中一个节点不存在于树中，该算法可能会返回错误的结果。
		原因：在递归过程中，没有对 p 和 q 是否存在于树中进行验证。如果 p 或 q 不在树中，算法可能会误判某个节点为最近公共祖先。
	改进建议：
		在查找最近公共祖先之前，应先验证 p 和 q 是否都在树中。
		如果任意一个节点不在树中，则直接返回 null。
 */
public class QuestionEBad {

    /**
     * 查找两个节点 p 和 q 的最近公共祖先（LCA），但此实现存在潜在问题。
     *
     * @param root 当前子树的根节点
     * @param p    第一个目标节点
     * @param q    第二个目标节点
     * @return 返回最近公共祖先节点，如果没有找到则返回 null
     */
    public static TreeNode commonAncestorBad(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) { // 如果当前节点为空，则直接返回 null
            return null;
        }

        // 如果当前节点既是 p 也是 q，则当前节点就是它们的最近公共祖先
        if (root == p && root == q) {
            return root;
        }

        // 递归检查左子树
        TreeNode x = commonAncestorBad(root.left, p, q);
        if (x != null && x != p && x != q) { // 如果在左子树中找到了最近公共祖先，则直接返回
            return x;
        }

        // 递归检查右子树
        TreeNode y = commonAncestorBad(root.right, p, q);
        if (y != null && y != p && y != q) { // 如果在右子树中找到了最近公共祖先，则直接返回
            return y;
        }

        // 如果 p 和 q 分别位于左右子树，则当前节点就是它们的最近公共祖先
        if (x != null && y != null) {
            return root; // 当前节点即为最近公共祖先
        } 
        // 如果当前节点是 p 或 q，并且在某个子树中找到了另一个节点，则当前节点可能是最近公共祖先
        else if (root == p || root == q) {
            return root;
        } 
        // 否则，返回已经找到的节点（如果有的话）
        else {
            return x == null ? y : x;
        }
    }

    /**
     * 主函数：测试查找最近公共祖先的功能。
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        TreeNode root = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        // 查找两个目标节点 n3 和 n7
        TreeNode n3 = root.find(9); // 找到值为 9 的节点
        TreeNode n7 = new TreeNode(6); // 创建一个新的节点（注意：该节点可能不在树中）

        // 查找 n3 和 n7 的最近公共祖先
        TreeNode ancestor = commonAncestorBad(root, n3, n7);

        // 输出最近公共祖先的值
        if (ancestor != null) {
            System.out.println(ancestor.data);
        } else {
            System.out.println("null");
        }
    }
}