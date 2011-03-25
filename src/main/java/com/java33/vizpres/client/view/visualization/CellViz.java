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
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.java33.vizpres.client.view.cell.BarCell;
import com.java33.vizpres.shared.model.PercentValue;

import java.util.List;


/**
 * Is a simple demonstration using a CellList to render a bar chart. The
 * CellList uses {@link BarCell} to render the bar.
 *
 * @author Jon Buffington
 */
public class CellViz extends Composite implements IsDataSync {
  interface CellVizUiBinder extends UiBinder<FlowPanel, CellViz> {
  }

  private final static CellVizUiBinder UIBINDER = GWT.create(CellVizUiBinder.class);

  private final String width;
  private final String height;

  @UiField
  CellList<PercentValue> cellList;
  @UiField
  Label                  infoLabel;

  @UiConstructor
  public CellViz(final String width, final String height) {
    this.width = width;
    this.height = height;
    initWidget(UIBINDER.createAndBindUi(this));
  }

  /**
   * Create a {@link CellList} instance using a custom factory method. The custom
   * factory is required because a CellList must be constructed using an
   * instance of its cell type.
   *
   * @return Returns a CellList that uses {@link BarCell} for rendering values.
   */
  @UiFactory
  CellList<PercentValue> makeCellList() {
    // Create a CellList using the custom BarCell class for rendering and interaction handling.
    final CellList<PercentValue> cellList = new CellList<PercentValue>(new BarCell());
    cellList.setWidth(width);
    cellList.setHeight(height);

    final SingleSelectionModel<PercentValue> selectionModel = new SingleSelectionModel<PercentValue>();
    cellList.setSelectionModel(selectionModel);
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        final PercentValue selectedObject = selectionModel.getSelectedObject();
        if (selectedObject != null) {
          infoLabel.setText("You selected: " + Integer.toString(selectedObject.percentage));
        }
      }
    });

    return cellList;
  }

  @Override
  public void setData(final List<PercentValue> values) {
    final StringBuilder sb = new StringBuilder("Drawing ").append(values.size()).append(" values.");
    infoLabel.setText(sb.toString());
    cellList.setRowData(values);
  }
}
