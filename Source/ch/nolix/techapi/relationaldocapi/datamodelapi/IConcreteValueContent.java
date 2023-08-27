//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//interface
public interface IConcreteValueContent extends IValueContent {
	
	//method declaration
	IConcreteParameterizedValueContent<?> getStoredConcreteParametrizedValueContent();
	
	//method declaration
	IConcreteValueContent setDataType(DataType dataType);
}
