//package declaration
package ch.nolix.core.errorcontrol.validator;

//class
public final class ExtendedTypeMediator<T> extends TypeMediator<T> {

  //constructor
  private ExtendedTypeMediator(final Class<T> argument) {
    super(argument);
  }

  //static method
  public static <T2> ExtendedTypeMediator<T2> forArgument(final Class<T2> argument) {
    return new ExtendedTypeMediator<>(argument);
  }

  //method
  public TypeMediator<T> thatIsNamed(final String arguemtName) {
    return new TypeMediator<>(arguemtName, getStoredArgument());
  }
}
