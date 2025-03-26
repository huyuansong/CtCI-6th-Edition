package Q2_04_Partition;

import CtCILibrary.LinkedListNode;

public class QuestionCBack {
    /**
     * 对链表进行分区操作，将所有小于x的节点移动到链表的前半部分，
     * 所有大于等于x的节点移动到链表的后半部分。
     * 
     * @param node 链表的头节点
     * @param x 分区值，用于区分节点的归属
     * @return 返回重新分区后的链表头节点
     */
    public static LinkedListNode partition(LinkedListNode node, int x) {
        // 初始化头节点和尾节点，初始时都指向链表的第一个节点
        LinkedListNode head = node;
        LinkedListNode tail = node;
        
        /* 遍历链表并进行分区操作 ，链表没有被拆分为两个，所以后续不需要合并 */
        while (node != null) {
            // 保存当前节点的下一个节点，以便继续遍历
            LinkedListNode next = node.next;
            
            // 如果当前节点的数据小于分区值x，则将其插入到链表头部
            if (node.data < x) {
                node.next = head; // 将当前节点的next指针指向原来的头节点
                head = node; // 更新头节点为当前节点
            } else {
                // 如果当前节点的数据大于或等于分区值x，则将其插入到链表尾部
                tail.next = node; // 将尾节点的next指针指向当前节点
                tail = node; // 更新尾节点为当前节点
            }
            
            // 移动到下一个节点继续处理
            node = next;
        }
        
        // 设置尾节点的next指针为null，确保链表结束
        tail.next = null;
        
        // 这里没有两个链表合并的部分，返回重新分区后的链表头节点
        return head;
    }

    public static void main(String[] args) {
		int length = 20;
		LinkedListNode[] nodes = new LinkedListNode[length];
		for (int i = 0; i < length; i++) {
			nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
		}
		
		for (int i = 0; i < length; i++) {
			if (i < length - 1) {
				nodes[i].setNext(nodes[i + 1]);
			}
			if (i > 0) {
				nodes[i].setPrevious(nodes[i - 1]);
			}
		}
		
		LinkedListNode head = nodes[0];
		System.out.println(head.printForward());
		
		LinkedListNode h = partition(head, 8);
		System.out.println(h.printForward());
	}

}