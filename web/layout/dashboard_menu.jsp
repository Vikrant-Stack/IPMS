
<style>
@import url("//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");
 

.navbar-icon-top .navbar-nav .nav-link > .fa {
  position: relative;
  width: 36px;
  font-size: 24px;
}

.navbar-icon-top .navbar-nav .nav-link > .fa > .badge {
  font-size: 0.75rem;
  position: absolute;
  right: 0;
  font-family: sans-serif;
}

.navbar-icon-top .navbar-nav .nav-link > .fa {
  top: 3px;
  line-height: 12px;
}

.navbar-icon-top .navbar-nav .nav-link > .fa > .badge {
  top: -10px;
}

@media (min-width: 576px) {
  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }

  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }

  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}

@media (min-width: 768px) {
  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }

  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }

  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}

@media (min-width: 992px) {
  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }

  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }

  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}

@media (min-width: 1200px) {
  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }

  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }

  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}

/*.container {
	background-size: cover;
 background: rgb(226,226,226);  Old browsers 
  background: -moz-linear-gradient(top,  rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);  FF3.6+ 
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(226,226,226,1)), color-stop(50%,rgba(219,219,219,1)), color-stop(51%,rgba(209,209,209,1)), color-stop(100%,rgba(254,254,254,1)));  Chrome,Safari4+ 
  background: -webkit-linear-gradient(top,  rgba(226,226,226,1) 0%,rgba(219,219,219,1) 50%,rgba(209,209,209,1) 51%,rgba(254,254,254,1) 100%);  Chrome10+,Safari5.1+ 
  background: -o-linear-gradient(top,  rgba(226,226,226,1) 0%,rgba(219,219,219,1) 50%,rgba(209,209,209,1) 51%,rgba(254,254,254,1) 100%);  Opera 11.10+ 
  background: -ms-linear-gradient(top,  rgba(226,226,226,1) 0%,rgba(219,219,219,1) 50%,rgba(209,209,209,1) 51%,rgba(254,254,254,1) 100%);  IE10+ 
  background: linear-gradient(to bottom,  rgba(226,226,226,1) 0%,rgba(219,219,219,1) 50%,rgba(209,209,209,1) 51%,rgba(254,254,254,1) 100%);  W3C 
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e2e2e2', endColorstr='#fefefe',GradientType=0 );  IE6-9 
  padding: 20px;
}*/

.led-box {
  height: 30px;
  width: 25%;
  margin: 10px 0;
  float: next;
  
}
.fuel{
     height: 30px;
  width: 25%;
  margin: 10px 0;
  float: right;
    
}
.led-box  {
  font-size: 12px;
  text-align: center;
  margin: 1em;
}

.t1{
    align-content: center;
}




.INACTIVE {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #F00;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 12px;
  -webkit-animation: blinkRed 0.5s infinite;
  -moz-animation: blinkRed 0.5s infinite;
  -ms-animation: blinkRed 0.5s infinite;
  -o-animation: blinkRed 0.5s infinite;
  animation: blinkRed 0.5s infinite;
}

@-webkit-keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}
@-moz-keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}
@-ms-keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}
@-o-keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}
@keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}





.led-red {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #F00;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 12px;
  -webkit-animation: blinkRed 0.5s infinite;
  -moz-animation: blinkRed 0.5s infinite;
  -ms-animation: blinkRed 0.5s infinite;
  -o-animation: blinkRed 0.5s infinite;
  animation: blinkRed 0.5s infinite;
}

