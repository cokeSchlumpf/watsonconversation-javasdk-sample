/* global ConversationPanel: true, PayloadPanel: true*/
/* eslint no-unused-vars: "off" */

// Other JS files required to be loaded first: apis.js, conversation.js, payload.js
(function() {
	// Initialize all modules
	ConversationPanel.init();

	if (window.self === window.top) {
		PayloadPanel.init();
	} else {
		window.top.document.getElementById('wcs-bot-window').style.display = 'none';
		window.top.document.getElementById('wcs-bot-button').onclick = function() {
			var current = window.top.document.getElementById('wcs-bot-window').style.display;
			if (current == "none") {
				window.top.document.getElementById('wcs-bot-window').style.display = 'block';
			}
		}
	}
})();
