/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.element.multistateconfiguration.ValueStoringState;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link StateProperty}.
 */
public final class StateProperty<V> {
  private boolean hasValueOrDefinesEmpty;

  private V value;

  private StateProperty() {
  }

  public static <V2> StateProperty<V2> createUndefinedStateProperty() {
    return new StateProperty<>();
  }

  public ValueStoringState getAssignmentType() {
    if (!hasValueOrDefinesEmpty()) {
      return ValueStoringState.FORWARDING;
    }

    if (!hasValue()) {
      return ValueStoringState.DEFINING_EMPTY;
    }

    return ValueStoringState.STORING_VALUE;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  public V getValue() {
    if (!hasValueOrDefinesEmpty || value == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.VALUE);
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

  //For a better performance, this implementation does not use all available comfort methods.
  public void setValue(final V value) {
    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.VALUE);
    }

    hasValueOrDefinesEmpty = true;
    this.value = value;
  }
}
