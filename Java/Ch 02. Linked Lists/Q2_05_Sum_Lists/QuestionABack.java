package Q2_05_Sum_Lists;


import CtCILibrary.LinkedListNode;

public class QuestionABack {
    /**
     * 递归地将两个链表相加，并处理进位。
     * 
     * @param l1 第一个链表的当前节点
     * @param l2 第二个链表的当前节点
     * @param carry 进位值（初始为0）
     * @return 返回相加结果的新链表头节点
     */
    private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
        // 如果两个链表都为空且没有进位，则返回null，表示递归结束
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        // 创建一个新的链表节点用于存储当前位的结果
        LinkedListNode result = new LinkedListNode();

        // 初始化当前位的值为进位值
        int value = carry;

        // 如果l1不为空，将l1的当前节点值加入到value中
        if (l1 != null) {
            value += l1.data;
        }

        // 如果l2不为空，将l2的当前节点值加入到value中
        if (l2 != null) {
            value += l2.data;
        }

        // 将value对10取模，得到当前位的最终值
        result.data = value % 10;

        // 如果l1或l2还有后续节点，或者存在进位，则递归处理下一位
        if (l1 != null || l2 != null) {
            // 递归调用addLists方法，传入l1和l2的下一个节点以及新的进位值
            LinkedListNode more = addLists(
                l1 == null ? null : l1.next,   // 如果l1为空，则传null，否则传下一个节点
                l2 == null ? null : l2.next,   // 如果l2为空，则传null，否则传下一个节点
                value >= 10 ? 1 : 0             // 如果value大于等于10，则进位为1，否则为0
            );

            // 将递归返回的节点设置为当前节点的下一个节点
            result.setNext(more);
        }

        // 返回当前节点作为结果链表的一部分
        return result;
    }


}