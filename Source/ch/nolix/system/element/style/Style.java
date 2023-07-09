//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyle;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;

//class
/**
 * @author Silvan Wyss
 * @date 2016-02-01
 */
public final class Style extends BaseStyle implements IStyle {
	
	//static method
	/**
	 * @param filePath
	 * @return a new standard specification from the file with the given file path.
	 * @throws InvalidArgumentException if the given file path is not valid.
	 * @throws InvalidArgumentException
	 * if the file with the given file path does not represent a standard configuration.
	 */
	public static Style fromFile(final String filePath) {
		
		//TODO: Implement.
		return null;
	}
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Style} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Style fromSpecification(final INode<?> specification) {
		
		//TODO: Implement.
		return null;
	}
	
	//constructor
	/**
	 * Creates a new {@link Style}.
	 * 
	 * @param attachingAttributes
	 * @param subStyles
	 */
	public Style(
		final IContainer<INode<?>> attachingAttributes,
		final IContainer<BaseSelectingStyle> subStyles
	) {
		super(attachingAttributes, subStyles);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<INode<?>> getAttributes() {
		return
		ReadContainer.forIterables(
			getAttachingAttributes().to(a -> Node.withHeaderAndChildNode(ATTACHING_ATTRIBUTE_HEADER, a)),
			getSubStyles().to(ISelectingStyle::getSpecification)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void styleElement(final IStylableElement<?> element) {
		setAttachingAttributesToElement(element);
		letSubStylesStyleChildElementsOfElement(element);
	}
}
