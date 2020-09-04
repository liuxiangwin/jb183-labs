// Define the REST resource service, allowing us to interact with it as a high level service
angular.module('itemsService', ['ngResource']).
    factory('Items', function($resource){
  return $resource('api/items/:id', {});
});
