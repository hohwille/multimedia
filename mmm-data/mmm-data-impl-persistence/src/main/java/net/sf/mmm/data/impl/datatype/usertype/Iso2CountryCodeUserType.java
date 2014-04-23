/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.data.impl.datatype.usertype;

import javax.persistence.MappedSuperclass;

import net.sf.mmm.persistence.impl.hibernate.usertype.StringDatatypeUserType;
import net.sf.mmm.util.datatype.api.address.Iso2CountryCode;
import net.sf.mmm.util.datatype.api.address.PostalCode;

import org.hibernate.annotations.TypeDef;

/**
 * This is the implementation of the {@link org.hibernate.usertype.UserType} to map the datatype
 * {@link PostalCode}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@MappedSuperclass
@TypeDef(defaultForType = Iso2CountryCode.class, typeClass = Iso2CountryCodeUserType.class)
public class Iso2CountryCodeUserType extends StringDatatypeUserType<Iso2CountryCode> {

  /**
   * The constructor.
   */
  public Iso2CountryCodeUserType() {

    super(Iso2CountryCode.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Iso2CountryCode toDatatype(String value) {

    return new Iso2CountryCode(value);
  }

}
