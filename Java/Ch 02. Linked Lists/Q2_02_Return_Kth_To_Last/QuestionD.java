package Q2_02_Return_Kth_To_Last;

import CtCILibrary.*;

public class QuestionD {
	
	public static LinkedListNode nthToLast(LinkedListNode head, int k) {
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		
		/* Move p1 k nodes into the list.*/
		for (int i = 0; i < k; i++) {
			if (p1 == null) return null; // Out of bounds
			p1 = p1.next;
		}
		
		/* Move them at the same pace. When p1 hits the end, 
		 * p2 will be at the right element. */
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
	  	}
	  	return p2;
	}
	
	/**
	 * 找到链表中倒数第 k 个节点。
	 * 
	 * @param head 链表的头节点。如果链表为空或长度不足 k，则返回 null。
	 * @param k 倒数的位置，从 1 开始计数。例如，k=1 表示最后一个节点，k=2 表示倒数第二个节点。
	 * @return 返回倒数第 k 个节点。如果 k 超过链表长度或链表为空，则返回 null。
	 */
	public static LinkedListNode nthToLast2(LinkedListNode head, int k) {
		// 初始化两个指针 p1 和 p2，都指向链表的头节点。
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;

		// 将指针 p1 向前移动 k 步，使其与 p2 之间保持 k 个节点的距离。
		for (int i = 0; i < k; i++) {
			if (p1 == null) return null; // 如果链表长度小于 k，则返回 null。
			p1 = p1.next;
		}

		// 同时移动 p1 和 p2，直到 p1 到达链表末尾。
		// 此时 p2 指向的就是倒数第 k 个节点。
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}

		// 返回 p2，即为倒数第 k 个节点。
		return p2;
	}


	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3};
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
		for (int i = 0; i <= array.length + 1; i++) {
			LinkedListNode node = nthToLast(head, i);
			String nodeValue = node == null ? "null" : "" + node.data;
			System.out.println(i + ": " + nodeValue);
		}
	}

}
