//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
//An IItemMenu can contain 0 or 1 selected item.
public interface IItemMenu<
	IM extends IItemMenu<IM, IMS>,
	IMS extends IItemMenuStyle<IMS>
> 
extends Clearable, IControl<IM, IMS> {
	
	//method declaration
	IM addItem(IItemMenuItem<?>... items);
	
	//method declaration
	IM addBlankItem();
	
	//method declaration
	IM addItemWithIdAndText(String id, String text);
	
	//method declaration
	IM addItemWithIdAndTextAndSelectAction(String id, String text, IAction selectAction);
	
	//method declaration
	IM addItemWithIdAndTextAndSelectAction(String id, String text, IElementTaker<IItemMenuItem<?>> selectAction);
	
	//method declaration
	IM addItemWithText(String... texts);
	
	//method declaration
	IM addItemWithTextAndSelectAction(String text, IAction selectAction);
	
	//method declaration
	IM addItemWithTextAndSelectAction(String text, IElementTaker<IItemMenuItem<?>> selectAction);
	
	//method declaration
	boolean blankItemIsSelected();
	
	//method declaration
	boolean containsBlankItem();
	
	//method declaration
	boolean containsItemWithId(String id);
	
	//method declaration
	boolean containsItemWithText(String text);
	
	//method declaration
	boolean containsSelectedItem();
	
	//method declaration
	String getIdByItemText(String itemText);
	
	//method declaration
	IContainer<IItemMenuItem<?>> getOriItems();
	
	//method declaration
	IItemMenuItem<?> getOriSelectedItem();
	
	//method declaration
	String getTextByItemId(String itemId);
	
	//method declaration
	void removeSelectAction();
	
	//method declaration
	IM selectBlankItem();
	
	//method declaration
	IM selectFirstItem();
	
	//method declaration
	IM selectItemById(String id);
	
	//method declaration
	IM selectItemByText(String text);
	
	//method declaration
	IM setSelectAction(IAction selectAction);
	
	//method declaration
	IM setSelectAction(IElementTaker<IItemMenuItem<?>> selectAction);
	
	//method declaration
	void technicalRunOptionalSelectActionForItem(IItemMenuItem<?> item);
}
