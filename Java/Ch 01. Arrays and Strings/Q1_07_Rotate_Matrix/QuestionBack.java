/**
 * 将一个二维矩阵顺时针旋转90度。
 * 该方法仅适用于正方形矩阵（行数等于列数）。
 * 如果输入矩阵不是正方形或为空，则返回false。
 *
 * @param matrix 待旋转的二维矩阵
 * @return 如果成功旋转返回true，否则返回false
 */
public static boolean rotate(int[][] matrix) {
    // 检查矩阵是否为空或是否为正方形。如果不是正方形，直接返回false。
    if (matrix.length == 0 || matrix.length != matrix[0].length) return false;

    int n = matrix.length; // 获取矩阵的大小

    // 遍历矩阵的每一层（从外层到内层）
    for (int layer = 0; layer < n / 2; layer++) {
        int first = layer; // 当前层的第一个索引
        int last = n - 1 - layer; // 当前层的最后一个索引

        // 遍历当前层的每个元素进行旋转
        for (int i = first; i < last; i++) {
            int offset = i - first; // 计算当前元素相对于层起始位置的偏移量
            int top = matrix[first][i]; // 保存当前层顶部的值

            // 左侧的值移动到顶部
            matrix[first][i] = matrix[last - offset][first];

            // 底部的值移动到左侧
            matrix[last - offset][first] = matrix[last][last - offset];

            // 右侧的值移动到底部
            matrix[last][last - offset] = matrix[i][last];

            // 顶部的值移动到右侧
            matrix[i][last] = top;
        }
    }

    // 如果成功完成旋转，返回true
    return true;
}