def is_safe(state, row, col):
    for r, c in enumerate(state[:row]):
        if c == col or abs(r - row) == abs(c - col):
            return False
    return True

def solve_n_queens_backtracking(n):
    solutions = []

    def backtrack(state, row):
        if row == n:
            solutions.append(state[:])
            return

        for col in range(n):
            if is_safe(state, row, col):
                state.append(col)
                backtrack(state, row + 1)
                state.pop()

    backtrack([], 0)
    return solutions

def print_board(state):
    n = len(state)
    for row in range(n):
        line = ['.'] * n
        line[state[row]] = 'Q'
        print(' '.join(line))

if __name__ == '__main__':
    print("Enter the number of queens:")
    N = int(input())
    solutions = solve_n_queens_backtracking(N)
    if solutions:
        for idx, solution in enumerate(solutions, 1):
            print(f"Solution {idx}:")
            print_board(solution)
            print("***************Chess Board*******************")
    else:
        print("No solution found for {} queens.".format(N))
