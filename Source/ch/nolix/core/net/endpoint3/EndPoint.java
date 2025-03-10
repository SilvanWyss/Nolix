package ch.nolix.core.net.endpoint3;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.baseendpoint.AbstractEndPoint;
import ch.nolix.core.programcontrol.flowcontrol.GlobalFlowController;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.netapi.endpoint3api.IDataProviderController;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public abstract class EndPoint extends AbstractEndPoint implements IEndPoint {

  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  private IDataProviderController receiverController;

  EndPoint() {
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
  public final void runCommands(final IChainedNode command, IChainedNode... commands) {

    runCommand(command);

    //Iterates the given commands.
    for (final var c : commands) {
      runCommand(c);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setReceivingDataProviderController(final IDataProviderController receiverController) {

    //Asserts that the given receiverController is not null.
    GlobalValidator.assertThat(receiverController).thatIsNamed("receiver controller").isNotNull();

    //Sets the receiver controller of the current EndPoint.
    this.receiverController = receiverController;
  }

  /**
   * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
   */
  protected void assertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  /**
   * @return the receiver controller of the current {@link EndPoint}.
   * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint}
   *                                               does not have a receiver
   *                                               controller.
   */
  IDataProviderController getStoredReceiverController() {

    if (hasReceivingDataProviderController()) {
      return receiverController;
    }

    GlobalFlowController
      .forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS)
      .waitUntil(this::hasReceivingDataProviderController);

    if (!hasReceivingDataProviderController()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.RECEIVER);
    }

    return receiverController;
  }
}
