app.controller('TicketsController', ['$scope', '$filter', '$http', '$window', 'TicketsService',
    function ($scope, $filter, $http, $window, TicketsService) {

        TicketsService.getCurrentUser()
            .then(
                function (response) {
                    $scope.user = response;
                    var user = response;
                    angular.forEach(user.tickets, function (value, key) {
                        if (value.state == 'IN_PROGRESS') {
                            value.state = 'In progress';
                        }
                        value.state = value.state.charAt(0).toUpperCase() + value.state.slice(1).toLowerCase();
                        value.urgency = value.urgency.charAt(0).toUpperCase() + value.urgency.slice(1).toLowerCase();
                        value.desiredResolutionDate = $filter('date')(value.desiredResolutionDate, "dd/MM/yyyy");
                        switch(value.urgency){
                            case "Critical": value.urgencyNumber = 1;
                            case "High": value.urgencyNumber = 2;
                            case "Medium": value.urgencyNumber = 3;
                            case "Low": value.urgencyNumber = 4;
                        }
                    });
                    $scope.allTickets = user.tickets;
                    $scope.role = user.role.toLowerCase();

                    var myTickets = [];

                    if (user.role.toLowerCase() == "employee") {
                        for (var i = $scope.allTickets.length - 1; i >= 0; i--) {
                            if ($scope.allTickets[i].owner != null) {
                                if ($scope.allTickets[i].owner.id == user.id) {
                                    myTickets.push($scope.allTickets[i]);
                                }
                            }
                        }
                    }
                    if (user.role.toLowerCase() == "manager") {
                        for (var i = $scope.allTickets.length - 1; i >= 0; i--) {
                            if ($scope.allTickets[i].owner != null && $scope.allTickets[i].approver != null) {
                                if ($scope.allTickets[i].owner.id == user.id || $scope.allTickets[i].approver.id == user.id) {
                                    myTickets.push($scope.allTickets[i]);
                                }
                            }
                        }
                    } else if (user.role.toLowerCase() == "enginer") {
                        for (var i = $scope.allTickets.length - 1; i >= 0; i--) {
                            if ($scope.allTickets[i].assignee != null) {
                                if ($scope.allTickets[i].assignee.id == user.id) {
                                    myTickets.push($scope.allTickets[i]);
                                }
                            }
                        }
                    }

                    $scope.myTickets = myTickets;
                },
                function (errResponse) {
                    console.log(errResponse.statusText);
                }
            );

        var decline = function (ticketId) {
            TicketsService.decline(ticketId)
                .then(
                    function (response) {
                        $window.location.reload();
                    }
                )
        }

        var approve = function (ticketId) {
            TicketsService.approve(ticketId)
                .then(
                    function (response) {
                        $window.location.reload();
                    }
                )
        }

        var submit = function (ticketId) {
            TicketsService.submit(ticketId)
                .then(
                    function (response) {
                        $window.location.reload();
                    }
                )
        }

        var cancel = function (ticketId) {
            TicketsService.cancel(ticketId)
                .then(
                    function (response) {
                        $window.location.reload();
                    }
                )
        }

        var assign = function (ticketId) {
            TicketsService.assign(ticketId)
                .then(
                    function (response) {
                        $window.location.reload();
                    }
                )
        }

        var done = function (ticketId) {
            TicketsService.done(ticketId)
                .then(
                    function (response) {
                        $window.location.reload();
                    }
                )
        }

        var leaveFeedback = function (ticketId) {
            var url = "/tickets/" + ticketId + "/leavefeedback";
            $window.location.href = url;
        }

        var viewFeedback = function (ticketId) {
            var url = "/tickets/" + ticketId + "/viewfeedback";
            $window.location.href = url;
        }

        $scope.doAction = function (action, ticketId) {
            console.log(action);
            $scope.action = action;
            if (action == 'decline') {
                decline(ticketId);
            } else if (action == 'approve') {
                approve(ticketId);
            }
            else if (action == 'submit') {
                submit(ticketId);
            }
            else if (action == 'cancel') {
                cancel(ticketId);
            }
            else if (action == 'approve') {
                approve(ticketId);
            } else if (action == 'done') {
                done(ticketId);
            } else if (action == 'assign') {
                assign(ticketId);
            } else if (action == 'leavefeedback') {
                leaveFeedback(ticketId);
            } else if (action == 'viewfeedback') {
                viewFeedback(ticketId);
            }

        }

        $scope.sortType = 'urgencyNumber';
        $scope.sortReverse = false;
        $scope.searchTicket = "";
    }]);