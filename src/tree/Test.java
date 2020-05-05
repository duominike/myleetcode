package tree;

public class Test {
    public static void main(String[] args) {
        Solutions.TreeNode root = new Solutions.TreeNode(1);
        Solutions.TreeNode n1 = new Solutions.TreeNode(2);
        Solutions.TreeNode n5 = new Solutions.TreeNode(5);
        Solutions.TreeNode n3 = new Solutions.TreeNode(3);
        Solutions.TreeNode n4 = new Solutions.TreeNode(4);
        Solutions.TreeNode n6 = new Solutions.TreeNode(6);
        root.left = n1;
        root.right = n5;
        n1.left = n3;
        n1.right = n4;
        n5.left = null;
        n5.right = n6;
        Solutions.flatten(root);
    }
}
