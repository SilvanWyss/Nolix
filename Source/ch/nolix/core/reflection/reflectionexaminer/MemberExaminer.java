package ch.nolix.core.reflection.reflectionexaminer;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

import ch.nolix.coreapi.reflection.reflectionexaminer.IMemberExaminer;

/**
 * @author Silvan Wyss
 */
public final class MemberExaminer implements IMemberExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isPrivate(final Member member) {
    return //
    member != null
    && Modifier.isPrivate(member.getModifiers());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isProtected(final Member member) {
    return //
    member != null
    && Modifier.isProtected(member.getModifiers());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isPublic(final Member member) {
    return //
    member != null
    && Modifier.isPublic(member.getModifiers());
  }
}
