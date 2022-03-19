//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

//own imports
import ch.nolix.core.document.node.BaseNode;
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
		final BaseNode contentFieldNode,
		final IColumnInfo columnInfo
	) {
		
		if (!contentFieldNode.containsAttributes()) {
			return new LoadedContentFieldDTO(columnInfo.getColumnName());
		}
		
		return
		new LoadedContentFieldDTO(
			columnInfo.getColumnName(),
			valueMapper.createValueFromString(contentFieldNode.getRefAttributes().toString(), columnInfo)
		);
	}
}
