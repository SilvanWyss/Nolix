//package declaration
package ch.nolix.system.sqldatabaserawdata.databasedto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;

//class
public record EntityHeadDto(String id, String saveStamp) implements IEntityHeadDto {

  //constructor
  public EntityHeadDto(final String id, final String saveStamp) { //NOSONAR: This implementations checks the given
                                                                  //arguments.

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ID);
    }

    if (saveStamp == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.SAVE_STAMP);
    }

    this.id = id;
    this.saveStamp = saveStamp;
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
