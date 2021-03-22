/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){                     //判断结点是否为空，或者树是否为空
            return null;
        }

        TreeNode temp = root.left;          //定义一个temp,暂时存储左结点
        root.left = root.right;             //令左结点等于右结点
        root.right = temp;                  //令右结点等于temp，即等于原来的左结点

        mirrorTree(root.left);              //递归
        mirrorTree(root.right);

        return root;

    }
}
