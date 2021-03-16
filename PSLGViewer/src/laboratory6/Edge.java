package laboratory6;

import java.util.ArrayList;

public class Edge {

    private static int counter;
    private final int id;
    private final String name;
    private Vertex vertexOne, vertexTwo;
    private int faceOne, faceTwo, edgeOne, edgeTwo;

    private Edge(Vertex vertexOne, Vertex vertexTwo, int faceOne, int faceTwo, int edgeOne, int edgeTwo) {
        this.id = counter;
        counter++;
        this.name = "E" + counter;
        this.vertexOne = vertexOne;
        this.vertexTwo = vertexTwo;
        this.faceOne = faceOne;
        this.faceTwo = faceTwo;
        this.edgeOne = edgeOne;
        this.edgeTwo = edgeTwo;
    }

    private static void setCounterZero() {
        counter = 0;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Vertex getVertexOne() {
        return this.vertexOne;
    }

    public Vertex getVertexTwo() {
        return this.vertexTwo;
    }

    public int getFaceOne() {
        return this.faceOne;
    }

    public int getFaceTwo() {
        return this.faceTwo;
    }

    public static void organizeEdgeVertexes(ArrayList<Edge> list) {
        Vertex temp;
        for (Edge edge : list) {
            if (edge.vertexOne.getId() > edge.vertexTwo.getId()) {
                temp = edge.vertexOne;
                edge.vertexOne = edge.vertexTwo;
                edge.vertexTwo = temp;

                edge.faceOne = edge.faceOne + edge.faceTwo;
                edge.faceTwo = edge.faceOne - edge.faceTwo;
                edge.faceOne = edge.faceOne - edge.faceTwo;

                edge.edgeOne = edge.edgeOne + edge.edgeTwo;
                edge.edgeTwo = edge.edgeOne - edge.edgeTwo;
                edge.edgeOne = edge.edgeOne - edge.edgeTwo;
            }
        }
    }

    public static ArrayList<Edge> initializeEdgeList(ArrayList<Vertex> vertexList) {
        setCounterZero();
        ArrayList<Edge> list = new ArrayList<>();
        list.add(new Edge(vertexList.get(10), vertexList.get(3), 1, 0, 13, 3));
        list.add(new Edge(vertexList.get(11), vertexList.get(1), 2, 5, 16, 15));
        list.add(new Edge(vertexList.get(8), vertexList.get(3), 0, 1, 4, 1));
        list.add(new Edge(vertexList.get(8), vertexList.get(9), 4, 0, 17, 12));
        list.add(new Edge(vertexList.get(9), vertexList.get(7), 4, 7, 4, 14));
        list.add(new Edge(vertexList.get(4), vertexList.get(10), 3, 2, 8, 7));
        list.add(new Edge(vertexList.get(5), vertexList.get(10), 2, 0, 18, 1));
        list.add(new Edge(vertexList.get(4), vertexList.get(6), 6, 3, 9, 14));
        list.add(new Edge(vertexList.get(2), vertexList.get(4), 6, 5, 10, 16));
        list.add(new Edge(vertexList.get(6), vertexList.get(2), 6, 0, 8, 15));
        list.add(new Edge(vertexList.get(0), vertexList.get(7), 3, 4, 13, 5));
        list.add(new Edge(vertexList.get(6), vertexList.get(9), 0, 7, 10, 5));
        list.add(new Edge(vertexList.get(0), vertexList.get(10), 1, 3, 17, 6));
        list.add(new Edge(vertexList.get(6), vertexList.get(7), 7, 3, 12, 11));
        list.add(new Edge(vertexList.get(1), vertexList.get(2), 0, 5, 18, 9));
        list.add(new Edge(vertexList.get(4), vertexList.get(11), 2, 5, 6, 2));
        list.add(new Edge(vertexList.get(0), vertexList.get(8), 4, 1, 11, 3));
        list.add(new Edge(vertexList.get(1), vertexList.get(5), 2, 0, 2, 7));

        return list;
    }

    public static String display(Edge edge) {
        return "" + edge.getName() +
                "\nVertices: " + edge.getVertexOne().getName() + "," + edge.vertexTwo.getName() +
                "\nFaces: " + edge.faceOne + "," + edge.faceTwo +
                "\nEdges: " + edge.edgeOne + "," + edge.edgeTwo;
    }

    @Override
    public String toString() {
        return name + " id:" + id + " Vertexes:" + getVertexOne().getName() + "," + vertexTwo.getName() +
                " Faces:" + faceOne + "," + faceTwo + " Edges:" + edgeOne + "," + edgeTwo + "\n";
    }
}