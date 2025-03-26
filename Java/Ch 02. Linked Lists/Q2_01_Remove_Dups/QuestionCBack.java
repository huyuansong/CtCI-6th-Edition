package Q2_01_Remove_Dups;

import CtCILibrary.LinkedListNode;

public class QuestionCBack {
    /**
 * 删除链表中的重复节点（不使用额外空间）
 * 
 * @param head 链表的头节点
 */
    public static void deleteDups(LinkedListNode head) {
    // 如果链表为空，直接返回
    if (head == null) return;
    
    // previous指针指向当前处理节点的前一个节点
    LinkedListNode previous = head;
    // current指针从头节点的下一个节点开始遍历
    LinkedListNode current = previous.next;

    // 遍历链表中的每个节点
    while (current != null) {
        /*
         * 使用runner指针从头节点开始向后查找，检查是否存在与current节点值相同的节点。
         * 如果找到，则删除current节点。
         */
        LinkedListNode runner = head;
        
        // 遍历从头节点到current节点之间的所有节点
        while (runner != current) { 
            if (runner.data == current.data) {
                // 找到重复节点，删除current节点
                LinkedListNode tmp = current.next;
                previous.next = tmp;
                current = tmp;
                /*
                 * 由于在之前的步骤中已经删除了前面的重复节点，
                 * 因此current节点之前不可能存在多个重复节点。
                 */
                break;
            }
            runner = runner.next;
        }

        /*
         * 如果runner == current，说明在之前的循环中没有找到重复节点，
         * 此时需要将previous和current指针向前移动。
         * 如果runner != current，说明在之前的循环中找到了重复节点并已删除，
         * 此时current指针已经被更新，无需再次移动。
         */
        if (runner == current) {
            previous = current;
            current = current.next;
        }
    }
}
	
	public static void main(String[] args) {	
		LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
		LinkedListNode head = first;
		LinkedListNode second = first;
		for (int i = 1; i < 8; i++) {
			second = new LinkedListNode(i % 2, null, null);
			first.setNext(second);
			second.setPrevious(first);
			first = second;
		}
		System.out.println(head.printForward());
		deleteDups(head);
	}
}
