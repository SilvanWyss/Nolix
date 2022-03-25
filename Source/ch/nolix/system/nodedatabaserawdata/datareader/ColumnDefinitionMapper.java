//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.ParametrizedPropertyTypeNodeSearcher;
import ch.nolix.system.sqlrawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public final class ColumnDefinitionMapper {
	
	//static attributes
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	private static final ParametrizedPropertyTypeNodeSearcher parametrizedPropertyTypeNodeSearcher =
	new ParametrizedPropertyTypeNodeSearcher();
	
	//method
	public IColumnInfo createColumnDefinitionFromColumnNode(
		final BaseNode columnNode,
		final int columnZeroBasedIndexOfCellInEntityArray
	) {
		return
		new ColumnInfo(
			getColumnIdFromColumnNode(columnNode),
			getColumnNameFromColumnNode(columnNode),
			getColumnPropertyTypeFromColumnNode(columnNode),
			getColumnDataTypeFromColumnNode(columnNode),
			columnZeroBasedIndexOfCellInEntityArray
		);
	}
	
	//method
	private DataType getColumnDataTypeFromColumnNode(final BaseNode columnNode) {
		return
		getDataTypeFromParametrizedPropertyTypeNode(
			columnNodeSearcher.getRefParametrizedPropertyTypeNodeFromColumnNode(columnNode)
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
	private PropertyType getColumnPropertyTypeFromColumnNode(final BaseNode columnNode) {
		
		final var parametrizedPropertyTypeNode =
		columnNodeSearcher.getRefParametrizedPropertyTypeNodeFromColumnNode(columnNode);
		
		final var propertyTypeNode =
		parametrizedPropertyTypeNodeSearcher.getRefPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
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
