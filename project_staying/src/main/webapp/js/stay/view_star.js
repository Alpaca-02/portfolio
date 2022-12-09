$(document).ready(function(){
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
});

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