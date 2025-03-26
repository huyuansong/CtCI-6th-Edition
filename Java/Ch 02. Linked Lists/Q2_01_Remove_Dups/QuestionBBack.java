/**
 * 删除链表中的重复节点（不使用额外空间）
 * 
 * @param head 链表的头节点
 */
public static void deleteDups(LinkedListNode head) {
    // 当前节点指针，从头节点开始遍历
    LinkedListNode current = head;
    
    // 遍历链表中的每个节点
    while (current != null) {
        /* 
         * 使用runner指针从当前节点出发，检查后续节点是否存在与当前节点值相同的节点，
         * 如果存在则删除该节点。
         */
        LinkedListNode runner = current;
        
        // 遍历当前节点之后的所有节点
        while (runner.next != null) { 
            if (runner.next.data == current.data) {
                // 如果下一个节点的值与当前节点相同，则跳过下一个节点（删除操作）
                runner.next = runner.next.next;
            } else {
                // 如果下一个节点的值不同，则移动runner指针到下一个节点
                runner = runner.next;
            }
        }
        // 移动current指针到下一个节点，继续处理剩余链表
        current = current.next;
    }
}