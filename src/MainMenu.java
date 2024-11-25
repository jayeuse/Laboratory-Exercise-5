import java.util.Scanner;

public class MainMenu {
    Scanner sc = new Scanner(System.in);
    Operations treeOperations = new Operations();

    String filler;

    public void menuScreen(){
        System.out.println("=====================================");
        System.out.println("*                                   *");
        System.out.println("*   Binary Search Tree Operations   *");
        System.out.println("*          S. Show Tree             *");
        System.out.println("*          I. Insert Node           *");
        System.out.println("*          D. Delete Node           *");
        System.out.println("*          T. Traverse Node         *");
        System.out.println("*          Q. Quit                  *");
        System.out.println("*                                   *");
        System.out.println("=====================================");
    }

    public void processMenu(){
        do {

            clearScreen(true);
            menuScreen();
            System.out.print("Enter choice: ");

            char choice = sc.nextLine().charAt(0);
            boolean isRunning = true;
            switch (choice) {
                case 'S':
                case 's':
                    System.out.println("*************************************");
                    treeOperations.showTree();
                    System.out.println("*************************************");
                    clearScreen(false);
                    break;

                case 'I':
                case 'i':
                    do {
                        System.out.println("*************************************");
                        treeOperations.insertNode();
                        System.out.println("*************************************");
                        String tryAgain;

                        while (true) {
                            System.out.print("Try Again? (Y/N): ");
                            tryAgain = sc.nextLine().trim();

                            if (tryAgain.equalsIgnoreCase("y") || tryAgain.equalsIgnoreCase("n")) {
                                clearScreen(false);
                                menuScreen();
                                break;

                            } else {
                                System.out.println("Invalid input. Please enter 'Y' or 'N'.");

                            }
                        }
                        if (tryAgain.equalsIgnoreCase("n")) {
                            isRunning = false;

                        }
                    } while (isRunning);
                    break;

                case 'D':
                case 'd':
                    do {
                        System.out.println("*************************************");
                        treeOperations.deleteNode();
                        System.out.println("*************************************");
                        String tryAgain;

                        while (true) {
                            System.out.print("Try Again? (Y/N): ");
                            tryAgain = sc.nextLine().trim();

                            if (tryAgain.equalsIgnoreCase("y") || tryAgain.equalsIgnoreCase("n")) {
                                clearScreen(false);
                                menuScreen();
                                break;

                            } else {
                                System.out.println("Invalid input. Please enter 'Y' or 'N'.");

                            }
                        }
                        if (tryAgain.equalsIgnoreCase("n")) {
                            isRunning = false;
                        }
                    } while (isRunning);
                    break;

                case 'T':
                case 't':
                    System.out.println("*************************************");
                    treeOperations.traverseNode();
                    System.out.println("*************************************");
                    clearScreen(false);
                    break;

                case 'Q':
                case 'q':
                    System.out.println("*************************************");
                    System.out.println("Goodbye!");
                    System.out.println("*************************************");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice");
                    clearScreen(false);

            }
        } while (true);
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