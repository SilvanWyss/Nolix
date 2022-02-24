//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.ParametrizedPropertyTypeNodeSearcher;
import ch.nolix.system.sqlrawobjectdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;

//class
public final class ColumnDefinitionMapper {
	
	//static attributes
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	private static final ParametrizedPropertyTypeNodeSearcher parametrizedPropertyTypeNodeSearcher =
	new ParametrizedPropertyTypeNodeSearcher();
	
	//method
	public IColumnInfo createColumnDefinitionFromColumnNode(final BaseNode columnNode) {
		return
		new ColumnInfo(
			getColumnIdFromColumnNode(columnNode),
			getColumnNameFromColumnNode(columnNode),
			getDataTypeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getColumnIdFromColumnNode(final BaseNode columnNode) {
		return columnNodeSearcher.getRefIdNodeFromColumnNode(columnNode).getOneAttributeHeader();
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
