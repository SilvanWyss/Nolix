//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.coreapi.netapi.endpoint2api.ISlot;
import ch.nolix.coreapi.netapi.securityapi.SecurityLevel;

//class
/**
 * A {@link Server} is a {@link BaseServer} that listens to {@link NetEndPoint}
 * on a specific port.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public final class Server extends BaseServer {

  //attribute
  private final ch.nolix.core.net.endpoint.Server internalServer;

  //constructor
  /**
   * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the
   * given port.
   * 
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  private Server(final int port) {

    //Creates the internal net server of the current net server.
    internalServer = ch.nolix.core.net.endpoint.Server.forPort(port);

    //Creates a close dependency to the internal net server of the current net
    //server.
    createCloseDependencyTo(internalServer);
  }

  //constructor
  /**
   * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the
   * given port.
   * 
   * When a web browser connects to the {@link Server}, the {@link Server} will
   * send the given httpMessage and close the connection.
   * 
   * @param port
   * @param httpMessage
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given httpMessage is null.
   * @throws EmptyArgumentException        if the given httpMessage is blank.
   */
  private Server(final int port, final String httpMessage) {

    //Creates the internal net server of the current net server.
    internalServer = ch.nolix.core.net.endpoint.Server.forPortAndHttpMessage(port, httpMessage);

    //Creates a close dependency to the internal net server of the current net
    //server.
    createCloseDependencyTo(internalServer);
  }

  //static method
  /**
   * @param port
   * @return a new {@link Server} that will listen to {@link NetEndPoint}s on the
   *         given port.
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public static Server forPort(final int port) {
    return new Server(port);
  }

  //static method
  /**
   * @param port
   * @param httpMessage
   * @return a new {@link Server} that will listen to {@link NetEndPoint}s on the
   *         given port. When a web browser connects to the {@link Server}, the
   *         {@link Server} will send the given httpMessage and close the
   *         connection.
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given httpMessage is null.
   * @throws EmptyArgumentException        if the given httpMessage is blank.
   */
  public static Server forPortAndHttpMessage(final int port, final String httpMessage) {
    return new Server(port, httpMessage);
  }

  //method
  /**
   * @return the port of the current {@link Server}.
   */
  public int getPort() {
    return internalServer.getPort();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityLevel getSecurityLevel() {
    return SecurityLevel.UNSECURE;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultSlot(final ISlot defaultSlot) {
    internalServer.addDefaultSlot(new ServerEndPointTaker(defaultSlot.getName(), this));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final ISlot slot) {
    internalServer.addSlot(new ServerEndPointTaker(slot.getName(), this));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final ISlot slot) {
    internalServer.removeSlotByName(slot.getName());
  }
}
