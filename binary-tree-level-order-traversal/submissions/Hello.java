//测试用例描述
class TreeN {
    int val;
    TreeN left;
    TreeN right;
    TreeN() {}
    TreeN(int val) { this.val = val; }
    TreeN(int val, TreeN left, TreeN right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//需要实现的方法
class Solution {
    public List<List<Integer>> levelOrder(TreeN root) {

        List<List<Integer>> lists = new ArrayList<>();  //定义一个大列表
        Queue<TreeN> queue = new LinkedList();     // 定义链队列
        int sum = 0;      //定义当前层次的结点数
        int count = 0;    //统计增加的结点数


        if(root!=null){
            queue.add(root);
            sum++;
        }

        while(!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();   //进入下一层，建立一个小列表
            for(int i=0;i<sum;i++){                   //确定把当前层结点数输出完毕
                TreeN remove = queue.remove();        //取出队列头元素，直至sum==0
                list.add(remove.val);                 //把取出的元素存入列表
                if(remove.left != null){              //左结点不为空，存入队列，count++
                    queue.add(remove.left);
                    count++;
                }
                if(remove.right != null){             //右结点不为空，存入队列，count++
                    queue.add(remove.right);
                    count++;
                }
            }
            sum = count;                              //当前层输出完毕，获得下一层的结点数 
            count = 0;                                //重新计数
            lists.add(list);                          //把当前小列表，存入大列表
        }

        return lists;
    }
}

public class Test{

    public static void main(String[] args){
        //测试用例的可选用结点和分支树
        TreeN t4 = new TreeN(4);
        TreeN t5 = new TreeN(5);
        TreeN t8 = new TreeN(8);
        TreeN t6 = new TreeN(6,t8,null);
        TreeN t7 = new TreeN(7);
        TreeN t2 = new TreeN(2,t4,t5);
        TreeN t3 = new TreeN(3,t6,t7);

        //测试用例
        TreeN[] test = new TreeN[]{
                new TreeN(0,t2,t3),
                new TreeN(1,t2,t6)
        };

        Solution solution = new Solution();
        for(int i=0;i<2;i++){
            List<List<Integer>> listList = solution.levelOrder(test[i]);
            System.out.println("第" + i + "用例: " + listList);
        }
    }
}
