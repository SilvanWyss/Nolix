//package declaration
package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class RecordUpdateDTO implements IRecordUpdateDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<IContentFieldDTO> updatedContentFields;
	
	//constructor
	public RecordUpdateDTO(
		final String id,
		final String saveStamp,
		final IContainer<IContentFieldDTO> updatedContentFields
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
		final IContentFieldDTO updatedContentField
	) {
		this(id, saveStamp, LinkedList.withElements(updatedContentField));
	}
	
	//constructor
	public RecordUpdateDTO(
		final String id,
		final String saveStamp,
		final IContentFieldDTO... updatedContentFields
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
	public IContainer<IContentFieldDTO> getUpdatedContentFields() {
		return updatedContentFields;
	}
}
