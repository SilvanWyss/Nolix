/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;
import ch.nolix.baseapi.net.target.IServerTarget;
import ch.nolix.baseapi.objectcomposition.applicationmanager.ApplicationManager;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link Server}
 */
public interface Server<S extends Server<S>>
extends ApplicationManager<Application<?, ?>>, Clearable, GroupCloseable, SecurityModeHolder {
  /**
   * Adds the given application to the current {@link Server}.
   * 
   * @param application
   * @param <C>         the type of the {@link BackendClient}s of the given
   *                    application
   * @param <T>         the type of the application service of the given
   *                    application
   * @return the current {@link Server}
   * @throws RuntimeException if the given application is null
   * @throws RuntimeException if the current {@link Server} contains already a
   *                          {@link Application} with the same name or URL name
   *                          as the given application
   */
  <C extends BackendClient<C, T>, T> S addApplication(Application<C, T> application);

  /**
   * Adds the given defaultApplication to the current {@link Server}.
   * 
   * @param defaultApplication
   * @param <C>                the type of the {@link BackendClient}s of the given
   *                           defaultApplication
   * @param <T>                the type of the application service of the given
   *                           defaultApplication
   * @return the current {@link Server}
   * @throws RuntimeException if the given defaultApplication is null
   * @throws RuntimeException if the current {@link Server} contains already a
   *                          {@link Application} with the same name or URL name
   *                          as the given defaultApplication
   */
  <C extends BackendClient<C, T>, T> S addDefaultApplication(Application<C, T> defaultApplication);

  /**
   * @param name
   * @return true if the current {@link Server} contains a {@link Application}
   *         with the given name, false otherwise
   */
  boolean containsApplicationWithName(String name);

  /**
   * @return true if the current {@link Server} contains a default
   *         {@link Application}, false otherwise
   */
  boolean containsDefaultApplication();

  /**
   * @param name
   * @return the {@link Application} with the given name from the current
   *         {@link Server}
   * @throws RuntimeException if the current {@link Server} does not contain a
   *                          {@link Application} with the given name.
   */
  Application<?, ?> getStoredApplicationByName(String name);

  /**
   * @param urlName
   * @return the {@link Application} with the given urlName from the current
   *         {@linkServer}
   * @throws RuntimeException if the current {@link Server} does not contain a
   *                          {@link Application} with the given urlName.
   */
  Application<?, ?> getStoredApplicationByUrlName(final String urlName);

  /**
   * @return the default {@link Application} of the current {@link Server}
   * @throws RuntimeException if the current {@link Server} does not contain a
   *                          default {@link Application}.
   */
  Application<?, ?> getStoredDefaultApplication();

  /**
   * @return true if the current {@link Server} has a {@link Client} connected,
   *         false otherwise
   */
  boolean hasClientConnected();

  /**
   * Removes the {@link Application} with the given name from the current
   * {@link Server}.
   * 
   * @param name
   * @throws RuntimeException if the current {@link Server} does not contain a
   *                          {@link Application} with the given instanceName
   */
  void removeApplicationByName(final String name);

  /**
   * @return a {@link IServerTarget} representation of the current {@link Server}
   */
  IServerTarget toTarget();
}
