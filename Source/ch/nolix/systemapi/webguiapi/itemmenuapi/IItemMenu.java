//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
//An IItemMenu can contain 0 or 1 selected item.
public interface IItemMenu<IM extends IItemMenu<IM, IMS>, IMS extends IItemMenuStyle<IMS>>
    extends Clearable, IControl<IM, IMS> {

  // method declaration
  IM addItem(IItemMenuItem<?> item, IItemMenuItem<?>... items);

  // method declaration
  IM addBlankItem();

  // method declaration
  IM addItemWithIdAndText(String id, String text);

  // method declaration
  IM addItemWithIdAndTextAndSelectAction(String id, String text, Runnable selectAction);

  // method declaration
  IM addItemWithIdAndTextAndSelectAction(String id, String text, Consumer<IItemMenuItem<?>> selectAction);

  // method declaration
  IM addItemWithText(String text, String... texts);

  // method declaration
  IM addItemWithTextAndSelectAction(String text, Runnable selectAction);

  // method declaration
  IM addItemWithTextAndSelectAction(String text, Consumer<IItemMenuItem<?>> selectAction);

  // method declaration
  boolean blankItemIsSelected();

  // method declaration
  boolean containsBlankItem();

  // method declaration
  boolean containsItemWithId(String id);

  // method declaration
  boolean containsItemWithText(String text);

  // method declaration
  boolean containsSelectedItem();

  // method declaration
  String getIdByItemText(String itemText);

  // method declaration
  IContainer<IItemMenuItem<?>> getStoredItems();

  // method declaration
  IItemMenuItem<?> getStoredSelectedItem();

  // method declaration
  String getTextByItemId(String itemId);

  // method declaration
  void removeSelectAction();

  // method declaration
  IM selectBlankItem();

  // method declaration
  IM selectFirstItem();

  // method declaration
  IM selectItemById(String id);

  // method declaration
  IM selectItemByText(String text);

  // method declaration
  IM setSelectAction(Runnable selectAction);

  // method declaration
  IM setSelectAction(Consumer<IItemMenuItem<?>> selectAction);

  // method declaration
  void technicalRunOptionalSelectActionForItem(IItemMenuItem<?> item);
}
