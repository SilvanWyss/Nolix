//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.structure.EntityNodeSearcher;
import ch.nolix.system.sqldatabaserawdata.databasedto.LoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class LoadedEntityDTOMapper {
	
	//static attributes
	private static final ContentFieldDTOMapper contentFieldDTOMapper = new ContentFieldDTOMapper();
	private static final EntityNodeSearcher entityNodeSearcher = new EntityNodeSearcher();
	
	//method
	public ILoadedEntityDTO createLoadedEntityDTOFromEntityNode(
		final IMutableNode<?> entityNode,
		final ITableInfo tableInfo
	) {
		return
		new LoadedEntityDTO(
			getIdFromEntityNode(entityNode),
			getSaveStampFromEntityNode(entityNode),
			createContentFieldsFromEntityNode(entityNode, tableInfo)
		);
	}
	
	//method
	private IContainer<ILoadedContentFieldDTO> createContentFieldsFromEntityNode(
		final IMutableNode<?> entityNode,
		final ITableInfo tableInfo
	) {
		
		final var contentFields = new LinkedList<ILoadedContentFieldDTO>();
		for (final var ci : tableInfo.getColumnInfos()) {
			
			final var contentFieldNode = entityNode.getRefChildNodeAt1BasedIndex(ci.getColumnIndexOnEntityNode());
			
			contentFields.addAtEnd(contentFieldDTOMapper.createContentFieldDTOFromContentFieldNode(contentFieldNode, ci));
		}
		
		return contentFields;
	}
	
	//method
	private String getIdFromEntityNode(final IMutableNode<?> entityNode) {
		
		final var idNode = entityNodeSearcher.getRefIdNodeFromEntityNode(entityNode);
		
		return idNode.getHeader();
	}
	
	//method
	private String getSaveStampFromEntityNode(IMutableNode<?> entityNode) {
		
		final var saveStampNode = entityNodeSearcher.getRefSaveStampNodeFromEntityNode(entityNode);
		
		return saveStampNode.getHeader();
	}
}
