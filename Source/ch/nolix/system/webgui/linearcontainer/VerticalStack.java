//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.systemapi.guiapi.structureproperty.HorizontalContentAlignment;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;

//class
public final class VerticalStack
extends LinearContainer<IVerticalStack, IVerticalStackStyle>
implements IVerticalStack {
	
	//constant
	public static final HorizontalContentAlignment DEFAULT_CONTENT_ALIGNMENT = HorizontalContentAlignment.LEFT;
	
	//constant
	private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";
	
	//constant
	private static final VerticalStackHtmlBuilder HTML_BUILDER = new VerticalStackHtmlBuilder();
	
	//constant
	private static final VerticalStackCssRuleBuilder CSS_RULE_BUILDER = new VerticalStackCssRuleBuilder();
	
	//attribute
	private final MutableValue<HorizontalContentAlignment> contentAlignment =
	new MutableValue<>(
		CONTENT_ALIGNMENT_HEADER,
		DEFAULT_CONTENT_ALIGNMENT,
		this::setContentAlignment,
		HorizontalContentAlignment::fromSpecification,
		Node::fromEnum
	);
	
	//method
	@Override
	public HorizontalContentAlignment getContentAlignment() {
		return contentAlignment.getValue();
	}
	
	//method
	@Override
	public IVerticalStack setContentAlignment(final HorizontalContentAlignment contentAlignment) {
		
		this.contentAlignment.setValue(contentAlignment);
		
		return this;
	}
	
	//method
	@Override
	protected IVerticalStackStyle createStyle() {
		return new VerticalStackStyle();
	}
	
	//method
	@Override
	protected IControlCssRuleBuilder<IVerticalStack, IVerticalStackStyle> getCssRuleCreator() {
		return CSS_RULE_BUILDER;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<IVerticalStack> getHtmlBuilder() {
		return HTML_BUILDER;
	}
	
	//method
	@Override
	protected void resetContainer() {
		setContentAlignment(DEFAULT_CONTENT_ALIGNMENT);
	}
}
