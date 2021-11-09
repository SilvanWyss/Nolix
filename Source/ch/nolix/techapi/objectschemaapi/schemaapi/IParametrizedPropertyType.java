//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//interface
public interface IParametrizedPropertyType<
	PPT extends IParametrizedPropertyType<PPT,DT>,
	DT
> {
	
	IBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType();
	
	//method declaration
	Class<DT> getDataType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean referencesTable(ITable<?, ?, ?> table);
	
	//method declaration
	boolean referencesBackColumn(IColumn<?, ?> column);
	
	//method declaration
	IParametrizedPropertyTypeDTO toDTO();
}
