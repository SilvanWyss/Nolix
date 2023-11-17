//package declaration
package ch.nolix.core.programstructure.builder.main;

import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public abstract class ArgumentCapturer< //NOSONAR: ArgumentCapturer does not have abstract methods.
A, N> {

  //attribute
  private boolean hasArgument;

  //optional attribute
  private A argument;

  //optional attribute
  private final N nextArgumentCapturer;

  //optional attribute
  private Supplier<N> builder;

  //constructor
  protected ArgumentCapturer() {
    nextArgumentCapturer = null;
  }

  //constructor
  protected ArgumentCapturer(final N nextArgumentCapturer) {

    GlobalValidator.assertThat(nextArgumentCapturer).thatIsNamed("next argument capturer").isNotNull();

    this.nextArgumentCapturer = nextArgumentCapturer;
  }

  //method
  public final N next() {

    assertHasNextArgumentCapturer();

    return nextArgumentCapturer;
  }

  //method
  protected final N setArgumentAndGetNext(final A argument) {

    setArgument(argument);

    return getNextArgumentCapturerOrResult();
  }

  //method
  @SuppressWarnings("unchecked")
  protected final void setBuilder(final Supplier<?> builder) {
    if (hasNextArgumentCapturer()) {
      ((ArgumentCapturer<?, ?>) nextArgumentCapturer).setBuilder(builder);
    } else {

      GlobalValidator.assertThat(builder).thatIsNamed(LowerCaseCatalogue.BUILDER).isNotNull();

      this.builder = (Supplier<N>) builder;
    }
  }

  //method
  protected final A getStoredArgument() {

    assertHasArgument();

    return argument;
  }

  //method
  private void assertHasArgument() {
    if (!hasArgument()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "argument");
    }
  }

  //method
  private void assertHasBuilder() {
    if (!hasBuilder()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "builder");
    }
  }

  //method
  private void assertHasNextArgumentCapturer() {
    if (!hasNextArgumentCapturer()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next argument capturer");
    }
  }

  //method
  private N build() {
    return getStoredBuilder().get();
  }

  //method
  private N getNextArgumentCapturerOrResult() {

    if (hasNextArgumentCapturer()) {
      return nextArgumentCapturer;
    }

    return build();
  }

  //method
  private Supplier<N> getStoredBuilder() {

    assertHasBuilder();

    return builder;
  }

  //method
  private boolean hasArgument() {
    return hasArgument;
  }

  //method
  private boolean hasBuilder() {
    return (builder != null);
  }

  //method
  private boolean hasNextArgumentCapturer() {
    return (nextArgumentCapturer != null);
  }

  //method
  private void setArgument(final A argument) {

    hasArgument = true;

    this.argument = argument;
  }
}
