package ch.nolix.core.language;

public final class EnglishWordEndAnalyser {

  public boolean endsWithVocalAndY(final String noun) {
    return //
    noun.endsWith("ay") //NOSONAR: This method is uniform.
    || noun.endsWith("ey")
    || noun.endsWith("iy")
    || noun.endsWith("oy")
    || noun.endsWith("uy");
  }
}
