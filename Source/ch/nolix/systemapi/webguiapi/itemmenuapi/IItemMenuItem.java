//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IdentifiedByString;
import ch.nolix.coreapi.functionapi.requestuniversalapi.BlanknessRequestable;
import ch.nolix.systemapi.elementapi.mainuniversalapi.IMutableElement;
import ch.nolix.systemapi.guiapi.processapi.Selectable;

//interface
public interface IItemMenuItem<IMI extends IItemMenuItem<IMI>>
extends BlanknessRequestable, IdentifiedByString, IMutableElement, Selectable {
	
	//method declaration
	boolean belongsToMenu();
	
	//method declaration
	String getText();
	
	//method declaration
	void technicalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
