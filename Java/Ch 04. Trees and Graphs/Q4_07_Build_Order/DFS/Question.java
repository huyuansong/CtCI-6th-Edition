package Q4_07_Build_Order.DFS;

import java.util.ArrayList;
import java.util.Stack;

public class Question {
	/* Build the graph, adding the edge (a, b) if b is dependent on a. 
	 * Assumes a pair is listed in “build order” (which is the reverse 
	 * of dependency order). The pair (a, b) in dependencies indicates
	 * that b depends on a and a must be built before a. */
    /**
     * 构建项目依赖图，根据给定的依赖关系添加边。
     *
     * @param projects      所有项目的名称数组
     * @param dependencies  项目之间的依赖关系数组
     * @return 返回构建好的项目依赖图
     */
    public static Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph(); // 创建一个空的项目依赖图

        for (String[] dependency : dependencies) { // 遍历所有的依赖关系
            String first = dependency[0]; // 被依赖的项目名称
            String second = dependency[1]; // 依赖于 first 的项目名称
            graph.addEdge(first, second); // 在图中添加一条从 first 到 second 的边
        }

        return graph; // 返回构建好的项目依赖图
    }

    /**
     * 使用深度优先搜索（DFS）遍历项目依赖图，并将结果存储在栈中。
     *
     * @param project 当前项目节点
     * @param stack   存储拓扑排序结果的栈
     * @return 如果存在环返回 false，否则返回 true
     */
    public static boolean doDFS(Project project, Stack<Project> stack) {
        if (project.getState() == Project.State.PARTIAL) { // 如果当前项目状态为 PARTIAL，说明存在环
            return false; // 返回 false 表示存在环
        }

        if (project.getState() == Project.State.BLANK) { // 如果当前项目状态为 BLANK
            project.setState(Project.State.PARTIAL); // 将当前项目状态设置为 PARTIAL
            ArrayList<Project> children = project.getChildren(); // 获取当前项目的所有子项目
            for (Project child : children) { // 遍历所有子项目
                if (!doDFS(child, stack)) { // 对每个子项目递归调用 DFS
                    return false; // 如果某个子项目存在环，则返回 false
                }
            }
            project.setState(Project.State.COMPLETE); // 将当前项目状态设置为 COMPLETE
            stack.push(project); // 将当前项目压入栈中
        }
        return true; // 返回 true 表示没有环
    }

    /**
     * 对所有项目进行拓扑排序。
     *
     * @param projects 所有项目节点的列表
     * @return 返回存储拓扑排序结果的栈，如果存在环则返回 null
     */
    public static Stack<Project> orderProjects(ArrayList<Project> projects) {
        Stack<Project> stack = new Stack<Project>(); // 创建一个空栈，用于存储拓扑排序结果
        for (Project project : projects) { // 遍历所有项目
            if (project.getState() == Project.State.BLANK) { // 如果当前项目状态为 BLANK
                if (!doDFS(project, stack)) { // 对当前项目调用 DFS
                    return null; // 如果存在环，则返回 null
                }
            }
        }
        return stack; // 返回存储拓扑排序结果的栈
    }

    /**
     * 将栈中的项目转换为字符串数组。
     *
     * @param projects 存储拓扑排序结果的栈
     * @return 返回转换后的字符串数组
     */
    public static String[] convertToStringList(Stack<Project> projects) {
        String[] buildOrder = new String[projects.size()]; // 创建一个与栈大小相同的字符串数组
        for (int i = 0; i < buildOrder.length; i++) { // 遍历栈中的所有项目
            buildOrder[i] = projects.pop().getName(); // 将栈顶项目的名称存入字符串数组
        }
        return buildOrder; // 返回转换后的字符串数组
    }

    /**
     * 查找项目的构建顺序。
     *
     * @param projects      所有项目的名称数组
     * @param dependencies  项目之间的依赖关系数组
     * @return 返回存储拓扑排序结果的栈，如果存在环则返回 null
     */
    public static Stack<Project> findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies); // 构建项目依赖图
        return orderProjects(graph.getNodes()); // 对所有项目进行拓扑排序
    }

    /**
     * 包装方法，返回项目的构建顺序字符串数组。
     *
     * @param projects      所有项目的名称数组
     * @param dependencies  项目之间的依赖关系数组
     * @return 返回项目的构建顺序字符串数组，如果存在环则返回 null
     */
    public static String[] buildOrderWrapper(String[] projects, String[][] dependencies) {
        Stack<Project> buildOrder = findBuildOrder(projects, dependencies); // 查找项目的构建顺序
        if (buildOrder == null) return null; // 如果存在环，则返回 null
        String[] buildOrderString = convertToStringList(buildOrder); // 将栈中的项目转换为字符串数组
        return buildOrderString; // 返回项目的构建顺序字符串数组
    }

    /**
     * 主函数：测试项目的构建顺序。
     */
    public static void main(String[] args) {
        String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"}; // 所有项目的名称
        String[][] dependencies = { // 项目之间的依赖关系
                {"a", "b"},
                {"b", "c"},
                {"a", "c"},
                {"d", "e"},
                {"b", "d"},
                {"e", "f"},
                {"a", "f"},
                {"h", "i"},
                {"h", "j"},
                {"i", "j"},
                {"g", "j"}
        };
        String[] buildOrder = buildOrderWrapper(projects, dependencies); // 查找项目的构建顺序
        if (buildOrder == null) { // 如果存在环
            System.out.println("Circular Dependency."); // 输出存在环的信息
        } else { // 如果没有环
            for (String s : buildOrder) { // 遍历构建顺序
                System.out.println(s); // 输出每个项目的名称
            }
        }
    }
}