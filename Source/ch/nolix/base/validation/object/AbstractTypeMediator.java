/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.lang.reflect.Modifier;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <T> the type a {@link AbstractTypeMediator} is for.
 */
public abstract class AbstractTypeMediator<T> extends AbstractObjectMediator<Class<T>> {
  protected AbstractTypeMediator(final Class<T> argument) {
    super(argument, LowerCaseVariableNameCatalog.TYPE);
  }

  protected AbstractTypeMediator(final Class<T> argument, final String argumentName) {
    super(argument, argumentName);
  }

  public final void isAbstract() {
    isNotNull();

    if (!Modifier.isAbstract(getStoredArgument().getModifiers())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not abstract");
    }
  }

  public final void isClass() {
    isNotNull();

    if (getStoredArgument().isInterface()
    || getStoredArgument().isEnum()
    || getStoredArgument().isArray()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not a class");
    }
  }

  public final void isConcrete() {
    isNotNull();

    if (Modifier.isAbstract(getStoredArgument().getModifiers())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not concrete");
    }
  }

  public final void isEnum() {
    isNotNull();

    if (!getStoredArgument().isEnum()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not an enum");
    }
  }

  public final void isImplementing(final Class<?> paramInterface) {
    TypeMediator.forArgumentAndArgumentName(paramInterface, LowerCaseVariableNameCatalog.TYPE).isInterface();

    isClass();

    if (!paramInterface.isAssignableFrom(getStoredArgument())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not implement " + paramInterface.getName());
    }
  }

  public final void isInterface() {
    isNotNull();

    if (!getStoredArgument().isInterface()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not an interface");
    }
  }

  public final void isSubTypeOf(final Class<?> type) {
    if (!type.isAssignableFrom(getStoredArgument())
    || getStoredArgument().isAssignableFrom(type)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not a sub type of " + type.getName());
    }
  }

  public final void isSuperTypeOf(final Class<?> type) {
    if (!getStoredArgument().isAssignableFrom(type)
    || type.isAssignableFrom(getStoredArgument())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not a super type of " + type.getName());
    }
  }
}
