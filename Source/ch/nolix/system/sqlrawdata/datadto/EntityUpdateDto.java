package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

public final class EntityUpdateDto implements IEntityUpdateDto {

  private final String id;

  private final String saveStamp;

  private final IContainer<ContentFieldDto> updatedContentFields;

  public EntityUpdateDto(
    final String id,
    final String saveStamp,
    final IContainer<ContentFieldDto> updatedContentFields) {

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

  public EntityUpdateDto(
    final String id,
    final String saveStamp,
    final ContentFieldDto updatedContentField) {
    this(id, saveStamp, LinkedList.withElement(updatedContentField));
  }

  public EntityUpdateDto(
    final String id,
    final String saveStamp,
    final ContentFieldDto updatedContentField,
    final ContentFieldDto... updatedContentFields) {
    this(id, saveStamp, ContainerView.forElementAndArray(updatedContentField, updatedContentFields));
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getSaveStamp() {
    return saveStamp;
  }

  @Override
  public IContainer<ContentFieldDto> getUpdatedContentFields() {
    return updatedContentFields;
  }
}
