package tree;

public class Test {
    public static void main(String[] args) {
        Solutions.TreeNode root = new Solutions.TreeNode(3);
        Solutions.TreeNode n1 = new Solutions.TreeNode(9);
        Solutions.TreeNode n2 = new Solutions.TreeNode(20);
        Solutions.TreeNode n3 = new Solutions.TreeNode(15);
        Solutions.TreeNode n4 = new Solutions.TreeNode(7);
        root.left = n1;
        root.right = n2;
        n2.left = n4;
        n2.right = n3;
        Solutions.isBalanced(root);
    }
}
