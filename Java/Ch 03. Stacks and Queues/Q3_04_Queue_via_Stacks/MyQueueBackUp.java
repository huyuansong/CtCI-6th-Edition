package Q3_04_Queue_via_Stacks;

import java.util.Stack;

/**
 * MyQueue 类通过两个栈（stackNewest 和 stackOldest）实现队列的功能。
 * 其中：
 * - stackNewest 用于存储新加入的元素。
 * - stackOldest 用于存储即将被移除的元素。
 */
public class MyQueueBackUp<T> {
    // 用于存储新加入的元素
    Stack<T> stackNewest;
    
    // 用于存储即将被移除的元素
    Stack<T> stackOldest;

    /**
     * 构造函数，初始化两个栈。
     */
    public MyQueueBackUp() {
        stackNewest = new Stack<T>(); // 初始化存储新加入元素的栈
        stackOldest = new Stack<T>(); // 初始化存储即将被移除元素的栈
    }

    /**
     * 获取当前队列的大小。
     *
     * @return 队列的大小
     */
    public int size() {
        return stackNewest.size() + stackOldest.size(); // 队列的大小等于两个栈的大小之和
    }

    /**
     * 向队列中添加一个新元素。
     *
     * @param value 要添加的值
     */
    public void add(T value) {
        // 将新元素压入 stackNewest 栈
        stackNewest.push(value);
    }

    /**
     * 将 stackNewest 中的所有元素转移到 stackOldest 中。
     * 这通常是为了在 stackOldest 上执行操作（如 peek 或 remove）。
     */
    private void shiftStacks() {
        if (stackOldest.isEmpty()) { // 如果 stackOldest 为空
            while (!stackNewest.isEmpty()) { // 将 stackNewest 中的所有元素转移到 stackOldest
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    /**
     * 获取队列的第一个元素（但不移除它）。
     *
     * @return 队列的第一个元素
     */
    public T peek() {
        shiftStacks(); // 确保 stackOldest 中有元素
        return stackOldest.peek(); // 返回 stackOldest 的栈顶元素（即队列的第一个元素）
    }

    /**
     * 移除并返回队列的第一个元素。
     *
     * @return 被移除的元素
     */
    public T remove() {
        shiftStacks(); // 确保 stackOldest 中有元素
        return stackOldest.pop(); // 弹出 stackOldest 的栈顶元素（即队列的第一个元素）
    }
}