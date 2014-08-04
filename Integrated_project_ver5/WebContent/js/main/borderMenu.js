/**
 * borderMenu.js v1.0.0 http://www.codrops.com
 * 
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2013, Codrops http://www.codrops.com
 */
$(function() {
	var menuwidth = 180; // pixel value for sliding menu width
	var menuspeed = 250; // milliseconds for sliding menu animation time

	var $bdy = $('body');
	var $container = $('#pgcontainer');
	var $burger = $('#hamburgermenu');
	var negwidth = "-" + menuwidth + "px";
	var poswidth = menuwidth + "px";

	$('.menubtn').on('click', function(e) {
		if ($bdy.hasClass('openmenu')) {
			jsAnimateMenu('close');
		} else {
			jsAnimateMenu('open');
		}
	});

	$('.overlay').on('click', function(e) {
		if ($bdy.hasClass('openmenu')) {
			jsAnimateMenu('close');
		}
	});

	$('a[href$="#"]').on('click', function(e) {
		e.preventDefault();
	});

	function jsAnimateMenu(tog) {
		if (tog == 'open') {
			$bdy.addClass('openmenu');

			$container.animate({
				marginRight : negwidth,
				marginLeft : poswidth
			}, menuspeed);
			$burger.animate({
				width : poswidth
			}, menuspeed);
			$('.overlay').animate({
				left : poswidth
			}, menuspeed);
		}

		if (tog == 'close') {
			$bdy.removeClass('openmenu');

			$container.animate({
				marginRight : "0",
				marginLeft : "0"
			}, menuspeed);
			$burger.animate({
				width : "0"
			}, menuspeed);
			$('.overlay').animate({
				left : "0"
			}, menuspeed);
		}
	}
});

	
	function newbieCheck(){
	$('#goGenreModal').show();
	}
	
$(document).ready(function(){
	var currentPage = location.href;
	
	 if(currentPage.indexOf("main") >= 0){
		$('#pageInfo').append("<h2>갓툰</h2>");
	}else if(currentPage.indexOf("genre") >= 0 ){
		$('#pageInfo').append("<h2>선호 장르</h2>");
	}else if(currentPage.indexOf("userGenre") >= 0 ){
		$('#pageInfo').append("<h2>평가 하기</h2>");
	}else if(currentPage.indexOf("recommend") >= 0 ){
		$('#pageInfo').append("<h2>추천 웹툰</h2>");
	}
	 else if(currentPage.indexOf("mypage") >= 0 ){
		$('#pageInfo').append("<h2>마이 페이지</h2>");
	} else if(currentPage.indexOf("manager") >= 0 ){
		$('#pageInfo').append("<h2>매니져</h2>");
	}
});


//ajax wait

$(document).ready(function() {
	$(document).ajaxStart(function() {
		$("#wait").css("display", "block");
	});
	$(document).ajaxComplete(function() {
		$("#wait").css("display", "none");
	});
	$("button").click(function() {
		$("#txt").load("demo_ajax_load.asp");
	});
});

