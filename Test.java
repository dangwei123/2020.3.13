给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/majority-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int majorityElement(int[] nums) {
        int res=nums[0];
        int count=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==res){
                count++;
            }else{
                count--;
            }
            if(count<0){
                res=nums[i];
                count=1;
            }
        }
        return res;
    }
}

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row=matrix.length;
        if(row==0) return false;
        int col=matrix[0].length;
        int i=0;
        int j=col-1;
        while(i<row&&j>=0){
            if(matrix[i][j]==target){
                return true;
            }else if(matrix[i][j]>target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }
}

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。
class Solution {
    public int fib(int n) {
        if(n<=1) return n;
        int f1=0;
        int f2=1;
        int f3=0;
        for(int i=2;i<=n;i++){
            f3=(f1+f2)%1000000007;
            f1=f2;
            f2=f3;
        }
        return f3;
    }
}

求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
class Solution {
    public int sumNums(int n) {
        boolean flag=n>0&&(n+=sumNums(n-1))>0;
        return n;
    }
}

在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

 
 class Solution {
    public int singleNumber(int[] nums) {
        int a=0;
        int b=0;
        for(int i:nums){
            a=a^i&(~b);
            b=b^i&(~a);
        }
        return a;
    }
}


class Solution {
    public int singleNumber(int[] nums) {
        int[] arr=new int[32];
        for(int num:nums){
            for(int i=0;i<32;i++){
                arr[i]+=num&1;
                num>>>=1;
            }
        }
        int res=0;
        for(int i=0;i<32;i++){
            if(arr[i]%3!=0){
                res+=(int)Math.pow(2,i);
            }
        }
        return res;
    }
}

请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        Node cur=head;
        while(cur!=null){
            Node node=new Node(cur.val);
            node.next=cur.next;
            cur.next=node;
            cur=cur.next.next;
        }
        cur=head;
        while(cur!=null){
            if(cur.random!=null){
                cur.next.random=cur.random.next;
            }
            cur=cur.next.next;
        }
        cur=head;
        Node newHead=head.next;
        while(cur.next!=null){
            Node curNext=cur.next;
            cur.next=curNext.next;
            cur=curNext;
        }
        return newHead;
    }
}

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int index;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length,map);
    }
    private TreeNode buildTree(int[] preorder,int[] inorder,int start,int end,Map<Integer,Integer> map){
        if(index>=inorder.length||start>=end) return null;
        TreeNode root=new TreeNode(preorder[index]);
        int inorderRoot=map.get(preorder[index++]);
        root.left=buildTree(preorder,inorder,start,inorderRoot,map);
        root.right=buildTree(preorder,inorder,inorderRoot+1,end,map);
        return root;
    }
}
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int maxValue(int[][] grid) {
        int row=grid.length;
        if(row==0) return 0;
        int col=grid[0].length;
        int[][] dp=new int[row][col];
        dp[0][0]=grid[0][0];
        for(int i=1;i<col;i++){
            dp[0][i]=grid[0][i]+dp[0][i-1];
        }
        for(int i=1;i<row;i++){
            dp[i][0]=grid[i][0]+dp[i-1][0];
        }
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }
}

一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
class Solution {
    public int[] singleNumbers(int[] nums) {
        int[] res={0,0};
        if(nums.length==0) return res;
        int tmp=0;
        for(int num:nums){
            tmp^=num;
        }
        int right=tmp&(-tmp);
        for(int num:nums){
            if((num&right)==right){
                res[0]^=num;
            }else{
                res[1]^=num;
            }
        }
        return res;
    }
}

