//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.sqldatabaserawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

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
		var columnIndexOnEntityNode = 3;
		for (final var cn : getOriColumnNodesInOrderFromTableNode(tableNode)) {
			
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
	private IContainer<? extends IMutableNode<?>> getOriColumnNodesInOrderFromTableNode(final IMutableNode<?> tableNode) {
		return tableNodeSearcher.getOriColumnNodesFromTableNode(tableNode);
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
