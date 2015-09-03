angular.module "project-test"
  .config ($routeProvider) ->
    $routeProvider
      .when "/",
        templateUrl: "app/main/main.html"
        controller: "MainController"
        controllerAs: "main"
      .otherwise
        redirectTo: "/"
    $routeProvider
      .when "/list-address",
        templateUrl: "app/address/list-address.html"
        controller: "AddressListController"
        controllerAs: "address"
      .otherwise
        redirectTo: "/"
    $routeProvider
      .when '/edit-address/:id',
        templateUrl: 'app/address/edit-address.html'
        controller: 'AddressDetailController'
      .otherwise
        redirectTo: "/"
    $routeProvider
      .when '/create-address',
        templateUrl: 'app/address/create-address.html'
        controller: 'AddressCreateController'
      .otherwise
        redirectTo: "/"
    $routeProvider.otherwise redirectTo: '/'