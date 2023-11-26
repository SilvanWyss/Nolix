//package declaration
package ch.nolix.core.document.node;

//Java imports
import java.util.function.Predicate;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public final class MutableNode extends BaseMutableNode<MutableNode> {

  //optional attribute
  private String header;

  //multi-attribute
  private final LinkedList<MutableNode> childNodes = new LinkedList<>();

  //static method
  /**
   * @param filePath
   * @return a new {@link MutableNode} from the file with the given filePath.
   * @throws InvalidArgumentException        if the given filePath is not valid.
   * @throws UnrepresentingArgumentException if the file with the given filePath
   *                                         does not represent a
   *                                         {@link MutableNode}.
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

    mutableNode.addChildNodes(node.getStoredChildNodes());

    return mutableNode;
  }

  //static method
  /**
   * @param string
   * @return a new {@link MutableNode} from the given string.
   * @throws UnrepresentingArgumentException if the given string does not
   *                                         represent a {@link MutableNode}.
   */
  public static MutableNode fromString(final String string) {

    final var mutableNode = new MutableNode();
    mutableNode.resetFromString(string);

    return mutableNode;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode addChildNode(final INode<?> childNode, final INode<?>... childNodes) {

    this.childNodes.addAtEnd(fromNode(childNode));

    for (final var cn : childNodes) {
      this.childNodes.addAtEnd(fromNode(cn));
    }

    return this;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode addChildNodeFromString(final String string, final String... strings) {

    addChildNode(fromString(string));

    for (final var s : strings) {
      addChildNode(fromString(s));
    }

    return this;
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

    for (final var s : strings) {
      addChildNode(fromString(s));
    }

    return this;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> asWithHeader(String header) {
    return Node.withHeaderAndChildNodes(header, getStoredChildNodes());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {

    if (!(object instanceof MutableNode)) {
      return false;
    }

    return equalsNode((MutableNode) object);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<MutableNode> getStoredChildNodes() {
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
  public int hashCode() {
    return toString().hashCode();
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
  public MutableNode removeAndGetRefFirstChildNodeThat(final Predicate<INode<?>> selector) {
    return childNodes.removeAndGetStoredFirst(selector::test);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirstChildNodeThat(final Predicate<? extends INode<?>> selector) {
    childNodes.removeFirstOccurrenceOf(selector);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirstChildNodeWithHeader(String header) {
    childNodes.removeFirst(cn -> cn.hasHeader(header));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode removeHeader() {

    header = null;

    return this;
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
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link MutableNode} does not
   *                                               have a header.
   */
  private void assertHasHeader() {
    if (!hasHeader()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.HEADER);
    }
  }
}
