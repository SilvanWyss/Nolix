package ch.nolix.core.document.node;

import java.util.function.Predicate;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.GlobalFileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

/**
 * A {@link FileNode} is a specification that is stored in a file.
 * 
 * @author Silvan Wyss
 * @version 2017-07-14
 */
public final class FileNode extends BaseMutableNode<FileNode> {

  private final IMutableNode<?> internalSpecification;

  private final FileAccessor fileAccessor;

  private final FileNode parentFileNode;

  /**
   * Creates a new {@link FileNode} with the given file path. Creates a new file
   * if there does not exist a file with the given file path. Access and changes
   * the file if there exists a file with the given file path.
   * 
   * @param filePath
   */
  public FileNode(final String filePath) {

    //Handles the case that there does not exist a file system item with the given
    //filePath.
    if (!GlobalFileSystemAccessor.exists(filePath)) {
      fileAccessor = GlobalFileSystemAccessor.createFile(filePath);

      //Handles the case that there exists a file with the given filePath.
    } else if (GlobalFileSystemAccessor.isFile(filePath)) {
      fileAccessor = new FileAccessor(filePath);

      //Handles the case that there exists file system item with the given filePath
      //that is not a file.
    } else {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(filePath, "is not a file");
    }

    internalSpecification = MutableNode.fromFile(filePath);

    parentFileNode = null;
  }

  /**
   * Creates a new {@link FileNode} that: -Belongs to the given parentFileNode.
   * -Has the given internal specification.
   * 
   * @param parentFileNode
   * @param internalSpecification
   */
  private FileNode(final FileNode parentFileNode, final IMutableNode<?> internalSpecification) {

    //Asserts that the given simple persistent specification is not null.
    GlobalValidator
      .assertThat(parentFileNode)
      .isOfType(FileNode.class);

    //Asserts that the given internal specification is not null.
    GlobalValidator.assertThat(internalSpecification)
      .thatIsNamed("internal specification")
      .isNotNull();

    //Sets the simple persistent specification of the current SubNode.
    this.parentFileNode = parentFileNode;

    //Sets the internal specification of the current SubNode.
    this.internalSpecification = internalSpecification;

    fileAccessor = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FileNode addChildNode(final INode<?> childNode, final INode<?>... childNodes) {

    internalSpecification.addChildNode(childNode, childNodes);
    save();

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FileNode addChildNodeFromString(final String string, final String... strings) {

    internalSpecification.addChildNodeFromString(string, strings);
    save();

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <N extends INode<?>> FileNode addChildNodes(final Iterable<N> childNodes) {

    internalSpecification.addChildNodes(childNodes);
    save();

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FileNode addChildNodesFromStrings(final Iterable<String> strings) {

    internalSpecification.addChildNodesFromStrings(strings);
    save();

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
  public boolean equals(final Object object) {

    if (!(object instanceof final FileNode)) {
      return false;
    }

    return equalsNode((FileNode) object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeader() {
    return internalSpecification.getHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ContainerView<FileNode> getStoredChildNodes() {
    return ContainerView.forIterable(
      internalSpecification.getStoredChildNodes().to(
        a -> new FileNode(getStoredRootFileNode(), a)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasHeader() {
    return internalSpecification.hasHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FileNode removeAndGetStoredFirstChildNodeThat(final Predicate<INode<?>> selector) {

    final var attribute = internalSpecification.removeAndGetStoredFirstChildNodeThat(selector::test);
    save();

    return new FileNode(this, attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirstChildNodeThat(final Predicate<INode<?>> selector) {
    internalSpecification.removeFirstChildNodeThat(selector);
    save();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirstChildNodeWithHeader(String header) {
    internalSpecification.removeFirstChildNodeWithHeader(header);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeChildNodes() {
    internalSpecification.removeChildNodes();
    save();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FileNode removeHeader() {

    internalSpecification.removeHeader();

    save();

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void replaceFirstChildNodeWithGivenHeaderByGivenNode(final String header, final INode<?> node) {
    internalSpecification.replaceFirstChildNodeWithGivenHeaderByGivenNode(header, node);
    save();
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
  public FileNode setHeader(final String header) {

    internalSpecification.setHeader(header);
    save();

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected FileNode asConcrete() {
    return this;
  }

  /**
   * @return the root {@link FileNode} of the current {@link FileNode}.
   */
  private FileNode getStoredRootFileNode() {

    if (!isRootFileNode()) {
      return parentFileNode.getStoredRootFileNode();
    }

    return this;
  }

  /**
   * @return true if the current {@link FileNode} is a root {@link FileNode}.
   */
  private boolean isRootFileNode() {
    return (fileAccessor != null);
  }

  /**
   * Saves this {@link FileNode}.
   * 
   * @throws RuntimeException if an error occurs.
   */
  private void save() {
    if (!isRootFileNode()) {
      save();
    } else {
      fileAccessor.overwriteFile(internalSpecification.toFormattedString());
    }
  }
}
