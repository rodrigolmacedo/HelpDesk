app.controller('LeaveFeedback', ['$scope', '$filter', '$http', '$window', '$timeout', 'FeedbackService', 'TicketsService',
    function ($scope, $filter, $http, $window, $timeout, FeedbackService, TicketsService) {

        var path = $window.location.pathname.split('/');
        var ticketId = path[path.length - 2];
        console.log(ticketId);

        $scope.rate = 0;

        $scope.saveRate = function (rate) {
            console.log(rate);
            $scope.rate = rate;
        }

        $scope.leaveFeedback = function () {
            var feedback = {
                rate: $scope.rate,
                text: $scope.text,
                ticketId: ticketId
            }
            FeedbackService.leaveFeedback(ticketId, feedback)
                .then(
                    function (response) {
                        $timeout(function () {
                            var url = "";
                            console.log(url);
                            window.history.back();
                        });
                    }
                )
        }

        TicketsService.getTicket(ticketId)
            .then(
                function (response) {
                    $scope.ticket = response;
                },
                function (errResponse) {
                    console.log(errResponse.statusText);
                }
            )

    }]);