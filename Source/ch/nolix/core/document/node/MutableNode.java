package ch.nolix.core.document.node;

import java.util.function.Predicate;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public final class MutableNode extends AbstractMutableNode<MutableNode> {
  private String memberHeader;

  private final LinkedList<MutableNode> memberChildNodes = LinkedList.createEmpty();

  /**
   * Creates a new empty {@link MutableNode}.
   */
  private MutableNode() {
  }

  /**
   * @return a new empty {@link MutableNode}.
   */
  public static MutableNode createEmpty() {
    return new MutableNode();
  }

  /**
   * @param filePath
   * @return a new {@link MutableNode} from the file with the given filePath.
   * @throws InvalidArgumentException        if the given filePath is not valid.
   * @throws UnrepresentingArgumentException if the file with the given filePath
   *                                         does not represent a
   *                                         {@link MutableNode}.
   */
  public static MutableNode fromFile(final String filePath) {
    final var mutableNode = MutableNode.createEmpty();
    mutableNode.resetFromFile(filePath);

    return mutableNode;
  }

  /**
   * @param node
   * @return a new {@link MutableNode} from the given {@link INode}.
   */
  public static MutableNode fromNode(final INode<?> node) {
    final var mutableNode = MutableNode.createEmpty();

    if (node.hasHeader()) {
      mutableNode.setHeader(node.getHeader());
    }

    mutableNode.addChildNodes(node.getStoredChildNodes());

    return mutableNode;
  }

  /**
   * @param string
   * @return a new {@link MutableNode} from the given string.
   * @throws UnrepresentingArgumentException if the given string does not
   *                                         represent a {@link MutableNode}.
   */
  public static MutableNode fromString(final String string) {
    final var mutableNode = MutableNode.createEmpty();
    mutableNode.resetFromString(string);

    return mutableNode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode addChildNode(final INode<?> childNode, final INode<?>... childNodes) {
    this.memberChildNodes.addAtEnd(fromNode(childNode));

    for (final var c : childNodes) {
      this.memberChildNodes.addAtEnd(fromNode(c));
    }

    return this;
  }

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

  /**
   * {@inheritDoc}
   */
  @Override
  public <N extends INode<?>> MutableNode addChildNodes(final Iterable<N> pChildNodes) {
    for (final var c : pChildNodes) {
      memberChildNodes.addAtEnd(fromNode(c));
    }

    return this;
  }

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

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> asWithHeader(String header) {
    return Node.withHeaderAndChildNodes(header, getStoredChildNodes());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<MutableNode> getStoredChildNodes() {
    return memberChildNodes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeader() {
    assertHasHeader();

    return memberHeader;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasHeader() {
    return (memberHeader != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeChildNodes() {
    memberChildNodes.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode removeAndGetStoredFirstChildNodeThat(final Predicate<INode<?>> selector) {
    return memberChildNodes.removeAndGetStoredFirst(selector::test);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirstChildNodeThat(final Predicate<INode<?>> selector) {
    memberChildNodes.removeFirst(selector::test);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirstChildNodeWithHeader(String header) {
    memberChildNodes.removeFirst(cn -> cn.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeHeader() {
    memberHeader = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void replaceFirstChildNodeWithGivenHeaderByGivenNode(final String header, final INode<?> childNode) {
    memberChildNodes.replaceFirst(a -> a.hasHeader(header), fromNode(childNode));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    removeHeader();
    removeChildNodes();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode setHeader(final String header) {
    Validator.assertThat(header).thatIsNamed(LowerCaseVariableCatalog.HEADER).isNotBlank();

    this.memberHeader = header;

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected MutableNode asConcrete() {
    return this;
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link MutableNode} does not
   *                                               have a header.
   */
  private void assertHasHeader() {
    if (!hasHeader()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.HEADER);
    }
  }
}
