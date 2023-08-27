//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.ParameterizedPropertyTypeNodeSearcher;
import ch.nolix.system.sqldatabaserawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;

//class
public final class ColumnDefinitionMapper {
	
	//constant
	private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();
	
	//constant
	private static final ParameterizedPropertyTypeNodeSearcher PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER =
	new ParameterizedPropertyTypeNodeSearcher();
	
	//method
	public IColumnInfo createColumnDefinitionFromColumnNode(
		final IMutableNode<?> columnNode,
		final int columnIndexOnEntityNode
	) {
		return
		new ColumnInfo(
			getColumnIdFromColumnNode(columnNode),
			getColumnNameFromColumnNode(columnNode),
			getColumnPropertyTypeFromColumnNode(columnNode),
			getColumnDataTypeFromColumnNode(columnNode),
			columnIndexOnEntityNode
		);
	}
	
	//method
	private DataType getColumnDataTypeFromColumnNode(final IMutableNode<?> columnNode) {
		return
		getDataTypeFromParametrizedPropertyTypeNode(
			COLUMN_NODE_SEARCHER.getStoredParametrizedPropertyTypeNodeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getColumnIdFromColumnNode(final IMutableNode<?> columnNode) {
		return COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private String getColumnNameFromColumnNode(final IMutableNode<?> columnNode) {
		return COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private PropertyType getColumnPropertyTypeFromColumnNode(final IMutableNode<?> columnNode) {
		
		final var parameterizedPropertyTypeNode =
		COLUMN_NODE_SEARCHER.getStoredParametrizedPropertyTypeNodeFromColumnNode(columnNode);
		
		final var propertyTypeNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parameterizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
	}
	
	//method
	private DataType getDataTypeFromDataTypeNode(final IMutableNode<?> dataTypeNode) {
		return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
	}
	
	//method
	private DataType getDataTypeFromParametrizedPropertyTypeNode(IMutableNode<?> parameterizedPropertyTypeNode) {
		return
		getDataTypeFromDataTypeNode(
			PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredDataTypeNodeFromParametriedPropertyTypeNode(
				parameterizedPropertyTypeNode
			)
		);
	}
}
