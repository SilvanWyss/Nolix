//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;

//interface
public interface IControlCSSRuleCreator<
	C extends IControl<C, CL>,
	CL extends IControlStyle<CL>
> {
	
	//method declaration
	IContainer<ICSSRule<?>> getCSSRules();
	
	//method declaration
	C getRefParentControl();
}
