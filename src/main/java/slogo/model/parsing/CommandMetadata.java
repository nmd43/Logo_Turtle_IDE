package slogo.model.parsing;

import java.util.List;

public class CommandMetadata {

  private final String canonicalName;
  private final List<Parameter> parameters;
  private final int numberOfExpectedParameters;
  private final String implementingClassName;
  private List<String> aliases;

  public CommandMetadata(String canonicalName, List<Parameter> parameters, int numberOfExpectedParameters,
                         String implementingClassName, List<String> aliases) {
    this.canonicalName = canonicalName;
    this.parameters = parameters;
    this.numberOfExpectedParameters = numberOfExpectedParameters;
    this.implementingClassName = implementingClassName;
    this.aliases = aliases;
  }

  // Getters
  public String getCanonicalName() {
    return canonicalName;
  }

  public List<Parameter> getParameters() {
    return parameters;
  }


  public int getNumberOfExpectedParameters() {
    return numberOfExpectedParameters;
  }

  public String getImplementingClassName() {
    return implementingClassName;
  }

  public List<String> getAliases() {
    return aliases;
  }

  // Setters

  public void setAliases(List<String> aliases) {
    this.aliases = aliases;
  }

  // Inner class for Parameters
  public static class Parameter {

    private String name;
    private String type;
    private String description;

    public Parameter(String name, String type, String description) {
      this.name = name;
      this.type = type;
      this.description = description;
    }

    // Parameter getters and setters
    public String getName() {
      return name;
    }

    public String getType() {
      return type;
    }

    public String getDescription() {
      return description;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setType(String type) {
      this.type = type;
    }

    public void setDescription(String description) {
      this.description = description;
    }
  }
}

