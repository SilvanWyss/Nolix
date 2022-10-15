//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IItemMenu<
	IM extends IItemMenu<IM, IMS, IMI>,
	IMS extends IItemMenuStyle<IMS>,
	IMI extends IItemMenuItem<IMI>
> 
extends Clearable, IControl<IM, IMS> {
	
	//method declaration
	IM addItem(@SuppressWarnings("unchecked")IMI... items);
	
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
	boolean containsBlankItem();
	
	//method declaration
	boolean containsItemWithId(String id);
	
	//method declaration
	boolean containsItemWithText(String text);
	
	//method declaration
	String getIdByItemText(String itemText);
	
	//method declaration
	IContainer<IMI> getRefItems();
	
	//method declaration
	String getTextByItemId(String itemId);
	
	//method declaration
	boolean hasSelectAction();
	
	//method declaration
	void removeSelectAction();
	
	//method declaration
	IM selectFirstItem();
	
	//method declaration
	IM setSelectAction(IAction selectAction);
	
	//method declaration
	IM setSelectAction(IElementTaker<IItemMenuItem<?>> selectAction);
}
