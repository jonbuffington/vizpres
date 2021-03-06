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

var show = (function() {
  var move = function(e) {
    var DIRECTIONS = {
      37: -1,     // >
      38: -1,     // up
      39: 1,      // <
      40: 1,      // down
      32: 1,      // _
      13: 1,      // return
      27: 'home', // esc
      left: -1,
      right: 1
    };

    var dir = DIRECTIONS[e.which || e];
    if (dir) {
      if (dir === 'home') {
        e.preventDefault();
        e.stopPropagation();
        location.href = '/';
      }
      else {
        $('#instructions').slideUp(100);
        show.setIndex(show.index() + dir);
      }
    }
  };

  var clickMove = function(e) {
    if (e.pageX < ($(window).width() / 2)) {
      move('left');
    }
    else {
      move('right');
    }
  };

  var dimensions = function() {
    return {
      width: $(window).width(),
      height: $(window).height()
    };
  };

  var setSlideDimensions = function() {
    var d = dimensions();
    $('#slides').height(d.height).width(d.width);
    show.slides().height(d.height).width(d.width);
  };

  var showCurrentSlide = function() {
    var index = (show.index() || 0);
    var offset = index * $('#slides').width();
    $('#reel').animate({ marginLeft: '-' + offset + 'px' }, 200);
  };

  var verticalAlign = function() {
    var d = dimensions();
    var margin = (d.height - $(this).height()) / 2;
    $(this).css({ paddingTop: margin + 'px' });
  };

  var adjustSlides = function() {
    setSlideDimensions();
    showCurrentSlide();
  };

  var followLinks = function(e) {
    e.stopPropagation();
    e.preventDefault();
    window.open(e.target.href);
  };

  function hideInstructions() {
    $('#instructions').slideUp(200);
  }

  $(window).bind('resize', function() {
    adjustSlides();
  });

  $(document).bind('keydown', move);

  $(document).bind("click", clickMove);

  return {
    slides: function() {
      return $('#slides .content');
    },
    index: function() {
      return Number(document.location.hash.split('#')[1]);
    },
    setIndex: function(i) {
      var newSlide = '#slide-' + i;
      if ($(newSlide).size() < 1) {
        return false;
      }
      else {
        document.location.hash = '#' + i;
        adjustSlides();
        $("a").unbind("click").click(followLinks);
      }
    },
    go: function() {
      this.setIndex(this.index() || 0);
      window.setTimeout(hideInstructions, 2000);
    }
  };
})();

$(document).ready(function() {
  show.go();
});
