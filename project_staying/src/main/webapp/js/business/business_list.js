

    

$(function() {
    /* 객실관리, 리뷰 클릭시 하단 화면 변경 */
    $('.select1').click(function() {
        $('.table').show();
        $('#review_wrap').hide();
        $('#select_wrap').show();
    })

    $('.select2').click(function() {
        $('.table').hide();
        $('#review_wrap').show();
        $('#select_wrap').hide();
        star_ck()

        // 객실삭제 버튼 누르고 input박스 생긴 상태에서 리뷰눌렀을 경우 객실관리(기본화면)으로 변경
        $('.del_click').css("display", "none");

                $('#del_click').css("display", "none");
                $('#canccle_click').css("display", "none");

                $('#add_click').css("display", "inline-block");
                $('#room_del_click').css("display", "inline-block");

                // 체크 된 체크박스 해제
                $(":checkbox").prop("checked",false);
    });

    // 객실 삭제 버튼 클릭시
    $('#room_del_click').click(function() {
        $('.del_click').css("display", "inline-block");

        $('#del_click').css("display", "inline-block");
        $('#canccle_click').css("display", "inline-block");

        $('#add_click').css("display", "none");
        $('#room_del_click').css("display", "none");
    });

    // 취소하기 버튼 클릭시
    $('#canccle_click').click(function() {
        $('.del_click').css("display", "none");

        $('#del_click').css("display", "none");
        $('#canccle_click').css("display", "none");

        $('#add_click').css("display", "inline-block");
        $('#room_del_click').css("display", "inline-block");

        // 체크 된 체크박스 해제
        $(":checkbox").prop("checked",false);
    });

    $("#btn_owner_review").click(function () {
        $(".owner").toggle();
    });

    $("#del_click").click(function () {
        $("#btn_remove_room").click();
    });

    var f = document.getElementsByClassName("select")[0];
    var s = document.getElementsByClassName("select")[1];

    f.onclick = function() {
        this.style.fontWeight = "bold";
        s.style.fontWeight = "normal";
       /* s.mouseenter= ("font-weight",'bold');
        s.onmouseout= ("font-weight",'');*/
    }

    s.onclick = function() {
        this.style.fontWeight = "bold";
        f.style.fontWeight = "normal";
       /* f.onmouseover= ("font-weight",'bold');
        f.onmouseout= ("font-weight",'');*/

        
    }

    document.querySelectorAll(".btn_owner_review").forEach(btn => {
        btn.addEventListener('click',function () {
            $(".owner").eq(this.value).toggle();
        });
    });
});




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