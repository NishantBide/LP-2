import java.util.Scanner; 
public class NQueens 
{ 
    private static int N; 
    public static void printChessboard(int[][] board) 
    { 
        for (int i = 0; i < N; i++) 
        { 
            for (int j = 0; j < N; j++) 
            { 
                if (board[i][j] == 1) 
                    System.out.print(" Q ");    
                else 
                    System.out.print(" - "); 
            } 
            System.out.println(); 
        } 
    } 
    public static boolean isSafe(int[][] board, int row, int col) 
    { 
        for (int i = 0; i < col; i++) 
        { 
            if (board[row][i] == 1) 
                return false; 
        } 
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) 
        { 
            if (board[i][j] == 1) 
                return false; 
        } 
        for (int i = row, j = col; j >= 0 && i < N; i++, j--) 
        { 
            if (board[i][j] == 1) 
                return false; 
        }
        return true; 
    } 
    public static boolean solveNQueensUtil(int[][] board, int col) 
    { 
        if (col >= N) 
        return true; 
        for (int i = 0;i < N; i++) 
        { 
            if (isSafe(board, i, col)) 
            { 
                board[i][col] = 1; 
                if (solveNQueensUtil(board, col + 1)) 
                    return true; 
                board[i][col] = 0; 
            } 
        } 
        return false; 
    } 
    public static void solveNQueens(int n) 
    { 
        N = n; 
        int[][] board = new int[N][N]; 
        if (!solveNQueensUtil(board,0)) 
        { 
            System.out.println("Solution does not exist"); 
            return; 
        } 
        printChessboard(board); 
    } 
    public static void main(String[] args) 
    { 
        Scanner scanner = new Scanner(System.in); 
        System.out.print("Enter the size of the chessboard (N): "); int n = scanner.nextInt(); 
        solveNQueens(n); 
        scanner.close(); 
    } 
}
