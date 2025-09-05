package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.BackReferenceToContentModelMapper;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.MultiBackReferenceToContentModelMapper;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.MultiReferenceToContentModelMapper;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.MultiValueTypeToContentModelMapper;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.OptionalBackReferenceToContentModelMapper;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.OptionalReferenceToContentModelMapper;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.OptionalValueToContentModelMapper;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.ReferenceToContentModelMapper;
import ch.nolix.system.objectdata.fieldtocontentmodelmapper.ValueToContentModelMapper;
import ch.nolix.systemapi.objectdata.model.IBackReference;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;
import ch.nolix.systemapi.objectdata.model.IOptionalBackReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;
import ch.nolix.systemapi.objectdata.model.IReference;
import ch.nolix.systemapi.objectdata.model.IValueField;
import ch.nolix.systemapi.objectdata.schemamapper.IContentModelMapper;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ContentModelMapper //NOSONAR: A ContentModelMapper is a principal object thus it has many dependencies.
implements IContentModelMapper {
  private static final ValueToContentModelMapper VALUE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new ValueToContentModelMapper();

  private static final OptionalValueToContentModelMapper OPTIONAL_VALUE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new OptionalValueToContentModelMapper();

  private static final MultiValueTypeToContentModelMapper MULTI_VALUE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new MultiValueTypeToContentModelMapper();

  private static final ReferenceToContentModelMapper REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new ReferenceToContentModelMapper();

  private static final OptionalReferenceToContentModelMapper OPTIONAL_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new OptionalReferenceToContentModelMapper();

  private static final MultiReferenceToContentModelMapper MULTI_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new MultiReferenceToContentModelMapper();

  private static final BackReferenceToContentModelMapper BACK_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new BackReferenceToContentModelMapper();

  private static final OptionalBackReferenceToContentModelMapper //
  OPTIONAL_BACK_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new OptionalBackReferenceToContentModelMapper();

  private static final MultiBackReferenceToContentModelMapper MULTI_BACK_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER = //
  new MultiBackReferenceToContentModelMapper();

  @Override
  public IContentModel mapFieldToContentModel(
    final IField field,
    final IContainer<ITable> referencedTables) {
    if (field instanceof IValueField<?> value) {
      return VALUE_TYPE_TO_CONTENT_MODEL_MAPPER.mapFieldToContentModel(value, referencedTables);
    }

    if (field instanceof IOptionalValueField<?> optionalValue) {
      return //
      OPTIONAL_VALUE_TYPE_TO_CONTENT_MODEL_MAPPER.mapFieldToContentModel(optionalValue, referencedTables);
    }

    if (field instanceof IMultiValueField<?> multiValue) {
      return MULTI_VALUE_TYPE_TO_CONTENT_MODEL_MAPPER.mapFieldToContentModel(multiValue, referencedTables);
    }

    if (field instanceof IReference<?> reference) {
      return REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER.mapFieldToContentModel(reference, referencedTables);
    }

    if (field instanceof IOptionalReference<?> optionalReference) {
      return //
      OPTIONAL_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER.mapFieldToContentModel(optionalReference,
        referencedTables);
    }

    if (field instanceof IMultiReference<?> multiReference) {
      return //
      MULTI_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER.mapFieldToContentModel(multiReference, referencedTables);
    }

    if (field instanceof IBackReference<?> backReference) {
      return BACK_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER.mapFieldToContentModel(backReference,
        referencedTables);
    }

    if (field instanceof IOptionalBackReference<?> optionalBackReference) {
      return OPTIONAL_BACK_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER
        .mapFieldToContentModel(optionalBackReference, referencedTables);
    }

    if (field instanceof IMultiBackReference<?> multiBackReference) {
      return MULTI_BACK_REFERENCE_TYPE_TO_CONTENT_MODEL_MAPPER.mapFieldToContentModel(multiBackReference,
        referencedTables);
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
