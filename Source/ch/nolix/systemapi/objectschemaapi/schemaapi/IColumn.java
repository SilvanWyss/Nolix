//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.core.attributeapi.mandatoryattributeapi.IdentifiedByString;
import ch.nolix.core.attributeapi.mutablemandatoryattributeapi.Namable;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.core.requestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface IColumn<IMPL>
extends Deletable, EmptinessRequestable, GroupCloseable, IDatabaseObject, IdentifiedByString, Namable<IColumn<IMPL>> {
	
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
