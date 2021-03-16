package laboratory6;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private PSLG pslg;

    //Top Menu
    public Label titleLabel;

    //Left Menu
    public VBox searchVBox;
    public TextField xPointTextField;
    public TextField yPointTextField;
    public Button searchPointButton;

    //Center Menu
    private int counter = 0;
    public Label stepLabel;
    public StackPane centerStackPane;
    public Button previousButton;
    public Button nextButton;
    private final String[] steps = {"Step 1 - VertexList initialization",
            "Step 2 - EdgeList and FaceList initialization",
            "Step 3 - Sort vertices by Y coordinates and rename them",
            "Step 4 - Reinitialize EdgeList with new VertexList",
            "Step 5 - Edge reorientation",
            "Step 6 - Slabs initialization",
            "Step 7 - SlabList initialization"};

    //Right Menu
    public CheckBox consoleCheckBox;
    public VBox resultsVBox;
    public Label foundLabel;
    public HBox slabHBox;
    public Label foundSlab;
    public HBox faceHBox;
    public Label foundFace;
    public HBox edgeHBox1;
    public Label foundEdge1;
    public HBox edgeHBox2;
    public Label foundEdge2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Top Menu
        TopContent.initializeTitle(titleLabel, "Planar Straight Line Graph - Slab Method");

        //Left Menu
        searchMenu(false);

        //Center Menu
        previousButton.setDisable(counter == 0);
        stepLabel.setText(steps[counter]);

        //Right Menu
        consoleCheckBox.setSelected(true);
        searchLabels(false);

        pslg = new PSLG();
        stepOne();
    }

    public void handlePreviousButtonClick() {
        counter--;
        switch (counter) {
            case 0:
                stepOne();
                break;
            case 1:
                stepTwo();
                break;
            case 2:
                stepThree();
                break;
            case 3:
                stepFour();
                break;
            case 4:
                stepFive();
                break;
            case 5:
                stepSix();
                break;
            case 6:
                stepSeven();
                break;
        }
        updateItems();
    }

    public void handleNextButtonClick() {
        counter++;
        switch (counter) {
            case 0:
                stepOne();
                break;
            case 1:
                stepTwo();
                break;
            case 2:
                stepThree();
                break;
            case 3:
                stepFour();
                break;
            case 4:
                stepFive();
                break;
            case 5:
                stepSix();
                break;
            case 6:
                stepSeven();
                break;
        }
        updateItems();
    }

    private void stepOne() {
        pslg.stepOne(consoleCheckBox.isSelected());
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(new Group(Drawing.drawVertices(pslg.getVertexList(), counter, null)));
    }

    private void stepTwo() {
        pslg.stepTwo(consoleCheckBox.isSelected());
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(new Group(Drawing.drawEdges(pslg.getEdgeList(),
                pslg.getFaceList(), pslg.getVertexList(), counter, null)));
    }

    private void stepThree() {
        pslg.stepThree(consoleCheckBox.isSelected());
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(new Group(Drawing.drawEdges(pslg.getEdgeList(),
                pslg.getFaceList(), pslg.getVertexList(), counter, null)));
    }

    private void stepFour() {
        pslg.stepFour(consoleCheckBox.isSelected());
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(new Group(Drawing.drawEdges(pslg.getEdgeList(),
                pslg.getFaceList(), pslg.getVertexList(), counter, null)));
    }

    private void stepFive() {
        pslg.stepFive(consoleCheckBox.isSelected());
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(new Group(Drawing.drawEdges(pslg.getEdgeList(),
                pslg.getFaceList(), pslg.getVertexList(), counter, null)));
    }

    private void stepSix() {
        searchMenu(false);
        searchLabels(false);
        pslg.stepSix(consoleCheckBox.isSelected());
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(new Group(Drawing.drawSlabs(pslg.getSlabList(), pslg.getEdgeList(),
                pslg.getFaceList(), pslg.getVertexList(), counter, null)));
    }

    private void stepSeven() {
        searchMenu(true);
        pslg.stepSeven(consoleCheckBox.isSelected());
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(new Group(Drawing.drawSlabs(pslg.getSlabList(), pslg.getEdgeList(),
                pslg.getFaceList(), pslg.getVertexList(), counter, null)));
    }

    private void updateItems() {
        //Center menu
        stepLabel.setText(steps[counter]);
        previousButton.setDisable(counter == 0);
        nextButton.setDisable(counter == 6);
    }

    private void searchMenu(boolean show) {
        searchVBox.setVisible(show);
    }

    private void searchLabels(boolean show) {
        resultsVBox.setVisible(show);
        slabHBox.setVisible(show);
        faceHBox.setVisible(show);
        edgeHBox1.setVisible(show);
        edgeHBox2.setVisible(show);

        if (show) {
            if (pslg.getSearchCase() == PSLG.getCase0()) {
                foundLabel.setText("Found");
                foundSlab.setText(Slab.display(pslg.getSlabList().get(pslg.getResultSlab())));
                foundFace.setText(pslg.getResultFace().getName());
                foundEdge1.setText(Edge.display(pslg.getResultFirstEdge()));
                foundEdge2.setText(Edge.display(pslg.getResultSecondEdge()));
            } else if (pslg.getSearchCase() == PSLG.getCase1()) {
                foundLabel.setText("Found");
                foundSlab.setText("Point < Slab " + pslg.getSlabList().get(0).getName());
                foundFace.setText(pslg.getResultFace().getName());
                edgeHBox1.setVisible(false);
                edgeHBox2.setVisible(false);
            } else if (pslg.getSearchCase() == PSLG.getCase2()) {
                foundLabel.setText("Found");
                foundSlab.setText("Point > Slab " + (pslg.getSlabList().get(pslg.getSlabList().size() - 1).getName()));
                foundFace.setText(pslg.getResultFace().getName());
                edgeHBox1.setVisible(false);
                edgeHBox2.setVisible(false);
            } else if (pslg.getSearchCase() == PSLG.getCase3()) {
                foundLabel.setText("Found");
                foundSlab.setText(Slab.display(pslg.getSlabList().get(pslg.getResultSlab())));
                foundFace.setText(pslg.getResultFace().getName());
                foundEdge2.setText(Edge.display(pslg.getResultSecondEdge()));
                edgeHBox1.setVisible(false);
            } else if (pslg.getSearchCase() == PSLG.getCase4()) {
                foundLabel.setText("Found");
                foundSlab.setText(Slab.display(pslg.getSlabList().get(pslg.getResultSlab())));
                foundFace.setText(pslg.getResultFace().getName());
                foundEdge1.setText(Edge.display(pslg.getResultFirstEdge()));
                edgeHBox2.setVisible(false);
            } else if (pslg.getSearchCase() == PSLG.getCase5()) {
                foundLabel.setText("Found Point = Slab Vertex");
                foundSlab.setText(Slab.display(pslg.getSlabList().get(pslg.getResultSlab())));
                faceHBox.setVisible(false);
                edgeHBox1.setVisible(false);
                edgeHBox2.setVisible(false);
            } else {
                resultsVBox.setVisible(false);
            }
        }
    }

    public void handleSearchButton() {
        pslg.searchSlabList(new Vertex(isDouble(xPointTextField, xPointTextField.getText()),
                isDouble(yPointTextField, yPointTextField.getText()), "S"), consoleCheckBox.isSelected());
        centerStackPane.getChildren().clear();
        centerStackPane.getChildren().add(new Group(Drawing.drawSlabs(pslg.getSlabList(), pslg.getEdgeList(),
                pslg.getFaceList(), pslg.getVertexList(), counter, pslg.getSearchPoint())));

        searchLabels(true);
    }

    private double isDouble(TextField input, String message) {
        try {
            return Double.parseDouble(input.getText());
        } catch (NumberFormatException e) {
            ConfirmBox.displayError("Error", "Error: \" " + message + " \" is not a number\n Please enter a number");
            return 0;
        }
    }

    public void closeProgram() {
        Boolean answer = ConfirmBox.display("Exit", " Sure you want to exit?");
        if (answer) {
            Platform.exit();
        }
    }
}
