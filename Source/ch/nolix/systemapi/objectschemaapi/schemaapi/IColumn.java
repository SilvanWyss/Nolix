//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IdentifiedByString;
import ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi.Namable;
import ch.nolix.coreapi.functionapi.requestuniversalapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface IColumn<IMPL>
extends Deletable, EmptinessRequestable, IDatabaseObject, IdentifiedByString, Namable<IColumn<IMPL>> {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	IParametrizedPropertyType<IMPL> getParametrizedPropertyType();
	
	//method declaration
	ITable<IMPL> getParentTable();
	
	//method declaration
	IColumn<IMPL> setParametrizedPropertyType(IParametrizedPropertyType<IMPL> parametrizedPropertyType);
	
	//method declaration
	IColumnDTO toDTO();
}
