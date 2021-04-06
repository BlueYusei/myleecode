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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {   //当链表为空或者循环次数为0时，直接返回head；
            return head;
        }

        ListNode start = head;  //记录头结点

        int sum = 0;            //记录链表长度
        while (head != null) {
            sum++;
            head = head.next;
        }

        head = start;         //返回头结点

        k = k % sum;          //求余数，获得最少循环次数，以此截取需调到前面的子链表
        if (k == 0) return head;

        ListNode temp = new ListNode();     //存储子链表
        ListNode tempHead = new ListNode();  //存储子链表的头结点
        while (head != null) {
            sum--;
            if (sum == k) {
                temp = head.next;     //找到后半部子链表的头结点
                tempHead = temp;      //记录后半部子链表的头结点
                head.next = null;     //令前半部子链表的末尾结点指向空
            }
            head = head.next;       //进入下一个结点
        }

        while (temp.next != null) {   //找到后半部子链表的尾结点
            temp = temp.next;
        }
        temp.next = start;       //令后半部尾结点指向前半部头结点

        return tempHead;
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
                        //1,2,3,4,5
                        new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null))))),
                        //0,1,2
                        new ListNode(0, new ListNode(1, new ListNode(2, null))),
                };
        // @doc resultVector 是结果用例集合
        ListNode[] resultVector = new ListNode[]{
                //4,5,1,2,3
                new ListNode(4, new ListNode(5, new ListNode(1, new ListNode(2, new ListNode(3, null))))),
                //2,0,1
                new ListNode(2, new ListNode(0, new ListNode(1, null))),
        };
        //定义特定值k的数组
        int[] k = {2, 4};

        Solution s = new Solution();
        // 将测试用例逐条喂给实现的函数，检查函数正确性
        for (int i = 0; i < testVector.length; i++) {
            ListNode result = s.rotateRight(testVector[i], k[i]);
            System.out.println(s.helloWorld(result, resultVector[i]));
            System.out.println("------------------------------");
        }
    }
}
