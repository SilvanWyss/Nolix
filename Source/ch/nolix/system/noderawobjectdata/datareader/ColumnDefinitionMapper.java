//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.noderawobjectschema.structure.ColumnNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.ParametrizedPropertyTypeNodeSearcher;
import ch.nolix.system.sqlrawobjectdata.schema.ColumnDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;

//class
public final class ColumnDefinitionMapper {
	
	//static attributes
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	private static final ParametrizedPropertyTypeNodeSearcher parametrizedPropertyTypeNodeSearcher =
	new ParametrizedPropertyTypeNodeSearcher();
	
	//method
	public IColumnDefinition createColumnDefinitionFromColumnNode(final BaseNode columnNode) {
		return
		new ColumnDefinition(
			getColumnNameFromColumnNode(columnNode),
			getDataTypeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getColumnNameFromColumnNode(final BaseNode columnNode) {
		return columnNodeSearcher.getRefNameNodeFromColumnNode(columnNode).getOneAttributeHeader();
	}
	
	//method
	private DataType getDataTypeFromColumnNode(final BaseNode columnNode) {
		return
		getDataTypeFromParametrizedPropertyTypeNode(
			columnNodeSearcher.getRefParametrizedPropertyTypeNodeFromColumnNode(columnNode)
		);
	}
	
	//method
	private DataType getDataTypeFromDataTypeNode(final BaseNode dataTypeNode) {
		return DataType.valueOf(dataTypeNode.getOneAttributeHeader());
	}
	
	//method
	private DataType getDataTypeFromParametrizedPropertyTypeNode(BaseNode parametrizedPropertyTypeNode) {
		return
		getDataTypeFromDataTypeNode(
			parametrizedPropertyTypeNodeSearcher.getRefDataTypeNodeFromParametriedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
}
