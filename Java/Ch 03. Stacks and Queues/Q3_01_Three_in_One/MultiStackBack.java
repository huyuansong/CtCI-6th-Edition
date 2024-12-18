package Q3_01_Three_in_One;
import java.util.EmptyStackException;
import CtCILibrary.AssortedMethods;

public class MultiStack {
    /* StackInfo 是一个简单的类，用于存储每个栈的一些数据。
     * 它不存储栈的实际元素。虽然我们可以用一堆单独的变量来实现，
     * 但这会很混乱，而且并没有带来多少好处。 */
    private class StackInfo {
        public int start, size, capacity; // 定义栈的起始位置、当前大小和容量

        public StackInfo(int start, int capacity) {
            this.start = start; // 初始化栈的起始位置
            this.capacity = capacity; // 初始化栈的容量
        }

        /* 检查一个索引是否在栈的边界内。栈可以环绕到数组的开头。 */
        public boolean isWithinStackCapacity(int index) {
            /* 如果索引超出数组范围，返回 false。 */
            if (index < 0 || index >= values.length) {
                return false; // 如果索引超出数组范围，返回 false
            }

            /* 如果索引环绕，调整它。 */
            int contiguousIndex = index < start ? index + values.length : index;
            // 如果索引小于起始位置，调整索引
            int end = start + capacity;
            return start <= contiguousIndex && contiguousIndex < end;
            // 检查调整后的索引是否在栈的范围内
        }

        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
            // 返回栈的最后一个容量索引
        }

        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
            // 返回栈的最后一个元素索引
        }

        public boolean isFull() {
            return size == capacity;
            // 检查栈是否已满
        }

        public boolean isEmpty() {
            return size == 0;
            // 检查栈是否为空
        }
    }

    private StackInfo[] info; // 存储每个栈的信息
    private int[] values; // 存储所有栈的值

    public MultiStack(int numberOfStacks, int defaultSize) {
        /* 创建所有栈的元数据。 */
        info = new StackInfo[numberOfStacks];
        // 创建所有栈的信息数组
        for (int i = 0; i < numberOfStacks; i++) {
            info[i] = new StackInfo(defaultSize * i, defaultSize);
            // 初始化每个栈的信息
        }
        values = new int[numberOfStacks * defaultSize];
        // 初始化存储所有栈值的数组
    }

    /* 返回栈中实际存在的元素数量。 */
    public int numberOfElements() {
        int size = 0;
        for (StackInfo sd : info) {
            size += sd.size;
            // 计算所有栈的总大小
        }
        return size;
    }

    /* 返回所有栈是否都已满。 */
    public boolean allStacksAreFull() {
        return numberOfElements() == values.length;
        // 检查所有栈是否已满
    }

    /* 调整索引，使其在 0 到 length - 1 的范围内。 */
    private int adjustIndex(int index) {
        /* Java 的模运算符可以返回负值。例如，(-11 % 5) 将返回 -1，而不是 4。
         * 我们实际上希望值为 4（因为我们正在环绕索引）。 */
        int max = values.length;
        return ((index % max) + max) % max;
        // 调整索引，使其在 0 到 length - 1 的范围内
    }

    /* 获取索引的下一个位置，考虑环绕。 */
    private int nextIndex(int index) {
        return adjustIndex(index + 1);
        // 获取索引的下一个位置，考虑环绕
    }

    /* 获取索引的前一个位置，考虑环绕。 */
    private int previousIndex(int index) {
        return adjustIndex(index - 1);
        // 获取索引的前一个位置，考虑环绕
    }

    /* 移动栈中的元素，如果需要，还会移动其他栈。 */
    private void shift(int stackNum) {
        System.out.println("/// 移动栈 " + stackNum);
        StackInfo stack = info[stackNum];
        /* 如果这个栈已满，需要移动下一个栈的一个元素。这个栈现在可以声明释放的索引。 */
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack); 
            stack.capacity++; // 声明索引，增加容量
        }
        /* 移动栈中的所有元素。 */
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }
        /* 调整栈的数据。 */
        values[stack.start] = 0; // 清空项
        stack.start = nextIndex(stack.start); // 移动起始位置
        stack.capacity--; // 缩小容量
    }

    /* 通过移动其他栈来扩展栈。 */
    private void expand(int stackNum) {
        System.out.println("/// 扩展栈 " + stackNum);
        shift((stackNum + 1) % info.length);
        info[stackNum].capacity++;
        // 扩展栈，通过移动其他栈
    }

    /* 将值推入指定栈，必要时移动/扩展栈。如果所有栈已满，抛出异常。 */
    public void push(int stackNum, int value) throws FullStackException {
        System.out.println("/// 推入栈 " + stackNum + ": " + value);
        if (allStacksAreFull()) {
            throw new FullStackException();
        }
        /* 如果这个栈已满，扩展它。 */
        StackInfo stack = info[stackNum];
        if (stack.isFull()) {
            expand(stackNum);
        }
        /* 找到数组中栈顶元素的下一个位置，并增加栈指针。 */
        stack.size++;    
        values[stack.lastElementIndex()] = value;    
        // 将值推入栈
    }

    /* 从指定栈中移除值。 */
    public int pop(int stackNum) throws Exception {
        System.out.println("/// 弹出栈 " + stackNum);
        StackInfo stack = info[stackNum];        
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        /* 移除最后一个元素。 */
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0; // 清空项
        stack.size--; // 缩小大小
        return value;
        // 从栈中弹出值
    }

    /* 获取指定栈的栈顶元素。 */
    public int peek(int stackNum) {
        StackInfo stack = info[stackNum];            
        return values[stack.lastElementIndex()];
        // 获取栈顶元素
    }

    public int[] getValues() {
        return values;
        // 返回所有栈的值
    }

    public int[] getStackValues(int stackNum) {
        StackInfo stack = info[stackNum];
        int[] items = new int[stack.size];
        for (int i = 0; i < items.length; i++) {
            items[i] = values[adjustIndex(stack.start + i)];
        }
        return items;
        // 返回指定栈的所有值
    }

    public String stackToString(int stackNum) {
        int[] items = getStackValues(stackNum);
        return stackNum + ": " + AssortedMethods.arrayToString(items);
        // 将指定栈转换为字符串
    }
}