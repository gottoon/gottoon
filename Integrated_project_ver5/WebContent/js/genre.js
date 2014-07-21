	

function Validate() {
			if (!validateForm()) {
				alert("하나 찍으라고 부왁!!");
				return false;
			}
			return true
		}
		function validateForm() {
			var c = document.getElementsByTagName('input');
			for (var i = 0; i < c.length; i++) {
				if (c[i].type == 'checkbox') {
					if (c[i].checked) {
						return true
					}
				}
			}
			return false;
		}
		
		