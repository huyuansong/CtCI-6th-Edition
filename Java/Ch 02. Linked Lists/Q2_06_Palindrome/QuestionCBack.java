public class QuestionC {
    // 定义一个名为QuestionC的公共类

    public static class Result {
        // 定义一个内部静态类Result，用于存储递归结果
        public LinkedListNode node;
        // 存储链表节点
        public boolean result;
        // 存储是否为回文的结果
        public Result(LinkedListNode n, boolean res) {
            // 构造函数，初始化节点和结果
            node = n;
            result = res;
        }
    }

    public static Result isPalindromeRecurse(LinkedListNode head, int length) {
        // 定义一个公共静态方法isPalindromeRecurse，用于递归判断链表是否为回文
        if (head == null || length <= 0) { // Even number of nodes
            // 如果链表为空或长度为0（偶数个节点），返回当前节点和true
            return new Result(head, true);
        } else if (length == 1) { // Odd number of nodes
            // 如果长度为1（奇数个节点），返回下一个节点和true
            return new Result(head.next, true);
        } 
        
        /* Recurse on sublist. */
        // 递归调用isPalindromeRecurse，处理子链表，长度减2
        Result res = isPalindromeRecurse(head.next, length - 2);
        
        /* If child calls are not a palindrome, pass back up 
         * a failure. */
        // 如果子链表不是回文，直接返回失败结果
        if (!res.result || res.node == null) {
            return res;
        } 
        
        /* Check if matches corresponding node on other side. */
        // 检查当前节点是否与对应节点的数据相等
        res.result = (head.data == res.node.data); 
        
        /* Return corresponding node. */
        // 返回对应节点的下一个节点
        res.node = res.node.next;
        
        return res;
        // 返回结果
    }
    
    public static int lengthOfList(LinkedListNode n) {
        // 定义一个公共静态方法lengthOfList，用于计算链表的长度
        int size = 0;
        // 初始化计数器为0
        while (n != null) {
            // 遍历链表
            size++;
            // 计数器加1
            n = n.next;
            // 移动到下一个节点
        }
        return size;
        // 返回链表的长度
    }
    
    public static boolean isPalindrome(LinkedListNode head) {
        // 定义一个公共静态方法isPalindrome，用于判断链表是否为回文
        int length = lengthOfList(head);
        // 计算链表的长度
        Result p = isPalindromeRecurse(head, length);
        // 调用isPalindromeRecurse方法，判断链表是否为回文
        return p.result;
        // 返回结果
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