//package declaration
package ch.nolix.system.objectdatabase.schemamapper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedMultiBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedMultiReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedMultiValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedOptionalBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedOptionalReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedOptionalValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedValueType;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.IParametrizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParametrizedPropertyTypeMapper implements IParametrizedPropertyTypeMapper {
	
	//constant
	private static final IPropertyHelper PROPERTY_HELPER = new PropertyHelper();
	
	//method
	@Override
	public IParameterizedPropertyType
	createParametrizedPropertyTypeFromGivenPropertyUsingGivenReferencableTables(
		final IProperty property,
		final IContainer<ITable> referencableTables
	) {
		switch (property.getType()) {
			case VALUE:
				return new ParameterizedValueType<>(DataType.forType(PROPERTY_HELPER.getDataType(property)));
			case OPTIONAL_VALUE:
				return new ParameterizedOptionalValueType<>(DataType.forType(PROPERTY_HELPER.getDataType(property)));
			case MULTI_VALUE:
				return new ParameterizedMultiValueType<>(DataType.forType(PROPERTY_HELPER.getDataType(property)));
			case REFERENCE:
										
				final var reference = (IReference<?>)property;
				
				return
				new ParameterizedReferenceType(
					referencableTables.getStoredFirst(t -> t.hasName(reference.getReferencedTableName()))
				);
			case OPTIONAL_REFERENCE:
				
				final var optionalReference = (IOptionalReference<?>)property;
				
				return
				new ParameterizedOptionalReferenceType(
					referencableTables.getStoredFirst(t -> t.hasName(optionalReference.getReferencedTableName()))
				);
			case MULTI_REFERENCE:
				
				final var multiReference = (IMultiReference<?>)property;
				
				return
				new ParameterizedMultiReferenceType(
					referencableTables.getStoredFirst(t -> t.hasName(multiReference.getReferencedTableName()))
				);
			case BACK_REFERENCE:
				
				final var backReference = (IBackReference<?>)property;
				
				return
				new ParameterizedBackReferenceType(
					referencableTables
					.getStoredFirst(t -> t.hasName(backReference.getBackReferencedTableName()))
					.getStoredColumns()
					.getStoredFirst(c -> c.hasName(backReference.getBackReferencedPropertyName()))
				);
			case OPTIONAL_BACK_REFERENCE:
				
				final var optionalBackReference = (IOptionalBackReference<?>)property;
				
				return
				new ParameterizedOptionalBackReferenceType(
					referencableTables
					.getStoredFirst(t -> t.hasName(optionalBackReference.getBackReferencedTableName()))
					.getStoredColumns()
					.getStoredFirst(c -> c.hasName(optionalBackReference.getBackReferencedPropertyName()))
				);
			case MULTI_BACK_REFERENCE:
				
				final var multiBackReference = (IMultiBackReference<?>)property;
				
				return
				new ParameterizedMultiBackReferenceType(
					referencableTables
					.getStoredFirst(t -> t.hasName(multiBackReference.getBackReferencedTableName()))
					.getStoredColumns()
					.getStoredFirst(c -> c.hasName(multiBackReference.getBackReferencedPropertyName()))
				);
			default:
				throw InvalidArgumentException.forArgument(property);
		}
	}
}
