/*
思路：题意为，给定两个非降序列，求两序列的中位数
        a[0..n-1] b[0..m-1]
    解法1：
        首先有
        1.如果n+m为奇数，那么中位数的左边有(n+m-1)/2个元素
        2.如果n+m为偶数, 中位数为两数平均，第一个数左边有（n+m-1)/2个元素
        设这个数为rest
        假设我们把a中元素a[k]作为中位数，那么如何判断这个中位数是正确呢？设在序列a中，已经得出有k个数比a[k]小，
        那么我们判断a[k]是否在b序列中满足 b[rest-k-1]<=a[k]<=b[rest-k]，如果满足，则这个数可以作为中位数或其一。
        （这里注意，由于有相等的情况，下标不一定就是中位数的下标，但是值一定是相等的）
       如果不满足，那么依据情况判断这个数a[k]是大了还是小了，所以这个a[k]我们可以二分来求得。
       当然中位数也可能不存在a序列中，所以我们在b序列中也要算一次。
       如果和为偶数，我们现在求得的数是其中较小的一个，那么另一个怎么求得？rest+1再二分一次吗？
       再二分一次是错的，因为有可能求得的数依旧是这个位置！（相等情况），除非特殊处理。
       我们思考下就可以知道，假设较小数为a[k]，那么较大数只可能存在于
       a[k-1](a[k]=a[k-1]相等情况)，
       a[k+1],
       b[rest-k],
       b[rest-k-1](a[k]=b[rest-k-1]相等情况)
       这四个位置！
       所以我们只要判断这四个位置是否满足情况，然后求得较小的值即可。
       时间复杂度是O(log(n)+log(m))
    解法2：https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation
        首先我们需要理解中位数的概念，我们以中位数为界限，将序列分成两个部分，即
        len(a[0..i-1]） == len(a[i..n-1]))  AND
        max(a[0..i-1]) <= min(a[i..n-1])
        所以我们只要找到一个分界能将序列分组且满足上述情况，那么中位数就可以很容易求得（就是边界的情况）
        我们来看看双数组a[0..n-1] b[0..m-1]的情况，也是分成两个部分
        part1          part2
        a[0..i-1]      a[i..n-1]
        b[0..j-1]      b[j..m-1]
        满足
        1.len(part1) == len(part2) (即i+j == m+n-i-j 或 i+j == m+n-i-j+1)
        2.max(part1) <= min(part2) (即a[i-1]<=b[j] and b[j-1]<=a[i])
        所以我们在a数组中二分i，那么j=(n+m+1)/2-i;
        然后根据条件2来判断分界正确性和调整二分
        最后结果为
        1)和为奇数，max(a[i-1],b[j-1])为中位数
        2)和为偶数，(max(a[i-1],b[j-1]+min(a[i],b[j]))/2位中位数
        这里为了使得j(j=(n+m+1)/2-i)的值不为负数，我们设置n <= m；
        所以复杂度为O(log(min(n,m)))
 */

//solution 1
public class Solution {
    private int findMedian(int[] a1,int[] a2, int rest){
        int n = a1.length, m = a2.length;
        int left = 0, right = n-1, mid, res;
        while (left <= right){
            mid = left + (right - left) / 2;
            res = islegal(a1,a2,mid,rest);
            if (res == 0)
                return mid;
            else if (res > 0)
                left = mid + 1;
            else
                right = mid-1;
        }
        return -1;
    }
    private int islegal(int[] a1,int[] a2,int x,int rest){
        int n = a1.length, m = a2.length;
        int z = rest - x;
        //1 xiao le
        //0 zhengque
        //-1 da le
        if (z > m) return 1;
        else if (z < 0) return -1;
        else if (z == m)
            if (a1[x] >= a2[m-1]) return 0;
            else return 1;
        else if (z == 0)
            if (a1[x] <= a2[0]) return 0;
            else return -1;
        else if (a1[x] > a2[z]) return -1;
        else if (a1[x] < a2[z-1]) return 1;
        else return 0;
    }

    private double findMedianSingle(int[] a){
        int n = a.length;
        if (n%2 == 1) return a[n/2];
        else return (((double)a[n/2-1]+a[n/2])/2);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n,m,ans1,ans2,z;
        double val1,val2;
        n = nums1.length;
        m = nums2.length;
        if (n == 0) return findMedianSingle(nums2);
        if (m == 0) return findMedianSingle(nums1);
        int rest = (n + m - 1) / 2;
        //find median from nums1
        ans1 = findMedian(nums1,nums2,rest);
        //find median from nums2
        int p;
        if (ans1 == -1){
            ans1 = findMedian(nums2,nums1,rest);
            val1 = nums2[ans1];
            p = -1;
        }
        else {
            p = 1;
            val1 = nums1[ans1];
        }
        if ((n + m) % 2 == 1)
            return val1;
        else{
            val2 = Integer.MAX_VALUE;
            if (p > 0){
                if (ans1 < n-1 && islegal(nums1,nums2,ans1+1,rest+1) == 0) val2 = Math.min(val2,nums1[ans1+1]);
                if (ans1 > 0 && islegal(nums1,nums2,ans1-1,rest+1) == 0) val2 = Math.min(val2,nums1[ans1-1]);
                z = rest - ans1;
                if (z < m && islegal(nums2,nums1,z,rest+1) == 0) val2 = Math.min(val2,nums2[z]);
                if (z - 1 > 0 && islegal(nums2,nums1,z-1,rest+1) == 0) val2 = Math.min(val2,nums2[z-1]);
            }
            else{
                if (ans1 < m-1 && islegal(nums2,nums1,ans1+1,rest+1) == 0) val2 = Math.min(val2,nums2[ans1+1]);
                if (ans1 > 0 && islegal(nums2,nums1,ans1-1,rest+1) == 0) val2 = Math.min(val2,nums2[ans1-1]);
                z = rest - ans1;
                if (z < n && islegal(nums1,nums2,z,rest+1) == 0) val2 = Math.min(val2,nums1[z]);
                if (z - 1 > 0 && islegal(nums1,nums2,z-1,rest+1) == 0) val2 = Math.min(val2,nums1[z-1]);
            }
            return (val1+val2)/2;
        }

    }
}
// solution 2
public class Solution {
    public double findMedianSortedArrays(int[] a, int[] b) {
        int n,m;
        int t[];
        if (a.length > b.length){
            t = a; a = b; b = t;
        }
        n = a.length;
        m = b.length;
        int left = 0, right = n;
        double val1,val2;
        while (left <= right){
            int i = (left+right)/2;
            int j = (n+m+1)/2 - i;
            if (i > 0 && j < m && a[i-1] > b[j])
                right = i - 1;
            else if (j > 0 && i < n && b[j-1] > a[i])
                left = i + 1;
            else {
                if (i == 0) val1 = b[j-1];
                else if (j == 0) val1 = a[i-1];
                else val1 = Math.max(a[i-1],b[j-1]);
                // odd
                if ((n + m) % 2 == 1) return val1;
                // even
                if (i == n) val2 = b[j];
                else if (j == m) val2 = a[i];
                else val2 = Math.min(a[i],b[j]);
                return (val1+val2)/2;
            }
        }
        return 0;
    }
}