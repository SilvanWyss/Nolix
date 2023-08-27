//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableCardinalityHolder;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;

//interface
public interface IAbstractableField
extends
Abstractable<IAbstractableField>,
FluentNameable<IAbstractableField>,
IFluentMutableCardinalityHolder<IAbstractableField> {
	
	//method declaration
	IAbstractableField getStoredBaseField();
	
	//method declaration
	IContent getStoredContent();
	
	//method declaration
	boolean inheritsFromBaseField();
	
	//method declaration
	IAbstractableField setContent(IContent content);
}