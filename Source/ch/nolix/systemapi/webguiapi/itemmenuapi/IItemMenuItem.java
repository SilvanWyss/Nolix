//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Identified;
import ch.nolix.coreapi.functionapi.requestuniversalapi.BlanknessRequestable;
import ch.nolix.systemapi.elementapi.mainuniversalapi.IMutableElement;
import ch.nolix.systemapi.guiapi.processapi.Selectable;

//interface
public interface IItemMenuItem<IMI extends IItemMenuItem<IMI>>
extends BlanknessRequestable, Identified, IMutableElement, Selectable {
	
	//method declaration
	boolean belongsToMenu();
	
	//method declaration
	String getText();
	
	//method declaration
	void technicalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
