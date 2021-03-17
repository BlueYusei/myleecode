class Solution {                                        //目标函数
    public int[][] transpose(int[][] matrix) {

        int[][] arr = new int[matrix[0].length][matrix.length];

        for(int i=0;i< matrix.length;i++){
            for(int j=0;j< matrix[0].length;j++){
                arr[j][i] = matrix[i][j];
            }
        }
        return arr;
    }
}


public class Test{

    static class TestCase{                                //测试用例的描述
        int[][] ints;

        public TestCase(int[][] a){
            ints = new int[a.length][a[0].length];
            ints = a;
        }
    }

    public static void main(String[] args){
       
        TestCase[] testCases = new TestCase[]{                                  //测试用例
              new TestCase(new int[][]{{1,2,3,4},{4,5,6,7},{7,8,9,0}}),
                new TestCase(new int[][]{{1,2},{3,4},{5,6},{7,8}})
        };

        int num = 0;
        int[][] b = new Solution().transpose(testCases[num].ints);

                                                                                //逐条测试
        while(num<testCases.length){
            System.out.println("第" + (num+1) + "个结果为：");
            for(int i=0;i<b.length;i++){
                for(int j=0;j<b[0].length;j++){
                    if(j==b[0].length-1) System.out.println(b[i][j]);
                    else System.out.print(b[i][j] + " ");
                }
            }
            num++;
            if(num==testCases.length) break;
            b = new Solution().transpose(testCases[num].ints);
        }
    }
}
