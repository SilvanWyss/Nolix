//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.noderawobjectschema.structure.ColumnNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.ParametrizedPropertyTypeNodeSearcher;
import ch.nolix.system.sqlrawobjectdata.schema.ColumnDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.databaseapi.datatypeapi.DataType;

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
			getColumnHeaderFromColumnNode(columnNode),
			getDataTypeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getColumnHeaderFromColumnNode(final BaseNode columnNode) {
		return columnNodeSearcher.getRefHeaderNodeFromColumnNode(columnNode).getOneAttributeHeader();
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
			parametrizedPropertyTypeNodeSearcher.getDataTypeNodeFromParametriedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
}
