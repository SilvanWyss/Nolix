package ch.nolix.systemapi.applicationapi.componentapi;

import ch.nolix.coreapi.creationapi.builderapi.Rebuildable;
import ch.nolix.coreapi.programcontrolapi.triggerapi.IRefreshableSubscriber;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IComponent
extends IControl<IComponent, IComponentStyle>, IRefreshableSubscriber, Rebuildable {

  RefreshBehavior getRefreshBehavior();
}
