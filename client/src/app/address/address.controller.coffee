angular.module "project-test"
  .controller "AddressDetailController", [ 
    '$timeout'
    '$scope'
    '$routeParams'
    'AddressFactory'
    '$location'
    'ZipFactory'
    ($timeout, $scope, $routeParams, AddressFactory, $location, ZipFactory) ->

      AddressFactory.show($routeParams.id).success (data) ->
        $scope.address  = data

      $scope.updateAddress = ($routeParams) ->
        console.log $scope.address
        AddressFactory.update($scope.address.id, $scope.address).success ->
          $location.path '/address-list'
        return

      $scope.findZip = ->
        ZipFactory.get($scope.address.zipcodeNumber)
          .success (data) ->
            $scope.address.street = data.address
            $scope.address.district = data.district
            $scope.address.state = data.uf
            $scope.address.complement = data.complement
            $scope.address.city = data.locality

        # $location.path '/list-address'
        return

      # callback for ng-click 'cancel':

      $scope.cancel = ->
        $location.path '/list-address'
        return

      return
]
angular.module "project-test"
  .controller 'AddressListController', [
    '$scope'
    'AddressesFactory'
    'AddressFactory'
    '$location'
    ($scope, AddressesFactory, AddressFactory, $location) ->
      # callback for ng-click 'editUser':

      $scope.editAddress = (addressId) ->
        $location.path '/edit-address/' + addressId
        return

      # callback for ng-click 'deleteAddress':

      $scope.deleteAddress = (addressId) ->
        AddressFactory.delete(addressId).success ->
          AddressesFactory.query().success (data) ->
            $scope.addresses = data
        return

      # callback for ng-click 'createAddress':

      $scope.createNewAddress = ->
        $location.path '/create-address'
        return

      AddressesFactory.query().success (data) ->
        $scope.addresses = data  
      return
]
angular.module "project-test"
  .controller 'AddressCreateController', [
    '$scope'
    'AddressesFactory'
    '$location'
    'ZipFactory'
    ($scope, AddressesFactory, $location, ZipFactory) ->
      # callback for ng-click 'createNewAddress':

      $scope.createNewAddress = ->
        AddressesFactory.create($scope.address).success ->
          $location.path '/list-address'
        return

      # callback for ng-click 'cancel':

      $scope.cancel = ->
        $location.path '/list-address'
        return

      $scope.findZip = ->
        ZipFactory.get($scope.address.zipcodeNumber)
          .success (data) ->
            $scope.address.street = data.address
            $scope.address.district = data.district
            $scope.address.state = data.uf
            $scope.address.complement = data.complement
            $scope.address.city = data.locality

        return

      return
]
