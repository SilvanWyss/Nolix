//package declaration
package ch.nolix.system.sqlrawobjectdata.datadto;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;

//class
public final class RecordDTO implements IRecordDTO {
	
	//attributes
	private final String id;
	
	//multi-attribute
	private final IContainer<ILoadedContentFieldDTO> contentFields;
	
	//constructor
	public RecordDTO(final String id, final IContainer<ILoadedContentFieldDTO> contentFields) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (contentFields == null) {
			throw new ArgumentIsNullException("content fields");
		}
		
		this.id = id;
		this.contentFields = contentFields;
	}
	
	//method
	@Override
	public IContainer<ILoadedContentFieldDTO> getContentFields() {
		return contentFields;
	}
	
	//method
	@Override
	public String getId() {
		return id;
	}
}
