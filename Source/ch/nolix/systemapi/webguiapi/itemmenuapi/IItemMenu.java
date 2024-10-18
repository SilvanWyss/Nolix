package ch.nolix.systemapi.webguiapi.itemmenuapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//An IItemMenu can contain 0 or 1 selected item.
public interface IItemMenu<IM extends IItemMenu<IM, IMS>, IMS extends IItemMenuStyle<IMS>>
extends Clearable, IControl<IM, IMS> {

  IM addItem(IItemMenuItem<?> item, IItemMenuItem<?>... items);

  IM addBlankItem();

  IM addItemWithIdAndText(String id, String text);

  IM addItemWithIdAndTextAndSelectAction(String id, String text, Runnable selectAction);

  IM addItemWithIdAndTextAndSelectAction(String id, String text, Consumer<IItemMenuItem<?>> selectAction);

  IM addItemWithText(String text, String... texts);

  IM addItemWithTextAndSelectAction(String text, Runnable selectAction);

  IM addItemWithTextAndSelectAction(String text, Consumer<IItemMenuItem<?>> selectAction);

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

  IM selectBlankItem();

  IM selectFirstItem();

  IM selectItemById(String id);

  IM selectItemByText(String text);

  IM setSelectAction(Runnable selectAction);

  IM setSelectAction(Consumer<IItemMenuItem<?>> selectAction);

  void internalRunOptionalSelectActionForItem(IItemMenuItem<?> item);
}
