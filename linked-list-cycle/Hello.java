// @doc  描述测试用例
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

// @doc 需要实现的类
class Solution {
    public boolean hasCycle(ListNode testVector) {                //快慢指针，若为无限循环下，即为有环，快指针终究会追上慢指针

        while (testVector == null || testVector.next == null) {    //若链表为空或为一个，直接返回
            return false;
        }

        ListNode fcar = testVector;                  //建立一个一次走两格的指针
        ListNode scar = testVector;                   //建立一个一次走一格的指针

        while (fcar != null && fcar.next != null) {     //若快指针所指向结点和下一个结点不为空
            if (fcar.val == scar.val) return true;      //若两指针指向同一个结点，存在环，返回true
            else {
                fcar = fcar.next.next;                  //否则慢指针指向下一个结点，快指针指向往后第二个结点
                scar = scar.next;
            }
        }
        return false;                                   //若无环，即可跳出循环，返回false
    }
}


// @doc 测试工具类，不需要提交到 LeetCode
public class HelloWorld {
    public static void main(String[] args) {
        // @doc testVector 是测试用例集合
        ListNode[] testVector = new ListNode[]{
                new ListNode(-1),
                new ListNode(-2),
                new ListNode(-3),
        };

        //测试用例数值
        int[] a = {3, 2, 0, 4};     //环为4-2
        int[] b = {1, 2};           //环为2-1
        int[] c = {1};

        //建立测试用例
        ListNode temp = new ListNode(-1);                //temp.next指向环结点，不是尾结点
        ListNode tempHead = new ListNode(-1);            //tempHead.next指向链表头结点的前一个结点

        tempHead.next = testVector[0];
        for (int i = 0; i < 4; i++) {
            testVector[0].next = new ListNode(a[i]);
            testVector[0] = testVector[0].next;
            if (i == 1) temp.next = testVector[0];
            if (i == 3) testVector[0].next = temp.next;
        }
        testVector[0] = tempHead.next.next;

        tempHead.next = testVector[1];
        for (int i = 0; i < 2; i++) {
            testVector[1].next = new ListNode(b[i]);
            testVector[1] = testVector[1].next;
            if (i == 0) temp = testVector[1];
            if (i == 1) testVector[1].next = temp;
        }
        testVector[1] = tempHead.next.next;

        testVector[2] = new ListNode(c[0]);

        // @doc resultVector 是测试用例集合
        boolean[] resultVector = {true, true, false};

        Solution s = new Solution();
        // 将测试用例逐条喂给实现的函数，对比结果集，若为true,则正确，检查函数正确性
        for (int i = 0; i < testVector.length; i++) {
            System.out.println(s.hasCycle(testVector[i]) == resultVector[i]);
            System.out.println("---------------------------");
        }
    }
}
