package com.forcode.base.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description:
 * @author: TJ
 * @date: 2021-05-15
 **/
public class CodeTest {

    /**
     * LeetCode-1
     */
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0, len = nums.length; i < len - 1; i++) {

            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSumV2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * LeetCode-20
     * 有效括号判断
     */
    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        if (charArray.length % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : charArray) {
            if (stack.empty()) {
                stack.push(c);
                continue;
            }
            Character peek = stack.peek();
            if (peek.equals('(') && c == ')') stack.pop();
            else if (peek.equals('{') && c == '}') stack.pop();
            else if (peek.equals('[') && c == ']') stack.pop();
            else stack.push(c);
        }
        return stack.empty();
    }

    /**
     * LeetCode-503
     */
    public static int[] nextGreaterElements(int[] nums) {

        int[] res = new int[nums.length];
        boolean flag = true;
        for (int i = 0, len = nums.length; i < len; i++) {

            for (int j = i + 1; j < len; j++) {
                if (nums[j] > nums[i]) {
                    res[i] = nums[j];
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (int k = 0; k < i; k++) {
                    if (nums[k] > nums[i]) {
                        res[i] = nums[k];
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                res[i] = -1;
            }
            flag = true;
        }
        return res;
    }

    /**
     * LeetCode-9
     * 回文数
     */
    public boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }
        String old = String.valueOf(x);
        String[] split = old.split("");
        Stack<String> stack = new Stack<>();
        for (String s : split) {
            stack.push(s);
        }
        StringBuilder newV = new StringBuilder();
        while (!stack.empty()) {
            newV.append(stack.pop());
        }
        return old.equals(newV.toString());
    }

    /**
     * LeetCode-1450
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {

        int res = 0;
        for (int i = 0, len = startTime.length; i < len; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                res++;
            }
        }
        return res;
    }

    /**
     * LeetCode-13
     * 罗马数字转整数
     */
    public static int romanToInt(String s) {

        Map<String, Integer> map = initRomanData();
        char[] charArray = s.toCharArray();
        if (charArray.length == 1) {
            return map.get(s);
        }
        int sum = 0;
        int slow = 0, fast = 1, len = charArray.length;
        while (true) {
            if (slow >= len) {
                return sum;
            }
            if (fast >= len) {
                sum += map.get(String.valueOf(charArray[slow]));
                return sum;
            }

            boolean match = false;
            String last = String.valueOf(charArray[slow]);
            String temp = String.valueOf(charArray[fast]);
            // "MCMXCIV"
            // 1000 + 900 + 90 + 4
            if (last.equals("I")) {
                if (temp.equals("V")) {
                    sum += 4;
                    match = true;
                } else if (temp.equals("X")) {
                    sum += 9;
                    match = true;
                }
            } else if (last.equals("X")) {
                if (temp.equals("L")) {
                    sum += 40;
                    match = true;
                } else if (temp.equals("C")) {
                    sum += 90;
                    match = true;
                }
            } else if (last.equals("C")) {
                if (temp.equals("D")) {
                    sum += 400;
                    match = true;
                } else if (temp.equals("M")) {
                    sum += 900;
                    match = true;
                }
            }
            if (match) {
                slow += 2;
                fast += 2;
            } else {
                sum += map.get(last);
                slow += 1;
                fast += 1;
            }
        }
    }

    private static Map<String, Integer> initRomanData() {

        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        return map;
    }

    /**
     * LeetCode-12
     * 整数转罗马数字
     */
    public String intToRoman(int num) {

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[num / 1000] +
                hundreds[num % 1000 / 100] +
                tens[num % 100 / 10] +
                ones[num % 10];
    }

    /**
     * LeetCode-26
     * 删除有序数组中的重复项
     */
    public int removeDuplicates(int[] nums) {

        if (nums.length == 1) {
            return 1;
        }
        int index = 0;
        for (int i = 1, len = nums.length; i < len; i++) {

            if (nums[i] > nums[index]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }

    /**
     * LeetCode-14
     * 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {

        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder res = new StringBuilder();
        char cur;
        int index = 0;
        for (char c : strs[0].toCharArray()) {

            cur = c;
            for (int i = 1, len = strs.length; i < len; i++) {
                char[] chars = strs[i].toCharArray();
                if (index >= chars.length || chars[index] != cur) {
                    return res.toString();
                }
            }
            index++;
            res.append(cur);
        }
        return res.toString();
    }

    /**
     * LeetCode-283
     * 将数组中的0移动到末尾
     */
    public static void moveZeroes(int[] nums) {

        int len = nums.length;
        if (len == 1) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] != 0) {
                stack.push(nums[i]);
            }
        }
        for (int i = 0; i < len; i++) {
            if (!stack.empty()) {
                nums[i] = stack.pop();
            } else {
                nums[i] = 0;
            }
        }
    }

    /**
     * LeetCode-566
     * 重塑矩阵
     */
    public static int[][] matrixReshape(int[][] mat, int r, int c) {

        int oldLength = mat.length * mat[0].length;
        if (oldLength != r * c) {
            return mat;
        }
        int[][] res = new int[r][c];
        int w = 0, h = 0;
        for (int[] arr : mat) {
            for (int k : arr) {

                if (w == (c - 1)) {
                    res[h][w] = k;
                    // 换下一行
                    w = 0;
                    h++;
                } else {
                    res[h][w] = k;
                    w++;
                }

            }
        }
        return res;
    }

    /**
     * LeetCode-645
     */
    public int[] findErrorNums(int[] nums) {
        int[] errorNums = new int[2];
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 1; i <= n; i++) {
            int count = map.getOrDefault(i, 0);
            if (count == 2) {
                errorNums[0] = i;
            } else if (count == 0) {
                errorNums[1] = i;
            }
        }
        return errorNums;
    }

    /**
     * LeetCode-128
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        int temp = 1;
        for (int i = 1, len = nums.length; i < len; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] - nums[i - 1] == 1) {
                temp++;
            } else {
                res = Math.max(res, temp);
                temp = 1;
            }
        }
        res = Math.max(res, temp);
        return res;
    }

    /**
     * LeetCode-240
     * 搜索二维矩阵中的值
     */
    public static boolean searchMatrix(int[][] matrix, int target) {

        int p = 0;
        int i = matrix.length;
        int j = matrix[0].length;
        while (p < i && p < j) {
            if (matrix[p][p] == target) {
                return true;
            } else if (matrix[p][p] > target) {
                break;
            }
            p++;
        }
        // 横向搜索
        p = p - 1;
        for (int m = 0; m <= p; m++) {
            for (int n = p + 1; n < j; n++) {
                if (matrix[m][n] == target) {
                    return true;
                } else if (matrix[m][n] > target) {
                    break;
                }
            }
        }
        // 纵向
        for (int m = p + 1; m < i; m++) {
            for (int n = 0; n <= p; n++) {
                if (matrix[m][n] == target) {
                    return true;
                } else if (matrix[m][n] > target) {
                    break;
                }
            }
        }
        return false;
    }

    public static boolean searchMatrixV2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }

    /**
     * LeetCode-27
     * 移除元素
     */
    public int removeElement(int[] nums, int val) {

        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = nums.length - 1;
        while (slow < fast) {

            if (nums[slow] == val) {

                while (nums[fast] == val && fast > slow) {
                    fast--;
                }
                if (slow == fast) {
                    return slow;
                }
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                fast--;
            }
            slow++;
        }
        if (slow >= 0 && nums[slow] == val) {
            return slow;
        }
        return slow + 1;
    }

    /**
     * LeetCode-28
     * 返回 needle 在 haystack 中第一次出现的位置
     */
    public int strStr(String haystack, String needle) {
        int h = haystack.length();
        int n = needle.length();
        if (h < n) {
            return -1;
        }
        for (int i = 0; i <= h - n; i++) {
            String substring = haystack.substring(i, i + n);
            if (substring.equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * LeetCode-35
     */
    public int searchInsert(int[] nums, int target) {

        if (nums.length == 1) {
            return target > nums[0] ? 1 : 0;
        }
        int left = 0, right = nums.length - 1;

        while (left < right) {
            if (right - left == 1) {
                if (nums[left] >= target) {
                    return left;
                } else if (nums[right] < target) {
                    return right + 1;
                } else {
                    return right;
                }
            }

            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return 0;
    }

    /**
     * LeetCode-7
     * 整数反转
     */
    public static int reverseInt(int x) {
        int res = 0;
        int n = x < 0 ? String.valueOf(x).length() - 1 : String.valueOf(x).length();
        while (x / 10 != 0) {

            int mod = x % 10;
            if (mod != 0) {
                for (int k = 0; k < n - 1; k++) {
                    // 一直乘10也可能超限
                    if (mod > 0 && Integer.MAX_VALUE / mod < 10) return 0;
                    if (mod < 0 && mod != -1 && Integer.MIN_VALUE / mod < 10) return 0;
                    mod *= 10;
                }
                if (x < 0) {
                    if (Integer.MIN_VALUE - res > mod) {
                        return 0;
                    }
                } else {
                    if (Integer.MAX_VALUE - res < mod) {
                        return 0;
                    }
                }
            }
            res += mod;
            x /= 10;
            n -= 1;
        }
        res += x;
        return res;
    }

    public static int reverseIntV2(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

    /**
     * LeetCode-4
     * 寻找两个正序数组的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] arr = new int[nums1.length + nums2.length];
        int index = 0, n1 = 0, n2 = 0;
        while (n1 <= nums1.length - 1 || n2 <= nums2.length - 1) {
            if (n1 <= nums1.length - 1) {

            }
        }
        return 0d;
    }

    /**
     * LeetCode-1455
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] arr = sentence.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * LeetCode-66
     */
    public static int[] plusOne(int[] digits) {
        String val = "";
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int temp = digits[i] + carry;
            carry = temp >= 10 ? 1 : 0;
            val = (temp % 10) + val;
        }
        if (carry == 1) {
            val = carry + val;
        }
        String[] split = val.split("");
        int[] res = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            res[i] = Integer.parseInt(split[i]);
        }
        return res;
    }

    public static void main(String[] args) {

//        int[] ints = {5,4,3,2,1};
//        nextGreaterElements(ints);

//        System.out.println(romanToInt("MCMXCIV"));

//        reverseInt(-901000);

    }

}
