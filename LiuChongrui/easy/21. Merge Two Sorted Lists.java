/*
思路：归并排序，注意处理下首指针。
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode l3 = new ListNode(0);
        ListNode l = l3;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                l3.next = l1;
                l1 = l1.next;
                l3 = l3.next;
            }
            else {
                l3.next = l2;
                l2 = l2.next;
                l3 = l3.next;
            }
        }
        if (l1 != null) l3.next = l1; else l3.next = l2;
        return l.next;
    }
}