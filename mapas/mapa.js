var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                    osmAttrib = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});

            var map = L.map('map').setView([-4.036, -79.201], 15);

            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map); + 
L.marker([-4.000909466384655, -79.20067374875437]).addTo(map)
.bindPopup('Miguel Riofrio')
.openPopup();L.marker([-4.000761536787824, -79.19850826224676]).addTo(map)
.bindPopup('18 de Noviembre')
.openPopup();L.marker([-4.009908, -79.2155779]).addTo(map)
.bindPopup('Pio Jaramillo ')
.openPopup();