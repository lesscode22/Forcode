package com.forcode.base.algorithm.leetcode.nodelist;

import java.util.*;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2021-12-16
 **/
public class SingleNode {

    public static void main(String[] args) {

        ListNode A5 = new ListNode(5, null);
        ListNode A4 = new ListNode(4, A5);
        ListNode A3 = new ListNode(3, A4);
        ListNode A2 = new ListNode(2, A3);
        ListNode A1 = new ListNode(1, A2);

        print(oddEvenList(A1));
    }

    private static void print(ListNode node) {
        List<Integer> data = new ArrayList<>();
        while (node != null) {
            data.add(node.val);
            node = node.next;
        }
        System.out.println(data);
    }

    // ****************************************************** simple 简单

    /**
     * LeetCode-83
     * 删除已排序链表中的重复元素
     */
    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return head;
        }
        ListNode cur = head.next;
        ListNode node = head;
        while (cur != null) {

            while (cur != null && cur.val == node.val) {
                // 与上个节点值相同
                cur = cur.next;
            }
            node.next = cur;
            node = cur;
            if (cur != null) {
                cur = cur.next;
            }
        }

        return head;
    }


    /**
     * LeetCode-21
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 根据第一个值大小选择基准链表
        ListNode left;
        ListNode right;
        if (list1.val > list2.val) {
            left = list2;
            right = list1;
        } else {
            left = list1;
            right = list2;
        }
        ListNode current = left;
        // 1 3 4 10
        // 3 7 8
        while (true) {

            if (current.next == null) {
                current.next = right;
                return left;
            }
            if (right == null) {
                return left;
            }

            while (current.next.val <= right.val) {
                current = current.next;
                if (current.next == null) {
                    current.next = right;
                    return left;
                }
            }
            // 此时将right节点加入其中
            ListNode lNext = current.next;
            ListNode rNext = right.next;
            current.next = right;
            right.next = lNext;
            right = rNext;
        }
    }

    public static ListNode mergeTwoListsV2(ListNode list1, ListNode list2) {

        ListNode first = new ListNode(0);
        ListNode cur = first;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            cur.next = list2;
        } else {
            cur.next = list1;
        }
        return first.next;
    }

    /**
     * LeetCode-234
     * 判断回文链表
     */
    public boolean isPalindrome(ListNode head) {

        if (head == null) {
            return true;
        }
        StringBuilder order = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            order.append(head.val);
            head = head.next;
        }
        StringBuilder reverseOrder = new StringBuilder();
        while (!stack.empty()) {
            reverseOrder.append(stack.pop());
        }
        return order.toString().equals(reverseOrder.toString());
    }

    // 主要原理为:
    // left 可以理解为将每个节点中的值拼在右边
    // right 可以理解为将每个节点中的值拼在左边
    public boolean isPalindromeV2(ListNode head) {
        ListNode t = head;
        int base = 11, mod = 1000000007;
        int left = 0, right = 0, mul = 1;
        while (t != null) {
            left = (int) (((long) left * base + t.val) % mod);
            right = (int) ((right + (long) mul * t.val) % mod);
            mul = (int) ((long) mul * base % mod);
            t = t.next;
        }
        return left == right;
    }

    /**
     * LeetCode-206
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            // 更改指向, 往前指
            current.next = prev;
            // 保存上个节点
            prev = current;
            current = next;
        }
        return prev;
    }
    

    /**
     * LeetCode-160
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == headB) {
            return headA;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode current = headA;
        while (current != null) {
            set.add(current);
            current = current.next;
        }
        current = headB;
        while (current != null) {
            if (set.contains(current)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    


    /**
     * LeetCode-445
     * 链表相加
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode end = null;
        // 进位
        int carry = 0;
        // 取栈顶元素相加
        while (!s1.empty() || !s2.empty() || carry != 0) {

            int sum = 0;
            if (!s1.empty()) {
                sum += s1.pop();
            }
            if (!s2.empty()) {
                sum += s2.pop();
            }
            sum += carry;

            ListNode node = new ListNode(sum % 10);
            node.next = end;
            end = node;

            carry = sum / 10;
        }
        return end;
    }

    /**
     * LeetCode-328
     * 奇偶合并
     */
    public static ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode bak = head.next;
        while (fast != null) {
            slow.next = fast.next;
            if (slow.next == null) {
                break;
            } else {
                slow = slow.next;
            }

            fast.next = slow.next;
            fast = fast.next;
        }
        slow.next = bak;
        return head;
    }

    /**
     * LeetCode-19
     * 删除链表的倒数第 n 个结点，并且返回链表的头结点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        // 求出节点总数
        int nodeNum = 1;
        ListNode temp = head;
        while (temp.next != null) {
            nodeNum++;
            temp = temp.next;
        }
        // 要删除的前一个节点序号
        int prevDelNum = nodeNum - n;
        if (prevDelNum == 0) {
            // 删除头节点
            return head.next;
        }
        ListNode cur = head;
        while (prevDelNum > 1) {
            cur = cur.next;
            prevDelNum--;
        }
        ListNode delNode = cur.next;
        cur.next = delNode.next;
        return head;
    }

    // 双指针做法: first比second超前n个节点
    public ListNode removeNthFromEndV2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * LeetCode-725
     * 链表分段
     */
    public ListNode[] splitListToParts(ListNode head, int k) {

        ListNode[] res = new ListNode[k];
        // 先求链表长度
        int nodeNum = 0;
        ListNode n = head;
        while (n != null) {
            nodeNum++;
            n = n.next;
        }

        if (nodeNum <= k) {
            n = head;
            for (int i = 0; i < k; i++) {
                if (n != null) {
                    res[i] = new ListNode(n.val);
                    n = n.next;
                } else {
                    res[i] = null;
                }
            }
        } else {
            // 数组中每个链表至少包含的节点数
            int elCount = nodeNum / k;
            // 数组中前mod个链表节点数应该+1
            int mod = nodeNum % k;
            // 链表节点数量记录
            int p = 1;
            ListNode current = head;
            for (int i = 0; i < k; i++) {

                ListNode end = current;
                while (true) {
                    if (p < elCount) {
                        end = end.next;
                        p++;
                    } else {
                        if (i < mod) {
                            ListNode temp = end.next.next;
                            end.next.next = null;
                            res[i] = current;
                            current = temp;

                        } else {
                            ListNode temp = end.next;
                            end.next = null;
                            res[i] = current;
                            current = temp;
                        }
                        p = 1;
                        break;
                    }
                }
            }
        }
        return res;
    }

    /**
     * LeetCode-24
     * 交换相邻节点
     */
    public static ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = new ListNode(0);
        ListNode p = first;
        ListNode current = head;
        while (current.next != null) {
            p.next = current.next;
            p = p.next;

            // 下一个开始交换的起点
            ListNode next = current.next.next;

            p.next = current;
            p.next.next = null;
            p = p.next;

            if (next != null) {
                current = next;
            } else {
                break;
            }
        }
        if (current.next == null) {
            p.next = current;
            p.next.next = null;
        }
        return first.next;
    }

    public static ListNode swapPairsV2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    /**
     * LeetCode-2
     * 两数相加
     */
    public ListNode addTwoNode(ListNode l1, ListNode l2) {
        ListNode start = new ListNode(0);
        ListNode cur = start;
        // 进位
        int carry = 0;
        while (l1 != null || l2 != null) {

            int i = carry;
            if (l1 != null) {
                i += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                i += l2.val;
                l2 = l2.next;
            }
            int mod = i % 10;
            cur.next = new ListNode(mod);
            cur = cur.next;

            carry = i / 10;
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return start.next;
    }
    

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
