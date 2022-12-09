$(function() {
    $('.icon1').click(function() {
        $('.best_pension').show().siblings('div').hide();
        $('#pensionIcon').attr('src', ctx+'images/Home/themeIcons/house_select.png');
        $('#hotelIcon').attr('src', ctx+'images/Home/themeIcons/hotel.png');
        $('#campingIcon').attr('src', ctx+'images/Home/themeIcons/camping.png');
        $('#hanokIcon').attr('src', ctx+'images/Home/themeIcons/hanok.png');
        $('#selected').attr("value", "펜션");
        $('#room_kind_select').attr("value","pension");
    })
});
$(function() {
    $('.icon2').click(function() {
        $('.best_hotel').show().siblings('div').hide();
        $('#pensionIcon').attr('src', ctx+'images/Home/themeIcons/house.png');
        $('#hotelIcon').attr('src', ctx+'images/Home/themeIcons/hotel_select.png');
        $('#campingIcon').attr('src', ctx+'images/Home/themeIcons/camping.png');
        $('#hanokIcon').attr('src', ctx+'images/Home/themeIcons/hanok.png');
        $('#selected').attr("value", "호텔");
        $('#room_kind_select').attr("value","hotel");
    })
});
$(function() {
    $('.icon3').click(function() {
        $('.best_camping').show().siblings('div').hide();
        $('#pensionIcon').attr('src', ctx+'images/Home/themeIcons/house.png');
        $('#hotelIcon').attr('src', ctx+'images/Home/themeIcons/hotel.png');
        $('#campingIcon').attr('src', ctx+'images/Home/themeIcons/camping_select.png');
        $('#hanokIcon').attr('src', ctx+'images/Home/themeIcons/hanok.png');
        $('#selected').attr("value", "캠핑");
        $('#room_kind_select').attr("value","camping");
    })
});
$(function() {
    $('.icon4').click(function() {
        $('.best_hanok').show().siblings('div').hide();
        $('#pensionIcon').attr('src', ctx+'images/Home/themeIcons/house.png');
        $('#hotelIcon').attr('src', ctx+'images/Home/themeIcons/hotel.png');
        $('#campingIcon').attr('src', ctx+'images/Home/themeIcons/camping.png');
        $('#hanokIcon').attr('src', ctx+'images/Home/themeIcons/hanok_select.png');
        $('#selected').attr("value", "한옥");
        $('#room_kind_select').attr("value","hanok");
    })
});