//package declaration
package ch.nolix.tech.relationaldoc.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableFieldEvaluator;
import ch.nolix.tech.relationaldoc.datamodel.AbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;

//class
public final class AbstractableFieldValidator {
	
	//constant
	private static final AbstractableFieldEvaluator ABSTRACTABLE_FIELD_EVALUATOR = new AbstractableFieldEvaluator();
	
	//method
	public void assertCanBeSetAsAbstract(final IAbstractableField abstractableField) {
		if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetAsAbstract(abstractableField)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set as abstract");
		}
	}
	
	//method
	public void assertCanBeSetAsConcrete(AbstractableField abstractableField) {
		if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetAsConcrete(abstractableField)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set as concrete");
		}
	}
	
	//method
	public void assertCanSetCardinality(final AbstractableField abstractableField, final Cardinality cardinality) {
		if (!ABSTRACTABLE_FIELD_EVALUATOR.canSetCardinality(abstractableField, cardinality)) {
			throw InvalidArgumentException.forArgument(cardinality);
		}
	}
	
	//method
	public void assertCanSetName(final IAbstractableField abstractableField, final String name) {
		if (!ABSTRACTABLE_FIELD_EVALUATOR.canSetName(abstractableField, name)) {
			throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.NAME, name);
		}
	}
}
