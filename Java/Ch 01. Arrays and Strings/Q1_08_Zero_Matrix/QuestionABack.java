package Q1_08_Zero_Matrix;

import CtCILibrary.AssortedMethods;

public class QuestionABack {
    /**
     * 将矩阵中包含零元素的行和列的所有元素设置为零。
     * 
     * 参数:
     * int[][] matrix - 输入的二维整数矩阵，将在原地修改。
     * 
     * 该方法不会返回任何值，但会直接修改输入矩阵。
     */
    public static void setZeros(int[][] matrix) {
        // 创建两个布尔数组，分别用于记录需要置零的行和列
        boolean[] row = new boolean[matrix.length];  
        boolean[] column = new boolean[matrix[0].length];

        // 遍历矩阵，记录包含零元素的行和列索引
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;  // 标记该行为需要置零
                    column[j] = true;  // 标记该列为需要置零
                }
            }
        }

        // 根据记录的布尔数组，将对应的行置零
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                nullifyRow(matrix, i);  // 置零第i行
            }
        }

        // 根据记录的布尔数组，将对应的列置零
        for (int j = 0; j < column.length; j++) {
            if (column[j]) {
                nullifyColumn(matrix, j);  // 置零第j列
            }
        }
    }


    public static void nullifyRow(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }		
    }

    public static void nullifyColumn(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }		
    }	


}