package ch.nolix.tech.relationaldoc.datamodel;

import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.datamodelapi.fieldproperty.ContentType;
import ch.nolix.coreapi.programatomapi.variableapi.PluralPascalCaseVariableCatalogue;
import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.OptionalReference;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractableFieldValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IContent;

public final class AbstractableField extends Entity implements IAbstractableField {

  public static final String DEFAULT_NAME = PluralPascalCaseVariableCatalogue.FIELD;

  public static final Cardinality DEFAULT_CARDINALITY = Cardinality.TO_ONE;

  private static final AbstractableFieldValidator ABSTRACTABLE_FIELD_VALIDATOR = new AbstractableFieldValidator();

  private final BackReference<AbstractableObject> parentObject = BackReference
    .forEntityAndBackReferencedFieldName(AbstractableObject.class, "declaredFields");

  private final Value<String> name = Value.withInitialValue(DEFAULT_NAME);

  private final Value<String> cardinality = Value.withInitialValue(DEFAULT_CARDINALITY.toString());

  //TODO: Enable BaseReference to reference base types.

  private final OptionalReference<AbstractValueContent> abstractValueContent = OptionalReference
    .forEntity(AbstractValueContent.class);

  //TODO: Enable BaseReference to reference base types.

  private final OptionalReference<AbstractReferenceContent> abstractReferenceContent = OptionalReference
    .forEntity(AbstractReferenceContent.class);

  //TODO: Enable BaseReference to reference base types.

  private final OptionalReference<ConcreteValueContent> concreteValueContent = OptionalReference
    .forEntity(ConcreteValueContent.class);

  //TODO: Enable BaseReference to reference base types.

  private final OptionalReference<ConcreteReferenceContent> concreteReferenceContent = OptionalReference
    .forEntity(ConcreteReferenceContent.class);

  public AbstractableField() {
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
  public IAbstractableField getStoredBaseField() {
    return getStoredParentObject()
      .getStoredDirectBaseTypes()
      .toMultiple(IAbstractableObject::getStoredFields)
      .getStoredFirst(f -> f.hasSameNameAs(this));
  }

  @Override
  public IContent getStoredContent() {

    if (abstractValueContent.containsAny()) {
      return abstractValueContent.getReferencedEntity();
    }

    if (abstractReferenceContent.containsAny()) {
      return abstractValueContent.getReferencedEntity();
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
  public IAbstractableObject getStoredParentObject() {
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
  public IAbstractableField setAsAbstract() {

    ABSTRACTABLE_FIELD_VALIDATOR.assertCanBeSetAsAbstract(this);

    setAsAbstractIfIsConcreteAndWhenAllowed();

    return this;
  }

  @Override
  public IAbstractableField setAsConcrete() {

    ABSTRACTABLE_FIELD_VALIDATOR.assertCanBeSetAsConcrete(this);

    setAsConcreteIfItIsAbstractAndWhenAllowed();

    return this;
  }

  @Override
  public IAbstractableField setCardinality(final Cardinality cardinality) {

    ABSTRACTABLE_FIELD_VALIDATOR.assertCanSetCardinality(this, cardinality);

    this.cardinality.setValue(cardinality.toString());

    return this;
  }

  @Override
  public IAbstractableField setForReferences() {

    ABSTRACTABLE_FIELD_VALIDATOR.assertCanBeSetForReferences(this);

    setForReferencesWhenAllowed();

    return this;
  }

  @Override
  public IAbstractableField setForValues() {

    ABSTRACTABLE_FIELD_VALIDATOR.assertCanBeSetForValues(this);

    setForValuesWhenAllowed();

    return this;
  }

  @Override
  public IAbstractableField setName(final String name) {

    if (inheritsFromBaseField()) {
      getStoredBaseField().setName(name);
    } else {

      ABSTRACTABLE_FIELD_VALIDATOR.assertCanSetName(this, name);

      this.name.setValue(name);
    }

    return this;
  }

  private void removeContent() {
    abstractValueContent.clear();
    abstractReferenceContent.clear();
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

    final var newAbstractReferenceContent = new AbstractReferenceContent();
    getStoredParentDatabase().insertEntity(newAbstractReferenceContent);
    abstractReferenceContent.setEntity(newAbstractReferenceContent);
  }

  private void setAsConcreteIfItIsAbstractAndWhenAllowed() {
    if (isAbstract()) {
      setAsConcreteWhenIsAbstractAndAllowed();
    }
  }

  private void setAsAbstractWhenIsConcreteAndForValuesAndAllowed() {

    removeContent();

    final var newAbstractValueContent = new AbstractValueContent();
    getStoredParentDatabase().insertEntity(newAbstractValueContent);
    abstractValueContent.setEntity(newAbstractValueContent);
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

    final var newAbstractReferenceContent = new AbstractReferenceContent();
    getStoredParentDatabase().insertEntity(newAbstractReferenceContent);
    abstractReferenceContent.setEntity(newAbstractReferenceContent);
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

    final var newAbstractValueContent = new AbstractValueContent();
    getStoredParentDatabase().insertEntity(newAbstractValueContent);
    abstractValueContent.setEntity(newAbstractValueContent);
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
