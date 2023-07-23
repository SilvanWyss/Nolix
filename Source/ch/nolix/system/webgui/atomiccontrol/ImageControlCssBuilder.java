//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlcssbuilder.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControl;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class ImageControlCssBuilder
extends ControlCssBuilder<IImageControl, IImageControlStyle> {
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		final IImageControl imageControl,
		final LinkedList<? super ICssRule> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
		final IImageControl imageControl,
		final ControlState state,
		final LinkedList<? super ICssRule> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
		final IImageControl control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndStateIntoList(
		final IImageControl imageControl,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		list.addAtEnd(CssProperty.withNameAndValue(CssPropertyNameCatalogue.DISPLAY, "block"));
	}
}
