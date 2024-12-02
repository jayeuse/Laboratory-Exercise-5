import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

public class Operations {
    Scanner sc = new Scanner(System.in);
    BinarySearchTree displayTree = new BinarySearchTree();

    String filler;

    public void showTree() {
        try {
            System.out.println("Show the Tree");
            // An array list of sample values to be inserted into the tree

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
        } catch (Exception e) {
            System.err.println("Error displaying tree: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertNode() {
        try {
            System.out.println("Insert Node");
            System.out.print("Enter value to insert: ");
            int value = sc.nextInt();
            displayTree.insert(value);
            System.out.println("Node inserted successfully.");
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a valid integer.");
            sc.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.err.println("Error inserting node: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteNode() {
        try {
            System.out.println("Delete Node");
            // Implement delete functionality here
        } catch (Exception e) {
            System.err.println("Error deleting node: " + e.getMessage());
            e.printStackTrace();
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
                System.out.print("Enter choice: ");
                char choice = sc.nextLine().charAt(0);
                switch (choice) {
                    case '1':
                        System.out.println("*************************************");
                        inorderTraversal();
                        System.out.println("*************************************");
                        clearScreen(false);
                        break;
                    case '2':
                        System.out.println("*************************************");
                        preOrderTraversal();
                        System.out.println("*************************************");
                        clearScreen(false);
                        break;
                    case '3':
                        System.out.println("*************************************");
                        postOrderTraversal();
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

    public void preOrderTraversal() {
        try {
            if (displayTree.root == null) {
                System.out.println("------------------------");
                System.out.println("THE TREE IS EMPTY");
                System.out.println("------------------------\n");
                return;
            }

            System.out.print("\nPreOrder Traversal: ");
            preOrderMethod(displayTree.root);
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            System.err.println("Error during pre-order traversal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void preOrderMethod(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");
        preOrderMethod(node.left);
        preOrderMethod(node.right);
    }

    public void inorderTraversal() {
        try {
            if (displayTree.root == null) {
                System.out.println("------------------------");
                System.out.println("THE TREE IS EMPTY");
                System.out.println("------------------------\n");
                return;
            }

            System.out.print("\nInOrder Traversal: ");
            inOrderMethod(displayTree.root);
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            System.err.println("Error during in-order traversal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void inOrderMethod(Node node) {
        if (node != null) {
            inOrderMethod(node.left);
            System.out.print(node.value + " ");
            inOrderMethod(node.right);
        }
    }

    public void postOrderTraversal() {
        try {
            if (displayTree.root == null) {
                System.out.println("------------------------");
                System.out.println("THE TREE IS EMPTY");
                System.out.println("------------------------\n");
                return;
            }

            System.out.print("\nPostOrder Traversal: ");
            postOrderMethod(displayTree.root);
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            System.err.println("Error during post-order traversal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void postOrderMethod(Node node) {
        if (node == null) {
            return;
        }

        postOrderMethod(node.left);
        postOrderMethod(node.right);
        System.out.print(node.value + " ");
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