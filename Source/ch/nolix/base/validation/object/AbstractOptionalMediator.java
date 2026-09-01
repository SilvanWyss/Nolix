/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.util.Optional;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.validation.object.IOptionalMediator;

/**
 * @author Silvan Wyss
 * @param <T> the type of the element of the {@link Optional} of a
 *            {@link AbstractOptionalMediator}.
 */
public abstract class AbstractOptionalMediator<T> extends AbstractObjectMediator<Optional<T>>
implements IOptionalMediator {
  protected AbstractOptionalMediator(
    final Optional<T> argument // NOSONAR: An Optional is the argument of an OptionalMediator.
  ) {
    super(argument);
  }

  protected AbstractOptionalMediator(
    final Optional<T> argument, // NOSONAR: An Optional is the argument of an OptionalMediator.
    final String argumentName) {
    super(argument, argumentName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void containsEqualObject(Object object) {
    final var argument = getStoredArgument();

    if (argument == null // NOSONAR: The argument can be null.
    || argument.isEmpty()
    || !argument.get().equals(object)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "does not contain an element that equals the given Object '" + object + "'");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void containsObject(Object object) {
    final var argument = getStoredArgument();

    if (argument == null // NOSONAR: The argument can be null.
    || argument.isEmpty()
    || argument.get() == object) {
      throw //
      ArgumentDoesNotContainElementException.forArgumentAndArgumentNameAndElement(argument, getArgumentName(), object);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void containsObjectOfType(Class<Object> type) {
    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.TYPE);
    }

    final var argument = getStoredArgument();

    if (argument == null // NOSONAR: The argument can be null.
    || argument.isEmpty()
    || !type.isAssignableFrom(argument.get().getClass())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "does not contain an element that is of the given type '" + type + "'");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void isEmpty() {
    isNotNull();

    final var argument = getStoredArgument();

    if (argument.isPresent()) {
      throw NonEmptyArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void isPresent() {
    isNotNull();

    final var argument = getStoredArgument();

    if (argument.isEmpty()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not present");
    }
  }
}
