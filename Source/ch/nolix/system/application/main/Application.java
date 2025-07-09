package ch.nolix.system.application.main;

import java.util.Locale;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.target.ApplicationInstanceTarget;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.core.structurecontrol.reflectiontool.ClassTool;
import ch.nolix.core.structurecontrol.reflectiontool.ReflectionTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <C> is the type of the {@link AbstractBackendClient}s of a
 *            {@link Application}.
 * @param <S> is the type of the application context of a {@link Application}.
 */
public abstract class Application //NOSONAR: An application class is expected to be abstract.
<C extends AbstractBackendClient<C, S>, S>
implements IApplication<S> {

  private static final ClassTool CLASS_TOOL = new ClassTool();

  private final S applicationService;

  private String nameAddendum;

  private AbstractServer<?> parentServer;

  private final LinkedList<C> clients = LinkedList.createEmpty();

  /**
   * Creates a new {@link Application} with the given applicationService.
   * 
   * @param applicationService
   * @throws ArgumentIsNullException if the given applicationService is null.
   */
  protected Application(final S applicationService) {

    Validator.assertThat(applicationService).thatIsNamed("application context").isNotNull();

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
   * @return the class of the {@link AbstractClient}s of the current
   *         {@link Application}.
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
   * @return the {@link AbstractClient}s of the current {@link Application}.
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
    return getInstanceName().replace(StringCatalog.SPACE, StringCatalog.UNDERSCORE).toLowerCase(Locale.ENGLISH);
  }

  /**
   * @return true if the current {@link Application} has a {@link AbstractClient}
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
  public final void takeClient(final AbstractBackendClient<?, ?> client) {
    final var localClient = (C) client;
    localClient.internalSetParentApplication(this);
    clients.addAtEnd(localClient);
    FlowController.runInBackground(() -> localClient.internalPush(createInitialSession()));
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
   * @return a new initial {@link AbstractSession} for a {@link AbstractClient} of
   *         the current {@link Application}.
   */
  @SuppressWarnings("unchecked")
  protected final AbstractSession<C, S> createInitialSession() {
    return (AbstractSession<C, S>) CLASS_TOOL.createInstanceFromDefaultConstructorOfClass(getInitialSessionClass());
  }

  /**
   * @return the initial {@link AbstractSession} class of the current
   *         {@link Application}.
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

    Validator.assertThat(nameAddendum).thatIsNamed("instance name").isNotBlank();

    assertDoesNotHaveNameAddendum();

    this.nameAddendum = nameAddendum;
  }

  /**
   * Sets the parent {@link AbstractServer} of the current {@link Application}.
   * 
   * @param parentServer
   * @throws ArgumentBelongsToParentException if the current {@link Application}
   *                                          belongs already to a
   *                                          {@link AbstractServer}.
   */
  final void internalSetParentServer(final AbstractServer<?> parentServer) {

    Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();
    assertDoesNotBelongToServer();

    this.parentServer = parentServer;
  }

  /**
   * @throws ArgumentDoesNotBelongToParentException if the current
   *                                                {@link Application} does not
   *                                                belong to a
   *                                                {@link AbstractServer}.
   */
  private void assertBelongsToServer() {
    if (!belongsToServer()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, AbstractServer.class);
    }
  }

  /**
   * @throws ArgumentBelongsToParentException if the current {@link Application}
   *                                          belongs already to a
   *                                          {@link AbstractServer}.
   */
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
   * @return a new {@link AbstractBackendClient} with the given endPoint
   */
  private C createBackendClientWithEndPoint(final IEndPoint endPoint) {

    final C backendClient = ReflectionTool.createInstanceFromDefaultConstructorOfClass(getClientClass());
    backendClient.internalSetEndPoint(endPoint);

    return backendClient;
  }

  /**
   * @return the parent {@link AbstractServer} of the current {@link Application}.
   * @throws ArgumentDoesNotBelongToParentException if the current
   *                                                {@link Application} does not
   *                                                belong to a
   *                                                {@link AbstractServer}.
   */
  private AbstractServer<?> getStoredParentServer() {

    assertBelongsToServer();

    return parentServer;
  }

  /**
   * Removes the closed {@link AbstractClient}s of the current
   * {@link Application}.
   */
  private void removeClosedClients() {
    clients.removeAll(AbstractClient::isClosed);
  }
}
