/*
思路：1.二分 2.牛顿迭代法
 */
public class Solution {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        while (left + 1 < right){
            int mid = left+(right-left)/2;
            long res = (long)mid*mid;
            if (res == x) return mid;
            else if (res > x) right = mid-1;
            else left = mid;
        }
        if ((left+1)*(left+1) <= x) left=left+1;
        return left;
    }
}
public class Solution {
    public int mySqrt(int x) {
        double exp = 0.0001;
        double newres = x,oldres;
        do{
            oldres = newres;
            newres = oldres-(oldres*oldres-x)/(2*oldres);
        }while (Math.abs(newres-oldres) > exp);
        return (int)newres;
    }
}