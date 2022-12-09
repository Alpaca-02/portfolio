$(document).ready(function ( ) {
    document.querySelector(".btn_map_close").addEventListener("click", close);
    document.querySelector(".map_bg").addEventListener("click", close);
});

function close() {
	document.querySelector(".map_modal_wrap").classList.add("hidden");
    document.body.style.overflow = "auto";
}

function openModal(stay_name, lat, lng) {
	document.querySelector(".map_modal_wrap").classList.remove("hidden");
	document.body.style.overflow = "hidden";
	view_map(stay_name, lat, lng);
}

function view_map(stay_name, lat, lng) {
	let mapContainer = document.getElementById('map_main'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	setTimeout(function(){ map.relayout(); }, 100);
	let bounds = new kakao.maps.LatLngBounds();
	let selectedMarker = null;

	let positions = [];
	let infowindow;
	
	for(let i = 0; i< stay_name.length; i++) {
		positions.push({
			content: stay_name[i], 
	        latlng: new kakao.maps.LatLng(lat[i], lng[i])
		});
	}
	
	var geocoder = new kakao.maps.services.Geocoder();
	
	for (let i = 0; i < positions.length; i ++) {
	    let marker = new kakao.maps.Marker({
	        map: map, 
	        position: positions[i].latlng, 
	        title : positions[i].content
	    });
		bounds.extend(positions[i].latlng);
	    infowindow = new kakao.maps.InfoWindow({
	    	content: ""
	    });
	
	    
	    
	    kakao.maps.event.addListener(marker, 'click', function() {
	    	searchDetailAddrFromCoords(positions[i].latlng, function(result, status) {
		        if (status === kakao.maps.services.Status.OK) {
		            var detailAddr = !!result[0].road_address ? '<div>' + result[0].road_address.address_name + '</div>' : '';
		            
			        if (!selectedMarker || selectedMarker !== marker) {
			            !!selectedMarker && infowindow.close();
			        	
			            infowindow.open(map, marker);
			            
			            var content = document.createElement('div');
			    	    content.className="wrap"
			    	    content.style.cssText = 'background: white; border: 1px solid black';
			    	    
			    	    var info = document.createElement('div');
			    	    info.className="info";
			    	    
			    	    var title = document.createElement('div');
			    	    title.innerHTML =  marker.getTitle();
			    	    title.className="title";
			    	    
			    	    var closeBtn = document.createElement('button');
			    	    closeBtn.innerHTML = '닫기';
			    	    closeBtn.className = "close"
			    	    closeBtn.onclick = function () {
			    	    	infowindow.setMap(null);
			    	    };
			    	    
			    	    var info_body = document.createElement('div');
			    	    info_body.className = "info_body";
			    	    var ellipsis = document.createElement('div');
			    	    ellipsis.innerHTML = !!result[0].road_address ? result[0].road_address.address_name: '';
			    	    ellipsis.className = "ellipsis";
			    	    
			    	    info_body.appendChild(ellipsis);
			    	    title.appendChild(closeBtn);
			    	    info.appendChild(title);
			    	    info.appendChild(info_body);
			    	    content.appendChild(info);
			    	    
			    	    infowindow.setContent(content);
			    	    
			            //infowindow.setContent("<div>" + marker.getTitle() + "</div>");
			        }
			        selectedMarker = marker;
		        }
		   	})
		});
	}
	
	map.setBounds(bounds);
	function searchDetailAddrFromCoords(coords, callback) {
	    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
	}

}