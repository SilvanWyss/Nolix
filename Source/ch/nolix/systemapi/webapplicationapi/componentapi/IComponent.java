package ch.nolix.systemapi.webapplicationapi.componentapi;

import ch.nolix.coreapi.creation.builder.Rebuildable;
import ch.nolix.coreapi.programcontrolapi.triggerapi.IRefreshableSubscriber;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IComponent
extends IControl<IComponent, IComponentStyle>, IRefreshableSubscriber, Rebuildable {

  RefreshBehavior getRefreshBehavior();
}
