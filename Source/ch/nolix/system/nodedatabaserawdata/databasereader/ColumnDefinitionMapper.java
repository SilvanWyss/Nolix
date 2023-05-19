//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.ParametrizedPropertyTypeNodeSearcher;
import ch.nolix.system.sqldatabaserawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;

//class
public final class ColumnDefinitionMapper {
	
	//static attributes
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	private static final ParametrizedPropertyTypeNodeSearcher parametrizedPropertyTypeNodeSearcher =
	new ParametrizedPropertyTypeNodeSearcher();
	
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
			columnNodeSearcher.getOriParametrizedPropertyTypeNodeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getColumnIdFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNodeSearcher.getOriIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private String getColumnNameFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNodeSearcher.getOriNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private PropertyType getColumnPropertyTypeFromColumnNode(final IMutableNode<?> columnNode) {
		
		final var parametrizedPropertyTypeNode =
		columnNodeSearcher.getOriParametrizedPropertyTypeNodeFromColumnNode(columnNode);
		
		final var propertyTypeNode =
		parametrizedPropertyTypeNodeSearcher.getOriPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
	}
	
	//method
	private DataType getDataTypeFromDataTypeNode(final IMutableNode<?> dataTypeNode) {
		return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
	}
	
	//method
	private DataType getDataTypeFromParametrizedPropertyTypeNode(IMutableNode<?> parametrizedPropertyTypeNode) {
		return
		getDataTypeFromDataTypeNode(
			parametrizedPropertyTypeNodeSearcher.getOriDataTypeNodeFromParametriedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
}
