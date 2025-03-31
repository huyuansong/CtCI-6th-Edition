package Q4_06_Successor;

import CtCILibrary.TreeNode;

public class QuestionBack {

    /**
     * 找到给定节点的中序遍历后继节点。
     *
     * @param n 当前节点
     * @return 中序遍历后继节点，如果不存在则返回 null
     */
    public static TreeNode inorderSucc(TreeNode n) {
        if (n == null) return null; // 如果当前节点为空，直接返回 null

        // 如果当前节点有右子树，则后继节点是右子树中最左的节点
        if (n.parent == null || n.right != null) { 
            return leftMostChild(n.right); // 调用辅助方法找到右子树中最左的节点
        } else { 
            TreeNode q = n; // 当前节点
            TreeNode x = q.parent; // 当前节点的父节点

            // 如果当前节点没有右子树，则需要向上查找
            // 一直向上直到找到某个节点是其父节点的左子节点
            while (x != null && x.left != q) {
                q = x; // 更新当前节点为父节点
                x = x.parent; // 更新父节点为其父节点
            }
            return x; // 返回最终找到的父节点（即后继节点）
        }  
    }

    /**
     * 找到给定节点的最左子节点。
     *
     * @param n 当前节点
     * @return 最左子节点，如果不存在则返回 null
     */
    public static TreeNode leftMostChild(TreeNode n) {
        if (n == null) {
            return null; // 如果当前节点为空，直接返回 null
        }
        while (n.left != null) { // 循环找到最左的子节点
            n = n.left; 
        }
        return n; // 返回最左的子节点
    }

    /**
     * 主函数：测试中序遍历后继节点的功能。
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个有序数组
        TreeNode root = TreeNode.createMinimalBST(array); // 根据数组创建最小高度的二叉搜索树

        // 遍历数组中的每个元素，找到对应的节点并打印其中序遍历后继节点
        for (int i = 0; i < array.length; i++) {
            TreeNode node = root.find(array[i]); // 找到数组中对应值的节点
            TreeNode next = inorderSucc(node); // 找到该节点的中序遍历后继节点

            // 打印当前节点和其后继节点的值
            if (next != null) {
                System.out.println(node.data + "->" + next.data); // 如果后继节点存在，打印两个节点的值
            } else {
                System.out.println(node.data + "->" + null); // 如果后继节点不存在，打印当前节点值和 null
            }
        }
    }
}