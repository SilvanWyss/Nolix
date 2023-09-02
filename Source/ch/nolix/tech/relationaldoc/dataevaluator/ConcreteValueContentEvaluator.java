//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;

//class
public final class ConcreteValueContentEvaluator {
	
	//method
	public boolean canSetDataType(final IConcreteValueContent concreteValueContent, final DataType dataType) {
		return
		concreteValueContent != null
		&& !concreteValueContent.getStoredParentField().inheritsFromBaseField()
		&& dataType != null;
	}
}
