//package declaration
package ch.nolix.system.objectdatabase.schemamapper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
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
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParametrizedPropertyTypeMapper implements IParametrizedPropertyTypeMapper {
	
	//constant
	private static final IPropertyHelper PROPERTY_HELPER = new PropertyHelper();
	
	//method
	@Override
	public IParametrizedPropertyType
	createParametrizedPropertyTypeFromGivenPropertyUsingGivenReferencableTables(
		final IProperty property,
		final IContainer<ITable> referencableTables
	) {
		switch (property.getType()) {
			case VALUE:
				return new ParametrizedValueType<>(DataType.forType(PROPERTY_HELPER.getDataType(property)));
			case OPTIONAL_VALUE:
				return new ParametrizedOptionalValueType<>(DataType.forType(PROPERTY_HELPER.getDataType(property)));
			case MULTI_VALUE:
				return new ParametrizedMultiValueType<>(DataType.forType(PROPERTY_HELPER.getDataType(property)));
			case REFERENCE:
										
				final var reference = (IReference<?>)property;
				
				return
				new ParametrizedReferenceType(
					referencableTables.getStoredFirst(t -> t.hasName(reference.getReferencedTableName()))
				);
			case OPTIONAL_REFERENCE:
				
				final var optionalReference = (IOptionalReference<?>)property;
				
				return
				new ParametrizedOptionalReferenceType(
					referencableTables.getStoredFirst(t -> t.hasName(optionalReference.getReferencedTableName()))
				);
			case MULTI_REFERENCE:
				
				final var multiReference = (IMultiReference<?>)property;
				
				return
				new ParametrizedMultiReferenceType(
					referencableTables.getStoredFirst(t -> t.hasName(multiReference.getReferencedTableName()))
				);
			case BACK_REFERENCE:
				
				final var backReference = (IBackReference<?>)property;
				
				return
				new ParametrizedBackReferenceType(
					referencableTables
					.getStoredFirst(t -> t.hasName(backReference.getBackReferencedTableName()))
					.getStoredColumns()
					.getStoredFirst(c -> c.hasName(backReference.getBackReferencedPropertyName()))
				);
			case OPTIONAL_BACK_REFERENCE:
				
				final var optionalBackReference = (IOptionalBackReference<?>)property;
				
				return
				new ParametrizedOptionalBackReferenceType(
					referencableTables
					.getStoredFirst(t -> t.hasName(optionalBackReference.getBackReferencedTableName()))
					.getStoredColumns()
					.getStoredFirst(c -> c.hasName(optionalBackReference.getBackReferencedPropertyName()))
				);
			case MULTI_BACK_REFERENCE:
				
				final var multiBackReference = (IMultiBackReference<?>)property;
				
				return
				new ParametrizedMultiBackReferenceType(
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
