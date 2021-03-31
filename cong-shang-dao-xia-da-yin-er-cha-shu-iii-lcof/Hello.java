// @doc TestCase 描述测试用例
class TestCase {
    int val;
    TestCase left;
    TestCase right;

    TestCase() {

    }

    public TestCase BuildTestCase(TestCase testCase, int[] x, int y) {
        testCase = new TestCase();
        testCase.left = new TestCase();
        testCase.right = new TestCase();

        testCase.val = x[y];

        y = 2 * y + 1;
        if (y < x.length) {
            testCase.left = BuildTestCase(testCase.left, x, y);
        }
        y = y + 1;
        if (y < x.length) {
            testCase.right = BuildTestCase(testCase.right, x, y);
        } else {
            testCase.right = null;
        }

        return testCase;
    }
}


// @doc 需要实现的类
class Solution {
    public List<List<Integer>> levelOrder(TestCase root) {
        Queue<TestCase> queue = new LinkedList<TestCase>();
        List<List<Integer>> lists = new ArrayList();

        if (root != null) {
            queue.add(root);
        }

        int layer = 1;
        while (!queue.isEmpty()) {
            int sum = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < sum; i++) {
                TestCase Node = queue.remove();
                list.add(Node.val);
                if (Node.left != null) {
                    queue.add(Node.left);
                }
                if (Node.right != null) {
                    queue.add(Node.right);
                }
            }
            if (layer % 2 == 0) Collections.reverse(list);
            lists.add(list);
            layer++;
        }

        return lists;
    }
}


// @doc 测试工具类，不需要提交到 LeetCode
public class HelloWorld {
    public static void main(String[] args) {
        // @doc testVector 是测试用例集合
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] b = {12, 9, 0, 15, 98, 41, 100};
        TestCase[] testVector = new TestCase[]
                {
                        new TestCase().BuildTestCase(new TestCase(), a, 0),
                        new TestCase().BuildTestCase(new TestCase(), b, 0)
                };

        Solution s = new Solution();

        // 将测试用例逐条喂给实现的函数，检查函数正确性
        for (int i = 0; i < testVector.length; i++) {
            List<List<Integer>> lists = s.levelOrder(testVector[i]);
            lists.remove(lists.size() - 1);
            System.out.println(lists);
            System.out.println("-------------------------");
        }
    }
}
