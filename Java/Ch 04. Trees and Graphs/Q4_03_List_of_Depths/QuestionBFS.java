package Q4_03_List_of_Depths;

import CtCILibrary.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * 给二叉树的每一层节点创建一个链表
 */
public class QuestionBFS {

    /*
     * 根据二叉树的根节点，创建一个按深度分层的链表集合。
     * 每一层的节点存储在一个链表中，所有链表存储在结果列表中。
     */
    public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>(); // 存储结果的列表

        /* 初始化当前层的链表，并将根节点加入其中 */
        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        if (root != null) { // 如果根节点不为空
            current.add(root); // 将根节点添加到当前层链表
        }

        /* 使用广度优先搜索（BFS）遍历二叉树, 在遍历过程中做记录，达到相关的应用目的 */
        while (current.size() > 0) { // 当前层链表不为空时继续
            result.add(current); // 将当前层链表添加到结果列表中
            LinkedList<TreeNode> parents = current; // 当前层作为父节点层
            current = new LinkedList<TreeNode>(); // 创建新的链表用于存储下一层节点

            /* 遍历当前层的所有节点（父节点的所有孩子） */
            for (TreeNode parent : parents) {
                /* 访问当前节点的子节点 */
                if (parent.left != null) { // 如果左子节点存在
                    current.add(parent.left); // 将左子节点添加到下一层链表
                }
                if (parent.right != null) { // 如果右子节点存在
                    current.add(parent.right); // 将右子节点添加到下一层链表
                }
            }
        }

        return result; // 返回按深度分层的链表集合
    }

    /*
     * 打印按深度分层的链表集合。
     * 每一层的节点数据以链表形式输出。
     */
    public static void printResult(ArrayList<LinkedList<TreeNode>> result) {
        int depth = 0; // 当前深度
        for (LinkedList<TreeNode> entry : result) { // 遍历每一层链表
            Iterator<TreeNode> i = entry.listIterator(); // 获取链表迭代器
            System.out.print("Link list at depth " + depth + ":"); // 输出当前深度
            while (i.hasNext()) { // 遍历链表中的每个节点
                System.out.print(" " + ((TreeNode) i.next()).data); // 输出节点数据
            }
            System.out.println(); // 换行
            depth++; // 深度加1
        }
    }

    /*
     * 主函数：测试按深度分层的链表生成功能。
     */
    public static void main(String[] args) {
        int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个数组表示二叉树节点
        TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened); // 根据数组创建二叉树
        ArrayList<LinkedList<TreeNode>> list = createLevelLinkedList(root); // 生成按深度分层的链表集合
        printResult(list); // 打印链表集合
    }
}