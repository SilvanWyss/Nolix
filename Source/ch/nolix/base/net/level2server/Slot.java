/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.level2server;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.net.level1server.IEndPoint;
import ch.nolix.baseapi.net.level1server.ISlot;

/**
 * @author Silvan Wyss
 */
public final class Slot implements ISlot {
  private final String name;

  private final AbstractServer parentServer;

  /**
   * Creates a new {@ServerSlot} with the given name and that will belong to the
   * given parentServer.
   * 
   * @param name
   * @param parentServer
   * @throws RuntimeException if given name is null or blank.
   * @throws RuntimeException if the given parentServer is null.
   */
  private Slot(final String name, final AbstractServer parentServer) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();

    this.parentServer = parentServer;
    this.name = name;
  }

  /**
   * @param name
   * @param parentServer
   * @return a new {@ServerSlot} with the given name and that will belong to the
   *         given parentServer.
   * @throws RuntimeException if given name is null or blank.
   * @throws RuntimeException if the given parentServer is null.
   */
  public static Slot withNameAndParentServer(final String name, final AbstractServer parentServer) {
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
  public void takeBackendEndPoint(final IEndPoint backendEndPoint) {
    parentServer.internalTakeBackendEndPoint(new NetEndPoint(backendEndPoint));
  }
}
