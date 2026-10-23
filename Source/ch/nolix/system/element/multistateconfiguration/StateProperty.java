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

  /**
   * @param <V2> the type of the value of the created {@link StateProperty}
   * @return a new undefined {@link StateProperty}
   */
  public static <V2> StateProperty<V2> createUndefinedStateProperty() {
    return new StateProperty<>();
  }

  /**
   * @return the {@link ValueStoringState} of the current {@link StateProperty}
   */
  public ValueStoringState getAssignmentType() {
    if (!hasValueOrDefinesEmpty()) {
      return ValueStoringState.FORWARDING;
    }

    if (!hasValue()) {
      return ValueStoringState.DEFINING_EMPTY;
    }

    return ValueStoringState.STORING_VALUE;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * @return the value of the current {@link StateProperty}
   * @throws RuntimeException if the current {@link StateProperty} does not have a
   *                          value
   */
  public V getValue() {
    if (!hasValueOrDefinesEmpty || value == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.VALUE);
    }

    return value;
  }

  /**
   * @return true if the current {@link StateProperty} has a value, false
   *         otherwise
   */
  public boolean hasValue() {
    return (value != null);
  }

  /**
   * @return true if the current {@link StateProperty} has a value or defines to
   *         be empty, false otherwise
   */
  public boolean hasValueOrDefinesEmpty() {
    return hasValueOrDefinesEmpty;
  }

  /**
   * Sets the current {@link StateProperty} empty.
   */
  public void setEmpty() {
    hasValueOrDefinesEmpty = true;
    value = null;
  }

  /**
   * Sets the current {@link StateProperty} forwarding.
   */
  public void setForwarding() {
    hasValueOrDefinesEmpty = false;
    value = null;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * Sets the given value to the current {@link StateProperty}.
   * 
   * @param value
   */
  public void setValue(final V value) {
    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.VALUE);
    }

    hasValueOrDefinesEmpty = true;
    this.value = value;
  }
}
