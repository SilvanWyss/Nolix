/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.clientserver.Application;
import ch.nolix.baseapi.net.executoranddataproviderserver.EndPoint;
import ch.nolix.baseapi.net.executoranddataproviderserver.Slot;

/**
 * @author Silvan Wyss
 */
final class ServerSlot implements Slot {
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
  private ServerSlot(final String name, final AbstractServer<?> parentServer) {
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
  public static ServerSlot withNameAndParentServer(
    final String name,
    final AbstractServer<?> parentServer) {
    return new ServerSlot(name, parentServer);
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
    final var targetApplication = (AbstractApplication<?, ?>) getTargetApplicationOfBackendEndPoint(backendEndPoint);
    final var clientClass = targetApplication.getClientClass();
    final var backendClient = ReflectionTool.createInstanceFromDefaultConstructorOfClass(clientClass);

    backendClient.internalSetEndPoint(backendEndPoint);
    targetApplication.takeBackendClient(backendClient);
  }

  private Application<?, ?> getTargetApplicationOfBackendEndPoint(final EndPoint endPoint) {
    if (endPoint.hasCustomTargetSlot()) {
      final var applicationUrlName = endPoint.getCustomTargetSlot();

      return parentServer.getStoredApplicationByUrlName(applicationUrlName);
    }

    return parentServer.getStoredDefaultApplication();
  }
}
