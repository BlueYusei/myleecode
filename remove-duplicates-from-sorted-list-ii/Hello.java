// @doc 描述测试用例
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {  //判断链表是否为空或者只有一个结点
            return head;
        }

        ListNode tempHead = new ListNode(-101, head);  //由于头结点重复和中间结点重复时，删除操作不同，头结点需删除一个指针，中间结点需删除前后两个指针
        // 故建立一个新头结点指向原头结点，形成新链表，令头结点和中间结点删除操作一致

        boolean bool = false;             //记录结点是否重复，初始为false，表示为没有
        ListNode node = tempHead;           //思路解释：设“head”为“含有相同val的结点集”，个数（1-300），node则为head（结点集）的前一个结点。
        while (head != null && head.next != null) {   //判断当前结点和下一个结点不为空
            //对于尾结点是重复结点：在循环中只可判断是否重复，记录bool，由于head.next==null,跳出循环，缺少删除操作，所以此删除操作在跳出循环之后）
            if (head.val == head.next.val) {      //若当前结点val等于下一个结点val
                bool = true;                //记录bool = true，结点val重复
                head = head.next;           //head指针（非结点）往后移,直到当前结点val不等于下一个结点val,才会用到bool
            } else {                              //当前结点val不等于下一个结点的val，判断bool是否为真，即之前的结点val是否重复，
                if (bool == true) {                   //bool为真，存在重复结点，当前结点即为重复结点的尾结点
                    ListNode temp = head.next;      //建立temp记录，当前结点的指向结点
                    head.next = null;               //令当前结点指向空，即重复结点子链表断开与后面子链表的指向
                    node.next = temp;               //令node结点指向temp结点，即前面子链表断开与重复结点子链表的指向，并指向后面的子链表
                    head = temp;                    //令head指针（非结点）指向后面子链表的头结点
                    bool = false;                   //重新记录是否重复
                } else {                             // bool为假，不存在重复
                    head = head.next;               //head指针（非结点）往后移
                    node = node.next;               //node指针（非结点）也往后移
                }
            }
        }

        if (bool == true) {            //判断尾结点是否重复，如果重复，由于尾结点后无结点，指向空
            node.next = null;       //故只需令node指向空即可删除重复尾结点
        }

        head = tempHead.next;       //令head指针（非结点）指向新链表的第二结点，即舍弃之前建立的头结点即为结果
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
                //1,2,3,3,4,4,5
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5, null))))))),
                //1,1,1,2,3
                new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, null))))),
        };
        // @doc resultVector 是测试用例集合
        ListNode[] resultVector = new ListNode[]{
                //1,2,5
                new ListNode(1, new ListNode(2, new ListNode(5, null))),
                //2,3
                new ListNode(2, new ListNode(3, null)),
        };

        Solution s = new Solution();
        // 将测试用例逐条喂给实现的函数，对比结果集，若为true,则正确，检查函数正确性
        for (int i = 0; i < resultVector.length; i++) {
            ListNode result = s.deleteDuplicates(testVector[i]);
            System.out.println(s.helloWorld(result, resultVector[i]));
            System.out.println("----------------------------------");
        }
    }
}
