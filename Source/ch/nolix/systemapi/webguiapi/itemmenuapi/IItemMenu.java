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
	IM extends IItemMenu<IM, IMI, IML>,
	IMI extends IItemMenuItem,
	IML extends IItemMenuLook<IML>
> 
extends Clearable, IControl<IM, IML> {
	
	//method declaration
	IM addItem(@SuppressWarnings("unchecked")IMI... items);
	
	//method declaration
	IM addBlankItem();
	
	//method declaration
	IM addItemWithIdAndText(String it, String text);
	
	//method declaration
	IM addItemWithIdAndTextAndSelectAction(String id, String text, IAction selectAction);
	
	//method declaration
	IM addItemWithIdAndTextAndSelectAction(String id, String text, IElementTaker<IItemMenuItem> selectAction);
	
	//method declaration
	IM addItemWithText(String... texts);
	
	//method declaration
	IM addItemWithTextAndSelectAction(String text, IAction selectAction);
	
	//method declaration
	IM addItemWithTextAndSelectAction(String text, IElementTaker<IItemMenuItem> selectAction);
	
	//method declaration
	boolean containsBlankItem();
	
	//method declaration
	boolean containsItemWithId(String id);
	
	//method declaration
	boolean containsItemWithText(String text);
	
	//method declaration
	String getIdByItemText(String itemTxt);
	
	//method declaration
	String getTextByItemId(String itemId);
	
	//method declaration
	IM selectFirstItem();
	
	//method declaration
	IContainer<IMI> getRefItems();
}
