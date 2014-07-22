/**
 * borderMenu.js v1.0.0 http://www.codrops.com
 * 
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2013, Codrops http://www.codrops.com
 */
$(function() {
	var menuwidth = 300; // pixel value for sliding menu width
	var menuspeed = 200; // milliseconds for sliding menu animation time

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

// 페북 관련
// 윈도우 시작시 페북 환경 체크
window.fbAsyncInit = function() {
	FB.init({
		appId : '618721798234752',
		cookie : true, // enable cookies to allow the server to access
		// the session
		xfbml : true, // parse social plugins on this page
		version : 'v2.0' // use version 2.0
	});

	// 페북 로그인 체크 시작
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});

};

// 로그인 상태에 따른 콜백
function statusChangeCallback(response) {
	console.log('statusChangeCallback 안녕하세요 스테터스 콜백 시전 ');
	if (response.status === 'connected') {
		login();
	} else if (response.status === 'not_authorized') {
		document.getElementById('welcomUser').innerHTML = 'Please log '
				+ 'into this app.';
		$("#facebookBtn").show();
	} else {
		$("#welcomeUser").html('<p>Please log into Facebook.</p>');
		$("#facebookBtn").show();
		FB
				.login(function(response) {
					if (response.authResponse) {
						console.log('Welcome!  Fetching your information.... ');

						location.reload();
					} else {
						console
								.log('User cancelled login or did not fully authorize.');
					}
				});
	}
}

function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

/* 로그인 */
function login() {
	console.log('로그인 실행 ');
	FB.api('/me', function(response) {
		console.log('Successful login for: ' + response.name);
	});

	/* make the API call */
	FB.api("/me/picture", function(response) {
		console.log('사진이야!!');
		if (response) {
			/* handle the result */
			console.log(response);
			$('#userImg').attr('src', response.data.url);
		}
	});

	FB.api('/me', function checkUser(response) {
		var form = $('#loginForm');

		var curruntUserName = response.name;
		var curruntUserEmail = response.email;
		var CurruntUser_facebookID = response.id;

		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : {
				"curruntUserName" : curruntUserName,
				"curruntUserEmail" : curruntUserEmail,
				"CurruntUser_facebookID" : CurruntUser_facebookID,
				todo : "checkUser"
			},

			success : function() {
				$("#welcomeUser").html(
						"<h2>안녕하세요 " + response.name + "님 !</h2>");
				
			}

		});

	});

}

/* sdk 불러오기 */
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/kr_KO/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// ajax wait

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

$(document).ready(function() {
	$('#logoutBtn').click(function logout() {
		console.log('로그아웃 시작 ');
		var con = confirm("나갈꺼야...?");
		if (con) {
			FB.logout(function(response) {
				console.log(response);
				// user is now logged out
				$.ajax({

					type : "POST",
					url : "user",
					data : {
						todo : "logout"
					},
					success : function() {
						location.href = "main";
					}

				});

			});
		}

	});

});