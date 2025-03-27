package Q2_06_Palindrome;

// 导入自定义的链表节点类
import CtCILibrary.LinkedListNode;

public class QuestionABack {

    // 判断链表是否为回文的方法
    public static boolean isPalindrome(LinkedListNode head) {
        // 1. 先将链表反转并克隆一份
        LinkedListNode reversed = reverseAndClone(head);
        // 2. 比较原链表和反转后的链表是否相等
        return isEqual(head, reversed);
    }

    // 反转并克隆链表的方法
    public static LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null; // 初始化新的头节点为 null
        while (node != null) { // 遍历原始链表
            // 1. 创建一个新的节点，克隆当前节点的数据
            LinkedListNode n = new LinkedListNode(node.data);
            // 2. 将新节点插入到新链表的头部（实现反转）
            n.next = head; // n-> head,原链表的头变成了新链表的尾部
            head = n;
            // 3. 移动到原始链表的下一个节点
            node = node.next;
        }
        return head; // 返回反转后的链表头节点
    }

    // 比较两个链表是否相等的方法
    public static boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != null) { // 同时遍历两个链表
            if (one.data != two.data) { // 如果任意一个节点的数据不同
                return false; // 返回 false，表示不相等
            }
            one = one.next; // 移动到第一个链表的下一个节点
            two = two.next; // 移动到第二个链表的下一个节点
        }
        // 当两个链表同时遍历完且长度相等时，返回 true
        return one == null && two == null;
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        int length = 9; // 定义链表的长度
        LinkedListNode[] nodes = new LinkedListNode[length]; // 创建一个数组存储链表节点

        // 构建链表节点
        for (int i = 0; i < length; i++) {
            // 如果 i 大于等于链表长度的一半，则将节点的数据设置为 length - i - 1，这样可以保证链表的后半部分是前半部分的对称
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
        }

        // 连接链表节点
        for (int i = 0; i < length; i++) {
            if (i < length - 1) { // 设置当前节点的下一个节点
                nodes[i].setNext(nodes[i + 1]);
            }
            if (i > 0) { // 设置当前节点的上一个节点
                nodes[i].setPrevious(nodes[i - 1]);
            }
        }

        // 下面这行代码可以取消注释以破坏回文结构
        // nodes[length - 2].data = 9;

        // 获取链表的头节点
        LinkedListNode head = nodes[0];
        // 打印链表内容
        System.out.println(head.printForward());
        // 判断并打印链表是否为回文
        System.out.println(isPalindrome(head));
    }
}