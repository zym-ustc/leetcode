/*
思路：非常有意思的一道题，题意是说a[i]表示在第i的位置最多往后跳a[i]步
     1.最容易想到的就是dp,f[i]表示到达i位置最少的跳跃次数,f[i+k] = min{f[i+k],f[i]+1}; 0<k<=a[i]
       这个复杂度是平方级别的。会超时
     2.贪心，当前在第i位置，能跳到的位置是[i+1..i+a[i]]这几个位置，那么我跳到哪个位置最优呢？
        是跳到最远的地方吗（i+a[i]）？不一定，比如极端情况，i+a[i]这个位置的值可能为0，那么之后就永远也跳不了了。
        所以我们应该选择调到覆盖范围最远的点即 max{a[k]+k}, i+1<=k<=i+a[i],这样能保证这个点能扩展到最远。
        直接处理可以AC，但是我们分析一下可以发现，这个复杂度最差也是平方级别的，因为每次都要扫一遍可能的到达点，
        然后很可能每次跳到的位置只前进一步。。。
        优化一下，可以发现，我们每次求得都是a[k]+k的最大值，每个位置的这个值只和自己有关，所以我们其实求的是某段中最大值
        max{a[k]+k}   i<=k<=j
        如果能用某种数据结构维护的话（线段树，堆。。。）就能快速求出了（一般是logn），不需要每次都扫一遍，复杂度可以降为O（nlogn）
     3.思想其实是BFS，
       跳跃0次，我们可以到达的右边界是0位置         f[0] = 0
       跳跃1次，以0位置扩展，我们可以到达的位置右边界是k1    f[1..k1] = 1
       跳跃2次，以[1..k1]进行扩张，我们可以到达的位置右边界是k2  f[k1+1,k2] = 2
       跳跃3次，以[k1+1..k2]进行扩张，我们可以到达的位置右边界是k3  f[k2+1,k3] = 3;
       。。。
       最后答案是f[n-1]，但其实可以发现f数组没有必要
 */
//贪心
public class Solution {
    public int jump(int[] nums) {
        int i = 0;
        int step = 0;
        while (i < nums.length-1){
            int x = nums[i];
            if (x == Integer.MAX_VALUE) continue;
            int p = i+1;
            for (int j = i+1; j < nums.length && j <= i + x; j++){
                if(nums[j]+j >= nums[p]+p || j == nums.length -1) p = j;
            }
            i = p;
            step++;
        }
        return step;
    }
}

//bfs
public class Solution {
    public int jump(int[] nums) {
        int max = 0;
        int [] f = new int [nums.length];
        int step = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++){
            f[i] = step;
            max = Math.max(nums[i]+i,max);
            if (i == right){
                right = max;
                step++;
            }
        }
        return f[nums.length-1];
    }
}