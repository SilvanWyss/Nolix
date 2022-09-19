//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;

//interface
public interface IControlCSSRuleCreator<
	C extends IControl<C, CL>,
	CL extends IControlStyle<CL>
> {
	
	//method declaration
	ICSSRule<?> getCSSRuleForState(ControlState state);
	
	//method declaration
	C getRefParentControl();
}
