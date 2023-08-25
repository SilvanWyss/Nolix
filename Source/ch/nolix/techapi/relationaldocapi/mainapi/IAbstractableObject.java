//package declaration
package ch.nolix.techapi.relationaldocapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;

//interface
public interface IAbstractableObject extends AbstractnessRequestable, FluentNameable<IAbstractableObject> {
	
	//method declaration
	IContainer<IAbstractableField> getFields();
	
	//method declaration
	IContainer<IAbstractableObject> getTypes();
}
