package ch.nolix.system.application.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;
import ch.nolix.systemapi.applicationapi.mainapi.IServer;

/**
 * A {@link BaseServer} can contain {@link Application}s. A {@link BaseServer}
 * is closable.
 * 
 * @param <S> is the type of a {@link BaseServer}.
 * @author Silvan Wyss
 * @version 2016-11-01
 */
public abstract class BaseServer<S extends BaseServer<S>> implements IServer {

  private final CloseController closeController = CloseController.forElement(this);

  private Application<?, ?> defaultApplication;

  private final LinkedList<Application<?, ?>> applications = LinkedList.createEmpty();

  /**
   * Adds the given application to the current {@link BaseServer}.
   * 
   * @param application
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException  if the given application is null.
   * @throws ArgumentIsNullException  if the given instanceName is null
   * @throws InvalidArgumentException if the given instanceName is blank.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with the given
   *                                  instanceName.
   */
  public final S addApplication(final Application<?, ?> application) {

    application.internalSetParentServer(this);

    addApplicationToList(application);
    noteAddedApplication(application);

    return asConcrete();
  }

  /**
   * Adds the given application with the given instanceName to the current
   * {@link BaseServer}.
   * 
   * @param application
   * @param nameAddendum
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException          if the given application is null.
   * @throws ArgumentBelongsToParentException if the given application belongs
   *                                          already to a {@link BaseServer}.
   * @throws ArgumentIsNullException          if the given instanceName is null
   * @throws InvalidArgumentException         if the given instanceName is blank.
   * @throws InvalidArgumentException         if the current {@link BaseServer}
   *                                          contains already a
   *                                          {@link Application} with the given
   *                                          instanceName.
   */
  public final S addApplicationWithNameAddendum(final Application<?, ?> application, final String nameAddendum) {

    application.internalSetParentServer(this);
    application.internalSetNameAddendum(nameAddendum);

    addApplicationToList(application);
    noteAddedApplication(application);

    return asConcrete();
  }

  /**
   * Adds a new {@link Application} with the given instanceName,
   * initialSessionClass and applicationService to the current {@link BaseServer}.
   * 
   * @param applicationName
   * @param initialSessionClass
   * @param applicationContext
   * @param <T>                 is the type of the given initialSessionClass.
   * @param <C>                 is the type of the {@link BackendClient} of the
   *                            given initialSessionClass.
   * @param <U>                 is the type of the given applicationContext.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException  if the given instanceName is null.
   * @throws InvalidArgumentException if the given instanceName is blank.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with the given
   *                                  instanceName.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <T extends Session<C, U>, C extends BackendClient<C, U>, U> S//
  addApplicationWithNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final U applicationContext) {

    //Creates Application.
    final var application = BasicApplication.withNameAndInitialSessionClassAndContext(
      applicationName,
      initialSessionClass,
      applicationContext);

    //Calls other method.
    return addApplication(application);
  }

  /**
   * Adds a new {@link Application} with the given name, initialSessionClass and a
   * void context to the current {@link BaseServer}.
   * 
   * @param name
   * @param initialSessionClass
   * @param <T>                 is the type of the given initialSessionClass.
   * @param <C>                 is the type of the {@link BackendClient} of the
   *                            given initialSessionClass.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with an
   *                                  instanceName that equals the given name.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <T extends Session<C, Object>, C extends BackendClient<C, Object>> S //
  addApplicationWithNameAndInitialSessionClassAndVoidContext(
    final String name,
    final Class<T> initialSessionClass) {

    //Creates Application.
    final var application = BasicApplication.withNameAndInitialSessionClassAndContext(
      name,
      initialSessionClass,
      new VoidObject());

    //Calls other method.
    return addApplication(application);
  }

  /**
   * Adds the given defaultApplication to the current {@link BaseServer}. A
   * default {@link Application} takes all {@link AbstractClient}s that do not have a
   * target.
   * 
   * @param defaultApplication
   * @param <C>                is the type of the {@link BackendClient} of the
   *                           given defaultApplication.
   * @param <U>                is the type of the context of the given
   *                           defaultApplication.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException if the given defaultApplication is null.
   */
  public final <C extends BackendClient<C, U>, U> S addDefaultApplication(
    final Application<C, U> defaultApplication) {

    defaultApplication.internalSetParentServer(this);

    addApplicationToList(defaultApplication);
    this.defaultApplication = defaultApplication;

    noteAddedDefaultApplication(defaultApplication);

    return asConcrete();
  }

