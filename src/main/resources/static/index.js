angular.module('app', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/app';

     $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET'
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

     $scope.filterTable = function (){
         $http({
             url: contextPath + '/products/cost',
             method: 'GET',
             params: {
                 minCost: $scope.filter ? $scope.filter.minCost : null,
                 maxCost: $scope.filter ? $scope.filter.maxCost : null
             }
         }).then(function (response) {
             $scope.ProductsList = response.data;
         });
     }
    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                // console.log('sended:');
                // console.log($scope.newProduct);
                // console.log('received');
                // console.log(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        console.log(id);
        $http.get(contextPath + '/products/delete/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.fillTable();
});