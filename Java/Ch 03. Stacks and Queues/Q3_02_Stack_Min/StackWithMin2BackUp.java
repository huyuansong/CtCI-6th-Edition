package Q3_02_Stack_Min;

import java.util.Stack;

/**
 * StackWithMin2 类用于实现一个带有最小值功能的栈。
 * 该类通过维护一个辅助栈（s2）来记录当前栈中的最小值。
 */
public class StackWithMin2BackUp extends Stack<Integer> {
    // 辅助栈，用于存储当前栈中的最小值
    Stack<Integer> s2;

    /**
     * 构造函数，初始化辅助栈 s2。
     */
    public StackWithMin2BackUp() {
        s2 = new Stack<Integer>(); // 创建辅助栈
    }

    /**
     * 向栈中压入一个新元素。
     *
     * @param value 要压入栈的值
     */
    public void push(int value) {
        // 如果新值小于或等于当前栈中的最小值，则将新值压入辅助栈 s2。
        if (value <= min()) {
            s2.push(value); // 将新值压入辅助栈 s2
        }
        super.push(value); // 将新值压入主栈
    }

    /**
     * 从栈中弹出一个元素。
     *
     * @return 弹出的值
     */
    public Integer pop() {
        int value = super.pop(); // 从主栈中弹出一个元素

        // 如果弹出的值等于当前栈中的最小值，则同时从辅助栈 s2 中弹出一个元素。
        if (value == min()) {
            s2.pop(); // 从辅助栈 s2 中弹出一个元素
        }
        return value; // 返回弹出的值
    }

    /**
     * 获取当前栈中的最小值。
     *
     * @return 当前栈中的最小值
     */
    public int min() {
        // 如果辅助栈 s2 为空，返回 Integer.MAX_VALUE，表示没有最小值。
        if (s2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            // 如果辅助栈 s2 不为空，返回辅助栈 s2 的栈顶元素，即当前栈中的最小值。
            return s2.peek();
        }
    }
}