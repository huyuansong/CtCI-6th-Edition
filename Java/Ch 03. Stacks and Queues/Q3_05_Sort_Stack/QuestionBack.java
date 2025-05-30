package Q3_05_Sort_Stack;

import java.util.Stack;

import CtCILibrary.AssortedMethods;

public class QuestionBack {

    /**
     * 使用归并排序对栈进行排序。
     *
     * @param inStack 待排序的栈
     * @return 排序后的栈（升序）
     */
    public static Stack<Integer> mergesort(Stack<Integer> inStack) {
        // 如果栈的大小小于等于 1，则直接返回，因为单个元素或空栈已经是有序的。
        if (inStack.size() <= 1) {
            return inStack;
        }

        // 创建两个新的栈 left 和 right，用于分割原栈中的元素。
        Stack<Integer> left = new Stack<Integer>();
        Stack<Integer> right = new Stack<Integer>();

        int count = 0; // 计数器，用于将元素交替分配到 left 和 right 栈中。
        while (inStack.size() != 0) { // 将原栈中的元素依次弹出，并分配到 left 和 right 中。 分治！均匀分割！
            count++;
            if (count % 2 == 0) { // 偶数次弹出的元素放入 left 栈。
                left.push(inStack.pop());
            } else { // 奇数次弹出的元素放入 right 栈。
                right.push(inStack.pop());
            }
        }

        // 对 left 和 right 栈递归调用 mergesort 方法，分别对它们进行排序。
        left = mergesort(left);
        right = mergesort(right);

        // 合并已排序的 left 和 right 栈，将结果存回 inStack。
        while (left.size() > 0 || right.size() > 0) { // 当 left 或 right 栈不为空时，继续合并。
            if (left.size() == 0) { // 如果 left 栈为空，则将 right 栈的栈顶元素压入 inStack。
                inStack.push(right.pop());
            } else if (right.size() == 0) { // 如果 right 栈为空，则将 left 栈的栈顶元素压入 inStack。
                inStack.push(left.pop());
            } else if (right.peek().compareTo(left.peek()) <= 0) { 
                // 如果 right 栈的栈顶元素小于等于 left 栈的栈顶元素，则将 left 栈的栈顶元素压入 inStack。
                inStack.push(left.pop());
            } else { // 否则将 right 栈的栈顶元素压入 inStack。
                inStack.push(right.pop());
            }
        }

        // 此时 inStack 是按降序排列的，需要将其反转为升序。
        Stack<Integer> reverseStack = new Stack<Integer>();
        while (inStack.size() > 0) { // 将 inStack 中的元素依次弹出并压入 reverseStack，实现反转。
            reverseStack.push(inStack.pop());
        }

        return reverseStack; // 返回排序后的栈（升序）。
    }

    /**
     *  使用插入排序对栈进行排序。
     *
     *  通过将 s 中的每个元素按顺序插入r 来对 s 进行排序。
     * @param s 待排序的整数栈。函数会直接修改该栈的内容，使其变为升序排列。
     */
    public static void sort(Stack<Integer> s){
        // 创建一个辅助栈 r，用于临时存储数据以完成排序。
        Stack<Integer> r = new Stack<Integer>();
        
        // 依次对s中的元素执行排序
        while(!s.isEmpty()){
            // 从主栈 s 弹出待排序的元素，暂存为 temp。
            int temp = s.pop();
           
            // 将辅助栈 r 中所有大于当前待排序元素temp大小的元素重新压回主栈 s，此时s充当缓冲区功能，确保 r 目前是排序的状态
            while(!r.isEmpty() && r.peek() > temp){
                s.push(r.pop());
            }
            
            // 将 temp 压入辅助栈 r，此时完成了对temp元素的排序
            r.push(temp);
        }
        
        // 将辅助栈 r 中的所有元素重新压回主栈 s，此时 s 已经是升序排列。
        while(!r.isEmpty()){
            s.push(r.pop());
        }
        // 打印排序后的结果
        while (!s.isEmpty()) {
            System.out.print(s.pop()+ "  ");
        }
    }

    /**
     * 主函数，用于测试栈排序功能。
     */
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>(); // 创建一个栈 s。

        // 向栈 s 中随机压入 10 个整数。
        for (int i = 0; i < 10; i++) {
            int r = AssortedMethods.randomIntInRange(0, 1000); // 随机生成 [0, 1000] 范围内的整数。
            s.push(r); // 将随机生成的整数压入栈 s。
        }

        // 调用 sort 方法对栈 s 进行排序。
        sort(s);

        // 打印排序后的栈内容。
        while (!s.isEmpty()) {
            System.out.println(s.pop()); // 依次弹出栈中的元素并打印。
        }
    }
}