package ch.nolix.systemapi.element.style;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.pair.IPair;
import ch.nolix.systemapi.element.base.IElement;

/**
 * A {@link IAbstractStyle} can style {@link IStylableElement}s. A
 * {@link IAbstractStyle} can distinguish if it would style also the child elements
 * of a given {@link IStylableElement}.
 * 
 * @author Silvan Wyss
 * @version 2023-07-09
 * @param <S> is the type of a {@link IAbstractStyle}.
 */
public interface IAbstractStyle<S extends IAbstractStyle<S>> extends IElement {

  /**
   * Applies the current {@link IAbstractStyle} to the given element.
   * 
   * @param element
   */
  void applyToElement(IStylableElement<?> element);

  /**
   * @return the attaching attributes of the current {@link IAbstractStyle}.
   */
  IContainer<? extends IAttachingAttribute> getAttachingAttributes();

  /**
   * @return the sub styles of the current {@link IAbstractStyle}.
   */
  IContainer<? extends ISelectingStyleWithSelectors> getSubStyles();

  /**
   * @return true if the current {@link IAbstractStyle} has attaching attributes,
   *         false otherwise.
   */
  boolean hasAttachingAttributes();

  /**
   * @param tag
   * @param value
   * @return a new {@link IStyle} from the current {@link IStyle} with an
   *         attaching attribute added that has the given tag and value.
   * @throws RuntimeException if the given tag is null.
   * @throws RuntimeException if the given attachingAttribute is not valid.
   */
  S withAttachingAttribute(Enum<?> tag, String value);

  /**
   * @param attachingAttribute
   * @param attachingAttributes
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         attachingAttribute and attachingAttributes added.
   * @throws RuntimeException if the given attachingAttribute is not valid.
   * @throws RuntimeException if the given attachingAttributes is null.
   * @throws RuntimeException if one of the given attachingAttributes is not
   *                          valid.
   */
  S withAttachingAttribute(String attachingAttribute, String... attachingAttributes);

  /**
   * @param attachingAttributes
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         attachingAttributes added.
   * @throws RuntimeException if the given attachingAttributes is null.
   * @throws RuntimeException if one of the given attachingAttributes is not
   *                          valid.
   */
  S withAttachingAttributes(IContainer<? extends IAttachingAttribute> attachingAttributes);

  /**
   * @param selectorType
   * @param newAttachingAttributes
   * @return a new {@link IAbstractStyle} from the current {@link IAbstractStyle} where
   *         each of the given newAttachingAttributes was either added or updated
   *         the according attachingAttributes where the given selectorType is.
   */
  S withNewAttachingAttributesWhereSelectorType(String selectorType, IContainer<String> newAttachingAttributes);

  /**
   * @param selectorType
   * @param newAttachingAttribute
   * @param newAttachingAttributes
   * @return a new {@link IAbstractStyle} from the current {@link IAbstractStyle} where
   *         the newAttachingAttribute and each of the given
   *         newAttachingAttributes either was added or updated the according
   *         attachingAttributes where the given selectorType is.
   */
  S withNewAttachingAttributesWhereSelectorType(
    String selectorType,
    String newAttachingAttribute,
    String... newAttachingAttributes);

  /**
   * @param attachingAttributeReplacements
   * @return a new {@link IStyle} from the current {@link IStyle} where the given
   *         attachingAttributeReplacements replaced all the according attaching
   *         attributes.
   */
  S withReplacedAttachingAttributes(IContainer<IPair<String, String>> attachingAttributeReplacements);

  /**
   * @param attachingAttributeReplacement
   * @param attachingAttributeReplacements
   * @return a new {@link IStyle} from the current {@link IStyle} where the given
   *         attributeAttributeReplacement and attachingAttributeReplacements
   *         replaced all the according attachingAttributes.
   */
  S withReplacedAttachingAttributes(
    IPair<String, String> attachingAttributeReplacement,
    @SuppressWarnings("unchecked") IPair<String, String>... attachingAttributeReplacements);

  /**
   * @param attachingAttributeReplacements
   * @return a new {@link IStyle} from the current {@link IStyle} where the given
   *         attachingAttributeReplacements replaced all the according attaching
   *         attributes.
   */
  S withReplacedTaggedAttachingAttributes(IContainer<IPair<Enum<?>, String>> attachingAttributeReplacements);

  /**
   * @param attachingAttributeReplacement
   * @param attachingAttributeReplacements
   * @return a new {@link IStyle} from the current {@link IStyle} where the given
   *         attributeAttributeReplacement and attachingAttributeReplacements
   *         replaced all the according attachingAttributes.
   */
  S withReplacedTaggedAttachingAttributes(
    IPair<Enum<?>, String> attachingAttributeReplacement,
    @SuppressWarnings("unchecked") IPair<Enum<?>, String>... attachingAttributeReplacements);

  /**
   * @param subStyle
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         subStyle and subStyles added.
   * @throws RuntimeException if the given subStyle is not valid.
   * @throws RuntimeException if the given subStyles is null.
   * @throws RuntimeException if one of the given subStyles is not valid.
   */
  S withSubStyle(ISelectingStyleWithSelectors subStyle, ISelectingStyleWithSelectors... subStyles);

  /**
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         subStyles added.
   * @throws RuntimeException if the given subStyles is null.
   * @throws RuntimeException if one of the given subStyles is not valid.
   */
  S withSubStyles(IContainer<? extends ISelectingStyleWithSelectors> subStyles);
}
