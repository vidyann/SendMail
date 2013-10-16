var SendMail = function() {
    this.resultCallback = null; // Function
};

cordova.addConstructor(function() {
	console.log("****************************");
	if (!window.plugins) {
		window.plugins = {};
	}

	// shim to work in 1.5 and 1.6
	if (!window.Cordova) {
		window.Cordova = cordova;
	}
	;

	window.plugins.sendMail = new SendMail();
});

SendMail.prototype.send = function (callback, message){
    console.log("Calling the send message");
    this.resultCallback = callback;
    cordova.exec(function(){ alert(mail sent')}, 
        function(){ alert(mail was not sent')}, 
        'SendMail', 
        'sendEmail', 
        [message]);
}

SendMail.prototype._didFinishWithResult = function(res) {
	this.resultCallback(res);
}

/*function send(){
    window.SendMail.prototype.send(message);
}*/