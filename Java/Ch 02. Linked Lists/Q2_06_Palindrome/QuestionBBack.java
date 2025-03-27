package Q2_06_Palindrome;

import CtCILibrary.LinkedListNode;

import java.util.Stack;

/**
 *  快慢指针：通过快慢指针找到链表的中点。快指针每次移动两步，慢指针每次移动一步，当快指针到达链表末尾时，慢指针正好位于链表中点。
    栈的使用：利用栈存储链表前半部分的节点数据，然后与后半部分的节点数据进行比较，判断是否为回文。
    奇偶处理：如果链表长度为奇数，需要跳过中间节点，只比较前后对称的部分。
    链表构建：通过数组和循环构建一个对称的链表，并连接节点形成完整的链表结构。
 */
public class QuestionBBack {
    // 判断链表是否为回文的方法
    public static boolean isPalindrome(LinkedListNode head) {
        LinkedListNode fast = head; // 定义快指针，初始指向链表头节点
        LinkedListNode slow = head; // 定义慢指针，初始指向链表头节点
        
        Stack<Integer> stack = new Stack<Integer>(); // 创建一个栈，用于存储前半部分链表的节点数据

        // 使用快慢指针遍历链表，将前半部分链表的节点数据压入栈中
        while (fast != null && fast.next != null) { 
            stack.push(slow.data); // 将慢指针当前节点的数据压入栈中
            slow = slow.next; // 慢指针每次移动一步
            fast = fast.next.next; // 快指针每次移动两步
        }

        /* 如果链表长度为奇数，则跳过中间节点 */
        if (fast != null) { 
            slow = slow.next; // 慢指针跳过中间节点，继续指向后半部分的第一个节点
        }

        // 比较后半部分链表与栈中的数据是否相同
        while (slow != null) { 
            int top = stack.pop().intValue(); // 从栈中弹出一个数据
            if (top != slow.data) { // 如果栈中的数据与当前慢指针指向的节点数据不同
                return false; // 返回 false，表示链表不是回文
            }
            slow = slow.next; // 慢指针移动到下一个节点
        }
        return true; // 如果所有数据都匹配，则返回 true，表示链表是回文
    }

    public static void main(String[] args) {
        int length = 9; // 定义链表的长度
        LinkedListNode[] nodes = new LinkedListNode[length]; // 创建一个数组存储链表节点

        // 构建链表节点，生成对称的链表数据
        for (int i = 0; i < length; i++) {
            // 如果 i 大于等于链表长度的一半，则设置数据为对称值
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

        // 下面这行代码可以取消注释以破坏回文结构
        // nodes[length - 2].data = 9;

        // 获取链表的头节点
        LinkedListNode head = nodes[0];
        // 打印链表内容
        System.out.println(head.printForward());
        // 判断并打印链表是否为回文
        System.out.println(isPalindrome(head));
    }
}