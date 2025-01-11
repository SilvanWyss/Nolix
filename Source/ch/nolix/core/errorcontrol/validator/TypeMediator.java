package ch.nolix.core.errorcontrol.validator;

import java.lang.reflect.Modifier;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public class TypeMediator<T> extends ArgumentMediator<Class<T>> {

  protected TypeMediator(final Class<T> argument) {
    super(LowerCaseVariableCatalog.TYPE, argument);
  }

  protected TypeMediator(final String argumentName, final Class<T> argument) {
    super(argumentName, argument);
  }

  public static <T2> TypeMediator<T2> forArgument(final Class<T2> argument) {
    return new TypeMediator<>(argument);
  }

  public static <T2> TypeMediator<T2> forArgumentNameAndArgument(final String argumentName, final Class<T2> argument) {
    return new TypeMediator<>(argumentName, argument);
  }

  public final void isAbstract() {

    isNotNull();

    if (!Modifier.isAbstract(getStoredArgument().getModifiers())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not abstract");
    }
  }

  public final void isClass() {

    isNotNull();

    if (getStoredArgument().isInterface()
    || getStoredArgument().isEnum()
    || getStoredArgument().isArray()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not a class");
    }
  }

  public final void isConcrete() {

    isNotNull();

    if (Modifier.isAbstract(getStoredArgument().getModifiers())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not concrete");
    }
  }

  public final void isEnum() {

    isNotNull();

    if (!getStoredArgument().isEnum()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not an enum");
    }
  }

  public final void isImplementing(final Class<?> pInterface) {

    new TypeMediator<>(pInterface).isInterface();

    isClass();

    if (!pInterface.isAssignableFrom(getStoredArgument())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "does not implement " + pInterface.getName());
    }
  }

  public final void isInterface() {

    isNotNull();

    if (!getStoredArgument().isInterface()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not an interface");
    }
  }

  public final void isSubTypeOf(final Class<?> type) {
    if (!type.isAssignableFrom(getStoredArgument())
    || getStoredArgument().isAssignableFrom(type)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not a sub type of " + type.getName());
    }
  }

  public final void isSuperTypeOf(final Class<?> type) {
    if (!getStoredArgument().isAssignableFrom(type)
    || type.isAssignableFrom(getStoredArgument())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not a super type of " + type.getName());
    }
  }
}
