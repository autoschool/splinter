jQuery(function ($) {
    // just a simple scaffolding
    var debug = true;
    function Logger() {
        function log(level, data) {
            if (console && console.log && level && data) {
                var args = Array.prototype.slice.call(data, 0);
                // quick padding hack
                while ((level += ' ').length < 8);
                var signature = level + ' ' + new Date().toLocaleString() + ':';
                while ((signature += ' ').length < 32);
                args.unshift(signature);
                console.log.apply(console, args);
            }
        }
        this.debug = function () {
            log('DEBUG', arguments);
        }
        this.info = function () {
            log('INFO', arguments);
        }
        this.warning = function () {
            log('WARNING', arguments);
        }
        this.error = function () {
            log('ERROR', arguments);
        }
    }
    var logger = new Logger;
    
    logger.info('Init');
    logger.debug('Timezone offset', (new Date().getTimezoneOffset() / 60).toString(), hours);
    //jQuery to collapse the navbar on scroll
    $(window).scroll(function() {
        if ($(".navbar").offset().top > 50) {
            $(".navbar-fixed-top").addClass("top-nav-collapse");
        } else {
            $(".navbar-fixed-top").removeClass("top-nav-collapse");
        }
    });
    
    /* ===============================================
        Scroll to Top Plugin
    =============================================== */
    
    $(window).scroll(function() {
        if( $(window).scrollTop() > 500 ) {
            $('#back-to-top').fadeIn(500);
                } else {
            $('#back-to-top').fadeOut(500);
        }
    });
    
    $('#back-to-top').click(function(){
        $('body').animate({
            scrollTop: 0
        },'slow');
        return false;
    });
    
    /* ===============================================
        Layout search form
    =============================================== */
    $(function () {
        // Show Search if form is not active // event.preventDefault() is important, this prevents the form from submitting
        $(document).on('click', '.navbar-collapse form[role="search"]:not(.active) button[type="submit"]', function(event) {
            event.preventDefault();
            var $form = $(this).closest('form'),
                $input = $form.find('input');
            $form.addClass('active');
            $input.focus();
        });
    
    });
});
