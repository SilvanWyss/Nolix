package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.model.BackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiValueModelDto;
import ch.nolix.systemapi.midschema.model.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalValueModelDto;
import ch.nolix.systemapi.midschema.model.ReferenceModelDto;
import ch.nolix.systemapi.midschema.model.ValueModelDto;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ContentModelMapper {

  private ContentModelMapper() {
  }

  public static IContentModel mapMidSchemaContentModelDtoToContentModel(
    final IContentModelDto midContentModelDto,
    final IContainer<? extends ITable> tables) {

    if (midContentModelDto instanceof ValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (midContentModelDto instanceof OptionalValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (midContentModelDto instanceof MultiValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (midContentModelDto instanceof ReferenceModelDto referenceModelDto) {

      final var referencedTableId = referenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      //TODO: Update ReferenceModelDto
      return ReferenceModel.forReferenceableTables(ImmutableList.withElement(referencedTable));
    }

    if (midContentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {

      final var referencedTableId = optionalReferenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      //TODO: Update OptionalReferenceModelDto
      return OptionalReferenceModel.forReferenceableTables(ImmutableList.withElement(referencedTable));
    }

    if (midContentModelDto instanceof MultiReferenceModelDto multiBackReferenceModelDto) {

      final var referencedTableId = multiBackReferenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      //TODO: Update MultiReferenceModelDto
      return MultiReferenceModel.forReferenceableTables(ImmutableList.withElement(referencedTable));
    }

    if (midContentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {

      final var backReferenceColumnId = backReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferenceColumnId));

      return BackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    if (midContentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {

      final var backReferencedColumnId = optionalBackReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return OptionalBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    if (midContentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {

      final var backReferencedColumnId = multiBackReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return MultiBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    throw InvalidArgumentException.forArgument(midContentModelDto);
  }
}
