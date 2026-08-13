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
 * @param <S> the type of a {@link Server}.
 */
public interface Server<S extends Server<S>>
extends Clearable, GroupCloseable, ApplicationManager<Application<?, ?>>, SecurityModeHolder {
  /**
   * Adds the given application to the current {@link Server}.
   * 
   * @param application
   * @return the current {@link Server}
   * @throws RuntimeException if the given application is null
   * @throws RuntimeException if the current {@link Server} contains already a
   *                          {@link Application} with the same instanceName as
   *                          the given application.
   */
  S addApplication(final Application<?, ?> application);

  /**
   * @return a target representation of the current {@link Server}.
   */
  IServerTarget asTarget();

  /**
   * Removes the {@link Application} with the given instanceName from the current
   * {@link Server}.
   * 
   * @param instanceName
   * @throws RuntimeException if the current {@link Server} does not contain a
   *                          {@link Application} with the given instanceName.
   */
  void removeApplicationWithInstanceName(final String instanceName);
}
