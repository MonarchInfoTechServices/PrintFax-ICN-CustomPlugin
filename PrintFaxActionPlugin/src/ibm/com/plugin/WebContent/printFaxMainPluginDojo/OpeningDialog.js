define(
		[

				"dojo/_base/declare",
				"ecm/widget/dialog/BaseDialog",
				"dijit/_TemplatedMixin",
				"dijit/_WidgetBase",
				"dojo/dom-style",
				"dojo/on",
				"dojo/_base/lang",
				"dojo/dom",
				"dojo/json",
				"dojo/dom-attr",
				"dijit/form/Select",
				"dijit/registry",
				"dojo/data/ObjectStore",
				"dojox/collections/ArrayList",
				"dijit/form/CheckBox",
				"dijit/form/TextBox",
				"dojo/store/Memory",
				"dijit/form/ComboBox",
				"dojo/_base/array",
				"ecm/model/Request",
				"ecm/model/Desktop",
				"ecm/widget/dialog/MessageDialog",
				"dijit/_WidgetsInTemplateMixin",
				"dojo/text!printFaxMainPluginDojo/templates/PrintFaxPane.html",
				"dojo/text!printFaxMainPluginDojo/json/ChoicelistValues.json",
				"dojo/domReady!" ],
		function(declare, BaseDialog, _TemplatedMixin, _WidgetBase, domStyle,
				on,lang, dom, json,domAttr, Select, registry, ObjectStore, ArrayList,
				CheckBox, TextBox, Memory, ComboBox, array, Request, Desktop,
				MessageDialog, _WidgetsInTemplateMixin, template, ChoicelistValues) {
			var th;
			var checkbox1;
			var documentid;
			var myTextBox;
			var lastTextBox;
			var pagecountvalue;
			var mimetype;
			var flagTiff = false;
			var comboBox;
			var grid;
			var papersizecombobox;
			var qualitycombobox;
			var ejecttraycombobox;
			var orientationcombobox;
			var saclingcombobox;
			var copiescombobox;
			var prioritycombobox;
			var responsedata;
			var printfaxname;
			var ObjStore;
			var orgObjStr;
			var  flagList = [];
	        var docSizeArr;
	        var docsarray;

			return declare(
					"printFaxMainPluginDojo.OpeningDialog",
					[ _WidgetBase, _TemplatedMixin, BaseDialog,
							_WidgetsInTemplateMixin ],
					{

						contentString : template,
						widgetsInTemplate : true,

						postCreate : function() {

							th = this;

							count = 0;

							this.inherited(arguments);

						},
						onHide : function() {

							this.destroyDescendants();

						},

						show : function(items, repository, itemList) {

							try {
								this.inherited(arguments);
								this._widgetList = [];

								var PrinterDiv = this.requesttype;

								var requesttypeserviceParams = {};

								var response = Request
										.invokeSynchronousPluginService(
												"PrintFaxMainPlugin",
												"PrintFaxservicePlugin",
												requesttypeserviceParams);

								

								responsedata = response.result;

								

								var printerstore = new Memory({
									data : responsedata
								});

								
								  var lablename = responsedata[0].name;

			                        var valuename = responsedata[0].id;
			                        
								PrinterCombo = new ComboBox({

									item: {name:lablename, id:valuename},            
									store : printerstore

								}, PrinterDiv);

								

								if (responsedata[0].id == "FAX") {

									domStyle.set(th.printoptiondiv, "display",
											"none");

								} else {

									domStyle
											.set(th.faxoptiondiv, "display", "");

								}

								on(PrinterCombo, "change", function(typeofrequest) {

									var devicerequesttype = PrinterCombo.item.id;
									
									
									if (devicerequesttype == "FAX") {

										domStyle.set(th.faxoptiondiv,
												"display", "");

										domStyle.set(th.printoptiondiv,
												"display", "none");

									
									} else {

										domStyle.set(th.faxoptiondiv,
												"display", "none");

										domStyle.set(th.printoptiondiv,
												"display", "");

									}

								});

							

								var Choicelistvalues = json.parse(ChoicelistValues);
								var Papersizevalues = Choicelistvalues.PaperSizeproperties;
								var qualityvalues = Choicelistvalues.qualityproperties;
								var ejecttrayvalues = Choicelistvalues.ejecttrayproperties;
								var orientationvalues = Choicelistvalues.orientationproperties;
								var scalingvalues = Choicelistvalues.scalingproperties;
								var copiesvalues = Choicelistvalues.copiesproperties;
								var priorityvalues = Choicelistvalues.priorityproperties;
								
								var papersizetype = this.papersize;

								var papersizestore = new Memory({
									data : Papersizevalues
								});

								papersizecombobox = new ComboBox({
									name : "Papersize values",
									value : "Global Default",
									store : papersizestore,
									searchAttr : "name"
								}, papersizetype);

								
								var Quality = this.quality;

								

								var qualitystore = new Memory({
									data : qualityvalues
								});

								qualitycombobox = new ComboBox({
									name : "Quality",
									value : "Normal 240dpi*98dpi",
									store : qualitystore

								}, Quality);

								var Ejecttray = this.ejecttray;

								

								var ejecttraystore = new Memory({
									data : ejecttrayvalues
								});

								ejecttraycombobox = new ComboBox({
									name : "Eject Tray",
									value : "Default",
									store : ejecttraystore

								}, Ejecttray);

								var Orientation = this.orientation;

							

								var orientationstore = new Memory({
									data : orientationvalues
								});

								orientationcombobox = new ComboBox({
									name : "Orientation",
									value : "Default",
									store : orientationstore

								}, Orientation);

								var Scaling = this.scaling;

								
								var Scalingstore = new Memory({
									data :scalingvalues
								});

								saclingcombobox = new ComboBox({
									name : "Scaling",
									value : "Default",
									store : Scalingstore

								}, Scaling);

								var Copies = this.copies;

							

								var copiesstore = new Memory({
									data : copiesvalues
								});

								copiescombobox = new ComboBox({

									name : "Copies",
									value : "1",
									store : copiesstore

								}, Copies);

								var Priority = this.priority;

							

								var prioritystore = new Memory({
									data : priorityvalues
								});

								prioritycombobox = new ComboBox({
									name : "Copies",
									value : "Normal",
									store : prioritystore

								}, Priority);

								
								
								tiffdocids = [];
								docIdArr=[];
								
							for (var i = 0; i < itemList.length; i++) {
								
									var mimetype = itemList[i].mimetype;
									var tiffdocumentsid=itemList[i].id;
									var tifffragments = tiffdocumentsid.split(",");
									var tifffinalid=tifffragments[tifffragments.length-1];
									docIdArr.push(tifffinalid);
								if(mimetype=="image/tiff"){
								    tiffdocids.push(tifffinalid);
									
								}
								}
								
							var tiffdocumentids = {};
							
							var defalutRep = Desktop.getDefaultRepository();
							var repositoryIdfortiff = defalutRep.repositoryId;
							
							
							
							tiffdocumentids.repositoryIdfortiff=repositoryIdfortiff;
							
							tiffdocumentids.tiffdocids = JSON.stringify(tiffdocids);

							var response = Request
									.invokeSynchronousPluginService(
											"PrintFaxMainPlugin",
											"ContentElementSizeService",
											tiffdocumentids);
							
							
							var responsefortiff=response.result

							docSizeArr= [];
							for ( var j=0 ; j<docIdArr.length;j++ ){
								
								cFlag= false;
								for(var k=0 ; k<responsefortiff.length;k++ )
								{
									
									if(docIdArr[j]==responsefortiff[k].name){
									// Tiff
										docSizeArr.push(responsefortiff[k].id);
									cFlag=true;
										break;
										
									}
									
										
								}
								//Pdf
								if(!cFlag)
										docSizeArr.push(9999);
										
							}
							
							itemList.responsefortiff=responsefortiff;
							
								starttextbox = [];
								lasttextboxarray = [];

								docsarray = [];

								docidsarray = [];
								checkboxarray = [];

								mimetypesarray = [];

								for (var i = 0; i < itemList.length; i++) {

									
									docsarray.push(itemList[i].name);

									var documentsid=itemList[i].id;
									var fragments = documentsid.split(",");
								    var finalid=fragments[fragments.length-1];
									docidsarray.push(finalid);

									mimetypesarray.push(itemList[i].mimetype);

								}
								
								var jsonArray = [];

								for (var i = 0; i < docsarray.length; i++) {
									var jsonObj = {};

									jsonObj["id"] = i + 1;

									jsonObj["name"] = docsarray[i];
									jsonObj["valueCheck"] =true;
									
									jsonObj["lastpagevalues"] = docSizeArr[i];
									
									jsonObj["startpagevalues"] =1;
									
								

									jsonArray.push(jsonObj);

								}
								
								var documentgrid = this.gridid;

								var documentsmemory = new Memory({
									data : jsonArray
								});

								
								
								 ObjStore = new ObjectStore({
									objectStore : documentsmemory
								});
								
								
								

								var layout = [
										{
											name : 'No.',
											field : 'id',
											'width' : '50px',
											styles : "text-align: left;"
										},
										{
											name : 'Name',
											field : 'name',
											'width' : '435px',
											styles : "text-align: left;"

										},
										{
											name : 'All Pages',
											field : 'valueCheck',
											'width' : '60px',
											styles : "text-align: center;",
											type: dojox.grid.cells.Bool,
											editable: true
											
										},

										{
											
												name : 'Start Page',
												'field' : 'startpagevalues',
												'width' : '70px',
												styles : "text-align: left;",
												editable:"false",
												singleClickEdit:"true"
												

											},

										{
											name : 'Last Page',
											field : 'lastpagevalues',
											'width' : '70px',
											styles : "text-align: left;",
											editable:"false",
											singleClickEdit:"true"
											
										}

								];

								
								grid = new dojox.grid.EnhancedGrid({

									store : ObjStore,
									structure : layout,
									autoHeight : true,
									autoWidth : true

								}, documentgrid);
								grid.startup();
								
								th.grid  = grid;
								
							
								
							
								th.grid.canEdit = function(inCell, inRowIndex) {
									var rowCheck=th.grid.getItem(inRowIndex).valueCheck;
									
									if(rowCheck){
										
										th.grid.getItem(inRowIndex).startpagevalues=1;
										th.grid.getItem(inRowIndex).lastpagevalues=docSizeArr[inRowIndex];
										
										
									}
									
									
									if(inCell.index == 2 )
									if(th.allpages.checked){
									 return false;}
									else {return true};


									
									
									if((inCell.index == 3 || inCell.index == 4 ) && !rowCheck ){
									
										return true;
									}
									  else {
									    return false;
									}
									};
							} catch (e) {

								console.log("", e);
							}
						},

						onchangeradio : function() {
							var allpagesradiovalue = this.allpages.checked;
							jsonArr=[];
							if (allpagesradiovalue == true){
								
								for (var i = 0; i < docsarray.length; i++) {
									var jsonObj = {};

									jsonObj["id"] = i + 1;

									jsonObj["name"] = docsarray[i];
									jsonObj["valueCheck"] =true;
									
									jsonObj["lastpagevalues"] = docSizeArr[i];
									
									jsonObj["startpagevalues"] =1;
									
								

									jsonArr.push(jsonObj);

								}
								
								var orgMemory = new Memory({
									data : jsonArr
								});

								var orgObjStr = new ObjectStore({
									objectStore : orgMemory
								});
								
							
								
								
								th.grid.setStore(orgObjStr);
								


							}
							

						},

						submit : function() {

							documentsarray = [];
							startpagevalues = [];
							lastpagevalues = [];
							documentsidarray = [];
							mimetypearray = [];

							
							var tovalue = th.to.value;						
							var faxfromvalue = th.faxfrom.value;
							var action = PrinterCombo.id;
	                        var printervalue=PrinterCombo.item.id;
							 printfaxname = PrinterCombo.value;
							
							var papersize = papersizecombobox.value;
						    var annotation = th.Annotation.checked;
							for (var i = 0; i < docsarray.length; i++) {
									startpagevalues.push(th.grid.getItem(i).startpagevalues);
									lastpagevalues.push(th.grid.getItem(i).lastpagevalues);
								documentsarray
										.push(th.grid.store.objectStore.data[i].name);
								documentsidarray.push(docidsarray[i]);
								mimetypearray.push(mimetypesarray[i]);

							}
							
							
							
							
							var ejecttrayvalue = ejecttraycombobox.value;
							var Orienatationvalue = orientationcombobox.value;		
							var scalingvalue = saclingcombobox.value;						
							var copiesvalue = copiescombobox.value;				
							var priorityvalue = prioritycombobox.value;
							var printtimevalue = th.printtime.value;
							var collatevalue = th.collate.checked;
							var CoverPagevalue = th.CoverPage.checked;						
							var qualityvalue = qualitycombobox.value;					
							var faxttovalue = th.faxto.value;						
											
							var phonenovalue = th.phoneno.value;
							var companyvalue = th.company.value;							
							var faxnovalue = th.faxno.value;							
							var notesvalue = th.notes.value;
							var repositoryId = repositoryId;
							var defalutRep = Desktop.getDefaultRepository();
							repositoryId = defalutRep.repositoryId;
							var userId = ecm.model.desktop.userId;
														
							var allPagesRadioValue= th.allpages.checked;
							
							var valuesforservice = {};

							
							valuesforservice.allPagesRadioValue=allPagesRadioValue;
							valuesforservice.repositoryId = repositoryId;							
							valuesforservice.printervalue=printervalue;
							valuesforservice.printfaxname = printfaxname;
							valuesforservice.papersize = papersize;
							valuesforservice.annotation = annotation;
							valuesforservice.ejecttrayvalue = ejecttrayvalue;
							valuesforservice.Orienatationvalue = Orienatationvalue;
							valuesforservice.scalingvalue = scalingvalue;
							valuesforservice.copiesvalue = copiesvalue;
							valuesforservice.priorityvalue = priorityvalue;
							valuesforservice.printtimevalue = printtimevalue;
							valuesforservice.collatevalue = collatevalue;
							valuesforservice.CoverPagevalue = CoverPagevalue;
							valuesforservice.qualityvalue = qualityvalue;
							valuesforservice.faxttovalue = faxttovalue;
							valuesforservice.tovalue = tovalue;
							valuesforservice.faxfromvalue = faxfromvalue;
							valuesforservice.phonenovalue = phonenovalue;
							valuesforservice.companyvalue = companyvalue;
							valuesforservice.faxnovalue = faxnovalue;
							valuesforservice.notesvalue = notesvalue;
							valuesforservice.userId = userId;
							valuesforservice.documentsarray = JSON
									.stringify(documentsarray);

							valuesforservice.startpagevalues = JSON
									.stringify(startpagevalues);
							valuesforservice.lastpagevalues = JSON
									.stringify(lastpagevalues);

							valuesforservice.documentsidarray = JSON
									.stringify(documentsidarray);
							valuesforservice.mimetypearray = JSON
									.stringify(mimetypearray);

							Request
									.invokePluginService(
											"PrintFaxMainPlugin",
											"PrintFaxvaluesservicePlugin",
											{

												requestParams : valuesforservice,

												requestCompleteCallback : function(
														response) {

												
													if(response.result[0].printrequestinsertionvalue =="1"){
													th.destroy();

													var messageDialog = new MessageDialog(
															{
																text : "Print/Fax request submitted sucsessfully to:"+printfaxname

															});
													messageDialog.show();
													}else{
													
														var errorDialog = new MessageDialog(
																{
																	text : "Error Ocurred Please contact system administrator",

																});
														errorDialog.show();
														
														
													}

												},
												requestFailedCallback : function(
														response) {
													console
															.log("Entered the requestFailedCallback");
												}

											});

						},

					});
		});














