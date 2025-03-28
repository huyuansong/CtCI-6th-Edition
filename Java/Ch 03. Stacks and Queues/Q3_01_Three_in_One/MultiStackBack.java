package Q3_01_Three_in_One;

import java.util.EmptyStackException;

import CtCILibrary.AssortedMethods;

public class MultiStackBack {
    /* 
     * StackInfo 是一个简单的类，用于保存每个堆栈的相关信息。
     * 它不存储堆栈的实际元素，而是保存堆栈的起始位置、大小和容量等元数据。
     */
    private class StackInfo {
        public int start; // 堆栈在数组中的起始索引
        public int size; // 堆栈当前的大小
        public int capacity; // 堆栈的最大容量

        // 构造函数，初始化堆栈的起始位置和容量
        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        /* 
         * 检查给定的索引是否在堆栈的有效范围内。
         * 由于堆栈可能会在数组中“环绕”，因此需要特殊处理。
         */
        public boolean isWithinStackCapacity(int index) {
            /* 如果索引超出数组范围，返回 false。*/
            if (index < 0 || index >= values.length) {
                return false;
            }

            /* 如果索引小于堆栈的起始位置，则将其调整为环绕后的有效索引。*/
            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity; // 堆栈的结束位置（不包括该位置）
            return start <= contiguousIndex && contiguousIndex < end; // 判断索引是否在堆栈范围内
        }

        // 返回堆栈最后一个可用容量的索引
        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
        }

        // 返回堆栈最后一个元素的索引
        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        // 判断堆栈是否已满
        public boolean isFull() {
            return size == capacity;
        }

