//package declaration
package ch.nolix.system.nodeintermediateschema.schemawriter;

//own imports
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.nodeintermediateschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IBaseParametrizedControlTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ParametrizedPropertyTypeNodeMapper {
	
	//method
	public Node createParametrizedPropertyTypeNodeFrom(final IParametrizedPropertyTypeDTO parametrizedPropertyType) {
		switch (parametrizedPropertyType.getPropertyType().getBaseType()) {
			case BASE_VALUE:
				return createParametrizedPropertyTypeFrom((IBaseParametrizedValueTypeDTO)parametrizedPropertyType);
			case BASE_REFERENCE:
				return createParametrizedPropertyTypeFrom((IBaseParametrizedReferenceTypeDTO)parametrizedPropertyType);
			case BASE_BACK_REFERENCE:
				return createParametrizedPropertyTypeFrom((IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyType);
			case BASE_CONTROL_TYPE:
				return createParametrizedPropertyTypeFrom((IBaseParametrizedControlTypeDTO)parametrizedPropertyType);
			default:
				throw new InvalidArgumentException(parametrizedPropertyType);
		}
	}
	
	//method
	private Node createParametrizedPropertyTypeFrom(IBaseParametrizedBackReferenceTypeDTO baseParametrizedBackReferenceType) {
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
				SubNodeHeaderCatalogue.BACK_REFERENCED_TABLE,
				baseParametrizedBackReferenceType.getBackReferencedTableName()
			),
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN,
				baseParametrizedBackReferenceType.getBackReferencedColumnHeader()
			)
		);
	}
	
	//method
	private Node createParametrizedPropertyTypeFrom(IBaseParametrizedControlTypeDTO baseParametrizedControlType) {
		return
		Node.withHeaderAndAttribute(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.PROPERTY_TYPE,
				baseParametrizedControlType.getPropertyType().toString()
			),
			Node.withHeaderAndAttribute(
				SubNodeHeaderCatalogue.DATA_TYPE,
				baseParametrizedControlType.getDataTypeFullClassName()
			)
		);
	}
	
	//method
	private Node createParametrizedPropertyTypeFrom(IBaseParametrizedReferenceTypeDTO baseParametrizedReferenceType) {
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
				SubNodeHeaderCatalogue.REFERENCED_TABLE,
				baseParametrizedReferenceType.getReferencedTableName()
			)
		);
	}
	
	//method
	private Node createParametrizedPropertyTypeFrom(final IBaseParametrizedValueTypeDTO baseParametrizedValueType) {
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