  /**
   * Adds a new default {@link Application} with the given name,
   * initialSessionClass and applicationService to the current {@link BaseServer}.
   * 
   * @param applicationName
   * @param initialSessionClass
   * @param applicationContext
   * @param <T>                 is the type of the given initialSessionClass.
   * @param <C>                 is the type of the {@link BackendClient} of the
   *                            given initialSessionClass.
   * @param <U>                 is the type of the given applicationContext.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException  if the given instanceName is null.
   * @throws InvalidArgumentException if the given instanceName is blank.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a default {@link Application}.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with the given
   *                                  instanceName.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <T extends Session<C, U>, C extends BackendClient<C, U>, U> S //
  addDefaultApplicationWithNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final U applicationContext) {

    //Creates default Application.
    final var localDefaultApplication = BasicApplication.withNameAndInitialSessionClassAndContext(
      applicationName,
      initialSessionClass,
      applicationContext);

    //Calls other method.
    return addDefaultApplication(localDefaultApplication);
  }

  /**
   * Adds a new {@link Application} with the given name, initialSessionClass and a
   * void context as default {@link Application} the current {@link BaseServer}.
   * 
   * @param name
   * @param initialSessionClass
   * @param <T>                 is the type of the given initialSessionClass.
   * @param <C>                 is the type of the {@link BackendClient} of the
   *                            given initialSessionClass.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with an
   *                                  instanceName that equals the given name.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <T extends Session<C, Object>, C extends BackendClient<C, Object>> S//
  addDefaultApplicationWithNameAndInitialSessionClassAndVoidContext(
    final String name,
    final Class<T> initialSessionClass) {

    //Creates a default Application.
    final var localDefaultApplication = BasicApplication.withNameAndInitialSessionClassAndContext(
      name,
      initialSessionClass,
      new VoidObject());

    //Calls other method.
    return addDefaultApplication(localDefaultApplication);
  }

  @Override
  public final void clear() {
    getStoredApplications().forEach(this::removeApplication);
  }

  /**
   * @return true if the current {@link BaseServer} contains a default
   *         {@link Application}.
   */
  public final boolean containsDefaultApplication() {
    return (defaultApplication != null);
  }

  /**
   * @param name
   * @return true if the current {@link BaseServer} contains a {@link Application}
   *         with the given name.
   */
  public final boolean containsApplicationWithName(final String name) {
    return applications.containsAny(a -> a.getInstanceName().equals(name));
  }

  /**
   * @param instanceName
   * @return the {@link Application} with the given instanceName from the current
   *         {@link BaseServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link BaseServer} does not
   *                                               contain a {@link Application}
   *                                               with the given instanceName.
   */
  public final Application<?, ?> getStoredApplicationByInstanceName(final String instanceName) {
    return applications.getStoredFirst(a -> a.getInstanceName().equals(instanceName));
  }

  /**
   * @param urlInstanceName
   * @return the {@link Application} with the given urlInstanceName from the
   *         current {@link BaseServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link BaseServer} does not
   *                                               contain a {@link Application}
   *                                               with the given urlInstanceName.
   */
  public final Application<?, ?> getStoredApplicationByUrlInstanceName(final String urlInstanceName) {
    return applications.getStoredFirst(a -> a.getUrlInstanceName().equals(urlInstanceName));
  }

