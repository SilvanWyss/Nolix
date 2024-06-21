//package declaration
package ch.nolix.core.argumentcaptor.base;

//Java imports
import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public abstract class ArgumentCaptor<A, N> { //NOSONAR: ArgumentCaptor does not have abstract methods.

  //attribute
  private boolean hasArgument;

  //optional attribute
  private A argument;

  //optional attribute
  private final ArgumentCaptor<?, ?> nextArgumentCaptor;

  //optional attribute
  private final N nextArgumentCaptorAsNext;

  //optional attribute
  private Supplier<N> builder;

  //constructor
  protected ArgumentCaptor() {

    nextArgumentCaptor = null;

    nextArgumentCaptorAsNext = null;
  }

  //constructor
  protected ArgumentCaptor(final N nextArgumentCaptor) {
    if (nextArgumentCaptor instanceof ArgumentCaptor<?, ?> localArgumentCaptor) {

      this.nextArgumentCaptor = localArgumentCaptor;

      nextArgumentCaptorAsNext = nextArgumentCaptor;
    } else {
      throw InvalidArgumentException.forArgumentNameAndArgument("next argument captor", nextArgumentCaptor);
    }
  }

  //method
  public final N nxtArgCpt() {

    assertHasNextArgumentCaptor();

    return nextArgumentCaptorAsNext;
  }

  //method
  protected final A getStoredArgument() {

    assertHasArgument();

    return argument;
  }

  //method
  protected final N setArgumentAndGetNext(final A argument) {

    setArgument(argument);

    return getNext();
  }

  //method
  @SuppressWarnings("unchecked")
  protected final void setBuilder(final Supplier<?> builder) {
    if (hasNextArgumentCaptor()) {
      nextArgumentCaptor.setBuilder(builder);
    } else {

      assertDoesNotHaveBuilder();

      GlobalValidator.assertThat(builder).thatIsNamed(LowerCaseVariableCatalogue.BUILDER).isNotNull();

      this.builder = (Supplier<N>) builder;
    }
  }

  //method
  private void assertDoesNotHaveBuilder() {
    if (hasBuilder()) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.BUILDER);
    }
  }

  //method
  private void assertHasArgument() {
    if (!hasArgument()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this,
        LowerCaseVariableCatalogue.ARGUMENT);
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
  private N getNext() {

    if (hasNextArgumentCaptor()) {
      return nextArgumentCaptorAsNext;
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
  private boolean hasNextArgumentCaptor() {
    return (nextArgumentCaptor != null);
  }

  //method
  private void setArgument(final A argument) {

    hasArgument = true;

    this.argument = argument;
  }
}
