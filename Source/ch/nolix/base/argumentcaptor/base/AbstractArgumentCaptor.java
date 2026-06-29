/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.base;

import java.util.function.Supplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.base.IArgumentCaptor;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <A> the type of the argument of a {@link AbstractArgumentCaptor}.
 * @param <S> the type of the successor of a {@link AbstractArgumentCaptor}.
 */
public abstract class AbstractArgumentCaptor<A, S> implements IArgumentCaptor<S> {
  private boolean hasArgument;

  private A optionalArgument;

  private final AbstractArgumentCaptor<?, ?> successorArgumentCaptor;

  private final S successorArgumentCaptorAsSuccessor;

  private Supplier<S> optionalBuilder;

  protected AbstractArgumentCaptor() {
    successorArgumentCaptor = null;
    successorArgumentCaptorAsSuccessor = null;
  }

  protected AbstractArgumentCaptor(final S successorArgumentCaptor) {
    if (successorArgumentCaptor instanceof final AbstractArgumentCaptor<?, ?> argumentCaptor) {
      this.successorArgumentCaptor = argumentCaptor;
      this.successorArgumentCaptorAsSuccessor = successorArgumentCaptor;
    } else {
      throw InvalidArgumentException.forArgumentAndArgumentName(successorArgumentCaptor, "successor argument captor");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S nxtArgCpt() {
    assertHasNextArgumentCaptor();

    return successorArgumentCaptorAsSuccessor;
  }

  protected final A getStoredArgument() {
    assertHasArgument();

    return optionalArgument;
  }

  protected final S setArgumentAndGetNext(final A argument) {
    setArgument(argument);

    return getNext();
  }

  @SuppressWarnings("unchecked")
  protected final void setBuilder(final Supplier<?> builder) {
    if (hasNextArgumentCaptor()) {
      successorArgumentCaptor.setBuilder(builder);
    } else {
      assertDoesNotHaveBuilder();
      Validator.assertThat(builder).thatIsNamed(LowerCaseVariableNameCatalog.BUILDER).isNotNull();

      this.optionalBuilder = (Supplier<S>) builder;
    }
  }

  private void assertDoesNotHaveBuilder() {
    if (hasBuilder()) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.BUILDER);
    }
  }

  private void assertHasArgument() {
    if (!hasArgument()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.ARGUMENT);
    }
  }

  private void assertHasBuilder() {
    if (!hasBuilder()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.BUILDER);
    }
  }

  private void assertHasNextArgumentCaptor() {
    if (!hasNextArgumentCaptor()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next argument captor");
    }
  }

  private S build() {
    return getStoredBuilder().get();
  }

  private S getNext() {
    if (hasNextArgumentCaptor()) {
      return successorArgumentCaptorAsSuccessor;
    }

    return build();
  }

  private Supplier<S> getStoredBuilder() {
    assertHasBuilder();

    return optionalBuilder;
  }

  private boolean hasArgument() {
    return hasArgument;
  }

  private boolean hasBuilder() {
    return optionalBuilder != null;
  }

  private boolean hasNextArgumentCaptor() {
    return successorArgumentCaptor != null;
  }

  private void setArgument(final A argument) {
    hasArgument = true;

    this.optionalArgument = argument;
  }
}
