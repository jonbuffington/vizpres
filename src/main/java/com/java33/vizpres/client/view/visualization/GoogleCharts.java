/*
 * Copyright (c) 2011 Jon Buffington. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.java33.vizpres.client.view.visualization;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.*;
import com.java33.vizpres.shared.model.PercentValue;

import java.util.Collections;
import java.util.List;

/**
 * Is a simple demonstration using the Google Chart tools to render a bar chart. Lots
 * of code was spilled in an attempt to wrangle the {@link BarChart}.
 *
 * @author Jon Buffington
 */
public class GoogleCharts extends Composite implements IsDataSync {
  private final FlowPanel root = new FlowPanel();
  private final int width;
  private final int height;

  private CoreChart chart;

  @UiConstructor
  public GoogleCharts(final int width, final int height) {
    this.width = width;
    this.height = height;
    initWidget(root);
    VisualizationUtils.loadVisualizationApi(new Runnable() {
      @Override
      public void run() {
        GoogleCharts.this.chart = makeChart();
        root.add(chart);
      }
    }, CoreChart.PACKAGE);
  }

  protected CoreChart makeChart() {
    return new BarChart(makeDataTable(Collections.<PercentValue>emptyList()), makeOptions(width, height));
  }

  protected static Options makeOptions(final int width, final int height) {
    final String fontName = "'Cabin', sans-serif";
    final Options options = BarChart.createOptions();

    final TextStyle ts = TextStyle.create();
    ts.setFontName(fontName);
    ts.setFontSize(14);
    options.setTitleTextStyle(ts);

    options.setWidth(width);
    options.setHeight(height);
    options.setFontName(fontName);
    options.setFontSize(10);
    options.setBackgroundColor("#eef7de");

    final AxisOptions hAxisOptions = AxisOptions.create();
    hAxisOptions.setMinValue(0);
    hAxisOptions.setMaxValue(100);
    hAxisOptions.setTitle("Percent");
    options.setHAxisOptions(hAxisOptions);

    options.setTitle("Google Charts Example");
    return options;
  }

  protected static DataTable makeDataTable(final List<PercentValue> values) {
    final DataTable data = DataTable.create();
    data.addColumn(AbstractDataTable.ColumnType.STRING, "Index");
    data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Value");
    final int n = values.size();
    if (n > 0) {
      data.addRows(n);
      for (int i = 0; i < n; i += 1) {
        data.setValue(i, 0, Integer.toString(i));
        data.setValue(i, 1, values.get(i).percentage);
      }
    }
    return data;
  }

  @Override
  public void setData(final List<PercentValue> values) {
    chart.draw(makeDataTable(values), makeOptions(width, height));
  }
}
