/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * <p>
 * Please see distribution for license.
 */
package com.opengamma.strata.examples.finance;

import com.google.common.collect.ImmutableList;
import com.opengamma.strata.basics.BuySell;
import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.basics.currency.CurrencyAmount;
import com.opengamma.strata.basics.currency.FxRate;
import com.opengamma.strata.basics.market.FxRateId;
import com.opengamma.strata.collect.id.StandardId;
import com.opengamma.strata.engine.CalculationEngine;
import com.opengamma.strata.engine.CalculationRules;
import com.opengamma.strata.engine.Column;
import com.opengamma.strata.engine.calculations.Results;
import com.opengamma.strata.engine.config.Measure;
import com.opengamma.strata.engine.config.ReportingRules;
import com.opengamma.strata.engine.marketdata.BaseMarketData;
import com.opengamma.strata.examples.data.ExampleData;
import com.opengamma.strata.examples.engine.ExampleEngine;
import com.opengamma.strata.examples.marketdata.ExampleMarketData;
import com.opengamma.strata.finance.Trade;
import com.opengamma.strata.finance.credit.common.RedCode;
import com.opengamma.strata.finance.credit.fee.SinglePayment;
import com.opengamma.strata.finance.credit.general.reference.SeniorityLevel;
import com.opengamma.strata.finance.credit.protection.RestructuringClause;
import com.opengamma.strata.finance.credit.type.StandardCdsConventions;
import com.opengamma.strata.finance.credit.type.StandardCdsTemplate;
import com.opengamma.strata.function.OpenGammaPricingRules;
import com.opengamma.strata.report.ReportCalculationResults;
import com.opengamma.strata.report.trade.TradeReport;
import com.opengamma.strata.report.trade.TradeReportTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class CdsPricingExample {

  public static void main(String[] args) {
    // the trades that will have measures calculated
    List<Trade> trades = ImmutableList.of(
        singleName()
    );

    // the columns, specifying the measures to be calculated
    List<Column> columns = ImmutableList.of(
        Column.of(Measure.TRADE_INFO),
        Column.of(Measure.PRESENT_VALUE)
    );

    // the complete set of rules for calculating measures
    CalculationRules rules = CalculationRules.builder()
        .pricingRules(OpenGammaPricingRules.standard())
        .marketDataRules(ExampleMarketData.rules())
        .reportingRules(ReportingRules.fixedCurrency(Currency.USD))
        .build();

    // Use an empty snapshot of market data, indicating only the valuation date.
    // The engine will attempt to source the data for us, which the example engine is
    // configured to load from JSON resources. We could alternatively populate the snapshot
    // with some or all of the required market data here.
    LocalDate valuationDate = LocalDate.of(2014, 10, 16);
    // TODO The rate is for automatic conversion to the reporting currency. Where should it come from?
    BaseMarketData baseMarketData = BaseMarketData.builder(valuationDate)
        .addValue(FxRateId.of(Currency.GBP, Currency.USD), FxRate.of(Currency.GBP, Currency.USD, 1.61))
        .build();

    // create the engine and calculate the results
    CalculationEngine engine = ExampleEngine.create();
    Results results = engine.calculate(trades, columns, rules, baseMarketData);

    assert (((CurrencyAmount) results.get(0, 1).getValue()).getAmount() == 3_694_048.58D);

    // use the report runner to transform the engine results into a trade report
    ReportCalculationResults calculationResults = ReportCalculationResults.of(
        valuationDate,
        columns,
        results);

    TradeReportTemplate reportTemplate = ExampleData.loadTradeReportTemplate("cds-report-template");
    TradeReport tradeReport = TradeReport.of(calculationResults, reportTemplate);
    tradeReport.writeAsciiTable(System.out);

  }

  //-----------------------------------------------------------------------  
  // create a vanilla fixed vs libor 3m swap
  private static Trade singleName() {
    return StandardCdsTemplate
        .of(StandardCdsConventions.northAmericanUsd())
        .toSingleNameTrade(
            StandardId.of("tradeid", "62726762"),
            LocalDate.of(2014, 10, 16),
            Period.ofYears(5),
            BuySell.BUY,
            100_000_000D,
            0.0100,
            RedCode.of("AH98A7"),
            "Ford Motor Company",
            SeniorityLevel.SeniorUnSec,
            RestructuringClause.XR,
            3_694_000D,
            LocalDate.of(2014, 10, 21)
        );
  }

}