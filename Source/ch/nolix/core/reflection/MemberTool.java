package ch.nolix.core.reflection;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public final class MemberTool {

  public boolean isPrivate(final Member member) {
    return Modifier.isPrivate(member.getModifiers());
  }

  public boolean isProtected(final Member member) {
    return Modifier.isProtected(member.getModifiers());
  }

  public boolean isPublic(final Member member) {
    return Modifier.isPublic(member.getModifiers());
  }
}
