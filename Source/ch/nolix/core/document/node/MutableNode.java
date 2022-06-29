//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerBooleanGetter;

//class
public final class MutableNode extends BaseMutableNode<MutableNode> {
	
	//static method
	/**
	 * @param filePath
	 * @return a new {@link MutableNode} from the file with the given filePath.
	 * @throws InvalidArgumentException if the given filePath is not valid.
	 * @throws UnrepresentingArgumentException if the file with the given filePath does not represent a {@link MutableNode}.
	 */
	public static MutableNode fromFile(final String filePath) {
		
		final var mutableNode = new MutableNode();
		mutableNode.resetFromFile(filePath);
		
		return mutableNode;
	}
	
	//static method
	/**
	 * @param node
	 * @return a new {@link MutableNode} from the given {@link INode}.
	 */
	public static MutableNode fromNode(final INode<?> node) {
		
		final var mutableNode = new MutableNode();
		
		if (node.hasHeader()) {
			mutableNode.setHeader(node.getHeader());
		}
		
		mutableNode.addChildNodes(node.getRefChildNodes());
		
		return mutableNode;
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link MutableNode} from the given string.
	 * @throws UnrepresentingArgumentException if the given string does not represent a {@link MutableNode}.
	 */
	public static MutableNode fromString(final String string) {
		
		final var mutableNode = new MutableNode();
		mutableNode.resetFromString(string);
		
		return mutableNode;
	}
	
	//optional attribute
	private String header;
	
	//multi-attribute
	private final LinkedList<MutableNode> childNodes = new LinkedList<>();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MutableNode addChildNode(final INode<?>... pChildNodes) {
		
		for (final var cn : pChildNodes) {
			childNodes.addAtEnd(fromNode(cn));
		}
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MutableNode addChildNodeFromString(final String... strings) {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <N extends INode<?>> MutableNode addChildNodes(final Iterable<N> pChildNodes) {
		
		for (final var cn : pChildNodes) {
			childNodes.addAtEnd(fromNode(cn));
		}
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MutableNode addChildNodesFromStrings(final Iterable<String> strings) {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<MutableNode> getRefChildNodes() {
		return childNodes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHeader() {
		
		assertHasHeader();
		
		return header;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasHeader() {
		return (header != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeChildNodes() {
		childNodes.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MutableNode removeAndGetRefFirstChildNodeThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		return childNodes.removeAndGetRefFirst(selector::getOutput);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeFirstChildNodeThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		childNodes.removeFirst(selector);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeHeader() {
		header = null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void replaceFirstChildNodeWithGivenHeaderByGivenNode(final String header, final INode<?> childNode) {
		childNodes.replaceFirst(a -> a.hasHeader(header), fromNode(childNode));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		removeHeader();
		removeChildNodes();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MutableNode setHeader(final String header) {
		
		GlobalValidator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		
		this.header = header;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MutableNode asConcrete() {
		return this;
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link MutableNode} does not have a header.
	 */
	private void assertHasHeader() {
		if (!hasHeader()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.HEADER);
		}
	}
}
