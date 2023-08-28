//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//class
public final class AbstractableObjectEvaluator {
	
	//method
	public boolean canBeSetAsConcrete(final IAbstractableObject abstractableObject) {
		return
		abstractableObject != null		
		&& abstractableObject.getStoredFields().containsNone(IAbstractableField::isAbstract);
	}
	
	//method
	public boolean canSetName(final IAbstractableObject abstractableObject, final String name) {
		return
		canSetName(name)
		&& abstractableObject != null
		&& abstractableObject.getStoredBaseTypes().containsNone(ao -> ao.hasName(name))
		&& abstractableObject.getStoredSubTypes().containsNone(ao -> ao.hasName(name));
	}
	
	//method
	private boolean canSetName(final String name) {
		return
		name != null
		&& !name.isBlank();
	}
}
