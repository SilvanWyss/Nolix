//package declaration
package ch.nolix.tech.relationaldoc.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableObjectEvaluator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//class
public final class AbstractableObjectValidator {
	
	//constant
	private static final AbstractableObjectEvaluator ABSTRACTABLE_OBJECT_EVALUATOR = new AbstractableObjectEvaluator();
	
	//method
	public void assertCanBeSetAsConcrete(final IAbstractableObject abstractableObject) {
		if (ABSTRACTABLE_OBJECT_EVALUATOR.canBeSetAsConcrete(abstractableObject)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(abstractableObject, "cannot be set as concrete");
		}
	}
}
