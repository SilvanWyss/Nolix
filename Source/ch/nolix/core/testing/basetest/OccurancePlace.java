//package declaration
package ch.nolix.core.testing.basetest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public record OccurancePlace(String className, int lineNumber) {

  //constructor
  public OccurancePlace( //NOSONAR: This constructor does more than the default one.
    final String className,
    final int lineNumber) {

    if (className == null) {
      throw ArgumentIsNullException.forArgumentName("class name");
    }

    if (className.isBlank()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate("class name", className, "is blank");
    }

    if (lineNumber < 1) {
      throw NonPositiveArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.LINE_NUMBER, lineNumber);
    }

    this.className = className;
    this.lineNumber = lineNumber;
  }

  //method
  public String getClassName() {
    return className;
  }

  //method
  public int getLineNumber() {
    return lineNumber;
  }

  //method
  @Override
  public String toString() {
    return ("(" + getClassName() + ".java:" + getLineNumber() + ")");
  }
}
