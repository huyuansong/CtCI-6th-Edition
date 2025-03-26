/**
 * 将矩阵中包含零元素的行和列的所有元素设置为零。
 * 
 * 参数:
 * int[][] matrix - 输入的二维整数矩阵，将在原地修改。
 * 
 * 该方法不会返回任何值，但会直接修改输入矩阵。
 */
public static void setZeros(int[][] matrix) {
    // 标志变量，用于记录第一行和第一列是否包含零
    boolean rowHasZero = false;
    boolean colHasZero = false;   
    
    // 检查第一行是否包含零
    for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[0][j] == 0) {
            rowHasZero = true;
            break;
        }
    }
    
    // 检查第一列是否包含零
    for (int i = 0; i < matrix.length; i++) {
        if (matrix[i][0] == 0) {
            colHasZero = true;
            break;
        }
    }
    
    // 检查剩余部分的矩阵，使用第一行和第一列作为标记
    for (int i = 1; i < matrix.length; i++) {
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0; // 标记对应行
                matrix[0][j] = 0; // 标记对应列
            }
        }
    }
    
    // 根据第一列的标记将对应的行置零
    for (int i = 1; i < matrix.length; i++) {
        if (matrix[i][0] == 0) {
            nullifyRow(matrix, i);
        }
    }
    
    // 根据第一行的标记将对应的列置零
    for (int j = 1; j < matrix[0].length; j++) {
        if (matrix[0][j] == 0) {
            nullifyColumn(matrix, j);
        }
    }
    
    // 如果第一行有零，则将第一行置零
    if (rowHasZero) {
        nullifyRow(matrix, 0);
    }
    
    // 如果第一列有零，则将第一列置零
    if (colHasZero) {
        nullifyColumn(matrix, 0);
    }
}