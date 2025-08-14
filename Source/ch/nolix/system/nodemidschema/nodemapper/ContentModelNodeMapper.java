package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.BackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiValueModelDto;
import ch.nolix.systemapi.midschema.model.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalValueModelDto;
import ch.nolix.systemapi.midschema.model.ReferenceModelDto;
import ch.nolix.systemapi.midschema.model.ValueModelDto;
import ch.nolix.systemapi.nodemidschema.nodemapper.IContentModelNodeMapper;

/**
 * @author Silvan Wyss
 * @version 2024-09-12
 */
public final class ContentModelNodeMapper implements IContentModelNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToContentModelNode(final ColumnDto columnDto) {

    final var contentModel = columnDto.contentModel();

    return mapContentModelDtoToNode(contentModel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapContentModelDtoToNode(final IContentModelDto contentModelDto) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return ContentModelNodeMapperHelper.mapValueModelDtoToNode(valueModelDto);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return ContentModelNodeMapperHelper.mapOptionalValueModelDtoToNode(optionalValueModelDto);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return ContentModelNodeMapperHelper.mapMultiValueModelDtoToNode(multiValueModelDto);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return ContentModelNodeMapperHelper.mapRefernceModelDtoToNode(referenceModelDto);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return ContentModelNodeMapperHelper.mapOptionalRefernceModelDtoToNode(optionalReferenceModelDto);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return ContentModelNodeMapperHelper.mapMultiRefernceModelDtoToNode(multiReferenceModelDto);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return ContentModelNodeMapperHelper.mapBackRefernceModelDtoToNode(backReferenceModelDto);
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return ContentModelNodeMapperHelper.mapOptionalBackRefernceModelDtoToNode(optionalBackReferenceModelDto);
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return ContentModelNodeMapperHelper.mapMultiBackRefernceModelDtoToNode(multiBackReferenceModelDto);
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
