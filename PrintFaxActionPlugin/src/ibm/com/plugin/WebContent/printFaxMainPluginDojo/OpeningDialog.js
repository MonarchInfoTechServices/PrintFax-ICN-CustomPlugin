define(
		[

				"dojo/_base/declare",
				"ecm/widget/dialog/BaseDialog",
				"dijit/_TemplatedMixin",
				"dijit/_WidgetBase",
				"dojo/dom-style",
				"dojo/on",
				"dojo/dom",
				"dojo/json",
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
				on, dom, json, Select, registry, ObjectStore, ArrayList,
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
	
			var  flagList = [];

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

							
								lastpagevaluefortiff = [];
								for (var i = 0; i < itemList.length; i++) {

									var mimetype = itemList[i].mimetype;

									var n = mimetype
											.localeCompare("image/tiff");

									if (n == 0) {
										
										flagTiff = true;
										flagList[i] = flagTiff;

										lastpagevaluefortiff
												.push(itemList[i].attributes.ContentElementsPresent.length)

									} else {
										flagTiff = false;
										flagList[i] = flagTiff;

									}

								}

								starttextbox = [];
								lasttextboxarray = [];

								docsarray = [];

								docidsarray = [];
								checkboxarray = [];

								mimetypesarray = [];

								for (var i = 0; i < itemList.length; i++) {

									
									docsarray.push(itemList[i].name);

									docidsarray.push(itemList[i].docid);

									mimetypesarray.push(itemList[i].mimetype);

								}
								
								var jsonArray = [];

								for (var i = 0; i < docsarray.length; i++) {
									var jsonObj = {};

									jsonObj["id"] = i + 1;

									jsonObj["name"] = docsarray[i];

									jsonArray.push(jsonObj);

								}
								
								var documentgrid = this.gridid;

								var documentsmemory = new Memory({
									data : jsonArray
								});

								var ObjStore = new ObjectStore({
									objectStore : documentsmemory
								});

								var layout = [
										{
											name : 'S.No',
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
											field : 'property',
											'width' : '60px',
											styles : "text-align: center;",

											'formatter' : function(data,ItemList) {

												allpagescheckbox = new CheckBox(
														{

															disabled : true,
															name : "allpagesvalue",
															label : "checkbox",
															checked : true,

															onClick : function() {

																var checkboxid = ItemList;

																

																if (this.checked == false) {

																	starttextbox[checkboxid]
																			.set(
																					"disabled",
																					false);

																	lasttextboxarray[checkboxid]
																			.set(
																					"disabled",
																					false);
																	

																} else {

																	starttextbox[checkboxid]
																			.set(
																					"disabled",
																					true);
																	lasttextboxarray[checkboxid]
																			.set(
																					"disabled",
																					true);


																}

															}

														});
												checkboxarray.push(allpagescheckbox);

												return allpagescheckbox;

											}

										},

										{
											name : 'Start Page',
											'field' : 'classdescription',
											'width' : '70px',
											styles : "text-align: left;",
											'formatter' : function(data) {

												StartpageTextBox = new TextBox(
														{

															label : "TextBox",
															style : {
																width : '30%',
																
															},
															value : "1",
															styles : "text-align: center;",
															disabled : true,
															
														});

												starttextbox.push(StartpageTextBox);

												
												return StartpageTextBox;

											}

										},

										{
											name : 'Last Page',
											field : 'classdescription',
											'width' : '70px',
											styles : "text-align: left;",
											'formatter' : function(data,
													ItemList) {

												lastTextBox = new TextBox(
														{
															label : "lastTextBox",

															disabled : true,

															style : {
																width : '50%',
															
															},

															

														});



												if (flagList[ItemList]) {

													for(i=0;i<lastpagevaluefortiff.length;i++){
														

													lastTextBox
															.set("value",
																	lastpagevaluefortiff[i]);}

												} else {

													
													lastTextBox.set("value",
															9999);
												}

												lasttextboxarray
														.push(lastTextBox);
												return lastTextBox;

												

											}
										}

								];

								
								grid = new dojox.grid.EnhancedGrid({

									store : ObjStore,
									structure : layout,
									autoHeight : true,
									autoWidth : true

								}, documentgrid);
								grid.startup();

							
							} catch (e) {

								console.log("", e);

							}
						},

						onchangeradio : function() {

							
							var allpagesradiovalue = this.allpages.checked;

							if (allpagesradiovalue == true)

							{
								for (var i = 0; i < checkboxarray.length; i++) {
									
									

									checkboxarray[i].set("disabled", true);
									checkboxarray[i].set("checked", true);
									starttextbox[i].set("disabled", true);
									lasttextboxarray[i].set("disabled", true);
									starttextbox[i].set("value", 1);
									
								
									if (flagList[i]) {

										for(j=0; j<lastpagevaluefortiff.length; j++){
											
											
										lastTextBox
												.set("value",
														lastpagevaluefortiff[j]);}

									} else {

										
										lastTextBox.set("value",
												9999);
									}

									
									
									
									
									//lasttextboxarray[i].set("value", 9999);
									
									

								}

							} else {

								for (var i = 0; i < checkboxarray.length; i++) {

									checkboxarray[i].set("disabled", false);

								}

							}

						},

						submit : function() {

							documentsarray = [];
							startpagevalues = [];
							lastpagevalues = [];
							documentsidarray = [];
							mimetypearray = [];

							var action = PrinterCombo.id;
	                        var printervalue=PrinterCombo.item.id;
							 printfaxname = PrinterCombo.value;
							
							var papersize = papersizecombobox.value;
						    var annotation = th.Annotation.checked;
							for (var i = 0; i < starttextbox.length; i++) {

								startpagevalues.push(starttextbox[i].value);
								lastpagevalues.push(lasttextboxarray[i].value);
								documentsarray
										.push(grid.store.objectStore.data[i].name);

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
							var tovalue = th.to.value;						
							var faxfromvalue = th.faxfrom.value;					
							var phonenovalue = th.phoneno.value;
							var companyvalue = th.company.value;							
							var faxnovalue = th.faxno.value;							
							var notesvalue = th.notes.value;
							var repositoryId = repositoryId;
							var defalutRep = Desktop.getDefaultRepository();
							repositoryId = defalutRep.repositoryId;
							var userId = ecm.model.desktop.userId;

							
							var valuesforservice = {};

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


