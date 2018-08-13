'use strict';

app.controller('HomeController', ['$localStorage',function ($localStorage,$scope,$http) {
    // $scope.greeting = data;
    var self = this;
    self.user = angular.fromJson(localStorage.getItem('user'));
}])