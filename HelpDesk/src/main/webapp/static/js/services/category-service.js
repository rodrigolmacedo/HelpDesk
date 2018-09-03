app.service('CategoryService', ['$http', '$q', function ($http, $q) {

    this.getCategories = function () {
        return $http.get("/categories")
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };


}]);