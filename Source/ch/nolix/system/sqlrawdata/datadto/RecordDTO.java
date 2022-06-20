//package declaration
package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;

//class
public final class RecordDTO implements IRecordDTO {
	
	//attributes
	private final String id;
	
	//multi-attribute
	private final IContainer<IContentFieldDTO> contentFields;
	
	//constructor
	public RecordDTO(final String id, final IContainer<IContentFieldDTO> contentFields) {
		
		if (id == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ID);
		}
		
		if (contentFields == null) {
			throw ArgumentIsNullException.forArgumentName("content fields");
		}
		
		this.id = id;
		this.contentFields = contentFields;
	}
	
	//method
	@Override
	public IContainer<IContentFieldDTO> getContentFields() {
		return contentFields;
	}
	
	//method
	@Override
	public String getId() {
		return id;
	}
}
