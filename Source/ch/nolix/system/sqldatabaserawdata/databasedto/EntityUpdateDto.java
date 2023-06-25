//package declaration
package ch.nolix.system.sqldatabaserawdata.databasedto;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class EntityUpdateDto implements IEntityUpdateDto {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<IContentFieldDto> updatedContentFields;
	
	//constructor
	public EntityUpdateDto(
		final String id,
		final String saveStamp,
		final IContainer<IContentFieldDto> updatedContentFields
	) {
		
		if (id == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ID);
		}
		
		if (saveStamp == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.SAVE_STAMP);
		}
		
		if (updatedContentFields == null) {
			throw ArgumentIsNullException.forArgumentName("updated content fields");
		}
		
		this.id = id;
		this.saveStamp = saveStamp;
		this.updatedContentFields = updatedContentFields;
	}
	
	//constructor
	public EntityUpdateDto(
		final String id,
		final String saveStamp,
		final IContentFieldDto updatedContentField
	) {
		this(id, saveStamp, LinkedList.withElements(updatedContentField));
	}
	
	//constructor
	public EntityUpdateDto(
		final String id,
		final String saveStamp,
		final IContentFieldDto... updatedContentFields
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
	public IContainer<IContentFieldDto> getUpdatedContentFields() {
		return updatedContentFields;
	}
}