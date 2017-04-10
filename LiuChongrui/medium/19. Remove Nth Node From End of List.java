/*
思路：我们可以用两个快慢指针，慢指针比快指针慢n步，这样当快指针走到链表尾，慢指针正好在需要删除的地方。
    tips：为了避免特殊处理头指针的情况，我们可以在头指针前多加一个虚头指针，一开始两指针都指向虚头。
        这个加虚头指针的处理在处理链表时很常用。
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode headf = new ListNode(0);
        headf.next = head;
        ListNode p = headf, q = headf;
        int count = 0;
        while (p.next != null){
            p = p.next;
            if (count == n) q = q.next;
            else count++;
        }
        q.next = q.next.next;
        return headf.next;
    }
}