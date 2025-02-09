package ch.nolix.coreapi.structurecontrolapi.reflectionexaminerapi;

import java.lang.reflect.Member;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public interface IMemberExaminer {

  /**
   * @param member
   * @return true if the given member is private, false otherwise.
   */
  boolean isPrivate(Member member);

  /**
   * @param member
   * @return true if the given member is protected, false otherwise.
   */
  boolean isProtected(Member member);

  /**
   * @param member
   * @return true if the given member is public, false otherwise.
   */
  boolean isPublic(Member member);
}
