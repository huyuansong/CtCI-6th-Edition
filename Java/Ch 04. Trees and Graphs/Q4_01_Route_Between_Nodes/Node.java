package Q4_01_Route_Between_Nodes;

/* 
 * 定义一个节点类（Node），用于表示图中的节点。
 * 每个节点包含相邻节点数组、相邻节点数量、节点状态以及节点名称。
 */
class Node {

    // 存储与当前节点相邻的其他节点数组
    private Node adjacent[];

    // 当前节点已添加的相邻节点数量
    public int adjacentCount;

    // 节点的名称（顶点标识）
    private String vertex;

    // 节点的状态，用于图遍历算法中标识节点是否被访问过
    public Question.State state;

    /*
     * 构造函数：初始化节点对象。
     * 参数：
     *   vertex - 节点的名称或标识。
     *   adjacentLength - 相邻节点数组的最大容量。
     */
    public Node(String vertex, int adjacentLength) {
        this.vertex = vertex; // 设置节点名称
        adjacentCount = 0; // 初始化相邻节点计数为0
        adjacent = new Node[adjacentLength]; // 初始化相邻节点数组
    }

    /*
     * 添加相邻节点。
     * 如果当前相邻节点数量未达到数组最大容量，则将新节点添加到相邻节点数组中；
     * 否则，打印提示信息“No more adjacent can be added”，表示无法再添加更多相邻节点。
     */
    public void addAdjacent(Node x) {
        if (adjacentCount < adjacent.length) { // 检查是否还有空间添加相邻节点
            this.adjacent[adjacentCount] = x; // 将新节点添加到相邻节点数组中
            adjacentCount++; // 更新相邻节点计数
        } else {
            System.out.print("No more adjacent can be added"); // 打印提示信息
        }
    }

    /*
     * 获取当前节点的所有相邻节点数组。
     * 返回值：相邻节点数组。
     */
    public Node[] getAdjacent() {
        return adjacent; // 返回相邻节点数组
    }

    /*
     * 获取当前节点的名称（顶点标识）。
     * 返回值：节点名称。
     */
    public String getVertex() {
        return vertex; // 返回节点名称
    }
}