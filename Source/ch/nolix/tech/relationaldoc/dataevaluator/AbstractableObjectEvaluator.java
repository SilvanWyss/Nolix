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
		&& abstractableObject.getStoredNonInheritedFields().containsNone(IAbstractableField::isAbstract);
	}
}
