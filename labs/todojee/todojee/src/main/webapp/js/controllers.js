function ItemsCtrl($scope, $http, Items) {

  $scope.currentPage = 1;
  $scope.pageSize = 5;

    // Define a refresh function, that updates the data from the REST service
    $scope.refresh = function() {
        $scope.items = Items.query();
    };

    // Define a clearMessages function that resets the values of the error and
    // success messages.
    $scope.clearMessages = function () {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};
    };

    // Define a reset function, that clears the prototype newItem object, and
    // consequently, the form
    $scope.reset = function() {
        // Sets the form to it's pristine state
        if($scope.itemForm) {
            $scope.itemForm.$setPristine();
        }
        // Clear input fields. If $scope.newItem was set to an empty object {},
        // then invalid form values would not be reset.
        // By specifying all properties, input fields with invalid values are also reset.
        $scope.newItem = {description: "", done: false};

        // clear messages
        $scope.clearMessages();
    };

    // Define a function to handle checkbox toggles, which updates the item status using the REST service,
    // and displays any error messages
    $scope.toggleDone = function(item) {
        $scope.clearMessages();

        Items.save(item, function() {

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();

            // mark success on the registration form
            //$scope.successMessages = [ 'Task updated successfully' ];
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
        });

    };

    // Define a delete function, which deletes the item using the REST service,
    // and displays any error messages
    $scope.delete = function(itemid) {
        $scope.clearMessages();

        Items.delete({ id: itemid }, function() {

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();

            // mark success on the registration form
            $scope.successMessages = [ 'Task deleted successfully' ];
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
        });

    };

    // Define a register function, which adds the item using the REST service,
    // and displays any error messages
    $scope.register = function() {
        $scope.clearMessages();

        Items.save($scope.newItem, function(data) {

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();

            // mark success on the registration form
            $scope.successMessages = [ 'Task Added successfully' ];
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
        });

    };

    // Call the refresh() function, to populate the list of members
    $scope.refresh();

    // Initialize newMember here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();
}
