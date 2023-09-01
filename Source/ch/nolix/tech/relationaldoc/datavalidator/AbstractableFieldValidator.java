//package declaration
package ch.nolix.tech.relationaldoc.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableFieldEvaluator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;

//class
public final class AbstractableFieldValidator {
	
	//constant
	private static final AbstractableFieldEvaluator ABSTRACTABLE_FIELD_EVALUATOR = new AbstractableFieldEvaluator();
	
	//method
	public void assertCanSetAsAbstract(final IAbstractableField abstractableField) {
		if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetAsAbstract(abstractableField)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set as abstract");
		}
	}
	
	//method
	public void assertCanSetName(final IAbstractableField abstractableField, final String name) {
		if (!ABSTRACTABLE_FIELD_EVALUATOR.canSetName(abstractableField, name)) {
			throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.NAME, name);
		}
	}
}
