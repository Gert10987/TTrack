var startRout = null;
var endRout = null;
var currPosition  = null;

function myMap() {

    initValues();

    var directionsDisplay = new google.maps.DirectionsRenderer;
    var directionsService = new google.maps.DirectionsService;

    var map = new google.maps.Map(document.getElementById('map'), {

      zoom: 14,
      center: currPosition,

    });

    var marker = new google.maps.Marker({
            position: currPosition,
            map: map,
    });

    directionsDisplay.setMap(map);
    calculateAndDisplayRoute(directionsService, directionsDisplay);

//    google.maps.event.addListener(map, 'click', function(event) {
//        placeMarker(event.latLng);
//    });
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

function initValues(){


    var currentTaskJson = $('#currentTaskJson').val()
    var currentPosition = $('#employerCurrentPosition').val().split(",");
    var currentTask = parseJSON(currentTaskJson);

    var startLocation = currentTask.startOfRoutLocation.split(",");
    var endLocation = currentTask.endOfRoutLocation.split(",");

    startRout = {lat: parseFloat(startLocation[0]), lng: parseFloat(startLocation[1])};
    endRout = {lat: parseFloat(endLocation[0]), lng: parseFloat(endLocation[1])};
    currPosition = {lat: parseFloat(currentPosition[0]), lng: parseFloat(currentPosition[1])};

}



