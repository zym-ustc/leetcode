/*
思路：简单的二分查找
 */
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1,right = n;
        while (left <= right){
            int mid = left+(right-left)/2;
            int x = guess(mid);
            if (x == 0) return mid;
            else if (x > 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}