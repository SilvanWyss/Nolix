//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.ITableHelper;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ParametrizedPropertyTypeMapper {
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//method
	public ParametrizedPropertyType<?> createParametrizedPropertyTypeFromDTO(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		switch (parametrizedPropertyType.getPropertyType()) {
			case VALUE:
				return new ParametrizedValueType<>(getValueClassFrom(parametrizedPropertyType));
			case OPTIONAL_VALUE:
				return new ParametrizedOptionalValueType<>(getValueClassFrom(parametrizedPropertyType));
			case MULTI_VALUE:
				return new ParametrizedMultiValueType<>(getValueClassFrom(parametrizedPropertyType));
			case REFERENCE:
				return
				new ParametrizedReferenceType(
					getReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_REFERENCE:
				return
				new ParametrizedOptionalReferenceType(
					getReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_REFERENCE:
				return
				new ParametrizedMultiReferenceType(
					getReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case BACK_REFERENCE:
				return
				new ParametrizedBackReferenceType(
					getBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_BACK_REFERENCE:
				return
				new ParametrizedOptionalBackReferenceType(
					getBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_BACK_REFERENCE:
				return
				new ParametrizedMultiBackReferenceType(
					getBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			default:
				throw new InvalidArgumentException(parametrizedPropertyType);
		}
	}
	
	//method
	private Column getBackReferencedColumnFromParametrizedPropertyType(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedBackReferenceType = (IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyType;
		
		final var backReferencedTable = 
		tables.getRefFirst(t -> t.hasName(baseParametrizedBackReferenceType.getBackReferencedTableName()));
		
		return
		(Column)
		tableHelper.getRefColumnWithGivenHeader(
			backReferencedTable,
			baseParametrizedBackReferenceType.getBackReferencedColumnHeader()
		);
	}
	
	//method
	private ITable getReferencedTableFromParametrizedPropertyType(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedReferenceType = (IBaseParametrizedReferenceTypeDTO)parametrizedPropertyType;
		
		return tables.getRefFirst(t -> t.hasName(baseParametrizedReferenceType.getReferencedTableName()));
	}
	
	//method
	private Class<?> getValueClassFrom(final IParametrizedPropertyTypeDTO parametrizedPropertyType) {
		return
		ValueClassCatalogue.VALUE_CLASSES.getRefFirst(
			vc -> vc.getName().equals(parametrizedPropertyType.getDataTypeFullClassName())
		);
	}
}
