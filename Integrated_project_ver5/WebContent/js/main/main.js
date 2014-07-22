/**
 * 
 */

$('#carousel').carousel({
	pause : true,
	interval : false
});


//윈도우 시작시 페북 환경 체크 
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
						console
								.log('Welcome!  Fetching your information.... ');

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


/*sdk 불러오기  */
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/kr_KO/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));