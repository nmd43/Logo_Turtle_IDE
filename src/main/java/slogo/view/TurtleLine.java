package slogo.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * TurtleLine represents a line that will be drawn.
 *
 * @author Jonathan Esponda
 */
public class TurtleLine implements Line {

  private final javafx.scene.shape.Line line;
  //private Color penColor = Color.BLUE;

  /**
   * TurtleLine draws a line that follows the turtle on the pane.
   *
   * @param startX beginning x position
   * @param startY beginning y position
   * @param endX   final x position
   * @param endY   final y position
   */
  TurtleLine(double startX, double startY, double endX, double endY, Color pen) {
    line = new javafx.scene.shape.Line(startX, startY, endX, endY);
    line.setStroke(pen);
    line.setStrokeWidth(2);
  }



  /**
   * Draws the line onto the pane.
   *
   * @param pane pane that the line will be drawn on
   */
  @Override
  public void draw(Pane pane) {
    pane.getChildren().add(line);
  }
}
