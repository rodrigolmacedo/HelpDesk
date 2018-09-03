app.service('TicketsService', ['$http', '$q', function ($http, $q) {

    this.getCurrentUser = function () {
        return $http.get("/users/current")
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.getTicket = function (ticketId) {
        return $http.get("/ticket/" + ticketId)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.saveTicket = function (formData) {
        var url = "/ticket/";
        return $http({
            method: 'POST',
            url: url,
            headers: {
                'Content-Type': undefined
            },
            data: formData,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
        })
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };


    this.decline = function (ticketId) {
        return $http.put("/ticket/" + ticketId + "/decline", null)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.approve = function (ticketId) {
        return $http.put("/ticket/" + ticketId + "/approve", null)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.submit = function (ticketId) {
        return $http.put("/ticket/" + ticketId + "/submit", null)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.cancel = function (ticketId) {
        return $http.put("/ticket/" + ticketId + "/cancel", null)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.assign = function (ticketId) {
        return $http.put("/ticket/" + ticketId + "/assign", null)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.done = function (ticketId) {
        return $http.put("/ticket/" + ticketId + "/done", null)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.loadHistory = function (ticketId) {
        return $http.get("/ticket/" + ticketId + "/history/")
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.loadComments = function (ticketId) {
        return $http.get("/ticket/" + ticketId + "/comments/")
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.addComment = function (ticketId, comment) {
        return $http.post("/ticket/" + ticketId + "/comments", comment)
            .then(
                function (response) {
                    return response.data
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.loadAttachments = function (ticketId) {
        return $http.get("/ticket/" + ticketId + "/attachments/")
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.deleteAttachment = function (ticketId, attachmentId) {
        return $http.delete('/ticket/' + ticketId + '/attachments/' + attachmentId)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    }

    this.loadUser = function (userId) {
        return $http.get("/users/" + userId)
            .then(
                function (response) {
                    console.log(response.data);
                    return response.data;
                },
                function (errResponse) {
                    return errResponse.statusText;
                }
            )
    };

    this.editTicket = function (ticketId, data) {
        return $http.put("/ticket/" + ticketId, data)
            .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    console.log(errResponse);
                    return $http.reject;
                }
            )
    }
}])
;