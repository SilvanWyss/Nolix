//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Headerable;
import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.common.requestapi.EmptinessRequestable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface IColumn extends Deletable, EmptinessRequestable, GroupCloseable, Headerable<IColumn>, IDatabaseObject {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	IParametrizedPropertyType<?> getParametrizedPropertyType();
	
	//method declaration
	ITable getParentTable();
	
	//method declaration
	IColumn setParametrizedPropertyType(IParametrizedPropertyType<?> parametrizedPropertyType);
	
	//method declaration
	IColumnDTO toDTO();
}
