/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.reflection.reflectionexaminer;

import java.lang.reflect.Member;

/**
 * @author Silvan Wyss
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
