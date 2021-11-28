//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Namable;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ITable extends Deletable, IDatabaseObject, GroupCloseable, Namable<ITable> {
	
	//method declaration
	ITable addColumn(IColumn column);
	
	//method declaration
	boolean belongsToDatabase();
	
	//method declaration
	ITable createColumnWithHeaderAndParametrizedPropertyType(
		String header,
		IParametrizedPropertyType<?> parametrizedPropertyType
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
