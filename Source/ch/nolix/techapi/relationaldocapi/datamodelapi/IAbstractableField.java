//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableCardinalityHolder;
import ch.nolix.coreapi.datamodelapi.entityrequestapi.ContentTypeRequestable;
import ch.nolix.coreapi.datamodelapi.entityrequestapi.MandatorynessRequestable;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;

//interface
public interface IAbstractableField
extends
Abstractable<IAbstractableField>,
ContentTypeRequestable,
FluentNameable<IAbstractableField>,
IFluentMutableCardinalityHolder<IAbstractableField>,
MandatorynessRequestable {
	
	//method declaration
	IAbstractableField getStoredBaseField();
	
	//method declaration
	IAbstractableObject getStoredParentObject();
	
	//method declaration
	IContent getStoredContent();
	
	//method declaration
	boolean inheritsFromBaseField();
}
