var host = "http://localhost:8080/blockerapp/machinelearning.jsp?domains=";
chrome.webRequest.onBeforeRequest.addListener(
    function(details) {
	return {redirectUrl: host+details.url};	
    },
    {
        urls: [
	"*://google.com/*",
	"*://howtodoinjava.com/*",
	"*://javatpoint.com/*",
	"*://paypal.comwebscr.123457.login.submit.dispatch.se/*",
	"*://paypal-manager-login.net/*",
	"*://paypal-manager-account.net/*",
	"*://paypal.com.laveaki.com.br/*",
	"*://paypal-manager-loesung.net/*",
	"*://paypal.client.identifiant.compte.clefs.informations.upgrade.mon.com/*",
	"*://yaxz.in.ed/*",
	"*://apple.com.adminconsole.in.com/*"
        ],
        types: ["main_frame", "sub_frame", "stylesheet", "script", "image", "object", "xmlhttprequest", "other"]
    },
    ["blocking"]
);