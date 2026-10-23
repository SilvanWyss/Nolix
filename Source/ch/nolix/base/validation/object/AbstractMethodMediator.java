/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractMethodMediator extends AbstractObjectMediator<Method> {
  protected AbstractMethodMediator(final Method argument) {
    super(argument, LowerCaseVariableNameCatalog.METHOD);
  }

  protected AbstractMethodMediator(final Method argument, final String argumentName) {
    super(argument, argumentName);
  }

  /**
   * @return the current {@link AbstractMethodMediator}
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} has annotations
   */
  public final AbstractMethodMediator doesNotHaveAnnotations() {
    isNotNull();

    if (getStoredArgument().getAnnotations().length != 0) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "has annotations");
    }

    return this;
  }

  /**
   * @return the current {@link AbstractMethodMediator}
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} returns something
   */
  public final AbstractMethodMediator doesNotReturnAnything() {
    isNotNull();

    if (getStoredArgument().getReturnType() != void.class) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "returns something");
    }

    return this;
  }

  /**
   * @param annotationType
   * @param <A>            the type of the annotation
   * @return the current {@link AbstractMethodMediator}
   * @throws RuntimeException if the given annotationType is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} does not have an
   *                          annotation of the given annotationType
   */
  public final <A extends Annotation> AbstractMethodMediator hasAnnotation(final Class<A> annotationType) {
    if (annotationType == null) {
      throw ArgumentIsNullException.forArgumentName("annotation type");
    }

    isNotNull();

    if (getStoredArgument().getAnnotation(annotationType) == null) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not have the annotation '" + annotationType.getName() + "'");
    }

    return this;
  }

  /**
   * @param type
   * @return the current {@link AbstractMethodMediator}
   * @throws RuntimeException if the given type is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} has a parameter that
   *                          is not of the given type
   */
  public final AbstractMethodMediator hasParametersOfTypeOnly(final Class<String> type) {
    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(getArgumentName());
    }

    isNotNull();

    for (final var p : getStoredArgument().getParameters()) {
      if (!p.getType().isAssignableFrom(type)) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          getStoredArgument(),
          getArgumentName(),
          "has a parameter '" + p.getName() + "', that is not a " + type.getName());
      }
    }

    return this;
  }

  /**
   * @param returnType
   * @return the current {@link AbstractMethodMediator}
   * @throws RuntimeException if the given returnType is null
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractMethodMediator} is null or does not
   *                          have the given returnType
   */
  public final AbstractMethodMediator hasReturnType(final Class<?> returnType) {
    if (returnType == null) {
      throw ArgumentIsNullException.forArgumentName("return type");
    }

    final var argument = getStoredArgument();

    if (argument == null || argument.getReturnType() != returnType) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "does not have the return type '" + returnType.getName() + "'");
    }

    return this;
  }
}
