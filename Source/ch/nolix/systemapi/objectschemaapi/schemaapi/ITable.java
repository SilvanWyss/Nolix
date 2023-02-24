//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi.FluentNameable;
//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Identified;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ITable
extends
Deletable,
IDatabaseObject,
Identified,
FluentNameable<ITable> {
	
	//method declaration
	ITable addColumn(IColumn column);
	
	//method declaration
	boolean belongsToDatabase();
	
	//method declaration
	ITable createColumnWithNameAndParametrizedPropertyType(
		String name,
		IParametrizedPropertyType parametrizedPropertyType
	);
	
	//method declaration
	IFlatTableDTO getFlatDTO();
	
	//method declaration
	IDatabase getParentDatabase();
	
	//method declarations
	IContainer<IColumn> getRefColumns();
	
	//method declaration
	ITableDTO toDTO();
}
