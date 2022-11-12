//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.sqlrawdata.datadto.LoadedContentFieldDTO;
import ch.nolix.system.sqlrawdata.datareader.ValueMapper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldDTOMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public ILoadedContentFieldDTO createContentFieldDTOFromContentFieldNode(
		final IMutableNode<?> contentFieldNode,
		final IColumnInfo columnInfo
	) {
		
		if (contentFieldNode.containsChildNodes()) {
			return new LoadedContentFieldDTO(columnInfo.getColumnName());
		}
		
		return
		new LoadedContentFieldDTO(
			columnInfo.getColumnName(),
			valueMapper.createValueFromString(contentFieldNode.getHeader(), columnInfo)
		);
	}
}
