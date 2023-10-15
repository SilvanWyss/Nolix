//package declaration
package ch.nolix.systemapi.webguiapi.controlserviceapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IControlCssBuilder<C extends IControl<C, CS>, CS extends IControlStyle<CS>> {

  // method declaration
  IContainer<ICssRule> createCssRulesForControl(C control);
}
