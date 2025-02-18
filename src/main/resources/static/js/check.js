$(document).ready(function (){
    $("form").on("submit",function (event){
        if($("#name").val()===''){
            event.preventDefault();
            alert("이름입력 필수")
            $("#name").focus()
            return
        }
        if($("#age").val()===''){
            event.preventDefault();
            alert("나이입력 필수")
            $("#age").focus()
            return
        }
        if($("#address").val()===''){
            event.preventDefault();
            alert("주소입력 필수")
            $("#address").focus()
            return
        }
    })
})