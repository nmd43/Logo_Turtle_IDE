package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

import java.awt.geom.Point2D;

public class TowardsCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
      throw new IllegalArgumentException("Towards command expects two numeric parameters.");
    }



    double targetX = ((Number) parameters[0]).doubleValue();
    double targetY = ((Number) parameters[1]).doubleValue();

    environment.getActiveTurtles().executeAction(turtle -> {
      Point2D.Double currentPosition = turtle.getPosition();

      // Calculate the angle to the target position
      double angleToTarget = calculateAngleToTarget(currentPosition.x, currentPosition.y, targetX, targetY);


      double oldHeading = turtle.getHeading();
      double angleDifference = angleToTarget - oldHeading;

      angleDifference = normalizeAngleDifference(angleDifference);

      //Set turtles heading to new angle
      double newHeading = (oldHeading + angleDifference) % 360;
      if (newHeading < 0) newHeading += 360;
      turtle.setHeading(newHeading);



      // Notify observers about the heading update
      observerNotifier.notifyHeadingUpdate(turtle.getId(), angleDifference);

    });

  }

  private double calculateAngleToTarget(double fromX, double fromY, double toX, double toY) {
    double deltaX = toX - fromX;
    double deltaY = toY - fromY;
    // Atan2 returns the angle in radians from the positive x-axis to the point (y,x)
    double angleInRadians = Math.atan2(deltaY, deltaX);

    System.out.println("DeltaX: " + deltaX);
    System.out.println("DeltaY: " + deltaY);
    System.out.println("Angle in radians: " + angleInRadians);

    // Convert radians to degrees to match the turtle's coordinate system
    // No need to subtract 90 degrees as right direction (0 degrees) is now our base
    double angleInDegrees = Math.toDegrees(angleInRadians);

    return angleInDegrees;
  }

  private double normalizeAngleDifference(double angleDifference) {
    angleDifference %= 360;
    if (angleDifference > 180) angleDifference -= 360;
    else if (angleDifference < -180) angleDifference += 360;
    return angleDifference;
  }
}
