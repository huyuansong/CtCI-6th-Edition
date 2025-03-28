package Q3_03_Stack_of_Plates;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * SetOfStacks 类实现了一个“栈的集合”，当一个栈达到指定容量时，
 * 会自动创建一个新的栈来存储后续的数据。
 */
public class SetOfStacksBack {
    // 存储所有栈的列表
    ArrayList<Stack> stacks = new ArrayList<Stack>();
    // 每个栈的最大容量
    public int capacity;

    /**
     * 构造函数，初始化每个栈的容量。
     *
     * @param capacity 每个栈的最大容量
     */
    public SetOfStacksBack(int capacity) { 
        this.capacity = capacity; 
    }

    /**
     * 获取当前集合中的最后一个栈。
     *
     * @return 最后一个栈，如果没有栈则返回 null
     */
    public Stack getLastStack() {
        if (stacks.size() == 0) { // 如果集合中没有栈，返回 null
            return null;
        }
        return stacks.get(stacks.size() - 1); // 返回集合中的最后一个栈
    }

    /**
     * 向集合中压入一个新元素。
     *
     * @param v 要压入的值
     */
    public void push(int v) {
        Stack last = getLastStack(); // 获取最后一个栈
        if (last != null && !last.isFull()) { // 如果最后一个栈存在且未满
            last.push(v); // 将值压入最后一个栈
        } else { // 如果最后一个栈不存在或已满
            Stack stack = new Stack(capacity); // 创建一个新的栈
            stack.push(v); // 将值压入新栈
            stacks.add(stack); // 将新栈添加到集合中
        }
    }

    /**
     * 从集合中弹出一个元素。
     *
     * @return 弹出的值
     * @throws EmptyStackException 如果集合为空，则抛出异常
     */
    public int pop() {
        Stack last = getLastStack(); // 获取最后一个栈
        if (last == null) throw new EmptyStackException(); // 如果集合为空，抛出异常
        int v = last.pop(); // 从最后一个栈中弹出一个元素
        if (last.size == 0) { // 如果最后一个栈为空
            stacks.remove(stacks.size() - 1); // 移除最后一个栈
        }
        return v; // 返回弹出的值
    }

    /**
     * 从指定索引的栈中弹出一个元素，并调整后续栈的结构。
     *
     * @param index 要弹出元素的栈的索引
     * @return 弹出的值
     */
    public int popAt(int index) {
        return leftShift(index, true); // 调用 leftShift 方法，从指定栈中弹出顶部元素
    }

    /**
     * 从指定索引的栈中移除一个元素，并将后续栈的元素向前移动以填补空缺。
     *
     * @param index 要操作的栈的索引
     * @param removeTop 是否移除栈顶元素（true 表示移除栈顶，false 表示移除栈底）
     * @return 移除的值
     */
    public int leftShift(int index, boolean removeTop) {
        Stack stack = stacks.get(index); // 获取指定索引的栈
        int removed_item;

        // 根据 removeTop 参数决定移除栈顶还是栈底元素
        if (removeTop) 
            removed_item = stack.pop(); // 移除栈顶元素
        else 
            removed_item = stack.removeBottom(); // 移除栈底元素

        // 如果当前栈变为空，则从集合中移除该栈
        if (stack.isEmpty()) {
            stacks.remove(index);
        } 
        // 如果集合中还有后续栈，则将后续栈的底部元素移动到当前栈的顶部
        else if (stacks.size() > index + 1) {
            int v = leftShift(index + 1, false); // 递归调用，从下一个栈中移除底部元素
            stack.push(v); // 将移除的底部元素压入当前栈的顶部
            /**
             *  1. 压入元素：
                    压入 1、2、3：第一个栈 [1, 2, 3]。
                    压入 4、5、6：第二个栈 [4, 5, 6]。
                    压入 7：第三个栈 [7]。
                2. 弹出元素：
                    调用 pop()：弹出 7，第三个栈变为空并被移除。
                    调用 pop()：弹出 6，第二个栈变为 [4, 5]。
                3. 从指定栈弹出：
                    调用 popAt(0)：从第一个栈中弹出 3，调整后续栈的结构。
                    第一个栈变为 [1, 2, 4]，第二个栈变为 [5, 6]。

             * 
             */
        }
        return removed_item; // 返回移除的值
    }

    /**
     * 判断集合是否为空。
     *
     * @return 如果集合为空，返回 true；否则返回 false
     */
    public boolean isEmpty() {
        Stack last = getLastStack(); // 获取最后一个栈
        return last == null || last.isEmpty(); // 如果最后一个栈为空或不存在，则集合为空
    }
}