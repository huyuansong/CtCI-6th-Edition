package Q2_02_Return_Kth_To_Last;

import CtCILibrary.*;

public class QuestionC {
	public static class Index {
		public int value = 0;
	}	
	
	public static LinkedListNode kthToLast(LinkedListNode head, int k) {
		Index idx = new Index();
		return kthToLast(head, k, idx);
	}
	
	public static LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
		if (head == null) {
			return null;
		}
		LinkedListNode node = kthToLast(head.next, k, idx);
		idx.value = idx.value + 1;
		if (idx.value == k) {
			return head;
		} 
		return node;
	}		

	/**
	 * 返回链表中倒数第k个节点
	 * 此方法通过递归方式实现，从链表尾部开始计数，当计数器等于k时返回对应的节点
	 * 
	 * @param head 链表的头节点，用于遍历链表
	 * @param k 指定的倒数位置，表示需要返回的是倒数第几个节点
	 * @param idx 一个封装了整数的Index对象，用作计数器，跟踪当前节点的倒数位置。如果使用int，则无法在递归的不同层次函数中共用一个计数器。
	 * ****** 使用 Index 封装类的核心目的是为了实现递归调用之间的状态共享
	 * @return 返回倒数第k个节点，如果不存在，则返回null
	 */
	public static LinkedListNode kthToLast2(LinkedListNode head, int k, Index idx) {
	    // 当链表为空时，返回null，表示已经到达链表末尾
	    if (head == null) {
	        return null;
	    }
	    
	    // 递归调用，向链表尾部遍历
	    LinkedListNode node = kthToLast(head.next, k, idx);
	    
	    // 计数器加1，表示已经遍历到倒数第几个节点
	    idx.value = idx.value + 1;
	    
	    // 如果计数器等于k，表示当前节点就是倒数第k个节点，返回当前节点
	    if (idx.value == k) {
	        return head;
	    } 
	    
	    // 如果当前节点不是倒数第k个节点，返回之前递归调用的结果
	    return node;
	}
	
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3, 4, 5, 6};
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
		for (int i = 0; i <= array.length + 1; i++) {
			LinkedListNode node = kthToLast(head, i);
			String nodeValue = node == null ? "null" : "" + node.data;
			System.out.println(i + ": " + nodeValue);
		}
	}
}
