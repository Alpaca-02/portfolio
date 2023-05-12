

//배너 url 저장
var bannerUrl = ['close_stranger.html','reverse.html','startBasics.html','inflation.html','flamingo.html'];


//오늘의 책 영역 링크저장 
var todayBook_href =['far_alone','spyfamily','ryu'];
//오늘의 책 영역 이미지 저장
var todayBook_img = ['저만치_혼자서','스파이패밀리','류'];
// 오늘의 책 영역 내용
var todayBook_inner= ['<a><h4>[강산무진] 이후 16년, 김훈 두번째 소설집</h4><br><p>비루한 인간사를 허무하게 바라보던 김훈의 시선은 16년의 세월을 지나며 조금 더 애틋해진 듯 하다. 물론 [저만치 혼자서]에서도 인간의 생애는 그들의 고통이나 절망과 관계없이 무심하게 흐르고, 시간은 살아가는 요령을 알려주는 대가로 그들의 신체를 허물어갈 뿐이다.</p><p class="innerTitle"><span>저만치 혼자서</span><br>김훈 저 | 문학동네</p><div class="tri"></div></a>',
'<a><h4>제 9권 온/오프 동시 발매!</h4><br><p>능력자 스파이 &lt;황혼&gt;은, 보다 좋은 세상을 만들기 위해 매일 첩보임무를 하고 있었다. 어느날, 새로운 곤란한 지령이 떨어지는데... 임무를 위해, 가짜 가족을 만들어, 새로운 생활이 시작되지만?! 화제의 스파이X액션X특수가족 코미디! 제 9권 온/오프 동시 발매!</p><p class="innerTitle"><span>스파이 패밀리 09권</span><br>ENDO Tatsuya 저 | 학산문화사/DCW</p><div class="tri"></div></a>',
'<a><h4>심사위원 만장일치로 선정된 나오키상 수상작!</h4><br><p>"몇십 년 만에 한 번 나올 만한 전대미문의 걸작" 1970~80년대를 배경으로, 할아버지의 죽음을 목격한 주인공이 살인범을 추적하는 과정을 그린 미스터리이자 시대물. 할아버지를 죽인 범인의 단서가 삐죽 머리를 내밀 때마다 급류에 휘말리듯 사건의 중심으로 빨려들어 간다!</p><p class="innerTitle"><span>류</span><br>히가시야마 아키라 저/민경욱 역 | 해피북스투유</p><div class="tri"></div></a>'];
// 오늘의 책 사이드 삼각형 포지션
var tri = ['-80px','50px','180px']


// 일반도서 링크저장


// 일반도서 슬라이드 좌표 ( 나중에 index * -930으로 바꿀것)
// var normalPos=[0,-930,-1860];


// var normalBest_link = [['reverse','close_stranger','inflation','natural_detergents','convenience'],
// ['reverse','inflation','sonMoney','goodbye','convenience'],
// ['reverse','inflation','goodbye','convenience','one_thing']];

// //일반도서 이미지 저장
// var normalBest_img = [['역행자','친밀한이방인','인플레이션에서 살아남기','천연 세제 생활','불편한 편의점'],
// ['역행자','인플레이션에서 살아남기','아들아 돈공부 해야한다','작별인사','불편한 편의점'],
// ['역행자','인플레이션에서 살아남기','작별인사','불편한 편의점','원씽']];

// // 일반도서 타이틀 저장
// var normalBest_title = [['역행자','친밀한 이방인','인플레이션에서 살아남기','천연 세제 생활','불편한 편의점'],
// ['역행자','인플레이션에서 살아남기','아들아, 돈공부 해야한다','작별인사','불편한 편의점'],
// ['역행자','인플레이션에서 살아남기','작별인사','불편한 편의점','원씽(The One Thing)']];

// // 일반도서 작가 저장 
// var normalBest_writer = [['자청','정한아','오건영','박철원','김호연'],
// ['자청','오건영','정선용(정스토리)','김영하','김호연'],
// ['자청','오건영','김영하','김호연','게리 켈러']];

