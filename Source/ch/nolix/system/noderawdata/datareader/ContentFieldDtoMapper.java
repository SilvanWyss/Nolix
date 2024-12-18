package ch.nolix.system.noderawdata.datareader;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.sqlrawdata.datareader.ValueMapper;
import ch.nolix.systemapi.noderawdataapi.datareaderapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

public final class ContentFieldDtoMapper implements IContentFieldDtoMapper {

  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  @Override
  public ContentFieldDto<Object> mapContentFieldNodeToContentFieldDto(
    final INode<?> contentFieldNode,
    final IColumnInfo columnInfo) {

    if (contentFieldNode.containsChildNodes()) {
      return ContentFieldDto.withColumnName(columnInfo.getColumnName());
    }

    if (!contentFieldNode.hasHeader()) {
      return ContentFieldDto.withColumnName(columnInfo.getColumnName());
    }

    return ContentFieldDto.withColumnNameAndContent(
      columnInfo.getColumnName(),
      VALUE_MAPPER.createValueFromString(contentFieldNode.getHeader(), columnInfo));
  }
}
