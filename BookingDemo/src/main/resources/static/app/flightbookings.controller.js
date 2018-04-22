(function() {
	'use strict';
	angular.module('app').controller('BookingController', BookingController);

	BookingController.$inject = ['$http'];

	function BookingController($http) {
		var vm = this;
		vm.bookings = [];
		vm.getAll = getAll;
		vm.getAffordable = getAffordable;
		vm.deletebooking = deletebooking;
		init();

		function init() {
			getAll();
		}

		function getAll() {
			var url = "bookings/all";
			var bookingspromise = $http.get(url);
			bookingspromise.then(function(response) {
				vm.bookings = response.data;
			});

		}

		function getAffordable() {
			var url = "bookings/affordable/" + 500;
			var bookingspromise = $http.get(url);
			bookingspromise.then(function(response) {
				vm.bookings = response.data;
			});


		}

		function deletebooking(id) {
			var url = "bookings/delete/" + id;
			$http.post(url).then(function(response) {
				vm.bookings = response.data;
			});




		}
	}
})();