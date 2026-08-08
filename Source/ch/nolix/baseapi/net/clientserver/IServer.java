/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.baseapi.manager.applicationmanager.ApplicationManager;
import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;
import ch.nolix.baseapi.net.target.IServerTarget;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link IServer}.
 */
public interface IServer<S extends IServer<S>>
extends Clearable, GroupCloseable, ApplicationManager<Application<?, ?>>, SecurityModeHolder {
  /**
   * Adds the given application to the current {@link IServer}.
   * 
   * @param application
   * @return the current {@link IServer}
   * @throws RuntimeException if the given application is null
   * @throws RuntimeException if the current {@link IServer} contains already a
   *                          {@link Application} with the same instanceName as
   *                          the given application.
   */
  S addApplication(final Application<?, ?> application);

  /**
   * @return a target representation of the current {@link IServer}.
   */
  IServerTarget asTarget();

  /**
   * Removes the {@link Application} with the given instanceName from the current
   * {@link IServer}.
   * 
   * @param instanceName
   * @throws RuntimeException if the current {@link IServer} does not contain a
   *                          {@link Application} with the given instanceName.
   */
  void removeApplicationWithInstanceName(final String instanceName);
}
