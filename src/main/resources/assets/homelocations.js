"strict";

$(document).ready(
        function () {
            var map = initMap();
            var geocoder = new google.maps.Geocoder();

            //test data
            var homes;
                    /*= [
                {
                    "id": 1,
                    "address1": "Av. Mariano Otero 1105 Piso 4",
                    "address2": "Colonia Rinconada del Bosque",
                    "city": "Guadalajara",
                    "state": "",
                    "zipCode": "44530",
                    "country": "Mexico"
                },
                {
                    "id": 2,
                    "address1": "Luxoft Professional, LLC",
                    "address2": "1-y Volokolamskiy pr-d",
                    "city": "Moscow",
                    "state": "",
                    "zipCode": "10 123060",
                    "country": "Russia"
                },
                {
                    "id": 3,
                    "address1": "King Street West, 100",
                    "address2": "Suite 5600",
                    "city": "Toronto",
                    "state": "",
                    "zipCode": "M5X 1C9",
                    "country": "Canada"
                },
                {
                    "id": 4,
                    "address1": "Radishcheva Street 10/14",
                    "address2": "Business Center IRVA",
                    "city": "Kiev",
                    "state": "",
                    "zipCode": "03680",
                    "country": "Ukraine"
                },
                {
                    "id": 5,
                    "address1": "Excelian Limited",
                    "address2": "44 Featherstone Street",
                    "city": "London",
                    "state": "",
                    "zipCode": "EC1Y 8RN",
                    "country": "United Kingdom"
                }
            ];*/

            var homesPromise = getHomesPromise();
            
            homesPromise.always(
                    function(data) {
                        if (data) {
                            homes = data[0];
                        }
                        
                        homes.forEach(
                                function (element) {
                                    addMarker(geocoder, map, element);
                                }
                        );
                    }
            );
        }
);

/**
 * Initializes the google map
 */
function initMap() {
    var mapCanvas = document.getElementById("map");
    var mapOptions = {
        center: new google.maps.LatLng(0, 0),
        zoom: 2
    };

    var map = new google.maps.Map(mapCanvas, mapOptions);

    return map;
}

/**
 * Adds a marker to the map.
 * This funciton uses the Geocoder in order to search for an address.
 * 
 * @param {Object} geocoder The geocoder use to search the address.
 * @param {Object} map The map where the markers will be added.
 * @param {Object} address The Object with the info to search
 */
function addMarker(geocoder, map, address) {
    geocoder.geocode({'address': formatAddress(address)},
            function (results, status) {
                if (status === 'OK') {
                    var marker = new google.maps.Marker(
                            {
                                map: map,
                                position: results[0].geometry.location
                            }
                    );

                    google.maps.event.addListener(marker, 'click',
                            onMarkerClick.bind(
                                    {
                                        address: address,
                                        marker: marker,
                                        map: map
                                    }
                            )
                            );
                } else {
                    console.log('Geocode was not successful for the following reason: ' + status);
                }
            }
    );
}

/**
 * Event handler for click event on markers.
 * The function checks if there's already an info window, in this case, it
 * only opens it.
 * Otherwise it creates a new one and add it to the marker, so the next time
 * it's just opened.
 */
function onMarkerClick() {
    var infoWindow = this.marker["infoWindow"];

    if (!infoWindow) {
        infoWindow = new google.maps.InfoWindow(
                {
                    content: formatAddress(this.address, "<br/>")
                }
        );

        this.marker["infoWindow"] = infoWindow;
    }

    infoWindow.open(this.map, this.marker);
}

/**
 * Returns a String containing the address formated in 1 or multiple lines
 * depending on the lineSeparator parameter.
 * By default the separator is a space.
 * 
 * @param {Object} address An object containing fields like address1, zip code, etc.
 * @param {String} lineSeparator The separator to use between some fields.
 * @returns {String} The address formated as text.
 */
function formatAddress(address, lineSeparator) {
    var separator = lineSeparator || " ";
    return address["address1"] + separator +
            address["address2"] + separator +
            address["city"] + " " + address["state"] + " " + address["zipCode"] + separator +
            address["country"];
}

//returns the array with JSON home objects
function getHomesPromise() {
    return $.get("/api/homes");
}