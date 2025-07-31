package ch.nolix.core.errorcontrol.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public class MethodMediator extends ArgumentMediator<Method> {

  MethodMediator(final Method argument) {
    super(LowerCaseVariableCatalog.METHOD, argument);
  }

  MethodMediator(final String argumentName, final Method argument) {
    super(argumentName, argument);
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

    if (getStoredArgument().getReturnType() != returnType) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not have the return type '" + returnType.getName() + "'");
    }

    return this;
  }
}
