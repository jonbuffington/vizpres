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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.java33.vizpres.shared.model.PercentValue;

import java.util.List;

/**
 * Is a demonstration of using Raphael to render SVG/VML to the browser.
 *
 * @author Jon Buffington
 */
public class RaphaelViz extends Composite implements IsDataSync {
  interface RaphaelVizUiBinder extends UiBinder<FlowPanel, RaphaelViz> {
  }

  private final static RaphaelVizUiBinder UIBINDER = GWT.create(RaphaelVizUiBinder.class);

  @UiField(provided = true)
  RaphaelCanvas canvas;
  @UiField
  Label         infoLabel;

  @UiConstructor
  public RaphaelViz(final int width, final int height) {
    canvas = new RaphaelCanvas(width, height, this);
    initWidget(UIBINDER.createAndBindUi(this));
  }

  @Override
  public void setData(final List<PercentValue> values) {
    final StringBuilder sb = new StringBuilder("Drawing ").append(values.size()).append(" values.");
    infoLabel.setText(sb.toString());
    canvas.setData(values);
  }

}
