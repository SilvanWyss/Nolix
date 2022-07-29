//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.base.StylableElement;
import ch.nolix.system.element.mutableelement.ExtensionElement;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.webguiapi.controllookapi.IControlLook;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public abstract class Control<
	C extends Control<C, CL>,
	CL extends IControlLook<CL>
>
extends StylableElement<C>
implements IControl<C, CL> {
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;
	
	//constant
	private static final String CURSOR_ICON_HEADER = PascalCaseCatalogue.CURSOR_ICON;
	
	//attribute
	private final MutableValue<CursorIcon> cursorIcon =
	new MutableValue<>(
		CURSOR_ICON_HEADER,
		DEFAULT_CURSOR_ICON,
		this::setCursorIcon,
		CursorIcon::fromSpecification,
		Node::fromEnum
	);
	
	//attribute
	private final ExtensionElement<CL> look = new ExtensionElement<>(createLook());
	
	//optional attribute
	private ILayer<?> parentLayer;
	
	//method
	@Override
	public final boolean belongsToGUI() {
		return (belongsToLayer() && getParentLayer().belongsToGUI());
	}
	
	//method
	@Override
	public final CursorIcon getCursorIcon() {
		return cursorIcon.getValue();
	}
	
	//method
	@Override
	public final CL getRefLook() {
		return look.getExtensionElement();
	}
	
	//method
	@Override
	public final IWebGUI<?> getParentGUI() {
		return getParentLayer().getRefParentGUI();
	}
	
	//method
	@Override
	public final ILayer<?> getParentLayer() {
		
		assertBelongsToLayer();
		
		return parentLayer;
	}
	
	//method
	@Override
	public final C setCursorIcon(final CursorIcon cursorIcon) {
		
		this.cursorIcon.setValue(cursorIcon);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final String toHTMLString() {
		return toHTMLElement().toString();
	}
	
	//method declaration
	protected abstract CL createLook();
	
	//method
	final void internalRemoveParentLayer() {
		
		assertBelongsToLayer();
		
		parentLayer = null;
	}
	
	//method
	private void assertBelongsToLayer() {
		if (!belongsToLayer()) {
			throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, ILayer.class);
		}
	}
}
