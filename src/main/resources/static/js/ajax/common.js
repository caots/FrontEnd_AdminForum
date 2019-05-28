function errMess(jqXHR, textStatus, errorThrown) {
    console.log('jqXHR:');
    console.log(jqXHR);
    console.log('textStatus:');
    console.log(textStatus);
    console.log('errorThrown:');
    console.log(errorThrown);
}

const URI = "http://123.31.45.240:8686/backend_admin_forum/";
const tokenHeader_value = "thangNaoDungTromApiLamCho";
const tokenHeader_admin_value = sessionStorage.getItem("token");




