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
public interface ITable<IMPL> extends Deletable, IDatabaseObject, GroupCloseable, Namable<ITable<IMPL>> {
	
	//method declaration
	ITable<IMPL> addColumn(IColumn<IMPL> column);
	
	//method declaration
	boolean belongsToDatabase();
	
	//method declaration
	ITable<IMPL> createColumnWithNameAndParametrizedPropertyType(
		String name,
		IParametrizedPropertyType<IMPL, ?> parametrizedPropertyType
	);
	
	//method declaration
	IFlatTableDTO getFlatDTO();
	
	//method declaration
	IDatabase<IMPL> getParentDatabase();
	
	//method declarations
	IContainer<IColumn<IMPL>> getRefColumns();
	
	//method declaration
	ITableDTO toDTO();
}
