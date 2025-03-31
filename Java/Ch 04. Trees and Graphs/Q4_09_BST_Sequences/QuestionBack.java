package Q4_09_BST_Sequences;

import java.util.ArrayList;
import java.util.LinkedList;

import CtCILibrary.TreeNode;
/**
 * 通过从左到右遍历数组来创建二叉搜索树并插入每个元素。给定一个具有不同元素的二叉搜索树，打印可能导致此树的所有可能的数组。
 */
public class QuestionBack {

    /**
     * 将两个链表的所有可能交织组合存储到结果列表中。
     *
     * @param first   第一个链表
     * @param second  第二个链表
     * @param results 存储所有交织组合的结果列表
     * @param prefix  当前的前缀链表（用于构建交织组合）
     */
    public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        /* 如果其中一个链表为空，则将另一个链表剩余部分添加到前缀的克隆副本中，
         * 并将结果存储到结果列表中。 */
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone(); // 克隆当前前缀
            result.addAll(first); // 添加第一个链表的剩余部分
            result.addAll(second); // 添加第二个链表的剩余部分
            results.add(result); // 将结果添加到结果列表中
            return;
        }

        /* 递归处理：将第一个链表的头部元素添加到前缀中，并继续递归处理剩余部分。
         * 注意：移除头部会破坏原链表，因此需要在递归完成后恢复链表状态。 */
        int headFirst = first.removeFirst(); // 移除第一个链表的头部
        prefix.addLast(headFirst); // 将头部添加到前缀中
        weaveLists(first, second, results, prefix); // 递归处理剩余部分
        prefix.removeLast(); // 恢复前缀
        first.addFirst(headFirst); // 恢复第一个链表

        /* 同样的逻辑对第二个链表进行处理。 */
        int headSecond = second.removeFirst(); // 移除第二个链表的头部
        prefix.addLast(headSecond); // 将头部添加到前缀中
        weaveLists(first, second, results, prefix); // 递归处理剩余部分
        prefix.removeLast(); // 恢复前缀
        second.addFirst(headSecond); // 恢复第二个链表
    }

    /**
     * 获取给定二叉搜索树的所有可能序列。
     *
     * @param node 当前子树的根节点
     * @return 返回包含所有可能序列的结果列表
     */
    public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>(); // 存储结果的列表

        if (node == null) { // 如果当前节点为空，则返回一个包含空链表的结果列表
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<Integer>(); // 当前序列的前缀
        prefix.add(node.data); // 将当前节点的值添加到前缀中

        /* 递归获取左子树和右子树的所有可能序列。 */
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left); // 左子树的所有序列
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right); // 右子树的所有序列

        /* 将左子树和右子树的所有序列进行交织组合。 */
        for (LinkedList<Integer> left : leftSeq) { // 遍历左子树的所有序列
            for (LinkedList<Integer> right : rightSeq) { // 遍历右子树的所有序列
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>(); // 存储交织组合的结果
                weaveLists(left, right, weaved, prefix); // 进行交织组合
                result.addAll(weaved); // 将交织组合的结果添加到最终结果中
            }
        }
        return result; // 返回所有可能序列
    }

    /**
     * 主函数：测试获取二叉搜索树的所有可能序列的功能。
     */
    public static void main(String[] args) {
        TreeNode node = new TreeNode(100); // 创建根节点，值为 100
        int[] array = {100, 50, 20, 75, 150, 120, 170}; // 定义一个数组，用于插入节点
        for (int a : array) {
            node.insertInOrder(a); // 按顺序插入节点，构建二叉搜索树
        }

        /* 获取二叉搜索树的所有可能序列，并输出每个序列及总数。 */
        ArrayList<LinkedList<Integer>> allSeq = allSequences(node); // 获取所有可能序列
        for (LinkedList<Integer> list : allSeq) { // 遍历并输出每个序列
            System.out.println(list);
        }
        System.out.println(allSeq.size()); // 输出序列总数
    }
}