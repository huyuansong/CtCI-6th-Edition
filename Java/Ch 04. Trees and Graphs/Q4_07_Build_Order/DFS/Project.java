package Q4_07_Build_Order.DFS;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {
    public enum State {COMPLETE, PARTIAL, BLANK}; // 定义项目的三种状态：已完成、部分完成、未开始

    private ArrayList<Project> children = new ArrayList<Project>(); // 存储当前项目的所有子项目（依赖项）
    private HashMap<String, Project> map = new HashMap<String, Project>(); // 用于快速查找子项目的映射表
    private String name; // 项目的名称
    private State state = State.BLANK; // 当前项目的状态，默认为未开始

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
        }
    }

    /**
     * 获取当前项目的状态。
     *
     * @return 返回当前项目的状态
     */
    public State getState() {
        return state; // 返回当前项目的状态
    }

    /**
     * 设置当前项目的状态。
     *
     * @param st 新的状态
     */
    public void setState(State st) {
        state = st; // 设置当前项目的状态
    }

    /**
     * 获取当前项目的所有子项目（依赖项）。
     *
     * @return 返回当前项目的所有子项目列表
     */
    public ArrayList<Project> getChildren() {
        return children; // 返回当前项目的所有子项目列表
    }
}