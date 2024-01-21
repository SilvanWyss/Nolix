//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class MethodMediator extends ArgumentMediator<Method> {

  //constructor
  MethodMediator(final Method argument) {
    super(LowerCaseVariableCatalogue.METHOD, argument);
  }

  //constructor
  MethodMediator(final String argumentName, final Method argument) {
    super(argumentName, argument);
  }

  //method
  public final MethodMediator doesNotHaveAnnotations() {

    isNotNull();

    if (getStoredArgument().getAnnotations().length != 0) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "has annotations");
    }

    return this;
  }

  //method
  public final MethodMediator doesNotReturnAnything() {

    isNotNull();

    if (getStoredArgument().getReturnType() != void.class) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "returns something");
    }

    return this;
  }

  //method
  public final <A extends Annotation> MethodMediator hasAnnotation(final Class<A> annotationType) {

    if (annotationType == null) {
      throw ArgumentIsNullException.forArgumentName("annotation type");
    }

    isNotNull();

    if (getStoredArgument().getAnnotation(annotationType) == null) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "does not have the annotation '" + annotationType.getName() + "'");
    }

    return this;
  }

  //method
  public final MethodMediator hasParametersOfTypeOnly(final Class<String> type) {

    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(getArgumentName());
    }

    isNotNull();

    for (final var p : getStoredArgument().getParameters()) {
      if (!p.getType().isAssignableFrom(type)) {
        throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          getArgumentName(),
          getStoredArgument(),
          "has a parameter '" + p.getName() + "', that is not a " + type.getName());
      }
    }

    return this;
  }

  //method
  public final MethodMediator hasReturnType(final Class<Node> returnType) {

    if (returnType == null) {
      throw ArgumentIsNullException.forArgumentName("return type");
    }

    if (getStoredArgument().getReturnType() != returnType) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "does not have the return type '" + returnType.getName() + "'");
    }

    return this;
  }
}
