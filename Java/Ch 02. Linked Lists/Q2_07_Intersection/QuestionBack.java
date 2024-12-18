public class Question {
    // 定义一个名为Question的公共类

    public static class Result {
        // 定义一个内部静态类Result，用于存储链表的尾节点和大小
        public LinkedListNode tail;
        // 存储链表的尾节点
        public int size;
        // 存储链表的大小（节点数）
        public Result(LinkedListNode tail, int size) {
            // 构造函数，初始化尾节点和大小
            this.tail = tail;
            this.size = size;
        }
    }

    public static Result getTailAndSize(LinkedListNode list) {
        // 定义一个公共静态方法getTailAndSize，用于获取链表的尾节点和大小
        if (list == null) return null;
        // 如果链表为空，返回null

        int size = 1;
        // 初始化链表大小为1
        LinkedListNode current = list;
        // 初始化当前节点为链表的头节点
        while (current.next != null) {
            // 遍历链表，直到当前节点的下一个节点为空
            size++;
            // 每遍历一个节点，大小加1
            current = current.next;
            // 移动到下一个节点
        }
        return new Result(current, size);
        // 返回包含尾节点和大小的Result对象
    }

    public static LinkedListNode getKthNode(LinkedListNode head, int k) {
        // 定义一个公共静态方法getKthNode，用于获取链表中第k个节点
        LinkedListNode current = head;
        // 初始化当前节点为链表的头节点
        while (k > 0 && current != null) {
            // 遍历链表，直到k为0或当前节点为空
            current = current.next;
            // 移动到下一个节点
            k--;
            // k减1
        }
        return current;
        // 返回第k个节点，如果k大于链表长度，返回null
    }

    public static LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
        // 定义一个公共静态方法findIntersection，用于找到两个链表的交点
        if (list1 == null || list2 == null) return null;
        // 如果任一链表为空，返回null

        /* Get tail and sizes. */
        // 获取两个链表的尾节点和大小
        Result result1 = getTailAndSize(list1);
        Result result2 = getTailAndSize(list2);

        /* If different tail nodes, then there's no intersection. */
        // 如果两个链表的尾节点不同，说明没有交点，返回null
        if (result1.tail != result2.tail) {
            return null;
        }

        /* Set pointers to the start of each linked list. */
        // 设置指针指向两个链表的头节点
        LinkedListNode shorter = result1.size < result2.size ? list1 : list2;
        // 较短的链表
        LinkedListNode longer = result1.size < result2.size ? list2 : list1;
        // 较长的链表

        /* Advance the pointer for the longer linked list by the difference in lengths. */
        // 将较长链表的指针向前移动，使其与较短链表的指针对齐
        longer = getKthNode(longer, Math.abs(result1.size - result2.size));

        /* Move both pointers until you have a collision. */
        // 同时移动两个指针，直到它们相遇
        while (shorter != longer) {
            shorter = shorter.next;
            // 移动较短链表的指针
            longer = longer.next;
            // 移动较长链表的指针
        }

        /* Return either one. */
        // 返回相遇的节点（即交点）
        return longer; 
    }
}