$(function(){

    // 배너영역 시작

    function moveBanner(index){
        $('.banner').css('flex', '0 0 0');
        $('.banner').eq(index).css('flex','20 20 0');
        $('.banner').eq(index+1).css('flex','1 1 0');
        $('.banner').eq(index+2).css('flex','1 1 0');

        $('.banner>div').css('opacity','0');
        $('.banner>div').eq(index).css('opacity','1');

        if(index==3){

            $('.banner').eq(index).css('flex','20 20 0');
            $('.banner').eq(index+1).css('flex','1 1 0');
            $('.banner').eq(index-1).css('flex','1 1 0');
        }
        if(index==4){

            $('.banner').eq(index).css('flex','20 20 0');
            $('.banner').eq(index-2).css('flex','1 1 0');
            $('.banner').eq(index-1).css('flex','1 1 0');
        }
    }

    /* 오토 슬라이드  3초 간격*/ 
    function autoSlide(){
        auto = setInterval(function(){
            $('.rightBtn').trigger('click');
        },3000);
    }

    var auto;
    var index = 0;

    autoSlide();
    moveBanner(index);

    $('#banner').hover(function(){
        clearInterval(auto);
    },function(){
        autoSlide();
    });


    // 배너 클릭시 링크이동 또는 배너 위치 이동
    $('.banner').click(function(){
        indexSub=$(this).index()-1;
        if(index==indexSub){
            window.location.href = 'book_info/'+bannerUrl[index];
        }else{
            index=indexSub;
            moveBanner(index);
        }
    });


    $('.rightBtn').click(function(){
        index++;
        if(index==5){
            index=0;
        }
        moveBanner(index);
    });
    $('.leftBtn').click(function(){
        index--;
        if(index==-1){
            index=4;
        }
        moveBanner(index);
    });


    // 배너영역 종료

    // 오늘의 책 영역 시작
    $('#bookSide .side').click(function(){
        index2 = $(this).index();
        $('#bookImg a').attr('href','book_info/'+todayBook_href[index2]+'.html');


        $('#bookImg a img').attr('src','images/todayBook/'+todayBook_img[index2]+'.jpg').attr('alt',todayBook_img[index2]);


        $('#bookImg .innerImg').html(todayBook_inner[index2]);

        $('#bookImg .innerImg  a').attr('href','book_info/'+todayBook_href[index2]+'.html');

        // 사이드 삼각형 좌표변경
        $('.tri').css('top',tri[index2])

        

        // 사이드 백그라운드 컬러 초기화
        $('#bookSide .side').css('backgroundColor','#9d9d9d');

        // 클릭후 사이드메뉴 백그라운드 컬러
        $('#bookSide .side').hover(function(){
            $(this).css('backgroundColor','#79b4b7');
        },function(){
            $(this).css('backgroundColor','#9d9d9d');
        });

        $(this).css('backgroundColor','#79b4b7');
        $(this).hover(function(){
            $(this).css('backgroundColor','#79b4b7');
        })

        
    });
    // 오늘의 책 영역 종룍

    // 일반도서 역역 시작
    // $('.normalBest_title .day > div').click(function(){
    //     index3 = $(this).index();
    //     $('.normalBest_title .day > div').css('backgroundColor','#9d9d9d');
    //     $(this).css('backgroundColor','#79b4b7');


    //     $('.normalBest > li img').attr('src',function(index2){
    //         return 'images/dayBest/'+normalBest_img[index3][index2]+'.jpg';
    //     })
    //     // 이미지 등장 애니메이션?(임시)
    //     $('.normalBest > li img').hide();
    //     $('.normalBest > li img').fadeIn(200);

        
    //     $('.normalBest > li >p >a').html(function(index2){
    //         return '<strong>'+normalBest_title[index3][index2]+'</strong><br>'+normalBest_writer[index3][index2];
    //     });

    //     $('.normalBest > li >a').attr('href',function(index2){
    //         return 'book_info/'+normalBest_link[index3][index2]+'.html'
    //     });
    //     $('.normalBest > li >p>a').attr('href',function(index2){
    //         return 'book_info/'+normalBest_link[index3][index2]+'.html'
    //     });

    // });







    $('.normalBest_title .day > div').click(function(){
        index3 = $(this).index();

        //일,주,월간 버튼 
        $('.normalBest_title .day > div').css('backgroundColor','#9d9d9d');
        $(this).css('backgroundColor','#79b4b7');
        
        // 도서목록 슬라이드
        $('.normalList').animate({
            left:-(930*index3)
        },500);

    });
    // 일반도서 역역 종료


})
