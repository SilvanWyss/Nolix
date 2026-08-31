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
