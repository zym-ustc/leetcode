/*
思路：从后往前扫，找到第一次出现a[i]<a[i+1]的位置，这时候a[i]是我们需要改变的第一个元素，但是注意不是和a[i+1]交换，是在
    a[i+1..n-1]这个区间中，找到最后一个大于他的元素，位置为p，然后交换a[i],a[p]。
    比如：[3,5,4,4,2,1] 我们是将3和第二个4交换。
    然后将a[i+1..n-1]字典序排序，整个序列即为所求。
    tips：a[i+1..n-1]排序的操作不需要真正的用到排序算法，因为这段元素肯定是从大到小的顺序，所以我们只要reverse就可以了。
    tips：交换时如果采用位运算异或，则要判断这两个交换的数不是同一地址，负责出错，所以位运算异或交换慎用。
 */
public class Solution {
    private void swap(int[] a,int i,int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public void nextPermutation(int[] a) {
        int i;
        for (i = a.length-2; i >= 0; i--){
            if (a[i] < a[i+1]) break;
        }
        if (i >= 0){
            int p = i+1;
            for (int j = i+1; j < a.length; j++){
                if (a[j] > a[i] && a[p] >= a[j]) p = j;
                else if (a[j] < a[i]) break;
            }
            swap(a,i,p);
        }
        if (i+1 <= a.length-1)
            reverse(a,i+1,a.length-1);
    }
    public void reverse(int[] a,int l,int r){
        //System.out.println(l+" "+r);
        for (int i = l; i <= (l+r-1)/2; i++){
            swap(a,i,r-(i-l));
        }
    }
}