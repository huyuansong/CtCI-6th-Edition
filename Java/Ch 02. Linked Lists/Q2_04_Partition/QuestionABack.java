package Q2_04_Partition;

import CtCILibrary.LinkedListNode;

public class QuestionABack {
    /**
     * 根据给定的值 x 对链表进行分区。
     * 小于 x 的节点会被放置在链表的前半部分，大于或等于 x 的节点会被放置在后半部分。
     * 
     * @param node 链表的头节点（LinkedListNode 类型）
     * @param x 分区的基准值（int 类型）
     * @return 返回重新分区后的链表头节点（LinkedListNode 类型）
     */
    public static LinkedListNode partition(LinkedListNode node, int x) {
        // 定义四个指针，用于分别记录小于 x 和大于等于 x 的链表的起始和结束节点
        LinkedListNode beforeStart = null; // 小于 x 的链表起始节点
        LinkedListNode beforeEnd = null;   // 小于 x 的链表结束节点
        LinkedListNode afterStart = null;  // 大于等于 x 的链表起始节点
        LinkedListNode afterEnd = null;    // 大于等于 x 的链表结束节点

        /* 遍历链表并根据节点值与 x 的大小关系将其分为两部分 */
        while (node != null) {
            LinkedListNode next = node.next; // 保存当前节点的下一个节点
            node.next = null;               // 断开当前节点与后续节点的连接

            // 如果当前节点值小于 x，则将其加入到小于 x 的链表中
            if (node.data < x) {
                if (beforeStart == null) { // 如果小于 x 的链表为空，初始化该链表
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else { // 否则将当前节点追加到小于 x 的链表末尾
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            } else { // 如果当前节点值大于或等于 x，则将其加入到大于等于 x 的链表中
                if (afterStart == null) { // 如果大于等于 x 的链表为空，初始化该链表
                    afterStart = node;
                    afterEnd = afterStart;
                } else { // 否则将当前节点追加到大于等于 x 的链表末尾
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }

            node = next; // 移动到下一个节点
        }

        /* 合并小于 x 和大于等于 x 的两部分链表 */
        if (beforeStart == null) { // 如果小于 x 的链表为空，直接返回大于等于 x 的链表
            return afterStart;
        }

        // 将小于 x 的链表的末尾连接到大于等于 x 的链表的头部
        beforeEnd.next = afterStart;
        return beforeStart; // 返回合并后链表的头节点
    }


}