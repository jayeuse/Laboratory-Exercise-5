import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

public class Operations {
    Scanner sc = new Scanner(System.in);
    BinarySearchTree displayTree = new BinarySearchTree();
    private boolean isWindowOpen = false;

    String filler;

    public void showTree() {

        if (isWindowOpen) {
            System.out.println("Tree visualization window is already open!");
            return;
        }

        try {
            System.out.println("Show the Tree");
            // An array list of sample values to be inserted into the tree

            int[] values = {50, 30, 70, 20, 40, 60, 80};

            for (int value : values) {
                displayTree.insert(value);
            }

            // Create a GUI window
            JFrame frame = new JFrame("Binary Search Tree Visualization");
            TreePanel panel = new TreePanel(displayTree);

            frame.add(panel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    isWindowOpen = false; // Reset the flag when the window is closed
                }
            });

            isWindowOpen = true;
            frame.setVisible(true);
        } catch (Exception e) {
            System.err.println("Error displaying tree: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertNode() {
        System.out.println("Insert Node");

        while (true){

            System.out.print("Enter Value to Insert: ");
            int value = sc.nextInt();

            if (displayTree.contains(value)) {
                System.out.println("Error: Value  already exists in the tree.");
                System.out.print("Try Again (Y/N)? ");
                char retry = sc.next().toUpperCase().charAt(0);
                if (retry == 'N') {
                    return;
                }

            } else {

                displayTree.insert(value);
                System.out.println (value + "Successfully Inserted!");
                return;
            }
        }
    }

    public void deleteNode() {

        System.out.println("Delete Node");

        while (true){

            System.out.print("Enter Value to Delete: ");
            int value = sc.nextInt();

            if (displayTree.contains(value)){
                displayTree.delete(value);
                System.out.println(value + "Successfully Deleted!");
                return;

            }else {

                System.out.println("Error: Value not found in the tree.");
                System.out.print("Try Again (Y/N)? ");
                char retry = sc.next().toUpperCase().charAt(0);

                if (retry == 'N'){
                    return;
                }
            }
        }
    }

    public void traverseNode() {
        System.out.println("Traverse Node");
        do {
            try {
                clearScreen(true);
                traverseMenuScreen();
                System.out.println("1. InOrder Traversal");
                System.out.println("2. PreOrder Traversal");
                System.out.println("3. PostOrder Traversal");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                char choice = sc.nextLine().charAt(0);
                switch (choice) {
                    case '1':
                        System.out.println("*************************************");
                        inorderTraversal(displayTree.root);
                        System.out.println("*************************************");
                        clearScreen(false);
                        break;
                    case '2':
                        System.out.println("*************************************");
                        preOrderTraversal(displayTree.root);
                        System.out.println("*************************************");
                        clearScreen(false);
                        break;
                    case '3':
                        System.out.println("*************************************");
                        postOrderTraversal(displayTree.root);
                        System.out.println("*************************************");
                        clearScreen(false);
                        break;
                    case '4':
                        System.out.println("Returning to Main Menu");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        clearScreen(false);
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                clearScreen(false);
                sc.nextLine();
            } catch (Exception e) {
                System.err.println("Error during traversal: " + e.getMessage());
                e.printStackTrace();
            }
        } while (true);
    }

    public void traverseMenuScreen() {
        System.out.println("=====================================");
        System.out.println("*                                   *");
        System.out.println("*           Tree Traversal          *");
        System.out.println("*            1. InOrder             *");
        System.out.println("*            2. PreOrder            *");
        System.out.println("*            3. PostOrder           *");
        System.out.println("*            4. Back                *");
        System.out.println("*                                   *");
        System.out.println("=====================================");
    }

    private void preOrderTraversal(Node root) {
        if (root == null) {
            System.out.println("\nThe tree is empty. Cannot perform traversal.");
            return;
        }
        System.out.print("Preorder traversal: ");
        preorderMethod(root);
        System.out.println();
    }

    private static void preorderMethod(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorderMethod(root.left);
            preorderMethod(root.right);
        }
    }

    private void inorderTraversal(Node root) {
        if (root == null) {
            System.out.println("\nThe tree is empty. Cannot perform traversal.");
            return;
        }
        System.out.print("Inorder traversal: ");
        inorderMethod(root);
        System.out.println();
    }

    private static void inorderMethod(Node root) {
        if (root != null) {
            inorderMethod(root.left);
            System.out.print(root.value + " ");
            inorderMethod(root.right);
        }
    }

    private void postOrderTraversal(Node root) {
        if (root == null) {
            System.out.println("\nThe tree is empty. Cannot perform traversal.");
            return;
        }
        System.out.print("Postorder traversal: ");
        postOrderMethod(root);
        System.out.println();
    }

    private static void postOrderMethod(Node root) {
        if (root != null) {
            postOrderMethod(root.left);
            postOrderMethod(root.right);
            System.out.print(root.value + " ");
        }
    }

    public void clearScreen(boolean autoProceed) {
        try {
            if (!autoProceed) {
                System.out.println("Enter to Continue");
                filler = sc.nextLine();
            }
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Error clearing screen: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class Node {
    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    void insert(int value) {
        root = insertRec(root, value);
    }

    void delete(int value){
        root = DelRecords(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    boolean contains(int value){
        return ConRecords(root, value);
    }

    private boolean ConRecords(Node root, int value){

        if (root == null){
            return false;
        }
        if (value == root.value){
            return true;
        } else if (value < root.value){
            return ConRecords(root.left, value);
        } else {
            return ConRecords(root.right, value);
        }
    }

    private Node DelRecords (Node root, int value){

        if (root == null){
        return null;
        }
        if (value < root.value){
            root.left = DelRecords (root.left, value);
        } else if (value > root.value){
            root.right = DelRecords (root.right, value);
        } else {
            if (root.left == null){
                return root.right;
            } else if (root.right == null) {
                return root.left;
        }

        root.value = FindMin(root.right);
        root.right = DelRecords(root.right, root.value);
    }
        return root;
    }

    private int FindMin(Node root){

        int MinValue = root.value;

        while (root.left != null) {
            root = root.left;
            MinValue = root.value;
        }

        return MinValue;
    }

    void drawTree(Graphics g, Node root, int x, int y, int xOffset, int yOffset) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            int childX = x - xOffset;
            int childY = y + yOffset;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, childX, childY);
            drawTree(g, root.left, childX, childY, xOffset / 2, yOffset);
        }

        if (root.right != null) {
            int childX = x + xOffset;
            int childY = y + yOffset;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, childX, childY);
            drawTree(g, root.right, childX, childY, xOffset / 2, yOffset);
        }

        g.setColor(Color.BLUE);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(root.value), x - 7, y + 5);
    }
}

class TreePanel extends JPanel {
    private final BinarySearchTree bst;

    TreePanel(BinarySearchTree bst) {
        this.bst = bst;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        if (bst.root != null) {
            bst.drawTree(g, bst.root, getWidth() / 2, 50, getWidth() / 4, 50);
        }
    }
}
