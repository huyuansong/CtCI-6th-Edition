package CtCILibrary;

/* 
 * 定义二叉树的一个节点类，数据元素存储为整数类型。
 */
public class TreeNode2 {
    // 节点存储的数据
    public int data;      
    // 左子节点
    public TreeNode2 left;    
    // 右子节点
    public TreeNode2 right; 
    // 父节点
    public TreeNode2 parent;
    // 子树的节点总数，默认为1（当前节点本身）
    private int size = 0;

    /*
     * 构造函数，初始化节点数据并设置子树大小为1。
     */
    public TreeNode2(int d) {
        data = d;
        size = 1;
    }

    /*
     * 设置左子节点，并将当前节点作为左子节点的父节点。
     * 如果左子节点为null，则不做任何操作。
     */
    private void setLeftChild(TreeNode2 left) {
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
    }

    /*
     * 设置右子节点，并将当前节点作为右子节点的父节点。
     * 如果右子节点为null，则不做任何操作。
     */
    private void setRightChild(TreeNode2 right) {
        this.right = right;
        if (right != null) {
            right.parent = this;
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
            if (left == null) { // 左子节点为空，直接创建新节点
                setLeftChild(new TreeNode2(d));
            } else { // 左子节点不为空，递归插入
                left.insertInOrder(d);
            }
        } else { // 新数据大于当前节点数据
            if (right == null) { // 右子节点为空，直接创建新节点
                setRightChild(new TreeNode2(d));
            } else { // 右子节点不为空，递归插入
                right.insertInOrder(d);
            }
        }
        size++; // 更新子树大小
    }

    /*
     * 返回以当前节点为根的子树的节点总数。
     */
    public int size() {
        return size;
    }

    /*
     * 检查当前节点是否为有效的二叉搜索树（BST）。
     * 条件：左子树的所有节点值都小于当前节点值，右子树的所有节点值都大于当前节点值。
     */
    public boolean isBST() {
        if (left != null) { // 检查左子树
            if (data < left.data || !left.isBST()) { // 当前节点值小于左子节点值或左子树不是BST
                return false;
            }
        }

        if (right != null) { // 检查右子树
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
        int leftHeight = left != null ? left.height() : 0; // 左子树高度
        int rightHeight = right != null ? right.height() : 0; // 右子树高度
        return 1 + Math.max(leftHeight, rightHeight); // 当前节点高度为左右子树高度的最大值加1
    }

    /*
     * 在二叉树中查找指定值的节点。
     * 如果找到返回该节点，否则返回null。
     */
    public TreeNode2 find(int d) {
        if (d == data) { // 找到目标值
            return this;
        } else if (d <= data) { // 目标值小于等于当前节点值，递归查找左子树
            return left != null ? left.find(d) : null;
        } else if (d > data) { // 目标值大于当前节点值，递归查找右子树
            return right != null ? right.find(d) : null;
        }
        return null; // 未找到目标值
    }

    /*
     * 根据有序数组创建最小高度的二叉搜索树。
     * 使用分治法：每次取数组中间值作为根节点，递归构造左右子树。
     */
    private static TreeNode2 createMinimalBST(int arr[], int start, int end){
        if (end < start) { // 如果起始索引大于结束索引，返回null
            return null;
        }
        int mid = (start + end) / 2; // 取中间索引
        TreeNode2 n = new TreeNode2(arr[mid]); // 创建中间值作为根节点
        n.setLeftChild(createMinimalBST(arr, start, mid - 1)); // 递归构造左子树
        n.setRightChild(createMinimalBST(arr, mid + 1, end)); // 递归构造右子树
        return n; // 返回构造完成的根节点
    }

    /*
     * 公共接口：根据有序数组创建最小高度的二叉搜索树。
     */
    public static TreeNode2 createMinimalBST(int[] array) {
        return createMinimalBST(array, 0, array.length - 1);
    }

    /*
     * 打印二叉树结构。
     */
    public void print() {
        //BTreePrinter.printNode(this);
    }
}