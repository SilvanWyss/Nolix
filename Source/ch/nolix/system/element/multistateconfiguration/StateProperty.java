package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.ValueStoringState;

final class StateProperty<V> {

  private boolean hasValueOrDefinesEmpty;

  private V value;

  public ValueStoringState getAssignmentType() {

    if (!hasValueOrDefinesEmpty()) {
      return ValueStoringState.FORWARDING;
    }

    if (!hasValue()) {
      return ValueStoringState.DEFINING_EMPTY;
    }

    return ValueStoringState.STORING_VALUE;
  }

  //For a better performance, this implementation does not use all comfortable methods.
  public V getValue() {

    if (!hasValueOrDefinesEmpty || value == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.VALUE);
    }

    return value;
  }

  public boolean hasValue() {
    return (value != null);
  }

  public boolean hasValueOrDefinesEmpty() {
    return hasValueOrDefinesEmpty;
  }

  public void setEmpty() {
    hasValueOrDefinesEmpty = true;
    value = null;
  }

  public void setForwarding() {
    hasValueOrDefinesEmpty = false;
    value = null;
  }

  //For a better performance, this implementation does not use all comfortable methods.
  public void setValue(final V value) {

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.VALUE);
    }

    hasValueOrDefinesEmpty = true;
    this.value = value;
  }
}
