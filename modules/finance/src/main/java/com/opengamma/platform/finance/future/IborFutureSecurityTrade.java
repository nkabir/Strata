/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.platform.finance.future;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableMap;
import com.opengamma.collect.id.StandardId;
import com.opengamma.platform.finance.SecurityLink;
import com.opengamma.platform.finance.Trade;
import com.opengamma.platform.finance.TradeInfo;

/**
 * A trade representing a futures contract based on an IBOR-like index.
 * <p>
 * A trade in an {@link IborFutureSecurity}.
 * For example, the purchase of 2 units of the exchange-traded CME Eurodollar 3 month future.
 */
@BeanDefinition
public final class IborFutureSecurityTrade
    implements Trade, ImmutableBean, Serializable {

  /**
   * The primary standard identifier for the trade.
   * <p>
   * The standard identifier is used to identify the trade.
   * It will typically be an identifier in an external data system.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final StandardId standardId;
  /**
   * The set of additional trade attributes.
   * <p>
   * Most data in the trade is available as bean properties.
   * Attributes are typically used to tag the object with additional information.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final ImmutableMap<String, String> attributes;
  /**
   * The additional trade information, defaulted to an empty instance.
   * <p>
   * This allows additional information to be attached to the trade.
   */
  @PropertyDefinition(overrideGet = true)
  private final TradeInfo tradeInfo;

  /**
   * The link referencing the underlying security.
   */
  @PropertyDefinition(validate = "notNull")
  private final SecurityLink<IborFuture> securityLink;
  /**
   * The multiplier, indicating the number of units of the underlying security in the trade.
   * <p>
   * This will be positive if buying and negative if selling.
   */
  @PropertyDefinition
  private final double multiplier;
  /**
   * Initial price of the product. 
   */
  @PropertyDefinition
  private final double initialPrice;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IborFutureSecurityTrade}.
   * @return the meta-bean, not null
   */
  public static IborFutureSecurityTrade.Meta meta() {
    return IborFutureSecurityTrade.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IborFutureSecurityTrade.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static IborFutureSecurityTrade.Builder builder() {
    return new IborFutureSecurityTrade.Builder();
  }

  private IborFutureSecurityTrade(
      StandardId standardId,
      Map<String, String> attributes,
      TradeInfo tradeInfo,
      SecurityLink<IborFuture> securityLink,
      double multiplier,
      double initialPrice) {
    JodaBeanUtils.notNull(standardId, "standardId");
    JodaBeanUtils.notNull(attributes, "attributes");
    JodaBeanUtils.notNull(securityLink, "securityLink");
    this.standardId = standardId;
    this.attributes = ImmutableMap.copyOf(attributes);
    this.tradeInfo = tradeInfo;
    this.securityLink = securityLink;
    this.multiplier = multiplier;
    this.initialPrice = initialPrice;
  }

  @Override
  public IborFutureSecurityTrade.Meta metaBean() {
    return IborFutureSecurityTrade.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the primary standard identifier for the trade.
   * <p>
   * The standard identifier is used to identify the trade.
   * It will typically be an identifier in an external data system.
   * @return the value of the property, not null
   */
  @Override
  public StandardId getStandardId() {
    return standardId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the set of additional trade attributes.
   * <p>
   * Most data in the trade is available as bean properties.
   * Attributes are typically used to tag the object with additional information.
   * @return the value of the property, not null
   */
  @Override
  public ImmutableMap<String, String> getAttributes() {
    return attributes;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the additional trade information, defaulted to an empty instance.
   * <p>
   * This allows additional information to be attached to the trade.
   * @return the value of the property
   */
  @Override
  public TradeInfo getTradeInfo() {
    return tradeInfo;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the link referencing the underlying security.
   * @return the value of the property, not null
   */
  public SecurityLink<IborFuture> getSecurityLink() {
    return securityLink;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the multiplier, indicating the number of units of the underlying security in the trade.
   * <p>
   * This will be positive if buying and negative if selling.
   * @return the value of the property
   */
  public double getMultiplier() {
    return multiplier;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets initial price of the product.
   * @return the value of the property
   */
  public double getInitialPrice() {
    return initialPrice;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IborFutureSecurityTrade other = (IborFutureSecurityTrade) obj;
      return JodaBeanUtils.equal(getStandardId(), other.getStandardId()) &&
          JodaBeanUtils.equal(getAttributes(), other.getAttributes()) &&
          JodaBeanUtils.equal(getTradeInfo(), other.getTradeInfo()) &&
          JodaBeanUtils.equal(getSecurityLink(), other.getSecurityLink()) &&
          JodaBeanUtils.equal(getMultiplier(), other.getMultiplier()) &&
          JodaBeanUtils.equal(getInitialPrice(), other.getInitialPrice());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getStandardId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getAttributes());
    hash = hash * 31 + JodaBeanUtils.hashCode(getTradeInfo());
    hash = hash * 31 + JodaBeanUtils.hashCode(getSecurityLink());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMultiplier());
    hash = hash * 31 + JodaBeanUtils.hashCode(getInitialPrice());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(224);
    buf.append("IborFutureSecurityTrade{");
    buf.append("standardId").append('=').append(getStandardId()).append(',').append(' ');
    buf.append("attributes").append('=').append(getAttributes()).append(',').append(' ');
    buf.append("tradeInfo").append('=').append(getTradeInfo()).append(',').append(' ');
    buf.append("securityLink").append('=').append(getSecurityLink()).append(',').append(' ');
    buf.append("multiplier").append('=').append(getMultiplier()).append(',').append(' ');
    buf.append("initialPrice").append('=').append(JodaBeanUtils.toString(getInitialPrice()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IborFutureSecurityTrade}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code standardId} property.
     */
    private final MetaProperty<StandardId> standardId = DirectMetaProperty.ofImmutable(
        this, "standardId", IborFutureSecurityTrade.class, StandardId.class);
    /**
     * The meta-property for the {@code attributes} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ImmutableMap<String, String>> attributes = DirectMetaProperty.ofImmutable(
        this, "attributes", IborFutureSecurityTrade.class, (Class) ImmutableMap.class);
    /**
     * The meta-property for the {@code tradeInfo} property.
     */
    private final MetaProperty<TradeInfo> tradeInfo = DirectMetaProperty.ofImmutable(
        this, "tradeInfo", IborFutureSecurityTrade.class, TradeInfo.class);
    /**
     * The meta-property for the {@code securityLink} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<SecurityLink<IborFuture>> securityLink = DirectMetaProperty.ofImmutable(
        this, "securityLink", IborFutureSecurityTrade.class, (Class) SecurityLink.class);
    /**
     * The meta-property for the {@code multiplier} property.
     */
    private final MetaProperty<Double> multiplier = DirectMetaProperty.ofImmutable(
        this, "multiplier", IborFutureSecurityTrade.class, Double.TYPE);
    /**
     * The meta-property for the {@code initialPrice} property.
     */
    private final MetaProperty<Double> initialPrice = DirectMetaProperty.ofImmutable(
        this, "initialPrice", IborFutureSecurityTrade.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "standardId",
        "attributes",
        "tradeInfo",
        "securityLink",
        "multiplier",
        "initialPrice");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1284477768:  // standardId
          return standardId;
        case 405645655:  // attributes
          return attributes;
        case 752580658:  // tradeInfo
          return tradeInfo;
        case 807992154:  // securityLink
          return securityLink;
        case 1265073601:  // multiplier
          return multiplier;
        case -423406491:  // initialPrice
          return initialPrice;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public IborFutureSecurityTrade.Builder builder() {
      return new IborFutureSecurityTrade.Builder();
    }

    @Override
    public Class<? extends IborFutureSecurityTrade> beanType() {
      return IborFutureSecurityTrade.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code standardId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<StandardId> standardId() {
      return standardId;
    }

    /**
     * The meta-property for the {@code attributes} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ImmutableMap<String, String>> attributes() {
      return attributes;
    }

    /**
     * The meta-property for the {@code tradeInfo} property.
     * @return the meta-property, not null
     */
    public MetaProperty<TradeInfo> tradeInfo() {
      return tradeInfo;
    }

    /**
     * The meta-property for the {@code securityLink} property.
     * @return the meta-property, not null
     */
    public MetaProperty<SecurityLink<IborFuture>> securityLink() {
      return securityLink;
    }

    /**
     * The meta-property for the {@code multiplier} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> multiplier() {
      return multiplier;
    }

    /**
     * The meta-property for the {@code initialPrice} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> initialPrice() {
      return initialPrice;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1284477768:  // standardId
          return ((IborFutureSecurityTrade) bean).getStandardId();
        case 405645655:  // attributes
          return ((IborFutureSecurityTrade) bean).getAttributes();
        case 752580658:  // tradeInfo
          return ((IborFutureSecurityTrade) bean).getTradeInfo();
        case 807992154:  // securityLink
          return ((IborFutureSecurityTrade) bean).getSecurityLink();
        case 1265073601:  // multiplier
          return ((IborFutureSecurityTrade) bean).getMultiplier();
        case -423406491:  // initialPrice
          return ((IborFutureSecurityTrade) bean).getInitialPrice();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code IborFutureSecurityTrade}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<IborFutureSecurityTrade> {

    private StandardId standardId;
    private Map<String, String> attributes = ImmutableMap.of();
    private TradeInfo tradeInfo;
    private SecurityLink<IborFuture> securityLink;
    private double multiplier;
    private double initialPrice;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(IborFutureSecurityTrade beanToCopy) {
      this.standardId = beanToCopy.getStandardId();
      this.attributes = beanToCopy.getAttributes();
      this.tradeInfo = beanToCopy.getTradeInfo();
      this.securityLink = beanToCopy.getSecurityLink();
      this.multiplier = beanToCopy.getMultiplier();
      this.initialPrice = beanToCopy.getInitialPrice();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1284477768:  // standardId
          return standardId;
        case 405645655:  // attributes
          return attributes;
        case 752580658:  // tradeInfo
          return tradeInfo;
        case 807992154:  // securityLink
          return securityLink;
        case 1265073601:  // multiplier
          return multiplier;
        case -423406491:  // initialPrice
          return initialPrice;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1284477768:  // standardId
          this.standardId = (StandardId) newValue;
          break;
        case 405645655:  // attributes
          this.attributes = (Map<String, String>) newValue;
          break;
        case 752580658:  // tradeInfo
          this.tradeInfo = (TradeInfo) newValue;
          break;
        case 807992154:  // securityLink
          this.securityLink = (SecurityLink<IborFuture>) newValue;
          break;
        case 1265073601:  // multiplier
          this.multiplier = (Double) newValue;
          break;
        case -423406491:  // initialPrice
          this.initialPrice = (Double) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public IborFutureSecurityTrade build() {
      return new IborFutureSecurityTrade(
          standardId,
          attributes,
          tradeInfo,
          securityLink,
          multiplier,
          initialPrice);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code standardId} property in the builder.
     * @param standardId  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder standardId(StandardId standardId) {
      JodaBeanUtils.notNull(standardId, "standardId");
      this.standardId = standardId;
      return this;
    }

    /**
     * Sets the {@code attributes} property in the builder.
     * @param attributes  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder attributes(Map<String, String> attributes) {
      JodaBeanUtils.notNull(attributes, "attributes");
      this.attributes = attributes;
      return this;
    }

    /**
     * Sets the {@code tradeInfo} property in the builder.
     * @param tradeInfo  the new value
     * @return this, for chaining, not null
     */
    public Builder tradeInfo(TradeInfo tradeInfo) {
      this.tradeInfo = tradeInfo;
      return this;
    }

    /**
     * Sets the {@code securityLink} property in the builder.
     * @param securityLink  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder securityLink(SecurityLink<IborFuture> securityLink) {
      JodaBeanUtils.notNull(securityLink, "securityLink");
      this.securityLink = securityLink;
      return this;
    }

    /**
     * Sets the {@code multiplier} property in the builder.
     * @param multiplier  the new value
     * @return this, for chaining, not null
     */
    public Builder multiplier(double multiplier) {
      this.multiplier = multiplier;
      return this;
    }

    /**
     * Sets the {@code initialPrice} property in the builder.
     * @param initialPrice  the new value
     * @return this, for chaining, not null
     */
    public Builder initialPrice(double initialPrice) {
      this.initialPrice = initialPrice;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(224);
      buf.append("IborFutureSecurityTrade.Builder{");
      buf.append("standardId").append('=').append(JodaBeanUtils.toString(standardId)).append(',').append(' ');
      buf.append("attributes").append('=').append(JodaBeanUtils.toString(attributes)).append(',').append(' ');
      buf.append("tradeInfo").append('=').append(JodaBeanUtils.toString(tradeInfo)).append(',').append(' ');
      buf.append("securityLink").append('=').append(JodaBeanUtils.toString(securityLink)).append(',').append(' ');
      buf.append("multiplier").append('=').append(JodaBeanUtils.toString(multiplier)).append(',').append(' ');
      buf.append("initialPrice").append('=').append(JodaBeanUtils.toString(initialPrice));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
