//package declaration
package ch.nolix.core.programstructure.builder.main;

//Java imports
import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public abstract class ArgumentCaptor //NOSONAR: ArgumentCaptor does not have abstract methods.
<A, N> {

  //attribute
  private boolean hasArgument;

  //optional attribute
  private A argument;

  //optional attribute
  private final N nextArgumentCaptor;

  //optional attribute
  private Supplier<N> builder;

  //constructor
  protected ArgumentCaptor() {
    nextArgumentCaptor = null;
  }

  //constructor
  protected ArgumentCaptor(final N nextArgumentCaptor) {

    GlobalValidator.assertThat(nextArgumentCaptor).thatIsNamed("next argument captor").isNotNull();

    this.nextArgumentCaptor = nextArgumentCaptor;
  }

  //method
  public final N next() {

    assertHasNextArgumentCaptor();

    return nextArgumentCaptor;
  }

  //method
  protected final A getStoredArgument() {

    assertHasArgument();

    return argument;
  }

  //method
  protected final N setArgumentAndGetNext(final A argument) {

    setArgument(argument);

    return getStoredNextArgumentCaptorOrResult();
  }

  //method
  @SuppressWarnings("unchecked")
  protected final void setBuilder(final Supplier<?> builder) {
    if (hasNextArgumentCaptor()) {
      ((ArgumentCaptor<?, ?>) nextArgumentCaptor).setBuilder(builder);
    } else {

      GlobalValidator.assertThat(builder).thatIsNamed(LowerCaseVariableCatalogue.BUILDER).isNotNull();

      this.builder = (Supplier<N>) builder;
    }
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
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.BUILDER);
    }
  }

  //method
  private void assertHasNextArgumentCaptor() {
    if (!hasNextArgumentCaptor()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next argument captor");
    }
  }

  //method
  private N build() {
    return getStoredBuilder().get();
  }

  //method
  private Supplier<N> getStoredBuilder() {

    assertHasBuilder();

    return builder;
  }

  //method
  private N getStoredNextArgumentCaptorOrResult() {

    if (hasNextArgumentCaptor()) {
      return nextArgumentCaptor;
    }

    return build();
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
  private boolean hasNextArgumentCaptor() {
    return (nextArgumentCaptor != null);
  }

  //method
  private void setArgument(final A argument) {

    hasArgument = true;

    this.argument = argument;
  }
}
