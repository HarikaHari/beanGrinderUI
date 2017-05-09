'use strict';

angular.module('app').controller('ctrlLogin', ['$scope', '$location', 'loginService', function($scope, $location, loginService) {
    var self = this;
    self.getUser = getUser;

    function getUser(){
    	loginService.getUser(self.studentId, self.studentPass)
            .then(
            function(d) {
                self.id = d;
                $location.path("/course");
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }


}]);


