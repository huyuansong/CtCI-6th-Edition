public class QuestionB {
    // 定义一个类QuestionB

    private static int length(LinkedListNode l) {
        // 定义一个私有静态方法length，用于计算链表的长度
        if (l == null) {
            // 如果链表为空，返回0
            return 0;
        } else {
            // 否则，返回1加上剩余链表的长度
            return 1 + length(l.next);
        }
    }

    private static PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
        // 定义一个私有静态方法addListsHelper，用于递归地将两个链表相加
        if (l1 == null && l2 == null) {
            // 如果两个链表都为空，创建并返回一个空的PartialSum对象
            PartialSum sum = new PartialSum();
            return sum;
        }
        // 递归调用addListsHelper，处理两个链表的下一个节点
        PartialSum sum = addListsHelper(l1.next, l2.next);
        // 计算当前节点的值，包括前一个节点的进位值
        int val = sum.carry + l1.data + l2.data;
        // 创建一个新的节点，值为val % 10，并将其插入到当前结果链表的前面
        LinkedListNode full_result = insertBefore(sum.sum, val % 10);
        // 更新sum的sum字段为新的结果链表
        sum.sum = full_result;
        // 更新sum的carry字段为新的进位值
        sum.carry = val / 10;
        // 返回更新后的PartialSum对象
        return sum;
    }

    private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2) {
        // 定义一个私有静态方法addLists，用于将两个链表相加
        int len1 = length(l1);
        // 计算链表l1的长度
        int len2 = length(l2);
        // 计算链表l2的长度
        if (len1 < len2) {
            // 如果l1的长度小于l2的长度，将l1补齐到与l2相同长度
            l1 = padList(l1, len2 - len1);
        } else {
            // 否则，将l2补齐到与l1相同长度
            l2 = padList(l2, len1 - len2);
        }
        // 调用addListsHelper方法，处理两个补齐后的链表
        PartialSum sum = addListsHelper(l1, l2);
        if (sum.carry == 0) {
            // 如果没有进位，直接返回结果链表
            return sum.sum;
        } else {
            // 如果有进位，创建一个新的节点，值为进位值，并将其插入到结果链表的前面
            LinkedListNode result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    private static LinkedListNode padList(LinkedListNode l, int padding) {
        // 定义一个私有静态方法padList，用于在链表前面添加指定数量的0节点
        LinkedListNode head = l;
        // 初始化head为链表的头节点
        for (int i = 0; i < padding; i++) {
            // 循环添加padding个0节点
            head = insertBefore(head, 0);
        }
        // 返回新的头节点
        return head;
    }

    private static LinkedListNode insertBefore(LinkedListNode list, int data) {
        // 定义一个私有静态方法insertBefore，用于在链表前面插入一个新节点
        LinkedListNode node = new LinkedListNode(data);
        // 创建一个新的节点，数据为data
        if (list != null) {
            // 如果链表不为空，将新节点的next指针指向当前链表的头节点
            node.next = list;
        }
        // 返回新节点，作为新的头节点
        return node;
    }

    public static int linkedListToInt(LinkedListNode node) {
        // 定义一个公共静态方法linkedListToInt，用于将链表转换为整数
        int value = 0;
        // 初始化value为0
        while (node != null) {
            // 遍历链表
            value = value * 10 + node.data;
            // 将当前节点的数据添加到value中
            node = node.next;
            // 移动到下一个节点
        }
        // 返回转换后的整数
        return value;
    }
}