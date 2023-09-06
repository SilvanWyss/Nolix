//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.coreapi.datamodelapi.entityproperty.ContentType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IReferenceContent;

//class
public abstract class ReferenceContent extends Content implements IReferenceContent {
	
	//method
	@Override
	public final ContentType getType() {
		return ContentType.REFERENCE;
	}
}
