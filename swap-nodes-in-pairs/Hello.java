// @doc TestCase 描述测试用例
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// @doc 需要实现的类
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {  //当链表为空或者只有一个结点时，直接返回
            return head;
        }

        ListNode headNode;    //以此存储头结点

        ListNode temp = head.next.next;   //交换第一位和第二位
        head.next.next = head;
        headNode = head.next;         //存储交换后的头结点
        head.next = temp;

        //当后面只有一个结点或者无结点的时候，退出循环
        while (head.next != null && head.next.next != null) {
            ListNode tempNode = head.next;   //存储下一个结点
            head.next = head.next.next;     //令当前结点指向，往后数的第二个结点
            head = tempNode;                //进入下一个结点
            ListNode tempN = head.next.next;   //存储当前结点的，下一个结点的指向
            head.next.next = head;        //令下一个结点指向当前结点
            head.next = tempN;            //令当前结点指向，下一个结点的指向
        }

        return headNode;
    }

    //检测结果是否正确
    public boolean helloWorld(ListNode a, ListNode b) {
        while (a != null) {
            if (a.val != b.val) return false;
            a = a.next;
            b = b.next;
        }
        return true;
    }
}


// @doc 测试工具类，不需要提交到 LeetCode
public class HelloWorld {
    public static void main(String[] args) {
        // @doc testVector 是测试用例集合
        ListNode[] testVector = new ListNode[]
                {
                        //1,2,3,4
                        new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null)))),
                        //1
                        new ListNode(1, null),
                        //[]
                        new ListNode()
                };

        // @doc resultVector 是结果用例集合
        ListNode[] resultVector = new ListNode[]{
                //2,1,4,3
                new ListNode(2, new ListNode(1, new ListNode(4, new ListNode(3, null)))),
                //1
                new ListNode(1, null),
                //[]
                new ListNode()
        };

        Solution s = new Solution();

        // 将测试用例逐条喂给实现的函数，检查函数正确性
        for (int i = 0; i < testVector.length; i++) {
            ListNode result = s.swapPairs(testVector[i]);
            System.out.println(s.helloWorld(result, resultVector[i]));
            System.out.println("------------------------------");
        }
    }
}
