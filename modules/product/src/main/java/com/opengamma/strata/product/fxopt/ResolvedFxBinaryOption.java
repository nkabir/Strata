/**
 * Copyright (C) 2017 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.product.fxopt;

import static com.opengamma.strata.collect.ArgChecker.inOrderOrEqual;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.opengamma.strata.basics.currency.AdjustablePayment;
import com.opengamma.strata.basics.currency.Payment;
import com.opengamma.strata.basics.index.FxIndex;
import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableValidator;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.ReferenceData;
import com.opengamma.strata.basics.currency.CurrencyPair;
import com.opengamma.strata.product.ResolvedProduct;
import com.opengamma.strata.product.common.LongShort;
import com.opengamma.strata.product.common.PutCall;

/**
 * A binary FX option, resolved for pricing.
 * <p>
 * This is the resolved form of {@link FxBinaryOption} and is an input to the pricers.
 * Applications will typically create a {@code ResolvedFxBinaryOption} from a {@code FxBinaryOption}
 * using {@link FxBinaryOption#resolve(ReferenceData)}.
 * <p>
 * A {@code ResolvedFxBinaryOption} is bound to data that changes over time, such as holiday calendars.
 * If the data changes, such as the addition of a new holiday, the resolved form will not be updated.
 * Care must be taken when placing the resolved form in a cache or persistence layer.
 */
