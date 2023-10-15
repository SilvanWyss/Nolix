//package declaration
package ch.nolix.system.sqldatabaserawdata.databasedto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDto;

//class
public record LoadedEntityDto(String id, String saveStamp, IContainer<ILoadedContentFieldDto> contentFields)
    implements ILoadedEntityDto {

  // constructor
  public LoadedEntityDto( // NOSONAR: This implementations checks the given arguments.
      final String id,
      final String saveStamp,
      final IContainer<ILoadedContentFieldDto> contentFields) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ID);
    }

    if (saveStamp == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.SAVE_STAMP);
    }

    if (contentFields == null) {
      throw ArgumentIsNullException.forArgumentName("content fields");
    }

    this.id = id;
    this.saveStamp = saveStamp;
    this.contentFields = contentFields;
  }

  // method
  @Override
  public IContainer<ILoadedContentFieldDto> getContentFields() {
    return contentFields;
  }

  // method
  @Override
  public String getId() {
    return id;
  }

  // method
  @Override
  public String getSaveStamp() {
    return saveStamp;
  }
}
