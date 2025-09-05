package ch.nolix.system.application.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.misc.dataobject.VoidObject;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.net.endpoint3.IEndPoint;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.application.main.IApplication;
import ch.nolix.systemapi.application.main.IServer;

/**
 * A {@link AbstractServer} can contain {@link Application}s. A
 * {@link AbstractServer} is closable.
 * 
 * @param <S> is the type of a {@link AbstractServer}.
 * @author Silvan Wyss
 * @version 2016-11-01
 */
public abstract class AbstractServer<S extends AbstractServer<S>> implements IServer<S> {
  private final ICloseController closeController = CloseController.forElement(this);

  private Application<?, ?> defaultApplication;

  private final ILinkedList<Application<?, ?>> applications = LinkedList.createEmpty();

  /**
   * {@inheritDoc}
   */
  @Override
  public final S addApplication(final IApplication<?, ?> application) {
    final var localApplication = (Application<?, ?>) application;

    localApplication.setParentServer(this);

    addApplicationToList(localApplication);
    noteAddedApplication(localApplication);

    return asConcrete();
  }

  /**
   * Adds the given application with the given instanceName to the current
   * {@link AbstractServer}.
   * 
   * @param application
   * @param nameAddendum
   * @return the current {@link AbstractServer}.
   * @throws ArgumentIsNullException          if the given application is null.
   * @throws ArgumentBelongsToParentException if the given application belongs
   *                                          already to a {@link AbstractServer}.
   * @throws ArgumentIsNullException          if the given instanceName is null
   * @throws InvalidArgumentException         if the given instanceName is blank.
   * @throws InvalidArgumentException         if the current
   *                                          {@link AbstractServer} contains
   *                                          already a {@link Application} with
   *                                          the given instanceName.
   */
  public final S addApplicationWithNameAddendum(final Application<?, ?> application, final String nameAddendum) {
    application.setParentServer(this);
    application.setNameAppendix(nameAddendum);

    addApplicationToList(application);
    noteAddedApplication(application);

    return asConcrete();
  }

  /**
   * Adds a new {@link Application} with the given instanceName,
   * initialSessionClass and applicationService to the current
   * {@link AbstractServer}.
   * 
   * @param applicationName
   * @param initialSessionClass
   * @param applicationService
   * @param <T>                 is the type of the given initialSessionClass.
   * @param <C>                 is the type of the {@link AbstractBackendClient}
   *                            of the given initialSessionClass.
   * @param <U>                 is the type of the given applicationService.
   * @return the current {@link AbstractServer}.
   * @throws ArgumentIsNullException  if the given instanceName is null.
   * @throws InvalidArgumentException if the given instanceName is blank.
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a {@link Application} with
   *                                  the given instanceName.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <T extends AbstractSession<C, U>, C extends AbstractBackendClient<C, U>, U> S//
  addApplicationWithNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final U applicationService) {
    //Creates Application.
    final var application = BasicApplication.withNameAndInitialSessionClassAndContext(
      applicationName,
      initialSessionClass,
      applicationService);

    //Calls other method.
    return addApplication(application);
  }

  /**
   * Adds a new {@link Application} with the given name, initialSessionClass and a
   * void context to the current {@link AbstractServer}.
   * 
   * @param name
   * @param initialSessionClass
   * @param <T>                 is the type of the given initialSessionClass.
   * @param <C>                 is the type of the {@link AbstractBackendClient}
   *                            of the given initialSessionClass.
   * @return the current {@link AbstractServer}.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a {@link Application} with
   *                                  an instanceName that equals the given name.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <T extends AbstractSession<C, Object>, C extends AbstractBackendClient<C, Object>> S //
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
   * Adds the given defaultApplication to the current {@link AbstractServer}. A
   * default {@link Application} takes all {@link AbstractClient}s that do not
   * have a target.
   * 
   * @param defaultApplication
   * @param <C>                is the type of the {@link AbstractBackendClient} of
   *                           the given defaultApplication.
   * @param <U>                is the type of the context of the given
   *                           defaultApplication.
   * @return the current {@link AbstractServer}.
   * @throws ArgumentIsNullException if the given defaultApplication is null.
   */
  public final <C extends AbstractBackendClient<C, U>, U> S addDefaultApplication(
    final Application<C, U> defaultApplication) {
    defaultApplication.setParentServer(this);

    addApplicationToList(defaultApplication);
    this.defaultApplication = defaultApplication;

    noteAddedDefaultApplication(defaultApplication);

    return asConcrete();
  }

