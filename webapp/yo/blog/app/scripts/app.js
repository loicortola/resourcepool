
// Declare app level module which depends on filters, and services
var app = angular.module('app', ['ngRoute', 'i18n']);
  app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/', {
      templateUrl: '../views/main.html', 
      controller: 'MainCtrl'
    });
    $routeProvider.otherwise({redirectTo: '/'});
  }]);