sap.ui.define(
	["sap/ui/core/mvc/Controller",
		"jquery.sap.global",
		"anubhav/util/service",
		"sap/m/MessageBox"],
	function(Controller, jQuery, service ,MessageBox) {
		return Controller.extend("anubhav.controller.Main", {
			onInit: function() {
				var oModel = new sap.ui.model.json.JSONModel();
				oModel.setData({
					"postPayload": {
						"companyName": "",
						"firstName": "",
						"lastName": "",
						"website": "",
						"email": "",
						"status": "",
						"gstNo": "",
					},
					"vendor": {}
				});

				//set the model ibjecct  to the App level
				sap.ui.getCore().setModel(oModel);
			}
			,
			onSave:function(){
				var oModel = sap.ui.getCore().getModel();
				var payload = oModel.getProperty("/postPayload");
				service.callService("/vendor", "POST", payload).then(function(){
						MessageBox.confirm("Save Sucess");
				}).catch(function(){MessageBox.error("Post failed");
				});
				
			},
			
			onLoadData: function() {
				//			alert("todo : we will call our service here");
				var that = this;
				service.callService("newVendor", "GET", {}).then(function(data) {
					//console.log(data)
					var oTable = that.getView().byId("idTable");
					//create new ui5 model which contains the data
					var oModel = sap.ui.getCore().getModel();
					oModel.setProperty("/vendor", data._embedded.vendor);

					//set the model ibjecct  to the App level
					//sap.ui.getCore().setModel(oModel);
					//Bind aggregation of the table
					oTable.bindRows("/vendor");
				}).catch(function(oError) {	

				});

			}
		});
	});