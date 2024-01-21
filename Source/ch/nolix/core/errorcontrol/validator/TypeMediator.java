//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class TypeMediator<T> extends ArgumentMediator<Class<T>> {

  //constructor
  protected TypeMediator(final Class<T> argument) {
    super(LowerCaseVariableCatalogue.TYPE, argument);
  }

  //constructor
  protected TypeMediator(final String argumentName, final Class<T> argument) {
    super(argumentName, argument);
  }

  //static method
  public static <T2> TypeMediator<T2> forArgument(final Class<T2> argument) {
    return new TypeMediator<>(argument);
  }

  //static method
  public static <T2> TypeMediator<T2> forArgumentNameAndArgument(final String argumentName, final Class<T2> argument) {
    return new TypeMediator<>(argumentName, argument);
  }

  //method
  public final void isAbstract() {

    isNotNull();

    if (!Modifier.isAbstract(getStoredArgument().getModifiers())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not abstract");
    }
  }

  //method
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

  //method
  public final void isConcrete() {

    isNotNull();

    if (Modifier.isAbstract(getStoredArgument().getModifiers())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not concrete");
    }
  }

  //method
  public final void isEnum() {

    isNotNull();

    if (!getStoredArgument().isEnum()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not an enum");
    }
  }

  //method
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

  //method
  public final void isInterface() {

    isNotNull();

    if (!getStoredArgument().isInterface()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not an interface");
    }
  }

  //method
  public final void isSubTypeOf(final Class<?> type) {
    if (!type.isAssignableFrom(getStoredArgument())
    || getStoredArgument().isAssignableFrom(type)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "is not a sub type of " + type.getName());
    }
  }

  //method
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
