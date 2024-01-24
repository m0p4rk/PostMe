// Spring Framework에서 전달한 FlashAttribute인 'error'를 JavaScript로 받기
let error = "${flash.error}";

// JavaScript로 받은 에러 메시지를 HTML 요소에 표시
let errorMessageElement = document.getElementById("error-message");
errorMessageElement.innerHTML = error;
