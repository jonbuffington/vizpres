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

//init front page
onResize();
$('#wrapper').show();

function onResize() {
	var t = $('#top');
	var h = $(window).height();
	var m = (h-t.height())/2;
	var m2 = (h-251)/2;
	if (m<0) m = 0;
	t.css('margin', m+'px 0');
	if (h<500) h = 500;
	$('.box').css('height', h+'px');
	$('#footer').css('padding', m2+'px 0');
}

//resize handler
$(window).resize(function() {
	onResize();
});


//interaction handlers
$('#menu').hover(
	function() {
		$('#menu_list').slideToggle(300);
	},
	function() {
		$('#menu_list').slideToggle(300);
	}
);

$('.menu_item').hover(
	function() {
		$('.hidden',this).show();
	},
	function() {
		$('.hidden',this).hide();
	}
);

$('.menu_item').click(function() {
	scr(this.id, true);
});

$('#read').click(function() {
	scr('0');
});

$('.box_arrow').click(function() {
	scr(this.parentNode.id);
});

$('#yes').click(function() {
	$.ajax({
		url:'yes.php',
		success: function(data) {
			$('#real_num').html(data);
			$('#yes').unbind('click');
			$('#stance').html('Thank you for your support.');
		}
	});
});

function scr(id, menu) {
	id = parseInt(id.slice(id.length-1),10);
	if (menu) id = id-1;
	var h=$(window).height();
	if (h<500) h=500;
	$(window.opera?'html':'html, body').animate({scrollTop:h*id+$(window).height()+id}, 900);
}	