  /**
   * @return the {@link Application}s of the current {@link BaseServer}.
   */
  public final IContainer<Application<?, ?>> getStoredApplications() {
    return applications;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * @return the default {@link Application} of the current {@link BaseServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link BaseServer} does not
   *                                               contain a default
   *                                               {@link Application}.
   */
  public final Application<?, ?> getStoredDefaultApplication() {

    //Asserts that the current Server contains a default Application.
    assertContainsDefaultApplication();

    return defaultApplication;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEmpty() {
    return getStoredApplications().isEmpty();
  }

  /**
   * @return true if the current {@link BaseServer} has a {@link AbstractClient}
   *         connected.
   */
  public final boolean hasClientConnected() {
    return applications.containsAny(Application::hasClientConnected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeApplicationByInstanceName(final String instanceName) {

    final var application = getStoredApplicationByInstanceName(instanceName);

    removeApplication(application);
  }

  /**
   * Lets the current {@link BaseServer} take the given client.
   * 
   * @param client
   * @throws ArgumentDoesNotHaveAttributeException if the given client does not
   *                                               have a target and the current
   *                                               {@link BaseServer} does not
   *                                               contain a default
   *                                               {@link Application}.
   * @throws ArgumentDoesNotHaveAttributeException if the given client has a
   *                                               target and the current
   *                                               {@link BaseServer} does not
   *                                               contain a {@link Application}
   *                                               with a name that equals the
   *                                               given target.
   */
  public final void takeClient(final BackendClient<?, ?> client) {

    //Handles the case that the given client does not have a target.
    if (!client.hasTarget()) {
      getStoredDefaultApplication().takeClient(client);

      //Handles the case that the given client has a target.
    } else {
      getStoredApplicationByUrlInstanceName(client.getTarget()).takeClient(client);
    }
  }

  /**
   * @return the current {@link BaseServer}.
   */
  protected abstract S asConcrete();

  /**
   * Notes that the given application has been added to the current
   * {@link BaseServer}.
   * 
   * @param application
   */
  protected abstract void noteAddedApplication(Application<?, ?> application);

  /**
   * Notes that the given defaultApplication has been added to the current
   * {@link BaseServer}.
   * 
   * @param defaultApplication
   */
  protected abstract void noteAddedDefaultApplication(Application<?, ?> defaultApplication);

  /**
   * Notes that the given application has been removed fromt the current
   * {@link BaseServer}.
   * 
   * @param application
   */
  protected abstract void noteRemovedApplication(IApplication<?> application);

  /**
   * Lets the current {@link Server} take the given endPoint.
   * 
   * @param endPoint
   */
  void internalTakeEndPoint(final IEndPoint endPoint) {

    //Handles the case that the given endPoint does not have a target.
    if (!endPoint.hasCustomTargetSlot()) {
      getStoredDefaultApplication().takeEndPoint(endPoint);

      //Handles the case that the given endPoint has a target.
    } else {
      getStoredApplicationByUrlInstanceName(endPoint.getCustomTargetSlot()).takeEndPoint(endPoint);
    }
  }

  /**
   * Adds the given application to the list of {@link Application}s of the current
   * {@link BaseServer}.
   * 
   * @param application
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with the same
   *                                  name as one of the given applications.
   */
  private void addApplicationToList(final Application<?, ?> application) {

    //Asserts that the current Server does not contain already
    //an Application with the same name as the given application..
    assertDoesNotContainApplicationWithName(application.getInstanceName());

    //Adds the given application to the list of Applications of the current
    //BaseServer.
    applications.addAtEnd(application);
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link BaseServer} does not
   *                                               contain a default
   *                                               {@link Application}.
   */
  private void assertContainsDefaultApplication() {
    if (!containsDefaultApplication()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "default Application");
    }
  }

  /**
   * @param name
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with the same
   *                                  name as one of the given applications.
   */
  private void assertDoesNotContainApplicationWithName(final String name) {
    if (containsApplicationWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already an Application with the name '" + name + "'");
    }
  }

  /**
   * Removes the given application from the current {@link BaseServer}
   * 
   * @param application
   * @throws ArgumentDoesNotContainElementException if the current
   *                                                {@link BaseServer} does not
   *                                                contain the given application.
   */
  private void removeApplication(final IApplication<?> application) {

    applications.removeStrictlyFirstOccurrenceOf(application);

    if (application == defaultApplication) {
      defaultApplication = null;
    }

    noteRemovedApplication(application);
  }
}
