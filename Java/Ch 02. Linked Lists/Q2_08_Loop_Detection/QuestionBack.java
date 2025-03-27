package Q2_08_Loop_Detection;

import CtCILibrary.LinkedListNode;

/**
 * 注释说明：

    快慢指针法：
    使用两个指针（慢指针 slow 和快指针 fast）遍历链表。
    慢指针每次移动一步，快指针每次移动两步。
    如果链表中存在环，快慢指针会在环内相遇。

    环的检测：
    如果快指针或快指针的下一个节点为 null，说明链表没有环。
    如果快慢指针相遇，说明链表中存在环。

    环起点的计算：
    将慢指针移回头节点，快指针保持在相遇点。
    两个指针以相同速度移动，再次相遇时即为环的起点。
    
    主方法逻辑：
    创建一个包含 100 个节点的链表，并在链表尾部创建一个环。
    调用 FindBeginning 方法查找环的起点，并输出结果
 */
public class QuestionBack {

    /**
     * 查找链表中环的起点。
     *
     * @param head 链表的头节点
     * @return 返回环的起点节点，如果没有环则返回 null
     */
    public static LinkedListNode FindBeginning(LinkedListNode head) {
        LinkedListNode slow = head; // 慢指针，每次移动一步
        LinkedListNode fast = head; // 快指针，每次移动两步

        /* 
         * 第一步：找到快慢指针的相遇点。
         * 如果链表中存在环，快慢指针一定会在环内相遇。
         */
        while (fast != null && fast.next != null) { 
            slow = slow.next; // 慢指针移动一步
            fast = fast.next.next; // 快指针移动两步
            if (slow == fast) { // 如果快慢指针相遇，说明存在环
                break;
            }
        }

        /* 
         * 第二步：检查是否存在环。
         * 如果快指针或快指针的下一个节点为 null，则链表没有环。
         */
        if (fast == null || fast.next == null) {
            return null; // 返回 null 表示链表没有环
        }

        /* 
         * 第三步：找到环的起点。
         * 将慢指针移回头节点，快指针保持在相遇点。
         * 两个指针以相同速度移动，再次相遇时即为环的起点。
         */
        slow = head; // 慢指针重新指向头节点
        while (slow != fast) { // 当慢指针和快指针不相等时
            slow = slow.next; // 慢指针移动一步
            fast = fast.next; // 快指针移动一步
        }

        /* 
         * 第四步：返回环的起点。
         * 此时慢指针和快指针都指向环的起点。
         */
        return fast; // 返回环的起点节点
    }

    public static void main(String[] args) {
        int list_length = 100; // 定义链表的长度
        int k = 10; // 定义环的起始位置距离链表尾部的距离

        /* 创建链表 */
        LinkedListNode[] nodes = new LinkedListNode[list_length];
        for (int i = 0; i < list_length; i++) {
            nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null); // 创建节点并连接前一个节点
        }

        /* 创建环 */
        nodes[list_length - 1].next = nodes[list_length - k]; // 将链表尾节点指向倒数第 k 个节点，形成环

        /* 查找环的起点 */
        LinkedListNode loop = FindBeginning(nodes[0]); // 调用方法查找环的起点
        if (loop == null) {
            System.out.println("No Cycle."); // 如果没有环，打印 "No Cycle."
        } else {
            System.out.println(loop.data); // 如果有环，打印环的起点节点的数据
        }
    }
}