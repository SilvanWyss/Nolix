/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.itemmenu;

import java.util.function.Consumer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.systemapi.webgui.main.Control;

//An IItemMenu can contain 0 or 1 selected item.
public interface IItemMenu<M extends IItemMenu<M, S>, S extends IItemMenuStyle<S>> extends Clearable, Control<M, S> {
  M addBlankItem();

  M addItem(IItemMenuItem<?> item);

  M addItem(String item);

  M addItems(IItemMenuItem<?>... items);

  M addItems(String... items);

  M addItemWithIdAndText(String id, String text);

  M addItemWithIdAndTextAndSelectAction(String id, String text, Runnable selectAction);

  M addItemWithIdAndTextAndSelectAction(String id, String text, Consumer<IItemMenuItem<?>> selectAction);

  M addItemWithTextAndSelectAction(String text, Runnable selectAction);

  M addItemWithTextAndSelectAction(String text, Consumer<IItemMenuItem<?>> selectAction);

  boolean blankItemIsSelected();

  boolean containsBlankItem();

  boolean containsItemWithId(String id);

  boolean containsItemWithText(String text);

  boolean containsSelectedItem();

  String getIdByItemText(String itemText);

  ExtendedIterable<IItemMenuItem<?>> getStoredItems();

  IItemMenuItem<?> getStoredSelectedItem();

  String getTextByItemId(String itemId);

  void removeSelectAction();

  M selectBlankItem();

  M selectFirstItem();

  M selectItemById(String id);

  M selectItemByText(String text);

  M setSelectAction(Runnable selectAction);

  M setSelectAction(Consumer<IItemMenuItem<?>> selectAction);

  void internalRunOptionalSelectActionForItem(IItemMenuItem<?> item);
}
