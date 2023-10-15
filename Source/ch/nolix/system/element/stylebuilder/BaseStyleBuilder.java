//package declaration
package ch.nolix.system.element.stylebuilder;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.element.style.BaseSelectingStyle;

//class
abstract class BaseStyleBuilder<SB extends BaseStyleBuilder<SB>> {

  // multi-attribute
  private final LinkedList<Node> attachingAttributes = new LinkedList<>();

  // multi-attribute
  private final LinkedList<BaseSelectingStyle> subStyles = new LinkedList<>();

  // method
  public final SB addAttachingAttribute(final String attachingAttribute, final String... attachingAttributes) {

    final var allAttachingAttributesAsNodes = ReadContainer.forElement(attachingAttribute, attachingAttributes);

    return addAttachingAttributes(allAttachingAttributesAsNodes);
  }

  // method
  public final SB addAttachingAttributes(final IContainer<String> attachingAttributes) {

    final var attachingAttributesAsNodes = attachingAttributes.to(Node::fromString);

    this.attachingAttributes.addAtEnd(attachingAttributesAsNodes);

    return asConcrete();
  }

  // method
  public final SB addSubStyle(final BaseSelectingStyle subStyle, final BaseSelectingStyle... subStyles) {

    final var allSubStyles = ReadContainer.forElement(subStyle, subStyles);

    return addSubStyles(allSubStyles);
  }

  // method
  public final SB addSubStyles(final IContainer<BaseSelectingStyle> subStyles) {

    this.subStyles.addAtEnd(subStyles);

    return asConcrete();
  }

  // method declaration
  protected abstract SB asConcrete();

  // method
  protected final IContainer<Node> getAttachingAttributes() {
    return attachingAttributes;
  }

  // method
  protected final IContainer<BaseSelectingStyle> getSubStyles() {
    return subStyles;
  }
}
