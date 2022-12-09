function readImage(input){
	if(input.files && input.files[0]){
		let reader = new FileReader();
		reader.onload=function (event){
			document.querySelector("#preview").src = event.target.result;
			document.querySelector("#preview").classList.remove("hidden");
		}
		reader.readAsDataURL(input.files[0]);
	}
}