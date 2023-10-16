//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.programcontrolapi.targetapi.IServerTarget;

//class
/**
 * A {@link BaseServer} can contain {@link Application}s. A {@link BaseServer}
 * is closable.
 * 
 * @param <SR> is the type of a {@link BaseServer}.
 * @author Silvan Wyss
 * @date 2016-11-01
 */
public abstract class BaseServer<SR extends BaseServer<SR>> implements GroupCloseable {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //optional attribute
  private Application<?, ?> defaultApplication;

  //multi-attribute
  private final LinkedList<Application<?, ?>> applications = new LinkedList<>();

  //method
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
  public final SR addApplication(final Application<?, ?> application) {

    application.internalSetParentServer(this);

    addApplicationToList(application);
    noteAddedApplication(application);

    return asConcrete();
  }

  //method
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
  public final SR addApplicationWithNameAddendum(final Application<?, ?> application, final String nameAddendum) {

    application.internalSetParentServer(this);
    application.internalSetNameAddendum(nameAddendum);

    addApplicationToList(application);
    noteAddedApplication(application);

    return asConcrete();
  }

  //method
  /**
   * Adds a new {@link Application} with the given instanceName,
   * initialSessionClass and applicationContext to the current {@link BaseServer}.
   * 
   * @param applicationName
   * @param initialSessionClass
   * @param applicationContext
   * @param <S>                 is the type of the given initialSessionClass.
   * @param <BC>                is the type of the {@link BackendClient} of the
   *                            given initialSessionClass.
   * @param <AC>                is the type of the given applicationContext.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException  if the given instanceName is null.
   * @throws InvalidArgumentException if the given instanceName is blank.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with the given
   *                                  instanceName.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <S extends Session<BC, AC>, BC extends BackendClient<BC, AC>, AC> SR//
      addApplicationWithNameAndInitialSessionClassAndContext(
          final String applicationName,
          final Class<S> initialSessionClass,
          final AC applicationContext) {

    //Creates Application.
    final var application = BasicApplication.withNameAndInitialSessionClassAndContext(
        applicationName,
        initialSessionClass,
        applicationContext);

    //Calls other method.
    return addApplication(application);
  }

  //method
  /**
   * Adds a new {@link Application} with the given name, initialSessionClass and a
   * void context to the current {@link BaseServer}.
   * 
   * @param name
   * @param initialSessionClass
   * @param <S>                 is the type of the given initialSessionClass.
   * @param <BC>                is the type of the {@link BackendClient} of the
   *                            given initialSessionClass.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with an
   *                                  instanceName that equals the given name.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <S extends Session<BC, Object>, BC extends BackendClient<BC, Object>> SR //
      addApplicationWithNameAndInitialSessionClassAndVoidContext(
          final String name,
          final Class<S> initialSessionClass) {

    //Creates Application.
    final var application = BasicApplication.withNameAndInitialSessionClassAndContext(
        name,
        initialSessionClass,
        new VoidObject());

    //Calls other method.
    return addApplication(application);
  }

  //method
  /**
   * Adds the given defaultApplication to the current {@link BaseServer}. A
   * default {@link Application} takes all {@link Client}s that do not have a
   * target.
   * 
   * @param defaultApplication
   * @param <BC>               is the type of the {@link BackendClient} of the
   *                           given defaultApplication.
   * @param <AC>               is the type of the context of the given
   *                           defaultApplication.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException if the given defaultApplication is null.
   */
  public final <BC extends BackendClient<BC, AC>, AC> SR addDefaultApplication(
      final Application<BC, AC> defaultApplication) {

    defaultApplication.internalSetParentServer(this);

    addApplicationToList(defaultApplication);
    this.defaultApplication = defaultApplication;

    noteAddedDefaultApplication(defaultApplication);

    return asConcrete();
  }

  /**
   * Adds a new default {@link Application} with the given name,
   * initialSessionClass and applicationContext to the current {@link BaseServer}.
   * 
   * @param applicationName
   * @param initialSessionClass
   * @param applicationContext
   * @param <S>                 is the type of the given initialSessionClass.
   * @param <BC>                is the type of the {@link BackendClient} of the
   *                            given initialSessionClass.
   * @param <AC>                is the type of the given applicationContext.
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
  public final <S extends Session<BC, AC>, BC extends BackendClient<BC, AC>, AC> SR //
      addDefaultApplicationWithNameAndInitialSessionClassAndContext(
          final String applicationName,
          final Class<S> initialSessionClass,
          final AC applicationContext) {

    //Creates default Application.
    final var localDefaultApplication = BasicApplication.withNameAndInitialSessionClassAndContext(
        applicationName,
        initialSessionClass,
        applicationContext);

    //Calls other method.
    return addDefaultApplication(localDefaultApplication);
  }

  //method
  /**
   * Adds a new {@link Application} with the given name, initialSessionClass and a
   * void context as default {@link Application} the current {@link BaseServer}.
   * 
   * @param name
   * @param initialSessionClass
   * @param <S>                 is the type of the given initialSessionClass.
   * @param <BC>                is the type of the {@link BackendClient} of the
   *                            given initialSessionClass.
   * @return the current {@link BaseServer}.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link Application} with an
   *                                  instanceName that equals the given name.
   * @throws ArgumentIsNullException  if the given initialSessionClass is null.
   */
  public final <S extends Session<BC, Object>, BC extends BackendClient<BC, Object>> SR//
      addDefaultApplicationWithNameAndInitialSessionClassAndVoidContext(
          final String name,
          final Class<S> initialSessionClass) {

    //Creates a default Application.
    final var localDefaultApplication = BasicApplication.withNameAndInitialSessionClassAndContext(
        name,
        initialSessionClass,
        new VoidObject());

    //Calls other method.
    return addDefaultApplication(localDefaultApplication);
  }

  //method
  /**
   * @return the current {@link Server} as {@link IServerTarget}.
   */
  public abstract IServerTarget asTarget();

  //method
  /**
   * @return true if the current {@link BaseServer} contains a default
   *         {@link Application}.
   */
  public final boolean containsDefaultApplication() {
    return (defaultApplication != null);
  }

  //method
  /**
   * @param name
   * @return true if the current {@link BaseServer} contains a {@link Application}
   *         with the given name.
   */
  public final boolean containsApplicationWithName(final String name) {
    return applications.containsAny(a -> a.getInstanceName().equals(name));
  }

  //method
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

  //method
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

  //method
  /**
   * @return the {@link Application}s of the current {@link BaseServer}.
   */
  public final IContainer<Application<?, ?>> getStoredApplications() {
    return applications;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  //method
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

  //method
  /**
   * @return true if the current {@link BaseServer} has a {@link Client}
   *         connected.
   */
  public final boolean hasClientConnected() {
    return applications.containsAny(Application::hasClientConnected);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
  }

  //method
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

  //method declaration
  /**
   * @return the current {@link BaseServer}.
   */
  protected abstract SR asConcrete();

  //method declaration
  /**
   * Notes that the curent {@link BaseServer} has added the given application.
   * 
   * @param application
   */
  protected abstract void noteAddedApplication(Application<?, ?> application);

  //method declaration
  /**
   * Notes that the curent {@link BaseServer} has added the given
   * defaultApplication2.
   * 
   * @param defaultApplication2
   */
  protected abstract void noteAddedDefaultApplication(Application<?, ?> defaultApplication2);

  //method
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

  //method
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

  //method
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

  //method
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
}
