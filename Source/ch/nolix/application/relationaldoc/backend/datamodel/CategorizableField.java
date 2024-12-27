package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datavalidator.CategorizableFieldValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IContent;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.datamodelapi.fieldproperty.ContentType;
import ch.nolix.coreapi.programatomapi.variableapi.PluralPascalCaseVariableCatalogue;
import ch.nolix.system.objectdata.model.BackReference;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.OptionalReference;
import ch.nolix.system.objectdata.model.Value;

public final class CategorizableField extends Entity implements ICategorizableField {

  public static final String DEFAULT_NAME = PluralPascalCaseVariableCatalogue.FIELD;

  public static final Cardinality DEFAULT_CARDINALITY = Cardinality.TO_ONE;

  private static final CategorizableFieldValidator CATEGORIZABLE_FIELD_VALIDATOR = new CategorizableFieldValidator();

  private final BackReference<CategorizableObject> parentObject = BackReference
    .forEntityAndBackReferencedFieldName(CategorizableObject.class, "declaredFields");

  private final Value<String> name = Value.withInitialValue(DEFAULT_NAME);

  private final Value<String> cardinality = Value.withInitialValue(DEFAULT_CARDINALITY.toString());

  //TODO: Enable BaseReference to reference base types

  private final OptionalReference<CategorizableValueContent> categorizableValueContent = OptionalReference
    .forEntity(CategorizableValueContent.class);

  //TODO: Enable BaseReference to reference base types

  private final OptionalReference<CategorizableReferenceContent> categorizableReferenceContent = OptionalReference
    .forEntity(CategorizableReferenceContent.class);

  //TODO: Enable BaseReference to reference base types

  private final OptionalReference<ConcreteValueContent> concreteValueContent = OptionalReference
    .forEntity(ConcreteValueContent.class);

  //TODO: Enable BaseReference to reference base types

  private final OptionalReference<ConcreteReferenceContent> concreteReferenceContent = OptionalReference
    .forEntity(ConcreteReferenceContent.class);

  public CategorizableField() {
    initialize();
  }

  @Override
  public ContentType getContentType() {
    return getStoredContent().getContentType();
  }

  @Override
  public Cardinality getCardinality() {

    if (inheritsFromBaseField()) {
      return getStoredBaseField().getCardinality();
    }

    return Cardinality.valueOf(cardinality.getStoredValue());
  }

  @Override
  public String getName() {

    if (inheritsFromBaseField()) {
      return getStoredBaseField().getName();
    }

    return name.getStoredValue();
  }

  @Override
  public ICategorizableField getStoredBaseField() {
    return getStoredParentObject()
      .getStoredDirectBaseTypes()
      .toMultiple(ICategorizableObject::getStoredFields)
      .getStoredFirst(f -> f.hasSameNameAs(this));
  }

  @Override
  public IContent getStoredContent() {

    if (categorizableValueContent.containsAny()) {
      return categorizableValueContent.getReferencedEntity();
    }

    if (categorizableReferenceContent.containsAny()) {
      return categorizableValueContent.getReferencedEntity();
    }

    if (concreteValueContent.containsAny()) {
      return concreteValueContent.getReferencedEntity();
    }

    if (concreteReferenceContent.containsAny()) {
      concreteReferenceContent.getReferencedEntity();
    }

    final var initialContent = new ConcreteValueContent();
    getStoredParentDatabase().insertEntity(initialContent);
    concreteValueContent.setEntity(initialContent);

    return concreteValueContent.getReferencedEntity();
  }

  @Override
  public ICategorizableObject getStoredParentObject() {
    return parentObject.getStoredBackReferencedEntity();
  }

  @Override
  public boolean inheritsFromBaseField() {
    return getStoredParentObject()
      .getStoredDirectBaseTypes()
      .containsAny(bt -> bt.getStoredFields().containsAny(f -> f.hasSameNameAs(this)));
  }

  @Override
  public boolean isAbstract() {
    return getStoredContent().isAbstract();
  }

  @Override
  public boolean isEmpty() {
    return getStoredContent().isEmpty();
  }

  @Override
  public boolean isForValues() {
    return getStoredContent().isForValues();
  }

  @Override
  public boolean isMandatory() {
    return (getCardinality() == Cardinality.TO_ONE);
  }

  @Override
  public ICategorizableField setAsAbstract() {

    CATEGORIZABLE_FIELD_VALIDATOR.assertCanBeSetAsAbstract(this);

    setAsAbstractIfIsConcreteAndWhenAllowed();

    return this;
  }

  @Override
  public ICategorizableField setAsConcrete() {

    CATEGORIZABLE_FIELD_VALIDATOR.assertCanBeSetAsConcrete(this);

    setAsConcreteIfItIsAbstractAndWhenAllowed();

    return this;
  }

  @Override
  public ICategorizableField setCardinality(final Cardinality cardinality) {

    CATEGORIZABLE_FIELD_VALIDATOR.assertCanSetCardinality(this, cardinality);

    this.cardinality.setValue(cardinality.toString());

    return this;
  }

  @Override
  public ICategorizableField setForReferences() {

    CATEGORIZABLE_FIELD_VALIDATOR.assertCanBeSetForReferences(this);

    setForReferencesWhenAllowed();

    return this;
  }

