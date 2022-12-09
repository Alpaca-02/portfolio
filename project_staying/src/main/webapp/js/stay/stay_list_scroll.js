
$(window).scroll(function() {
    if ($(window).scrollTop() >= ($(document).height() - $(window).height()-10)) {
        $(".stay_list_area").append(
            '<a class="stay_content" href="#"><div class="stay_image"><img src="' +
            '" alt=""></div><div class="stay_info"><div class="stay_info_l"><h2>' + 
            '</h2><p>' +
            '</p><p>' + 
            '</p><p>' + '</p></div></div></a>'+
            '<a class="stay_content" href="#"><div class="stay_image"><img src="' +
            '" alt=""></div><div class="stay_info"><div class="stay_info_l"><h2>' + 
            '</h2><p>' +
            '</p><p>' + 
            '</p><p>' + '</p></div></div></a>'+
            '<a class="stay_content" href="#"><div class="stay_image"><img src="' +
            '" alt=""></div><div class="stay_info"><div class="stay_info_l"><h2>' + 
            '</h2><p>' +
            '</p><p>' + 
            '</p><p>' + '</p></div></div></a>'
        );

        //조건 폼 sticky
        //$("#contents_area").height($(".stay_list_area").outerHeight());
        
    }
 });

function append_stay (image_src, stay_name, stay_content, stay_star, stay_price){
    $(".stay_list_area").append(
        '<a class="stay_content" href="#"><div class="stay_image"><img src="' + image_src +
        '" alt=""></div><div class="stay_info"><div class="stay_info_l"><h2>' +  stay_name + 
        '</h2><p>' + stay_content +
        '</p><p><span>' + stay_star + 
        '</span><span>' + stay_price + '</span></p></div></div></a>'
    );
}