  /**
   * Adds a new default {@link Application} with the given name,
   * initialSessionClass and applicationService to the current
   * {@link AbstractServer}.
   * 
   * @param applicationName
   * @param initialSessionClass
   * @param applicationService
   * @param <T>                 is the type of the given initialSessionClass.
   * @param <C>                 is the type of the {@link AbstractBackendClient}
   *                            of the given initialSessionClass.
   * @param <U>                 is the type of the given applicationService.
   * @return the current {@link AbstractServer}.
   * @throws ArgumentIsNullException  if the given instanceName is null.
   * @throws InvalidArgumentException if the given instanceName is blank.
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a default
   *                                  {@link Application}.
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a {@link Application} with
   *                                  the given instanceName.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <T extends AbstractSession<C, U>, C extends AbstractBackendClient<C, U>, U> S //
  addDefaultApplicationWithNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final U applicationService) {
    //Creates default Application.
    final var localDefaultApplication = BasicApplication.withNameAndInitialSessionClassAndContext(
      applicationName,
      initialSessionClass,
      applicationService);

    //Calls other method.
    return addDefaultApplication(localDefaultApplication);
  }

  /**
   * Adds a new {@link Application} with the given name, initialSessionClass and a
   * void context as default {@link Application} the current
   * {@link AbstractServer}.
   * 
   * @param name
   * @param initialSessionClass
   * @param <T>                 is the type of the given initialSessionClass.
   * @param <C>                 is the type of the {@link AbstractBackendClient}
   *                            of the given initialSessionClass.
   * @return the current {@link AbstractServer}.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a {@link Application} with
   *                                  an instanceName that equals the given name.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <T extends AbstractSession<C, Object>, C extends AbstractBackendClient<C, Object>> S//
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
   * @return true if the current {@link AbstractServer} contains a default
   *         {@link Application}.
   */
  public final boolean containsDefaultApplication() {
    return (defaultApplication != null);
  }

  /**
   * @param name
   * @return true if the current {@link AbstractServer} contains a
   *         {@link Application} with the given name.
   */
  public final boolean containsApplicationWithName(final String name) {
    return applications.containsAny(a -> a.getInstanceName().equals(name));
  }

  /**
   * @param instanceName
   * @return the {@link Application} with the given instanceName from the current
   *         {@link AbstractServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a {@link Application}
   *                                               with the given instanceName.
   */
  public final Application<?, ?> getStoredApplicationByInstanceName(final String instanceName) {
    return applications.getStoredFirst(a -> a.getInstanceName().equals(instanceName));
  }

  /**
   * @param urlInstanceName
   * @return the {@link Application} with the given urlInstanceName from the
   *         current {@link AbstractServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a {@link Application}
   *                                               with the given urlInstanceName.
   */
  public final Application<?, ?> getStoredApplicationByUrlInstanceName(final String urlInstanceName) {
    return applications.getStoredFirst(a -> a.getUrlInstanceName().equals(urlInstanceName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends IApplication<?, ?>> getStoredApplications() {
    return applications;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * @return the default {@link Application} of the current
   *         {@link AbstractServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
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
   * @return true if the current {@link AbstractServer} has a
   *         {@link AbstractClient} connected.
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
  public final void removeApplicationWithInstanceName(final String instanceName) {
    final var application = getStoredApplicationByInstanceName(instanceName);

    removeApplication(application);
  }

  /**
   * Lets the current {@link AbstractServer} take the given client.
   * 
   * @param client
   * @throws ArgumentDoesNotHaveAttributeException if the given client does not
   *                                               have a target and the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default
   *                                               {@link Application}.
   * @throws ArgumentDoesNotHaveAttributeException if the given client has a
   *                                               target and the current
   *                                               {@link AbstractServer} does not
   *                                               contain a {@link Application}
   *                                               with a name that equals the
   *                                               given target.
   */
  public final void takeClient(final AbstractBackendClient<?, ?> client) {
    //Handles the case that the given client does not have a target.
    if (!client.hasUrlInstanceNameOfTargetApplication()) {
      getStoredDefaultApplication().takeClient(client);

      //Handles the case that the given client has a target.
    } else {
      final var targetApplicaitonUrlInstanceName = client.getUrlInstanceNameOfTargetApplication();

      getStoredApplicationByUrlInstanceName(targetApplicaitonUrlInstanceName).takeClient(client);
    }
  }

  /**
   * @return the current {@link AbstractServer}.
   */
  protected abstract S asConcrete();

  /**
   * Notes that the given application has been added to the current
   * {@link AbstractServer}.
   * 
   * @param application
   */
  protected abstract void noteAddedApplication(Application<?, ?> application);

  /**
   * Notes that the given defaultApplication has been added to the current
   * {@link AbstractServer}.
   * 
   * @param defaultApplication
   */
  protected abstract void noteAddedDefaultApplication(Application<?, ?> defaultApplication);

  /**
   * Notes that the given application has been removed fromt the current
   * {@link AbstractServer}.
   * 
   * @param application
   */
  protected abstract void noteRemovedApplication(IApplication<?, ?> application);

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
   * {@link AbstractServer}.
   * 
   * @param application
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a {@link Application} with
   *                                  the same name as one of the given
   *                                  applications.
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
   *                                               {@link AbstractServer} does not
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
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a {@link Application} with
   *                                  the same name as one of the given
   *                                  applications.
   */
  private void assertDoesNotContainApplicationWithName(final String name) {
    if (containsApplicationWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already an Application with the name '" + name + "'");
    }
  }

  /**
   * Removes the given application from the current {@link AbstractServer}
   * 
   * @param application
   * @throws ArgumentDoesNotContainElementException if the current
   *                                                {@link AbstractServer} does
   *                                                not contain the given
   *                                                application.
   */
  private void removeApplication(final IApplication<?, ?> application) {
    applications.removeStrictlyFirstOccurrenceOf(application);

    if (application == defaultApplication) {
      defaultApplication = null;
    }

    noteRemovedApplication(application);
  }
}
