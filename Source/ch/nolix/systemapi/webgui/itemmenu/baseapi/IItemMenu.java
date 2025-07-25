package ch.nolix.systemapi.webgui.itemmenu.baseapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.state.statemutation.Clearable;
import ch.nolix.systemapi.webgui.main.IControl;

//An IItemMenu can contain 0 or 1 selected item.
public interface IItemMenu<M extends IItemMenu<M, S>, S extends IItemMenuStyle<S>> extends Clearable, IControl<M, S> {

  M addItem(IItemMenuItem<?> item, IItemMenuItem<?>... items);

  M addBlankItem();

  M addItemWithIdAndText(String id, String text);

  M addItemWithIdAndTextAndSelectAction(String id, String text, Runnable selectAction);

  M addItemWithIdAndTextAndSelectAction(String id, String text, Consumer<IItemMenuItem<?>> selectAction);

  M addItemWithText(String text, String... texts);

  M addItemWithTextAndSelectAction(String text, Runnable selectAction);

  M addItemWithTextAndSelectAction(String text, Consumer<IItemMenuItem<?>> selectAction);

  boolean blankItemIsSelected();

  boolean containsBlankItem();

  boolean containsItemWithId(String id);

  boolean containsItemWithText(String text);

  boolean containsSelectedItem();

  String getIdByItemText(String itemText);

  IContainer<IItemMenuItem<?>> getStoredItems();

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
