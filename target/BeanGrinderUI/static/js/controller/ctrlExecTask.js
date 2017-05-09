'use strict';

angular.module('app').controller('ctrlExecTask', ['$scope', '$rootScope', '$location', 'execTaskService', function($scope, $rootScope, $location, execTaskService) {
    var self = this;

    self.task = $rootScope.task;
}]);


