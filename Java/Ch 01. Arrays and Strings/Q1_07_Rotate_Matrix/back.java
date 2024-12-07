public static boolean rotate(int[][] matrix) {
    // 定义一个名为 `rotate` 的公共静态方法，它接受一个整数类型的二维数组 `matrix` 作为参数，
    // 并返回一个布尔值。这个方法用于检查并旋转给定的矩阵。
    
    if (matrix.length == 0 || matrix.length != matrix[0].length) return false; 
    // 检查传入的矩阵是否为空或者不是一个正方形（即行数和列数不相等）。
    // 如果不是正方形或空矩阵，则无法进行90度旋转，因此直接返回 `false`。

    int n = matrix.length;
    // 获取矩阵的大小（即行数或列数，因为是正方形矩阵），并将这个值赋给变量 `n`。
    // 这将用来控制循环的次数以及确定矩阵边界。

    for (int layer = 0; layer < n / 2; layer++) {
        // 开始一个外层循环，用 `layer` 来表示当前处理的层。
        // 对于一个n x n的矩阵，我们只需要处理 `n/2` 层，因为最外面的一层旋转后，
        // 内部的层也会随之旋转。

        int first = layer;
        int last = n - 1 - layer;
        // 定义两个变量 `first` 和 `last` 分别指向当前层的第一个元素和最后一个元素的位置。
        // 随着 `layer` 的增加，`first` 和 `last` 会逐渐向内收缩，直到所有层都被处理。

        for(int i = first; i < last; i++) {
            // 开始一个内层循环，用 `i` 来遍历当前层中的元素。
            // 注意这里的循环条件是 `i < last`，因为我们不需要对最后一个元素进行旋转操作，
            // 因为它会被第一个元素替换。

            int offset = i - first;
            int top = matrix[first][i]; // save top
            // 计算偏移量 `offset`，这是当前元素与该层起始位置的距离。
            // 然后保存当前层顶部的元素到变量 `top` 中，以便后续使用。

            // left -> top
            matrix[first][i] = matrix[last-offset][first];
            // 将左侧的元素移动到顶部相应的位置。
            // 这里使用了之前计算的 `offset` 来找到左侧对应的元素。

            // bottom -> left
            matrix[last-offset][first] = matrix[last][last - offset];
            // 将底部的元素移动到左侧相应的位置。
            // 同样使用 `offset` 找到底部对应的元素。

            // right -> bottom
            matrix[last][last - offset] = matrix[i][last];
            // 将右侧的元素移动到底部相应的位置。
            // 这里直接使用 `i` 和 `last` 来定位右侧和底部的元素。

            // top -> right
            matrix[i][last] = top; // right <- saved top
            // 最后，将最初保存的顶部元素放到右侧相应的位置，
            // 完成了一次完整的旋转操作。
        }
    }

    return true;
    // 如果成功完成了所有旋转操作，返回 `true` 表示矩阵已经被正确地旋转了90度。
}