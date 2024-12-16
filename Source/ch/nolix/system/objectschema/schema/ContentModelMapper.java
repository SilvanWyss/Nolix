package ch.nolix.system.objectschema.schema;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.contentmodel.BackReferenceModel;
import ch.nolix.system.objectschema.contentmodel.MultiBackReferenceModel;
import ch.nolix.system.objectschema.contentmodel.MultiReferenceModel;
import ch.nolix.system.objectschema.contentmodel.OptionalBackReferenceModel;
import ch.nolix.system.objectschema.contentmodel.OptionalReferenceModel;
import ch.nolix.system.objectschema.contentmodel.ReferenceModel;
import ch.nolix.system.objectschema.contentmodel.ValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;

public final class ContentModelMapper {

  public IContentModel createContentModelFromDto(
    final IContentModelDto contentModelDto,
    final IContainer<ITable> tables) {

    if (contentModelDto instanceof ValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (contentModelDto instanceof OptionalValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (contentModelDto instanceof MultiValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {

      final var referencedTableIds = referenceModelDto.referencedTableIds();
      final var referencedTables = tables.getStoredSelected(t -> referencedTableIds.containsEqualing(t.getId()));

      return ReferenceModel.forReferencedTables(referencedTables);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {

      final var referencedTableIds = optionalReferenceModelDto.referencedTableIds();
      final var referencedTables = tables.getStoredSelected(t -> referencedTableIds.containsEqualing(t.getId()));

      return OptionalReferenceModel.forReferencedTables(referencedTables);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiBackReferenceModelDto) {

      final var referencedTableIds = multiBackReferenceModelDto.referencedTableIds();
      final var referencedTables = tables.getStoredSelected(t -> referencedTableIds.containsEqualing(t.getId()));

      return MultiReferenceModel.forReferencedTables(referencedTables);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {

      final var backReferenceColumnId = backReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiple(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferenceColumnId));

      return BackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {

      final var backReferencedColumnId = optionalBackReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiple(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return OptionalBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {

      final var backReferencedColumnId = multiBackReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiple(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return MultiBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
