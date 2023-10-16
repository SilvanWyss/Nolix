//package declaration
package ch.nolix.systemapi.applicationapi.componentapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.triggerapi.IRefreshableSubscriber;
import ch.nolix.systemapi.webguiapi.basecontainerapi.IControlGetter;

//interface
public interface IComponent extends IControlGetter, IRefreshableSubscriber {

  //method declaration
  RefreshBehavior getRefreshBehavior();
}
