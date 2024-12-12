package ch.nolix.system.application.main;

import java.util.Locale;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.target.ApplicationInstanceTarget;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.reflection.ClassTool;
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <C> is the type of the {@link BackendClient}s of a
 *             {@link Application}.
 * @param <S> is the type of the application context of a {@link Application}.
 */
public abstract class Application<C extends BackendClient<C, S>, S> implements IApplication<S> {

  private static final ClassTool CLASS_TOOL = new ClassTool();

  private final S applicationService;

  private String nameAddendum;

  private BaseServer<?> parentServer;

  private final LinkedList<C> clients = LinkedList.createEmpty();

  /**
   * Creates a new {@link Application} with the given applicationContext.
   * 
   * @param applicationService
   * @throws ArgumentIsNullException if the given applicationService is null.
   */
  protected Application(final S applicationService) {

    GlobalValidator.assertThat(applicationService).thatIsNamed("application context").isNotNull();

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
   * @return the class of the {@link Client}s of the current {@link Application}.
   */
  @SuppressWarnings("unchecked")
  public final Class<C> getClientClass() {
    return (Class<C>) (createInitialSession().getClientClass());
  }

  /**
   * @return the instance name of the current {@link Application}.
   */
  @Override
  public final String getInstanceName() {

    if (!hasNameAddendum()) {
      return getApplicationName();
    }

    return String.format("%s %s", getApplicationName(), getNameAddendum());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getNameAddendum() {

    assertHasNameAddendum();

    return nameAddendum;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S getStoredApplicationService() {
    return applicationService;
  }

  /**
   * @return the {@link Client}s of the current {@link Application}.
   */
  public final IContainer<C> getStoredClients() {

    removeClosedClients();

    return clients;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getUrlInstanceName() {
    return getInstanceName().replace(StringCatalogue.SPACE, StringCatalogue.UNDERSCORE).toLowerCase(Locale.ENGLISH);
  }

  /**
   * @return true if the current {@link Application} has a {@link Client}
   *         connected.
   */
  public final boolean hasClientConnected() {
    return getStoredClients().containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasNameAddendum() {
    return (nameAddendum != null);
  }

  /**
   * Lets the current {@link Application} take the given client.
   * 
   * @param client
   */
  @SuppressWarnings("unchecked")
  public final void takeClient(final BackendClient<?, ?> client) {
    final C lClient = ((C) client);
    lClient.internalSetParentApplication(this);
    clients.addAtEnd(lClient);
    GlobalSequencer.runInBackground(() -> lClient.internalPush(createInitialSession()));
  }

  /**
   * Lets the current {@link Application} take the given endPoint.
   * 
   * @param endPoint
   */
  public final void takeEndPoint(final IEndPoint endPoint) {
    takeClient(createBackendClientWithEndPoint(endPoint));
  }

  /**
   * @return a new initial {@link Session} for a {@link Client} of the current
   *         {@link Application}.
   */
  @SuppressWarnings("unchecked")
  protected final Session<C, S> createInitialSession() {
    return (Session<C, S>) CLASS_TOOL.createInstanceFromDefaultConstructorOf(getInitialSessionClass());
  }

  /**
   * @return the initial {@link Session} class of the current {@link Application}.
   */
  protected abstract Class<?> getInitialSessionClass();

  /**
   * Sets the given nameAddendum to the current {@link Application}.
   * 
   * @param nameAddendum
   * @throws ArgumentIsNullException       if the given nameAddendum is null
   * @throws InvalidArgumentException      if the given nameAddendum is blank.
   * @throws ArgumentHasAttributeException if the current {@link Application} has
   *                                       already an instance name.
   */
  final void internalSetNameAddendum(final String nameAddendum) {

    GlobalValidator.assertThat(nameAddendum).thatIsNamed("instance name").isNotBlank();

    assertDoesNotHaveNameAddendum();

    this.nameAddendum = nameAddendum;
  }

  /**
   * Sets the parent {@link BaseServer} of the current {@link Application}.
   * 
   * @param parentServer
   * @throws ArgumentBelongsToParentException if the current {@link Application}
   *                                          belongs already to a
   *                                          {@link BaseServer}.
   */
  final void internalSetParentServer(final BaseServer<?> parentServer) {

    GlobalValidator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();
    assertDoesNotBelongToServer();

    this.parentServer = parentServer;
  }

  /**
   * @throws ArgumentDoesNotBelongToParentException if the current
   *                                                {@link Application} does not
   *                                                belong to a
   *                                                {@link BaseServer}.
   */
  private void assertBelongsToServer() {
    if (!belongsToServer()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, BaseServer.class);
    }
  }

  /**
   * @throws ArgumentBelongsToParentException if the current {@link Application}
   *                                          belongs already to a
   *                                          {@link BaseServer}.
   */
  @SuppressWarnings("resource")
  private void assertDoesNotBelongToServer() {
    if (belongsToServer()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentServer());
    }
  }

  /**
   * @throws ArgumentHasAttributeException if the current {@link Application} has
   *                                       already an instance name.
   */
  private void assertDoesNotHaveNameAddendum() {
    if (hasNameAddendum()) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, "instance name");
    }
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link Application} does not
   *                                               have a name addendum.
   */
  private void assertHasNameAddendum() {
    if (!hasNameAddendum()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "name addendum");
    }
  }

  /**
   * @param serverTarget
   * @return the current {@link Application} as target using the given
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
   * @return a new {@link BackendClient} with the given endPoint
   */
  private C createBackendClientWithEndPoint(final IEndPoint endPoint) {

    final C backendClient = GlobalReflectionTool.createInstanceFromDefaultConstructorOfClass(getClientClass());
    backendClient.internalSetEndPoint(endPoint);

    return backendClient;
  }

  /**
   * @return the parent {@link BaseServer} of the current {@link Application}.
   * @throws ArgumentDoesNotBelongToParentException if the current
   *                                                {@link Application} does not
   *                                                belong to a
   *                                                {@link BaseServer}.
   */
  private BaseServer<?> getStoredParentServer() {

    assertBelongsToServer();

    return parentServer;
  }

  /**
   * Removes the closed {@link Client}s of the current {@link Application}.
   */
  private void removeClosedClients() {
    clients.removeAll(Client::isClosed);
  }
}
