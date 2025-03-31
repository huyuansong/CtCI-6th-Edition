package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;
/**
 * 	核心思想：通过递归检查子树中包含的目标节点数量，确定最近公共祖先的位置。
	时间复杂度：每次调用 covers 方法会遍历整个树，因此时间复杂度为 O(N)，其中 N 是树中节点的数量。
	空间复杂度：递归调用栈的空间复杂度为 O(H)，其中 H 是树的高度。
 */
public class Question {
    // 定义常量，用于表示找到的节点数量
    static int TWO_NODES_FOUND = 2; // 找到了两个目标节点
    static int ONE_NODE_FOUND = 1;  // 找到了一个目标节点
    static int NO_NODES_FOUND = 0;  // 没有找到任何目标节点

    /**
     * 检查以 root 为根的子树中包含多少个目标节点（p 和 q）。
     *
     * @param root 当前子树的根节点
     * @param p    第一个目标节点
     * @param q    第二个目标节点
     * @return 返回找到的目标节点数量（0、1 或 2）
     */
    public static int covers(TreeNode root, TreeNode p, TreeNode q) {
        int ret = NO_NODES_FOUND; // 初始化返回值为 0
        if (root == null) return ret; // 如果当前节点为空，直接返回 0

        // 如果当前节点是 p 或 q，则增加计数
        if (root == p || root == q) ret += 1;

        // 递归检查左子树
        ret += covers(root.left, p, q);
        if (ret == TWO_NODES_FOUND) { // 如果已经找到了两个节点，直接返回
            return ret;
        }

        // 递归检查右子树
        return ret + covers(root.right, p, q);
    }

    /**
     * 查找两个节点 p 和 q 的最近公共祖先（LCA）。
     *
     * @param root 树的根节点
     * @param p    第一个目标节点
     * @param q    第二个目标节点
     * @return 返回最近公共祖先节点，如果没有找到则返回 null
     */
    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 特殊情况：如果 p 和 q 是同一个节点，并且该节点是 root 的直接子节点
        if (q == p && (root.left == q || root.right == q)) return root;

        // 检查左子树中有多少个目标节点
        int nodesFromLeft = covers(root.left, p, q);
        if (nodesFromLeft == TWO_NODES_FOUND) { // 如果左子树中包含了两个目标节点
            if (root.left == p || root.left == q) return root.left; // 如果左子树根节点是目标节点之一，直接返回
            else return commonAncestor(root.left, p, q); // 否则在左子树中递归查找
        } else if (nodesFromLeft == ONE_NODE_FOUND) { // 如果左子树中只包含一个目标节点
            if (root == p) return p; // 如果当前节点是 p，直接返回
            else if (root == q) return q; // 如果当前节点是 q，直接返回
        }

        // 检查右子树中有多少个目标节点
        int nodesFromRight = covers(root.right, p, q);
        if (nodesFromRight == TWO_NODES_FOUND) { // 如果右子树中包含了两个目标节点
            if (root.right == p || root.right == q) return root.right; // 如果右子树根节点是目标节点之一，直接返回
            else return commonAncestor(root.right, p, q); // 否则在右子树中递归查找
        } else if (nodesFromRight == ONE_NODE_FOUND) { // 如果右子树中只包含一个目标节点
            if (root == p) return p; // 如果当前节点是 p，直接返回
            else if (root == q) return q; // 如果当前节点是 q，直接返回
        }

        // 如果左右子树各包含一个目标节点，则当前节点就是最近公共祖先
        if (nodesFromLeft == ONE_NODE_FOUND && nodesFromRight == ONE_NODE_FOUND) return root;

        // 如果没有找到任何目标节点，返回 null
        else return null;
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