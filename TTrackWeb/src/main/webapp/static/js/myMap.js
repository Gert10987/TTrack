var currentTask = null;
var startLocation = null;
var endLocation = null;

function myMap() {

    var currentTaskJson = $('#currentTask');
        currentTask = parseJSON(currentTaskJson.val());
    var startLocation = currentTask.startOfRoutLocation.split("-");
    var endLocation = currentTask.endOfRoutLocation.split("-");

    var startRout = {lat: startLocation[0], lng: startLocation[1]};
    var endRout = {lat: endLocation[0], lng: endLocation[1]};
    var currentPosition = {lat: 57.00, lng: 122.00};

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

function parseJSON(data) {
    return window.JSON && window.JSON.parse ? window.JSON.parse( data ) : (new Function("return " + data))();
}

