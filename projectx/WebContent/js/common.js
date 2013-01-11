try {( function() {"use strict";
            //this function uses log only if console object exists (console is not part of the specification)
            function log(message) {
                if (console) {
                    console.log(message);
                }
            }

            log("common.js started");

            function hideLoadingMask() {
                if (document.getElementById('loadingMask').length !== 0) {
                    document.getElementById('loadingMask').style.display = "none";
                }
            }


            $(window).load(function() {
                var css_link, script;
                log("onload event triggered");
                // Add jQuery Mobile CSS to document
                log("Adding jQuery Mobile CSS to DOM");
                css_link = document.createElement('link');
                css_link.rel = 'stylesheet';
                css_link.href = 'http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css';
                document.getElementsByTagName('head')[0].appendChild(css_link);

                // Add jQuery library to document
                // log("Adding jQuery library to DOM");
                // script = document.createElement('script');
                // script.src = '//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js';
                // document.body.insertBefore(script, document.body.getElementsByTagName("script")[0]);

                // $(document).bind("mobileinit", function() {
                // $.mobile.ajaxEnabled = false;
                // });
                //
                // log("Adding jQuery Mobile library to DOM");
                // //Add jQuery Mobile library to document
                // script = document.createElement('script');
                // script.src = 'http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js';
                // document.body.appendChild(script);
                // document.body.insertBefore(script, document.body.getElementsByTagName("script")[1]);

                hideLoadingMask();

                log("finished loading");
            });
        }());
} catch (err) {
    message = "Exception in common.js:\n" + err.name + "\n" + err.message;
    if (err.stack) {
        message += "\n" + err.stack;
    }
    alert(message);
}
