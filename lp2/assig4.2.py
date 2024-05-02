def is_safe(board, row, col):
    # Check if no queen can attack this position
    for i in range(row):
        if board[i] == col or board[i] - i == col - row or board[i] + i == col + row:
            return False
    return True



def solve_n_queens_util(board, row, n):
    if row == n:
        return True

    for col in range(n):
        if is_safe(board, row, col):
            board[row] = col
            if solve_n_queens_util(board, row + 1, n):
                return True
            board[row] = -1  # Backtrack if placing a queen doesn't lead to a solution

    return False

def print_board(board):
    n = len(board)
    for i in range(n):
        row = ["Q" if board[i] == j else "." for j in range(n)]
        print(" ".join(row))
    print()

def solve_n_queens_backtracking(n):
    board = [-1] * n  # Initialize the board with no queens placed
    if solve_n_queens_util(board, 0, n):
        print_board(board)
    else:
        print("No solution found for {} queens.".format(n))

if __name__ == "__main__":
    print("Enter the number of queens (recommended less than 10 for better performance):")
    N = int(input())
    solve_n_queens_backtracking(N)
