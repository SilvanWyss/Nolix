//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
final class PropertyFromTableExtractor {
	
	//method
	public IContainer<Property> createPropertiesFromTable(
		final ITable<DataImplementation, IEntity<DataImplementation>> table
	) {
		return table.getColumns().to(this::createPropertyFromColumn);
	}
	
	//method
	private Property createEmptyPropertyFromColumn(final IColumn<DataImplementation> column) {
		switch (column.getParametrizedPropertyType().getPropertyType()) {
			case VALUE:
				return new Value<>();
			case OPTIONAL_VALUE:
				return new OptionalValue<>();
			case MULTI_VALUE:
				return new MultiValue<>();
			case REFERENCE:
				
				final var referencedtableName =
				column
				.getParametrizedPropertyType()
				.asBaseParametrizedReferenceType()
				.getRefencedTable()
				.getName();
				
				return Reference.forEntityWithTableName(referencedtableName);
			case OPTIONAL_REFERENCE:
				
				final var referencedtableName2 =
				column
				.getParametrizedPropertyType()
				.asBaseParametrizedReferenceType()
				.getRefencedTable()
				.getName();
				
				return OptionalReference.forEntityWithTableName(referencedtableName2);
			case MULTI_REFERENCE:
				
				final var referencedtableName3 =
				column
				.getParametrizedPropertyType()
				.asBaseParametrizedReferenceType()
				.getRefencedTable()
				.getName();
				
				return MultiReference.forEntityWithTableName(referencedtableName3);
			case BACK_REFERENCE:
			case OPTIONAL_BACK_REFERENCE:
			case MULTI_BACK_REFERENCE:
			default:
				throw InvalidArgumentException.forArgument(column);
			}
	}
	
	//method
	private Property createPropertyFromColumn(final IColumn<DataImplementation> column) {
		
		final var property = createEmptyPropertyFromColumn(column);
		property.internalSetParentColumn(column);
		
		return property;
	}
}
