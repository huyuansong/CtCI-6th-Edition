package Q3_01_Three_in_One;

import java.util.EmptyStackException;

import CtCILibrary.AssortedMethods;

public class FixedMultiStackBack {
    // 定义堆栈的数量
    private int numberOfStacks = 3;
    // 每个堆栈的最大容量
    private int stackCapacity;
    // 存储所有堆栈元素的数组
    private int[] values;
    // 记录每个堆栈中元素数量的数组
    private int[] sizes;

    /**
     * 构造函数，初始化多堆栈。
     *
     * @param stackSize 每个堆栈的容量
     */
    public FixedMultiStackBack(int stackSize) {
        stackCapacity = stackSize; // 设置每个堆栈的容量
        values = new int[stackSize * numberOfStacks]; // 初始化存储所有堆栈元素的数组
        sizes = new int[numberOfStacks]; // 初始化记录每个堆栈大小的数组
    }

    /**
     * 向指定堆栈中压入一个值。
     *
     * @param stackNum 堆栈编号（从0开始）
     * @param value    要压入的值
     * @throws FullStackException 如果堆栈已满，则抛出异常
     */
    public void push(int stackNum, int value) throws FullStackException {
        // 检查堆栈是否已满
        if (isFull(stackNum)) { 
            throw new FullStackException(); // 如果堆栈已满，抛出异常
        }

        // 增加堆栈的大小，并将值存入堆栈顶部
        sizes[stackNum]++; // 增加堆栈的大小
        values[indexOfTop(stackNum)] = value; // 将值存入堆栈顶部位置
    }

    /**
     * 从指定堆栈中弹出一个值。
     *
     * @param stackNum 堆栈编号（从0开始）
     * @return 弹出的值
     * @throws EmptyStackException 如果堆栈为空，则抛出异常
     */
    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException(); // 如果堆栈为空，抛出异常
        }

        // 获取堆栈顶部的索引
        int topIndex = indexOfTop(stackNum);
        int value = values[topIndex]; // 获取堆栈顶部的值
        values[topIndex] = 0; // 清空堆栈顶部的值
        sizes[stackNum]--; // 减少堆栈的大小
        return value; // 返回弹出的值
    }

    /**
     * 获取指定堆栈的顶部值。
     *
     * @param stackNum 堆栈编号（从0开始）
     * @return 堆栈顶部的值
     * @throws EmptyStackException 如果堆栈为空，则抛出异常
     */
    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException(); // 如果堆栈为空，抛出异常
        }
        return values[indexOfTop(stackNum)]; // 返回堆栈顶部的值
    }

    /**
     * 判断指定堆栈是否为空。
     *
     * @param stackNum 堆栈编号（从0开始）
     * @return 如果堆栈为空，返回 true；否则返回 false
     */
    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0; // 如果堆栈大小为0，则堆栈为空
    }

    /**
     * 判断指定堆栈是否已满。
     *
     * @param stackNum 堆栈编号（从0开始）
     * @return 如果堆栈已满，返回 true；否则返回 false
     */
    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity; // 如果堆栈大小等于容量，则堆栈已满
    }

    /**
     * 获取指定堆栈顶部元素的索引。
     *
     * @param stackNum 堆栈编号（从0开始）
     * @return 堆栈顶部元素的索引
     */
    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity; // 计算堆栈的起始偏移量 1*4
        int size = sizes[stackNum]; // 获取堆栈的当前大小 2
        return offset + size - 1; // 返回堆栈顶部元素的索引 4+3-1=6，因为当前堆栈的索引从0开始，而size表示元素个数，所以需要减1
    }

    /**
     * 获取存储所有堆栈元素的数组。
     *
     * @return 包含所有堆栈元素的数组
     */
    public int[] getValues() {
        return values; // 返回存储所有堆栈元素的数组
    }

    /**
     * 获取指定堆栈的所有元素。
     *
     * @param stackNum 堆栈编号（从0开始）
     * @return 包含指定堆栈所有元素的数组
     */
    public int[] getStackValues(int stackNum) {
        int[] items = new int[sizes[stackNum]]; // 创建一个数组存储堆栈中的元素
        for (int i = 0; i < items.length; i++) {
            items[i] = values[stackNum * stackCapacity + i]; // 将堆栈中的元素复制到新数组中
        }
        return items; // 返回包含堆栈所有元素的数组
    }

    /**
     * 将指定堆栈的内容转换为字符串。
     *
     * @param stackNum 堆栈编号（从0开始）
     * @return 包含堆栈内容的字符串
     */
    public String stackToString(int stackNum) {
        int[] items = getStackValues(stackNum); // 获取堆栈的所有元素
        return stackNum + ": " + AssortedMethods.arrayToString(items); // 将堆栈内容转换为字符串
    }
}