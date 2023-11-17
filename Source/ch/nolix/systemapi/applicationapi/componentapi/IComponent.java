//package declaration
package ch.nolix.systemapi.applicationapi.componentapi;

import ch.nolix.coreapi.methodapi.mutationapi.Rebuildable;
import ch.nolix.coreapi.programcontrolapi.triggerapi.IRefreshableSubscriber;
import ch.nolix.systemapi.webguiapi.basecontainerapi.IControlGetter;

//interface
public interface IComponent extends IControlGetter, IRefreshableSubscriber, Rebuildable {

  //method declaration
  RefreshBehavior getRefreshBehavior();
}
