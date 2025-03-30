package CtCILibrary;

/* 
 * 定义二叉树的一个节点类，存储的数据元素为整数类型。
 */
public class TreeNode {
    // 节点存储的数据值
    public int data;      
    // 左子节点引用
    public TreeNode left;    
    // 右子节点引用
    public TreeNode right; 
    // 父节点引用
    public TreeNode parent;
    // 子树中节点的总数（包括自身）
    private int size = 0;

    /*
     * 构造函数：初始化一个新节点，并设置其数据值和子树大小为1。
     */
    public TreeNode(int d) {
        data = d; // 设置节点数据值
        size = 1; // 初始化子树大小为1
    }

    /*
     * 设置左子节点，并将当前节点作为左子节点的父节点。
     * 如果左子节点不为空，则更新其父节点指针。
     */
    private void setLeftChild(TreeNode left) {
        this.left = left; // 设置左子节点
        if (left != null) { // 如果左子节点不为空
            left.parent = this; // 更新左子节点的父节点指针
        }
    }

    /*
     * 设置右子节点，并将当前节点作为右子节点的父节点。
     * 如果右子节点不为空，则更新其父节点指针。
     */
    private void setRightChild(TreeNode right) {
        this.right = right; // 设置右子节点
        if (right != null) { // 如果右子节点不为空
            right.parent = this; // 更新右子节点的父节点指针
        }
    }

    /*
     * 按顺序插入新数据到二叉搜索树中。
     * 如果新数据小于等于当前节点数据，则递归插入到左子树；
     * 如果新数据大于当前节点数据，则递归插入到右子树。
     * 插入完成后更新子树大小。
     */
    public void insertInOrder(int d) {
        if (d <= data) { // 新数据小于等于当前节点数据
            if (left == null) { // 如果左子节点为空
                setLeftChild(new TreeNode(d)); // 创建新的左子节点
            } else { // 如果左子节点不为空
                left.insertInOrder(d); // 递归插入到左子树
            }
        } else { // 新数据大于当前节点数据
            if (right == null) { // 如果右子节点为空
                setRightChild(new TreeNode(d)); // 创建新的右子节点
            } else { // 如果右子节点不为空
                right.insertInOrder(d); // 递归插入到右子树
            }
        }
        size++; // 更新子树大小
    }

    /*
     * 返回以当前节点为根的子树的节点总数。
     */
    public int size() {
        return size; // 返回子树大小
    }

    /*
     * 检查当前节点是否为有效的二叉搜索树（BST）。
     * 条件：左子树的所有节点值都小于当前节点值，右子树的所有节点值都大于当前节点值。
     */
    public boolean isBST() {
        if (left != null) { // 如果存在左子树
            if (data < left.data || !left.isBST()) { // 当前节点值小于左子节点值或左子树不是BST
                return false;
            }
        }

        if (right != null) { // 如果存在右子树
            if (data >= right.data || !right.isBST()) { // 当前节点值大于等于右子节点值或右子树不是BST
                return false;
            }
        }

        return true; // 所有条件均满足，返回true
    }

    /*
     * 计算以当前节点为根的子树的高度。
     * 高度定义为从根节点到最远叶子节点的路径长度。
     */
    public int height() {
        int leftHeight = left != null ? left.height() : 0; // 获取左子树高度
        int rightHeight = right != null ? right.height() : 0; // 获取右子树高度
        return 1 + Math.max(leftHeight, rightHeight); // 当前节点高度为左右子树高度的最大值加1
    }

    /*
     * 在二叉树中查找指定值的节点。
     * 如果找到返回该节点，否则返回null。
     */
    public TreeNode find(int d) {
        if (d == data) { // 如果找到目标值
            return this; // 返回当前节点
        } else if (d <= data) { // 如果目标值小于等于当前节点值
            return left != null ? left.find(d) : null; // 递归查找左子树
        } else if (d > data) { // 如果目标值大于当前节点值
            return right != null ? right.find(d) : null; // 递归查找右子树
        }
        return null; // 未找到目标值
    }

    /*
     * 根据有序数组创建最小高度的二叉搜索树。
     * 使用分治法：每次取数组中间值作为根节点，递归构造左右子树。
     */
    private static TreeNode createMinimalBST(int arr[], int start, int end){
        if (end < start) { // 如果起始索引大于结束索引，返回null
            return null;
        }
        int mid = (start + end) / 2; // 取中间索引
        TreeNode n = new TreeNode(arr[mid]); // 创建中间值作为根节点
        n.setLeftChild(createMinimalBST(arr, start, mid - 1)); // 递归构造左子树
        n.setRightChild(createMinimalBST(arr, mid + 1, end)); // 递归构造右子树
        return n; // 返回构造完成的根节点
    }

    /*
     * 公共接口：根据有序数组创建最小高度的二叉搜索树。
     */
    public static TreeNode createMinimalBST(int[] array) {
        return createMinimalBST(array, 0, array.length - 1); // 调用私有方法进行构造
    }

    /*
     * 打印二叉树结构。
     */
    public void print() {
        BTreePrinter.printNode(this); // 调用外部工具打印二叉树
    }
}