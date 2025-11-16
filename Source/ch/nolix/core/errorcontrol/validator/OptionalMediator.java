package ch.nolix.core.errorcontrol.validator;

import java.util.Optional;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.errorcontrol.validation.IOptionalMediator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public class OptionalMediator<T> extends ArgumentMediator<Optional<T>> implements IOptionalMediator {
  protected OptionalMediator(final Optional<T> argument //NOSONAR: An Optional is the argument of an OptionalMediator.
  ) {
    super(argument);
  }

  protected OptionalMediator(
    final String argumentName,
    final Optional<T> argument //NOSONAR: An Optional is the argument of an OptionalMediator.
  ) {
    super(argumentName, argument);
  }

  public static <T2> OptionalMediator<T2> forArgument(final Optional<T2> argument //NOSONAR: An Optional is the argument of an OptionalMediator.
  ) {
    return new OptionalMediator<>(argument);
  }

  public static <T2> OptionalMediator<T2> forArgumentNameAndArgument(
    final String argumentName,
    final Optional<T2> argument //NOSONAR: An Optional is the argument of a OptionalMediator.
  ) {
    return new OptionalMediator<>(argumentName, argument);
  }

  @Override
  public final void containsEqualObject(Object object) {
    final var argument = getStoredArgument();

    if (argument == null //NOSONAR: The argument can be null.
    || argument.isEmpty()
    || !argument.get().equals(object)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "does not contain an element that equals the given Object '" + object + "'");
    }
  }

  @Override
  public final void containsObject(Object object) {
    final var argument = getStoredArgument();

    if (argument == null //NOSONAR: The argument can be null.
    || argument.isEmpty()
    || argument.get() == object) {
      throw //
      ArgumentDoesNotContainElementException.forArgumentAndArgumentNameAndElement(argument, getArgumentName(), object);
    }
  }

  @Override
  public final void containsObjectOfType(Class<Object> type) {
    Validator.assertThat(type).thatIsNamed(LowerCaseVariableCatalog.TYPE).isNotNull();

    final var argument = getStoredArgument();

    if (argument == null //NOSONAR: The argument can be null.
    || argument.isEmpty()
    || !type.isAssignableFrom(argument.get().getClass())) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "does not contain an element that is of the given type '" + type + "'");
    }
  }

  @Override
  public final void isEmpty() {
    isNotNull();

    final var argument = getStoredArgument();

    if (argument.isEmpty()) {
      throw EmptyArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  @Override
  public final void isPresent() {
    isNotNull();

    final var argument = getStoredArgument();

    if (argument == null //NOSONAR: The argument can be null.
    || argument.isEmpty()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not present");
    }
  }
}
