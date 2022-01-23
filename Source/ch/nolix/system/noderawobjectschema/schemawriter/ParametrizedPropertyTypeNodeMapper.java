//package declaration
package ch.nolix.system.noderawobjectschema.schemawriter;

//own imports
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ParametrizedPropertyTypeNodeMapper {
	
	//method
	public Node createParametrizedPropertyTypeNodeFrom(final IParametrizedPropertyTypeDTO parametrizedPropertyType) {
		switch (parametrizedPropertyType.getPropertyType().getBaseType()) {
			case BASE_VALUE:
				return createParametrizedPropertyTypeNodeFrom((IBaseParametrizedValueTypeDTO)parametrizedPropertyType);
			case BASE_REFERENCE:
				return createParametrizedPropertyTypeNodeFrom((IBaseParametrizedReferenceTypeDTO)parametrizedPropertyType);
			case BASE_BACK_REFERENCE:
				return createParametrizedPropertyTypeNodeFrom((IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyType);
			default:
				throw new InvalidArgumentException(parametrizedPropertyType);
		}
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(
		IBaseParametrizedBackReferenceTypeDTO baseParametrizedBackReferenceType
	) {
		return
		Node.withHeaderAndAttribute(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.PROPERTY_TYPE,
				baseParametrizedBackReferenceType.getPropertyType().toString()
			),
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.DATA_TYPE,
				baseParametrizedBackReferenceType.getDataTypeFullClassName()
			),
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID,
				baseParametrizedBackReferenceType.getBackReferencedColumnId()
			)
		);
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(IBaseParametrizedReferenceTypeDTO baseParametrizedReferenceType) {
		return
		Node.withHeaderAndAttribute(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.PROPERTY_TYPE,
				baseParametrizedReferenceType.getPropertyType().toString()
			),
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.DATA_TYPE,
				baseParametrizedReferenceType.getDataTypeFullClassName()
			),
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.REFERENCED_TABLE_ID,
				baseParametrizedReferenceType.getReferencedTableId()
			)
		);
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(final IBaseParametrizedValueTypeDTO baseParametrizedValueType) {
		return
		Node.withHeaderAndAttribute(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.PROPERTY_TYPE,
				baseParametrizedValueType.getPropertyType().toString()
			),
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.DATA_TYPE,
				baseParametrizedValueType.getDataTypeFullClassName()
			)
		);
	}
}
