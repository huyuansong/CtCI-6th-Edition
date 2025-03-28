package Q3_02_Stack_Min;

import java.util.Stack;

/**
 * StackWithMin 类继承自 Java 的 Stack 类，用于实现一个带有最小值功能的栈。
 * 每次压入元素时，栈会记录当前栈中的最小值。
 * Stack<NodeWithMin> 栈中存放的元素是NodeWithMin类型的对象，其中包含两个属性：value和min，分别表示当前节点的值和最小值。
 */
public class StackWithMinBackUp extends Stack<NodeWithMin> { 

    /**
     * 向栈中压入一个新元素。
     *
     * @param value 要压入栈的值
     */
    public void push(int value) {
        // 计算当前栈中的最小值。如果栈为空，则最小值为要压入的值；
        // 如果栈不为空，则最小值为当前值和栈顶节点最小值之间的较小值。
        int newMin = Math.min(value, min());

        // 创建一个新的 NodeWithMin 节点，并将其压入栈中。
        super.push(new NodeWithMin(value, newMin));
    }

    /**
     * 获取当前栈中的最小值。
     *
     * @return 当前栈中的最小值
     */
    public int min() {
        // 如果栈为空，返回 Integer.MAX_VALUE，表示没有最小值。
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            // 如果栈不为空，返回栈顶节点的最小值（NodeWithMin 中的 min 属性）。
            return peek().min; // peek出的节点是NodeWithMin，然后直接从节点上读取属性
        }
    }
}