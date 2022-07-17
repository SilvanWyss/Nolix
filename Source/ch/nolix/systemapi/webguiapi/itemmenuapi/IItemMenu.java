//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IItemMenu<
	IM extends IItemMenu<IM, IMI, IML>,
	IMI extends IItemMenuItem<IMI>,
	IML extends IItemMenuLook<IML>
> 
extends Clearable, IControl<IM, IML> {
	
	//method declaration
	IM addItem(@SuppressWarnings("unchecked")IMI... items);
	
	//method declaration
	IContainer<IMI> getRefItems();
}
