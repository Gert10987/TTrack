function myMap() {

var mapCanvas = document.getElementById("map");
var mapOptions = {
    center: new google.maps.LatLng(52.2, 21.01), zoom: 10
    };
var map = new google.maps.Map(mapCanvas, mapOptions);

}
