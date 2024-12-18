public class FixedMultiStack {
    private int numberOfStacks = 3; // 定义多栈类中栈的数量
    private int stackCapacity; // 定义每个栈的容量
    private int[] values; // 存储所有栈的值
    private int[] sizes; // 存储每个栈的当前大小

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize; // 初始化每个栈的容量
        values = new int[stackSize * numberOfStacks]; // 初始化存储所有栈值的数组
        sizes = new int[numberOfStacks]; // 初始化存储每个栈大小的数组
    }

    /* 将值推入指定栈。 */
    public void push(int stackNum, int value) throws FullStackException {
        /* 检查是否有空间添加下一个元素 */
        if (isFull(stackNum)) { 
            throw new FullStackException(); // 如果栈已满，抛出异常
        }
        
        /* 增加栈指针并更新栈顶值。 */        
        sizes[stackNum]++; // 增加栈的大小
        values[indexOfTop(stackNum)] = value; // 将值放入栈顶
    }

    /* 从指定栈的栈顶弹出元素。 */
    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException(); // 如果栈为空，抛出异常
        }
        
        int topIndex = indexOfTop(stackNum); // 获取栈顶索引
        int value = values[topIndex]; // 获取栈顶值
        values[topIndex] = 0; // 清空栈顶值
        sizes[stackNum]--; // 减少栈的大小
        return value; // 返回弹出的值
    }

    /* 返回指定栈的栈顶元素。 */
    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException(); // 如果栈为空，抛出异常
        }        
        return values[indexOfTop(stackNum)]; // 返回栈顶值
    }

    /* 检查指定栈是否为空。 */
    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0; // 如果栈的大小为0，返回true
    }
    
    /* 检查指定栈是否已满。 */
    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity; // 如果栈的大小等于容量，返回true
    }
    
    /* 返回指定栈的栈顶索引。 */
    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity; // 计算栈的偏移量
        int size = sizes[stackNum]; // 获取栈的当前大小
        return offset + size - 1; // 返回栈顶索引
    }    
    
    public int[] getValues() {
        return values; // 返回所有栈的值
    }
    
    public int[] getStackValues(int stackNum) {
        int[] items = new int[sizes[stackNum]]; // 创建一个数组来存储指定栈的值
        for (int i = 0; i < items.length; i++) {
            items[i] = values[stackNum * stackCapacity + i]; // 将值复制到新数组中
        }
        return items; // 返回指定栈的值
    }
    
    public String stackToString(int stackNum) {
        int[] items = getStackValues(stackNum); // 获取指定栈的值
        return stackNum + ": " + AssortedMethods.arrayToString(items); // 将栈转换为字符串
    }    
}