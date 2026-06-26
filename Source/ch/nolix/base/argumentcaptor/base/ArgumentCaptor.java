/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.base;

import java.util.function.Supplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <A> is the type of a {@link ArgumentCaptor}.
 * @param <N> the type of the next thing of a {@link ArgumentCaptor}.
 */
public abstract class ArgumentCaptor<A, N> { //NOSONAR: ArgumentCaptor does not have abstract methods.

  private boolean hasArgument;

  private A argument;

  private final ArgumentCaptor<?, ?> nextArgumentCaptor;

  private final N nextArgumentCaptorAsNext;

  private Supplier<N> builder;

  protected ArgumentCaptor() {
    nextArgumentCaptor = null;

    nextArgumentCaptorAsNext = null;
  }

  protected ArgumentCaptor(final N nextArgumentCaptor) {
    if (nextArgumentCaptor instanceof final ArgumentCaptor<?, ?> localArgumentCaptor) {
      this.nextArgumentCaptor = localArgumentCaptor;

      nextArgumentCaptorAsNext = nextArgumentCaptor;
    } else {
      throw InvalidArgumentException.forArgumentAndArgumentName(nextArgumentCaptor, "next argument captor");
    }
  }

  public final N nxtArgCpt() {
    assertHasNextArgumentCaptor();

    return nextArgumentCaptorAsNext;
  }

  protected final A getStoredArgument() {
    assertHasArgument();

    return argument;
  }

  protected final N setArgumentAndGetNext(final A argument) {
    setArgument(argument);

    return getNext();
  }

  @SuppressWarnings("unchecked")
  protected final void setBuilder(final Supplier<?> builder) {
    if (hasNextArgumentCaptor()) {
      nextArgumentCaptor.setBuilder(builder);
    } else {
      assertDoesNotHaveBuilder();

      Validator.assertThat(builder).thatIsNamed(LowerCaseVariableNameCatalog.BUILDER).isNotNull();

      this.builder = (Supplier<N>) builder;
    }
  }

  private void assertDoesNotHaveBuilder() {
    if (hasBuilder()) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.BUILDER);
    }
  }

  private void assertHasArgument() {
    if (!hasArgument()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this,
        LowerCaseVariableNameCatalog.ARGUMENT);
    }
  }

  private void assertHasBuilder() {
    if (!hasBuilder()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.BUILDER);
    }
  }

  private void assertHasNextArgumentCaptor() {
    if (!hasNextArgumentCaptor()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next argument captor");
    }
  }

  private N build() {
    return getStoredBuilder().get();
  }

  private N getNext() {
    if (hasNextArgumentCaptor()) {
      return nextArgumentCaptorAsNext;
    }

    return build();
  }

  private Supplier<N> getStoredBuilder() {
    assertHasBuilder();

    return builder;
  }

  private boolean hasArgument() {
    return hasArgument;
  }

  private boolean hasBuilder() {
    return (builder != null);
  }

  private boolean hasNextArgumentCaptor() {
    return (nextArgumentCaptor != null);
  }

  private void setArgument(final A argument) {
    hasArgument = true;

    this.argument = argument;
  }
}
