'use strict';
app.controller('CategoryController', ['CategoryService','$scope','$localStorage', function(CategoryService, $scope, $localStorage) {
    var self = this;
    self.user = angular.fromJson(localStorage.getItem('user'));
    self.topics=[];

    self.category={ id:0, description: '', name: '', parentCategory:null};
    self.message ='';


    fetchAllCategories();
    //Fetching all Categories
    function fetchAllCategories() {
        CategoryService.fetchAllCategories()
            .then(
                function (d) {
                    self.categories = d;
                    console.log(self.categories);
                },
                function (errorResponse) {
                    self.message="Categories inaccessibles "
                    console.error(errorResponse);
                })
    }

    //creating a new Category
    function createCategoryAndTopics(topics) {
        CategoryService.CreateCategoryAndTopics(topics)
            .then(
                fetchAllCategories(),
                function (err) {
                    self.message ='La categorie n\'a pas put etre cree';
                    console.error(err);
                }
            )
    }

    //updating a category
    function updateCategory(category, id) {
       CategoryService.updateCategory(category,id).then(
            fetchAllCategories(),
            function (errResponse) {
                self.message='La categorie n\'a pas put etre modifiee';
                console.error(errResponse);
            }
        )
    }

    //deleting a category
    function deleteCategory(id) {
        CategoryService.deleteCategory(id)
            .then  (
                fetchAllCategories(),
                function (errResponse) {
                    self.message='La categorie n\'a pas put etre eliminee';
                    console.error(errResponse)
                }
            )
    }

    //adding new category and topics
    self.register= function () {
        console.log( self.category.parentCategory)
        self.category.parentCategory =self.category.parentCategory.id;
        createCategoryAndTopics(self.topics);
        self.reset();
    };

    //resetting category form
    self.reset= function () {
        self.topics = [];
        self.category = {};
        $scope.categoryForm.$setPristine();
    };

    //Adding a new topics
    self.newTopic = function(topic) {
        return {
            name: topic,
            category: self.category
        };
    };

}]);


