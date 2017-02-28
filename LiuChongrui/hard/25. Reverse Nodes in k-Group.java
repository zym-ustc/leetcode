/*
思路：其实就是模拟题，不难，画图把需要用到的指针转化表示清楚就可以了，
     自己代码写得有点麻烦，可以参考discuss中其他的。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre,reright,releft,last,newpre,p,aft;
        int count;

        reright = head;
        pre = null;
        releft = head;
        count = 0;
        while (reright != null){
            count++;
            if (count == k){ // begin change
                newpre = releft;
                last = reright.next;
                while (reright != releft){
                    aft = reright.next;
                    p = releft.next;
                    if (pre != null) pre.next = p;
                    releft.next = aft;
                    reright.next = releft;
                    releft = p;
                }
                if (pre == null) head = reright;
                pre = newpre;
                releft = reright = last;
                count = 0;
            }
            else reright = reright.next;
        }
        return head;
    }
}