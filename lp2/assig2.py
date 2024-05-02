import heapq

class Node:
    def __init__(self, state, cost, heuristic):
        self.state = state
        self.cost = cost
        self.heuristic = heuristic

    def __lt__(self, other):
        return (self.cost + self.heuristic) < (other.cost + other.heuristic)

def is_safe(state, row, col):
    for r, c in enumerate(state[:row]):
        if c == col or abs(r - row) == abs(c - col):
            return False
    return True

def attacking_pairs(state):
    pairs = 0
    n = len(state)
    for i in range(n):
        for j in range(i + 1, n):
            if state[i] == state[j] or abs(state[i] - state[j]) == abs(i - j):
                pairs += 1
    return pairs

def solve_n_queens_astar(n):
    heap = [Node([], 0, 0)]
    heapq.heapify(heap)

    while heap:
        node = heapq.heappop(heap)
        state, cost, heuristic = node.state, node.cost, node.heuristic

        if len(state) == n:
            return state

        for col in range(n):
            if is_safe(state, len(state), col):
                new_state = state + [col]
                new_cost = cost + 1
                new_heuristic = attacking_pairs(new_state)
                heapq.heappush(heap, Node(new_state, new_cost, new_heuristic))

    return None

def print_board_step(state):
    n = len(state)
    for row in range(n):
        line = ['.'] * n
        line[state[row]] = 'Q'
        print(row+1," Queen is placed at ",row," row and ",state[row]," column.")
        print(' '.join(line))

def print_board(state):
    n = len(state)
    for row in range(n):
        line = ['.'] * n
        line[state[row]] = 'Q'
        print(' '.join(line))

if __name__ == '__main__':
    print("Enter the number of queens:")
    N = int(input())
    solution = solve_n_queens_astar(N)
    if solution:
        print_board_step(solution)
        print("***************Chess Board*******************")
        print_board(solution)
    else:
        print("No solution found for {} queens.".format(N))
