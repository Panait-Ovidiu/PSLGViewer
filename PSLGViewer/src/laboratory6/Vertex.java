package laboratory6;

import java.util.ArrayList;
import java.util.Comparator;

public class Vertex {

    private static int counter;
    private int id;
    private String name;
    private final double x;
    private final double y;

    private Vertex(double x, double y) {
        this.id = counter;
        counter++;
        this.name = "V" + counter;
        this.x = x;
        this.y = y;
    }

    public Vertex(double x, double y, String name) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    private static void setCounterZero() {
        counter = 0;
    }

    public int getId() {
        return this.id;
    }

    private void setId(int newId) {
        this.id = newId;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String newName) {
        this.name = newName;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    private static Vertex[] organize(Vertex[] vertexList) {
        Vertex[] temp = new Vertex[vertexList.length];
        int index;
        for (int i = 0; i < vertexList.length; i++) {
            index = vertexList[i].getId();
            temp[index] = vertexList[i];
            temp[index].setId(i);
            temp[index].setName("V" + (i + 1));
        }

        return temp;
    }

    public static void organize(ArrayList<Vertex> list) {
        Vertex[] vertexList = new Vertex[list.size()];
        for (int i = 0; i < list.size(); i++) {
            vertexList[i] = list.get(i);
        }
        vertexList = Vertex.organize(vertexList);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, vertexList[i]);
        }
    }

    public static void sortVertex(ArrayList<Vertex> list) {
        list.sort(Comparator.comparing(vertex -> vertex.y));
    }

    public static boolean compareVertices(Vertex one, Vertex two) {
        return one.getX() == two.getX() && one.getY() == two.getY();
    }

    public static ArrayList<Vertex> initializeVertexList() {
        setCounterZero();
        ArrayList<Vertex> list = new ArrayList<>();
        list.add(new Vertex(-8, 1));
        list.add(new Vertex(8, 10));
        list.add(new Vertex(7, -6));
        list.add(new Vertex(-15, 5));
        list.add(new Vertex(2, 3));
        list.add(new Vertex(1, 12));
        list.add(new Vertex(5, -10));
        list.add(new Vertex(3, -5));
        list.add(new Vertex(-10, -4));
        list.add(new Vertex(1, -9));
        list.add(new Vertex(-3, 8));
        list.add(new Vertex(1, 7));

        return list;
    }

    @Override
    public String toString() {
        return name + " id:" + id + " x:" + x + " y:" + y + "\n";
    }
}