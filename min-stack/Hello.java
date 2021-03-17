// @doc 需要实现的类
class MinStack {
    private int CAPACITY = 10010;   //定义栈（数组）数组存储空间最大值
    int len;          //栈（数组）真实长度
    int[] arr;

    /** initialize your data structure here. */
    public MinStack() {
        len = -1;         //代表栈（数组）为空
        arr = new int[CAPACITY];
    }

    public void push(int x) {
        if(len>CAPACITY-1){      //判断栈（数组）是否为空，为空则跳出函数
            return;
        }

        arr[++len] = x;    //把元素压入栈
    }

    public void pop(){
        if(len<0){        //判断栈（数组）是否为空，为空则跳出函数
            return;
        }
        arr[len] = 0;     //提醒：元素已出栈
        len--;         //去掉尾结点，数组减小一
    }

    public int top(){
        if(len<0){        //判断栈（数组）是否为空，为空则跳出函数
            return -1;
        }

        int num = arr[len];   //提取顶结点的数据
        return num;
    }

    public int getMin(){
        if(len<0){
            return -1;
        }

        int min = arr[0];
        for(int i=1;i<=len;i++){     //依次比较min和各个结点的数据
            if(min>arr[i]){
                min = arr[i];
            }
        }
        return min;
    }
}

// @doc 测试工具类，不需要提交到 LeetCode
public class HelloWorld
{
    // @doc TestCase 描述测试用例
    static class TestCase
    {
        String[] st;
        int[] ints;

        public TestCase(){
        }

        public TestCase(String[] a,int[] b){
            st = new String[a.length];
            ints = new int[a.length];

            st = a;
            ints = b;
        }
    }

    public static void main(String[] args)
    {
        // @doc testVector 是测试用例集合
        TestCase[] testCase = new TestCase[]{
                //由于加入第二个元素的话，需要设计不属于目的类的方法去清空栈，故只用一个例子
                new TestCase(new String[]{"MinStack","push","push","push","getMin","pop","top","getMin"}
                        ,new int[]{' ',-2,0,-3,' ',' ',' ',' '}),
        };

        MinStack minStack = new MinStack();
        // 将测试用例逐条喂给实现的函数，检查函数正确性
        for(int i=0;i<testCase.length;i++){
            for(int j=0;j<testCase[i].st.length;j++){
                if(testCase[i].st[j].equals("push")){
                    minStack.push(testCase[i].ints[j]);
                    System.out.print("null" + ", ");
                }
                else if(testCase[i].st[j].equals("pop")){
                    minStack.pop();
                    System.out.print("null" + ", ");
                }
                else if(testCase[i].st[j].equals("top")){
                    System.out.print(minStack.top() + ", ");
                }
                else if(testCase[i].st[j].equals("getMin"))
                    System.out.print(minStack.getMin() + ", ");
                else{
                    System.out.print("null" + ", ");
                }
            }
        }
    }
}
