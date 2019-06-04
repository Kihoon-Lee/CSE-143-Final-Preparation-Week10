// CSE 143 Final Preparation Answers (Binary Trees) - Week 9 

public class IntTree {
    public IntTreeNode overallRoot;

    // post: constructs an empty tree
    public IntTree() {
        overallRoot = null;
    }

    // pre : max > 0
    // post: constructs a sequential tree with given number of
    //       nodes
    public IntTree(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }

    // 1. construct
    void construct(int[] ls) {
        overallRoot = construct(ls, 0, ls.length - 1);
    }

    private IntTreeNode construct(int[] ls, int start, int end) {
        if (start > end) {
            return null;
        } else {
            int mid = (start + end + 1) / 2;
            return new IntTreeNode(ls[mid], construct(ls, start, mid - 1), 
                                construct(ls, mid + 1, end));
        }
    }

    // 2. makeFull
    void makeFull() {
        overallRoot = makeFull(overallRoot, 1);
    }

    private IntTreeNode makeFull(IntTreeNode root, int level) {
        if (root != null) {
            if (root.left == null && root.right != null) {
                root = new IntTreeNode(-level, root, root.right);
                root.left.right = null;
            } else if (root.left != null && root.right == null) {
                root = new IntTreeNode(-level, root.left, root);
                root.right.left = null;
            }
                root.left = makeFull(root.left, level + 1);
                root.right = makeFull(root.right, level + 1);
        }
        return root;
    }
    /*************************************************************/
    /**********************IntTree methods from class*************/
    // post: returns a sequential tree with n as its root unless
    //       n is greater than max, in which case it returns an
    //       empty tree
    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntTreeNode(n, buildTree(2 * n, max),
                                   buildTree(2 * n + 1, max));
        }
    }

    // pre : tree is a binary search tree
    // post: value is added to overall tree so as to preserve the binary search
    //       tree property
    public void add(int value) {
        overallRoot = add(overallRoot, value);
    }

    // post: value is added to given binary search tree so as to preserve the
    //       binary search tree property
    private IntTreeNode add(IntTreeNode root, int value) {
        if (root == null) {
            root = new IntTreeNode(value);
        } else if (value <= root.data) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
	}
        return root;
    }

    // post: prints the tree contents using a preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    // post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    // post: prints the tree contents using an inorder traversal
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    // post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    // post: prints the tree contents, one per line, following an
    //       inorder traversal and using indentation to indicate node
    //       depth; prints right to left so that it looks correct when
    //       the output is rotated; prints "empty" for an empty tree
    public void printSideways() {
        if (overallRoot == null) {
            System.out.println("empty tree");
        } else {
            printSideways(overallRoot, 0);
        }
    }

    // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }  
        }
    }
}
