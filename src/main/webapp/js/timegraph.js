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

$(function () {
    // Grab the data
    var data = [],
        axisx = [],
        axisy = [],
        table = $("#for-chart");    
        
    // Grab all the table data    
    var maxVal = -1;
    $("tbody td", table).each(function (i) {
        v = parseFloat($(this).text(), 10);
        if (!v) v = .1;
        data.push(v);                    
        if (v > maxVal) maxVal = v;
    });
    table.hide();
    $("tbody th", table).each(function () {
        axisy.push($(this).text());
    });
    $("tfoot th", table).each(function () {
        axisx.push($(this).text());
    });
    // Draw
    var width = 1000,
        height = 300, 
        leftgutter = 100,
        bottomgutter = 20,
        r = Raphael("chart", width, height),
        txt = {"font": '12px Arvo, Helvetica, Arial', stroke: "none", fill: "#fff"},
        X = (width - leftgutter) / axisx.length,
        Y = (height - bottomgutter) / axisy.length,
        color = $("#chart").css("color");
        max = Math.round(X/2) - 1;
                                                                   
    // background
    r.rect(0, 0, width, height, 5).attr({fill: "#32869F", opacity: 0.2, stroke: "none"});
    
                       
    // x axis labels
    for (var i = 0, ii = axisx.length; i < ii; i++) {
        r.text(leftgutter + X * (i + .5), height-6, axisx[i]).attr(txt);
    }                
                    
    // y axis labels
    for (var i = 0, ii = axisy.length; i < ii; i++) {
        r.text(100, Y * (i + .5), axisy[i]).attr(txt).attr('text-anchor', 'end');
    }
                             
    // Draw a growth curve between points
    function growthCurve(x1,y1,x2,y2,x3,y3,x4,y4,clr1,clr2) {
      // function curveit()   
      var path = [["M", x1, y1], 
                  ["C", x1*.25+x2*.75, y1*.5+y2*.5, x1*.75+x2*.25, y1*.5+y2*.5, x2, y2],
                  ["L", x3, y3],
                  ["C", x3*.25+x4*.75, y3*.5+y4*.5, x3*.75+x4*.25, y3*.5+y4*.5, x4, y4],
                  ["Z"]
                  ]
      r.path(path).attr({stroke: "none", fill: "0.0-"+clr1+":5-"+clr2+":95", opacity: 1});
    }    
               
    // Draw a box to indicate spending in a given year
    function drawBox(dx, dy, R, value, color) {
        var dt = r.rect(dx, dy, R, R).attr({stroke: "none", fill: color});
        // if (R < 6) {
        //     var bg = r.rect(dx, dy, 6, 6).attr({stroke: "none", fill: "#000", opacity: .4}).hide();
        // }
        // var lbl = r.text(dx, dy, data[o])
        //         .attr({"font": '10px Fontin-Sans, Arial', stroke: "none", fill: "#fff"}).hide(); 
                
        // var dot = r.circle(dx + R, dy, max).attr({stroke: "none", fill: "#000", opacity: 0});
        // dot[0].onmouseover = function () {
        //     if (bg) {
        //         bg.show();
        //     } else {
        //         var clr = Raphael.rgb2hsb(color);
        //         clr.b = .5;
        //         dt.attr("fill", Raphael.hsb2rgb(clr).hex);
        //     }
        //     lbl.show();
        // };
        // dot[0].onmouseout = function () {
        //     if (bg) {
        //         bg.hide();
        //     } else {
        //         dt.attr("fill", color);
        //     }
        //     lbl.hide();
        // };
    }
    
    var o = 0;                    
        
    // For each row
    for (var i = 0, ii = axisy.length; i < ii; i++) { 
        prevDx = null;
        prevDy = null;     
        prevR = null;       
        prevClr = null;   
        prevData = null;          
                   
        // For each column
        for (var j = 0, jj = axisx.length; j < jj; j++) { 
            // Pct of max val       
            var thisData = data[o];
            var pct = thisData / maxVal;
            pct = Math.sqrt(pct);  
            var R = pct * max;
            var chg = (thisData - prevData) / thisData;
            if (chg > .3) chg = .3;
            if (chg < -.3) chg = -.3;
            chg = (chg + .3) / .6
//            var R = data[o] && Math.min(Math.round(Math.sqrt(data[o] / Math.PI) * 4), max*2);      
            var dx = leftgutter + X * (j + .5);
            var dy = Y * (i + .5);
            var color = "hsb(" + [chg, 1, .75] + ")";   
            if (R) {      
                if (prevDx) {
                  growthCurve(prevDx +  prevR-1, prevDy,
                              dx+1, dy,
                              dx+1, dy + R,
                              prevDx + prevR-1, prevDy + prevR,
                              prevClr, color);             
                 } 
                drawBox(dx, dy, R, data[o], color);
            }  
            prevDx = dx;   
            prevDy = dy;
            prevR = R;                      
            prevClr = color;  
            prevData = thisData;
            o++;
        }
    }
});
