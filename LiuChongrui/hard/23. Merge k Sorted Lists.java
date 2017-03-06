/*
思路：一开始的想法是比较K个lists的头结点的最小值，就是K个串一起归并一次的想法，发现超时。因为如果K很大的话，复杂度是O(K*sigma(n))
所以还是采用二路归并的，二分到两两归并。复杂度是O（segma（n）*logK）？
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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        if (lists.length == 0) return head;
        head = domerge(lists,0,lists.length-1);
        return head;
    }
    public ListNode domerge(ListNode[] lists,int l,int r){

        if (l == r) return lists[l];
        int mid = (l+r)/2;
        ListNode listA = domerge(lists,l,mid);
        ListNode listB = domerge(lists,mid+1,r);
        return merge2Lists(listA,listB);
    }
    public ListNode merge2Lists(ListNode a,ListNode b){
        ListNode head=null,p=null;
        while (a != null && b != null){
            if (a.val > b.val){
                if (head == null){
                    head = b; p = b;
                }
                else{
                    p.next = b;
                    p = p.next;
                }
                b = b.next;
            }
            else{
                if (head == null){
                    head = a; p = a;
                }
                else{
                    p.next = a;
                    p = p.next;
                }
                a = a.next;
            }

        }
        if (a != null)
        {
            if (head == null){head = p = a;}
            else{
                p.next = a;
            }
        }
        if (b != null){
            if (head == null){head = p = b;}
            else{
                p.next = b;
            }
        }
        return head;
    }
}