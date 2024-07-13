package slogo.model;

import java.util.HashMap;
import java.util.Map;

public class Environment {
  private final Map<Integer, TurtleInterface> turtles;
  private TurtleInterface activeTurtles;

  public Environment() {
    this.turtles = new HashMap<>();
    this.activeTurtles = new TurtleComposite();
    addTurtle(); // Add the initial turtle to the environment
  }

  public void addTurtle() {
    SingleTurtle newTurtle = new SingleTurtle(turtles.size() + 1); //ID starts from 1
    turtles.put(newTurtle.getId(), newTurtle);
    ((TurtleComposite) activeTurtles).addTurtle(newTurtle); //cast to a turtle composite
  }

  public TurtleInterface addTurtleAndGet() {
    int newId = turtles.size() + 1; // Assuming IDs are sequential and start from 1
    SingleTurtle newTurtle = new SingleTurtle(newId);
    turtles.put(newId, newTurtle);
    ((TurtleComposite) activeTurtles).addTurtle(newTurtle);
    return newTurtle;
  }

    public TurtleInterface getTurtle(int turtleId) {
    return turtles.get(turtleId);
  }

  public Map<Integer, TurtleInterface> getAllTurtles() {
    return turtles;
  }

  public void setActiveTurtle(int turtleId) {
    if (turtles.containsKey(turtleId)) {
      TurtleComposite newActiveTurtles = new TurtleComposite();
      newActiveTurtles.addTurtle(turtles.get(turtleId));
      this.activeTurtles = newActiveTurtles;
    } else {
      throw new IllegalArgumentException("Turtle ID does not exist: " + turtleId);
    }
  }

  public TurtleInterface getActiveTurtles() {
    return activeTurtles;
  }

  // Allows setting multiple turtles as active based on their IDs
  public void setActiveTurtles(int... turtleIds) {
    TurtleComposite newActiveTurtles = new TurtleComposite();
    for (int id : turtleIds) {
      if (turtles.containsKey(id)) {
        newActiveTurtles.addTurtle(turtles.get(id));
      } else {
        throw new IllegalArgumentException("Turtle ID does not exist: " + id);
      }
    }
    this.activeTurtles = newActiveTurtles;
  }

}
