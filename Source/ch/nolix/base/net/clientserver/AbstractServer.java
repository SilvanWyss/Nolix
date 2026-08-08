/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.datamodel.dataobject.VoidObject;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.net.clientserver.Application;
import ch.nolix.baseapi.net.clientserver.IServer;
import ch.nolix.baseapi.net.executoranddataproviderserver.EndPoint;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;

/**
 * A {@link AbstractServer} can contain {@link AbstractApplication}s. A
 * {@link AbstractServer} is closable.
 * 
 * @param <S> the type of a {@link AbstractServer}.
 * @author Silvan Wyss
 */
public abstract class AbstractServer<S extends AbstractServer<S>> implements IServer<S> {
  private final ICloseController closeController = CloseController.forElement(this);

  private AbstractApplication<?, ?> memberDefaultApplication;

  private final ILinkedList<AbstractApplication<?, ?>> abstractApplications = LinkedList.createEmpty();

  /**
   * {@inheritDoc}
   */
  @Override
  public final S addApplication(final Application<?, ?> application) {
    final var localApplication = (AbstractApplication<?, ?>) application;

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
   * @return the current {@link AbstractServer}
   * @throws RuntimeException if the given application is null
   * @throws RuntimeException if the given application belongs already to a
   *                          {@link AbstractServer}
   * @throws RuntimeException if the given instanceName is null
   * @throws RuntimeException if the given instanceName is blank
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link AbstractApplication} with the given
   *                          instanceName.
   */
  public final S addApplicationWithNameAddendum(
    final AbstractApplication<?, ?> application,
    final String nameAddendum) {
    application.setParentServer(this);
    application.setNameAppendix(nameAddendum);

    addApplicationToList(application);
    noteAddedApplication(application);

    return asConcrete();
  }

