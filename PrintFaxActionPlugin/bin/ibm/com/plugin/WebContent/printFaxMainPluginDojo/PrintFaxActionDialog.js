
define([
		
"dojo/_base/declare",
"ecm/widget/dialog/BaseDialog",
"ecm/model/Action",
"printFaxMainPluginDojo/OpeningDialog",
"dijit/Dialog",
"dojo/text!./templates/ConfigurationPane.html"
	],
	function(declare,BaseDialog,Action,OpeningDialog,Dialog,template) {

		return declare("printFaxMainPluginDojo.PrintFaxActionDialog", [Action], {
		
		contentString: template,
		widgetsInTemplate: true,
		
		postCreate:function(){


			this.inherited(arguments);

		},

		performAction: function(repository,itemList,listType,teamspace,resultSet,items)
		{
			try{   

				var opendialog= new OpeningDialog();

       opendialog.show(items, repository,itemList);
       opendialog.setSize(800, 660);
 

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
