package ch.nolix.system.noderawdata.modelmapper;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.rawdata.valuemapper.ValueMapper;
import ch.nolix.systemapi.noderawdataapi.modelmapperapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.rawdataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.valuemapperapi.IValueMapper;

public final class ContentFieldDtoMapper implements IContentFieldDtoMapper {

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  @Override
  public ContentFieldDto<Object> mapContentFieldNodeToContentFieldDto(
    final INode<?> contentFieldNode,
    final ColumnSchemaViewDto columnInfo) {

    if (contentFieldNode.containsChildNodes()) {
      return ContentFieldDto.withColumnName(columnInfo.name());
    }

    if (!contentFieldNode.hasHeader()) {
      return ContentFieldDto.withColumnName(columnInfo.name());
    }

    return ContentFieldDto.withColumnNameAndContent(
      columnInfo.name(),
      VALUE_MAPPER.mapStringToValue(contentFieldNode.getHeader(), columnInfo.dataType()));
  }
}
