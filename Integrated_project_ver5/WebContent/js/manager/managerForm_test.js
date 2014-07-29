/**
 * 
 */

$(document).ready(function() {

	$('#userMenu li').first().click(function showUserMenu() {
		$(this).siblings().slideToggle("300");
	});
	$('#webtoonMenu li').first().click(function showUserMenu() {
		$(this).siblings().slideToggle("300");
	});
	$('#chartMenu li').first().click(function showUserMenu() {
		$(this).siblings().slideToggle("300");
	});
	$('#genreMenu li').first().click(function showUserMenu() {
		$(this).siblings().slideToggle("300");
	});
	$('#keywordMenu li').first().click(function showUserMenu() {
		$(this).siblings().slideToggle("300");
	});
	$('#authorMenu li').first().click(function showUserMenu() {
		$(this).siblings().slideToggle("300");
	});

});
