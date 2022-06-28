//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.sqlrawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
final class TableDefinitionMapper {
	
	//static attribute
	private static final ColumnDefinitionMapper columnDefinitionMapper = new ColumnDefinitionMapper();
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//method
	public ITableInfo createTableDefinitionFromTableNode(final IMutableNode<?> tableNode) {
		return new TableInfo(
			getTableIdFromTableNode(tableNode),
			getTableNameFromTableNode(tableNode),
			getContentColumnDefinitionsFromTableNode(tableNode)
		);
	}
	
	//method
	private IContainer<IColumnInfo> getContentColumnDefinitionsFromTableNode(IMutableNode<?> tableNode) {
		
		final var columnInfos = new LinkedList<IColumnInfo>();
		var columnIndexOnEntityNode = 2;
		for (final var cn : getRefColumnNodesInOrderFromTableNode(tableNode)) {
			
			columnInfos.addAtEnd(
				columnDefinitionMapper.createColumnDefinitionFromColumnNode(
					cn,
					columnIndexOnEntityNode
				)
			);
			
			columnIndexOnEntityNode++;
		}
		
		return columnInfos;
	}
	
	//method
	private IContainer<IMutableNode<?>> getRefColumnNodesInOrderFromTableNode(final IMutableNode<?> tableNode) {
		return tableNodeSearcher.getRefColumnNodesFromTableNode(tableNode);
	}
	
	//method
	private String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
		return tableNodeSearcher.getTableIdFromTableNode(tableNode);
	}
	
	//method
	private String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
		return tableNodeSearcher.getTableNameFromTableNode(tableNode);
	}
}
