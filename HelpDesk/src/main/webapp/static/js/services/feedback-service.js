app.service('FeedbackService', ['$http', '$q', function ($http, $q) {

    this.getFeedback = function (ticketId) {
        return $http.get("/ticket/" + ticketId + "/feedback")
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.leaveFeedback = function (ticketId, feedback) {
        return $http.post("/ticket/" + ticketId + "/feedback", feedback)
            .then(
                function (response) {
                    return response.data
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

}]);