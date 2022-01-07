//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datareader.ValueMapper;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;

//class
public final class ContentFieldDTOMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public IContentFieldDTO createContentFieldDTOFromContentFieldNode(
		final BaseNode contentFieldNode,
		final IColumnDefinition columnDefinition
	) {
		
		if (!contentFieldNode.containsAttributes()) {
			return new ContentFieldDTO(columnDefinition.getColumnName());
		}
		
		return
		new ContentFieldDTO(
			columnDefinition.getColumnName(),
			valueMapper.createValueFromString(contentFieldNode.getRefAttributes().toString(), columnDefinition)
		);
	}
}
