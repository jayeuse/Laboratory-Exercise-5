import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

public class Operations {
    Scanner sc = new Scanner(System.in);
    BinarySearchTree displayTree = new BinarySearchTree();

    String filler;

    public void showTree() {
        System.out.println("Show the Tree");
        // Sample values (This should be replaced with the values from the user
        int[] values = {50, 45, 37, 32, 20, 35, 47,
                55, 51, 53, 64, 60, 68};
        for (int value : values) {
            displayTree.insert(value);
        }

        // Create a GUI window
        JFrame frame = new JFrame("Binary Search Tree Visualization");
        TreePanel panel = new TreePanel(displayTree);

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void insertNode() {
        System.out.println("Insert Node");
    }

    public void deleteNode() {
        System.out.println("Delete Node");
    }

    public void traverseNode() {
        System.out.println("Traverse Node");
        do {

            try{
                clearScreen(true);
                traverseMenuScreen();
                System.out.print("Enter choice: ");
                char choice = sc.nextLine().charAt(0);
                switch (choice) {
                    case '1':
                        System.out.println("*************************************");
                        traverseInOrder();
                        System.out.println("*************************************");
                        clearScreen(false);
                        break;
                    case '2':
                        System.out.println("*************************************");
                        traversePreOrder();
                        System.out.println("*************************************");
                        clearScreen(false);
                        break;
                    case '3':
                        System.out.println("*************************************");
                        traversePostOrder();
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
                System.out.println("Invalid input. Please enter a number.");
                clearScreen(false);
                sc.nextLine();
            }

        } while (true);
    }

    public void traverseMenuScreen(){
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

    public void traversePreOrder() {
        System.out.println("Traverse Pre Order");
    }

    public void traverseInOrder() {
        System.out.println("Traverse In Order");
    }

    public void traversePostOrder() {
        System.out.println("Traverse Post Order");
    }

    public void clearScreen(boolean autoProceed){

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

    // Insert a value into the BST
    void insert(int value) {
        root = insertRec(root, value);
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

    // Draw the tree with improved formatting
    void drawTree(Graphics g, Node root, int x, int y, int xOffset, int yOffset) {
        if (root == null) {
            return;
        }

        // Draw left subtree
        if (root.left != null) {
            int childX = x - xOffset;
            int childY = y + yOffset;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, childX, childY); // Line to left child
            drawTree(g, root.left, childX, childY, xOffset / 2, yOffset); // Recursively draw left subtree
        }

        // Draw right subtree
        if (root.right != null) {
            int childX = x + xOffset;
            int childY = y + yOffset;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, childX, childY); // Line to right child
            drawTree(g, root.right, childX, childY, xOffset / 2, yOffset); // Recursively draw right subtree
        }

        // Draw current node
        g.setColor(Color.BLUE);
        g.fillOval(x - 15, y - 15, 30, 30); // Draw the node circle
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(root.value), x - 7, y + 5); // Draw the node's value inside the circle
    }
    
}

// Panel to display the tree
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