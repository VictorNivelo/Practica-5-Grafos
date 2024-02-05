var nodes = [{id: 1, label: 'V1'},{id: 2, label: 'V2'},{id: 3, label: 'V3'},];
var edges = [];
var container = document.getElementById("mynetwork");
var data = {
  nodes: nodes,
  edges: edges,
};
var options = {};
var network = new vis.Network(container, data, options);