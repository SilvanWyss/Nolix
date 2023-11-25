//package declaration
package ch.nolix.systemapi.applicationapi.componentapi;

//own imports
import ch.nolix.coreapi.methodapi.mutationapi.Rebuildable;
import ch.nolix.coreapi.programcontrolapi.triggerapi.IRefreshableSubscriber;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IComponent
extends IControl<IComponent, IComponentStyle>, IRefreshableSubscriber, Rebuildable {

  //method declaration
  RefreshBehavior getRefreshBehavior();
}
