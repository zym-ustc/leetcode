/*
思路：两两处理交换，注意指针为null值的情况就行
当然也可以用递归的方法，比较巧妙
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
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode a = head.next;
        head.next = swapPairs(head.next.next);
        a.next = head;
        return a;
    }
}
//非递归
public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode x,y,ans,pre;

        if (head == null) return null;
        if (head.next == null) return head;

        pre = null;
        x = head;
        ans = y = x.next;
        while (x != null && y != null){
            if (pre != null) pre.next = y;
            x.next = y.next;
            y.next = x;
            //
            pre = x;
            x = x.next;
            if (x == null) break;
            y = x.next;
        }
        return ans;
    }
}