app.controller('CreateTicketController', ['$scope', '$filter', '$http', '$window', '$timeout',
    'TicketsService', 'CategoryService',
    function ($scope, $filter, $http, $window, $timeout, TicketsService, CategoryService) {

        var contextPath = $window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));


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
            var categoryId = $scope.category != undefined ? $scope.category : null;
            var urgency = $scope.urgency != undefined ? $scope.urgency : "";
            var comment = $scope.comment != undefined ? $scope.comment : "";
            var data = {
                name: $scope.name,
                state: ticketState,
                category: {id: categoryId},
                urgency: urgency,
                comment: comment,
                description: description,
                desiredResolutionDate: desiredResolutionDate
            };
            var fd = new FormData();
            angular.forEach($scope.file, function (value, key) {
                fd.append('files', value);
            });
            fd.append('ticketDto', JSON.stringify(data));
            var url = "/ticket/";
            return $http({
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
                        $window.location.href = "/tickets";
                    },
                    function (errResponse) {
                        console.log(errResponse);
                        $scope.error = 'true';
                    }
                )
        }

        $scope.saveDraft = function () {
            console.log("save");
            save('DRAFT');
        }

        $scope.saveNew = function () {
            save('NEW');
        }
    }]);
