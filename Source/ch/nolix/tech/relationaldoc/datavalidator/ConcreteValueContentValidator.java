//package declaration
package ch.nolix.tech.relationaldoc.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.tech.relationaldoc.dataevaluator.ConcreteValueContentEvaluator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;

//class
public final class ConcreteValueContentValidator {
	
	//constant
	private static final ConcreteValueContentEvaluator CONCRETE_VALUE_CONTENT_EVALUATOR =
	new ConcreteValueContentEvaluator();
	
	//method
	public void assertCanSetDataType(final IConcreteValueContent concreteValueContent, final DataType dataType) {
		if (!CONCRETE_VALUE_CONTENT_EVALUATOR.canSetDataType(concreteValueContent, dataType)) {
			throw InvalidArgumentException.forArgument(dataType);
		}
	}
}
