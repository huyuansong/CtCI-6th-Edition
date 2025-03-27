package Q2_05_Sum_Lists;
import CtCILibrary.LinkedListNode;

public class QuestionBBack {

    /**
     * 计算链表的长度。
     * 
     * @param l 链表头节点
     * @return 返回链表的长度
     */
    private static int length(LinkedListNode l) {
        if (l == null) {
            return 0; // 如果链表为空，返回长度为0
        } else {
            return 1 + length(l.next); // 递归计算链表长度
        }
    }

    /**
     * 辅助函数，用于递归地将两个相同长度的链表相加。
     * 
     * @param l1 第一个链表的当前节点
     * @param l2 第二个链表的当前节点
     * @return 返回包含部分和与进位的PartialSum对象
     */
    private static PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum(); // 创建一个新的PartialSum对象
            return sum; // 如果两个链表都为空，返回空的PartialSum
        }

        // 递归处理下一位
        PartialSum sum = addListsHelper(l1.next, l2.next);

        // 计算当前位的值，加上进位、l1.data和l2.data
        int val = sum.carry + l1.data + l2.data;

        // 创建一个新的节点，存储当前位的结果，并插入到结果链表的前面
        LinkedListNode full_result = insertBefore(sum.sum, val % 10);

        // 更新sum对象的sum字段为新的结果链表
        sum.sum = full_result;

        // 计算新的进位
        sum.carry = val / 10;

        return sum; // 返回更新后的PartialSum对象
    }

    /**
     * 将两个链表相加，支持不同长度的链表。
     * 
     * @param l1 第一个链表头节点
     * @param l2 第二个链表头节点
     * @return 返回相加结果的新链表头节点
     */
    private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2) {
        int len1 = length(l1); // 计算第一个链表的长度
        int len2 = length(l2); // 计算第二个链表的长度

        // 如果两个链表长度不同，对较短的链表进行补零操作
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

        // 调用辅助函数addListsHelper进行递归相加
        PartialSum sum = addListsHelper(l1, l2);

        // 如果存在最高位进位，创建一个新的节点存储进位值
        if (sum.carry == 0) {
            return sum.sum; // 如果没有进位，直接返回结果链表
        } else {
            LinkedListNode result = insertBefore(sum.sum, sum.carry); // 插入进位值
            return result; // 返回最终结果链表
        }
    }

    /**
     * 对链表进行补零操作，使其达到指定长度。
     * 
     * @param l 原始链表头节点
     * @param padding 补零的数量
     * @return 返回补零后的链表头节点
     */
    private static LinkedListNode padList(LinkedListNode l, int padding) {
        LinkedListNode head = l; // 初始化head为原始链表头节点
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0); // 在链表头部插入0
        }
        return head; // 返回补零后的链表头节点
    }

    /**
     * 在链表头部插入一个新节点。
     * 
     * @param list 原始链表头节点
     * @param data 新节点的数据值
     * @return 返回插入新节点后的链表头节点
     */
    private static LinkedListNode insertBefore(LinkedListNode list, int data) {
        LinkedListNode node = new LinkedListNode(data); // 创建一个新的节点
        if (list != null) {
            node.next = list; // 将新节点的next指向原始链表头节点
        }
        return node; // 返回新节点作为新的链表头节点
    }

    /**
     * 将链表转换为整数。
     * 
     * @param node 链表头节点
     * @return 返回链表表示的整数值
     */
    public static int linkedListToInt(LinkedListNode node) {
        int value = 0; // 初始化结果值为0
        while (node != null) {
            value = value * 10 + node.data; // 按位累加链表中的数据
            node = node.next; // 移动到下一个节点
        }
        return value; // 返回最终的整数值
    }

    public static void main(String[] args) {
        // 创建链表lA: 3 -> 1
        LinkedListNode lA1 = new LinkedListNode(3, null, null);
        LinkedListNode lA2 = new LinkedListNode(1, null, lA1);

        // 创建链表lB: 5 -> 9 -> 1
        LinkedListNode lB1 = new LinkedListNode(5, null, null);
        LinkedListNode lB2 = new LinkedListNode(9, null, lB1);
        LinkedListNode lB3 = new LinkedListNode(1, null, lB2);

        // 调用addLists方法将两个链表相加
        LinkedListNode list3 = addLists(lA1, lB1);

        // 打印链表内容
        System.out.println("  " + lA1.printForward());
        System.out.println("+ " + lB1.printForward());
        System.out.println("= " + list3.printForward());

        // 将链表转换为整数并打印结果
        int l1 = linkedListToInt(lA1);
        int l2 = linkedListToInt(lB1);
        int l3 = linkedListToInt(list3);

        System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
        System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));
    }
}