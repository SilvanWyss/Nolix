package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.midschema.model.BackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiValueModelDto;
import ch.nolix.systemapi.midschema.model.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalValueModelDto;
import ch.nolix.systemapi.midschema.model.ReferenceModelDto;
import ch.nolix.systemapi.midschema.model.ValueModelDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

public final class ContentModelNodeMapperHelper {

  private ContentModelNodeMapperHelper() {
  }

  public static Node mapBackRefernceModelDtoToNode(final BackReferenceModelDto backReferenceModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        backReferenceModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        backReferenceModelDto.dataType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.BACK_REFERENCED_COLUMN_ID,
        backReferenceModelDto.backReferencedColumnId()));
  }

  public static Node mapMultiBackRefernceModelDtoToNode(final MultiBackReferenceModelDto multiBackReferenceModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        multiBackReferenceModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        multiBackReferenceModelDto.dataType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.BACK_REFERENCED_COLUMN_ID,
        multiBackReferenceModelDto.backReferencedColumnId()));
  }

  public static Node mapMultiRefernceModelDtoToNode(final MultiReferenceModelDto multiReferenceModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        multiReferenceModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        multiReferenceModelDto.dataType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.REFERENCED_TABLE_ID,
        multiReferenceModelDto.referencedTableId()));
  }

  public static Node mapMultiValueModelDtoToNode(final MultiValueModelDto multiValueModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        multiValueModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        multiValueModelDto.dataType().name()));
  }

  public static Node mapOptionalBackRefernceModelDtoToNode(
    final OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        optionalBackReferenceModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        optionalBackReferenceModelDto.dataType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.BACK_REFERENCED_COLUMN_ID,
        optionalBackReferenceModelDto.backReferencedColumnId()));
  }

  public static Node mapOptionalRefernceModelDtoToNode(final OptionalReferenceModelDto optionalReferenceModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        optionalReferenceModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        optionalReferenceModelDto.dataType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.REFERENCED_TABLE_ID,
        optionalReferenceModelDto.referencedTableId()));
  }

  public static Node mapOptionalValueModelDtoToNode(final OptionalValueModelDto optionalValueModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        optionalValueModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        optionalValueModelDto.dataType().name()));
  }

  public static Node mapRefernceModelDtoToNode(final ReferenceModelDto referenceModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        referenceModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        referenceModelDto.dataType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.REFERENCED_TABLE_ID,
        referenceModelDto.referencedTableId()));
  }

  public static Node mapValueModelDtoToNode(final ValueModelDto valueModelDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_TYPE,
        valueModelDto.getFieldType().name()),
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.DATA_TYPE,
        valueModelDto.dataType().name()));
  }
}
