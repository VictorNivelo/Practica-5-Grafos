var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                    osmAttrib = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});

            var map = L.map('map').setView([-4.036, -79.201], 15);

            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
L.marker([-4.0326, -79.202]).addTo(map)
.bindPopup("Pozo 1")
.openPopup();
L.marker([-4.0327, -79.201]).addTo(map)
.bindPopup("Pozo 2")
.openPopup();
L.marker([-4.0328, -79.203]).addTo(map)
.bindPopup("Pozo 3")
.openPopup();
L.marker([-4.0329, -79.204]).addTo(map)
.bindPopup("Pozo 4")
.openPopup();
L.marker([-4.033, -79.205]).addTo(map)
.bindPopup("Pozo 5")
.openPopup();
L.marker([-4.031, -79.206]).addTo(map)
.bindPopup("Pozo 6")
.openPopup();
L.marker([-4.029, -79.207]).addTo(map)
.bindPopup("Pozo 7")
.openPopup();
L.marker([-4.029, -79.207]).addTo(map)
.bindPopup("Pozo 8")
.openPopup();
