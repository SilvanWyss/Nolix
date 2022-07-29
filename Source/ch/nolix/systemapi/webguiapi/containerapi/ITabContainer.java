//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.webguiapi.controlapi.ILabelStyle;

//interface
public interface ITabContainer
extends ch.nolix.systemapi.webguiapi.containerapi.IContainer<ITabContainer, ITabContainerStyle> {
	
	//method declaration
	ITabContainer addTab(ITabContainerTab... tabs);
	
	//method declaration
	ITabContainer addTabs(IContainer<ITabContainerTab> tabs);
	
	//method declaration
	boolean containsSelectedTab();
	
	//method declaration
	ILabelStyle getRefHeaderStyle();
	
	//method declaration
	ITabContainerTab getRefSelectedTab();
	
	//method declaration
	ITabContainerTab getRefTabByHeader(String header);
	
	//method declaration
	IContainer<ITabContainerTab> getRefTabs();
	
	//method declaration
	int getTabCount();
	
	//method declaration
	ITabContainer onHeaderStyle(IElementTaker<ILabelStyle> headerStyleEditor);
	
	//method declaration
	void selectFirstTab();
	
	//method declaration
	void selectTabByHeader(String header);
}
