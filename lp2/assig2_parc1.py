import heapq

class Node:
    def __init__(self,state,cost,heuristic):
        self.state=state
        self.cost=cost
        self.heuristic=heuristic
    def __lt__(self,other):
        return (self.cost + self.heuristic) < (other.cost + other.heuristic)
    
def is_safe(state,row,col):
    for r, c in enumerate(state[:row]):
        if c==col or abs(c-col)==abs(r-row):
            return False
    return True

def attacking_pair(state):
    pair=0
    n= len(state)
    for i in range (n):
        for j in range(i+1,n):
            if state[i]==state[j] or abs(state[i]-state[j])== abs(i-j):
                pair+=1

    return pair

def astar(n):
    heap=[Node([],0,0)]
    heapq.heapify(heap)

    while(heap):
        node=heapq.heappop(heap)
        state,cost,heuristic=node.state,node.cost,node.heuristic

        if len(state)==n:
            return state
        
        for col in range(n):
            if is_safe(state, len(state),col):
                new_state= state + [col]
                new_cost=cost+1
                new_heuristic=attacking_pair(new_state)
                heapq.heappush(heap, Node(new_state,new_cost,new_heuristic))
    return None

def display(state):
    n=len(state)
    
    for i in range(n):
        line=['.']*n
        line[state[i]]='Q'
        print(' '.join(line))

if __name__=='__main__':
    print("Enter number of queens:")
    n=int(input())
    solution=astar(n)
    if solution:
        display(solution)
    else:
        print("No solution exist")
