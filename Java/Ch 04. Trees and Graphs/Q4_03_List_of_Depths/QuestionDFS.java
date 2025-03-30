package Q4_03_List_of_Depths;

import CtCILibrary.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class QuestionDFS {

    /**
     * 使用深度优先搜索（DFS）递归地将二叉树的每一层节点存入链表集合中。
     *
     * @param root  当前遍历的树节点
     * @param lists 存储每一层节点的链表集合
     * @param level 当前节点所在的层数
     */
    public static void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
        if (root == null) return; // 如果当前节点为空，直接返回

        LinkedList<TreeNode> list = null;

        // 如果当前层还没有对应的链表，则创建一个新的链表
        if (lists.size() == level) { 
            list = new LinkedList<TreeNode>();
            /* 
             * 层级总是按顺序遍历。因此，如果这是第一次访问第 i 层，
             * 我们必须已经访问过第 0 到第 i-1 层。因此可以安全地将新链表添加到末尾。
             */
            lists.add(list);  
        } else {
            // 如果当前层已经有对应的链表，则获取该链表
            list = lists.get(level);
        }

        // 将当前节点加入对应层的链表中
        list.add(root);

        // 递归处理左子树和右子树，层级加 1
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    /**
     * 主方法：调用递归方法生成按深度分层的链表集合。
     *
     * @param root 二叉树的根节点
     * @return 按深度分层的链表集合
     */
    public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>(); // 创建一个链表集合
        createLevelLinkedList(root, lists, 0); // 调用递归方法，从根节点开始，初始层级为 0
        return lists; // 返回结果
    }

    /**
     * 打印按深度分层的链表集合。
     *
     * @param result 按深度分层的链表集合
     */
    public static void printResult(ArrayList<LinkedList<TreeNode>> result) {
        int depth = 0; // 当前深度，从 0 开始
        for (LinkedList<TreeNode> entry : result) { // 遍历每一层链表
            Iterator<TreeNode> i = entry.listIterator(); // 获取链表迭代器
            System.out.print("Link list at depth " + depth + ":"); // 输出当前深度
            while (i.hasNext()) { // 遍历链表中的每个节点
                System.out.print(" " + ((TreeNode) i.next()).data); // 输出节点数据
            }
            System.out.println(); // 换行
            depth++; // 深度加 1
        }
    }

    /**
     * 主函数：测试按深度分层的链表生成功能。
     */
    public static void main(String[] args) {
        int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个数组表示二叉树节点
        TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened); // 根据数组创建二叉树
        ArrayList<LinkedList<TreeNode>> list = createLevelLinkedList(root); // 调用方法生成按深度分层的链表集合
        printResult(list); // 调用方法打印链表集合
    }
}