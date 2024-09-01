//package declaration
package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.container.containerview.ContainerView;
//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//class
public final class EntityUpdateDto implements IEntityUpdateDto {

  //attribute
  private final String id;

  //attribute
  private final String saveStamp;

  //multi-attribute
  private final IContainer<IContentFieldDto> updatedContentFields;

  //constructor
  public EntityUpdateDto(
    final String id,
    final String saveStamp,
    final IContainer<IContentFieldDto> updatedContentFields) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ID);
    }

    if (saveStamp == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.SAVE_STAMP);
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
    final IContentFieldDto updatedContentField) {
    this(id, saveStamp, LinkedList.withElement(updatedContentField));
  }

  //constructor
  public EntityUpdateDto(
    final String id,
    final String saveStamp,
    final IContentFieldDto updatedContentField,
    final IContentFieldDto... updatedContentFields) {
    this(id, saveStamp, ContainerView.forElementAndArray(updatedContentField, updatedContentFields));
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
