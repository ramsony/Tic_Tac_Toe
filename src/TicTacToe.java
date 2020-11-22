import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char[][] input = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, };



        printGameBoard(input);
        while (true) {
            System.out.println("Enter the coordinates:");
            String p1 = sc.nextLine();

            while (isNotANum(p1)) {
                p1 = sc.nextLine();
            }
            while (numIsGreaterThan3(p1)) {
                p1 = sc.nextLine();
            }

            while (p1Pos.contains(p1) || p2Pos.contains(p1)) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.println("Enter the coordinates:");
                p1 = sc.nextLine();
                while (isNotANum(p1)) {
                    p1 = sc.nextLine();
                }
                while (numIsGreaterThan3(p1)) {
                    p1 = sc.nextLine();
                }

            }
            setGameBoard(input, p1, "player one");
            printGameBoard(input);

            String result = checkWinner();

            if (result.length() > 0) {
                System.out.println(result);
                break;
            }


            System.out.println("Enter the coordinates:");
            String p2 = sc.nextLine();

            while (isNotANum(p2)) {
                p2 = sc.nextLine();
            }

            while (numIsGreaterThan3(p2)) {
                p2 = sc.nextLine();
            }

            while (p2Pos.contains(p2) || p1Pos.contains(p2)) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.println("Enter the coordinates:");
                p2 = sc.nextLine();
                while (isNotANum(p2)) {
                    p2 = sc.nextLine();
                }
                while (numIsGreaterThan3(p2)) {
                    p2 = sc.nextLine();
                }
            }
            setGameBoard(input, p2, "player two");
            printGameBoard(input);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }

    }

    public static ArrayList<String> p1Pos = new ArrayList<>();
    public static ArrayList<String> p2Pos = new ArrayList<>();

    public static void setGameBoard(char[][] input, String pos, String player) {

        char symbol = ' ';
        if (player.equals("player one")) {
            symbol = 'X';
            p1Pos.add(pos);
        } else if (player.equals("player two")) {
            symbol = 'O';
            p2Pos.add(pos);
        }

        switch (pos) {
            case "1 3":
                input[0][0] = symbol;
                break;
            case "2 3":
                input[0][1] = symbol;
                break;
            case "3 3":
                input[0][2] = symbol;
                break;
            case "1 2":
                input[1][0] = symbol;
                break;
            case "2 2":
                input[1][1] = symbol;
                break;
            case "3 2":
                input[1][2] = symbol;
                break;
            case "1 1":
                input[2][0] = symbol;
                break;
            case "2 1":
                input[2][1] = symbol;
                break;
            case "3 1":
                input[2][2] = symbol;
                break;
            default:
                break;

        }
    }

    public static void printGameBoard(char[][] input) {
        System.out.println("--------");
        for (int i = 0; i < input.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < input.length; j++) {
                System.out.print(input[i][j] +" ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("--------");
    }

    public static boolean isNotANum(String s) {
        char[] cha = s.toLowerCase().toCharArray();
        for (int i = 0; i < cha.length; i++) {
            char ch = cha[i];
            if (ch >= 'a' && ch <= 'z') {
                System.out.println("You should enter numbers!");
                System.out.println("Enter the coordinates:");
                return true;
            }
        }
        return false;
    }
    public static boolean numIsGreaterThan3(String s) {
        int a = Integer.parseInt(s.substring(0,1));
        int b = Integer.parseInt(s.substring(2));
        if (a > 3 || b > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            System.out.println("Enter the coordinates:");
            return true;
        }
        return  false;
    }

    public static String checkWinner() {
        List topRow = Arrays.asList("1 3", "2 3", "3 3");
        List midRow = Arrays.asList("1 2", "2 2", "3 2");
        List botRow = Arrays.asList("1 1", "2 1", "3 1");
        List leftCol = Arrays.asList("1 3", "1 2", "1 1");
        List midCol = Arrays.asList("2 3", "2 2", "2 1");
        List rightCol = Arrays.asList("3 3", "3 2", "3 1");
        List diagn1 = Arrays.asList("1 3", "2 2", "3 1");
        List diagn2 = Arrays.asList("3 3", "2 2", "1 1");

//        (1, 3) (2, 3) (3, 3)
//        (1, 2) (2, 2) (3, 2)
//        (1, 1)  (2, 1)  (3, 1)
        List<List> winningCondition = new ArrayList<List>();
        winningCondition.add(topRow);
        winningCondition.add(midRow);
        winningCondition.add(botRow);
        winningCondition.add(leftCol);
        winningCondition.add(midCol);
        winningCondition.add(rightCol);
        winningCondition.add(diagn1);
        winningCondition.add(diagn2);

        for ( List l : winningCondition) {
            if (p1Pos.containsAll(l)) {
                return "Player 1 win";
            } else if (p2Pos.containsAll(l)) {
                return "Player 2 win";
            } else if (p1Pos.size() + p2Pos.size() == 9 && p1Pos.containsAll(l)) {
                return "Player 1 win";
            } else if (p1Pos.size() + p2Pos.size() == 9 && p1Pos.containsAll(l)) {
                return "Player 2 win";
            } else if (p1Pos.size() + p2Pos.size() == 9) {
                return "Draw";
            }
        }
        return "";

    }
}
