//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi.Headerable;
//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Headered;
import ch.nolix.systemapi.guiapi.processapi.Selectable;
import ch.nolix.systemapi.webguiapi.mainapi.IRootControlOwner;

//interface
public interface ITabContainerTab extends Headerable<Headered>, IRootControlOwner<ITabContainerTab>, Selectable {
	
	//method declaration
	boolean belongsToTabContainer();
	
	//method declaration
	ITabContainer getParentTabContainer();
}
