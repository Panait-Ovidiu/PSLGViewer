package laboratory6;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;

class Drawing {

    private static final int CIRCLE_POINT_RADIUS = 10;
    private static final int MULTIPLY_POINT_VALUE = 20;

    private static Node drawXYAxis() {
        Group group = new Group();

        Line xLine1 = new Line(0, 0, 350, 0);
        xLine1.setStroke(Color.DARKGREY);
        Polygon xLine1Arrow = new Polygon(350, 0, 340, -5, 340, 5);
        xLine1Arrow.setFill(Color.DARKGREY);
        Text xLine1Label = new Text(345, -10, "x");
        xLine1Label.setFill(Color.DARKGREY);
        xLine1Label.setScaleX(1.2);
        xLine1Label.setScaleY(1.2);

        Line xLine2 = new Line(0, 0, -350, 0);
        xLine2.setStroke(Color.DARKGREY);
        Polygon xLine2Arrow = new Polygon(-350, 0, -340, -5, -340, 5);
        xLine2Arrow.setFill(Color.DARKGREY);
        Text xLine2Label = new Text(-350, -10, "-x");
        xLine2Label.setFill(Color.DARKGREY);
        xLine2Label.setScaleX(1.2);
        xLine2Label.setScaleY(1.2);

        Line yLine1 = new Line(0, 0, 0, 275);
        yLine1.setStroke(Color.DARKGREY);
        Polygon yLine1Arrow = new Polygon(0, 275, -5, 265, 5, 265);
        yLine1Arrow.setFill(Color.DARKGREY);
        Text yLine1Label = new Text(-20, 270, "-y");
        yLine1Label.setFill(Color.DARKGREY);
        yLine1Label.setScaleX(1.2);
        yLine1Label.setScaleY(1.2);

        Line yLine2 = new Line(0, 0, 0, -275);
        yLine2.setStroke(Color.DARKGREY);
        Polygon yLine2Arrow = new Polygon(0, -275, -5, -265, 5, -265);
        yLine2Arrow.setFill(Color.DARKGREY);
        Text yLine2Label = new Text(-15, -270, "y");
        yLine2Label.setFill(Color.DARKGREY);
        yLine2Label.setScaleX(1.2);
        yLine2Label.setScaleY(1.2);

        group.getChildren().addAll(xLine1, xLine1Arrow, xLine1Label,
                xLine2, xLine2Arrow, xLine2Label,
                yLine1, yLine1Arrow, yLine1Label,
                yLine2, yLine2Arrow, yLine2Label);
        return group;
    }

    public static Node drawVertices(ArrayList<Vertex> vertexList, int step, Vertex searchVertex) {
        Group group = new Group();

        String name = "";

        if (step == 0) {
            group.getChildren().add(drawXYAxis());
        }

        for (Vertex vertex : vertexList) {
            Circle circle = new Circle();
            circle.setRadius(CIRCLE_POINT_RADIUS);
            circle.setFill(Color.FIREBRICK);
            circle.setStroke(Color.FIREBRICK);
            circle.setStrokeWidth(1.5);

            Text circleLabel = new Text();
            circleLabel.setText(name + (vertex.getId() + 1));
            circleLabel.setFill(Color.WHITE);
            circleLabel.setStroke(Color.WHITE);
            circleLabel.setScaleX(1);
            circleLabel.setScaleY(1);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(circle, circleLabel);
            stackPane.setLayoutX((vertex.getX() * MULTIPLY_POINT_VALUE) - CIRCLE_POINT_RADIUS);
            stackPane.setLayoutY(-(vertex.getY() * MULTIPLY_POINT_VALUE) - CIRCLE_POINT_RADIUS);
            group.getChildren().add(stackPane);
        }

        if (searchVertex != null) {
            group.getChildren().add(drawSearchVertex(searchVertex));
        }

        return group;
    }

    public static Node drawEdges(ArrayList<Edge> edgeList, ArrayList<Face> faceList,
                                 ArrayList<Vertex> vertexList, int step, Vertex searchVertex) {
        Group group = new Group();

        String name = "";

        if (step < 5) {
            group.getChildren().add(drawXYAxis());
        }

        for (Edge edge : edgeList) {
            Line line = new Line();
            line.setStartX((edge.getVertexOne().getX() * MULTIPLY_POINT_VALUE));
            line.setStartY(-(edge.getVertexOne().getY() * MULTIPLY_POINT_VALUE));
            line.setEndX((edge.getVertexTwo().getX() * MULTIPLY_POINT_VALUE));
            line.setEndY(-(edge.getVertexTwo().getY() * MULTIPLY_POINT_VALUE));
            line.setFill(Color.BLACK);
            line.setStroke(Color.BLACK);

            Text lineLabel = new Text();
            lineLabel.setX((line.getStartX() + line.getEndX()) / 2 - 5);
            lineLabel.setY((line.getStartY() + line.getEndY()) / 2);
            lineLabel.setText(name + (edge.getId() + 1));
            lineLabel.setFill(Color.WHITE);
            lineLabel.setScaleX(1);
            lineLabel.setScaleY(1);
            lineLabel.setStroke(Color.BLACK);

            Polygon arrow = drawArrow(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());

            group.getChildren().addAll(line, arrow, lineLabel);
        }

        group.getChildren().add(drawFaces(faceList));

        group.getChildren().add(drawVertices(vertexList, step, searchVertex));

        return group;
    }

