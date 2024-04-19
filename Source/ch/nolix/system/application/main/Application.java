//package declaration
package ch.nolix.system.application.main;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.target.ApplicationInstanceTarget;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programcontrolapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.coreapi.programcontrolapi.targetapi.IServerTarget;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <BC> is the type of the {@link BackendClient}s of a
 *             {@link Application}.
 * @param <AC> is the type of the application context of a {@link Application}.
 */
public abstract class Application<BC extends BackendClient<BC, AC>, AC> implements IApplication<AC> {

  //attribute
  private final AC applicationContext;

  //optional attribute
  private String nameAddendum;

  //optional attribute
  private BaseServer<?> parentServer;

  //multi-attribute
  private final LinkedList<BC> clients = new LinkedList<>();

  //constructor
  /**
   * Creates a new {@link Application} with the given applicationContext.
   * 
   * @param applicationContext
   * @throws ArgumentIsNullException if the given applicationContext is null.
   */
  protected Application(final AC applicationContext) {

    GlobalValidator.assertThat(applicationContext).thatIsNamed("application context").isNotNull();

    this.applicationContext = applicationContext;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final IApplicationInstanceTarget asTarget() {

    final var serverTarget = getStoredParentServer().asTarget();

    return asTargetWithServerTarget(serverTarget);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean belongsToServer() {
    return (parentServer != null);
  }

  //method
  /**
   * @return the class of the {@link Client}s of the current {@link Application}.
   */
  @SuppressWarnings("unchecked")
  public final Class<BC> getClientClass() {
    return (Class<BC>) (createInitialSession().getClientClass());
  }

  //method
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String getNameAddendum() {

    assertHasNameAddendum();

    return nameAddendum;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final AC getStoredApplicationContext() {
    return applicationContext;
  }

  //method
  /**
   * @return the {@link Client}s of the current {@link Application}.
   */
  public final IContainer<BC> getStoredClients() {

    removeClosedClients();

    return clients;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String getUrlInstanceName() {
    return getInstanceName().replace(StringCatalogue.SPACE, StringCatalogue.UNDERSCORE).toLowerCase(Locale.ENGLISH);
  }

  //method
  /**
   * @return true if the current {@link Application} has a {@link Client}
   *         connected.
   */
  public final boolean hasClientConnected() {
    return getStoredClients().containsAny();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasNameAddendum() {
    return (nameAddendum != null);
  }

  //method
  /**
   * Lets the current {@link Application} take the given client.
   * 
   * @param client
   */
  @SuppressWarnings("unchecked")
  public final void takeClient(final BackendClient<?, ?> client) {
    final var lClient = ((BC) client);
    lClient.internalSetParentApplication(this);
    clients.addAtEnd(lClient);
    GlobalSequencer.runInBackground(() -> lClient.internalPush(createInitialSession()));
  }

  //method
  /**
   * Lets the current {@link Application} take the given endPoint.
   * 
   * @param endPoint
   */
  public final void takeEndPoint(final IEndPoint endPoint) {
    takeClient(createBackendClientWithEndPoint(endPoint));
  }

  //method
  /**
   * @return a new initial {@link Session} for a {@link Client} of the current
   *         {@link Application}.
   */
  @SuppressWarnings("unchecked")
  protected final Session<BC, AC> createInitialSession() {
    try {
      return (Session<BC, AC>) getInitialSessionConstructor().newInstance();
    } catch (final
    InstantiationException
    | IllegalAccessException
    | IllegalArgumentException
    | InvocationTargetException exception) {
      throw WrapperException.forError(exception);
    }
  }

  //method
  /**
   * @return the initial {@link Session} class of the current {@link Application}.
   */
  protected abstract Class<?> getInitialSessionClass();

  //method
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

  //method
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

  //method
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

  //method
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

  //method
  /**
   * @throws ArgumentHasAttributeException if the current {@link Application} has
   *                                       already an instance name.
   */
  private void assertDoesNotHaveNameAddendum() {
    if (hasNameAddendum()) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, "instance name");
    }
  }

  //method
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

  //method
  /**
   * @param serverTarget
   * @return the current {@link Application} as target using the given
   *         serverTarget.
   */
  private IApplicationInstanceTarget asTargetWithServerTarget(final IServerTarget serverTarget) {
    return ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityLevelForConnections(
        serverTarget.getIpOrDomain(),
        serverTarget.getPort(),
        getInstanceName(),
        getUrlInstanceName(),
        serverTarget.getSecurityLevelForConnections());
  }

  //method
  /**
   * @param endPoint
   * @return a new {@link BackendClient} with the given endPoint
   */
  private BC createBackendClientWithEndPoint(final IEndPoint endPoint) {

    final var backendClient = GlobalClassTool.createInstanceFromDefaultConstructorOf(getClientClass());
    backendClient.internalSetEndPoint(endPoint);

    return backendClient;
  }

  //method
  /**
   * @return the constructor of the initial {@link Session} class of the current
   *         {@link Application}.
   */
  private Constructor<?> getInitialSessionConstructor() {
    final var constructor = getInitialSessionClass().getDeclaredConstructors()[0];
    constructor.setAccessible(true);
    return constructor;
  }

  //method
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

  //method
  /**
   * Removes the closed {@link Client}s of the current {@link Application}.
   */
  private void removeClosedClients() {
    clients.removeAll(Client::isClosed);
  }
}
