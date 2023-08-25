//package declaration
package ch.nolix.techapi.relationaldocapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;

//interface
public interface IAbstractableObject extends Abstractable<IAbstractableObject>, FluentNameable<IAbstractableObject> {
	
	//method declaration
	IContainer<IAbstractableField> getStoredFields();
	
	//method declaration
	IContainer<IAbstractableObject> getStoredTypes();
}
