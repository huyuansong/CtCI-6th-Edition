package Q2_04_Partition;

import CtCILibrary.LinkedListNode;

public class QuestionBBack {
    /**
     * 根据给定的值 x 对链表进行分区。
     * 小于 x 的节点会被放置在链表的前半部分，大于或等于 x 的节点会被放置在后半部分。
     * 
     * @param node 链表的头节点（LinkedListNode 类型）
     * @param x 分区的基准值（int 类型）
     * @return 返回重新分区后的链表头节点（LinkedListNode 类型）
     */
    public static LinkedListNode partition(LinkedListNode node, int x) {
        // 定义两个指针，分别用于记录小于 x 和大于等于 x 的链表的起始节点
        LinkedListNode beforeStart = null; // 小于 x 的链表起始节点
        LinkedListNode afterStart = null;  // 大于等于 x 的链表起始节点

        /* 迭代法 遍历链表并根据节点值与 x 的大小关系将其分为两部分 */
        while (node != null) {
            LinkedListNode next = node.next; // 保存当前节点的下一个节点

            // 如果当前节点值小于 x，则将其插入到小于 x 的链表头部
            if (node.data < x) {
                node.next = beforeStart; // 将当前节点插入到 beforeStart 的前面
                beforeStart = node;      // 更新 beforeStart 指针
            } else { // 如果当前节点值大于或等于 x，则将其插入到大于等于 x 的链表头部
                node.next = afterStart; // 将当前节点插入到 afterStart 的前面
                afterStart = node;      // 更新 afterStart 指针
            }

            node = next; // 移动到下一个节点
        }

        /* 段注释：后面的代码都是：合并小于 x 和大于等于 x 的两部分链表 */
        if (beforeStart == null) { // 如果小于 x 的链表为空，直接返回大于等于 x 的链表
            return afterStart;
        }

        // 找到小于 x 的链表的最后一个节点
        LinkedListNode head = beforeStart; // 保存小于 x 的链表头节点
        while (beforeStart.next != null) {
            beforeStart = beforeStart.next; // 遍历到小于 x 的链表末尾
        }

        // 将小于 x 的链表末尾连接到大于等于 x 的链表头部
        beforeStart.next = afterStart;
        return head; // 返回合并后链表的头节点
    }


	/**
	 * 主函数执行链表的创建、分割和打印操作
	 */
	public static void main(String[] args) {
	    // 定义链表的长度为20
	    int length = 20;
	    // 创建一个数组来存储链表节点
	    LinkedListNode[] nodes = new LinkedListNode[length];
	    
	    // 初始化链表节点，前半部分节点的值递增，后半部分节点的值递减
	    for (int i = 0; i < length; i++) {
	        nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
	    }
	    
	    // 连接链表节点，设置每个节点的下一个和上一个节点指针
	    for (int i = 0; i < length; i++) {
	        if (i < length - 1) {
	            nodes[i].setNext(nodes[i + 1]);
	        }
	        if (i > 0) {
	            nodes[i].setPrevious(nodes[i - 1]);
	        }
	    }
	    
	    // 获取链表的头节点
	    LinkedListNode head = nodes[0];
	    // 打印原始链表
	    System.out.println(head.printForward());
	    
	    // 对链表进行分割，分割点为值为7的节点
	    LinkedListNode h = partition(head, 7);
	    // 打印分割后的链表
	    System.out.println(h.printForward());
	}
}