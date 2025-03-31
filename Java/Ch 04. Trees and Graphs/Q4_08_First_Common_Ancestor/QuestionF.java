package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;
/**
 * 	总结：
	 	核心思想：通过分别遍历两个节点的所有祖先，找到第一个相同的祖先作为最近公共祖先。
		时间复杂度：O(D^2)，其中 D 是树的高度。最坏情况下需要遍历两个节点的所有祖先。
		空间复杂度：O(1)，只需要常量级别的额外空间。
	关键点：
		双层循环：外层循环遍历 p 的所有祖先，内层循环遍历 q 的所有祖先。
		祖先匹配：一旦找到相同的祖先，则立即返回该祖先作为最近公共祖先。
 */
public class QuestionF {

    /**
     * 查找两个节点 p 和 q 的最近公共祖先（LCA）。
     *
     * @param root 当前树的根节点（仅用于上下文，未实际使用）
     * @param p    第一个目标节点
     * @param q    第二个目标节点
     * @return 返回最近公共祖先节点，如果没有找到则返回 null
     */
    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((p == null) || (q == null)) { // 如果任意一个节点为空，则直接返回 null
            return null;
        }

        // 从 p 节点开始向上查找其所有祖先
        TreeNode ap = p.parent; // 获取 p 的父节点
        while (ap != null) { // 遍历 p 的所有祖先
            TreeNode aq = q.parent; // 获取 q 的父节点
            while (aq != null) { // 遍历 q 的所有祖先
                if (aq == ap) { // 如果某个祖先相同，则该祖先就是最近公共祖先
                    return aq;
                }
                aq = aq.parent; // 向上移动到 q 的下一个祖先
            }
            ap = ap.parent; // 向上移动到 p 的下一个祖先
        }
        return null; // 如果没有找到公共祖先，则返回 null
    }

    /**
     * 主函数：测试查找最近公共祖先的功能。
     */
    public static void main(String[] args) {
        int[] array = {5, 3, 6, 1, 9, 11}; // 定义一个数组，用于插入节点
        TreeNode root = new TreeNode(20); // 创建根节点，值为 20

        // 将数组中的元素按顺序插入到二叉搜索树中
        for (int a : array) {
            root.insertInOrder(a);
        }

        // 查找两个目标节点 n1 和 n9
        TreeNode n1 = root.find(1); // 找到值为 1 的节点
        TreeNode n9 = root.find(9); // 找到值为 9 的节点

        // 查找 n1 和 n9 的最近公共祖先
        TreeNode ancestor = commonAncestor(root, n1, n9);

        // 输出最近公共祖先的值
        System.out.println(ancestor.data);
    }
}