@-webkit-keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}
@-moz-keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}
@-ms-keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}
@-o-keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}
@keyframes blinkRed {
    from { background-color: #F00; }
    50% { background-color: #A00; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 0;}
    to { background-color: #F00; }
}

.IDEAL {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #FF0;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 12px;
  -webkit-animation: blinkYellow 1s infinite;
  -moz-animation: blinkYellow 1s infinite;
  -ms-animation: blinkYellow 1s infinite;
  -o-animation: blinkYellow 1s infinite;
  animation: blinkYellow 1s infinite;
}

@-webkit-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-moz-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-ms-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-o-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}


.led-yellow {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #FF0;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 12px;
  -webkit-animation: blinkYellow 1s infinite;
  -moz-animation: blinkYellow 1s infinite;
  -ms-animation: blinkYellow 1s infinite;
  -o-animation: blinkYellow 1s infinite;
  animation: blinkYellow 1s infinite;
}

@-webkit-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-moz-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-ms-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-o-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}

.led-green {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #ABFF00;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #304701 0 -1px 9px, #89FF00 0 2px 12px;
}

.ACTIVE {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #ABFF00;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #304701 0 -1px 9px, #89FF00 0 2px 12px;
}

.Running {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #3374FF;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #304701 0 -1px 9px,#3374FF  0 2px 12px;
}

.voltage {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #FC33FF;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #304701 0 -1px 9px, #89FF00 0 2px 12px;
}

.speed{
     margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #8033FF;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #304701 0 -1px 9px, #89FF00 0 2px 12px;
}
.GREY {
   margin: 0 auto; 
    width: 24px;
    height: 24px;
    background-color: #FFFFFF;
     border-radius: 50%;
     box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #304701 0 -1px 9px, 0 2px 12px;
}


.led-blue {
  margin: 0 auto;
  width: 24px;
  height: 24px;
  background-color: #24E0FF;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #006 0 -1px 9px, #3F8CFF 0 2px 14px;
 
}



 div.dm-wrapperDiv {
  display: block;
  position: relative;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  zoom: 1;
  overflow: hidden;
  width: 200px;
  height: 100px;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 16px;
  font-weight: bold;
  line-height: 1;
}
div.dm-wrapperDiv * {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  zoom: 1;
}
div.dm-wrapperDiv div {
  position: absolute;
  bottom: 0;
  left: 0;
}
div.dm-wrapperDiv div.dm-bgClrDiv,
div.dm-wrapperDiv div.dm-maskDiv {
  width: 200px;
  height: 100px;
}
div.dm-wrapperDiv div.dm-bgClrDiv,
div.dm-wrapperDiv div.dm-maskDiv {
  -webkit-border-radius: 100px 100px 0 0;
  -moz-border-radius: 100px 100px 0 0;
  -ms-border-radius: 100px 100px 0 0;
  border-radius: 100px 100px 0 0;
  border: 2px solid #999999;
  border-bottom: 0;
  -webkit-transform-origin: center bottom;
  -moz-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
}
div.dm-wrapperDiv div.dm-bgClrDiv.normal {
  background-color: #228b22;
}
div.dm-wrapperDiv div.dm-bgClrDiv.warn {
  background-color: #daa520;
}
div.dm-wrapperDiv div.dm-bgClrDiv.error {
  background-color: #ff0000;
}
div.dm-wrapperDiv div.dm-maskDiv {
  background-color: #dddddd;
  z-index: 4;
}
div.dm-wrapperDiv div.dm-innerDiv {
  bottom: 0;
  left: 20px;
  -webkit-border-radius: 80px 80px 0 0;
  -moz-border-radius: 80px 80px 0 0;
  -ms-border-radius: 80px 80px 0 0;
  border-radius: 80px 80px 0 0;
  border-bottom: 1px solid #eeeeee;
  width: 160px;
  height: 80px;
  padding-top: .7em;
  background-color: #eeeeee;
  text-align: center;
  text-transform: uppercase;
  z-index: 5;
}
div.dm-wrapperDiv div.dm-innerDiv p {
  margin: 3px auto;
  width: auto;
  max-width: 85%;
  overflow: hidden;
  text-align: center;
}
div.dm-wrapperDiv div.dm-innerDiv p.dm-valueP {
  font-size: 210%;
  line-height: .85;
}
div.dm-wrapperDiv div.dm-innerDiv p.dm-unitP {
  margin-top: 0;
  font-size: 75%;
  font-weight: normal;
}
div.dm-wrapperDiv div.dm-innerDiv p.dm-labelP {
  font-size: 100%;
}
    
input[type=text], select {
  width: 20%;
   align-content: center;
  padding: 12px 10px;
  margin: 4px 0;
  display: inline-block;
 
 
}
</style>

<script>
    
    
     var $myFuelGauge;
   
    $(function () {
    
      $myFuelGauge = $("div#fuel-gauge").dynameter({
  
                     
        width: 200,
    
        label:'fuel',
    
        value:  hello(),
    
        min: 0.0,
    
        max: 100.0,
    
        unit:'litre',
    
        regions: {// Value-keys and color-refs
    
          0:'error',
    
          .5:'warn',
    
          1.5:'normal'
   
        }
    
      });
    
     
    
      // jQuery UI slider widget
    
      $('div#fuel-gauge-control').slider({
    
        min: 0.0,
    
        max: 15.0,
    
        value: 7.5,
    
        step: .1,
    
        slide:function (evt, ui) {
    
          $myFuelGauge.changeValue((ui.value).toFixed(1));
    
        }
    
      });
    
     
    
    });
            
            
        (function ( $ ) {
    	 
        $.fn.dynameter = function ( options ) {
            var defaults = {
                label: 'DynaMeter',
                value: 50,
                min: 0,
                max: 100,
                regions: {  // Value-keys and color-refs.  E.g., value: 'normal'|'warn|'error', etc.
                    0: 'normal'
                }
            };

            var settings = $.extend({}, defaults, options);

            settings._range = settings.max - settings.min;
            settings._clrRef0 = 'normal';
            settings._clrRef1 = 'warn';
            settings._clrRef2 = 'error';

            this.changeValue =  function ( myVal )  { 
                var $this = $(this);
                var myMin = $this.data('dm-min');
                var myMax = $this.data('dm-max');
                var myRange = $this.data('dm-range');
                // Update value text.
                $this.find('.dm-innerDiv .dm-valueP').html(myVal);
                // Ensure value is in-range.
                if (myVal < myMin) {
                    myVal = myMin;
                }
                if (myVal > myMax) {
                    myVal = myMax;
                }
                // Rotate mask div.
                var myRelVal = myVal - myMin;
                var myDeg = myRelVal / myRange * 180;
                $this.find('.dm-maskDiv').css({
                    '-webkit-transform': 'rotate(' + myDeg + 'deg)',
                    '-moz-transform': 'rotate(' + myDeg + 'deg)',
                    '-ms-transform': 'rotate(' + myDeg + 'deg)',
                    'border-radius': 'rotate(' + myDeg + 'deg)'
                });
                // Set/update dm-value attr.
                $this.data('dm-value', myVal);
                // console.log('[dynameter.changeValue] Method called.  myVal = ' + myVal);
            };

            // Greenify the collection based on the settings variable.
            return this.each(function () {
                var $this = $(this);  // Div that's getting DynaMeter-ized.

                function updateValue (myVal) {
                    var myMin = $this.data('dm-min');
                    var myRange = $this.data('dm-range');
                    // Update value text.
                    $this.find('.dm-innerDiv .dm-valueP').html(myVal);
                    // Rotate mask div.
                    var myRelVal = myVal - myMin;
                    var myDeg = myRelVal / myRange * 180;
                    $this.find('.dm-maskDiv').css({
                        '-webkit-transform': 'rotate(' + myDeg + 'deg)',
                        '-ms-transform': 'rotate(' + myDeg + 'deg)',
                        '-moz-transform': 'rotate(' + myDeg + 'deg)',
                        'border-radius': 'rotate(' + myDeg + 'deg)'
                    });
                    // Set/update dm-value attr.
                    $this.data('dm-value', myVal);
                }

                // Initialize once.
                if (!$this.hasClass('dm-wrapperDiv')) {
                    // Skip init if settings are invalid.
                    if (settings.value < settings.min ||
                        settings.value > settings.max ||
                        settings.min >= settings.max) {
                        throw new Error("DynaMeter initialization failed -- invalid value/min/max settings.");
                    }
                    var currClrRef;
                    for (var key in settings.regions) {
                        currClrRef = settings.regions[key];
                        if (currClrRef != settings._clrRef0 &&
                            currClrRef != settings._clrRef1 &&
                            currClrRef != settings._clrRef2) {
                            throw new Error("DynaMeter initialization failed -- invalid region color-key.");
                        }
                        if (key < settings.min || key > settings.max) {
                            throw new Error("DynaMeter initialization failed -- invalid region value.");
                        }
                    }

                    $this.addClass('dm-wrapperDiv');
                    $this.data('dm-id', ('dm-' + new Date().getTime()));
                    $this.data('dm-min', settings.min);
                    $this.data('dm-max', settings.max);
                    $this.data('dm-range', settings.max - settings.min);

                    $this.html('');
                    $this.append('<div class="dm-maskDiv"></div>');
                    $this.append('<div class="dm-innerDiv"></div>');

                    var $bgDiv = $this.find('div.dm-bgDiv');
                    var $maskDiv = $this.find('div.dm-maskDiv');
                    var $innerDiv = $this.find('div.dm-innerDiv');

                    $innerDiv.append('<p class="dm-valueP">' + settings.value + '</p>');
                    if (settings.unit) {
                        $innerDiv.append('<p class="dm-unitP">' + settings.unit + '</p>');
                    }
                    $innerDiv.append('<p class="dm-labelP">' + settings.label + '</p>');

                    var $valueP = $this.find('p.dm-valueP');
                    var $unitP = $this.find('p.dm-unitP');
                    var $labelP = $this.find('p.dm-labelP');

                    var getAngleFromValue = function (myVal) {
                        // Returns angle for canvas arc color-stops.
                        if (myVal < settings.min || myVal > settings.max) {
                            // console.log('[dynameter.getAngleFromValue] ERROR: myValue is outside value range!');
                            return null;
                        }
                        return parseInt((myVal - $this.data('dm-min')) / $this.data('dm-range') * 180);
                    };

                    // Color stops for indicator color-bands [[angle, color-reference],...].
                    var colorStops = [];  // Color-ref by angle, based on settings.regions.
                    var keyIdx = 0;
                    for (var key2 in settings.regions) {
                        // If there's no min-value starting region, prepend one using 'normal' as color-ref.
                        if (keyIdx === 0 && key2 > settings.min) {
                            colorStops.push([getAngleFromValue(settings.min), 'normal']);
                            keyIdx++;
                            // If starting region is still "normal" w/ non-min-value, skip this key.
                            if (settings.regions[key2] == 'normal') {
                                continue;
                            }
                        }
                        colorStops.push([getAngleFromValue(key2), settings.regions[key2]]);
                        keyIdx++;
                    }
                    var colorStopsLength = colorStops.length;

                    // Create and rotate color-bands for indicator background.
                    for (var i = 0; i < colorStopsLength; i++) {
                        var myAngle = colorStops[i][0];
                        var myClrRef = colorStops[i][1];
                        $(document.createElement('div'))
                            .addClass('dm-bgClrDiv ' + myClrRef)
                            .css({
                                '-webkit-transform': 'rotate(' + myAngle + 'deg)',
                                '-moz-transform': 'rotate(' + myAngle + 'deg)',
                                '-ms-transform': 'rotate(' + myAngle + 'deg)',
                                'transform': 'rotate(' + myAngle + 'deg)',
                                'zIndex': i + 1
                            })
                            .prependTo($this);
                    }

                    console.log('[dynameter] div#' + $this.attr('id') + ' initialized.');

                }

                updateValue(settings.value);

            });


     
        };
     
    }( jQuery ));    
            
            
            
            function hello()
            {
               
                
                var fuel=document.getElementById("indicator").value; 
               
                return fuel;
            }
           
    function excep(id)
            {
              
                
                var url="Livefuel?"+id;
               
                return url;
            }
   
    
   $( function() {
  var $winHeight = $( window ).height()
  $( '.container' ).height( $winHeight );
}); 


var myVar = document.getElementById("status").value;
document.body.className = (myVar == "ACTIVE" ? "active" : "normal");



 $(function() {  
                    
                      $("#vehicle_code").autocomplete({
                    
                source: function (request, response) {
                debugger;
                $.ajax({
                    url: "VehicleKeyPersonController",
                    dataType: "json",
                    data: {action1: "getVehicleCode"},
                    success: function (data) {

                        console.log(data);
                        response(data.list);
                    }, error: function (error) {
                        console.log(error.responseText);
                        response(error.responseText);
                    }
                });
            },
            select: function (events, ui) {
                console.log(ui);
                $('#vehicle_code').val(ui.item.label); // display the selected text
                return false;
            }
        }); 
    }); 


</script>
    
  



<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">DashBoard</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item ">
        <a class="nav-link" href="index">
          <i class="fa fa-home"></i>
          Home
          <span class="sr-only">(current)</span>
          </a>
      </li>
   

     
      <li class="nav-item">
        <a class="nav-link " href="#">
          <i class="fa fa-envelope-o">
            <span class="badge badge-warning"></span>
          </i>
          Report
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link " href="vehicleKeypersonLiveloc?task=getLatestStatusDash" target="_blank">
          <i class="fa fa-map-marker ">
            <span class="badge badge-warning"></span>
          </i>
          Map
        </a>
        
      </li>
      
    </ul>
      </div>
    </nav>
  
  
                                            
<!--                                                    <form name="form1" class="form-group row" method="POST" action="VehicleKeyPersonController" style=" width: 50%; margin-left:500px;">-->
                                                    <table  align="center"  class="table table-bordered table-responsive"   align="center" >
                                                        <tr>
                                                           
                                                            <td><input type="text" class="form-control" id="vehicle" name="vehicle" size="1000" value="${vehicle_no}" disabled>
                                                            
                                                           
                                                            <input type="text" class="form-control" id="key_person" name="key_person" size="1000" value="${key_person}" disabled >
                                                          
                                                            
                                                           
                                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                           <strong>    Vehicle No : </strong><input type="text" class="form-control" id="vehicle" name="vehicle" size="19" value="" >
                                                            <input class="btn btn-primary" type="submit" name="task" id="searchInOrgSubType" value="Search">
                                                           <input class="btn btn-success" type="submit" name="task" id="showAllRecords" value="Show All Records">
                                                           </td>
                                                       
<!--                                                        <td colspan="5" align="center">-->
<!--<td>   <input class="btn btn-danger" type="button" id="viewPdf" name="viewPdf" value="PDF" onclick="displayOrgSubTypeList(id);"/></td>
<td>    <input class="btn btn-info" type="button" id="viewXls" name="viewXls" value="Excel" onclick="displayOrgSubTypeList(id);"/>-->
                                                           
                                                 
                                                        </tr>
                                                    </table>
<!--                                                    </form>-->
                                             
<form action="Livefuel">   
       <table  class="table table-bordered   table-striped"  align="center" cellpadding="0" cellspacing="0">
                                
                                <tr><input class="input" type="hidden" id="device_vehicle_mapping_id" name="device_vehicle_mapping_id" value="" ></tr>

                               
                                <tr style="margin-left:50%;">

                                   <th width="25%"  class="heading1" style="text-align:center;"><strong>Fuel Level:</strong</th>
                                    
                                    <td align="center" width="35%">    <input align="center" type="text" class="form-control" id="fuel_level" size="19" name="fuel_level" value="${fuel_level}" disabled>
                                     
                                   <input align="center" type="text" class="form-control" id="fuel_level" size="19" name="fuel_level" value="Litres" disabled></td>
                                   
                             
                                  </td>
                                </tr>
                                
                                
                                 <tr style="margin-left:50%;">

                                   
                                 
                                     <th width="25%"  class="heading1" style="text-align:center;"><strong>Voltage:</strong</th>
                                <td align="center" width="35%">       <input align="center" type="text" class="form-control" id="voltage" size="19" name="voltage" value="${voltage}" disabled>
                                     <input align="center" type="text" class="form-control" id="voltage" size="19" name="voltage" value="Volts" disabled>
                                  </td>
                                </tr>
                                
                                
                                 <tr style="margin-left:50%;">

                                   
                                 
                                     <th width="25%"  class="heading1" style="text-align:center;"><strong>Exception:</strong</th>
                                     <td align="center" width="35%"><input align="center" type="submit" class="btn btn-success" value="Exception" id="exception" size="60" name="exception">
                                   
                                  </td>
                                </tr>
                                
                                
                                
                                
                                
                                <tr>
                                    <th rowspan="2"  class="heading1" style="text-align:center;"><strong>Vehicle Status</strong></th>
                                          <td>
                                               <p align="center"><strong>ON</strong></p>
                                         </td>
                                          <td>
                                              <p align="center"><strong>OFF</strong></p>
                                          </td>
                                           <td>
                                              <p align="center"><strong>IDLE</strong></p>
                                          </td>
                                           <td>
                                              <p align="center"><strong>Running</strong></p>
                                          </td>
                                          
<!--                                          <td>
                                               <p align="center"><strong>INACTIVE</strong></p>
                                          </td>-->
                                      </tr> 
                                      
                                      <tr>
                                          
                                          <td>
                                              <div class="${led1} led-box"></div>

                                          </td>
                                          <td>
                                              <div class="${led2} led-box"></div>
                                          </td>
                                          <td>
                                              <div class="${led3} led-box"></div>
                                          </td>
                                           <td>
                                              <div class="${led4} led-box"></div>
                                          </td>
                                          
                                      </tr>
<!-- <tr>

                                    <th class="heading1"> Vehicle Status</th>
                                    <td><input type="text" class="form-control" id="fuel_level" size="19" name="fuel_level" value="${status}" disabled></td>

                                </tr>-->

             </table>              
                      
</form>

                        

                               
                               
                                
                              
                               



                              
<!--                                    <div id="fuel-gauge" align="center" class="container" onload="hello()">
                                       <input type="hidden" class="form-control" id="indicator" size="19" name="fuel_level" value="${indicator}" disabled>  
                                    </div>  
                                    
                                <div class="container">-->
<div class="row">
 
     
                                
                                
                                  <table  class="table table-bordered " style="" >    
                                       
                                      
    
                             
 
                                  </table>
                                    


</div>
      
                                         
  <div id="fuel-gauge" class="fuel"  onload="hello()"  style=" width: 40%;margin-right:700px;margin-bottom:500px;margin-top:100px;">
      
                                       <input type="hidden" class="form-control" id="indicator" size="19" name="fuel_level" value="${indicator}" disabled>  
                                    </div> 
