package Q4_07_Build_Order.EdgeRemoval;

import java.util.ArrayList;

public class Question {

    /**
     * 构建项目依赖图，根据给定的依赖关系添加边。
     *
     * @param projects      所有项目的名称数组
     * @param dependencies  项目之间的依赖关系数组
     * @return 返回构建好的项目依赖图
     */
    public static Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph(); // 创建一个空的项目依赖图

        for (String project : projects) { // 遍历所有的项目名称
            graph.getOrCreateNode(project); // 在图中创建对应的项目节点
        }

        for (String[] dependency : dependencies) { // 遍历所有的依赖关系
            String first = dependency[0]; // 被依赖的项目名称
            String second = dependency[1]; // 依赖于 first 的项目名称
            graph.addEdge(first, second); // 在图中添加一条从 first 到 second 的边
        }

        return graph; // 返回构建好的项目依赖图
    }

    /**
     * 将没有依赖的项目插入到构建顺序数组中。
     *
     * @param order     存储构建顺序的数组
     * @param projects  所有项目节点的列表
     * @param offset    插入的起始位置
     * @return 返回插入后的位置偏移量
     */
    public static int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
        for (Project project : projects) { // 遍历所有项目
            if (project.getNumberDependencies() == 0) { // 如果当前项目的依赖计数为 0
                order[offset] = project; // 将该项目插入到构建顺序数组中
                offset++; // 更新插入位置偏移量
            }
        }
        return offset; // 返回更新后的位置偏移量
    }

    /**
     * 对所有项目进行拓扑排序。
     *
     * @param projects 所有项目节点的列表
     * @return 返回存储拓扑排序结果的数组，如果存在环则返回 null
     */
    public static Project[] orderProjects(ArrayList<Project> projects) {
        Project[] order = new Project[projects.size()]; // 创建一个与项目数量相同的数组，用于存储构建顺序

        /* 将没有依赖的项目先加入构建顺序 */
        int endOfList = addNonDependent(order, projects, 0); // 将没有依赖的项目插入到构建顺序数组中

        int toBeProcessed = 0; // 表示当前需要处理的项目索引
        while (toBeProcessed < order.length) { // 遍历所有项目
            Project current = order[toBeProcessed]; // 获取当前需要处理的项目

            /* 如果存在循环依赖，则无法生成有效的构建顺序 */
            if (current == null) {
                return null; // 返回 null 表示存在循环依赖
            }

            /* 移除当前项目作为其他项目的依赖 */
            ArrayList<Project> children = current.getChildren(); // 获取当前项目的所有子项目
            for (Project child : children) { // 遍历所有子项目
                child.decrementDependencies(); // 减少子项目的依赖计数
            }

            /* 将新的没有依赖的子项目加入构建顺序 */
            endOfList = addNonDependent(order, children, endOfList); // 将新的没有依赖的子项目插入到构建顺序数组中

            toBeProcessed++; // 处理下一个项目
        }

        return order; // 返回存储拓扑排序结果的数组
    }

    /**
     * 将项目数组转换为字符串数组。
     *
     * @param projects 存储拓扑排序结果的项目数组
     * @return 返回转换后的字符串数组
     */
    public static String[] convertToStringList(Project[] projects) {
        String[] buildOrder = new String[projects.length]; // 创建一个与项目数量相同的字符串数组
        for (int i = 0; i < projects.length; i++) { // 遍历所有项目
            buildOrder[i] = projects[i].getName(); // 将每个项目的名称存入字符串数组
        }
        return buildOrder; // 返回转换后的字符串数组
    }

    /**
     * 查找项目的构建顺序。
     *
     * @param projects      所有项目的名称数组
     * @param dependencies  项目之间的依赖关系数组
     * @return 返回存储拓扑排序结果的数组，如果存在环则返回 null
     */
    public static Project[] findBuildOrder(String[] projects, String[][] dependencies) {
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
        Project[] buildOrder = findBuildOrder(projects, dependencies); // 查找项目的构建顺序
        if (buildOrder == null) return null; // 如果存在环，则返回 null
        String[] buildOrderString = convertToStringList(buildOrder); // 将项目数组转换为字符串数组
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