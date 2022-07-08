//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IdentifiedByString;
import ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi.Namable;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ITable<IMPL>
extends
Deletable,
IDatabaseObject,
IdentifiedByString,
Namable<ITable<IMPL>> {
	
	//method declaration
	ITable<IMPL> addColumn(IColumn<IMPL> column);
	
	//method declaration
	boolean belongsToDatabase();
	
	//method declaration
	ITable<IMPL> createColumnWithNameAndParametrizedPropertyType(
		String name,
		IParametrizedPropertyType<IMPL> parametrizedPropertyType
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
