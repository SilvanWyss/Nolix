//package declaration
package ch.nolix.system.element.multistateconfiguration;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.ValueStoringState;

//class
final class StateProperty<V> {

  //attribute
  private boolean hasValueOrDefinesEmpty;

  //optional attribute
  private V value;

  //method
  public ValueStoringState getAssignmentType() {

    if (!hasValueOrDefinesEmpty()) {
      return ValueStoringState.FORWARDING;
    }

    if (!hasValue()) {
      return ValueStoringState.DEFINING_EMPTY;
    }

    return ValueStoringState.STORING_VALUE;
  }

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  public V getValue() {

    if (!hasValueOrDefinesEmpty || value == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.VALUE);
    }

    return value;
  }

  //method
  public boolean hasValue() {
    return (value != null);
  }

  //method
  public boolean hasValueOrDefinesEmpty() {
    return hasValueOrDefinesEmpty;
  }

  //method
  public void setEmpty() {
    hasValueOrDefinesEmpty = true;
    value = null;
  }

  //method
  public void setForwarding() {
    hasValueOrDefinesEmpty = false;
    value = null;
  }

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  public void setValue(final V value) {

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.VALUE);
    }

    hasValueOrDefinesEmpty = true;
    this.value = value;
  }
}
