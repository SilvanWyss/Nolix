//package declaration
package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;

//class
public final class LoadedRecordDTO implements ILoadedRecordDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<ILoadedContentFieldDTO> contentFields;
	
	//constructor
	public LoadedRecordDTO(
		final String id,
		final String saveStamp,
		final IContainer<ILoadedContentFieldDTO> contentFields
	) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (saveStamp == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.SAVE_STAMP);
		}
		
		if (contentFields == null) {
			throw new ArgumentIsNullException("content fields");
		}
		
		this.id = id;
		this.saveStamp = saveStamp;
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
	
	//method
	@Override
	public String getSaveStamp() {
		return saveStamp;
	}
}
