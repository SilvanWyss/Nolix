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
  /**
   * Adds a blank item to the current {@link IItemMenu}.
   * 
   * @return the current {@link IItemMenu}
   */
  M addBlankItem();

  /**
   * Adds the given item to the current {@link IItemMenu}.
   * 
   * @param item
   * @return the current {@link IItemMenu}
   */
  M addItem(IItemMenuItem<?> item);

  /**
   * Adds a new item with the given item as text to the current {@link IItemMenu}.
   * 
   * @param item
   * @return the current {@link IItemMenu}
   */
  M addItem(String item);

  /**
   * Adds the given items to the current {@link IItemMenu}.
   * 
   * @param items
   * @return the current {@link IItemMenu}
   */
  M addItems(IItemMenuItem<?>... items);

  /**
   * Adds new items with the given items as texts to the current
   * {@link IItemMenu}.
   * 
   * @param items
   * @return the current {@link IItemMenu}
   */
  M addItems(String... items);

  /**
   * Adds a new item with the given id and text to the current {@link IItemMenu}.
   * 
   * @param id
   * @param text
   * @return the current {@link IItemMenu}
   */
  M addItemWithIdAndText(String id, String text);

  /**
   * Adds a new item with the given id, text and selectAction to the current
   * {@link IItemMenu}.
   * 
   * @param id
   * @param text
   * @param selectAction
   * @return the current {@link IItemMenu}
   */
  M addItemWithIdAndTextAndSelectAction(String id, String text, Runnable selectAction);

  /**
   * Adds a new item with the given id, text and selectAction to the current
   * {@link IItemMenu}.
   * 
   * @param id
   * @param text
   * @param selectAction
   * @return the current {@link IItemMenu}
   */
  M addItemWithIdAndTextAndSelectAction(String id, String text, Consumer<IItemMenuItem<?>> selectAction);

  /**
   * Adds a new item with the given text and selectAction to the current
   * {@link IItemMenu}.
   * 
   * @param text
   * @param selectAction
   * @return the current {@link IItemMenu}
   */
  M addItemWithTextAndSelectAction(String text, Runnable selectAction);

  /**
   * Adds a new item with the given text and selectAction to the current
   * {@link IItemMenu}.
   * 
   * @param text
   * @param selectAction
   * @return the current {@link IItemMenu}
   */
  M addItemWithTextAndSelectAction(String text, Consumer<IItemMenuItem<?>> selectAction);

  /**
   * @return true if the blank item of the current {@link IItemMenu} is selected,
   *         false otherwise
   */
  boolean blankItemIsSelected();

  /**
   * @return true if the current {@link IItemMenu} contains a blank item, false
   *         otherwise
   */
  boolean containsBlankItem();

  /**
   * @param id
   * @return true if the current {@link IItemMenu} contains an item with the given
   *         id, false otherwise
   */
  boolean containsItemWithId(String id);

  /**
   * @param text
   * @return true if the current {@link IItemMenu} contains an item with the given
   *         text, false otherwise
   */
  boolean containsItemWithText(String text);

  /**
   * @return true if the current {@link IItemMenu} contains a selected item, false
   *         otherwise
   */
  boolean containsSelectedItem();

  /**
   * @param itemText
   * @return the id of the item with the given itemText of the current
   *         {@link IItemMenu}
   * @throws RuntimeException if the current {@link IItemMenu} does not contain an
   *                          item with the given itemText
   */
  String getIdByItemText(String itemText);

  /**
   * @return the items of the current {@link IItemMenu}
   */
  ExtendedIterable<IItemMenuItem<?>> getStoredItems();

  /**
   * @return the selected item of the current {@link IItemMenu}
   * @throws RuntimeException if the current {@link IItemMenu} does not contain a
   *                          selected item
   */
  IItemMenuItem<?> getStoredSelectedItem();

  /**
   * @param itemId
   * @return the text of the item with the given itemId of the current
   *         {@link IItemMenu}
   * @throws RuntimeException if the current {@link IItemMenu} does not contain an
   *                          item with the given itemId
   */
  String getTextByItemId(String itemId);

  /**
   * Runs the select action of the current {@link IItemMenu} for the given item if
   * the current {@link IItemMenu} has a select action.
   * 
   * @param item
   */
  void internalRunOptionalSelectActionForItem(IItemMenuItem<?> item);

  /**
   * Removes the select action of the current {@link IItemMenu}.
   */
  void removeSelectAction();

  /**
   * Selects the blank item of the current {@link IItemMenu}.
   * 
   * @return the current {@link IItemMenu}
   * @throws RuntimeException if the current {@link IItemMenu} does not contain a
   *                          blank item
   */
  M selectBlankItem();

  /**
   * Selects the first item of the current {@link IItemMenu}.
   * 
   * @return the current {@link IItemMenu}
   * @throws RuntimeException if the current {@link IItemMenu} is empty
   */
  M selectFirstItem();

  /**
   * Selects the item with the given id of the current {@link IItemMenu}.
   * 
   * @param id
   * @return the current {@link IItemMenu}
   * @throws RuntimeException if the current {@link IItemMenu} does not contain an
   *                          item with the given id
   */
  M selectItemById(String id);

  /**
   * Selects the item with the given text of the current {@link IItemMenu}.
   * 
   * @param text
   * @return the current {@link IItemMenu}
   * @throws RuntimeException if the current {@link IItemMenu} does not contain an
   *                          item with the given text
   */
  M selectItemByText(String text);

  /**
   * Sets the given selectAction to the current {@link IItemMenu}.
   * 
   * @param selectAction
   * @return the current {@link IItemMenu}
   */
  M setSelectAction(Runnable selectAction);

  /**
   * Sets the given selectAction to the current {@link IItemMenu}.
   * 
   * @param selectAction
   * @return the current {@link IItemMenu}
   */
  M setSelectAction(Consumer<IItemMenuItem<?>> selectAction);
}
