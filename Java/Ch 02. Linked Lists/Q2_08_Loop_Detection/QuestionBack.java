public class Question {
    // 定义一个名为Question的公共类

    public static LinkedListNode FindBeginning(LinkedListNode head) {
        // 定义一个公共静态方法FindBeginning，用于找到链表环的起始节点
        LinkedListNode slow = head;
        // 初始化慢指针slow，指向链表的头节点
        LinkedListNode fast = head; 
        // 初始化快指针fast，指向链表的头节点
        
        // Find meeting point
        // 寻找快慢指针的相遇点
        while (fast != null && fast.next != null) { 
            // 遍历链表，直到快指针到达链表末尾或倒数第二个节点
            slow = slow.next; 
            // 慢指针向前移动一个节点
            fast = fast.next.next;
            // 快指针向前移动两个节点
            if (slow == fast) {
                // 如果快慢指针相遇，跳出循环
                break;
            }
        }

        // Error check - there is no meeting point, and therefore no loop
        // 错误检查 - 如果没有相遇点，说明链表没有环
        if (fast == null || fast.next == null) {
            // 如果快指针到达链表末尾或倒数第二个节点，返回null
            return null;
        }

        /* Move slow to Head. Keep fast at Meeting Point. Each are k steps
         * from the Loop Start. If they move at the same pace, they must
         * meet at Loop Start. */
        // 将慢指针移回链表头，快指针保持在相遇点。两者都距离环起点k步。如果它们以相同的速度移动，它们必须在环起点相遇。
        slow = head; 
        // 将慢指针移回链表头
        while (slow != fast) { 
            // 同时移动慢指针和快指针，直到它们相遇
            slow = slow.next; 
            fast = fast.next; 
        
        }
        
        // Both now point to the start of the loop.
        // 现在慢指针和快指针都指向环的起始节点
        return fast;
        // 返回环的起始节点
    }
    
    public static void main(String[] args) {
        int list_length = 100;
        // 定义链表的长度为100
        int k = 10;
        // 定义环的长度为10
        
        // Create linked list
        // 创建链表
        LinkedListNode[] nodes = new LinkedListNode[list_length];
        // 创建一个长度为100的数组，用于存储链表的节点
        for (int i = 0; i < list_length; i++) {
            // 遍历数组，初始化每个节点
            nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
            // 创建一个新的节点，数据为i，初始下一个节点为null，前一个节点为上一个节点
        }
        
        // Create loop
        // 创建环
        nodes[list_length - 1].next = nodes[list_length - k];
        // 将最后一个节点的下一个节点指向第(list_length - k)个节点，形成环

        LinkedListNode loop = FindBeginning(nodes[0]);
        // 调用FindBeginning方法，找到环的起始节点
        if (loop == null) {
            // 如果没有环，打印"No Cycle."
            System.out.println("No Cycle.");
        } else {
            // 如果有环，打印环的起始节点的数据
            System.out.println(loop.data);
        }
    }
}