package laboratory6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Face {
    private static int counter;
    private final int id;
    private final String name;
    private final double x;
    private final double y;

    private Face(double x, double y) {
        this.id = counter;
        this.name = "F" + counter;
        this.x = x;
        this.y = y;
        counter++;
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

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public static ArrayList<Face> initializeFaceList(ArrayList<Edge> edgeList, ArrayList<Vertex> vertexList) {
        setCounterZero();
        ArrayList<Face> list = new ArrayList<>();

        vertexList.sort(Comparator.comparing(Vertex::getY));
        list.add(new Face(-5,
                vertexList.get(0).getY() - 1));

        for (int i = 1; i < edgeList.size(); i++) {
            Set<Vertex> hashSet = new HashSet<>();
            for (Edge edge : edgeList) {
                if (edge.getFaceOne() == i) {
                    hashSet.add(edge.getVertexOne());
                    hashSet.add(edge.getVertexTwo());
                }
                if (edge.getFaceTwo() == i) {
                    hashSet.add(edge.getVertexOne());
                    hashSet.add(edge.getVertexTwo());
                }
            }
            ArrayList<Vertex> temp = new ArrayList<>(hashSet);
            double tempX = 0;
            double tempY = 0;
            for (Vertex vertex : temp) {
                tempX += vertex.getX();
                tempY += vertex.getY();
            }
            tempX = tempX / temp.size();
            tempY = tempY / temp.size();
            if (temp.size() == 0) {
                break;

            }
            list.add(new Face(tempX, tempY));
        }
        return list;
    }

    @Override
    public String toString() {
        return name + " id:" + id + " x:" + x + " y:" + y + "\n";
    }
}
