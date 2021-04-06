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
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }

        //定义两个新链表，并记录头结点
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode listNode1 = head1;
        ListNode listNode2 = head2;

        while (head != null) {
            if (head.val < x) {  //若数值小于x,填入链表1
                listNode1.next = new ListNode(head.val);
                listNode1 = listNode1.next;
            } else {           //若数值大于等于x,填入链表2
                listNode2.next = new ListNode(head.val);
                listNode2 = listNode2.next;
            }
            head = head.next;
        }
        listNode1.next = head2.next;   //链接令链表1尾结点指向链表2头结点
        head1 = head1.next;            //更新链表1头结点
        return head1;
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
                        //1,4,3,2,5,2
                        new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2, null)))))),
                        //2,1
                        new ListNode(2, new ListNode(1, null)),
                };
        // @doc resultVector 是结果用例集合
        ListNode[] resultVector = new ListNode[]{
                //1,2,2,4,3,5
                new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(4, new ListNode(3, new ListNode(5, null)))))),
                //1,2
                new ListNode(1, new ListNode(2, null)),
        };
        //定义特定值x的数组
        int[] a = {3, 2};

        Solution s = new Solution();
        // 将测试用例逐条喂给实现的函数，检查函数正确性
        for (int i = 0; i < testVector.length; i++) {
            ListNode result = s.partition(testVector[i], a[i]);
            System.out.println(s.helloWorld(result, resultVector[i]));
            System.out.println("------------------------------");
        }
    }
}
