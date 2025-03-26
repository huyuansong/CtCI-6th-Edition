package Q2_01_Remove_Dups;

import java.util.HashSet;
import CtCILibrary.LinkedListNode;

public class QuestionABack {
    /** 
     * 该方法通过使用HashSet来跟踪链表中已经出现过的元素，从而实现删除重复元素的目的
     * 
     * @param n 链表的头节点，表示从该节点开始处理删除重复元素的操作
     */
    public static void deleteDups(LinkedListNode n) {
        // 使用HashSet存储已经遇到的数据值，以实现去重
        HashSet<Integer> set = new HashSet<Integer>();
        // previous用于记录前一个节点，以便在发现重复元素时重新链接节点
        LinkedListNode previous = null;
        // 遍历链表
        while (n != null) {
            // 如果当前节点的数据值已经存在于HashSet中，则说明遇到了重复元素
            if (set.contains(n.data)) {
                // 删除重复元素，通过将前一个节点的next指针指向当前节点的下一个节点
                previous.next = n.next;
            } else {
                // 如果当前节点的数据值尚未存在于HashSet中，则将其添加到HashSet中
                set.add(n.data);
                // 更新previous为当前节点
                previous = n;
            }
            // 移动到下一个节点
            n = n.next;
        }
    }                                                   

}