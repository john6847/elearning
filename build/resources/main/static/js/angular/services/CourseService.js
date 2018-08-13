'use strict'; 
app.factory('CourseService',['$http', '$q', function($http, $q){
    return{
        fetchAllCourses:function(){ // fetching all courses from backend
            var deferred = $q.defer();
            $http.get('/api/course/')
                .then(function(response){
                    deferred.resolve(response.data);
                },function(error){
                    console.error(error);
                    deferred.reject(error);
                }
            );
            return deferred.promise;
        },

        CreateCourse: function createCourse(course) {
                var deferred = $q.defer();
                $http.post("/api/course/", course)
                    .then(
                        function (response) {
                            deferred.resolve(response.data)
                        }, function (errResponse) {
                            console.error(errResponse);
                            deferred.reject(errResponse);
                        });
                return deferred.promise;
            },

            updateCourse: function updateCourse(course, id) {
                var deferred = $q.defer();
                $http.put("/api/course/" + id, course).then(
                    function (response) {
                        deferred.resolve(response.data)
                    }, function (errResponse) {
                        console.error(errResponse);
                        deferred.reject(errResponse);
                    });
                return deferred.promise;
            },

            deleteCourse: function deleteCourse(id) {
                var deferred = $q.defer();
                $http.delete("/api/course/" + id)
                    .then(function (response) {
                        deferred.resolve(response.data)
                    }, function (errResponse) {
                        console.error(errResponse);
                        deferred.reject(errResponse);
                    });
                return deferred.promise;
            }
        }
}]);