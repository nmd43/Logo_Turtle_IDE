package slogo.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * TurtleImage class implements TurtleView interface and creates the visible turtle within the main
 * scene.
 *
 * @author Jonathan Esponda
 */

public class TurtleImage implements TurtleView {

  private final Pane turtlePane;
  private final double startingX = 282.5;
  private final double startingY = 232.75;
  private final ImageView turtleImage;
  private final Color penColor = Color.BLACK;
  private boolean hasMoved = false;
  private int id = 1;
  private boolean penDown = true;

  private TurtleLine line  = new TurtleLine(0,0,0,0, penColor);

  public TurtleImage(Pane pane) {
    turtleImage = new ImageView();
    turtleImage.setFitWidth(35);
    turtleImage.setFitHeight(35);
    turtleImage.setRotate(90);
    turtlePane = pane;
    turtleImage.getStyleClass().add("turtle-image");
  }

  /**
   * getNode returns the ImageView of the turtle.
   */
  @Override
  public ImageView getImageView() {
    return turtleImage;
  }

  /**
   * update will be set off when the ModelTurtle is updated and will cause the turtle to move on
   * screen.
   *
   * @param x the new x position update from the model
   * @param y the new y position update from the model
   */
  @Override
  public void updatePosition(double x, double y) {
    double previousX = turtleImage.getX();
    double previousY = turtleImage.getY();

    if (penDown) {
      double nextX = startingX + x + (turtleImage.getFitWidth() / 2);
      double nextY = startingY - y + (turtleImage.getFitHeight() / 2);
      turtleImage.setX(startingX + x);
      turtleImage.setY(startingY - y);
      line = new TurtleLine(previousX + (turtleImage.getFitWidth() / 2),
          previousY + (turtleImage.getFitHeight() / 2),
          nextX, nextY, penColor);
      line.draw(turtlePane);
    } else {
      turtleImage.setX(startingX + x);
      turtleImage.setY(startingY - y);
    }

  }

  /**
   * update will be set off when the ModelTurtle is updated and will cause the turtle to rotate.
   *
   * @param degrees the new degree rotation from the model
   */
  @Override
  public void updateHeading(double degrees) {
    turtleImage.setRotate(turtleImage.getRotate() - degrees);
  }


  public void setPosition(double x, double y) {
    double previousX = turtleImage.getX();
    double previousY = turtleImage.getY();
    turtleImage.setX(startingX + x);
    turtleImage.setY(startingY - y);
    double nextX = turtleImage.getX() + (turtleImage.getFitWidth() / 2);
    double nextY = turtleImage.getY() + (turtleImage.getFitHeight() / 2);
  }




  private TurtleLine getLine(){
    return this.line;
  }



  /**
   * Updates the visibility of the turtle.
   *
   * @param id turtle id being affected
   * @param visible boolean that decides whether the turtle is visible or not
   */
  @Override
  public void updateVisibility(int id, boolean visible) {
    //turtle id yet to be implemented
    turtleImage.setVisible(visible);
  }

  /**
   * Set the direction of the turtle.
   *
   * @param id turtle id being affected
   * @param degrees double that the turtle's direction will be changed to
   */
  @Override
  public void setHeading(int id, double degrees) {
    turtleImage.setRotate(degrees);
  }

}
