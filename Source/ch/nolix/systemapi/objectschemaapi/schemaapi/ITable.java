//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Identified;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

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
	IFlatTableDto getFlatDto();
	
	//method declaration
	IDatabase getParentDatabase();
	
	//method declarations
	IContainer<IColumn> getOriColumns();
	
	//method declaration
	ITableDto toDto();
}
