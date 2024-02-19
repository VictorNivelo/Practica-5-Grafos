var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                    osmAttrib = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});

            var map = L.map('map').setView([-4.036, -79.201], 15);

            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
L.marker([-4.036, -79.201]).addTo(map)
.bindPopup("Pozo 1")
.openPopup();
L.marker([-4.032, -79.19]).addTo(map)
.bindPopup("Pozo 2")
.openPopup();
L.marker([-4.032633806543791, -79.20247866449027]).addTo(map)
.bindPopup("Pc")
.openPopup();
