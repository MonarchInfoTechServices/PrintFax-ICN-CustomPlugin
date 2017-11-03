console.log("Hi I am in action js");
define([
		
"dojo/_base/declare",
"ecm/widget/dialog/BaseDialog",
"ecm/model/Action",
"printFaxMainPluginDojo/OpeningDialogbox",
"dojo/text!./templates/ConfigurationPane.html"
	],
	function(declare,BaseDialog,Action,OpeningDialogbox,template) {

		return declare("printFaxMainPluginDojo.PrintFaxActionDialog", [Action], {
		
		contentString: template,
		widgetsInTemplate: true,
		
		
		postCreate:function(){
			
			console.log("Hi I am in action js11111111111111111111");
			
			  this.inherited(arguments);
			
		},
		
		   performAction: function(repository,itemList,listType,teamspace,resultSet,items)
	        {
	        	try{   
	        		
	        		console.log("in performAction")
      
       var opendialog= new OpeningDialogbox();
   	
      
   	
       opendialog.show(items, repository);
       opendialog.setSize(800, 600);
 

       opendialog.setResizable(true);
       
       
       opendialog.setTitle("Print/Fax Dialog");
       
       opendialog.addButton("Submit", function() {
           
    	   opendialog.submit();   
   		  
          }, false, false, null);
   	

   	
	        	}catch(e){
	        		
	        		console.log("error generted ",e);
	        		
	        	}

	     
	        },
	});
});
