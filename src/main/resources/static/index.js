angular.module('app', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/app/api/v1';

     $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET'
        }).then(function (response) {
            $scope.ProductsDTOList = response.data;
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
             $scope.ProductsDTOList = response.data;
         });
     }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.fillTable();
});