package Q3_05_Sort_Stack;

import java.util.Arrays;
import java.util.Stack;

public class Origin {
    /**
     * 对整数栈 s 进行升序排序。
     * 该方法使用辅助栈 r 来实现栈的排序，确保最终栈 s 的元素从栈顶到栈底为升序排列。
     *  通过将 s1 中的每个元素按顺序插入r 来对s1进行排序。
     * @param s 待排序的整数栈。函数会直接修改该栈的内容，使其变为升序排列。
     */
    public static void sort(Stack<Integer> s){
        // 创建一个辅助栈 r，用于临时存储数据以完成排序。
        Stack<Integer> r = new Stack<Integer>();
        
        // 依次对s中的元素执行排序
        while(!s.isEmpty()){
            // 从主栈 s 弹出待排序的元素，暂存为 tmp。
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
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<Integer>();
        s1.push(8);
        s1.push(3);
        s1.push(1);
        s1.push(12);        
        sort(s1);
    }

}