@BeanDefinition
public final class ResolvedFxBinaryOption
        implements ResolvedProduct, ImmutableBean, Serializable {

    /**
     * Whether the option is long or short.
     * <p>
     * At expiry, the long party will have the option to enter in this transaction;
     * the short party will, at the option of the long party, potentially enter into the inverse transaction.
     */
    @PropertyDefinition(validate = "notNull")
    private final LongShort longShort;
    /**
     * The expiry date-time of the option.
     * <p>
     * The option is European, and can only be exercised on the expiry date.
     */
    @PropertyDefinition(validate = "notNull")
    private final ZonedDateTime expiry;
    /**
     * The reference FX Index of the option.
     * <p>
     * The reference FX Index is used to determine whether or not a payment event has occurred at the expiry
     * of the option.
     */
    @PropertyDefinition(validate = "notNull")
    private final FxIndex underlying;
    /**
     * The amount and currency of the option payment.
     * <p>
     * The payment amount that will be made to the long party in the specified currency, if at expiry, a payment
     * event has occurred.
     */
    @PropertyDefinition(validate = "notNull")
    private final AdjustablePayment paymentCurrencyAmount;
    /**
     * Whether the the option is a put or a call.
     * <p>
     * A call on the FX pair is not necessarily the same as a put on the inverse pair - unlike for vanilla
     * options - since the payment currencies can be different in each case.
     */
    @PropertyDefinition(validate = "notNull")
    private final PutCall putCall;
    /**
     * The strike FX rate for the binary option.
     * <p>
     * At expiry, the strike rate of the option will be compared to the reference FX Index to determine whether or not
     * a payment event has occurred.
     */
    @PropertyDefinition(validate = "notNull")
    private final double strike;

    //-------------------------------------------------------------------------
    @ImmutableValidator
    private void validate() {
        inOrderOrEqual(expiry.toLocalDate(), paymentCurrencyAmount.getDate().getUnadjusted(), "expiry.date", "underlying.paymentDate");
    }

    //-------------------------------------------------------------------------
    /**
     * Gets currency pair of the base currency and counter currency.
     * <p>
     * This currency pair is conventional, thus indifferent to the direction of FX.
     *
     * @return the currency pair
     */
    public CurrencyPair getCurrencyPair() {
      return underlying.getCurrencyPair();
    }

    /**
     * Gets the expiry date of the option.
     *
     * @return the expiry date
     */
    public LocalDate getExpiryDate() {
        return expiry.toLocalDate();
    }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvedFxBinaryOption}.
   * @return the meta-bean, not null
   */
  public static ResolvedFxBinaryOption.Meta meta() {
    return ResolvedFxBinaryOption.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvedFxBinaryOption.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ResolvedFxBinaryOption.Builder builder() {
    return new ResolvedFxBinaryOption.Builder();
  }

  private ResolvedFxBinaryOption(
      LongShort longShort,
      ZonedDateTime expiry,
      FxIndex underlying,
      AdjustablePayment paymentCurrencyAmount,
      PutCall putCall,
      double strike) {
    JodaBeanUtils.notNull(longShort, "longShort");
    JodaBeanUtils.notNull(expiry, "expiry");
    JodaBeanUtils.notNull(underlying, "underlying");
    JodaBeanUtils.notNull(paymentCurrencyAmount, "paymentCurrencyAmount");
    JodaBeanUtils.notNull(putCall, "putCall");
    JodaBeanUtils.notNull(strike, "strike");
    this.longShort = longShort;
    this.expiry = expiry;
    this.underlying = underlying;
    this.paymentCurrencyAmount = paymentCurrencyAmount;
    this.putCall = putCall;
    this.strike = strike;
    validate();
  }

  @Override
  public ResolvedFxBinaryOption.Meta metaBean() {
    return ResolvedFxBinaryOption.Meta.INSTANCE;
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
   * Gets whether the option is long or short.
   * <p>
   * At expiry, the long party will have the option to enter in this transaction;
   * the short party will, at the option of the long party, potentially enter into the inverse transaction.
   * @return the value of the property, not null
   */
  public LongShort getLongShort() {
    return longShort;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the expiry date-time of the option.
   * <p>
   * The option is European, and can only be exercised on the expiry date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getExpiry() {
    return expiry;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reference FX Index of the option.
   * <p>
   * The reference FX Index is used to determine whether or not a payment event has occurred at the expiry
   * of the option.
   * @return the value of the property, not null
   */
  public FxIndex getUnderlying() {
    return underlying;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the amount and currency of the option payment.
   * <p>
   * The payment amount that will be made to the long party in the specified currency, if at expiry, a payment
   * event has occurred.
   * @return the value of the property, not null
   */
  public AdjustablePayment getPaymentCurrencyAmount() {
    return paymentCurrencyAmount;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether the the option is a put or a call.
   * <p>
   * A call on the FX pair is not necessarily the same as a put on the inverse pair - unlike for vanilla
   * options - since the payment currencies can be different in each case.
   * @return the value of the property, not null
   */
  public PutCall getPutCall() {
    return putCall;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the strike FX rate for the binary option.
   * <p>
   * At expiry, the strike rate of the option will be compared to the reference FX Index to determine whether or not
   * a payment event has occurred.
   * @return the value of the property, not null
   */
  public double getStrike() {
    return strike;
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
      ResolvedFxBinaryOption other = (ResolvedFxBinaryOption) obj;
      return JodaBeanUtils.equal(longShort, other.longShort) &&
          JodaBeanUtils.equal(expiry, other.expiry) &&
          JodaBeanUtils.equal(underlying, other.underlying) &&
          JodaBeanUtils.equal(paymentCurrencyAmount, other.paymentCurrencyAmount) &&
          JodaBeanUtils.equal(putCall, other.putCall) &&
          JodaBeanUtils.equal(strike, other.strike);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(longShort);
    hash = hash * 31 + JodaBeanUtils.hashCode(expiry);
    hash = hash * 31 + JodaBeanUtils.hashCode(underlying);
    hash = hash * 31 + JodaBeanUtils.hashCode(paymentCurrencyAmount);
    hash = hash * 31 + JodaBeanUtils.hashCode(putCall);
    hash = hash * 31 + JodaBeanUtils.hashCode(strike);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(224);
    buf.append("ResolvedFxBinaryOption{");
    buf.append("longShort").append('=').append(longShort).append(',').append(' ');
    buf.append("expiry").append('=').append(expiry).append(',').append(' ');
    buf.append("underlying").append('=').append(underlying).append(',').append(' ');
    buf.append("paymentCurrencyAmount").append('=').append(paymentCurrencyAmount).append(',').append(' ');
    buf.append("putCall").append('=').append(putCall).append(',').append(' ');
    buf.append("strike").append('=').append(JodaBeanUtils.toString(strike));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedFxBinaryOption}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code longShort} property.
     */
    private final MetaProperty<LongShort> longShort = DirectMetaProperty.ofImmutable(
        this, "longShort", ResolvedFxBinaryOption.class, LongShort.class);
    /**
     * The meta-property for the {@code expiry} property.
     */
    private final MetaProperty<ZonedDateTime> expiry = DirectMetaProperty.ofImmutable(
        this, "expiry", ResolvedFxBinaryOption.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code underlying} property.
     */
    private final MetaProperty<FxIndex> underlying = DirectMetaProperty.ofImmutable(
        this, "underlying", ResolvedFxBinaryOption.class, FxIndex.class);
    /**
     * The meta-property for the {@code paymentCurrencyAmount} property.
     */
    private final MetaProperty<AdjustablePayment> paymentCurrencyAmount = DirectMetaProperty.ofImmutable(
        this, "paymentCurrencyAmount", ResolvedFxBinaryOption.class, AdjustablePayment.class);
    /**
     * The meta-property for the {@code putCall} property.
     */
    private final MetaProperty<PutCall> putCall = DirectMetaProperty.ofImmutable(
        this, "putCall", ResolvedFxBinaryOption.class, PutCall.class);
    /**
     * The meta-property for the {@code strike} property.
     */
    private final MetaProperty<Double> strike = DirectMetaProperty.ofImmutable(
        this, "strike", ResolvedFxBinaryOption.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "longShort",
        "expiry",
        "underlying",
        "paymentCurrencyAmount",
        "putCall",
        "strike");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 116685664:  // longShort
          return longShort;
        case -1289159373:  // expiry
          return expiry;
        case -1770633379:  // underlying
          return underlying;
        case 944314223:  // paymentCurrencyAmount
          return paymentCurrencyAmount;
        case -219971059:  // putCall
          return putCall;
        case -891985998:  // strike
          return strike;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ResolvedFxBinaryOption.Builder builder() {
      return new ResolvedFxBinaryOption.Builder();
    }

    @Override
    public Class<? extends ResolvedFxBinaryOption> beanType() {
      return ResolvedFxBinaryOption.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code longShort} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LongShort> longShort() {
      return longShort;
    }

    /**
     * The meta-property for the {@code expiry} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ZonedDateTime> expiry() {
      return expiry;
    }

    /**
     * The meta-property for the {@code underlying} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FxIndex> underlying() {
      return underlying;
    }

    /**
     * The meta-property for the {@code paymentCurrencyAmount} property.
     * @return the meta-property, not null
     */
    public MetaProperty<AdjustablePayment> paymentCurrencyAmount() {
      return paymentCurrencyAmount;
    }

    /**
     * The meta-property for the {@code putCall} property.
     * @return the meta-property, not null
     */
    public MetaProperty<PutCall> putCall() {
      return putCall;
    }

    /**
     * The meta-property for the {@code strike} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> strike() {
      return strike;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 116685664:  // longShort
          return ((ResolvedFxBinaryOption) bean).getLongShort();
        case -1289159373:  // expiry
          return ((ResolvedFxBinaryOption) bean).getExpiry();
        case -1770633379:  // underlying
          return ((ResolvedFxBinaryOption) bean).getUnderlying();
        case 944314223:  // paymentCurrencyAmount
          return ((ResolvedFxBinaryOption) bean).getPaymentCurrencyAmount();
        case -219971059:  // putCall
          return ((ResolvedFxBinaryOption) bean).getPutCall();
        case -891985998:  // strike
          return ((ResolvedFxBinaryOption) bean).getStrike();
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
   * The bean-builder for {@code ResolvedFxBinaryOption}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<ResolvedFxBinaryOption> {

    private LongShort longShort;
    private ZonedDateTime expiry;
    private FxIndex underlying;
    private AdjustablePayment paymentCurrencyAmount;
    private PutCall putCall;
    private double strike;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ResolvedFxBinaryOption beanToCopy) {
      this.longShort = beanToCopy.getLongShort();
      this.expiry = beanToCopy.getExpiry();
      this.underlying = beanToCopy.getUnderlying();
      this.paymentCurrencyAmount = beanToCopy.getPaymentCurrencyAmount();
      this.putCall = beanToCopy.getPutCall();
      this.strike = beanToCopy.getStrike();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 116685664:  // longShort
          return longShort;
        case -1289159373:  // expiry
          return expiry;
        case -1770633379:  // underlying
          return underlying;
        case 944314223:  // paymentCurrencyAmount
          return paymentCurrencyAmount;
        case -219971059:  // putCall
          return putCall;
        case -891985998:  // strike
          return strike;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 116685664:  // longShort
          this.longShort = (LongShort) newValue;
          break;
        case -1289159373:  // expiry
          this.expiry = (ZonedDateTime) newValue;
          break;
        case -1770633379:  // underlying
          this.underlying = (FxIndex) newValue;
          break;
        case 944314223:  // paymentCurrencyAmount
          this.paymentCurrencyAmount = (AdjustablePayment) newValue;
          break;
        case -219971059:  // putCall
          this.putCall = (PutCall) newValue;
          break;
        case -891985998:  // strike
          this.strike = (Double) newValue;
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
    public ResolvedFxBinaryOption build() {
      return new ResolvedFxBinaryOption(
          longShort,
          expiry,
          underlying,
          paymentCurrencyAmount,
          putCall,
          strike);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets whether the option is long or short.
     * <p>
     * At expiry, the long party will have the option to enter in this transaction;
     * the short party will, at the option of the long party, potentially enter into the inverse transaction.
     * @param longShort  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder longShort(LongShort longShort) {
      JodaBeanUtils.notNull(longShort, "longShort");
      this.longShort = longShort;
      return this;
    }

    /**
     * Sets the expiry date-time of the option.
     * <p>
     * The option is European, and can only be exercised on the expiry date.
     * @param expiry  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder expiry(ZonedDateTime expiry) {
      JodaBeanUtils.notNull(expiry, "expiry");
      this.expiry = expiry;
      return this;
    }

    /**
     * Sets the reference FX Index of the option.
     * <p>
     * The reference FX Index is used to determine whether or not a payment event has occurred at the expiry
     * of the option.
     * @param underlying  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder underlying(FxIndex underlying) {
      JodaBeanUtils.notNull(underlying, "underlying");
      this.underlying = underlying;
      return this;
    }

    /**
     * Sets the amount and currency of the option payment.
     * <p>
     * The payment amount that will be made to the long party in the specified currency, if at expiry, a payment
     * event has occurred.
     * @param paymentCurrencyAmount  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder paymentCurrencyAmount(AdjustablePayment paymentCurrencyAmount) {
      JodaBeanUtils.notNull(paymentCurrencyAmount, "paymentCurrencyAmount");
      this.paymentCurrencyAmount = paymentCurrencyAmount;
      return this;
    }

    /**
     * Sets whether the the option is a put or a call.
     * <p>
     * A call on the FX pair is not necessarily the same as a put on the inverse pair - unlike for vanilla
     * options - since the payment currencies can be different in each case.
     * @param putCall  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder putCall(PutCall putCall) {
      JodaBeanUtils.notNull(putCall, "putCall");
      this.putCall = putCall;
      return this;
    }

    /**
     * Sets the strike FX rate for the binary option.
     * <p>
     * At expiry, the strike rate of the option will be compared to the reference FX Index to determine whether or not
     * a payment event has occurred.
     * @param strike  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder strike(double strike) {
      JodaBeanUtils.notNull(strike, "strike");
      this.strike = strike;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(224);
      buf.append("ResolvedFxBinaryOption.Builder{");
      buf.append("longShort").append('=').append(JodaBeanUtils.toString(longShort)).append(',').append(' ');
      buf.append("expiry").append('=').append(JodaBeanUtils.toString(expiry)).append(',').append(' ');
      buf.append("underlying").append('=').append(JodaBeanUtils.toString(underlying)).append(',').append(' ');
      buf.append("paymentCurrencyAmount").append('=').append(JodaBeanUtils.toString(paymentCurrencyAmount)).append(',').append(' ');
      buf.append("putCall").append('=').append(JodaBeanUtils.toString(putCall)).append(',').append(' ');
      buf.append("strike").append('=').append(JodaBeanUtils.toString(strike));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
