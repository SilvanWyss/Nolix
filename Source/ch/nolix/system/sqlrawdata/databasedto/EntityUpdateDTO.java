//package declaration
package ch.nolix.system.sqlrawdata.databasedto;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//class
public final class EntityUpdateDTO implements IEntityUpdateDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<IContentFieldDTO> updatedContentFields;
	
	//constructor
	public EntityUpdateDTO(
		final String id,
		final String saveStamp,
		final IContainer<IContentFieldDTO> updatedContentFields
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
	public EntityUpdateDTO(
		final String id,
		final String saveStamp,
		final IContentFieldDTO updatedContentField
	) {
		this(id, saveStamp, LinkedList.withElements(updatedContentField));
	}
	
	//constructor
	public EntityUpdateDTO(
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
