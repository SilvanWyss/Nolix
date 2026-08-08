/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.executoranddataproviderserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.senderandreplierserver.Slot;

/**
 * @author Silvan Wyss
 */
final class Level2Slot implements Slot {
  private final String name;

  private final AbstractServer parentServer;

  /**
   * Creates a new {@ServerSlot} with the given name and that will belong to the
   * given parentServer.
   * 
   * @param name
   * @param parentServer
   * @throws RuntimeException if given name is null or blank
   * @throws RuntimeException if the given parentServer is null
   */
  private Level2Slot(final String name, final AbstractServer parentServer) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();
    Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();

    this.parentServer = parentServer;
    this.name = name;
  }

  /**
   * @param name
   * @param parentServer
   * @return a new {@ServerSlot} with the given name and that will belong to the
   *         given parentServer
   * @throws RuntimeException if given name is null or blank
   * @throws RuntimeException if the given parentServer is null
   */
  public static Level2Slot withNameAndParentServer(final String name, final AbstractServer parentServer) {
    return new Level2Slot(name, parentServer);
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
  public void takeBackendEndPoint(final ch.nolix.baseapi.net.senderandreplierserver.EndPoint backendEndPoint) {
    parentServer.internalTakeBackendEndPoint(NetEndPoint.withInternalEndPoint(backendEndPoint));
  }
}
