//package declaration
package ch.nolix.system.element.base;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
public abstract class StylableElement<SE extends IStylableElement<SE>>
    extends MutableElement
    implements IStylableElement<SE> {

  // method
  @Override
  public final void resetStyleRecursively() {

    resetStyle();

    getStoredChildStylableElements().forEach(IStylableElement::resetStyleRecursively);
  }

  // method declaration
  protected abstract void resetStylableElement();

  // method declaration
  protected abstract void resetStyle();

  // constant
  private static final String ID_HEADER = PascalCaseCatalogue.ID;

  // constant
  private static final String TOKEN_HEADER = PascalCaseCatalogue.TOKEN;

  // attribute
  private final MutableOptionalValue<String> id = MutableOptionalValue.forString(ID_HEADER, this::setId);

  // attribute
  private final MultiValue<String> tokens = MultiValue.forStrings(TOKEN_HEADER, this::addToken);

  // method
  @Override
  public final SE addToken(final String token) {

    GlobalValidator.assertThat(token).thatIsNamed(LowerCaseCatalogue.TOKEN).isNotBlank();

    tokens.add(token);

    return asConcrete();
  }

  // method
  @Override
  public final String getId() {
    return id.getValue();
  }

  // method
  @Override
  public final String getIdInQuotes() {
    return GlobalStringHelper.getInQuotes(getId());
  }

  // method
  @Override
  public final IContainer<String> getTokens() {
    return tokens.getStoredValues();
  }

  // method
  @Override
  public final boolean hasId() {
    return id.containsAny();
  }

  // method
  @Override
  public final boolean hasId(final String id) {
    return (hasId() && getId().equals(id));
  }

  // method
  @Override
  public SE removeId() {

    id.clear();

    return asConcrete();
  }

  // method
  @Override
  public final SE removeToken(final String token) {

    tokens.remove(token);

    return asConcrete();
  }

  // method
  @Override
  public final SE removeTokens() {

    tokens.clear();

    return asConcrete();
  }

  // method
  @Override
  public final void reset() {

    removeId();
    removeTokens();

    resetStyleRecursively();

    resetStylableElement();
  }

  // method
  @Override
  public final SE setId(final String id) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();

    this.id.setValue(id);

    return asConcrete();
  }

  // method
  @SuppressWarnings("unchecked")
  protected final SE asConcrete() {
    return (SE) this;
  }
}
