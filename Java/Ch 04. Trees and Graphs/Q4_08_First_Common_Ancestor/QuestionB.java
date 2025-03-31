package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;
/**
 * 	核心思想：通过调整两个节点的深度，使其处于同一层，然后同时向上遍历，直到找到公共祖先。
	时间复杂度：O(D)，其中 D 是树的高度。需要计算深度并向上遍历。
	空间复杂度：O(1)，只需要常量级别的额外空间。
 */
public class QuestionB {
    /**
     * 查找两个节点 p 和 q 的最近公共祖先（LCA）。
     *
     * @param p 第一个目标节点
     * @param q 第二个目标节点
     * @return 返回最近公共祖先节点，如果没有找到则返回 null
     */
    public static TreeNode commonAncestor(TreeNode p, TreeNode q) {
        int delta = depth(p) - depth(q); // 计算两个节点的深度差
        TreeNode first = delta > 0 ? q : p; // 获取较浅的节点
        TreeNode second = delta > 0 ? p : q; // 获取较深的节点

        // 将较深的节点向上移动，直到与较浅的节点处于同一深度
        second = goUpBy(second, Math.abs(delta));

        // 同时向上遍历两个节点，直到它们相遇或到达根节点
        while (first != second && first != null && second != null) {
            first = first.parent; // 向上移动较浅的节点
            second = second.parent; // 向上移动较深的节点
        }

        // 如果任意一个节点为 null，则说明没有公共祖先；否则返回相遇的节点
        return first == null || second == null ? null : first;
    }

    /**
     * 将节点向上移动指定的层数。
     *
     * @param node 要移动的节点
     * @param delta 移动的层数
     * @return 返回移动后的节点
     */
    public static TreeNode goUpBy(TreeNode node, int delta) {
        while (delta > 0 && node != null) { // 循环向上移动 delta 层
            node = node.parent; // 向上移动到父节点
            delta--; // 减少移动层数
        }
        return node; // 返回移动后的节点
    }

    /**
     * 计算节点的深度（距离根节点的距离）。
     *
     * @param node 当前节点
     * @return 返回节点的深度
     */
    public static int depth(TreeNode node) {
        int depth = 0; // 初始化深度为 0
        while (node != null) { // 循环向上遍历到根节点
            node = node.parent; // 向上移动到父节点
            depth++; // 深度加 1
        }
        return depth; // 返回节点的深度
    }

    /**
     * 主函数：测试查找最近公共祖先的功能。
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        TreeNode root = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        // 查找两个目标节点 n3 和 n7
        TreeNode n3 = root.find(3); // 找到值为 3 的节点
        TreeNode n7 = root.find(7); // 找到值为 7 的节点

        // 查找 n3 和 n7 的最近公共祖先
        TreeNode ancestor = commonAncestor(n3, n7);

        // 输出最近公共祖先的值
        System.out.println(ancestor.data);
    }
}