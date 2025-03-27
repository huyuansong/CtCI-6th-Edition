package Q2_06_Palindrome;

import CtCILibrary.LinkedListNode;

/***
 *  递归逻辑：通过递归从链表尾部开始逐步向前比较，判断链表是否为回文。
    Result 类：用于封装递归过程中返回的节点和结果，便于在递归中传递状态。
    链表长度计算：通过 lengthOfList 方法计算链表的长度，为递归提供参数。
    链表构建：通过数组和循环构建一个对称的链表，并连接节点形成完整的链表结构。
 */
public class QuestionCBack {

    // 定义一个内部类 Result，用于存储递归过程中返回的结果
    public static class Result {
        public LinkedListNode node; // 当前比较的节点
        public boolean result; // 是否为回文的结果

        // 构造函数，初始化节点和结果
        public Result(LinkedListNode n, boolean res) {
            node = n;
            result = res;
        }
    }

    /**
     * 递归判断链表是否为回文的方法。
     * 通过递归遍历链表的后半部分，并与前半部分进行比较。
     *
     * @param head   链表的头节点
     * @param length 链表的长度
     * @return 返回一个 Result 对象，包含当前比较的节点和是否为回文的结果
     * 
     * length <= 0 并不是直接表示链表长度为偶数，而是递归终止条件的一部分。
        当链表长度为偶数时，递归最终会进入 length == 0 的情况；当链表长度为奇数时，递归最终会进入 length == 1 的情况。
        通过这两种终止条件，代码能够正确处理链表长度为偶数或奇数的情况。
     */
    public static Result isPalindromeRecurse(LinkedListNode head, int length) {
        if (head == null || length <= 0) { // 如果链表为空或长度为偶数（<=0）
            return new Result(head, true); // 返回当前节点和 true
        } else if (length == 1) { // 如果链表长度为奇数（==1）
            return new Result(head.next, true); // 跳过中间节点，返回下一个节点和 true
        }

        /* 
         * 递归处理子链表。
         * 每次递归时，减少长度 2，跳过链表的前两个节点。
         */
        Result res = isPalindromeRecurse(head.next, length - 2);

        /* 
         * 如果子链表不是回文，直接将失败结果传递回去。
         * 如果 res.result 为 false 或 res.node 为 null，则直接返回。
         */
        if (!res.result || res.node == null) {
            return res;
        }

        /* 
         * 检查当前节点是否与对应位置的节点匹配。
         * 如果不匹配，则设置 res.result 为 false。
         */
        res.result = (head.data == res.node.data);

        /* 
         * 返回对应的下一个节点。
         * 将 res.node 移动到下一个节点，以便上一层递归继续比较。
         */
        res.node = res.node.next;

        return res; // 返回结果
    }

    /**
     * 计算链表的长度。
     *
     * @param n 链表的头节点
     * @return 链表的长度
     */
    public static int lengthOfList(LinkedListNode n) {
        int size = 0; // 初始化长度为 0
        while (n != null) { // 遍历链表
            size++; // 每遍历一个节点，长度加 1
            n = n.next; // 移动到下一个节点
        }
        return size; // 返回链表的长度
    }

    /**
     * 判断链表是否为回文的主方法。
     *
     * @param head 链表的头节点
     * @return 如果是回文链表返回 true，否则返回 false
     */
    public static boolean isPalindrome(LinkedListNode head) {
        int length = lengthOfList(head); // 获取链表的长度
        Result p = isPalindromeRecurse(head, length); // 调用递归方法判断是否为回文
        return p.result; // 返回最终的结果
    }

    public static void main(String[] args) {
        int length = 9; // 定义链表的长度
        LinkedListNode[] nodes = new LinkedListNode[length]; // 创建一个数组存储链表节点

        // 构建链表节点，生成对称的链表数据
        for (int i = 0; i < length; i++) {
            /*
             * 如果 i 大于等于链表长度的一半，则设置数据为对称值。
             * 前半部分的节点数据递增，后半部分的节点数据与前半部分对称。
             */
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
        }

        // 连接链表节点，构建完整的链表结构
        for (int i = 0; i < length; i++) {
            if (i < length - 1) { // 设置当前节点的下一个节点
                nodes[i].setNext(nodes[i + 1]);
            }
            if (i > 0) { // 设置当前节点的上一个节点
                nodes[i].setPrevious(nodes[i - 1]);
            }
        }

        /*
         * 下面这行代码可以取消注释以破坏回文结构。
         * 将倒数第二个节点的数据改为 9，从而破坏链表的对称性。
         */
        // nodes[length - 2].data = 9;

        // 获取链表的头节点
        LinkedListNode head = nodes[0];
        // 打印链表内容
        System.out.println(head.printForward());
        // 判断并打印链表是否为回文
        System.out.println(isPalindrome(head));
    }
}