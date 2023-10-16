//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public class TypeMediator<T> extends ArgumentMediator<Class<T>> {

  //constructor
  TypeMediator(final Class<T> argument) {
    super(LowerCaseCatalogue.TYPE, argument);
  }

  //constructor
  TypeMediator(final String argumentName, final Class<T> argument) {
    super(argumentName, argument);
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
  public final void isNotAbstract() {

    isNotNull();

    if (Modifier.isAbstract(getStoredArgument().getModifiers())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          getArgumentName(),
          getStoredArgument(),
          "is abstract");
    }
  }

  //method
  public final void isSubClassOf(final Class<?> pClass) {

    new TypeMediator<>(pClass).isClass();

    isClass();

    if (!pClass.isAssignableFrom(getStoredArgument())
        || getStoredArgument().isAssignableFrom(pClass)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          getArgumentName(),
          getStoredArgument(),
          "is not a sub class of " + pClass.getName());
    }
  }

  //method
  public final void isSuperClassOf(final Class<?> pClass) {

    new TypeMediator<>(pClass).isClass();

    isClass();

    if (!getStoredArgument().isAssignableFrom(pClass)
        || pClass.isAssignableFrom(getStoredArgument())) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          getArgumentName(),
          getStoredArgument(),
          "is not a super class of " + pClass.getName());
    }
  }
}
