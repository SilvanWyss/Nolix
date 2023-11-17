//package declaration
package ch.nolix.core.testing.basetest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public final class Error {

  //attribute
  private final String errorMessage;

  //attribute
  private final OccurancePlace occurancePlace;

  //constructor
  public Error(
    final String errorMessage,
    final String occuranceClassName,
    final int occuranceLineNumber) {
    this(errorMessage, new OccurancePlace(occuranceClassName, occuranceLineNumber));
  }

  //constructor
  public Error(final String errorMessage, final OccurancePlace occurancePlace) {

    if (errorMessage == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ERROR_MESSAGE);
    }

    if (errorMessage.isBlank()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseCatalogue.ERROR_MESSAGE,
        errorMessage,
        "is blank");
    }

    if (occurancePlace == null) {
      throw ArgumentIsNullException.forArgumentName("occurance place");
    }

    this.errorMessage = errorMessage;
    this.occurancePlace = occurancePlace;
  }

  //method
  public String getErrorMessage() {
    return errorMessage;
  }

  //method
  public String getOccuranceClassName() {
    return occurancePlace.getClassName();
  }

  //method
  public int getOccuranceLineNumber() {
    return occurancePlace.getLineNumber();
  }

  //method
  @Override
  public String toString() {
    return (errorMessage + " " + occurancePlace.toString());
  }
}
