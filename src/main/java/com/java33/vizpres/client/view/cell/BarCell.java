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

package com.java33.vizpres.client.view.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.java33.vizpres.shared.model.PercentValue;

/**
 * Is a custom cell that uses an horizontal bar to display a percentage
 * using a filled DIV element.
 *
 * @author Jon Buffington
 */
public class BarCell extends AbstractCell<PercentValue> {
  /**
   * {@inheritDoc}
   */
  @Override
  public void render(final Context context, final PercentValue value, final SafeHtmlBuilder sb) {
    final int percentage = value.percentage;
    if (percentage >= 0 && percentage <= 100) {
      final StringBuilder tags = new StringBuilder("<div class='java33-barCell' style='width: ")
          .append(percentage)
          .append("%;'>")
          .append(percentage)
          .append("</div>");
      sb.appendHtmlConstant(tags.toString());
    }
    else {
      sb.appendHtmlConstant("<p><strong>Value is out-of-range.</strong></p>");
    }
  }
}
