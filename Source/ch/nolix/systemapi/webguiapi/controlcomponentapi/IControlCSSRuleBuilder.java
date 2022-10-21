//package declaration
package ch.nolix.systemapi.webguiapi.controlcomponentapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IControlCSSRuleBuilder<
	C extends IControl<C, CL>,
	CL extends IControlStyle<CL>
> {
	
	//method declaration
	IContainer<ICSSRule<?>> getCSSRules();
	
	//method declaration
	C getRefParentControl();
}
