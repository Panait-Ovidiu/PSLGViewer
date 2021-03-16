package laboratory6;

import java.util.ArrayList;

class PSLG {

    private ArrayList<Vertex> vertexList = new ArrayList<>();
    private ArrayList<Edge> edgeList = new ArrayList<>();
    private ArrayList<Face> faceList = new ArrayList<>();
    private ArrayList<Slab> slabList = new ArrayList<>();
    private Vertex searchPoint;
    private int resultSlab;
    private Face resultFace;
    private Edge resultFirstEdge;
    private Edge resultSecondEdge;
    private static final int NOT_FOUND = -3;
    private static final int CASE_0 = 0;
    private static final int CASE_1 = 1;
    private static final int CASE_2 = 2;
    private static final int CASE_3 = 3;
    private static final int CASE_4 = 4;
    private static final int CASE_5 = 5;
    private int searchCase;

    /**
     * searchCase =
     * 0 - Found in Graph
     * 1 - Point < Slab 0
     * 2 - Point > Slab n
     * 3 - Point < Slab Edge 0
     * 4 - Point > Slab Edge n
     * 5 - Point = Slab Vertex
     */

    public PSLG() {
    }

    public static int getCase0() {
        return CASE_0;
    }

    public static int getCase1() {
        return CASE_1;
    }

    public static int getCase2() {
        return CASE_2;
    }

    public static int getCase3() {
        return CASE_3;
    }

    public static int getCase4() {
        return CASE_4;
    }

    public static int getCase5() {
        return CASE_5;
    }

    public ArrayList<Vertex> getVertexList() {
        return this.vertexList;
    }

    public ArrayList<Edge> getEdgeList() {
        return this.edgeList;
    }

    public ArrayList<Face> getFaceList() {
        return this.faceList;
    }

    public ArrayList<Slab> getSlabList() {
        return this.slabList;
    }

    public Vertex getSearchPoint() {
        return this.searchPoint;
    }

    public int getSearchCase() {
        return this.searchCase;
    }

    public int getResultSlab() {
        return this.resultSlab;
    }

    public Face getResultFace() {
        return this.resultFace;
    }

    public Edge getResultFirstEdge() {
        return this.resultFirstEdge;
    }

    public Edge getResultSecondEdge() {
        return this.resultSecondEdge;
    }

    public void stepOne(boolean show) {
        if (show) {
            System.out.println("Step 1 - VertexList initialization\n==================================");
        }
        vertexList = Vertex.initializeVertexList();
        if (show) {
            System.out.println(vertexList.toString());
        }
    }

    public void stepTwo(boolean show) {
        stepOne(show);
        if (show) {
            System.out.println("Step 2 - EdgeList and FaceList initialization\n================================");
        }
        edgeList = Edge.initializeEdgeList(vertexList);
        faceList = Face.initializeFaceList(edgeList, vertexList);
        if (show) {
            System.out.println(edgeList.toString());
            System.out.println(faceList.toString());
        }
    }

    public void stepThree(boolean show) {
        stepTwo(show);
        if (show) {
            System.out.println("Step 3 - Sort vertices by Y coordinates and rename them\n=======================================================");
        }
        Vertex.sortVertex(vertexList);
        Vertex.organize(vertexList);
        if (show) {
            System.out.println(vertexList.toString());
        }
    }

    public void stepFour(boolean show) {
        stepThree(show);
        if (show) {
            System.out.println("Step 4 - Reinitialize EdgeList with new VertexList\n==================================================");
        }
        edgeList = Edge.initializeEdgeList(vertexList);
        if (show) {
            System.out.println(edgeList.toString());
        }
    }

    public void stepFive(boolean show) {
        stepFour(show);
        if (show) {
            System.out.println("Step 5 - Edge reorientation\n===========================");
        }
        Edge.organizeEdgeVertexes(edgeList);
        if (show) {
            System.out.println(edgeList.toString());
        }
    }

    public void stepSix(boolean show) {
        stepFive(show);
        if (show) {
            System.out.println("Step 6 - Slabs initialization\n=============================");
        }
        Vertex.sortVertex(vertexList);
        slabList = Slab.initializeSlabs(edgeList, vertexList);
        if (show) {
            System.out.println(slabList.toString());
        }
    }

