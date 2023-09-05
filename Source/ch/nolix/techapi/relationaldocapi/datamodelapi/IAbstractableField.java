//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableCardinalityHolder;
import ch.nolix.coreapi.datamodelapi.entityproperty.ContentType;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;

//interface
public interface IAbstractableField
extends
Abstractable<IAbstractableField>,
FluentNameable<IAbstractableField>,
IFluentMutableCardinalityHolder<IAbstractableField> {
	
	//method declaration
	ContentType getContentType();
	
	//method declaration
	IAbstractableField getStoredBaseField();
	
	//method declaration
	IAbstractableObject getStoredParentObject();
	
	//method declaration
	IContent getStoredContent();
	
	//method declaration
	boolean inheritsFromBaseField();
	
	//method declaration
	IAbstractableField setContent(IContent content);
}
