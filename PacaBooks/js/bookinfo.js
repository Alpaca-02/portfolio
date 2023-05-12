// var bookCount = $('#bookCount').val();
// var oriPrice = $('.oriPrice').slice(0,-2);

$(function(){
    // $('#bookInfo .bookImg').click(function(){
    //     window.open('../book_prev/spyfamily_prev.html','미리보기','width=1000,height=750');
    // });

    // 결제 혜택 토글
    $('#bookInfo .bookExp .event span').on('click',function(){
        $('#bookInfo .bookExp .event ul').toggle();
    });


    // 책 수량별 가격 계산
    var bookCount = $('#bookCount').val();
    var oriPrice = parseInt($('.oriPrice').html());
    var disPrice = parseInt($('.disPrice').html());

    $('#bookCount').on('change',function(){
        var bookCount = $('#bookCount').val();
        $('.oriPrice').html(oriPrice*bookCount+'원');
        $('.disPrice').html(disPrice*bookCount+'원');
    });
    
    // 상단바 스크롤 좌표 => 588
});