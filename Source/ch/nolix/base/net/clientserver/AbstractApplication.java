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
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.net.clientserver.Application;
import ch.nolix.baseapi.net.executoranddataproviderserver.IEndPoint;
import ch.nolix.baseapi.net.target.IApplicationInstanceTarget;
import ch.nolix.baseapi.net.target.IServerTarget;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link AbstractBackendClient}s of a
 *            {@link AbstractApplication}.
 * @param <S> the type of the application service of a {@link AbstractApplication}.
 */
public abstract class AbstractApplication
<C extends AbstractBackendClient<C, S>, S>
implements Application<C, S> {
  private String instanceAddendix;

  private AbstractServer<?> parentServer;

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
  public final IApplicationInstanceTarget asTarget() {
    final var serverTarget = getStoredParentServer().asTarget();

    return asTargetWithServerTarget(serverTarget);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean belongsToServer() {
    return (parentServer != null);
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
  public final String getInstanceAppendix() {
    assertHasNameAddendum();

    return instanceAddendix;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getInstanceName() {
    if (!hasInstanceAppendix()) {
      return getApplicationName();
    }

    return String.format("%s %s", getApplicationName(), getInstanceAppendix());
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
  public final String getUrlInstanceName() {
    return getInstanceName().replace(StringCatalog.SPACE, StringCatalog.UNDERSCORE).toLowerCase(Locale.ENGLISH);
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
  public final boolean hasInstanceAppendix() {
    return (instanceAddendix != null);
  }

  /**
   * Lets the current {@link AbstractApplication} take the given client.
   * 
   * @param client
   */
  @SuppressWarnings("unchecked")
  final void takeClient(final AbstractBackendClient<?, ?> client) {
    final var localClient = (C) client;
    localClient.internalSetParentApplication(this);
    clients.addAtEnd(localClient);
    FlowController.runInBackground(() -> localClient.internalPush(createInitialSession()));
  }

  /**
   * Lets the current {@link AbstractApplication} take the given endPoint.
   * 
   * @param endPoint
   */
  final void takeEndPoint(final IEndPoint endPoint) {
    takeClient(createBackendClientWithEndPoint(endPoint));
  }

  /**
   * @return the initial {@link AbstractSession} class of the current
   *         {@link AbstractApplication}.
   */
  protected abstract Class<?> getInitialSessionClass();

  /**
   * Sets the given nameAddendix to the current {@link AbstractApplication}.
   * 
   * @param nameAddendix
   * @throws RuntimeException if the given nameAddendix is null
   * @throws RuntimeException if the given nameAddendix is blank
   * @throws RuntimeException if the current {@link AbstractApplication} has already an
   *                          instance name.
   */
  final void setNameAppendix(final String nameAddendix) {
    Validator.assertThat(nameAddendix).thatIsNamed("instance name").isNotBlank();

    assertDoesNotHaveNameAddendum();

    this.instanceAddendix = nameAddendix;
  }

  /**
   * Sets the parent {@link AbstractServer} of the current {@link AbstractApplication}.
   * 
   * @param parentServer
   * @throws RuntimeException if the current {@link AbstractApplication} belongs already
   *                          to a {@link AbstractServer}.
   */
  final void setParentServer(final AbstractServer<?> parentServer) {
    Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();
    assertDoesNotBelongToServer();

    this.parentServer = parentServer;
  }

  /**
   * @throws RuntimeException if the current {@link AbstractApplication} does not belong
   *                          to a {@link AbstractServer}.
   */
  private void assertBelongsToServer() {
    if (!belongsToServer()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, AbstractServer.class);
    }
  }

  /**
   * @throws RuntimeException if the current {@link AbstractApplication} belongs already
   *                          to a {@link AbstractServer}.
   */
  private void assertDoesNotBelongToServer() {
    if (belongsToServer()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentServer());
    }
  }

  /**
   * @throws RuntimeException if the current {@link AbstractApplication} has already an
   *                          instance name.
   */
  private void assertDoesNotHaveNameAddendum() {
    if (hasInstanceAppendix()) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, "instance name");
    }
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractApplication} does not
   *                                               have a name addendum.
   */
  private void assertHasNameAddendum() {
    if (!hasInstanceAppendix()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "name addendum");
    }
  }

  /**
   * @param serverTarget
   * @return the current {@link AbstractApplication} as target using the given
   *         serverTarget.
   */
  private IApplicationInstanceTarget asTargetWithServerTarget(final IServerTarget serverTarget) {
    return ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        serverTarget.getIpOrDomain(),
        serverTarget.getPort(),
        getInstanceName(),
        getUrlInstanceName(),
        serverTarget.getSecurityModeForConnection());
  }

  /**
   * @param endPoint
   * @return a new {@link AbstractBackendClient} with the given endPoint
   */
  private C createBackendClientWithEndPoint(final IEndPoint endPoint) {
    final C backendClient = ReflectionTool.createInstanceFromDefaultConstructorOfClass(getClientClass());
    backendClient.setEndPoint(endPoint);

    return backendClient;
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
   * @return the parent {@link AbstractServer} of the current {@link AbstractApplication}
   * @throws RuntimeException if the current {@link AbstractApplication} does not belong
   *                          to a {@link AbstractServer}.
   */
  private AbstractServer<?> getStoredParentServer() {
    assertBelongsToServer();

    return parentServer;
  }

  /**
   * Removes the closed {@link AbstractClient}s of the current
   * {@link AbstractApplication}.
   */
  private void removeClosedClients() {
    clients.removeAll(AbstractClient::isClosed);
  }
}
