 /* $(function() {
    $('#menu > div').mouseover(function() {
        $(this).css("font-weight", "bold");
    });
    $('#menu > div').mouseleave(function() {
        $(this).css("font-weight", "normal");
    });
});*/
$(function() {
    $('#detail').click(function() {
        $('#click_detail').show().siblings('div').hide();
        $('#menu > div').css("font-weight", "normal");
        $('#detail').css("font-weight", "bold");
        //$('#detail').css("color", "#48a9ed");
    })

    $('#review').click(function() {
        $('#click_review').show().siblings('div').hide();
        $('#menu > div').css("font-weight", "normal");
        $('#review').css("font-weight", "bold");
        star_ck();
    })
    

    var className = document.getElementsByClassName("amenitie");
    $(".amenitie").click(function() {
        if($(this).children().css("display") == "none") {
            $(this).children().show();
        }else {
            $(this).children().hide();
        }
        /*$(this).children().show();
        if($(this).children().style.display == "block") {
            $(this).children().hide();
        }*/
    })
});

function place_click(stay_name, lat, lng) {
    $('#click_place').show().siblings('div').hide();
    $('#menu > div').css("font-weight", "normal");
    $('#place').css("font-weight", "bold");
    show_map(stay_name, lat, lng);
}

function show_map(stay_name, lat, lng) {
    var mapContainer = document.getElementById('map');
    mapOption = { 
        center: new kakao.maps.LatLng(lat, lng),
        level: 3
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	var position =  new kakao.maps.LatLng(lat, lng);
	
	var marker = new kakao.maps.Marker({
	    map: map, 
	    position: position
	});
	
	marker.setMap(map);
	
	var geocoder = new kakao.maps.services.Geocoder();
	
	var overlay = new kakao.maps.CustomOverlay({
	    content: "",
	    map: map,
	    position: marker.getPosition()       
});

	    
    searchDetailAddrFromCoords(position, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address ? '<div>' + result[0].road_address.address_name + '</div>' : '';
            
        	var content = '<div class="info_wrap">' + 
            '    <div class="info_content">' + 
            '        <div class="info_title">' + 
            			stay_name + 
            '        </div>' + 
            '        <div class="info_body">' + 
            			detailAddr +
            '        </div>' + 
            '    </div>' +    
            '</div>';

            overlay.setContent(content);
            overlay.setMap(map);
        }   
    });


	function searchDetailAddrFromCoords(coords, callback) {
	    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
	}
}

function star_ck(){
    let star_rating = document.querySelectorAll(".view_star");

    star_rating.forEach(p_star_rating => {
        let star_rating = p_star_rating.innerText
        let star_decimal = Number(((star_rating/2)%1).toFixed(1));
        let star_point = Math.floor(star_rating/2);
        star_width = 0;

        let back_star = "<span class='back_star_wrap'>"
        back_star += '<span class="bk-star fa fa-star"></span>';
        back_star += '<span class="bk-star fa fa-star"></span>';
        back_star += '<span class="bk-star fa fa-star"></span>';
        back_star += '<span class="bk-star fa fa-star"></span>';
        back_star += '<span class="bk-star fa fa-star"></span>';
        back_star += '</span>';
        
        let star = "<div class='front_star_wrap'>";
        for(let i=0; i<star_point; i++){
            star += '<span class="star fa fa-star"></span>';
            star_width += 0.2;
        }
        if(star_decimal != 0) {
            star += '<span class="last_star fa fa-star"></span>'
            //star_width += (0.2 * star_decimal);
        }
        star += "</div>"
        p_star_rating.innerHTML = star + back_star + star_rating;
        
        setTimeout(function () {
            let w = p_star_rating.querySelector(".back_star_wrap").offsetWidth
            p_star_rating.querySelector(".front_star_wrap").style.width = star_rating/10 * w + "px";
        }, 100)
    }); 
}