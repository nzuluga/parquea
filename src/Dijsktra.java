
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nzuluaga
 */
public class Dijsktra {
  
  private static final double EPS = 1e-6;

  // una clase edge para tener la dirección y el costo
  public static class Edge {
    double cost;
    int from, to;

    public Edge(int from, int to, double cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  // clase nodo para revisar los nodos que ya hemos revisado
  public static class Node {
    int id;
    double value;

    public Node(int id, double value) {
      this.id = id;
      this.value = value;
    }
  }

  private int n;
  private double[] dist;
  private Integer[] prev;
  private List<List<Edge>> graph;

  private Comparator<Node> comparator =
      new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
          if (Math.abs(node1.value - node2.value) < EPS) return 0;
          return (node1.value - node2.value) > 0 ? +1 : -1;
        }
      };

  
  public Dijsktra(int n) {
    this.n = n;
    createEmptyGraph();
  }

  public Dijsktra(int n, Comparator<Node> comparator) {
    this(n);
    if (comparator == null) throw new IllegalArgumentException("Comparator cannot be null");
    this.comparator = comparator;
  }

  //Funcion para agregar una arista
  public void addEdge(int from, int to, int cost) {
    graph.get(from).add(new Edge(from, to, cost));
  }

  //me permite retornar el grafo en forma de una grafo
  public List<List<Edge>> getGraph() {
    return graph;
  }

  //Es una función para revisar el camino que recorrio
  public List<Integer> reconstructPath(int start, int end) {
    if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
    if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");
    double dist = dijkstra(start, end);
    List<Integer> path = new ArrayList<>();
    if (dist == Double.POSITIVE_INFINITY) return path;
    for (Integer at = end; at != null; at = prev[at]) path.add(at);
    Collections.reverse(path);
    return path;
  }

  //En esta función se corre el algoritmo dijkstra en donde se coloca el nodo de inicio y fin
  //después se va visitando cada nodo revisando y se va colocando en una priority queue
  public double dijkstra(int start, int end) {
    // Maintain an array of the minimum distance to each node
    dist = new double[n];
    Arrays.fill(dist, Double.POSITIVE_INFINITY);
    dist[start] = 0;

    // Keep a priority queue of the next most promising node to visit.
    PriorityQueue<Node> pq = new PriorityQueue<>(2 * n, comparator);
    pq.offer(new Node(start, 0));

    // Array used to track which nodes have already been visited.
    boolean[] visited = new boolean[n];
    prev = new Integer[n];

    while (!pq.isEmpty()) {
      Node node = pq.poll();
      visited[node.id] = true;

      //Si la distancia es menor a la calculada, se pasa
      if (dist[node.id] < node.value) continue;

      List<Edge> edges = graph.get(node.id);
      for (int i = 0; i < edges.size(); i++) {
        Edge edge = edges.get(i);

        //Se tiene un vector en donde se revisa si ya fueron visitadas
        if (visited[edge.to]) continue;

        // Relax edge by updating minimum cost if applicable.
        double newDist = dist[edge.from] + edge.cost;
        if (newDist < dist[edge.to]) {
          prev[edge.to] = edge.from;
          dist[edge.to] = newDist;
          pq.offer(new Node(edge.to, dist[edge.to]));
        }
      }
      //Cuando ya se revisaron todos los nodos se devuelve la distanca
      if (node.id == end) return dist[end];
    }
    // se devuelve infinito cuando es muy grande
    return Double.POSITIVE_INFINITY;
  }

   //Constructor para el grafo
  private void createEmptyGraph() {
    graph = new ArrayList<>(n);
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
  }
}
