
define([
		
"dojo/_base/declare",
"ecm/widget/dialog/BaseDialog",
"dijit/_TemplatedMixin",
"dijit/_WidgetBase",
"dojo/dom-style",
"dojo/on",
"dojo/dom",
"dijit/registry",
"dojo/data/ObjectStore",
"dojox/collections/ArrayList",
"dijit/form/CheckBox",
"dijit/form/TextBox",
"dojo/store/Memory", "dijit/form/ComboBox",
"dijit/_WidgetsInTemplateMixin",
"dojo/text!printFaxMainPluginDojo/templates/PrintFaxplugin.html",
"dojo/domReady!"
	],
	function(declare,BaseDialog,_TemplatedMixin,_WidgetBase,domStyle,on,dom,registry,ObjectStore,ArrayList,CheckBox,TextBox,Memory,ComboBox,_WidgetsInTemplateMixin,template) {
     var th;
     var checkbox1;
     var checkboxvalue;
 	
		return declare("printFaxMainPluginDojo.OpeningDialogbox", [_WidgetBase,_TemplatedMixin,BaseDialog,_WidgetsInTemplateMixin], {
		
			contentString: template,
			widgetsInTemplate: true,
			
			

		postCreate:function(){
			
			
			
			console.log("Hi I am in action js2222222222222222",th);
			
			console.log("Hi I am in action js2222222222222222",this);
			
			
			th=this;
			
			count=0;
			
			
			  this.inherited(arguments);
			
		},
		onHide:function(){

			
			console.log("in destroy")
			  this.destroyDescendants();

			 },
		
		 show: function(items, repository) {
			 
		
             this.inherited(arguments);
             this._widgetList = [];
             
             var PrinterDiv = this.requesttype ; 

          console.log("11111",PrinterDiv)
         

          
          
          
          
          
          var printerstore = new Memory({
              data: [
                  {name:"Microsoft XPS Document Writer", id:"printrequest"},
                  {name:"FaxDev", id:"faxtype"},
                 
              ]
          }); 
             
          var comboBox = new ComboBox({
              name: "print/fax Name",
              value: "Microsoft XPS Document Writer",
              store: printerstore,
              
              searchAttr: "name"
          }, PrinterDiv); 
             
          
     
          domStyle.set(th.faxoptiondiv,"display", "none");
          
          on(comboBox, "change", function(typeofrequest){
				 
				 console.log("typeofrequest:::::",typeofrequest);
				 
				 var typerequest = typeofrequest;
				 
				 console.log("activityObj.oc:::::",typerequest);
				 
				 
				 if(typerequest =="FaxDev")
					 {
				
				 
				 domStyle.set(th.faxoptiondiv,"display", "");
				 
				  
				 
				 domStyle.set(th.printoptiondiv,"display", "none");
				 
				 console.log("displayed nothing");
					 }else{
						 

						 domStyle.set(th.faxoptiondiv,"display", "none");
						 
						 
						 domStyle.set(th.printoptiondiv,"display", "");
						 
						 
					 }
				 
				 
				 
					 
			 });
          
          
          
          var papersizetype= this.papersize;
			 
			 console.log("in papersize",papersizetype)
			 
			 var papersizestore = new Memory({
	              data: [
	                  {name:"Global Default", id:"Defaultpapersize"},
	                  {name:"Letter(8.5\"*11\")", id:"letter"},
	                 
	              ]
	          }); 
	             
	          var papersizecombobox = new ComboBox({
	              name: "Papersize values",
	              value: "Global Default",
	              store: papersizestore,
	              searchAttr: "name"
	          }, papersizetype); 
	          
	          
	          var Quality = this.quality ; 
			   
			   console.log("in quality",Quality)
			   
			   
			  var qualitystore = new Memory({
	              data: [
	                  {name:"Normal 240dpi\*98dpi", id:"normal"},
	                  {name:"fine 204\*196", id:"fine"},
	                 
	              ]
	          }); 
	             
	          var qualitycombobox = new ComboBox({
	              name: "Quality",
	              value: "Normal 240dpi*98dpi",
	              store: qualitystore
	              
	              
	          }, Quality); 
	          
	          
	          
	          var Ejecttray = this.ejecttray ; 
			   
			   console.log("in Ejecttray",Ejecttray)
			   
			   
			  var ejecttraystore = new Memory({
	              data: [
	                     {name:"Default", id:"default"},
	                  {name:"Upper", id:"upper"},
	                  {name:"Upperoffset", id:"upperoffset"},
	                  {name:"Lower", id:"lower"},
	                  {name:"Loweroffset", id:"loweroffset"},
	                 
	              ]
	          }); 
	             
	          var ejecttraycombobox = new ComboBox({
	              name: "Eject Tray",
	              value: "Default",
	              store: ejecttraystore
	              
	              
	          }, Ejecttray); 
	          
	       
	          
	          
	          var Orientation = this.orientation ; 
			   
			   console.log("in Orientation",Orientation)
			   
			   
			  var orientationstore = new Memory({
	              data: [
	                  {name:"Default", id:"Default"},
	                  {name:"Portrait", id:"Portrait"},
	                  {name:"LandScape", id:"LandScape"},
	                  {name:"No Rotate", id:"noroatate"},
	                 
	              ]
	          }); 
	             
	          var ejecttraycombobox = new ComboBox({
	              name: "Orientation",
	              value: "Default",
	              store: orientationstore
	              
	              
	          }, Orientation); 
	          
	          var Scaling = this.scaling ; 
			   
			   console.log("in Scaling",Scaling)
			   
			   
			  var Scalingstore = new Memory({
	              data: [
	                  {name:"Default", id:"Default"},
	                  {name:"Clip Both", id:"ClipBoth"},
	                  {name:"Exact", id:"Exact"},
	                  {name:"Approximate", id:"Approximate"},
	                  {name:"Original", id:"Original"},
	                  {name:"Center", id:"Center"},
	                  {name:"Enhanced Exact", id:"Enhancedexact"},
	                 
	              ]
	          }); 
	             
	          var saclingcombobox = new ComboBox({
	              name: "Scaling",
	              value: "Default",
	              store: Scalingstore
	              
	              
	          }, Scaling); 
	          
	          
	          var Copies = this.copies ; 
			   
			   console.log("in Copies",Copies)
			   
			   
			  var copiesstore = new Memory({
	              data: [
	                  {name:"1", id:"1"},
	                  {name:"2", id:"2"},
	                  {name:"3", id:"3"},
	                  {name:"4", id:"4"},
	                 
	                 
	              ]
	          }); 
	             
	          var copiescombobox = new ComboBox({
	        	id:"copiescombobox",
	              name: "Copies",
	              value: "1",
	              store: copiesstore
	              
	              
	          }, Copies); 
	          
	          var Priority = this.priority ; 
			   
			   console.log("in Priority",Priority)
			   
			   
			  var prioritystore = new Memory({
	              data: [
	                  {name:"Low", id:"low"},
	                  {name:"Normal", id:"normal"},
	                  {name:"High", id:"high"},
	                
	                 
	                 
	              ]
	          }); 
	             
	          var prioritycombobox = new ComboBox({
	              name: "Copies",
	              value: "Normal",
	              store: prioritystore
	              
	              
	          }, Priority); 
	          
	          
	          
	          
	          
			 
          
          
	          var documentgrid=this.gridid;
	          
	          console.log("documentgrid",documentgrid);
	          
	          
	          
	          
	          var documentsmemory = new Memory({
	              data: [ 
	                     {name:"Demo Test Batch", id:"document"},
	                     {name:"Demo Test Batch", id:"document"},
	                     {name:"Demo Test Batch", id:"document"},
	                 
	                 
	              ]
	          }); 
	          
	          
	          var ObjStore = new ObjectStore({
					objectStore : documentsmemory
				});
	             
	          var layout = [{
					name : 'S.No',
					field : 'id',
					'width' : '100px',
					 styles : "text-align: center;"
				}, {
					name : 'Name',
					field : 'property',
					'width' : '240px',
					 styles : "text-align: center;"
				}, {
					name : 'All Pages',
					field : 'property',
					'width' : '100px',
					 styles : "text-align: center;",
					 'formatter': function(data){
		                    checkbox1 = new CheckBox({
		                    	id:checkboxvalue+count++,
		                    	
		                    	name:"allpagesvalue",
		                    	label: "checkbox",
		                    	checked: true,
		                    	disabled:true,
		                        onClick: function() {
		                       console.log("in checkbox");
		                        }
		                    });
		                    
		                    
		                    
		                  
		                   console.log("ID Values:::::" , checkbox1);
		                    
		                    return checkbox1;
		                   
		                   
		                }
				
				
				
			
					 
				},{
					name: 'Start Page',
					'field' : 'classdescription',
					'width' : '110px',
					 styles : "text-align: center;",
					'formatter': function(data){
	                  var myTextBox  = new TextBox({
	                    	
	                    	label: "TextBox",
	                    	style: {width: '30%',height:'15px'},
	                    	  value: "1",
	                    	  styles : "text-align: center;",
	                    	  disabled:true,
	                        onClick: function() {
	                       console.log("in TextBox");
	                        }
	                    });
	                    
	                    return myTextBox;
	                   
	                }
						
						
				}, 
				
				
				
				
				{
					name : 'last Page',
					field : 'classdescription',
					'width' : '110px',
						 styles : "text-align: center;",
						 'formatter': function(data){
			                    var lastTextBox  = new TextBox({
			                    	label: "lastTextBox",
			                    	  value: "9999",
			                    	  disabled:true,
			                    	style: {width: '30%',height:'15px'},
			                        onClick: function() {
			                       console.log("in lastTextBox");
			                        }
			                    });
			                    
			                    return lastTextBox;
			                   
			                }
				}
				] ;
	          
	          console.log("layout",layout);
	          
	          var grid = new dojox.grid.EnhancedGrid({
			
					store : ObjStore,
					structure : layout,
					  autoHeight:true,
				       autoWidth:true
					
				},documentgrid);
	          grid.startup();
	         
	          console.log("end of gridgrid",grid);
         },
         
         
         onchangeradio:function()
        { 
        	
        	 
        	 
        	 console.log("checkbox1",checkbox1)
        	 
        },
         
         submit:function()
         {
        	 
        	 
        	 
        	  
         },
         
         
         
        
         
	});
});
