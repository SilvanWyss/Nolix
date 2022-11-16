//package declaration
package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.container.immutablelist.ImmutableList;
//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordDTO;

//class
public record RecordDTO(String id,  ImmutableList<IContentFieldDTO> contentFields) implements IRecordDTO {
	
	//constructor
	public RecordDTO(final String id, final IContainer<IContentFieldDTO> contentFields) {
		this(id, ImmutableList.forIterable(contentFields));
	}
	
	//constructor
	public RecordDTO(final String id, final ImmutableList<IContentFieldDTO> contentFields) { //NOSONAR
		
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
