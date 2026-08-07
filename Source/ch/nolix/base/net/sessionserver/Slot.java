/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.sessionserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.executoranddataproviderserver.IEndPoint;
import ch.nolix.baseapi.net.executoranddataproviderserver.ISlot;

/**
 * @author Silvan Wyss
 */
final class Slot implements ISlot {
  private final String name;

  private final AbstractServer<?> parentServer;

  /**
   * Creates a new {Slot} with the given name that will belong to the given
   * parentServer.
   * 
   * @param name
   * @param parentServer
   * @throws RuntimeException if given name is null or blank
   * @throws RuntimeException if the given parentServer is null
   */
  private Slot(final String name, final AbstractServer<?> parentServer) {
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
  public static Slot withNameAndParentServer(final String name, final AbstractServer<?> parentServer) {
    return new Slot(name, parentServer);
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
    parentServer.internalTakeEndPoint(endPoint);
  }
}
