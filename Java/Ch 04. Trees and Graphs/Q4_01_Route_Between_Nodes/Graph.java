package Q4_01_Route_Between_Nodes;


/* 
 * 定义一个图（Graph）类，用于表示图数据结构。
 * 图中的节点由Node类表示，Graph类负责管理这些节点。
 */
public class Graph {

    // 定义图中最多可以包含的节点数
    public static int MAX_VERTICES = 6;

    // 存储图中所有节点的数组
    private Node vertices[];

    // 当前图中已有的节点数量
    public int count;

    /*
     * 构造函数：初始化图对象。
     * 创建一个大小为MAX_VERTICES的节点数组，并将计数器count初始化为0。
     */
    public Graph() {
        vertices = new Node[MAX_VERTICES]; // 初始化节点数组
        count = 0; // 初始化节点计数器
    }

    /*
     * 添加节点到图中。
     * 如果当前节点数小于数组的最大容量，则将新节点添加到数组中；
     * 否则，打印提示信息“Graph full”，表示图已满。
     */
    public void addNode(Node x) {
        if (count < vertices.length) { // 检查图是否已满
            vertices[count] = x; // 将新节点添加到数组中
            count++; // 更新节点计数器
        } else {
            System.out.print("Graph full"); // 图已满时打印提示信息
        }
    }

    /*
     * 获取图中所有节点的数组。
     * 返回存储节点的数组vertices。
     */
    public Node[] getNodes() {
        return vertices; // 返回节点数组
    }
}

