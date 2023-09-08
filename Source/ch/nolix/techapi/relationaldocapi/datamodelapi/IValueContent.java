//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//interface
public interface IValueContent extends IContent {
	
	//method declaration
	IContainer<IConstraint<String>> getConstraints();
	
	//method declaration
	DataType getDataType();
}
