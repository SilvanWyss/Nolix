/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreplierserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.senderandreceiverserver.EndPoint;
import ch.nolix.baseapi.net.senderandreceiverserver.Slot;

/**
 * @author Silvan Wyss
 */
public final class SenderAndReceiverSlot implements Slot {
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
  private SenderAndReceiverSlot(final String name, final AbstractServer parentServer) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();
    Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();

    this.parentServer = parentServer;
    this.name = name;
  }

  /**
   * @param name
   * @param parentServer
   * @return a new {@link SenderAndReceiverSlot} with the given name and that will belong to the
   *         given parentServer
   * @throws RuntimeException if given name is null or blank
   * @throws RuntimeException if the given parentServer is null
   */
  public static SenderAndReceiverSlot withNameAndParentServer(final String name, final AbstractServer parentServer) {
    return new SenderAndReceiverSlot(name, parentServer);
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
  public void takeBackendEndPoint(final EndPoint backendEndPoint) {
    parentServer.internalTakeBackendEndPoint(NetEndPoint.withInternalEndPoint(backendEndPoint));
  }
}
