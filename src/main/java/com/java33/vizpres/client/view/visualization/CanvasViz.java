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

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.java33.vizpres.shared.model.PercentValue;

import java.util.List;

/**
 * Is a simple demonstration using the Canvas 2D context to render a bar chart.
 *
 * @author Jon Buffington
 */
public class CanvasViz extends Composite implements IsDataSync {
  private static final int ROW_HEIGHT  = 30;
  private static final int BAR_HEIGHT  = 28;
  private static final int TEXT_HEIGHT = 14;

  private final FlowPanel root;
  private final int       width;
  private final int       height;
  private       Canvas    canvas;

  @UiConstructor
  public CanvasViz(final int width, final int height) {
    root = new FlowPanel();
    this.width = width;
    this.height = height;
    root.add(new Label("Technique: Use a Canvas element to draw the chart."));
    initWidget(root);
  }

  /**
   * If the browser supports the canvas tag and 2D drawing API, add a GWT
   * {@link Canvas} to the root panel.
   */
  @Override
  protected void onLoad() {
    super.onLoad();

    canvas = Canvas.createIfSupported();
    if (canvas != null) {
      // Set-up a 1-to-1 pixel to coordinate space.
      canvas.setWidth(Integer.toString(width) + "px");
      canvas.setCoordinateSpaceWidth(width);
      canvas.setHeight(Integer.toString(height) + "px");
      canvas.setCoordinateSpaceHeight(height);
      root.add(canvas);
    }
  }

  /**
   * Renders the percent data values using the Canvas 2D API as horizontal
   * bars with a text label.
   *
   * @param values Is the list of values to render.
   */
  @Override
  public void setData(final List<PercentValue> values) {
    if (canvas != null) {
      final Context2d ctx = canvas.getContext2d();
      ctx.clearRect(0, 0, width, height);
      ctx.setTextBaseline(Context2d.TextBaseline.TOP);
      if (values != null) {
        final int n = values.size();
        for (int i = 0; i < n; i += 1) {
          final int rowY = i * ROW_HEIGHT;
          final int percent = values.get(i).percentage;
          final double barWidth = (double) (percent * width) / 100.0;
          ctx.setFillStyle("#ff6600");
          ctx.fillRect(0, rowY, barWidth, BAR_HEIGHT);
          ctx.setFillStyle("#ffffff");
          ctx.fillText(Integer.toString(percent), 4, rowY + TEXT_HEIGHT / 2);
        }
      }
    }
  }
}
