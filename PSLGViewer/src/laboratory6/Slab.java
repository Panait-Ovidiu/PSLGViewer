package laboratory6;

import java.util.ArrayList;

class Slab {

    private static int counter;
    private final int id;
    private final String name;
    private final Vertex vertex;
    private final double maxY;
    private final ArrayList<Edge> listA;
    private final ArrayList<Edge> listB;
    private ArrayList<Edge> edgeList;

    private Slab(ArrayList<Edge> edgeList, Vertex vertex, double maxY) {
        this.id = counter;
        counter++;
        this.name = "S" + counter;
        this.vertex = vertex;
        this.maxY = maxY;
        this.listA = initializeListA(edgeList, vertex);
        this.listB = initializeListB(edgeList, vertex);
        this.edgeList = new ArrayList<>();
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

    public Vertex getVertex() {
        return this.vertex;
    }

    public double getMaxY() {
        return this.maxY;
    }

    private ArrayList<Edge> getListA() {
        return this.listA;
    }

    private ArrayList<Edge> getListB() {
        return this.listB;
    }

    public ArrayList<Edge> getEdgeList() {
        return this.edgeList;
    }

    private void setEdgeList(ArrayList<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    private void addToEdgeList(Edge edge) {
        this.edgeList.add(edge);
    }

    private static ArrayList<Edge> initializeListA(ArrayList<Edge> edgeList, Vertex vertex) {
        ArrayList<Edge> listA = new ArrayList<>();
        for (Edge anEdgeList : edgeList) {
            if (Vertex.compareVertices(anEdgeList.getVertexTwo(), vertex)) {
                listA.add(anEdgeList);
            }
        }
        listA.sort((Edge one, Edge two) ->
                determinant(one.getVertexOne(), one.getVertexTwo(), two.getVertexOne()) -
                        determinant(two.getVertexOne(), one.getVertexTwo(), one.getVertexOne()));
        return listA;
    }

    private static ArrayList<Edge> initializeListB(ArrayList<Edge> edgeList, Vertex vertex) {
        ArrayList<Edge> listB = new ArrayList<>();
        for (Edge anEdgeList : edgeList) {
            if (Vertex.compareVertices(anEdgeList.getVertexOne(), vertex)) {
                listB.add(anEdgeList);
            }
        }
        listB.sort((Edge one, Edge two) ->
                determinant(two.getVertexTwo(), two.getVertexOne(), one.getVertexTwo()) -
                        determinant(one.getVertexTwo(), one.getVertexOne(), two.getVertexTwo()));

        return listB;
    }

    public static ArrayList<Slab> initializeEdgeList(ArrayList<Slab> slabList) {
        for (int i = 0; i < slabList.size(); i++) {
            if (i == 0) {
                slabList.get(i).setEdgeList(slabList.get(i).listB);
            } else {
                slabList.get(i).makeSlabEdgeList(slabList.get(i - 1).getEdgeList(), slabList.get(i).listA, slabList.get(i).listB);
            }
        }
        return slabList;
    }

    private void makeSlabEdgeList(ArrayList<Edge> previousList, ArrayList<Edge> compareList, ArrayList<Edge> addList) {
        int i = 0;
        int j = 0;

        while (i < compareList.size() && j < previousList.size()) {
            if (previousList.get(j) == compareList.get(i)) {
                j++;
                i++;
            } else {
                addToEdgeList(previousList.get(j));
                j++;
            }
        }
        edgeList.addAll(addList);
        while (j < previousList.size()) {
            addToEdgeList(previousList.get(j));
            j++;
        }
    }

    public static int determinant(Vertex one, Vertex origin, Vertex two) {
        double determinant = one.getX() * (origin.getY() - two.getY()) +
                origin.getX() * (two.getY() - one.getY()) +
                two.getX() * (one.getY() - origin.getY());
        return (int) determinant;
    }

    public static ArrayList<Slab> initializeSlabs(ArrayList<Edge> edgeList, ArrayList<Vertex> vertexList) {
        Slab.setCounterZero();
        ArrayList<Slab> slabList = new ArrayList<>();
        for (int i = 0; i < vertexList.size() - 1; i++) {
            slabList.add(new Slab(edgeList, vertexList.get(i), vertexList.get(i + 1).getY()));
        }
        return slabList;
    }

    public static int binarySearch(ArrayList<Slab> slabList, int leftPointer, int rightPointer, double y) {
        while (leftPointer <= rightPointer) {
            int middlePointer = leftPointer + (rightPointer - leftPointer) / 2;
            if (slabList.get(middlePointer).vertex.getY() <= y && slabList.get(middlePointer).getMaxY() > y) {
                return middlePointer;
            }
            if (slabList.get(middlePointer).getMaxY() <= y) {
                leftPointer = middlePointer + 1;
            } else {
                rightPointer = middlePointer - 1;
            }
        }
        return -3;
    }

    @Override
    public String toString() {
        return name + " id:" + id + " vertex:" + vertex.getName() + " y:" + vertex.getY() +
                " maxY:" + maxY +
                " List A:" + listToString(listA) +
                " ListB:" + listToString(listB) +
                " Edge List: " + listToString(edgeList) + "\n";
    }

    public static String display(Slab slab) {
        return "" + slab.getName() +
                "\nY: " + slab.getVertex().getY() + "  MaxY: " + slab.getMaxY() +
                "\nVertex: " + slab.getVertex().getName() + "  X: " + slab.getVertex().getX() + "  Y: " + slab.getVertex().getY() +
                "\nListA: " + Slab.listToString(slab.getListA()) +
                "\nListB: " + Slab.listToString(slab.getListB()) +
                "\nEdgeList: " + Slab.listToString(slab.getEdgeList());
    }

    private static String listToString(ArrayList<Edge> list) {
        StringBuilder message = new StringBuilder("");
        try {
            if (list.size() != 0) {
                for (Edge aList : list) {
                    message.append(aList.getName()).append(" ");
                }
            } else {
                message.append("Empty");
            }
        } catch (Exception e) {
            // System.out.print(" NULL");
        }
        return message.toString();
    }
}