

//---------------------Input type File Setting------------------------------------------//

function setPath(f) {
	document.getElementById('mypath').value = f;
	//document.getElementById('mypath').value = "";
}


function browse() 
{  //alert("hello");
	document.getElementById('realFile').click();
}
//----------------------------validations-------------------------//
    

 
function validate()
      {

        var flag = true;
	var url = $("#Username").val();
     
    
	
	
	if (Username == "")
        {
	 $("#msgUsername").text("Enter User Name");
	 return false;
	}
     
	


      if(flag)
		{
		$("#formData").submit();
		}
	
	
}

function removeMessage(id) {
	$("#" + id).text("");
}
