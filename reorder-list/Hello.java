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
    public void reorderList(ListNode head) {
        ListNode tempHead = head;

        List<Integer> list = new ArrayList<>();    //建立list,存储链表全部的值
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        head = tempHead;

        for (int i = 0; i < list.size() / 2; i++) {                    //以后面需要插入到前面的元素总长度为条件
            ListNode node = new ListNode(list.get(list.size() - 1 - i), head.next);  //建立新结点，从list最后的元素开始取值，并指向当前结点所指向的结点
            head.next = node;                           //令当前结点指向新结点
            if (i == list.size() / 2 - 1) {                   //判断当前是否为最后一个所需插入的结点
                if (list.size() % 2 == 0) {                  //判断原链表总长度是否为偶数
                    node.next = null;                 //是，令新结点指向空
                } else {
                    node.next.next = null;              //否，令新结点所指向的下一个结点指向空
                }
            }
            head = head.next.next;             //由于插入了新结点，所以需进入到后第二位，也可以写为=node.next
        }

        head = tempHead;                  //回到头结点
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
                //1,2,3,4
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null)))),
                //1,2,3,4,5
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))))
        };
        // @doc resultVector 是测试用例集合
        ListNode[] resultVector = new ListNode[]{
                //1,4,2,3
                new ListNode(1, new ListNode(4, new ListNode(2, new ListNode(3, null)))),
                //1,5,2,4,3
                new ListNode(1, new ListNode(5, new ListNode(2, new ListNode(4, new ListNode(3, null))))),
        };

        Solution s = new Solution();
        // 将测试用例逐条喂给实现的函数，对比结果集，若为true,则正确，检查函数正确性
        for (int i = 0; i < resultVector.length; i++) {
            s.reorderList(testVector[i]);
            System.out.println(s.helloWorld(testVector[i], resultVector[i]));
            System.out.println("----------------------------------");
        }
    }
}
