//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.coreapi.datamodelapi.entityproperty.ContentType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IValueContent;

//class
public abstract class ValueContent extends Content implements IValueContent {
	
	//method
	@Override
	public final ContentType getType() {
		return ContentType.VALUE;
	}
}
