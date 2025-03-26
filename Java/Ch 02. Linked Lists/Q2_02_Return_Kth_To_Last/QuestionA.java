package Q2_02_Return_Kth_To_Last;

import CtCILibrary.*;

public class QuestionA {

	public static int printKthToLast(LinkedListNode head, int k) {
		if (head == null) {
			return 0;
		}
		int index = printKthToLast(head.next, k) + 1;
		if (index == k) {
			System.out.println(k + "th to last node is " + head.data);
		}
		return index;
	}

	/**
	 * 打印链表中倒数第k个节点的值
	 * 该方法通过递归的方式遍历链表，从链表尾部开始计数，当计数等于k时，打印当前节点的值
	 * 
	 * @param head 链表的头节点，用于遍历链表
	 * @param k 指定的倒数第k个位置
	 * @return 当前节点在链表中的位置，从链表尾部开始计数
	 */
	public static int printKthToLast2(LinkedListNode head, int k) {
		// 判断链表是否为空，如果为空则返回0，表示已经遍历到链表末尾
		if (head == null) {
			return 0;
		}
		// 递归调用，继续遍历下一个节点，并将返回的位置加1，表示当前节点的位置
		int index = printKthToLast(head.next, k) + 1;
		// 判断当前节点是否是倒数第k个节点，如果是，则打印其值
		if (index == k) {
			System.out.println(k + "th to last node is " + head.data);
		}
		// 返回当前节点的位置，用于上一级递归的判断
		return index;
	}
	
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3, 4, 5, 6};
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
		for (int i = 0; i <= array.length + 1; i++) {
			printKthToLast(head, i);
		}
	}

}
