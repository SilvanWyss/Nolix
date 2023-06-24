//package declaration
package ch.nolix.system.sqldatabaserawdata.databasedto;

import ch.nolix.core.container.immutablelist.ImmutableList;
//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;

//class
public record NewEntityDto(String id,  ImmutableList<IContentFieldDto> contentFields) implements INewEntityDto {
	
	//constructor
	public NewEntityDto(final String id, final IContainer<IContentFieldDto> contentFields) {
		this(id, ImmutableList.forIterable(contentFields));
	}
	
	//constructor
	public NewEntityDto(final String id, final ImmutableList<IContentFieldDto> contentFields) { //NOSONAR
		
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
	public IContainer<IContentFieldDto> getContentFields() {
		return contentFields;
	}
	
	//method
	@Override
	public String getId() {
		return id;
	}
}
