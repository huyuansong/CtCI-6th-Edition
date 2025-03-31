package Q4_07_Build_Order.DFS;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private ArrayList<Project> nodes = new ArrayList<Project>(); // 存储所有项目节点的列表
    private HashMap<String, Project> map = new HashMap<String, Project>(); // 用于快速查找项目的映射表

    /**
     * 获取或创建一个指定名称的项目节点。
     *
     * @param name 项目名称
     * @return 返回对应的项目节点
     */
    public Project getOrCreateNode(String name) {
        if (!map.containsKey(name)) { // 如果映射表中不存在该项目
            Project node = new Project(name); // 创建一个新的项目节点
            nodes.add(node); // 将新节点添加到节点列表中
            map.put(name, node); // 将新节点加入映射表
        }
        return map.get(name); // 返回对应的项目节点
    }

    /**
     * 添加一条依赖关系边，表示从 startName 到 endName 的依赖关系。
     *
     * @param startName 被依赖的项目名称
     * @param endName   依赖于 startName 的项目名称
     */
    public void addEdge(String startName, String endName) {
        Project start = getOrCreateNode(startName); // 获取或创建被依赖的项目节点
        Project end = getOrCreateNode(endName); // 获取或创建依赖于 startName 的项目节点
        start.addNeighbor(end); // 在 start 节点中添加 end 节点作为其子节点
    }

    /**
     * 获取所有的项目节点。
     *
     * @return 返回包含所有项目节点的列表
     */
    public ArrayList<Project> getNodes() {
        return nodes; // 返回所有项目节点的列表
    }
}