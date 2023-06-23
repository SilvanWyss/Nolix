//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.systemapi.guiapi.structureproperty.VerticalContentAlignment;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStackStyle;

//class
public final class HorizontalStack
extends LinearContainer<IHorizontalStack, IHorizontalStackStyle>
implements IHorizontalStack {
	
	//constant
	public static final VerticalContentAlignment DEFAULT_CONTENT_ALIGNMENT = VerticalContentAlignment.TOP;
	
	//constant
	private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";
	
	//attribute
	private final MutableValue<VerticalContentAlignment> contentAlignment =
	new MutableValue<>(
		CONTENT_ALIGNMENT_HEADER,
		DEFAULT_CONTENT_ALIGNMENT,
		this::setContentAlignment,
		VerticalContentAlignment::fromSpecification,
		Node::fromEnum
	);
	
	//method
	@Override
	public VerticalContentAlignment getContentAlignment() {
		return contentAlignment.getValue();
	}
	
	//method
	@Override
	public IHorizontalStack setContentAlignment(final VerticalContentAlignment contentAlignment) {
		
		this.contentAlignment.setValue(contentAlignment);
		
		return this;
	}
	
	//method
	@Override
	protected IHorizontalStackStyle createStyle() {
		return new HorizontalStackStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<IHorizontalStack, IHorizontalStackStyle> getCSSRuleCreator() {
		return HorizontalStackCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<IHorizontalStack> getHTMLBuilder() {
		return HorizontalStackHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetContainer() {
		setContentAlignment(DEFAULT_CONTENT_ALIGNMENT);
	}
}
