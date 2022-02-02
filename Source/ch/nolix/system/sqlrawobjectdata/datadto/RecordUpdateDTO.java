//package declaration
package ch.nolix.system.sqlrawobjectdata.datadto;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class RecordUpdateDTO implements IRecordUpdateDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<ILoadedContentFieldDTO> updatedContentFields;
	
	//constructor
	public RecordUpdateDTO(
		final String id,
		final String saveStamp,
		final IContainer<ILoadedContentFieldDTO> updatedContentFields
	) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (saveStamp == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.SAVE_STAMP);
		}
		
		if (updatedContentFields == null) {
			throw new ArgumentIsNullException("updated content fields");
		}
		
		this.id = id;
		this.saveStamp = saveStamp;
		this.updatedContentFields = updatedContentFields;
	}
	
	//constructor
	public RecordUpdateDTO(
		final String id,
		final String saveStamp,
		final ILoadedContentFieldDTO updatedContentField
	) {
		this(id, saveStamp, LinkedList.withElements(updatedContentField));
	}
	
	//constructor
	public RecordUpdateDTO(
		final String id,
		final String saveStamp,
		final ILoadedContentFieldDTO... updatedContentFields
	) {
		this(id, saveStamp, ReadContainer.forArray(updatedContentFields));
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
	
	//method
	@Override
	public IContainer<ILoadedContentFieldDTO> getUpdatedContentFields() {
		return updatedContentFields;
	}
}
