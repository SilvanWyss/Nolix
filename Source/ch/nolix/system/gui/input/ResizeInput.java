//package declaration
package ch.nolix.system.gui.input;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.pair.IntPair;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.guiapi.inputapi.IResizeInput;

//class
public final class ResizeInput implements IResizeInput<ResizeInput> {
	
	//constant
	private static final String SIZE_HEADER = PascalCaseCatalogue.SIZE;
	
	//static method
	public static ResizeInput fromSpecification(final INode<?> specification) {
		
		final var sizeSpecification = specification.getRefSingleChildNode();
		
		return
		withViewAreaWidthAndViewAreaHeight(
			sizeSpecification.getRefChildNodeAt1BasedIndex(1).toInt(),
			sizeSpecification.getRefChildNodeAt1BasedIndex(2).toInt()
		);
	}
	
	//static method
	public static ResizeInput withSize(final IntPair size) {
		return new ResizeInput(size);
	}
	
	//static method
	public static ResizeInput withViewAreaWidthAndViewAreaHeight(final int viewAreaWidth, final int viewAreaHeight) {
		return new ResizeInput(new IntPair(viewAreaWidth, viewAreaHeight));
	}
			
	//attribute
	private final IntPair size;
	
	//constructor
	private ResizeInput(final IntPair size) {
		
		GlobalValidator.assertThat(size).thatIsNamed(LowerCaseCatalogue.SIZE).isNotNull();
		GlobalValidator.assertThat(size.getValue1()).thatIsNamed("view area width").isNotNegative();
		GlobalValidator.assertThat(size.getValue2()).thatIsNamed("view area hegiht").isNotNegative();
		
		this.size = size;
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final IMutableList<INode<?>> list) {
		
		final var sizeSpecification =
		Node.withHeaderAndChildNode(
			SIZE_HEADER,
			Node.withHeader(getViewAreaWidth()),
			Node.withHeader(getViewAreaHeight())
		);
		
		list.addAtEnd(sizeSpecification);
	}
	
	//method
	@Override
	public IContainer<INode<?>> getAttributes() {
		return
		LinkedList.withElements(
			Node.withHeaderAndChildNode(
				SIZE_HEADER,
				Node.withHeader(getViewAreaWidth()),
				Node.withHeader(getViewAreaHeight())
			)
		);
	}
	
	//method
	@Override
	public int getViewAreaHeight() {
		return size.getValue2();
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		return size.getValue1();
	}
}
