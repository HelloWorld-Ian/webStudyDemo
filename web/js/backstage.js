window.onload =function(){
    $(function(){
   $(".btn_nav").on("click",function(){
     $(this).siblings(".smenu").toggleClass("maxmore");
     $(this).parent().siblings(".item").find(".smenu").removeClass("maxmore");
   });
        let jsonArray={};
        $.ajax({
            method:"get",
            url:"/Info/getBasic",
            async:false,
            success:function (data) {
                jsonArray=JSON.parse(data);
            }
        })
        $("#id_display").html(jsonArray.user_id);
        $("#name_display").html(jsonArray.user_name);
        $("#sex_display").html(jsonArray.user_sex);
        $("#date_display").html(jsonArray.user_date);
        $("#city_display").html(jsonArray.user_city);
        $("#text_display").html(jsonArray.user_introduction);
        $("#id").val(jsonArray.user_id);
        $("#name").val(jsonArray.user_name);
        $("#inputdate").val(jsonArray.user_date);
        $("#city").val(jsonArray.user_city);
        $("#introduction").val(jsonArray.user_introduction);
        if(jsonArray.user_sex==="男"){
            $("#inlineRadio1").prop("checked","checked");
        }else{
            $("#inlineRadio2").prop("checked","checked");
        }
        let EduJsonArray={};
        $.ajax({
            method:"get",
            url:"/Info/getEdu",
            async:false,
            success:function (data) {
                EduJsonArray=JSON.parse(data);
            }
        })
        $("#schoolDisplay").html(EduJsonArray.school);
        $("#majorDisplay").html(EduJsonArray.major);
        $("#timeDisplay").html(EduJsonArray.time);
        $("#backgroundDisplay").html(EduJsonArray.background);
        $("#school").val(EduJsonArray.school);
        $("#year-select").val(EduJsonArray.time);
        $("#major").val(EduJsonArray.major);
        $("#background").val(EduJsonArray.background);
 })
 

var d = new DateJs({
    inputEl: '#inputdate',
	el: '#date'
            })
$("#city").on("click",function (e) {
    SelCity(this,e);
    console.log("inout",$(this).val(),new Date())
});

laydate.render({
    elem: '#year-select'
    ,type: 'year'
    ,range: true
  });
}

// //getBasicInfo
// $("#basic_submit").click(function () {
//     let user_id=$("#id").val();
//     let user_name=$("#name").val();
//     let user_date=$("#date").val();
//     let user_city=$("#city").val();
//     let user_introduction=$("#introduction").val();
//     let sex_male=$("#inlineRadio1");
//     let sex_female=$("#inlineRadio2");
//     let user_sex=sex_male.checked===true?sex_male.value:sex_female.value;
//     alert(user_sex);
// })