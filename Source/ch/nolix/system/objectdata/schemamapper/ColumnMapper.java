//package declaration
package ch.nolix.system.objectdata.schemamapper;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IParametrizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ColumnMapper implements IColumnMapper<SchemaImplementation> {
	
	//static attribute
	private static final EntityCreator entityCreator = new EntityCreator();
	
	//static attribute
	private static final IParametrizedPropertyTypeMapper<SchemaImplementation> parametrizedPropertyTypeMapper =
	new ParametrizedPropertyTypeMapper();
	
	//method
	@Override
	public IColumn<SchemaImplementation> createColumnFromGivenPropertyUsingGivenReferencableTables(
		final IProperty<?> property,
		final IContainer<ITable<SchemaImplementation>> referencableTables
	) {
		return
		new Column(
			property.getName(),
			parametrizedPropertyTypeMapper.createParametrizedPropertyTypeFromGivenPropertyUsingGivenReferencableTables(
				property,
				referencableTables
			)
		);
	}
	
	//method
	@Override
	public <E extends IEntity<?>> IContainer<IColumn<SchemaImplementation>>
	createColumnsFromGivenEntityTypeUsingGivenReferencableTables(
		final Class<E> entityType,
		final IContainer<ITable<SchemaImplementation>> referencableTables
	) {
		return
		createColumnsFromGivenEntityUsingGivenReferencableTables(
			entityCreator.createEmptyEntityOf(entityType),
			referencableTables
		);
	}
	
	//method
	@Override
	public IContainer<IColumn<SchemaImplementation>> createColumnsFromGivenEntityUsingGivenReferencableTables(
		final IEntity<?> entity,
		final IContainer<ITable<SchemaImplementation>> referencableTables
	) {
		return
		entity
		.technicalGetRefProperties()
		.to(p -> createColumnFromGivenPropertyUsingGivenReferencableTables(p, referencableTables));
	}
}
