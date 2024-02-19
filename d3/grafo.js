var nodes = new vis.DataSet([
{id: 1, label: " Pozo 1"},
{id: 2, label: " Pozo 2"},
]);

var edges = new vis.DataSet([
{from: 1, to: 2, label: "0.35"},
]);

var container = document.getElementById("mynetwork");
      var data = {
        nodes: nodes,
        edges: edges,
      };
      var options = {};
      var network = new vis.Network(container, data, options);