  @Override
  public ICategorizableField setForValues() {

    CATEGORIZABLE_FIELD_VALIDATOR.assertCanBeSetForValues(this);

    setForValuesWhenAllowed();

    return this;
  }

  @Override
  public ICategorizableField setName(final String name) {

    if (inheritsFromBaseField()) {
      getStoredBaseField().setName(name);
    } else {

      CATEGORIZABLE_FIELD_VALIDATOR.assertCanSetName(this, name);

      this.name.setValue(name);
    }

    return this;
  }

  private void removeContent() {
    categorizableValueContent.clear();
    categorizableReferenceContent.clear();
    concreteValueContent.clear();
    concreteReferenceContent.clear();
  }

  private void setAsAbstractIfIsConcreteAndWhenAllowed() {
    if (isConcrete()) {
      setAsAbstractWhenIsConcreteAndAllowed();
    }
  }

  private void setAsAbstractWhenIsConcreteAndForReferencesAndAllowed() {

    removeContent();

    final var newAbstractReferenceContent = new CategorizableReferenceContent();
    getStoredParentDatabase().insertEntity(newAbstractReferenceContent);
    categorizableReferenceContent.setEntity(newAbstractReferenceContent);
  }

  private void setAsConcreteIfItIsAbstractAndWhenAllowed() {
    if (isAbstract()) {
      setAsConcreteWhenIsAbstractAndAllowed();
    }
  }

  private void setAsAbstractWhenIsConcreteAndForValuesAndAllowed() {

    removeContent();

    final var newAbstractValueContent = new CategorizableValueContent();
    getStoredParentDatabase().insertEntity(newAbstractValueContent);
    categorizableValueContent.setEntity(newAbstractValueContent);
  }

  //mehtod
  private void setAsAbstractWhenIsConcreteAndAllowed() {
    if (isForValues()) {
      setAsAbstractWhenIsConcreteAndForValuesAndAllowed();
    } else {
      setAsAbstractWhenIsConcreteAndForReferencesAndAllowed();
    }
  }

  private void setAsConcreteWhenIsAbstractAndAllowed() {
    if (isForValues()) {
      setAsConcreteWhenIsAbstractAndForValuesAndAllowed();
    } else {
      setAsConcreteWhenIsAbstractAndForReferencesAndAllowed();
    }
  }

  private void setAsConcreteWhenIsAbstractAndForReferencesAndAllowed() {

    removeContent();

    final var newConcreteReferenceContent = new ConcreteReferenceContent();
    getStoredParentDatabase().insertEntity(newConcreteReferenceContent);
    concreteReferenceContent.setEntity(newConcreteReferenceContent);
  }

  private void setAsConcreteWhenIsAbstractAndForValuesAndAllowed() {

    removeContent();

    final var newConcreteValueContent = new ConcreteValueContent();
    getStoredParentDatabase().insertEntity(newConcreteValueContent);
    concreteValueContent.setEntity(newConcreteValueContent);
  }

  private void setForReferencesIfIsForRerencesAndWhenAllowed() {
    if (isAbstract()) {
      setForReferencesWhenIsAbstractAndForValuesAndAllowed();
    } else {
      setForReferencesWhenIsConcreteAndForValuesAndAllowed();
    }
  }

  private void setForReferencesWhenAllowed() {
    if (isForValues()) {
      setForReferencesIfIsForRerencesAndWhenAllowed();
    }
  }

  private void setForReferencesWhenIsAbstractAndForValuesAndAllowed() {

    removeContent();

    final var newAbstractReferenceContent = new CategorizableReferenceContent();
    getStoredParentDatabase().insertEntity(newAbstractReferenceContent);
    categorizableReferenceContent.setEntity(newAbstractReferenceContent);
  }

  private void setForReferencesWhenIsConcreteAndForValuesAndAllowed() {

    removeContent();

    final var newConcreteReferenceContent = new ConcreteReferenceContent();
    getStoredParentDatabase().insertEntity(newConcreteReferenceContent);
    concreteReferenceContent.setEntity(newConcreteReferenceContent);
  }

  private void setForValuesWhenAllowed() {
    if (isForReferences()) {
      setForValuesWhenIsForReferencesAndAllowed();
    }
  }

  private void setForValuesWhenIsAbstractAndForReferencesAndAllowed() {

    removeContent();

    final var newAbstractValueContent = new CategorizableValueContent();
    getStoredParentDatabase().insertEntity(newAbstractValueContent);
    categorizableValueContent.setEntity(newAbstractValueContent);
  }

  private void setForValuesWhenIsConcreteAndForReferencesAndAllowed() {

    removeContent();

    final var newConcreteValueContent = new ConcreteValueContent();
    getStoredParentDatabase().insertEntity(newConcreteValueContent);
    concreteValueContent.setEntity(newConcreteValueContent);
  }

  private void setForValuesWhenIsForReferencesAndAllowed() {
    if (isAbstract()) {
      setForValuesWhenIsAbstractAndForReferencesAndAllowed();
    } else {
      setForValuesWhenIsConcreteAndForReferencesAndAllowed();
    }
  }
}