    private static Node drawFaces(ArrayList<Face> faceList) {
        Group group = new Group();

        String name = "F";

        for (Face face : faceList) {
            Rectangle rectangle = new Rectangle();
            rectangle.setHeight(15);
            rectangle.setWidth(15);
            rectangle.setFill(Color.DARKGREEN);
            rectangle.setStroke(Color.DARKGREEN);
            rectangle.setStrokeWidth(1);

            Text circleLabel = new Text();
            circleLabel.setText(name + face.getId());
            circleLabel.setFill(Color.WHITE);
            circleLabel.setStroke(Color.WHITE);
            circleLabel.setScaleX(1);
            circleLabel.setScaleY(1);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(rectangle, circleLabel);
            stackPane.setLayoutX((face.getX() * MULTIPLY_POINT_VALUE) - CIRCLE_POINT_RADIUS);
            stackPane.setLayoutY(-(face.getY() * MULTIPLY_POINT_VALUE) - CIRCLE_POINT_RADIUS);
            group.getChildren().add(stackPane);
        }
        return group;
    }

    private static Polygon drawArrow(double startX, double startY, double endX, double endY) {
        double arrowAngle = Math.toRadians(10);
        double arrowTipAngle = Math.toRadians(0);
        double arrowLength = 25;
        double dx = startX - endX;
        double dy = startY - endY;
        double angle = Math.atan2(dy, dx);

        double x0 = Math.cos(angle + arrowTipAngle) * 10 + endX;
        double y0 = Math.sin(angle + arrowTipAngle) * 10 + endY;

        double x1 = Math.cos(angle + arrowAngle) * arrowLength + endX;
        double y1 = Math.sin(angle + arrowAngle) * arrowLength + endY;

        double x2 = Math.cos(angle - arrowAngle) * arrowLength + endX;
        double y2 = Math.sin(angle - arrowAngle) * arrowLength + endY;

        Polygon arrow = new Polygon();
        arrow.getPoints().addAll(x0, y0, x1, y1, x2, y2);

        return arrow;
    }

    public static Node drawSlabs(ArrayList<Slab> slabList, ArrayList<Edge> edgeList, ArrayList<Face> faceList,
                                 ArrayList<Vertex> vertexList, int step, Vertex searchVertex) {
        Group group = new Group();

        String name = "";

        if (step < 7) {
            group.getChildren().add(drawXYAxis());
        }

        for (Slab slab : slabList) {
            Line line = new Line();
            line.setStartX(-320);
            line.setStartY(-(slab.getVertex().getY() * MULTIPLY_POINT_VALUE));
            line.setEndX(320);
            line.setEndY(-(slab.getVertex().getY() * MULTIPLY_POINT_VALUE));
            line.setStroke(Color.DARKRED);
            line.setStrokeWidth(1.5);

            Text lineLabel = new Text();
            if ((slab.getId() + 1) > 9) {
                lineLabel.setX(-335);
            } else {
                lineLabel.setX(-330);
            }
            lineLabel.setY(line.getStartY() + 3);
            lineLabel.setText(name + (slab.getId() + 1));
            lineLabel.setFill(Color.DARKRED);
            lineLabel.setScaleX(1);
            lineLabel.setScaleY(1);
            lineLabel.setStroke(Color.DARKRED);

            group.getChildren().addAll(line, lineLabel);
        }

        group.getChildren().add(drawEdges(edgeList, faceList, vertexList, step, searchVertex));

        return group;
    }

    private static Node drawSearchVertex(Vertex searchVertex) {

        double xValue = ((searchVertex.getX() * MULTIPLY_POINT_VALUE));
        double yValue = (-(searchVertex.getY() * MULTIPLY_POINT_VALUE));

        Circle circle = new Circle(xValue, yValue, CIRCLE_POINT_RADIUS);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.DODGERBLUE);
        circle.setStrokeWidth(1.5);

        Circle dot = new Circle(xValue, yValue, 0.5);
        dot.setStroke(Color.DODGERBLUE);
        dot.setFill(Color.DODGERBLUE);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(circle, dot);
        stackPane.setLayoutX((searchVertex.getX() * MULTIPLY_POINT_VALUE) - CIRCLE_POINT_RADIUS);
        stackPane.setLayoutY(-(searchVertex.getY() * MULTIPLY_POINT_VALUE) - CIRCLE_POINT_RADIUS);

        Group group = new Group();
        group.getChildren().add(stackPane);

        return group;
    }
}
