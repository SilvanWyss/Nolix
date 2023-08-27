//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;

//interface
public interface IAbstractableObject extends Abstractable<IAbstractableObject>, FluentNameable<IAbstractableObject> {
	
	//method declaration
	IAbstractableObject addField(IAbstractableField field);
	
	//method declaration
	IContainer<IAbstractableField> getStoredFields();
	
	//method declaration
	IContainer<IAbstractableObject> getStoredTypes();
	
	//method declaration
	void removeField(IAbstractableField field);
}