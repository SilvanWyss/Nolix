/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.component;

import ch.nolix.baseapi.generalstate.staterequest.AlivenessRequestable;
import ch.nolix.baseapi.programcontrol.builder.Rebuildable;
import ch.nolix.baseapi.programcontrol.refresh.Refreshable;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IComponent
extends Control<IComponent, IComponentStyle>, AlivenessRequestable, Rebuildable, Refreshable {
  RefreshTrigger getRefreshTrigger();
}
