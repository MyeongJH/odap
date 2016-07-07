var cbtn = document.querySelector( '.cbtn' );

var cbtnFront = cbtn.querySelector( '.cbtn-front' );
var cbtnYes = cbtn.querySelector( '.cbtn-back .yes' );
var cbtnNo = cbtn.querySelector( '.cbtn-back .no' );

cbtnFront.addEventListener( 'click', function( event ) {
  var mx = event.clientX - cbtn.offsetLeft;
  var my = event.clientY - cbtn.offsetTop;

  var w = cbtn.offsetWidth;
  var h = cbtn.offsetHeight;
	
  var directions = [
    { id: 'top', x: w/2, y: 0 },
    { id: 'right', x: w, y: h/2 },
    { id: 'bottom', x: w/2, y: h },
    { id: 'left', x: 0, y: h/2 }
  ];
  
  directions.sort( function( a, b ) {
    return distance( mx, my, a.x, a.y ) - distance( mx, my, b.x, b.y );
  } );
  
  cbtn.setAttribute( 'data-direction', directions.shift().id );
  cbtn.classList.add( 'is-open' );

} );

cbtnYes.addEventListener( 'click', function( event ) {	
  cbtn.classList.remove( 'is-open' );
} );

cbtnNo.addEventListener( 'click', function( event ) {
  cbtn.classList.remove( 'is-open' );
} );

function distance( x1, y1, x2, y2 ) {
  var dx = x1-x2;
  var dy = y1-y2;
  return Math.sqrt( dx*dx + dy*dy );
}