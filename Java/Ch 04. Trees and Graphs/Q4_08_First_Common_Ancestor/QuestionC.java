package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;
/**
 * 	核心思想：通过检查节点的覆盖关系和兄弟节点，逐步向上查找最近公共祖先。
	时间复杂度：O(D)，其中 D 是树的高度。需要多次调用 covers 方法来检查覆盖关系。
	空间复杂度：O(1)，只需要常量级别的额外空间。
 */
public class QuestionC {

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

        // 如果 p 是 q 的祖先，则返回 p
        else if (covers(p, q)) {
            return p;
        }

        // 如果 q 是 p 的祖先，则返回 q
        else if (covers(q, p)) {
            return q;
        }

        // 否则，从 p 开始向上查找其兄弟节点，并检查该兄弟节点是否包含 q
        TreeNode sibling = getSibling(p); // 获取 p 的兄弟节点
        TreeNode parent = p.parent; // 获取 p 的父节点

        // 循环向上查找，直到找到包含 q 的兄弟节点
        while (!covers(sibling, q)) {
            sibling = getSibling(parent); // 获取当前节点的兄弟节点
            parent = parent.parent; // 向上移动到父节点
        }

        // 返回最终找到的公共祖先节点
        return parent;
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
     * 获取当前节点的兄弟节点。
     *
     * @param node 当前节点
     * @return 返回兄弟节点，如果没有兄弟节点则返回 null
     */
    public static TreeNode getSibling(TreeNode node) {
        if (node == null || node.parent == null) { // 如果当前节点为空或没有父节点，则返回 null
            return null;
        }

        TreeNode parent = node.parent; // 获取当前节点的父节点
        // 根据当前节点是父节点的左子节点还是右子节点，返回对应的兄弟节点
        return parent.left == node ? parent.right : parent.left;
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