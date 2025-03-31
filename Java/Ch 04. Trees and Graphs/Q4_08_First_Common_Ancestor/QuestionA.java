package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;

public class QuestionA {
    /**
     * 查找两个节点 p 和 q 的最近公共祖先（LCA）。
     *
     * @param p 第一个目标节点
     * @param q 第二个目标节点
     * @return 返回最近公共祖先节点，如果没有找到则返回 null
     */
    public static TreeNode commonAncestor(TreeNode p, TreeNode q) {
        if (p == q) return p; // 如果 p 和 q 是同一个节点，则直接返回该节点

        TreeNode ancestor = p; // 从 p 节点开始向上查找
        while (ancestor != null) { // 遍历 p 节点的所有祖先
            if (isOnPath(ancestor, q)) { // 检查当前祖先是否在 q 节点的路径上
                return ancestor; // 如果是，则返回该祖先节点
            }
            ancestor = ancestor.parent; // 否则继续向上查找
        }
        return null; // 如果没有找到公共祖先，则返回 null
    }

    /**
     * 检查某个节点是否在另一个节点的路径上。
     *
     * @param ancestor 当前检查的祖先节点
     * @param node     目标节点
     * @return 如果目标节点在祖先节点的路径上，则返回 true；否则返回 false
     */
    public static boolean isOnPath(TreeNode ancestor, TreeNode node) {
        while (node != ancestor && node != null) { // 遍历目标节点的所有祖先
            node = node.parent; // 向上移动到父节点
        }
        return node == ancestor; // 如果最终目标节点等于祖先节点，则返回 true
    }

    /**
     * 主函数：测试查找最近公共祖先的功能。
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        TreeNode root = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        // 查找两个目标节点 n3 和 n7
        TreeNode n3 = root.find(8); // 找到值为 8 的节点
        TreeNode n7 = root.find(8); // 找到值为 8 的节点（注意这里是重复的）

        // 查找 n3 和 n7 的最近公共祖先
        TreeNode ancestor = commonAncestor(n3, n7);

        // 输出最近公共祖先的值
        System.out.println(ancestor.data);
    }
}