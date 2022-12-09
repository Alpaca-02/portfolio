$(document).ready(function(){
    let stay_intro = document.querySelectorAll("div.stay_info_l > p:nth-child(2)");
    stay_intro.forEach(stay => {
        let text = stay.innerText;
        if(text.length > 30){
            console.log(text.length )
            stay.innerText = text.slice(0, 30) + "...";
        }
    })

    if($(".stay_list_area").outerHeight() > $(".conditions_map_area").outerHeight()){
        $("#contents_area").height($(".stay_list_area").outerHeight());
    } else {
        $("#contents_area").height($(".conditions_map_area").outerHeight());
    }
});
function stay_kind_search (obj) {
    let label = document.querySelector(".stay_type_condition label[for="+obj.value+"]");
    document.querySelector(".selected_option").innerText = label.innerText;
    document.querySelector("#selected").value = obj.value;
}
/*function fn_text_ellipsis(obj) {
    let stay_intro = obj.innerText;
    console.log(stay_intro)
    if(stay_intro.length > 30){
        console.log(stay_intro.length )
        obj.innerText = stay_intro.slice(0, 30) + "...";
    }
}*/

function fn_add_form() {
    
    let top_form = document.frm_stay_search;
    let contidion_form = document.frm_stay_condition;

    let checked_star = document.querySelector('input[name="grade_value"]:checked');
    if(checked_star){
        top_form.grade_value.value = checked_star.value;

    } 
    if(contidion_form.fromInput.value != null){
        top_form.min_price.value = contidion_form.fromInput.value;

    } 
    if(contidion_form.toInput.value != null){
        top_form.max_price.value = contidion_form.toInput.value;

    }

    document.querySelector("input.btn_stay_search").click();
    
    return false;

}