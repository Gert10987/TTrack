function myMap(var lat, var lng) {

var myLatLng = {lat: 52.2, lng: 21.01};

var mapCanvas = document.getElementById("map");
var mapOptions = {
    center: new google.maps.LatLng(52.2, 21.01), zoom: 10
    };
var map = new google.maps.Map(mapCanvas, mapOptions);

var marker = new google.maps.Marker({
          position: myLatLng,
          map: map,
          title: 'Hello World!'
        });

}
