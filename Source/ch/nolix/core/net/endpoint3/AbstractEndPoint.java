package ch.nolix.core.net.endpoint3;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.baseendpoint.AbstractBaseEndPoint;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.net.endpoint3.IDataProviderController;
import ch.nolix.coreapi.net.endpoint3.IEndPoint;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractEndPoint extends AbstractBaseEndPoint implements IEndPoint {
  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  private IDataProviderController receiverController;

  AbstractEndPoint() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasReceivingDataProviderController() {
    return (receiverController != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void runCommands(IChainedNode... commands) {
    for (final var c : commands) {
      runCommand(c);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setReceivingDataProviderController(final IDataProviderController receiverController) {
    //Asserts that the given receiverController is not null.
    Validator.assertThat(receiverController).thatIsNamed("receiver controller").isNotNull();

    //Sets the receiver controller of the current EndPoint.
    this.receiverController = receiverController;
  }

  /**
   * @throws ClosedArgumentException if the current {@link AbstractEndPoint} is
   *                                 closed.
   */
  protected void assertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  /**
   * @return the receiver controller of the current {@link AbstractEndPoint}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractEndPoint} does
   *                                               not have a receiver controller.
   */
  IDataProviderController getStoredReceiverController() {
    if (hasReceivingDataProviderController()) {
      return receiverController;
    }

    FlowController
      .forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS)
      .waitUntil(this::hasReceivingDataProviderController);

    if (!hasReceivingDataProviderController()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.RECEIVER);
    }

    return receiverController;
  }
}
