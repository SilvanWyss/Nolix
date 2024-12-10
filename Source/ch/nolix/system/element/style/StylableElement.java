package ch.nolix.system.element.style;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.element.base.StructureSpecificationCreator;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.baseapi.IStructureElement;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

public abstract class StylableElement<SE extends IStylableElement<SE>>
extends MutableElement
implements IStylableElement<SE> {

  private static final String ID_HEADER = PascalCaseVariableCatalogue.ID;

  private static final String TOKEN_HEADER = PascalCaseVariableCatalogue.TOKEN;

  private static final StructureSpecificationCreator STRUCTURE_SPECIFICATION_CREATOR = //
  new StructureSpecificationCreator();

  private final MutableOptionalValue<String> id = MutableOptionalValue.forString(ID_HEADER, this::setId);

  private final MultiValue<String> tokens = MultiValue.forStrings(TOKEN_HEADER, this::addToken);

  @Override
  public final SE addToken(final String token) {

    GlobalValidator.assertThat(token).thatIsNamed(LowerCaseVariableCatalogue.TOKEN).isNotBlank();

    tokens.add(token);

    return asConcrete();
  }

  @Override
  public final IContainer<? extends IStructureElement> getChildStructureElements() {
    return getStoredChildStylableElements();
  }

  @Override
  public final String getId() {
    return id.getValue();
  }

  @Override
  public final IContainer<String> getTokens() {
    return tokens.getStoredValues();
  }

  @Override
  public final INode<?> getStructureSpecification() {
    return STRUCTURE_SPECIFICATION_CREATOR.getStructureSpecificationOfElement(this);
  }

  @Override
  public final boolean hasId() {
    return id.containsAny();
  }

  @Override
  public final SE removeId() {

    id.clear();

    return asConcrete();
  }

  @Override
  public final SE removeToken(final String token) {

    tokens.remove(token);

    return asConcrete();
  }

  @Override
  public final SE removeTokens() {

    tokens.clear();

    return asConcrete();
  }

  @Override
  public final void reset() {

    removeId();
    removeTokens();

    resetStyleRecursively();

    resetStylableElement();
  }

  @Override
  public final void resetStyleRecursively() {

    resetStyle();

    getStoredChildStylableElements().forEach(IStylableElement::resetStyleRecursively);
  }

  @Override
  public final SE setId(final String id) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();

    this.id.setValue(id);

    return asConcrete();
  }

  @SuppressWarnings("unchecked")
  protected final SE asConcrete() {
    return (SE) this;
  }

  protected abstract void resetStylableElement();

  protected abstract void resetStyle();
}
