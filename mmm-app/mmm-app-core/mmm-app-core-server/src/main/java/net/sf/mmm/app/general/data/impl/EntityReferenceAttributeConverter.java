package net.sf.mmm.app.general.data.impl;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import net.sf.mmm.app.general.common.api.EntityReference;
import net.sf.mmm.util.entity.api.PersistenceEntity;

/**
 * This is the {@link AttributeConverter} to allow that JPA vendors can persist instances of {@link EntityReference} if
 * used in {@link PersistenceEntity entities}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@Converter(autoApply = true)
public class EntityReferenceAttributeConverter implements AttributeConverter<EntityReference<?>, Long> {

  /**
   * The constructor.
   */
  public EntityReferenceAttributeConverter() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long convertToDatabaseColumn(EntityReference<?> attribute) {

    if (attribute == null) {
      return null;
    }
    return attribute.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("rawtypes")
  @Override
  public EntityReference<?> convertToEntityAttribute(Long dbData) {

    if (dbData == null) {
      return null;
    }
    return new EntityReference(dbData);
  }

}
