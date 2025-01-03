package ch.nolix.systemapi.rawdataapi.dto;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityUpdateDto(
String id,
String saveStamp,
IContainer<StringContentFieldDto> updatedContentFields) {

  public EntityUpdateDto(String id, String saveStamp, StringContentFieldDto updatedContentField) {
    this(id, saveStamp, ImmutableList.withElement(updatedContentField));
  }
}
