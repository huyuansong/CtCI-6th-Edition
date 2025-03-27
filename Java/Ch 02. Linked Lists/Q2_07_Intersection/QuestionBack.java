package Q2_07_Intersection;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;

/**
 *  Result 类：用于封装链表的尾节点和长度，便于在方法间传递信息。
    getTailAndSize 方法：计算链表的长度，并返回尾节点。通过比较尾节点是否相同，判断两个链表是否有交点。
    getKthNode 方法：获取链表中第 k 个节点，用于对齐两个链表的起始位置。
    findIntersection 方法：核心逻辑，通过调整较长链表的起始位置，使两个链表从相同的距离开始遍历，从而找到交点。
 */
public class QuestionBack {

    // 定义一个内部类 Result，用于存储链表的尾节点和长度
    public static class Result {
        public LinkedListNode tail; // 链表的尾节点
        public int size; // 链表的长度

        // 构造函数，初始化尾节点和长度
        public Result(LinkedListNode tail, int size) {
            this.tail = tail;
            this.size = size;
        }
    }

    /**
     * 获取链表的尾节点和长度。
     *
     * @param list 链表的头节点
     * @return 返回一个 Result 对象，包含尾节点和链表长度
     */
    public static Result getTailAndSize(LinkedListNode list) {
        if (list == null) return null; // 如果链表为空，返回 null

        int size = 1; // 初始化链表长度为 1
        LinkedListNode current = list; // 当前节点指针初始化为链表头节点

        // 遍历链表，直到到达尾节点
        while (current.next != null) {
            size++; // 每遍历一个节点，长度加 1
            current = current.next; // 移动到下一个节点
        }

        // 返回尾节点和链表长度
        return new Result(current, size);
    }

    /**
     * 获取链表中第 k 个节点（从头节点开始计数）。
     *
     * @param head 链表的头节点
     * @param k 要获取的节点位置
     * @return 返回第 k 个节点，如果超出链表范围则返回 null
     */
    public static LinkedListNode getKthNode(LinkedListNode head, int k) {
        LinkedListNode current = head; // 当前节点指针初始化为链表头节点

        // 遍历链表，移动 k 次
        while (k > 0 && current != null) {
            current = current.next; // 移动到下一个节点
            k--; // 计数器减 1
        }

        return current; // 返回第 k 个节点
    }

    /**
     * 查找两个链表的交点。
     *
     * @param list1 第一个链表的头节点
     * @param list2 第二个链表的头节点
     * @return 返回交点节点，如果没有交点则返回 null
     */
    public static LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null || list2 == null) return null; // 如果任意一个链表为空，返回 null

        /* 
         * 获取两个链表的尾节点和长度。
         * 如果两个链表有交点，则它们的尾节点一定相同。
         */
        Result result1 = getTailAndSize(list1); // 获取第一个链表的尾节点和长度
        Result result2 = getTailAndSize(list2); // 获取第二个链表的尾节点和长度

        /* 
         * 如果两个链表的尾节点不同，则它们没有交点。
         */
        if (result1.tail != result2.tail) {
            return null; // 返回 null 表示没有交点
        }

        /* 
         * 确定较短链表和较长链表。
         */
        LinkedListNode shorter = result1.size < result2.size ? list1 : list2; // 较短链表的头节点
        LinkedListNode longer = result1.size < result2.size ? list2 : list1; // 较长链表的头节点

        /* 
         * 将较长链表的指针向前移动，使其与较短链表对齐。
         */
        longer = getKthNode(longer, Math.abs(result1.size - result2.size)); 

        /* 
         * 同时遍历两个链表，直到找到交点。
         */
        while (shorter != longer) {
            shorter = shorter.next; // 移动较短链表的指针
            longer = longer.next; // 移动较长链表的指针
        }

        /* 
         * 返回交点节点。
         */
        return longer; // 或者返回 shorter，因为此时 shorter == longer
    }

    public static void main(String[] args) {
        /* 创建第一个链表 */
        int[] vals = {-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        LinkedListNode list1 = AssortedMethods.createLinkedListFromArray(vals); // 使用工具方法创建链表

        /* 创建第二个链表 */
        int[] vals2 = {12, 14, 15};
        LinkedListNode list2 = AssortedMethods.createLinkedListFromArray(vals2); // 使用工具方法创建链表

        /* 设置两个链表的交点 */
        list2.next.next = list1.next.next.next.next; // 将第二个链表的最后一个节点指向第一个链表的某个节点

        /* 打印两个链表的内容 */
        System.out.println(list1.printForward()); // 打印第一个链表
        System.out.println(list2.printForward()); // 打印第二个链表

        /* 查找并打印交点 */
        LinkedListNode intersection = findIntersection(list1, list2); // 调用方法查找交点
        System.out.println(intersection.printForward()); // 打印交点及其后续节点
    }
}