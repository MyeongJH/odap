var qno = location.href.split("=")[1];
(function() {
  var App;
  App = {};
  
  /*
  	Init 
  */
  App.init = function() {
    App.canvas = document.createElement('canvas');
//    App.canvas.height = window.innerHeight;
//    App.canvas.width = window.innerWidth;
    App.canvas.height = 705;
    App.canvas.width = 915;
    document.getElementsByTagName('article')[0].appendChild(App.canvas);
    App.ctx = App.canvas.getContext("2d");
    App.ctx.fillStyle = "rgba(0,0,0,0)";
    App.ctx.fillRect(0,0,App.canvas.width,App.canvas.height);
    App.ctx.strokeStyle = selectedColor;
    App.ctx.lineCap = "round";
    App.socket = io.connect('http://192.168.0.54:4000');
    App.socket.on('connect', function () {    	
    	App.socket.emit('joinRoom','room'+qno);
    	//console.log(qno);
    	//console.log(typeof qno);
    	App.socket.on('msgAlert',function(data){
            alert(data);
        });
    });
    App.socket.on('draw', function(data) {
      //console.log(data);
      return App.draw(data.x, data.y, data.type, data.color, data.line);	
    });
    App.draw = function(x, y, type,color,line) {
    	//console.log(color);
      if (type === "dragstart") {
        App.ctx.beginPath();
        return App.ctx.moveTo(x, y);
      } else if (type === "drag") {
    	App.ctx.strokeStyle = color;
    	App.ctx.lineWidth = line;
        App.ctx.lineTo(x, y);
        return App.ctx.stroke();
      } else {
        return App.ctx.closePath();
      }
    };
  };
  
  /*
  	Draw Events
  */
  $('canvas').live('drag dragstart dragend', function(e) {
    var offset, type, x, y;
    type = e.handleObj.type;
    offset = $(this).offset();
    App.ctx = App.canvas.getContext("2d");
    App.ctx.fillStyle = "rgba(0,0,0,0)";
    App.ctx.fillRect(0,0,App.canvas.width,App.canvas.height);
    App.ctx.strokeStyle = selectedColor;
    App.ctx.lineWidth = selectedThickness;
    App.ctx.lineCap = "round";
    e.offsetX = e.layerX;
    e.offsetY = e.layerY*1.08;
    x = e.offsetX;
    y = e.offsetY;
    App.draw(x, y, type,selectedColor);
    App.socket.emit('drawClick', {
      x: x,
      y: y,
      type: type,
      color: selectedColor,
      lineWidth: selectedThickness
    });
  });
  $(function() {
    return App.init();
  });
}).call(this);
