//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Headerable;
import ch.nolix.common.requestapi.EmptinessRequestable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface IColumn<
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<?>
> extends Deletable, EmptinessRequestable, Headerable<C>, IDatabaseObject {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	PPT getParametrizedPropertyType();
	
	//method declaration
	ITable<?, ?, ?> getParentTable();
	
	//method declaration
	C setParametrizedPropertyType(PPT parametrizedPropertyType);
	
	//method declaration
	IColumnDTO toDTO();
}
