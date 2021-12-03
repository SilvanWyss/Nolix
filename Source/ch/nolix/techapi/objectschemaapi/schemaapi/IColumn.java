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
public interface IColumn<IMPL>
extends Deletable, EmptinessRequestable, GroupCloseable, Headerable<IColumn<IMPL>>, IDatabaseObject {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	IParametrizedPropertyType<IMPL, ?> getParametrizedPropertyType();
	
	//method declaration
	ITable<IMPL> getParentTable();
	
	//method declaration
	IColumn<IMPL> setParametrizedPropertyType(IParametrizedPropertyType<IMPL, ?> parametrizedPropertyType);
	
	//method declaration
	IColumnDTO toDTO();
}
