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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.hydro4ge.raphaelgwt.client.Raphael;
import com.java33.vizpres.shared.model.PercentValue;

import java.util.List;

/**
 * Is a demonstration of using Raphael to render SVG/VML to the browser.
 *
 * @author Jon Buffington
 */
public class RaphaelCanvas extends Raphael implements IsDataSync {
  private static final int ROW_HEIGHT  = 30;
  private static final int BAR_HEIGHT  = 28;
  private static final int TEXT_HEIGHT = 12;
  private static final int TEXT_OFFSET = (TEXT_HEIGHT / 2) + ((BAR_HEIGHT - TEXT_HEIGHT) / 2);

  private final RaphaelViz parent;
  private final int        width;

  public RaphaelCanvas(final int width, final int height, final RaphaelViz parent) {
    super(width, height);
    this.width = width;
    this.parent = parent;
  }

  @Override
  public void setData(final List<PercentValue> values) {
    clear();

    for (int i = 0; i < values.size(); i += 1) {
      final int percent = values.get(i).percentage;
      final String label = Integer.toString(percent);
      final double barWidth = (double) (percent * width) / 100.0;
      final int rowY = i * ROW_HEIGHT;
      final Rect rect = new Rect(0, rowY, 0, BAR_HEIGHT);
      rect.attr("stroke", "none");
      rect.attr("fill", "#ff6600");
      rect.attr("title", label);

      // Is the click handler for the bar.
      rect.addDomHandler(new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
          parent.infoLabel.setText("You selected: " + rect.attrAsString("title"));
        }
      }, ClickEvent.getType());

      // Is the interpolation animation.
      final JSONObject bw = new JSONObject();
      bw.put("width", new JSONNumber(barWidth));
      rect.animate(bw, 1000);

      // Is the text label for a bar.
      final Text text = new Text(10, rowY + TEXT_OFFSET, label);
      text.attr("font", "10px 'Cabin', sans-serif");
      text.attr("fill", "white");
    }
  }
}
