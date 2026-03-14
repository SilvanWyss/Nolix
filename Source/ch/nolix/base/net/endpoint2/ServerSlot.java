/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.endpoint2;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.net.endpoint.IEndPoint;
import ch.nolix.baseapi.net.endpoint.ISlot;

/**
 * @author Silvan Wyss
 */
final class ServerSlot implements ISlot {
  private final String name;

  private final AbstractServer parentServer;

  /**
   * Creates a new {@ServerSlot} that will belong to the given parentServer.
   * 
   * @param name
   * @param parentServer
   * @throws RuntimeException  if given name is null.
   * @throws RuntimeException if the given name is blank.
   * @throws RuntimeException  if the given parentServer is null.
   */
  public ServerSlot(final String name, final AbstractServer parentServer) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();

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
