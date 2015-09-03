# $resource é um módulo do Angular que usamos para consumir serviços, ele é injetado por ngResource no app/index.module.coffee
angular.module "project-test"
  .factory 'AddressesFactory', ($http) ->
    {
      query: ->
        $http(
          url:  '/api/address'
          method: 'GET'
      )

      create: (address) ->
        $http(
          method: 'POST'
          isArray: true
          url:  '/api/address'
          data: address
          headers: 
            "Content-Type" : "application/json"
        )
    }

angular.module "project-test"
  .factory 'AddressFactory', ($http) ->
    {
      show: (id) ->
        $http(
          url:  "/api/address/#{id}"
          method: 'GET'
        )

      update: (id, address) ->
        $http(
          url:  "/api/address/#{id}"
          method: 'PUT'
          data: address
          headers: 
            "Content-Type" : "application/json"
        )

      delete: (id) ->
        $http(
          url:  "/api/address/#{id}"
          method: 'DELETE'
        )
    }
    
angular.module "project-test"
  .factory 'ZipFactory', ($http) ->
    
    {
      get: (zip) ->
        $http 
          url: '/api/zip/find',
          method: 'GET',
          params: {code: zip} 
    }
    
      