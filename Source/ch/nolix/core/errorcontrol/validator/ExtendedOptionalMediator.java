package ch.nolix.core.errorcontrol.validator;

import java.util.Optional;

public final class ExtendedOptionalMediator<T> extends OptionalMediator<T> {

  private ExtendedOptionalMediator(
    final Optional<T> argument //NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    super(argument);
  }

  public static <T2> ExtendedOptionalMediator<T2> forArgument(
    final Optional<T2> argument //NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    return new ExtendedOptionalMediator<>(argument);
  }

  public OptionalMediator<T> thatIsNamed(final Class<?> type) {
    return OptionalMediator.forArgumentNameAndArgument(type.getSimpleName(), getStoredArgument());
  }

  public OptionalMediator<T> thatIsNamed(final String argumentName) {
    return OptionalMediator.forArgumentNameAndArgument(argumentName, getStoredArgument());
  }
}
