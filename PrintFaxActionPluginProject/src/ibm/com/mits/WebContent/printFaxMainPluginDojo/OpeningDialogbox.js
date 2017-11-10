
define([
		
"dojo/_base/declare",
"ecm/widget/dialog/BaseDialog",
"dijit/_TemplatedMixin",
"dijit/_WidgetBase",
"dojo/dom-style",
"dojo/on",
"dojo/dom",
"dojo/json",
"dijit/registry",
"dojo/data/ObjectStore",
"dojox/collections/ArrayList",
"dijit/form/CheckBox",
"dijit/form/TextBox",
"dojo/store/Memory", "dijit/form/ComboBox",
"dojo/_base/array",
"ecm/model/Request",
"ecm/model/Desktop",
"dijit/_WidgetsInTemplateMixin",
"dojo/text!printFaxMainPluginDojo/templates/PrintFaxplugin.html",
"dojo/text!printFaxMainPluginDojo/json/Jsondata.json",

"dojo/domReady!"
	],
	function(declare,BaseDialog,_TemplatedMixin,_WidgetBase,domStyle,on,dom,json,registry,ObjectStore,ArrayList,CheckBox,TextBox,Memory,ComboBox,array,Request,Desktop,_WidgetsInTemplateMixin,template,jsonData) {
     var th;
     var checkbox1;
     var documentid;
     var myTextBox;
     var lastTextBox ;
     var pagecountvalue;
  
   
 	
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
		
		 show: function(items, repository,itemList) {
			 
		try{
             this.inherited(arguments);
             this._widgetList = [];
             
             var PrinterDiv = this.requesttype ; 

          console.log("11111",PrinterDiv)
         

          
          var serviceParams = {};
          
          
          
          var response = Request.invokeSynchronousPluginService("PrintFaxMainPlugin", "PrintFaxservicePlugin",serviceParams);
          
          
          
          console.log("response",response)
          
          


          
var responsedata=response.result;
          
          console.log("responsedata",responsedata)
          
          
          var printerstore = new Memory({
              data: responsedata
          }); 
          
        
          
        
     
          var comboBox = new ComboBox({
        	  
        	  id:"cId",
        	  
        	  value: "Dev Print Server",
              name: "print/fax Name",
             
              store: printerstore
              
          }, PrinterDiv); 
             
         
          console.log("the combobox",comboBox);
     
          domStyle.set(th.faxoptiondiv,"display", "none");
          
          on(comboBox, "change", function(typeofrequest){
				 
				 console.log("typeofrequest:::::",typeofrequest);
				 
				 var typerequest = typeofrequest;
				 
				 console.log("activityObj.oc:::::",typerequest);
				 
				 
				 if(typerequest=="DEV Fax Server")
					 {
				
				 
				 domStyle.set(th.faxoptiondiv,"display", "");
				 
				  
				 
				 domStyle.set(th.printoptiondiv,"display", "none");
				 
				 console.log("displayed nothing");
					 }else{
						 

						 domStyle.set(th.faxoptiondiv,"display", "none");
						 
						 
						 domStyle.set(th.printoptiondiv,"display", "");
						 
						 
					 }
				 
				 
				 
					 
			 });
          
          
          console.log(jsonData,":::jsonData");
			
			
			var jsonobject =json.parse(jsonData);
			var jsonfields=jsonobject.PaperSizeproperties;	
			var jsonfields1=jsonobject.qualityproperties;	
			console.log("jsonfields:::::::::",fields1[0]);
          
          
          var papersizetype= this.papersize;
			 
			 console.log("in papersize",papersizetype)
			 
			 
			 
			 var papersizestore = new Memory({
	              data:jsonobject.PaperSizeproperties
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
	              data: jsonobject.qualityproperties
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
	          
	          
	          
	          
	          
	          
	          var serviceParams1 = {};
	          
	          var repositoryId =repositoryId;
				
				
	  var defalutRep=Desktop.getDefaultRepository();
	 
	   repositoryId=defalutRep.repositoryId;
	   
	   console.log("repositoryId is",repositoryId);
	   
	   for(var i=0;i<itemList.length;i++){
	   
		   var mimetype=itemList[i]. mimetype;
		   
		   console.log(" mimetype",mimetype)
		   
		   if(mimetype == "image/tiff"){

		   	console.log("in if")
	 
	           documentid= itemList[i].docid;
	          
		   }
	

	          
	          console.log("documentid",documentid)
	   
	   }
	   
	   
	   
	   serviceParams1.repositoryId = repositoryId;

		serviceParams1.documentid=documentid;
		
		
		Request.invokePluginService("PrintFaxMainPlugin", "PageNumberCount" ,{
			
			requestParams:serviceParams1,
			
			requestCompleteCallback:function(response){
				
				console.log("response :",response.result);
				
				pagecountvalue=response.result[0].docPagecount;
				console.log("pagecountvalue :",pagecountvalue);
				
			},
			requestFailedCallback:function(response){
				 console.log("Entered the requestFailedCallback");
			}
			
		});
	          
	          
	          
	          
	          
	           	checkboxarray= [];
	           	starttextbox=[];
	           	lasttextboxarray=[];
	          
	      
	          
	          console.log("itemList",itemList.length);
	           	docsarray= [];
	          
	          for(var i=0;i<itemList.length;i++){
	        	  
	        	  console.log("in for loop")
	        	console.log("itemlist",  itemList[i].name)
	        	       
	        	
	        	docsarray.push(itemList[i].name);

	        	        	console.log("docsarray",docsarray)
	        	  
	          }
	          console.log("var docsarray", docsarray)

	var jsonArray=[];

for (var i = 0 ; i < docsarray.length; i++) {
	var jsonObj = {};
	

	jsonObj["id"] = i+1;

  jsonObj["name"] = docsarray[i];



jsonArray.push(jsonObj);

} 
	          console.log("jsonobj",jsonObj)
	          
	     
          
	          var documentgrid=this.gridid;
	          
	          console.log("documentgrid",documentgrid);
	          
	          
	          
	          
	          var documentsmemory = new Memory({
	              data: jsonArray
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
					field : 'name',
					'width' : '240px',
					 styles : "text-align: center;"
						 
						 
						 
						 
						 
						 
						 
						 
				}, {
					name : 'All Pages',
					field : 'property',
					'width' : '100px',
					 styles : "text-align: center;",
				
					 
					 
					 
					 
					  'formatter': function(data,ItemList){
						
		                   checkbox1 = new CheckBox({
		                   
		                    	disabled:true,
		                    	name:"allpagesvalue",
		                    	label: "checkbox",
		                    	checked: true,
		                   
		                        onClick: function() {
		                      
		                       var checkboxid =ItemList;
		                       
		                       
		                       
		                       
		                       console.log("in checkbox",this.checked);
		                       
		                       if(this.checked==false)  {

		   		             

		   		        	  
		   		        	       starttextbox[checkboxid].set("disabled",false);
		   		        	       
		   		        	    lasttextboxarray[checkboxid].set("disabled",false);

		   		        	       console.log("after starttextbox disabled")
		   		               	
		   		         

		   		               }  else{
		   		            	   
		   		            	   
		   		            
			   		        	  
		   		        	       starttextbox[checkboxid].set("disabled",true);
		   		        	    lasttextboxarray[checkboxid].set("disabled",true);
		   		        	       
		   		        	    console.log("after starttextbox disabled true")
		   		            	   
		   		            	   
		   		               }    
		   		                   
		                      
		                       
		                       
		                       
		                        }
		                   
		                   
		                   
		                    });
	        	checkboxarray.push(checkbox1);
	        	
	        	
	        	console.log("checkboxarray",checkboxarray)
	        	
	        

   
		                    return checkbox1;
	        	
	        

		                   
		                }
				
				
					 
				},
				
				{
					name: 'Start Page',
					'field' : 'classdescription',
					'width' : '110px',
					 styles : "text-align: center;",
					'formatter': function(data){
						
	                   myTextBox  = new TextBox({
	                	 
	                    	label: "TextBox",
	                    	style: {width: '30%',height:'15px'},
	                    	  value: "1",
	                    	  styles : "text-align: center;",
	                    	  disabled:true,
	                        onClick: function() {
	                       console.log("in TextBox");
	                        }
	                    });
	                    
	                  
	                  starttextbox.push(myTextBox);
	                  
	                  console.log("myTextBox",starttextbox)
	                    return myTextBox;
	                    

	             
	                   
	                   
	                }
						
						
				}, 
				
				
				
				
				{
					name : 'last Page',
					field : 'classdescription',
					'width' : '110px',
						 styles : "text-align: center;",
						 'formatter': function(data){
							 
							 
							 if(mimetype == "image/tiff"){
								 
							console.log("in if")
			                     lastTextBox  = new TextBox({
			                    	label: "lastTextBox",
			                    	
			                    	  disabled:true,
			                    	  value:pagecountvalue,
			                    	  
			                     	style: {width: '30%',height:'15px'},
			                     	
			                        onClick: function() {
			                       console.log("in lastTextBox");
			                        }
			                     	
			                    });
			                    
			                    
			                    lasttextboxarray.push(lastTextBox);
			                    return lastTextBox;
							 }
							 else
								 {
								 
								 console.log("in else")
								 lastTextBoxforpdf  = new TextBox({
				                    	label: "lastTextBox",
				                    	value:"9999",
				                    	  disabled:true,
				                    	  
				                     	style: {width: '30%',height:'15px'},
				                     	
				                        onClick: function() {
				                       console.log("in lastTextBox");
				                        }
				                     	
				                    });
								 
								 
								 return lastTextBoxforpdf;
								 
								 
								 
								 }
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
	          
	           
		}catch(e){
			
			console.log("",e);
			
			
			
		}
         },
         
         
         onchangeradio:function()
        { 
        	 
        	 
        	 
        	 
 console.log("on changeradio",this.allpages)
        	 
        	 var value=this.allpages.checked;
        	
     if(value==true)   	 

{
 for(var i=0;i<checkboxarray.length;i++){
	        	  
	   checkboxarray[i].set("disabled",true);
	   checkboxarray[i].set("checked",true);
	   starttextbox[i].set("disabled",true);
	   lasttextboxarray[i].set("disabled",true);
 
	   starttextbox[i].set("value",1);
	   lasttextboxarray[i].set("value",9999);
	        
	          }


}else{

	for(var i=0;i<checkboxarray.length;i++){
	        	  
	        	       checkboxarray[i].set("disabled",false);

	          }

}
     

        },
        
        
        
       
         
         submit:function()
         {
        	 
        	 console.log("in submit function")
        	 
        	  
         },
         
         
         
        
         
	});
});


