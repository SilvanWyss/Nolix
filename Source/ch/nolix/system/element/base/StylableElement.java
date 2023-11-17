//package declaration
package ch.nolix.system.element.base;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
public abstract class StylableElement<SE extends IStylableElement<SE>>
extends MutableElement
implements IStylableElement<SE> {

  //constant
  private static final String ID_HEADER = PascalCaseCatalogue.ID;

  //constant
  private static final String TOKEN_HEADER = PascalCaseCatalogue.TOKEN;

  //attribute
  private final MutableOptionalValue<String> id = MutableOptionalValue.forString(ID_HEADER, this::setId);

  //attribute
  private final MultiValue<String> tokens = MultiValue.forStrings(TOKEN_HEADER, this::addToken);

  //method
  @Override
  public final SE addToken(final String token) {

    GlobalValidator.assertThat(token).thatIsNamed(LowerCaseCatalogue.TOKEN).isNotBlank();

    tokens.add(token);

    return asConcrete();
  }

  //method
  @Override
  public final String getId() {
    return id.getValue();
  }

  //method
  @Override
  public final IContainer<String> getTokens() {
    return tokens.getStoredValues();
  }

  //method
  @Override
  public final boolean hasId() {
    return id.containsAny();
  }

  //method
  @Override
  public SE removeId() {

    id.clear();

    return asConcrete();
  }

  //method
  @Override
  public final SE removeToken(final String token) {

    tokens.remove(token);

    return asConcrete();
  }

  //method
  @Override
  public final SE removeTokens() {

    tokens.clear();

    return asConcrete();
  }

  //method
  @Override
  public final void reset() {

    removeId();
    removeTokens();

    resetStyleRecursively();

    resetStylableElement();
  }

  //method
  @Override
  public final void resetStyleRecursively() {

    resetStyle();

    getStoredChildStylableElements().forEach(IStylableElement::resetStyleRecursively);
  }

  //method
  @Override
  public final SE setId(final String id) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();

    this.id.setValue(id);

    return asConcrete();
  }

  //method
  @SuppressWarnings("unchecked")
  protected final SE asConcrete() {
    return (SE) this;
  }

  //method declaration
  protected abstract void resetStylableElement();

  //method declaration
  protected abstract void resetStyle();
}
