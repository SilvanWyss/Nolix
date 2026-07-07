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
public class MethodMediator extends AbstractObjectMediator<Method> {
  protected MethodMediator(final Method argument) {
    super(argument, LowerCaseVariableNameCatalog.METHOD);
  }

  private MethodMediator(final String argumentName, final Method argument) {
    super(argument, argumentName);
  }

  public static MethodMediator forArgument(final Method argument) {
    return new MethodMediator(argument);
  }

  public static MethodMediator forArgumentNameAndArgument(final String argumentName, final Method argument) {
    return new MethodMediator(argumentName, argument);
  }

  public final MethodMediator doesNotHaveAnnotations() {
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

  public final MethodMediator doesNotReturnAnything() {
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

  public final <A extends Annotation> MethodMediator hasAnnotation(final Class<A> annotationType) {
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

  public final MethodMediator hasParametersOfTypeOnly(final Class<String> type) {
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

  public final MethodMediator hasReturnType(final Class<?> returnType) {
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
