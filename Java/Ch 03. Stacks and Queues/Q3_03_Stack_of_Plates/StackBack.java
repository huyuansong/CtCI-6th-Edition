package Q3_03_Stack_of_Plates;

import java.util.EmptyStackException;

/**
 * Stack 类实现了一个具有固定容量的栈。
 * 栈中的每个节点由 Node 类表示，栈支持基本的压入、弹出和移除底部元素的操作。
 */
public class StackBack {
    // 栈的最大容量
    private int capacity;
    // 栈顶节点
    public Node top;
    // 栈底节点
    public Node bottom;
    // 栈中当前元素的数量
    public int size = 0;

    /**
     * 构造函数，初始化栈的容量。
     *
     * @param capacity 栈的最大容量
     */
    public StackBack(int capacity) { 
        this.capacity = capacity; 
    }

    /**
     * 判断栈是否已满。
     *
     * @return 如果栈已满，返回 true；否则返回 false
     */
    public boolean isFull() { 
        return capacity == size; 
    }

    /**
     * 将两个节点连接起来。
     *
     * @param above 上方的节点
     * @param below 下方的节点
     */
    public void join(Node above, Node below) {
        if (below != null) below.above = above; // 如果下方节点存在，则将其上方指针指向上方节点
        if (above != null) above.below = below; // 如果上方节点存在，则将其下方指针指向下方节点
    }

    /**
     * 向栈中压入一个新元素。
     *
     * @param v 要压入的值
     * @return 如果成功压入，返回 true；如果栈已满，则返回 false
     */
    public boolean push(int v) {
        if (size >= capacity) return false; // 如果栈已满，返回 false
        size++; // 增加栈的大小
        Node n = new Node(v); // 创建一个新的节点
        if (size == 1) bottom = n; // 如果这是第一个元素，则将该节点设置为栈底节点
        join(n, top); // 将新节点与当前栈顶节点连接起来
        top = n; // 更新栈顶节点为新节点
        return true; // 返回 true 表示成功压入
    }

    /**
     * 从栈中弹出一个元素。
     *
     * @return 弹出的值
     * @throws EmptyStackException 如果栈为空，则抛出异常
     */
    public int pop() {
        if (top == null) throw new EmptyStackException(); // 如果栈为空，抛出异常
        Node t = top; // 获取当前栈顶节点，做备份，要删除top
        top = top.below; // 更新栈顶节点为当前栈顶节点的下方节点,删除top
        size--; // 减少栈的大小
        return t.value; // 返回弹出的值
    }

    /**
     * 判断栈是否为空。
     *
     * @return 如果栈为空，返回 true；否则返回 false
     */
    public boolean isEmpty() { 
        return size == 0; 
    }

    /**
     * 从栈底移除一个元素。
     *
     * @return 移除的值
     */
    public int removeBottom() {
        Node b = bottom; // 获取栈底节点
        bottom = bottom.above; // 更新栈底节点为当前栈底节点的上方节点
        if (bottom != null) bottom.below = null; // 如果新的栈底节点存在，则将其下方指针置为 null
        size--; // 减少栈的大小
        return b.value; // 返回移除的值
    }
}