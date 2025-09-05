package ch.nolix.core.errorcontrol.validator;

public final class ExtendedTypeMediator<T> extends TypeMediator<T> {
  private ExtendedTypeMediator(final Class<T> argument) {
    super(argument);
  }

  public static <T2> ExtendedTypeMediator<T2> forArgument(final Class<T2> argument) {
    return new ExtendedTypeMediator<>(argument);
  }

  public TypeMediator<T> thatIsNamed(final String arguemtName) {
    return new TypeMediator<>(arguemtName, getStoredArgument());
  }
}
