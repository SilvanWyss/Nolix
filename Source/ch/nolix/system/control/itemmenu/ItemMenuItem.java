/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.itemmenu;

import java.util.function.Consumer;

import ch.nolix.base.foundation.util.IdCreator;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.element.mutableelement.AbstractMutableElementWithProperties;
import ch.nolix.system.element.valueproperty.OptionalValueProperty;
import ch.nolix.system.element.valueproperty.ValueProperty;
import ch.nolix.systemapi.control.itemmenu.IItemMenu;
import ch.nolix.systemapi.control.itemmenu.IItemMenuItem;

/**
 * @author Silvan Wyss
 */
public final class ItemMenuItem extends AbstractMutableElementWithProperties implements IItemMenuItem<ItemMenuItem> {
  public static final boolean DEFAULT_SELECTION_FLAG = false;

  private static final String ID_HEADER = PascalCaseVariableNameCatalog.ID;

  private static final String TEXT_HEADER = PascalCaseVariableNameCatalog.TEXT;

  private static final String SELECTION_FLAG_HEADER = "Selected";

  private IItemMenu<?, ?> nullableParentMenu;

  private final OptionalValueProperty<String> id = //
  OptionalValueProperty.forStringWithNameAndSetter(ID_HEADER, this::setId);

  private final ValueProperty<String> text = //
  ValueProperty.forStringWithNameAndDefaultValueAndSetter(TEXT_HEADER, StringCatalog.EMPTY_STRING, this::setText);

  private final ValueProperty<Boolean> selectionFlag = //
  ValueProperty.forBooleanWithNameAndDefaultValueAndSetter(
    SELECTION_FLAG_HEADER,
    DEFAULT_SELECTION_FLAG,
    this::setSelectionFlag);

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

  public static ItemMenuItem fromSpecification(final Node<?> specification) {
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
    return id.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return text.getStoredValue();
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
    return selectionFlag.getStoredValue();
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
    Validator.assertThat(id).thatIsNamed(LowerCaseVariableNameCatalog.ID).isNotBlank();

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
