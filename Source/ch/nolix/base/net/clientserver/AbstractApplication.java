/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import java.util.Locale;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.net.target.ApplicationInstanceTarget;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.net.clientserver.Application;
import ch.nolix.baseapi.net.clientserver.BackendClient;
import ch.nolix.baseapi.net.target.IApplicationTarget;
import ch.nolix.baseapi.net.target.IServerTarget;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link AbstractBackendClient}s of a
 *            {@link AbstractApplication}.
 * @param <S> the type of the application service of a
 *            {@link AbstractApplication}.
 */
public abstract class AbstractApplication<C extends AbstractBackendClient<C, S>, S>
implements Application<C, S> {
  private ch.nolix.baseapi.net.clientserver.Server<?> memberParentServer;

  private final S applicationService;

  private final ILinkedList<C> clients = LinkedList.createEmpty();

  /**
   * Creates a new {@link AbstractApplication} with the given applicationService.
   * 
   * @param applicationService
   * @throws RuntimeException if the given applicationService is null
   */
  protected AbstractApplication(final S applicationService) {
    Validator.assertThat(applicationService).thatIsNamed("application service").isNotNull();

    this.applicationService = applicationService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IApplicationTarget toTarget() {
    final var serverTarget = getStoredParentServer().toTarget();

    return asTargetWithServerTarget(serverTarget);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean belongsToServer() {
    return (memberParentServer != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final Class<C> getClientClass() {
    return (Class<C>) (createInitialSession().getClientClass());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S getStoredApplicationService() {
    return applicationService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<C> getStoredClients() {
    removeClosedClients();

    return clients;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getUrlName() {
    return getName().replace(StringCatalog.SPACE, StringCatalog.UNDERSCORE).toLowerCase(Locale.ENGLISH);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasClientConnected() {
    return getStoredClients().containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalSetParentServer(ch.nolix.baseapi.net.clientserver.Server<?> parentServer) {
    Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();
    assertDoesNotBelongToServer();

    memberParentServer = parentServer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void takeBackendClient(final BackendClient<?> backendClient) {
    @SuppressWarnings("unchecked")
    final var castedBackendClient = (C) backendClient;

    castedBackendClient.internalSetParentApplication(this);
    clients.addAtEnd(castedBackendClient);
    FlowController.runInBackground(() -> castedBackendClient.internalPush(createInitialSession()));
  }

  /**
   * @return the initial {@link AbstractSession} class of the current
   *         {@link AbstractApplication}.
   */
  protected abstract Class<?> getInitialSessionClass();

  /**
   * @throws RuntimeException if the current {@link AbstractApplication} does not
   *                          belong to a {@link AbstractServer}.
   */
  private void assertBelongsToServer() {
    if (!belongsToServer()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, AbstractServer.class);
    }
  }

  /**
   * @throws RuntimeException if the current {@link AbstractApplication} belongs
   *                          already to a {@link AbstractServer}.
   */
  private void assertDoesNotBelongToServer() {
    if (belongsToServer()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentServer());
    }
  }

  /**
   * @param serverTarget
   * @return the current {@link AbstractApplication} as target using the given
   *         serverTarget.
   */
  private IApplicationTarget asTargetWithServerTarget(final IServerTarget serverTarget) {
    return ApplicationInstanceTarget
      .forHostAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        serverTarget.getHost(),
        serverTarget.getPort(),
        getName(),
        getUrlName(),
        serverTarget.getSecurityMode());
  }

  /**
   * @return a new initial {@link AbstractSession} for a {@link AbstractClient} of
   *         the current {@link AbstractApplication}.
   */
  @SuppressWarnings("unchecked")
  private AbstractSession<C, S> createInitialSession() {
    return (AbstractSession<C, S>) ReflectionTool.createInstanceFromDefaultConstructorOfClass(getInitialSessionClass());
  }

  /**
   * @return the parent {@link AbstractServer} of the current
   *         {@link AbstractApplication}
   * @throws RuntimeException if the current {@link AbstractApplication} does not
   *                          belong to a {@link AbstractServer}.
   */
  private ch.nolix.baseapi.net.clientserver.Server<?> getStoredParentServer() {
    assertBelongsToServer();

    return memberParentServer;
  }

  /**
   * Removes the closed {@link AbstractClient}s of the current
   * {@link AbstractApplication}.
   */
  private void removeClosedClients() {
    clients.removeAll(AbstractClient::isClosed);
  }
}
