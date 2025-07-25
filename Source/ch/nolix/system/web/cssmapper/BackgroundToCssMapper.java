package ch.nolix.system.web.cssmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.guiapi.backgroundapi.IBackground;
import ch.nolix.systemapi.webapi.cssmapperapi.IBackgroundToCssMapper;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public final class BackgroundToCssMapper implements IBackgroundToCssMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ICssProperty> mapBackgroundToCssProperties(final IBackground background) {

    final var type = background.getType();

    return switch (type) {
      case COLOR ->
        BackgroundToCssMapperHelper.mapBackgroundToCssPropertiesWhenIsColor(background);
      case COLOR_GRADIENT ->
        BackgroundToCssMapperHelper.mapBackgroundToCssPropertiesWhenIsColorGradient(background);
      case IMAGE ->
        BackgroundToCssMapperHelper.mapBackgroundToCssPropertiesWhenIsImage(background);
      case TRANSPARENCY ->
        BackgroundToCssMapperHelper.TRANSPARENT_BACKGROUND_CSS_PROPERTIES;
      default ->
        throw InvalidArgumentException.forArgument(type);
    };
  }
}
