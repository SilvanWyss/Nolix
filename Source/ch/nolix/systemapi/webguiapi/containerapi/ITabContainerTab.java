//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Headered;
import ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi.Headerable;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ITabContainerTab extends Clearable, Headerable<Headered> {
	
	//method declaration
	boolean belongsToTabContainer();
	
	//method declaration
	IControl<?, ?> getRefControl();
	
	//method declaration
	boolean isSelected();
	
	//method declaration
	void select();
}
