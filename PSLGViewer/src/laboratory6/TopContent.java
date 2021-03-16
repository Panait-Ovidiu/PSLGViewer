package laboratory6;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

class TopContent {

    public static void initializeTitle(Label title, String subTitleString) {
        title.setText(subTitleString);
        title.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 14));
    }
}
