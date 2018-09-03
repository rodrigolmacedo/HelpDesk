app.controller('TicketOverviewController', ['$scope', '$filter', '$http', '$window', '$timeout','TicketsService', 'FeedbackService',
    function ($scope, $filter, $http, $window, $timeout, TicketsService, FeedbackService) {

        var path = $window.location.pathname.split('/');
        var contextPath = path[path.length - 3];
        var ticketId = path[path.length - 1];

        $scope.attachments = {};
        $scope.editUrl = "";
        $scope.newCommentUrl = "";


        var loadOwner = function (userId) {
            TicketsService.loadUser(userId)
                .then(
                    function (response) {
                        $scope.owner = response;
                    },
                    function (errResponse) {
                        console.log(errResponse);
                    }
                );
        }

        var loadApprover = function (approverId) {
            TicketsService.loadUser(approverId)
                .then(
                    function (response) {
                        $scope.approver = response;
                    },
                    function (errResponse) {
                        console.log(errResponse);
                    }
                );
        }

        var loadAssignee = function (assigneeId) {
            TicketsService.loadUser(assigneeId)
                .then(
                    function (response) {
                        $scope.assignee = response;
                    },
                    function (errResponse) {
                        console.log(errResponse);
                    }
                );
        }

        var loadAttachments = function (ticketId) {
            TicketsService.loadAttachments(ticketId)
                .then(
                    function (response) {
                        $scope.attachments = response;
                    },
                    function (errResponse) {
                        console.log(errResponse);
                    }
                );
        }

        var loadHistory = function (ticketId) {
            TicketsService.loadHistory(ticketId)
                .then(
                    function (response) {
                        var history = response;
                        angular.forEach(history, function (value, key) {
                            value.date = $filter('date')(value.date, "MMM dd, yyyy HH:mm");
                            value.orderId = key;
                        });
                        $scope.history = history;
                    },
                    function (errResponse) {
                        console.log(errResponse);
                    }
                );
        }

        var loadComments = function (ticketId) {
            TicketsService.loadComments(ticketId)
                .then(
                    function (response) {
                        var comments = response;
                        angular.forEach(comments, function (value, key) {
                            value.date = $filter('date')(value.date, "MMM dd, yyyy HH:mm");
                            value.orderId = key;
                        });
                        $scope.comments = comments;
                    },
                    function (errResponse) {
                        console.log(errResponse);
                    }
                );
        }

        TicketsService.getTicket(ticketId)
            .then(
                function (response) {
                    $scope.ticket = response;
                    $scope.editUrl = contextPath + "/ticket/" + ticketId + "/edit";
                    $scope.leaveFeedbackUrl = "/ticket/" + ticketId + "/leavefeedback";
                    $scope.viewFeedbackUrl = "/ticket/" + ticketId + "/viewfeedback";
                    $scope.newCommentUrl = contextPath + "tickets/" + ticketId + "/comments/new";
                    $scope.ticket.desiredResolutionDate = $filter('date')($scope.ticket.desiredResolutionDate, "dd/MM/yyyy");
                    $scope.ticket.createdOn = $filter('date')($scope.ticket.createdOn, "dd/MM/yyyy");
                    $scope.ticket.category = $scope.ticket.category.name;
                    $scope.ticket.state = $scope.ticket.state.charAt(0).toUpperCase() + $scope.ticket.state.slice(1).toLowerCase();
                    $scope.ticket.urgency = $scope.ticket.urgency.charAt(0).toUpperCase() + $scope.ticket.urgency.slice(1).toLowerCase();
                    loadOwner($scope.ticket.owner.id);
                    if ($scope.ticket.approver != null) {
                        loadApprover($scope.ticket.approver.id);
                    }
                    if ($scope.ticket.assignee != null) {
                        loadAssignee($scope.ticket.assignee.id);
                    }
                    loadAttachments($scope.ticket.id);
                    loadHistory($scope.ticket.id);
                    loadComments($scope.ticket.id);
                },
                function (errResponse) {
                    console.log(errResponse);
                }
            );

        FeedbackService.getFeedback(ticketId)
            .then(
                function (response) {
                    $scope.feedback = response;
                    console.log($scope.feedback);
                },
                function (errResponse) {
                    console.log(errResponse.statusText);
                }
            );

        $scope.addComment = function () {
            var data = {text: $scope.newComment};
            if ($scope.newComment != undefined) {
                TicketsService.addComment(ticketId, data)
                    .then(
                        function (response) {
                            loadComments(ticketId);
                        },
                        function (errResponse) {
                            console.log(errResponse);
                        }
                    )
            }
        }

        $scope.showAllComments = false;
        $scope.showComments = function () {
            $scope.showAllComments = true;
        }

        $scope.showAllHistory = false;
        $scope.showHistory = function () {
            $scope.showAllHistory = true;
        }
    }]);