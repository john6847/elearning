app.controller('CourseController', ['CourseService','CategoryService','$scope', '$localeStorage', function(CourseService,CategoryService, $scope, $localeStorage) {
    var self = this;
    self.user = angular.fromJson(localStorage.getItem('user'));
    self.categories=[];
    self.courses=[];
    self.course={ id:0, price: '', duration: '', title: '', syllabus:{}, startDate: null, category:{}, premium: false};
    self.message ='';

    fetchAllCategories();

    function fetchAllCategories() {
        CategoryService.fetchAllCategories()
            .then(
                function (d) {
                    self.categories = d;
                    console.log(self.categories);
                },
                function (errorResponse) {

                    console.error(errorResponse);
                })
    }

    fetchAllCourses();

    function fetchAllCourses() {
        CourseService.fetchAllCourses()
            .then(
                function (d) {
                    self.courses = d;
                    console.log(self.courses);
                },
                function (errorResponse) {
                    console.error(errorResponse);
                })
    }

    //creating a new Course
    function createCourse(course) {
        CourseService.CreateCourse(course)
            .then(
                fetchAllCourses(),
                function (err) {
                    self.message ='Le cours n\'a pas put etre cree';
                    console.error(err);
                }
            )
    }

    //updating a course
    function updateCourse(course, id) {
        CourseService.updateCourse(course,id).then(
            fetchAllCourses(),
            function (errResponse) {
                self.message='La categorie n\'a pas put etre modifie';
                console.error(errResponse);
            }
        )
    }

    //deleting a course
    function deleteCourse(id) {
        CourseService.deleteCourse(id)
            .then  (
                fetchAllCourses(),
                function (errResponse) {
                    self.message='La course n\'a pas put etre elimine';
                    console.error(errResponse)
                }
            )
    }

    //adding new course
    self.register= function () {
        createCourse(self.course);
        console.log(self.course)
        self.reset();
    };

    //resetting category form
    self.reset= function () {
        self.course = {};
        $scope.courseForm.$setPristine();
    };

}]);


'use strict';
