public class QuestionC {
    // 内部静态类，用于存储递归过程中的结果和当前节点
    public static class Result {
        public LinkedListNode node; // 当前节点
        public boolean result; // 是否为回文

        // Result类的构造函数，初始化节点和结果
        public Result(LinkedListNode n, boolean res) {
            node = n;
            result = res;
        }
    }

    // 递归函数，用于判断链表是否为回文
    public static Result isPalindromeRecurse(LinkedListNode head, int length) {
        // 如果链表为空或长度为0，返回true（回文）
        if (head == null || length <= 0) { 
            return new Result(head, true);
        } else if (length == 1) { // 如果链表长度为1，也是回文
            return new Result(head.next, true);
        } 

        /* 递归调用函数，处理子链表（跳过当前头节点和尾节点） */
        Result res = isPalindromeRecurse(head.next, length - 2);

        /* 如果子链表不是回文或者子链表的尾节点为空，直接返回结果 */
        if (!res.result || res.node == null) {
            return res;
        } 

        /* 检查当前头节点的值是否与子链表的尾节点的值相等 */
        res.result = (head.data == res.node.data); 

        /* 如果相等，更新子链表的尾节点为下一个节点 */
        res.node = res.node.next;

        return res;
    }

    // 计算链表的长度
    public static int lengthOfList(LinkedListNode n) {
        int size = 0;
        while (n != null) {
            size++; // 遍历链表，计数节点
            n = n.next;
        }
        return size;
    }

    // 判断链表是否为回文的函数
    public static boolean isPalindrome(LinkedListNode head) {
        int length = lengthOfList(head); // 计算链表长度
        Result p = isPalindromeRecurse(head, length); // 递归判断回文
        return p.result; // 返回判断结果
    }
}