        // 判断堆栈是否为空
        public boolean isEmpty() {
            return size == 0;
        }
    }

    // 存储所有堆栈的元数据
    private StackInfo[] info;
    // 存储所有堆栈元素的数组
    private int[] values;

    /**
     * 构造函数，初始化多堆栈。
     *
     * @param numberOfStacks 堆栈的数量
     * @param defaultSize 每个堆栈的默认容量
     */
    public MultiStackBack(int numberOfStacks, int defaultSize) {
        /* 创建所有堆栈的元数据。*/
        info = new StackInfo[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            info[i] = new StackInfo(defaultSize * i, defaultSize); // 初始化每个堆栈的起始位置和容量
        }
        values = new int[numberOfStacks * defaultSize]; // 初始化存储所有堆栈元素的数组
    }

    /**
     * 返回所有堆栈中实际存在的元素总数。
     *
     * @return 元素总数
     */
    public int numberOfElements() {
        int size = 0;
        for (StackInfo sd : info) {
            size += sd.size; // 累加每个堆栈的大小
        }
        return size;
    }

    /**
     * 判断所有堆栈是否都已满。
     *
     * @return 如果所有堆栈都已满，返回 true；否则返回 false
     */
    public boolean allStacksAreFull() {
        return numberOfElements() == values.length; // 如果元素总数等于数组长度，则所有堆栈都已满
    }

    /**
     * 调整索引，使其在数组范围内（0 到 length-1）。
     *
     * @param index 待调整的索引
     * @return 调整后的索引
     */
    private int adjustIndex(int index) {
        /* Java 的取模运算可能会返回负值。例如，(-11 % 5) 返回 -1，但我们希望结果是 4。
         * 因此需要对结果进行修正。*/
        int max = values.length;
        return ((index % max) + max) % max;
    }

    /**
     * 获取指定索引的下一个索引，并调整以适应环绕。
     *
     * @param index 当前索引
     * @return 下一个索引
     */
    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    /**
     * 获取指定索引的上一个索引，并调整以适应环绕。
     *
     * @param index 当前索引
     * @return 上一个索引
     */
    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }

    /**
     * 将指定堆栈的所有元素向右移动一位。
     * 如果堆栈已满，则需要调整下一个堆栈的容量。
     *
     * @param stackNum 堆栈编号
     */
    private void shift(int stackNum) {
        System.out.println("/// Shifting " + stackNum);
        StackInfo stack = info[stackNum];

        /* 如果当前堆栈已满，则需要将下一个堆栈向右移动一位，
         * 并释放一个空位供当前堆栈使用。*/
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNum + 1) % info.length; // 获取下一个堆栈的编号
            shift(nextStack); // 对下一个堆栈进行移位操作，递归！
            stack.capacity++; // 当前堆栈可以扩展一个单位
        }

        /* 将堆栈中的所有元素向右移动一位。*/
        int index = stack.lastCapacityIndex(); // 获取堆栈最后一个可用容量的索引
        while (stack.isWithinStackCapacity(index)) { // 遍历堆栈的有效范围
            values[index] = values[previousIndex(index)]; // 将元素向右移动
            index = previousIndex(index); // 移动到上一个索引
        }

        /* 调整堆栈的元数据。*/
        values[stack.start] = 0; // 清空堆栈的起始位置
        stack.start = nextIndex(stack.start); // 将堆栈的起始位置向右移动一位
        stack.capacity--; // 减少堆栈的容量
    }

    /**
     * 扩展指定堆栈的容量，通过移位其他堆栈来腾出空间。
     *
     * @param stackNum 堆栈编号
     */
    private void expand(int stackNum) {
        System.out.println("/// Expanding stack " + stackNum);

        /* 将下一个堆栈向右移动一位，腾出空间给当前堆栈。*/
        shift((stackNum + 1) % info.length);
        info[stackNum].capacity++; // 增加当前堆栈的容量
    }

    /**
     * 向指定堆栈中压入一个值。
     * 如果堆栈已满，则扩展堆栈容量。
     *
     * @param stackNum 堆栈编号
     * @param value 要压入的值
     * @throws FullStackException 如果所有堆栈都已满，则抛出异常
     */
    public void push(int stackNum, int value) throws FullStackException {
        System.out.println("/// Pushing stack " + stackNum + ": " + value);

        /* 如果所有堆栈都已满，抛出异常。*/
        if (allStacksAreFull()) {
            throw new FullStackException();
        }

        /* 如果当前堆栈已满，则扩展其容量。*/
        StackInfo stack = info[stackNum];
        if (stack.isFull()) {
            expand(stackNum);
        }

        /* 增加堆栈的大小，并将值存入堆栈顶部位置。*/
        stack.size++;
        values[stack.lastElementIndex()] = value;
    }

    /**
     * 从指定堆栈中弹出一个值。
     *
     * @param stackNum 堆栈编号
     * @return 弹出的值
     * @throws EmptyStackException 如果堆栈为空，则抛出异常
     */
    public int pop(int stackNum) throws Exception {
        System.out.println("/// Popping stack " + stackNum);
        StackInfo stack = info[stackNum];

        /* 如果堆栈为空，抛出异常。*/
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        /* 获取并移除堆栈的最后一个元素。*/
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0; // 清空堆栈的最后一个位置
        stack.size--; // 减少堆栈的大小
        return value;
    }

    /**
     * 获取指定堆栈的顶部值。
     *
     * @param stackNum 堆栈编号
     * @return 堆栈顶部的值
     */
    public int peek(int stackNum) {
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()]; // 返回堆栈顶部的值
    }

    /**
     * 获取存储所有堆栈元素的数组。
     *
     * @return 包含所有堆栈元素的数组
     */
    public int[] getValues() {
        return values;
    }

    /**
     * 获取指定堆栈的所有元素。
     *
     * @param stackNum 堆栈编号
     * @return 包含指定堆栈所有元素的数组
     */
    public int[] getStackValues(int stackNum) {
        StackInfo stack = info[stackNum];
        int[] items = new int[stack.size]; // 创建一个数组存储堆栈中的元素
        for (int i = 0; i < items.length; i++) {
            items[i] = values[adjustIndex(stack.start + i)]; // 将堆栈中的元素复制到新数组中
        }
        return items;
    }

    /**
     * 将指定堆栈的内容转换为字符串。
     *
     * @param stackNum 堆栈编号
     * @return 包含堆栈内容的字符串
     */
    public String stackToString(int stackNum) {
        int[] items = getStackValues(stackNum); // 获取堆栈的所有元素
        return stackNum + ": " + AssortedMethods.arrayToString(items); // 将堆栈内容转换为字符串
    }
}