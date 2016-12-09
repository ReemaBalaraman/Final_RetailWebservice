$(document).ready(function () {

	$('#login').click(onLogin);

	function onLogin() {
		var username = document.getElementById("userName").value;
		var pwd = document.getElementById("password").value;

		var json = {
				"email": username,
				"password": pwd
		};
		var data = JSON.stringify(json);
		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'http://localhost:8081/customerservice/customer/auth');
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.onload = function() {
			alert('inside xhr'+xhr.responseText);
			if (xhr.responseText.indexOf('Succefully') > -1) {
				$('.login_form').hide();

				localStorage.usr=username;
				$('#message').html('Succefully logged in');
				$('#orderMessage').hide();


			}
		};
		xhr.send(data);

	}

	// Produce a  Product
	var produceProductList = function (product) {
		alert('insideinside');
		$image_url = 'images/'+product["productID"]+'.jpg';
		var content = '<ul>';
		content += '<img src=' + $image_url + '>';
		content += '<li class="ProductInfo" id="' + product["productID"] + '">' + product["productDescription"] + ', Price ' + product["unitPrice"] + '</li>';

		content += '</ul>';

		$('#content').html(content);

		var username = localStorage.usr ;
		alert('user is'+username);
		if (typeof(username) !== "undefined" && username !== null)
		{
			
			$("#order").css("display", "block");
			$('#order').show();
			var prodData = JSON.stringify(product);
			$("#orderData").val(prodData);


		}
		else
		{
			$('#orderMessage').html('Please login to place an order for this Productby one click');
		}



	}

	$('#order').click(placeOrder);

	function placeOrder()
	{
		var orderData = $("#orderData").val();

		var data = jQuery.parseJSON(orderData);
		var urlOrder = data["links"][1]["url"];
		var urlAction = data["links"][1]["action"];
		var username = localStorage.usr ;
		var productid = data["productID"];
		  var json ={"orderDate":"2016-10-10","productOrder":[{"product":{"productID":productid},"orderQuantity":"3"}],"customerEmail":username} ;
	  	
	var reqData = JSON.stringify(json);

		
		var xhr = new XMLHttpRequest();
		xhr.open(urlAction, urlOrder);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.onload = function() {
			
			if (xhr.responseText.indexOf('succefully') > -1) {
				alert('insidejhbjh xhr'+xhr.responseText);
				$('.login_form').hide();
				$('#order').hide();
				localStorage.removeItem('usr');
				$('#message').html('Succefully logged in');
				$('#orderMessage').html(xhr.responseText);
				$("#orderMessage").css("display", "block");

			}
		};
		xhr.send(reqData);


	}
	var getSearchResults = function () {

		// Get the search term.
		var productID = $('#term').val();

		// If the title was empty, give an error message.
		if (productID == '') {
			$('#content').html('No ID to enter a search term.');

			// Otherwise do API call
		} else {
			$('#content').html("Fetching search results...");

			var xhr = new XMLHttpRequest();
			xhr.open('GET', 'http://localhost:8081/productservice/product/' + productID);
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.onload = function() {
				alert('inside xhr'+xhr.responseText);
				if (xhr.status === 200) {
					var data = jQuery.parseJSON(xhr.responseText);
					produceProductList(data);
				}
			};
			xhr.send();

		}
	}
	$('#search').click(getSearchResults);	

});