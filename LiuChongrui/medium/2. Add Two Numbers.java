/*
思路：模拟题，位数不够的情况我们可以置0，注意进位,
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int g=0,v1,v2,val;
        ListNode sta = null,p = null;
        while (l1 != null || l2 != null || g != 0){
            v1 = (l1 == null? 0:l1.val);
            v2 = (l2 == null? 0:l2.val);

            val = (v1 + v2 +g)%10;
            g = (v1 + v2 +g)/10;
            ListNode q = new ListNode(val);
            if (p != null) p.next = q;
            else sta = q;
            p = q;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return sta;
    }
}