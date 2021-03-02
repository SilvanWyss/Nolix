//package declaration
package ch.nolix.system.database.entitysetmapper;

import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.database.databaseadapter.Column;
import ch.nolix.system.database.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.database.databaseschemaadapter.EntitySet;
import ch.nolix.system.database.datatype.BaseBackReferenceType;
import ch.nolix.system.database.datatype.BaseReferenceType;
import ch.nolix.system.database.schemadatatype.SchemaBackReferenceType;
import ch.nolix.system.database.schemadatatype.SchemaDataType;
import ch.nolix.system.database.schemadatatype.SchemaIdType;
import ch.nolix.system.database.schemadatatype.SchemaMultiBackReferenceType;
import ch.nolix.system.database.schemadatatype.SchemaMultiReferenceType;
import ch.nolix.system.database.schemadatatype.SchemaMultiValueType;
import ch.nolix.system.database.schemadatatype.SchemaOptionalBackReferenceType;
import ch.nolix.system.database.schemadatatype.SchemaOptionalReferenceType;
import ch.nolix.system.database.schemadatatype.SchemaOptionalValueType;
import ch.nolix.system.database.schemadatatype.SchemaReferenceType;
import ch.nolix.system.database.schemadatatype.SchemaValueType;

//class
public final class ParametrizedSchemaDataTypeMapper {
	
	//attribute
	private final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter;
	
	//constructor
	public ParametrizedSchemaDataTypeMapper(final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter) {
		
		Validator.assertThat(parentDatabaseSchemaAdapter).thatIsNamed("parent DatabaseSchemaAdapter").isNotNull();
		
		this.parentDatabaseSchemaAdapter = parentDatabaseSchemaAdapter;
	}
	
	//method
	public SchemaDataType<?> createParametrizedSchemaDataTypeFor(final Column<?> column) {
		switch (column.getPropertyKind()) {
			case VALUE:
				return new SchemaValueType<>(column.getRefContentClass());
			case OPTIONAL_VALUE:
				return new SchemaOptionalValueType<>(column.getRefContentClass());
			case MULTI_VALUE:
				return new SchemaMultiValueType<>(column.getRefContentClass());
			case REFERENCE:
				return new SchemaReferenceType(getReferencedEntitySetFor((BaseReferenceType<?>)column.getDataType()));
			case OPTIONAL_REFERENCE:
				return
				new SchemaOptionalReferenceType(getReferencedEntitySetFor((BaseReferenceType<?>)column.getDataType()));
			case MULTI_REFERENCE:
				return
				new SchemaMultiReferenceType(getReferencedEntitySetFor((BaseReferenceType<?>)column.getDataType()));
			case BACK_REFERENCE:
				return
				new SchemaBackReferenceType(getBackReferencingEntitySetFor((BaseBackReferenceType<?>)column.getDataType()));
			case OPTIONAL_BACK_REFERENCE:
				return
				new SchemaOptionalBackReferenceType(
					getBackReferencingEntitySetFor((BaseBackReferenceType<?>)column.getDataType())
				);
			case MULTI_BACK_REFERENCE:
				return
					new SchemaMultiBackReferenceType(
						getBackReferencingEntitySetFor((BaseBackReferenceType<?>)column.getDataType())
					);
			case ID:
				return new SchemaIdType();
			default:
				throw new InvalidArgumentException(column);
		}
	}
	
	//method
	private EntitySet getBackReferencingEntitySetFor(BaseBackReferenceType<?> baseBackReferenceType) {
		return
		parentDatabaseSchemaAdapter
		.getRefEntitySets()
		.getRefFirst(es -> es.hasName(baseBackReferenceType.getBackReferencedEntitiesName()));
	}
	
	//method
	private EntitySet getReferencedEntitySetFor(final BaseReferenceType<?> baseReferenceType) {
		return
		parentDatabaseSchemaAdapter
		.getRefEntitySets()
		.getRefFirst(es -> es.hasName(baseReferenceType.getReferencedEntitiesName()));
	}
}
