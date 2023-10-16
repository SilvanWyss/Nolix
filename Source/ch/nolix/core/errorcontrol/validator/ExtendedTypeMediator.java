//package declaration
package ch.nolix.core.errorcontrol.validator;

//class
public final class ExtendedTypeMediator<T> extends TypeMediator<T> {

  //constructor
  ExtendedTypeMediator(final Class<T> argument) {
    super(argument);
  }

  //method
  public TypeMediator<T> thatIsNamed(final String arguemtName) {
    return new TypeMediator<>(arguemtName, getStoredArgument());
  }
}