    public void stepSeven(boolean show) {
        stepSix(show);
        if (show) {
            System.out.println("Step 7 - SlabList initialization\n================================");
        }
        slabList = Slab.initializeEdgeList(Slab.initializeSlabs(edgeList, vertexList));
        if (show) {
            System.out.println(slabList.toString());
        }
    }

    public void searchSlabList(Vertex vertex, boolean show) {
        if (show) {
            System.out.println("\nStep 8 - Search\n===============");
        }

        searchPoint = vertex;

        if (vertex.getY() < slabList.get(0).getVertex().getY()) {
            searchCase = 1;
            resultFace = getFaceList().get(0);
            if (show) {
                System.out.println(" Face 0 X < First Slab");
            }
            return;
        } else if (vertex.getY() >= slabList.get(slabList.size() - 1).getMaxY()) {
            searchCase = 2;
            resultFace = getFaceList().get(0);
            if (Vertex.compareVertices(vertex, slabList.get(resultSlab).getVertex())) {
                searchCase = 5;
            }
            if (show) {
                System.out.println(" Face 0 X > Last Slab");
            }
            return;
        } else {
            //Binary Search by Y
            resultSlab = Slab.binarySearch(slabList, 0, slabList.size() - 1, vertex.getY());
        }

        if (resultSlab >= 0) {

            ArrayList<Edge> edgeList = slabList.get(resultSlab).getEdgeList();
            int edgeListSize = slabList.get(resultSlab).getEdgeList().size() - 1;
            Vertex origin1 = slabList.get(resultSlab).getEdgeList().get(0).getVertexOne();
            Vertex origin2 = slabList.get(resultSlab).getEdgeList().get(edgeListSize).getVertexOne();
            Vertex firstEdge = slabList.get(resultSlab).getEdgeList().get(0).getVertexTwo();
            Vertex lastEdge = slabList.get(resultSlab).getEdgeList().get(edgeListSize).getVertexTwo();

            if (Slab.determinant(vertex, origin1, firstEdge) > 0) {
                searchCase = 3;
                resultFace = getFaceList().get(0);
                resultSecondEdge = edgeList.get(0);
                if (show) {
                    System.out.println(" Face 0 X < E0");
                }
            } else if (Slab.determinant(vertex, origin2, lastEdge) < 0) {
                searchCase = 4;
                resultFace = getFaceList().get(0);
                resultFirstEdge = edgeList.get(edgeList.size() - 1);
                if (show) {
                    System.out.println(" Face 0 X> En");
                }
            } else {
                int result = PSLG.binarySearch(edgeList, edgeListSize, vertex);
                if (result == NOT_FOUND) {
                    searchCase = NOT_FOUND;
                    if (Vertex.compareVertices(vertex, slabList.get(resultSlab).getVertex())) {
                        searchCase = 5;
                        if (show) {
                            System.out.println("Point = Slab Vertex" + "\nSlab: " + slabList.get(resultSlab).getName());
                        }
                    }
                } else {
                    searchCase = 0;
                    resultFace = getFaceList().get(edgeList.get(result).getFaceTwo());
                    resultFirstEdge = edgeList.get(result);
                    resultSecondEdge = edgeList.get(result + 1);

                    if (show) {
                        System.out.println("Slab: " + slabList.get(resultSlab).getName() +
                                "\nFace: " + resultFace.getName() +
                                "\nEdge 1: " + resultFirstEdge.getName() +
                                "\nEdge 2: " + resultSecondEdge.getName());
                    }
                }
            }
        }
    }

    private static int binarySearch(ArrayList<Edge> edgeList, int rightPointer, Vertex vertex) {
        int leftPointer = 0;
        try {
            while (leftPointer <= rightPointer) {
                int middlePointer = leftPointer + (rightPointer - leftPointer) / 2;
                int determinant1 = Slab.determinant(vertex,
                        edgeList.get(middlePointer).getVertexOne(),
                        edgeList.get(middlePointer).getVertexTwo());
                int determinant2 = Slab.determinant(vertex,
                        edgeList.get(middlePointer + 1).getVertexOne(),
                        edgeList.get(middlePointer + 1).getVertexTwo());

                if (determinant1 <= 0 && determinant2 > 0) {
                    return middlePointer;
                }
                if (determinant2 < 0) {
                    leftPointer = middlePointer + 1;
                } else {
                    rightPointer = middlePointer - 1;
                }
            }
        } catch (Exception e) {
            e.getCause().printStackTrace();
            e.printStackTrace();
        }
        return NOT_FOUND;
    }
}
