//package declaration
package ch.nolix.systemapi.webguiapi.controlcomponentapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IControlCssRuleBuilder<
	C extends IControl<C, CL>,
	CL extends IControlStyle<CL>
> {
	
	//method declaration
	IContainer<ICSSRule<?>> createCSSRulesForControl(C control);
}
