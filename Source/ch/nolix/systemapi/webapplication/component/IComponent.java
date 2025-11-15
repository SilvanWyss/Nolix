package ch.nolix.systemapi.webapplication.component;

import ch.nolix.coreapi.objectcreation.builder.Rebuildable;
import ch.nolix.coreapi.programcontrol.trigger.IRefreshableSubscriber;
import ch.nolix.systemapi.webgui.main.IControl;

public interface IComponent
extends IControl<IComponent, IComponentStyle>, IRefreshableSubscriber, Rebuildable {
  RefreshTrigger getRefreshBehavior();
}
