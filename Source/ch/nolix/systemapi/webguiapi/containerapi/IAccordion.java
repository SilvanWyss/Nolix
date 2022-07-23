//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.guiapi.controlproperty.TabExpansionBehavior;

//interface
public interface IAccordion extends ch.nolix.systemapi.webguiapi.containerapi.IContainer<IAccordion, IAccordionLook> {
	
	//method declaration
	IAccordion addTab(IAccordionTab... tabs);
	
	//method declaration
	IAccordion addTabs(IContainer<IAccordionTab> tabs);
	
	//method declaration
	void expandFirstTab();
	
	//method declaration
	void expandTabByHeader(String header);
	
	//method declaration
	ILinearContainerLook<?> getHeaderLook();
	
	//method declaration
	IAccordionTab getRefTabByHeader(String header);
	
	//method declaration
	IContainer<IAccordionTab> getRefTabs();
	
	//method declaration
	int getTabCount();
	
	//method declaration
	TabExpansionBehavior getTabExpansionBehavior();
	
	//method declaration
	ILinearContainerLook<?> onHeaderLook(IElementTaker<ILinearContainerLook<?>> headerLookEditor);
		
	//method declaration
	IAccordion setTabExpansionBehaviour(TabExpansionBehavior tabExpansionBehavior);
}
