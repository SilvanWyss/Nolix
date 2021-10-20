//package declaration
package ch.nolix.system.nodeintermediatedata.datareader;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.sqlintermediatedata.recorddto.ContentFieldDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IContentFieldDTO;

//class
public final class ContentFieldDTOMapper {
	
	//method
	public IContentFieldDTO createContentFieldDTOFromContentFieldNode(
		final String columnHeader,
		final BaseNode contentFieldNode
	) {
		
		if (!contentFieldNode.containsAttributes()) {
			return new ContentFieldDTO(columnHeader);
		}
		
		return new ContentFieldDTO(columnHeader, contentFieldNode.getRefAttributes().toString());
	}
}
