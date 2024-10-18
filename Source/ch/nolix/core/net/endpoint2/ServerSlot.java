package ch.nolix.core.net.endpoint2;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

/**
 * @author Silvan Wyss
 * @version 2016-06-01
 */
final class ServerSlot implements ISlot {

  private final String name;

  private final BaseServer parentServer;

  /**
   * Creates a new {@ServerSlot} that will belong to the given parentServer.
   * 
   * @param name
   * @param parentServer
   * @throws ArgumentIsNullException  if given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given parentServer is null.
   */
  public ServerSlot(final String name, final BaseServer parentServer) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();

    this.parentServer = parentServer;
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void takeBackendEndPoint(final IEndPoint endPoint) {
    parentServer.internalTakeBackendEndPoint(new NetEndPoint(endPoint));
  }
}
