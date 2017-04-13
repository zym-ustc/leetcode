/*
思路：中序遍历二叉查找树，输出第k个点即可。
    PS：针对题目中提出的follow up（如果树经常变化，如何应对频繁的第kth数询问？）
    我们可以在树节点中添加一位count，记录当前这个子树有多少的节点，那么一个k询问，根据count,我们就可以判断它在左子树还是右子树中。
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int i=0,ans = 0;
    public void dfs(TreeNode x, int k){
        if (x == null) return;
        dfs(x.left,k);
        i++; if (i == k) ans = x.val;
        dfs(x.right,k);
    }
    public int kthSmallest(TreeNode root, int k) {
        dfs(root,k);
        return ans;
    }
}