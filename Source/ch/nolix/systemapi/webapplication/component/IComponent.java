/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.component;

import ch.nolix.baseapi.objectcreation.builder.Rebuildable;
import ch.nolix.baseapi.programcontrol.trigger.IRefreshableSubscriber;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IComponent
extends Control<IComponent, IComponentStyle>, IRefreshableSubscriber, Rebuildable {
  RefreshTrigger getRefreshTrigger();
}
