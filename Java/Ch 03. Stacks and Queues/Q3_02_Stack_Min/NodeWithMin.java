package Q3_02_Stack_Min;

/**
 * NodeWithMin 类用于表示一个带有最小值信息的节点。
 * 这个类是为了解决栈中每个元素都记录当前栈中的最小值的问题。
 * 每个节点不仅存储自身的值，还存储当前栈中的最小值。
 */
class NodeWithMin {
    // 节点的值
    public int value;
    
    // 当前栈中的最小值
    public int min;

    /**
     * 构造函数，初始化节点的值和当前栈中的最小值。
     *
     * @param v   节点的值
     * @param min 当前栈中的最小值
     */
    public NodeWithMin(int v, int min) {
        value = v;  // 初始化节点的值
        this.min = min;  // 初始化当前栈中的最小值
    }
}
