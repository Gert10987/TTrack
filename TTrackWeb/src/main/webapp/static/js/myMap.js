var startRout = {lat: 37.77, lng: -122.447};
var endRout = {lat: 37.768, lng: -122.511};
var currentPosition = {lat: 37.760, lng: -122.447};

function myMap() {

    var directionsDisplay = new google.maps.DirectionsRenderer;
    var directionsService = new google.maps.DirectionsService;

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 14,
      center: currentPosition,
    });

    directionsDisplay.setMap(map);
    calculateAndDisplayRoute(directionsService, directionsDisplay);

    var marker = new google.maps.Marker({
        position: currentPosition,
        map: map
    });

    google.maps.event.addListener(marker, 'rightclick', (function(marker, i) {

              return function() {
                marker.setMap(null);
              }
            })(marker, i));

    google.maps.event.addListener(map, 'click', function(event) {
        placeMarker(event.latLng);
    });
}

function placeMarker(location) {

    var marker = new google.maps.Marker({
        position: location,
        map: map
    });
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
    directionsService.route({
    origin: startRout,
    destination: endRout,
    travelMode: google.maps.TravelMode['DRIVING']
    }, function(response, status) {
            if (status == 'OK') {
            directionsDisplay.setDirections(response);
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
}

