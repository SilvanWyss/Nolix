//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi.Headerable;
import ch.nolix.systemapi.guiapi.processapi.Selectable;
import ch.nolix.systemapi.webguiapi.mainapi.IRootControlOwner;

//interface
public interface IAccordionTab extends Headerable<IAccordionTab>, IRootControlOwner<IAccordionTab>, Selectable {
	
	//method declaration
	boolean belongsToAccordion();
	
	//method declaration
	IAccordion getParentAccordion();
}
