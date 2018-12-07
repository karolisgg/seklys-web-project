$(function () {
    //Creates map
    var map = L.map('map').setView([55.616, 21.135], 11);
    L.mapboxGL({
        attribution: '<a href="https://www.maptiler.com/license/maps/" target="_blank">© MapTiler</a> <a href="https://www.openstreetmap.org/copyright" target="_blank">© OpenStreetMap contributors</a>',
        accessToken: 'not-needed',
        style: 'https://maps.tilehosting.com/styles/bright/style.json?key=Ss7bP3slPwuWrAi6zhjf'
    }).addTo(map);

    // Loads all devices with last coordinates, creates markers and adds to markerMap
    var markerMap = new Map();
    $.ajax({
        url: ctx + "/api/get_last_coordinates",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            $.each(data, function (index, value) {
                var marker = L.marker([value.latitude, value.longitude]).addTo(map);
                var date = new Date(value.dateTime);
                marker.bindTooltip("<b>" + value.device.deviceId + " " + value.device.name + "</b><br/> Last seen: " + date.toLocaleString('en-GB')).openTooltip();
                markerMap.set(value.device.deviceId, marker);
            });
        }
    });

    // Updates all markers location every 10sec
    setInterval(updateMarkers, 10000);
    function updateMarkers() {
        $.ajax({
            url: ctx + "/api/get_last_coordinates",
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                $.each(data, function (index, value) {
                    var date = new Date(value.dateTime);
                    markerMap.get(value.device.deviceId).setTooltipContent("<b>" + value.device.deviceId + " " + value.device.name + "</b><br/> Last seen: " + date.toLocaleString('en-GB'));
                    markerMap.get(value.device.deviceId).setLatLng(new L.LatLng(value.latitude, value.longitude));
                });
            }
        });
    }

    // Show/hide selected devices marker, if show then go to marker
    $('.deviceBtn').on('click', function () {
        var deviceId = $(this).data("deviceId");
        if ($(this).hasClass("active")) {
            markerMap.get(deviceId).addTo(map);
            markerMap.get(deviceId).openTooltip();
            map.panTo(markerMap.get(deviceId).getLatLng());
        } else {
            markerMap.get(deviceId).remove();
        }
    });

    // Finds coordinates by deviceId and date, draws polylines
    var polyline = L.polyline([], {color: 'red'});
    $('#devicePathForm').on('submit', function (e) {
        e.preventDefault();
        var devId = $('#deviceSelect').val();
        var startDate = $('#result1').val();
        var endDate = $('#result2').val();
        $.ajax({
            url: ctx + "/api/get_by_time",
            type: "GET",
            dataType: "JSON",
            data: {deviceId : devId, fromDate : startDate, toDate : endDate},
            success: function (data) {
                var latlngs = [data.length];
                $.each(data, function (index, value) {
                    latlngs[index] = [value.latitude, value.longitude];
                });
                polyline.bindTooltip(data[1].device.deviceId + " " + data[1].device.name);
                polyline.setLatLngs(latlngs);
                polyline.addTo(map);
                polyline.openTooltip();
                map.panTo(polyline.getCenter());
            }
        });
    });

    // Creates date time picker
    $('#picker1').dateTimePicker({
        selectData: "2018-11-20 10:00",
        positionShift: {top: -150, left: 250},
        title: "Select Start Date"
    });
    $('#picker2').dateTimePicker({
        selectData: "2018-11-20 10:00",
        positionShift: {top: -150, left: 250},
        title: "Select End Date"
    });
});