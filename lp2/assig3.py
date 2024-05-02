def dijkstra(graph, start):
    visited = set()
    distances = {node: float('infinity') for node in graph}
    distances[start] = 0

    while len(visited) < len(graph):
        min_distance = float('infinity')
        min_node = None

        for node in graph:
            if node not in visited and distances[node] < min_distance:
                min_distance = distances[node]
                min_node = node

        if min_node is None:
            break

        visited.add(min_node)

        for neighbor, weight in graph[min_node]:
            distance = distances[min_node] + weight
            if distance < distances[neighbor]:
                distances[neighbor] = distance

    return distances

# Example usage:
graph = {
    'A': [('B', 10), ('C', 6), ('D', 5)],
    'B': [('D', 15)],
    'C': [('D', 4),('B',2)],
    'D': [],
    'E': [('F', 8)],
    'F': []
}

start_node = 'A'
distances = dijkstra(graph, start_node)
for node, distance in distances.items():
    print(f"Shortest distance from {start_node} to {node}: {distance}")
