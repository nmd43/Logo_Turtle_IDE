package slogo.view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;
import slogo.view.TurtleImage;

public class TurtleImageManager {
  private Pane turtlePane; // The pane where turtles are displayed
  private List<TurtleImage> turtleImages = new ArrayList<>();

  public TurtleImageManager(Pane turtlePane) {
    this.turtlePane = turtlePane;
  }

  public TurtleImage addTurtle() {
    TurtleImage turtleImage = new TurtleImage(turtlePane);
    turtleImages.add(turtleImage);
    turtlePane.getChildren().add(turtleImage.getImageView());
    return turtleImage;
  }

  public void updateTurtlePosition(int turtleId, double x, double y) {
    if (turtleId >= 0 && turtleId < turtleImages.size()) {
      turtleImages.get(turtleId).updatePosition(x, y);
    }
  }

  public void updateTurtleHeading(int turtleId, double heading) {
    if (turtleId >= 0 && turtleId < turtleImages.size()) {
      turtleImages.get(turtleId).updateHeading(heading);
    }
  }
}
