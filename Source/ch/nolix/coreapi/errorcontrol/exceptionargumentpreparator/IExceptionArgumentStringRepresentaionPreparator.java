package ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator;

/**
 * A {@link IExceptionArgumentStringRepresentaionPreparator} provides method to
 * prepare {@link String} representations of arguments for {@link Exception}s.
 * 
 * @author Silvan Wyss
 * @version 2025-04-06
 */
public interface IExceptionArgumentStringRepresentaionPreparator {

  /**
   * @param argument - Can be null.
   * @return a space enclosed and quoted {@link String} representation of the
   *         given argument with a certain max length.
   */
  String getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(Object argument);

  /**
   * @param argument - Can be null.
   * @return a {@link String} representation of the given argument.
   */
  String getStringRepresentationOfArgument(Object argument);

  /**
   * @param argument - Can be null.
   * @return a {@link String} representation of the given argument with a certain
   *         max length.
   */
  String getStringRepresentationWithMaxLengthOfArgument(Object argument);
}
