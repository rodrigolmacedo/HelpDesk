app.controller('EditTicketController', ['$scope', '$filter', '$http', '$window', '$timeout',
    'TicketsService', 'CategoryService',
    function ($scope, $filter, $http, $window, $timeout, TicketsService, CategoryService) {

        var path = $window.location.pathname.split('/');
        var ticketId = path[path.length - 2];

        $scope.urgency = 'LOW';
        $scope.category = '1';

        CategoryService.getCategories()
            .then(
                function (response) {
                    $scope.categories = response;
                },
                function (errResponse) {
                    console.log(errResponse);
                }
            );

        var save = function (ticketState) {
            if ($scope.name == undefined || $scope.name == "") {
                return;
            }

            var description = $scope.description != undefined ? $scope.description : "";
            var desiredResolutionDate = $scope.date != undefined ? new Date($scope.date).getTime() : new Date().getTime();
            var categoryId = $scope.category != undefined ? $scope.category : "";
            var urgency = $scope.urgency != undefined ? $scope.urgency : "";
            console.log(urgency)
            var comment = $scope.comment != undefined ? $scope.comment : "";
            console.log(desiredResolutionDate);
            var data = {
                id: $scope.ticket.id,
                name: $scope.name,
                state: ticketState,
                category: {id: categoryId},
                urgency: urgency,
                comment: comment,
                description: description,
                desiredResolutionDate: desiredResolutionDate
            };

            if ($scope.file != undefined) {
                var fd = new FormData();
                angular.forEach($scope.file, function (value, key) {
                    fd.append('files', value);
                });
                var url = "/ticket/" + ticketId + "/attachments";
                $http({
                    method: 'POST',
                    url: url,
                    headers: {
                        'Content-Type': undefined
                    },
                    data: fd,
                    transformRequest: function (data, headersGetterFunction) {
                        return data;
                    }
                })
                    .then(
                        function (response) {
                        },
                        function (errResponse) {
                            $scope.error=true;
                        }
                    )
            }

            TicketsService.editTicket(ticketId, data)
                .then(
                    function (response) {
                        $timeout(function () {
                            $window.location.href = "/overview/" + $scope.ticket.id;
                        });
                    },
                    function (errResponse) {
                        console.log(errResponse);
                    }
                )
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

        TicketsService.getTicket(ticketId)
            .then(
                function (response) {
                    $scope.ticket = response;
                    $scope.name = $scope.ticket.name;
                    loadAttachments($scope.ticket.id);
                },
                function (errResponse) {
                    console.log(errResponse);
                }
            );

        $scope.saveDraft = function () {
            save('DRAFT');
        }

        $scope.saveNew = function () {
            save('NEW');
        }

        $scope.deleteFile = function (fileId) {
            TicketsService.deleteAttachment(ticketId, fileId)
                .then(
                    function (response) {
                        loadAttachments(ticketId);
                    },
                    function (errResponse) {
                        console.log(errResponse.statusText);
                    }
                )
        }
    }]);
