// @doc 描述测试用例
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

// @doc 需要实现的类
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack();   //定义栈记录两个链表的逆序值
        Stack<Integer> stack2 = new Stack();

        while (l1 != null) {             //链表1逆序值
            stack1.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {            //链表2逆序值
            stack2.add(l2.val);
            l2 = l2.next;
        }

        Stack<Integer> stack3 = new Stack();      //存储计算结果的逆序，也就是所求结果的正序
        int plusOne = 0;                        //记录是否需要往前进1
        while (!stack1.isEmpty() || !stack2.isEmpty()) {   //链表之一不为空的情况下
            int num = 0;                                  //记录计算结果

            if (stack1.isEmpty()) {          //链表1为空
                num = stack2.peek();        //此位结果为链表2元素数值
                stack2.pop();                //去掉栈顶元素
            } else if (stack2.isEmpty()) {   //链表2为空，此位结果为链表1元素数值
                num = stack1.peek();           //此位结果为链表1元素数值
                stack1.pop();               //去掉栈顶元素
            } else {                        //都不为空
                num = stack1.peek() + stack2.peek();   //num为两链表元素之和
                stack1.pop();               //去掉栈顶元素
                stack2.pop();
            }

            if (plusOne == 1) {         //判断当前num是否加1
                num++;
                plusOne--;            //加1，重新记录
            }
            if (num >= 10) {           //判断当前num是否大于等于10
                num = num % 10;       //num取个位上的数值
                plusOne++;             //记录下一位（高位）需加1
            }
            stack3.add(num);           //num进栈
        }

        if (plusOne == 1) {            //出循环，判断最后一位（最高位）是否仍需进1
            stack3.add(1);              //stack多加一位，此位数值为1
            plusOne--;
        }

        ListNode head = new ListNode(0);      //建立目的链表，并记录头结点
        ListNode tempHead = head;

        while (!stack3.isEmpty()) {             //依次取出栈顶元素，建立链表即可
            head.next = new ListNode(stack3.peek());
            stack3.pop();
            head = head.next;
        }

        head = tempHead.next;
        return head;
    }

    //验证结果是否符合
    public boolean helloWorld(ListNode a, ListNode b) {
        while (a != null) {
            if (a.val != b.val) {
                return false;
            }
            a = a.next;
            b = b.next;
        }
        return true;
    }
}


// @doc 测试工具类，不需要提交到 LeetCode
public class HelloWorld {
    public static void main(String[] args) {
        // @doc testVector 是测试用例集合,两个new为一个测试用例
        ListNode[] testVector = new ListNode[]{
                //first
                new ListNode(-1),
                new ListNode(-1),
                //second
                new ListNode(-2),
                new ListNode(-2),
        };

        List<int[]> lists1 = new ArrayList<>();
        int[] a1 = {7, 2, 4, 3};
        lists1.add(a1);
        int[] a2 = {5, 6, 4};
        lists1.add(a2);
        int[] b1 = {1, 2, 3};
        lists1.add(b1);
        int[] b2 = {1, 0};
        lists1.add(b2);

        for (int i = 0; i < testVector.length; i++) {
            ListNode tempHead = new ListNode(-1);
            tempHead.next = testVector[i];
            for (int j = 0; j < lists1.get(i).length; j++) {
                testVector[i].next = new ListNode(lists1.get(i)[j]);
                testVector[i] = testVector[i].next;
            }
            testVector[i] = tempHead.next.next;
        }

        // @doc resultVector 是测试用例集合
        ListNode[] resultVector = new ListNode[]{
                new ListNode(-1),
                new ListNode(-2),
        };

        List<int[]> lists2 = new ArrayList<>();
        int[] a = {7, 8, 0, 7};
        lists2.add(a);
        int[] b = {1, 3, 3};
        lists2.add(b);

        for (int i = 0; i < resultVector.length; i++) {
            ListNode tempHead = new ListNode(-1);
            tempHead.next = resultVector[i];
            for (int j = 0; j < lists2.get(i).length; j++) {
                resultVector[i].next = new ListNode(lists2.get(i)[j]);
                resultVector[i] = resultVector[i].next;
            }
            resultVector[i] = tempHead.next.next;
        }

        Solution s = new Solution();
        // 将测试用例逐条喂给实现的函数，对比结果集，若为true,则正确，检查函数正确性
        for (int i = 0; i < resultVector.length; i++) {
            ListNode sum = s.addTwoNumbers(testVector[i * 2], testVector[i * 2 + 1]);
            System.out.println(s.helloWorld(sum, resultVector[i]));
            System.out.println("---------------------------");
        }
    }
}
