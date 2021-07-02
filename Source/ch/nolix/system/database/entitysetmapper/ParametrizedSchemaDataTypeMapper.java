//package declaration
package ch.nolix.system.database.entitysetmapper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.database.databaseadapter.Column;
import ch.nolix.system.database.parametrizeddatatype.BaseParametrizedBackReferenceType;
import ch.nolix.system.database.parametrizeddatatype.BaseParametrizedReferenceType;
import ch.nolix.system.databaseschema.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaBackReferenceType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaMultiBackReferenceType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaMultiReferenceType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaMultiValueType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaOptionalBackReferenceType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaOptionalReferenceType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaOptionalValueType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaReferenceType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaValueType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.SchemaIdType;

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
	public ParametrizedPropertyType<?> createParametrizedSchemaDataTypeFor(final Column<?> column) {
		switch (column.getDataType()) {
			case VALUE:
				return new ParametrizedSchemaValueType<>(column.getRefContentClass());
			case OPTIONAL_VALUE:
				return new ParametrizedSchemaOptionalValueType<>(column.getRefContentClass());
			case MULTI_VALUE:
				return new ParametrizedSchemaMultiValueType<>(column.getRefContentClass());
			case REFERENCE:
				return
				new ParametrizedSchemaReferenceType(
					getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType())
				);
			case OPTIONAL_REFERENCE:
				return
				new ParametrizedSchemaOptionalReferenceType(
					getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType())
				);
			case MULTI_REFERENCE:
				return
				new ParametrizedSchemaMultiReferenceType(
					getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType())
				);
			case BACK_REFERENCE:
				return
				new ParametrizedSchemaBackReferenceType(
					getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType())
				);
			case OPTIONAL_BACK_REFERENCE:
				return
				new ParametrizedSchemaOptionalBackReferenceType(
					getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType())
				);
			case MULTI_BACK_REFERENCE:
				return
					new ParametrizedSchemaMultiBackReferenceType(
						getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType())
					);
			case ID:
				return new SchemaIdType();
			default:
				throw new InvalidArgumentException(column);
		}
	}
	
	//method
	private EntitySet getBackReferencingEntitySetFor(BaseParametrizedBackReferenceType<?> baseBackReferenceType) {
		return
		parentDatabaseSchemaAdapter
		.getRefEntitySets()
		.getRefFirst(es -> es.hasName(baseBackReferenceType.getBackReferencedEntitiesName()));
	}
	
	//method
	private EntitySet getReferencedEntitySetFor(final BaseParametrizedReferenceType<?> baseReferenceType) {
		return
		parentDatabaseSchemaAdapter
		.getRefEntitySets()
		.getRefFirst(es -> es.hasName(baseReferenceType.getReferencedEntitiesName()));
	}
}
