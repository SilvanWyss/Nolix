//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi.FluentNameable;
//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Identified;
import ch.nolix.coreapi.functionapi.requestuniversalapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//interface
public interface IColumn
extends Deletable, EmptinessRequestable, IDatabaseObject, Identified, FluentNameable<IColumn> {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	IParametrizedPropertyType getParametrizedPropertyType();
	
	//method declaration
	ITable getParentTable();
	
	//method declaration
	IColumn setParametrizedPropertyType(IParametrizedPropertyType parametrizedPropertyType);
	
	//method declaration
	IColumnDto toDTO();
}
