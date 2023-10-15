//package declaration
package ch.nolix.core.builder.main;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;

//class
public abstract class ArgumentCapturer< // NOSONAR: ArgumentCapturer does not have abstract methods.
    A, N> {

  // attribute
  private boolean hasArgument;

  // optional attribute
  private A argument;

  // optional attribute
  private final N nextArgumentCapturer;

  // optional attribute
  private IElementGetter<N> builder;

  // constructor
  protected ArgumentCapturer(final N nextArgumentCapturer) {
    this.nextArgumentCapturer = nextArgumentCapturer;
  }

  // method
  public final N next() {

    assertHasNextArgumentCapturer();

    return nextArgumentCapturer;
  }

  // method
  protected final N setArgumentAndGetNext(final A argument) {

    setArgument(argument);

    return getNextArgumentCapturerOrResult();
  }

  // method
  @SuppressWarnings("unchecked")
  protected final void setBuilder(final IElementGetter<?> builder) {
    if (hasNextArgumentCapturer()) {
      ((ArgumentCapturer<?, ?>) nextArgumentCapturer).setBuilder(builder);
    } else {

      GlobalValidator.assertThat(builder).thatIsNamed(LowerCaseCatalogue.BUILDER).isNotNull();

      this.builder = (IElementGetter<N>) builder;
    }
  }

  // method
  protected final A getStoredArgument() {

    assertHasArgument();

    return argument;
  }

  // method
  private void assertHasArgument() {
    if (!hasArgument()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "argument");
    }
  }

  // method
  private void assertHasBuilder() {
    if (!hasBuilder()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "builder");
    }
  }

  // method
  private void assertHasNextArgumentCapturer() {
    if (!hasNextArgumentCapturer()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next argument capturer");
    }
  }

  // method
  private N build() {
    return getStoredBuilder().getOutput();
  }

  // method
  private N getNextArgumentCapturerOrResult() {

    if (hasNextArgumentCapturer()) {
      return nextArgumentCapturer;
    }

    return build();
  }

  // method
  private IElementGetter<N> getStoredBuilder() {

    assertHasBuilder();

    return builder;
  }

  // method
  private boolean hasArgument() {
    return hasArgument;
  }

  // method
  private boolean hasBuilder() {
    return (builder != null);
  }

  // method
  private boolean hasNextArgumentCapturer() {
    return (nextArgumentCapturer != null);
  }

  // method
  private void setArgument(final A argument) {

    hasArgument = true;

    this.argument = argument;
  }
}
