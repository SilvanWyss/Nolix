package ch.nolix.system.webatomiccontrol.itemmenu;

import java.util.function.Consumer;

import ch.nolix.core.datamodel.id.IdCreator;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.mutableelement.AbstractMutableElement;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.element.property.OptionalValue;
import ch.nolix.system.element.property.Value;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuItem;

/**
 * @author Silvan Wyss
 */
public final class ItemMenuItem extends AbstractMutableElement implements IItemMenuItem<ItemMenuItem> {
  public static final boolean DEFAULT_SELECTION_FLAG = false;

  private static final String ID_HEADER = PascalCaseVariableCatalog.ID;

  private static final String TEXT_HEADER = PascalCaseVariableCatalog.TEXT;

  private static final String SELECTION_FLAG_HEADER = "Selected";

  private IItemMenu<?, ?> nullableParentMenu;

  private final OptionalValue<String> id = OptionalValue.forString(ID_HEADER, this::setId);

  private final Value<String> text = Value.forString(TEXT_HEADER, this::setText);

  private final MutableValue<Boolean> selectionFlag = //
  MutableValue.forBoolean(SELECTION_FLAG_HEADER, DEFAULT_SELECTION_FLAG, this::setSelectionFlag);

  private final Consumer<IItemMenuItem<?>> nullableSelectAction;

  private ItemMenuItem() {
    nullableSelectAction = null;
  }

  private ItemMenuItem(final Consumer<IItemMenuItem<?>> selectAction) {
    Validator.assertThat(selectAction).thatIsNamed("select action").isNotNull();

    this.nullableSelectAction = selectAction;
  }

  public static ItemMenuItem createBlankItem() {
    return withText(StringCatalog.EMPTY_STRING);
  }

  public static ItemMenuItem fromSpecification(final INode<?> specification) {
    final var item = new ItemMenuItem();
    item.resetFromSpecification(specification);

    return item;
  }

  public static ItemMenuItem withIdAndText(
    final String id,
    final String text) {
    final var item = new ItemMenuItem();
    item.setId(id);
    item.setText(text);

    return item;
  }

  public static ItemMenuItem withIdAndTextAndSelectAction(
    final String id,
    final String text,
    final Runnable selectAction) {
    @SuppressWarnings("unused")
    final var item = new ItemMenuItem(i -> selectAction.run());

    item.setId(id);
    item.setText(text);

    return item;
  }

  public static ItemMenuItem withIdAndTextAndSelectAction(
    final String id,
    final String text,
    final Consumer<IItemMenuItem<?>> selectAction) {
    final var item = new ItemMenuItem(selectAction);
    item.setId(id);
    item.setText(text);

    return item;
  }

  public static ItemMenuItem withText(
    final String text) {
    final var item = new ItemMenuItem();
    item.setId(IdCreator.createIdOf10HexadecimalCharacters());
    item.setText(text);

    return item;
  }

  public static ItemMenuItem withTextAndSelectAction(
    final String text,
    final Runnable selectAction) {
    @SuppressWarnings("unused")
    final var item = new ItemMenuItem(i -> selectAction.run());

    item.setId(IdCreator.createIdOf10HexadecimalCharacters());
    item.setText(text);

    return item;
  }

  public static ItemMenuItem withTextAndSelectAction(
    final String text,
    final Consumer<IItemMenuItem<?>> selectAction) {
    final var item = new ItemMenuItem(selectAction);
    item.setId(IdCreator.createIdOf10HexadecimalCharacters());
    item.setText(text);

    return item;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToMenu() {
    return (nullableParentMenu != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return id.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return text.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBlank() {
    return getText().isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSelected() {
    return selectionFlag.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    unselect();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void select() {
    if (!isSelected()) {
      selectWhenNotSelected();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unselect() {
    selectionFlag.setValue(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void internalSetParentMenu(final IItemMenu<?, ?> parentMenu) {
    Validator.assertThat(parentMenu).thatIsNamed("parent menu").isNotNull();

    this.nullableParentMenu = parentMenu;
  }

  private boolean hasSelectAction() {
    return (nullableSelectAction != null);
  }

  private void runOptionalSelectAction() {
    if (hasSelectAction()) {
      nullableSelectAction.accept(this);
    }
  }

  private void selectWhenNotSelected() {
    unselectItemsOfOptionalParentMenu();

    selectionFlag.setValue(true);

    if (belongsToMenu()) {
      nullableParentMenu.internalRunOptionalSelectActionForItem(this);
    }

    runOptionalSelectAction();
  }

  private void setId(final String id) {
    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    this.id.setValue(id);
  }

  private void setSelectionFlag(final boolean selected) {
    if (selected) {
      select();
    } else {
      unselect();
    }
  }

  private void setText(final String text) {
    this.text.setValue(text);
  }

  private void unselectItemsOfOptionalParentMenu() {
    if (belongsToMenu()) {
      nullableParentMenu.getStoredItems().forEach(IItemMenuItem::unselect);
    }
  }
}