  /**
   * Adds a new {@link AbstractApplication} with the given instanceName,
   * initialSessionClass and applicationService to the current
   * {@link AbstractServer}.
   * 
   * @param applicationName
   * @param initialSessionClass
   * @param applicationService
   * @param <T>                 the type of the given initialSessionClass
   * @param <C>                 the type of the {@link AbstractBackendClient} of
   *                            the given initialSessionClass
   * @param <U>                 the type of the given applicationService
   * @return the current {@link AbstractServer}
   * @throws RuntimeException if the given instanceName is null
   * @throws RuntimeException if the given instanceName is blank
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link AbstractApplication} with the given
   *                          instanceName
   * @throws RuntimeException if the given initialSessionClass is null
   */
  public final <T extends AbstractSession<C, U>, C extends AbstractBackendClient<C, U>, U> S//
  addApplicationWithNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final U applicationService) {
    // Creates Application.
    final var application = BasicApplication.withNameAndInitialSessionClassAndContext(
      applicationName,
      initialSessionClass,
      applicationService);

    // Calls other method.
    return addApplication(application);
  }

  /**
   * Adds a new {@link AbstractApplication} with the given name,
   * initialSessionClass and a void context to the current {@link AbstractServer}.
   * 
   * @param name
   * @param initialSessionClass
   * @param <T>                 the type of the given initialSessionClass
   * @param <C>                 the type of the {@link AbstractBackendClient} of
   *                            the given initialSessionClass
   * @return the current {@link AbstractServer}
   * @throws RuntimeException if the given name is null
   * @throws RuntimeException if the given name is blank
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link AbstractApplication} with an
   *                          instanceName that equals the given name
   * @throws RuntimeException if the given initialSessionClass is null
   */
  public final <T extends AbstractSession<C, Object>, C extends AbstractBackendClient<C, Object>> S //
  addApplicationWithNameAndInitialSessionClassAndVoidContext(
    final String name,
    final Class<T> initialSessionClass) {
    // Creates Application.
    final var application = BasicApplication.withNameAndInitialSessionClassAndContext(
      name,
      initialSessionClass,
      new VoidObject());

    // Calls other method.
    return addApplication(application);
  }

  /**
   * Adds the given defaultApplication to the current {@link AbstractServer}. A
   * default {@link AbstractApplication} takes all {@link AbstractClient}s that do
   * not have a target.
   * 
   * @param defaultApplication
   * @param <C>                the type of the {@link AbstractBackendClient} of
   *                           the given defaultApplication
   * @param <U>                the type of the context of the given
   *                           defaultApplication
   * @return the current {@link AbstractServer}
   * @throws RuntimeException if the given defaultApplication is null
   */
  public final <C extends AbstractBackendClient<C, U>, U> S addDefaultApplication(
    final AbstractApplication<C, U> defaultApplication) {
    defaultApplication.setParentServer(this);

    addApplicationToList(defaultApplication);
    memberDefaultApplication = defaultApplication;

    noteAddedDefaultApplication(defaultApplication);

    return asConcrete();
  }

  /**
   * Adds a new default {@link AbstractApplication} with the given name,
   * initialSessionClass and applicationService to the current
   * {@link AbstractServer}.
   * 
   * @param applicationName
   * @param initialSessionClass
   * @param applicationService
   * @param <T>                 the type of the given initialSessionClass
   * @param <C>                 the type of the {@link AbstractBackendClient} of
   *                            the given initialSessionClass
   * @param <U>                 the type of the given applicationService
   * @return the current {@link AbstractServer}
   * @throws RuntimeException if the given instanceName is null
   * @throws RuntimeException if the given instanceName is blank
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a default {@link AbstractApplication}
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link AbstractApplication} with the given
   *                          instanceName
   * @throws RuntimeException if the given initialSessionClass is null
   */
  public final <T extends AbstractSession<C, U>, C extends AbstractBackendClient<C, U>, U> S //
  addDefaultApplicationWithNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final U applicationService) {
    // Creates default Application.
    final var localDefaultApplication = BasicApplication.withNameAndInitialSessionClassAndContext(
      applicationName,
      initialSessionClass,
      applicationService);

    // Calls other method.
    return addDefaultApplication(localDefaultApplication);
  }

  /**
   * Adds a new {@link AbstractApplication} with the given name,
   * initialSessionClass and a void context as default {@link AbstractApplication}
   * the current {@link AbstractServer}.
   * 
   * @param name
   * @param initialSessionClass
   * @param <T>                 the type of the given initialSessionClass
   * @param <C>                 the type of the {@link AbstractBackendClient} of
   *                            the given initialSessionClass
   * @return the current {@link AbstractServer}
   * @throws RuntimeException if the given name is null
   * @throws RuntimeException if the given name is blank
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link AbstractApplication} with an
   *                          instanceName that equals the given name
   * @throws RuntimeException if the given initialSessionClass is null
   */
  public final <T extends AbstractSession<C, Object>, C extends AbstractBackendClient<C, Object>> S//
  addDefaultApplicationWithNameAndInitialSessionClassAndVoidContext(
    final String name,
    final Class<T> initialSessionClass) {
    // Creates a default Application.
    final var localDefaultApplication = BasicApplication.withNameAndInitialSessionClassAndContext(
      name,
      initialSessionClass,
      new VoidObject());

    // Calls other method.
    return addDefaultApplication(localDefaultApplication);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void clear() {
    getStoredApplications().forEach(this::removeApplication);
  }

  /**
   * @return true if the current {@link AbstractServer} contains a default
   *         {@link AbstractApplication}, false otherwise
   */
  public final boolean containsDefaultApplication() {
    return (memberDefaultApplication != null);
  }

  /**
   * @param name
   * @return true if the current {@link AbstractServer} contains a
   *         {@link AbstractApplication} with the given name, false otherwise
   */
  public final boolean containsApplicationWithName(final String name) {
    return abstractApplications.containsMatching(a -> a.getInstanceName().equals(name));
  }

  /**
   * @param instanceName
   * @return the {@link AbstractApplication} with the given instanceName from the
   *         current {@link AbstractServer}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a
   *                                               {@link AbstractApplication}
   *                                               with the given instanceName.
   */
  public final AbstractApplication<?, ?> getStoredApplicationByInstanceName(final String instanceName) {
    return abstractApplications.getStoredFirst(a -> a.getInstanceName().equals(instanceName));
  }

  /**
   * @param urlInstanceName
   * @return the {@link AbstractApplication} with the given urlInstanceName from
   *         the current {@link AbstractServer}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a
   *                                               {@link AbstractApplication}
   *                                               with the given urlInstanceName.
   */
  public final AbstractApplication<?, ?> getStoredApplicationByUrlInstanceName(final String urlInstanceName) {
    return abstractApplications.getStoredFirst(a -> a.getUrlInstanceName().equals(urlInstanceName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<? extends Application<?, ?>> getStoredApplications() {
    return abstractApplications;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * @return the default {@link AbstractApplication} of the current
   *         {@link AbstractServer}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default
   *                                               {@link AbstractApplication}.
   */
  public final AbstractApplication<?, ?> getStoredDefaultApplication() {
    // Asserts that the current Server contains a default Application.
    assertContainsDefaultApplication();

    return memberDefaultApplication;
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
   *         {@link AbstractClient} connected, false otherwise
   */
  public final boolean hasClientConnected() {
    return abstractApplications.containsMatching(AbstractApplication::hasClientConnected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    // Does nothing.
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
   *                                               {@link AbstractApplication}
   * @throws ArgumentDoesNotHaveAttributeException if the given client has a
   *                                               target and the current
   *                                               {@link AbstractServer} does not
   *                                               contain a
   *                                               {@link AbstractApplication}
   *                                               with a name that equals the
   *                                               given target.
   */
  public final void takeClient(final AbstractBackendClient<?, ?> client) {
    // Handles the case that the given client does not have a target.
    if (!client.hasUrlInstanceNameOfTargetApplication()) {
      getStoredDefaultApplication().takeClient(client);

      // Handles the case that the given client has a target.
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
  protected abstract void noteAddedApplication(AbstractApplication<?, ?> application);

  /**
   * Notes that the given defaultApplication has been added to the current
   * {@link AbstractServer}.
   * 
   * @param defaultApplication
   */
  protected abstract void noteAddedDefaultApplication(AbstractApplication<?, ?> defaultApplication);

  /**
   * Notes that the given application has been removed fromt the current
   * {@link AbstractServer}.
   * 
   * @param application
   */
  protected abstract void noteRemovedApplication(Application<?, ?> application);

  /**
   * Lets the current {@link Server} take the given endPoint.
   * 
   * @param endPoint
   */
  void internalTakeEndPoint(final EndPoint endPoint) {
    // Handles the case that the given endPoint does not have a target.
    if (!endPoint.hasCustomTargetSlot()) {
      getStoredDefaultApplication().takeEndPoint(endPoint);

      // Handles the case that the given endPoint has a target.
    } else {
      getStoredApplicationByUrlInstanceName(endPoint.getCustomTargetSlot()).takeEndPoint(endPoint);
    }
  }

  /**
   * Adds the given application to the list of {@link AbstractApplication}s of the
   * current {@link AbstractServer}.
   * 
   * @param application
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link AbstractApplication} with the same
   *                          name as one of the given applications.
   */
  private void addApplicationToList(final AbstractApplication<?, ?> application) {
    // Asserts that the current Server does not contain already
    // an Application with the same name as the given application..
    assertDoesNotContainApplicationWithName(application.getInstanceName());

    // Adds the given application to the list of Applications of the current
    // BaseServer.
    abstractApplications.addAtEnd(application);
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default
   *                                               {@link AbstractApplication}.
   */
  private void assertContainsDefaultApplication() {
    if (!containsDefaultApplication()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "default Application");
    }
  }

  /**
   * @param name
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link AbstractApplication} with the same
   *                          name as one of the given applications.
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
  private void removeApplication(final Application<?, ?> application) {
    abstractApplications.removeStrictlyFirstOccurrenceOf(application);

    if (application == memberDefaultApplication) {
      memberDefaultApplication = null;
    }

    noteRemovedApplication(application);
  }
}
