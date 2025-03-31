package Q4_07_Build_Order.EdgeRemoval;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {
    private ArrayList<Project> children = new ArrayList<Project>(); // 存储当前项目的所有子项目（依赖项）
    private HashMap<String, Project> map = new HashMap<String, Project>(); // 用于快速查找子项目的映射表
    private String name; // 项目的名称
    private int dependencies = 0; // 当前项目所依赖的其他项目的数量

    /**
     * 构造函数，初始化项目名称。
     *
     * @param n 项目名称
     */
    public Project(String n) {
        name = n; // 初始化项目名称
    }

    /**
     * 获取项目的名称。
     *
     * @return 返回项目的名称
     */
    public String getName() {
        return name; // 返回项目的名称
    }

    /**
     * 添加一个子项目（依赖项）。
     *
     * @param node 子项目节点
     */
    public void addNeighbor(Project node) {
        if (!map.containsKey(node.getName())) { // 如果当前项目的子项目列表中不存在该子项目
            children.add(node); // 将子项目添加到子项目列表中
            map.put(node.getName(), node); // 将子项目加入映射表
            node.incrementDependencies(); // 增加子项目的依赖计数
        }
    }

    /**
     * 增加当前项目的依赖计数。
     */
    public void incrementDependencies() {
        dependencies++; // 增加当前项目的依赖计数
    }

    /**
     * 获取当前项目的所有子项目（依赖项）。
     *
     * @return 返回当前项目的所有子项目列表
     */
    public ArrayList<Project> getChildren() {
        return children; // 返回当前项目的所有子项目列表
    }

    /**
     * 减少当前项目的依赖计数。
     */
    public void decrementDependencies() {
        dependencies--; // 减少当前项目的依赖计数
    }

    /**
     * 获取当前项目的依赖计数。
     *
     * @return 返回当前项目的依赖计数
     */
    public int getNumberDependencies() {
        return dependencies; // 返回当前项目的依赖计数
    }
}