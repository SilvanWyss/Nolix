//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Headerable;
import ch.nolix.common.requestapi.EmptinessRequestable;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface IColumn<
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<?>
> extends EmptinessRequestable, Headerable<C>, IDatabaseObject {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	void delete();
	
	//method declaration
	PPT getParametrizedPropertyType();
	
	//method declaration
	ITable<?, ?, ?> getParentTable();
	
	//method declaration
	C setParametrizedPropertyType(PPT parametrizedPropertyType);
	
	//method declaration
	IColumnDTO toDTO();
}
