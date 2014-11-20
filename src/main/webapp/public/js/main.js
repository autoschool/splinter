
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
    $.scrollTo(0,'slow');
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




