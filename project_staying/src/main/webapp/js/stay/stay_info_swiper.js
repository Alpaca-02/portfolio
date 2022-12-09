var swiper = new Swiper(".stay_image_swiper_bottom", {
spaceBetween: 10,
slidesPerView: 4,
freeMode: true,
watchSlidesProgress: true
});
var swiper2 = new Swiper(".stay_image_swiper_top", {
spaceBetween: 10,
navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev"
},
thumbs: {
    swiper: swiper
}
});

