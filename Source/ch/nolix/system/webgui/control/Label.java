//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.html.HTMLAttribute;
import ch.nolix.core.document.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.document.html.HTMLElement;
import ch.nolix.core.document.html.HTMLElementTypeCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLElement;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.webguiapi.controlapi.ILabel;

//class
public final class Label extends Control<Label, LabelStyle> implements ILabel<Label, LabelStyle> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
	//constant
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	
	//attribute
	private MutableValue<String> text = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);
	
	//method
	@Override
	public IContainer<? extends IStylableElement<?>> getRefChildStylableElements() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public String getText() {
		return text.getValue();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public void noteKeyPress(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteKeyRelease(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteKeyTyping(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteLeftMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteMouseWheelPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteMouseWheelRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteRightMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteRightMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public Label setText(final String text) {
		
		GlobalValidator.assertThat(text).thatIsNamed(LowerCaseCatalogue.TEXT).isNotBlank();
		
		this.text.setValue(text);
		
		return this;
	}
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return
		HTMLElement.withTypeAndAttributes(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(
				HTMLAttribute.withNameAndValue(
					HTMLAttributeNameCatalogue.INNER_TEXT,
					getText()
				)
			)
		);
	}
	
	//method
	@Override
	protected LabelStyle createStyle() {
		return new LabelStyle();
	}
	
	//method
	@Override
	protected void resetControl() {
		setText(DEFAULT_TEXT);
	}
}
