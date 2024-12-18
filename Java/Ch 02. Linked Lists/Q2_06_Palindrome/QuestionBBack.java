public class QuestionB {
    // 定义一个名为QuestionB的公共类

    public static boolean isPalindrome(LinkedListNode head) {
        // 定义一个公共静态方法isPalindrome，用于判断链表是否为回文链表
        LinkedListNode fast = head;
        // 初始化快指针fast，指向链表的头节点
        LinkedListNode slow = head;
        // 初始化慢指针slow，指向链表的头节点
        
        Stack<Integer> stack = new Stack<Integer>();
        // 创建一个栈，用于存储链表前半部分的节点数据
        
        while (fast != null && fast.next != null) {
            // 使用快慢指针遍历链表，直到快指针到达链表末尾或倒数第二个节点
            stack.push(slow.data);
            // 将慢指针当前节点的数据压入栈中
            slow = slow.next;
            // 慢指针向前移动一个节点
            fast = fast.next.next;
            // 快指针向前移动两个节点
        }
        
        /* Has odd number of elements, so skip the middle */
        if (fast != null) { 
            // 如果快指针不为空，说明链表长度为奇数，需要跳过中间节点
            slow = slow.next;
            // 慢指针向前移动一个节点，跳过中间节点
        }
        
        while (slow != null) {
            // 继续遍历链表的后半部分
            int top = stack.pop().intValue();
            // 从栈中弹出一个元素，获取其整数值
            if (top != slow.data) {
                // 如果栈顶元素与当前慢指针节点的数据不相等，返回false
                return false;
            }
            slow = slow.next;
            // 慢指针向前移动一个节点
        }
        return true;
        // 如果遍历完后半部分链表，所有对应节点的数据都相等，返回true
    }
}