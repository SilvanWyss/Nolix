//package declaration
package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdata.propertyhelper.PropertyHelper;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IParametrizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParametrizedPropertyTypeMapper implements IParametrizedPropertyTypeMapper<SchemaImplementation> {
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//method
	@Override
	public IParametrizedPropertyType<SchemaImplementation>
	createParametrizedPropertyTypeFromGivenPropertyUsingGivenReferencableTables(
		final IProperty<?> property,
		final IContainer<ITable<SchemaImplementation>> referencableTables
	) {
		switch (property.getType()) {
			case VALUE:
				return new ParametrizedValueType<>(DataType.forType(propertyHelper.getDataType(property)));
			case OPTIONAL_VALUE:
				return new ParametrizedOptionalValueType<>(DataType.forType(propertyHelper.getDataType(property)));
			case MULTI_VALUE:
				return new ParametrizedMultiValueType<>(DataType.forType(propertyHelper.getDataType(property)));
			case REFERENCE:
										
				final var reference = (IReference<?, ?>)property;
				
				return
				new ParametrizedReferenceType(
					referencableTables.getRefFirst(t -> t.hasName(reference.getReferencedTableName()))
				);
			case OPTIONAL_REFERENCE:
				
				final var optionalReference = (IOptionalReference<?, ?>)property;
				
				return
				new ParametrizedOptionalReferenceType(
					referencableTables.getRefFirst(t -> t.hasName(optionalReference.getReferencedTableName()))
				);
			case MULTI_REFERENCE:
				
				final var multiReference = (IMultiReference<?, ?>)property;
				
				return
				new ParametrizedMultiReferenceType(
					referencableTables.getRefFirst(t -> t.hasName(multiReference.getReferencedTableName()))
				);
			case BACK_REFERENCE:
				
				final var backReference = (IBackReference<?, ?>)property;
				
				return
				new ParametrizedBackReferenceType(
					referencableTables
					.getRefFirst(t -> t.hasName(backReference.getBackReferencedTableName()))
					.getRefColumns()
					.getRefFirst(c -> c.hasName(backReference.getBackReferencedPropertyName()))
				);
			case OPTIONAL_BACK_REFERENCE:
				
				final var optionalBackReference = (IOptionalBackReference<?, ?>)property;
				
				return
				new ParametrizedOptionalBackReferenceType(
					referencableTables
					.getRefFirst(t -> t.hasName(optionalBackReference.getBackReferencedTableName()))
					.getRefColumns()
					.getRefFirst(c -> c.hasName(optionalBackReference.getBackReferencedPropertyName()))
				);
			case MULTI_BACK_REFERENCE:
				
				final var multiBackReference = (IMultiBackReference<?, ?>)property;
				
				return
				new ParametrizedMultiBackReferenceType(
					referencableTables
					.getRefFirst(t -> t.hasName(multiBackReference.getBackReferencedTableName()))
					.getRefColumns()
					.getRefFirst(c -> c.hasName(multiBackReference.getBackReferencedPropertyName()))
				);
			default:
				throw InvalidArgumentException.forArgument(property);
		}
	}
}
