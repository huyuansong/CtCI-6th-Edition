public class QuestionA {
    // 定义一个名为QuestionA的公共类

    public static boolean isPalindrome(LinkedListNode head) {
        // 定义一个公共静态方法isPalindrome，用于判断链表是否为回文链表
        LinkedListNode reversed = reverseAndClone(head);
        // 调用reverseAndClone方法，将原链表反转并克隆，返回反转后的链表
        return isEqual(head, reversed);
        // 调用isEqual方法，比较原链表和反转后的链表是否相等，返回结果
    }

    public static LinkedListNode reverseAndClone(LinkedListNode node) {
        // 定义一个公共静态方法reverseAndClone，用于反转并克隆链表
        LinkedListNode head = null;
        // 初始化一个新链表的头节点为null
        while (node != null) {
            // 遍历原链表
            LinkedListNode n = new LinkedListNode(node.data); // Clone
            // 创建一个新的节点，数据为当前节点的数据（克隆）
            n.next = head;
            // 将新节点的next指针指向当前的新链表头节点
            head = n;
            // 更新新链表的头节点为新创建的节点
            node = node.next;
            // 移动到原链表的下一个节点
        }
        return head;
        // 返回反转并克隆后的新链表的头节点
    }

    public static boolean isEqual(LinkedListNode one, LinkedListNode two) {
        // 定义一个公共静态方法isEqual，用于比较两个链表是否相等
        while (one != null && two != null) {
            // 遍历两个链表，直到其中一个链表为空
            if (one.data != two.data) {
                // 如果两个节点的数据不相等，返回false
                return false;
            }
            one = one.next;
            // 移动到第一个链表的下一个节点
            two = two.next;
            // 移动到第二个链表的下一个节点
        }
        return one == null && two == null;
        // 如果两个链表同时为空，返回true，否则返回false
    }

    public static void main(String[] args) {
        // 定义主方法main，程序的入口点
        int length = 9;
        // 定义链表的长度为9
        LinkedListNode[] nodes = new LinkedListNode[length];
        // 创建一个长度为9的数组，用于存储链表的节点
        for (int i = 0; i < length; i++) {
            // 遍历数组，初始化每个节点
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
            // 创建一个新的节点，数据为i（前半部分）或length - i - 1（后半部分），初始前后指针为null
        }

        for (int i = 0; i < length; i++) {
            // 再次遍历数组，设置每个节点的前后指针
            if (i < length - 1) {
                nodes[i].setNext(nodes[i + 1]);
                // 设置当前节点的下一个节点为数组中的下一个节点
            }
            if (i > 0) {
                nodes[i].setPrevious(nodes[i - 1]);
                // 设置当前节点的前一个节点为数组中的前一个节点
            }
        }
        // nodes[length - 2].data = 9; // Uncomment to ruin palindrome
        // 可选代码：将倒数第二个节点的数据改为9，破坏回文结构

        LinkedListNode head = nodes[0];
        // 获取链表的头节点
        System.out.println(head.printForward());
        // 打印链表的正向表示
        System.out.println(isPalindrome(head));
        // 判断链表是否为回文链表，并打印结果
    }
}