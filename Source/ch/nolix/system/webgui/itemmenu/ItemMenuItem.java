package ch.nolix.system.webgui.itemmenu;

import java.util.function.Consumer;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.element.property.OptionalValue;
import ch.nolix.system.element.property.Value;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

public final class ItemMenuItem extends MutableElement implements IItemMenuItem<ItemMenuItem> {

  public static final boolean DEFAULT_SELECTION_FLAG = false;

  private static final String ID_HEADER = PascalCaseVariableCatalog.ID;

  private static final String TEXT_HEADER = PascalCaseVariableCatalog.TEXT;

  private static final String SELECTION_FLAG_HEADER = "Selected";

  private final OptionalValue<String> id = new OptionalValue<>(
    ID_HEADER,
    this::setId,
    INode::getSingleChildNodeHeader,
    Node::withChildNode);

  private final Value<String> text = new Value<>(
    TEXT_HEADER,
    this::setText,
    INode::getSingleChildNodeHeader,
    Node::withChildNode);

  private final MutableValue<Boolean> selectionFlag = new MutableValue<>(
    SELECTION_FLAG_HEADER,
    DEFAULT_SELECTION_FLAG,
    this::setSelectionFlag,
    INode::getSingleChildNodeAsBoolean,
    Node::withChildNode);

  private IItemMenu<?, ?> parentMenu;

  private final Consumer<IItemMenuItem<?>> selectAction;

  private ItemMenuItem() {
    selectAction = null;
  }

  private ItemMenuItem(final Consumer<IItemMenuItem<?>> selectAction) {

    GlobalValidator.assertThat(selectAction).thatIsNamed("select action").isNotNull();

    this.selectAction = selectAction;
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
    item.setId(GlobalIdCreator.createIdOf10HexadecimalCharacters());
    item.setText(text);

    return item;
  }

  public static ItemMenuItem withTextAndSelectAction(
    final String text,
    final Runnable selectAction) {

    final var item = new ItemMenuItem(i -> selectAction.run());
    item.setId(GlobalIdCreator.createIdOf10HexadecimalCharacters());
    item.setText(text);

    return item;
  }

  public static ItemMenuItem withTextAndSelectAction(
    final String text,
    final Consumer<IItemMenuItem<?>> selectAction) {

    final var item = new ItemMenuItem(selectAction);
    item.setId(GlobalIdCreator.createIdOf10HexadecimalCharacters());
    item.setText(text);

    return item;
  }

  @Override
  public boolean belongsToMenu() {
    return (parentMenu != null);
  }

  @Override
  public String getId() {
    return id.getValue();
  }

  @Override
  public String getText() {
    return text.getValue();
  }

  @Override
  public boolean isBlank() {
    return getText().isEmpty();
  }

  @Override
  public boolean isSelected() {
    return selectionFlag.getValue();
  }

  @Override
  public void reset() {
    unselect();
  }

  @Override
  public void select() {
    if (!isSelected()) {
      selectWhenNotSelected();
    }
  }

  @Override
  public void unselect() {
    selectionFlag.setValue(false);
  }

  @Override
  public void internalSetParentMenu(final IItemMenu<?, ?> parentMenu) {

    GlobalValidator.assertThat(parentMenu).thatIsNamed("parent menu").isNotNull();

    this.parentMenu = parentMenu;
  }

  private boolean hasSelectAction() {
    return (selectAction != null);
  }

  private void runOptionalSelectAction() {
    if (hasSelectAction()) {
      selectAction.accept(this);
    }
  }

  private void selectWhenNotSelected() {

    unselectItemsOfOptionalParentMenu();

    selectionFlag.setValue(true);

    if (belongsToMenu()) {
      parentMenu.internalRunOptionalSelectActionForItem(this);
    }

    runOptionalSelectAction();
  }

  private void setId(final String id) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

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
      parentMenu.getStoredItems().forEach(IItemMenuItem::unselect);
    }
  }
}
