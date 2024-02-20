var nodes = new vis.DataSet([
{id: 1, label: " Pozo 1"},
{id: 2, label: " Pozo 2"},
{id: 3, label: " Pozo 3"},
{id: 4, label: " Pozo 4"},
{id: 5, label: " Pozo 5"},
{id: 6, label: " Pozo 6"},
{id: 7, label: " Pozo 7"},
{id: 8, label: " Pozo 8"},
{id: 9, label: " pozo 9"},
]);

var edges = new vis.DataSet([
{from: 1, to: 5, label: "0.33"},
{from: 1, to: 2, label: "0.11"},
{from: 2, to: 3, label: "0.22"},
{from: 2, to: 8, label: "0.67"},
{from: 3, to: 7, label: "0.45"},
{from: 3, to: 9, label: "0.23"},
{from: 4, to: 7, label: "0.34"},
{from: 4, to: 6, label: "0.23"},
{from: 5, to: 9, label: "0.04"},
{from: 5, to: 7, label: "0.24"},
{from: 6, to: 8, label: "0.12"},
{from: 6, to: 7, label: "0.12"},
{from: 7, to: 8, label: "0.0"},
{from: 7, to: 9, label: "0.26"},
{from: 8, to: 9, label: "0.26"},
]);

var container = document.getElementById("mynetwork");
      var data = {
        nodes: nodes,
        edges: edges,
      };
      var options = {};
      var network = new vis.Network(container, data, options);