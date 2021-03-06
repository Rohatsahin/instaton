translationStorageProvider.$inject = ['$cookies', '$log', 'LANGUAGES'];

function translationStorageProvider($cookies, $log, LANGUAGES) {
  return {
    get: get,
    put: put
  };

  function get(name) {
    if (LANGUAGES.indexOf($cookies.getObject(name)) === -1) {
      $cookies.putObject(name, 'tr');
    }
    return $cookies.getObject(name);
  }

  function put(name, value) {
    $cookies.putObject(name, value);
  }
}

angular.module('instaton.app.utils').factory('translationStorageProvider', translationStorageProvider);