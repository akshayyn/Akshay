<html>
<head>
	<title>CSE220 Final Project</title>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; CHARSET=windows-1252">
	<script language="JavaScript" src="javascript/matchnet.js"></script>
	<script language="JavaScript" src="javascript/region3.js"></script>
	<script language="JavaScript" src="im/im.js"></script>
	<link rel="stylesheet" type="text/css" href="lib/css/as.css">
	<link rel="stylesheet" type="text/css" href="lib/css/common.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body marginheight=0 marginwidth=0 topmargin=0 leftmargin=0 text="#000000" bgcolor="#ffffff">

<%
   String userName = request.getParameter( "UserName" );
   String password = request.getParameter( "Password" );
   String dobMonth = request.getParameter( "BirthDate_month" );
   String dobDay = request.getParameter( "BirthDate_day" );
   String dobYear = request.getParameter( "BirthDate_year" );
   String email = request.getParameter( "Email" );
   String gender = request.getParameter( "GenderID" );
   String seekingGender = request.getParameter( "SeekingGenderID" );
   String country = request.getParameter( "CountryRegionID" );
   String state = request.getParameter( "State" );
   String city = request.getParameter( "City" );
   String zip = request.getParameter( "PostalCode" );
%>





<div class="container">
<form name="frmWelcome" method="post"ENCTYPE="MULTIPART/FORM-DATA" action="register_03.jsp" onSubmit="return validateFormfrmWelcome()">
<div class="form-group">
	<img src="images/confirm_headline.gif" width="333" height="57" alt="congratulations!">
</div>

<div class="form-group">
	<p>Double check your username and password, you will need them. </p>
</div>

<div class="form-group">
	<label for="pwd">User Name:&nbsp;</label>
	<input class="form-control" type="text" placeholder=<%= userName %> readonly>
</div>

<div class="form-group">
	<label for="pwd">Password:&nbsp;</label>
	<input class="form-control" type="text" placeholder=<%= password %> readonly>
</div>

<div class="form-group">
	<label for="pwd">Date of Birth:&nbsp;</label>
	<input class="form-control" type="text" placeholder=<%= dobMonth %>/<%= dobDay %>/<%= dobYear %> readonly>
</div>

<div class="form-group">
	<label for="pwd">Email Address:&nbsp;</label>
	<input class="form-control" type="text" placeholder=<%= email %> readonly>
</div>

<div class="form-group">
	<label for="pwd">From:&nbsp;</label>
	<input class="form-control" type="text" placeholder=<%= city %>, <%= state %> <%= zip %> <%= country %> readonly>
</div>

<div class="form-group">
	<label for="pwd">You are a:&nbsp;</label>
	<input class="form-control" type="text" placeholder=<%= gender %> seeking <%= seekingGender %> readonly>
</div>

<div class="form-group">
	<div class="col-md-6" colspan="2" align="center"><img src="images/be_smart.gif" width="511" height="22" alt="be smart! introduce yourself with a few words and a photo."></div>
</div>

<div class="form-group">
	<div class="col-md-6" align="right" valign="top"><font class="defaultSmall">What is the first thing you want people to know about you? <br><a href="javascript:launchWindow('greatEssays.html','Help',650,560,'');" class="defaultSmall">Need help?</a></label>
	<div class="col-md-6" valign="top"><textarea name="AboutMe" class="defaultSmall" form-groups="3" height="10" cols="30" maxlength="2000"></textarea></div>
</div>

<div class="form-group">
	<div class="col-md-6" align="right" valign="top"><font class="boldSmall">Upload your photo: </font><br><font class="defaultSmall"><a href="javascript:launchWindow('greatPhotos.html','Help',650,560,'');" class="defaultSmall">Need help?</a></label>
	<div class="col-md-6" valign="top"><input type="file" name="TFileID" value=""></div>
</div>

<div class="form-group">
	<div class="col-md-6" colspan="2" align="center">
		<table width="450" cellspacing="0" cellpadding="0" border="0" >
			<div class="form-group">
				<div class="col-md-6" align="left"><a href="register_01.jsp"  class="default"><img src="images/btn_back_edit.gif" width="224" height="41" border="0" alt="back & edit"></a></div>
				<div class="col-md-6" align="right"><input type="image" name="btnContinue" src="images/btn_continue.gif" width="172" height="40" alt="Continue"></div>
			</div>
		</table>
		
		<input type="hidden" name="UserName" value="<%= userName %>" >
		<input type="hidden" name="Password" value="<%= password %>" >
		<input type="hidden" name="BirthDate_month" value="<%= dobMonth %>" >
		<input type="hidden" name="BirthDate_day" value="<%= dobDay %>" >
		<input type="hidden" name="BirthDate_year" value="<%= dobYear %>" >
		<input type="hidden" name="Email" value="<%= email %>" >
		<input type="hidden" name="GenderID" value="<%= gender %>" >
		<input type="hidden" name="SeekingGenderID" value="<%= seekingGender %>" >
		<input type="hidden" name="CountryRegionID" value="<%= country %>" >
		<input type="hidden" name="State" value="<%= state %>" >
		<input type="hidden" name="City" value="<%= city %>" >
		<input type="hidden" name="PostalCode" value="<%= zip %>" >


		</div>
</div>
</form>
</div>

<script language=JavaScript>

	var blnAbortValidation = false;
	
	function validateFormfrmStep1() {
		if (blnAbortValidation == true) {
			blnAbortValidation = false;
			return true;
		}
		
	var vfrmStep1txtUserName = document.frmStep1.UserName;
	var vfrmStep1pasPassword = document.frmStep1.Password;
	var vfrmStep1pasPassword2 = document.frmStep1.Password2;
	var vfrmStep1dteBirthDate_month = document.frmStep1.BirthDate_month[document.frmStep1.BirthDate_month.selectedIndex].value;
	var vfrmStep1dteBirthDate_day = document.frmStep1.BirthDate_day[document.frmStep1.BirthDate_day.selectedIndex].value;
	var vfrmStep1dteBirthDate_year = document.frmStep1.BirthDate_year[document.frmStep1.BirthDate_year.selectedIndex].value;
	var vfrmStep1txtEmail = document.frmStep1.Email;
	var vfrmStep1radGenderID = document.frmStep1.GenderID;
	var vfrmStep1radSeekingGenderID = document.frmStep1.SeekingGenderID;
	var vfrmStep1selCountryRegionID = document.frmStep1.CountryRegionID;
	var vfrmStep1txtPostalCode = document.frmStep1.PostalCode;
	var vfrmStep1chkNewsLetterMask = document.frmStep1.NewsLetterMask;

	if(IsEmpty(vfrmStep1txtUserName, 'text')) {
		ModalBox(false,"User Name: is required");
		return false;
	}
	if ( InLengthRange(vfrmStep1txtUserName.value, 2, 25) == false) {
		ModalBox(false,"User Name: must be between 2 and 25 characters");
		vfrmStep1txtUserName.focus();
		return false;
	}
	if(IsEmpty(vfrmStep1pasPassword, 'password')) {
		ModalBox(false,"Password: is required");
		return false;
	}
	if ( InLengthRange(vfrmStep1pasPassword.value, 4, 16) == false) {
		ModalBox(false,"Password: must be between 4 and 16 characters");
		vfrmStep1pasPassword.focus();
		return false;
	}
	if(IsEmpty(vfrmStep1pasPassword2, 'password')) {
		ModalBox(false,"Re-enter your password: is required");
		return false;
	}
	if ( InLengthRange(vfrmStep1pasPassword2.value, 4, 16) == false) {
		ModalBox(false,"Re-enter your password: must be between 4 and 16 characters");
		vfrmStep1pasPassword2.focus();
		return false;
	}
	if (vfrmStep1dteBirthDate_month == "") {
		ModalBox(false,"Date of Birth: is required");
		document.frmStep1.BirthDate_month.focus();
		return false;
	}
	if (vfrmStep1dteBirthDate_day == "") {
		ModalBox(false,"Date of Birth: is required");
		document.frmStep1.BirthDate_day.focus();
		return false;
	}
	if (vfrmStep1dteBirthDate_year == "") {
		ModalBox(false,"Date of Birth: is required");
		document.frmStep1.BirthDate_year.focus();
		return false;
	}
	if (!IsValidDate(vfrmStep1dteBirthDate_month, vfrmStep1dteBirthDate_day, vfrmStep1dteBirthDate_year) ){
		ModalBox(false,"The Date of Birth: field is not a valid date");
		document.frmStep1.BirthDate_day.focus();
		return false;
	}
	if(IsEmpty(vfrmStep1txtEmail, 'text')) {
		ModalBox(false,"Email address: is required");
		return false;
	}
	if ( IsEmail(vfrmStep1txtEmail.value, 3) == false) {
		ModalBox(false,"Email address: is not a valid email address");
		vfrmStep1txtEmail.focus();
		return false;
	}
	if ( InLengthRange(vfrmStep1txtEmail.value, null , 255) == false) {
		ModalBox(false,"Email address: cannot have more than 255 characters");
		vfrmStep1txtEmail.focus();
		return false;
	}
	if(IsEmpty(vfrmStep1radGenderID, 'radio')) {
		ModalBox(false,"Your Gender is required");
		return false;
	}
	if(IsEmpty(vfrmStep1radSeekingGenderID, 'radio')) {
		ModalBox(false,"Looking for a: is required");
		return false;
	}
	if(IsEmpty(vfrmStep1selCountryRegionID, 'select')) {
		ModalBox(false,"Country is required");
		return false;
	}
	if(IsEmpty(vfrmStep1txtPostalCode, 'text')) {
		ModalBox(false,"Zip Code is required");
		return false;
	}
	if ( IsAlphaNumeric(vfrmStep1txtPostalCode.value) == false) {
		ModalBox(false,"Zip Code must be alphanumeric (i.e. jsmith33)");
		vfrmStep1txtPostalCode.focus();
		return false;
	}
	if ( InLengthRange(vfrmStep1txtPostalCode.value, 5, 6) == false) {
		ModalBox(false,"Zip Code must be between 5 and 6 characters");
		vfrmStep1txtPostalCode.focus();
		return false;
	}
	if(vfrmStep1pasPassword2.value != vfrmStep1pasPassword.value) {
	   ModalBox(false,"Passwords do not match.  Please re-enter your password.");
	   return false;
	}		var dateTarget = new Date(1985, 12, 6, 0, 0, 0)
	var dateEntered = new Date(vfrmStep1dteBirthDate_year, vfrmStep1dteBirthDate_month, vfrmStep1dteBirthDate_day , 0, 0, 0)
	if ((dateTarget - dateEntered) < 0) {
		alert('You must be at least 18 years old to register.');
	   document.frmStep1.BirthDate_year.focus();
		return false;
	}
}

</script>

<script language=JavaScript>
	var blnAbortValidation = false;
	
	function validateFormfrmWelcome() {
		if (blnAbortValidation == true) {
			blnAbortValidation = false;
			return true;
		}
	
	var vfrmWelcometxaAboutMe = document.frmWelcome.AboutMe;
	var vfrmWelcomefilTFileID = document.frmWelcome.TFileID;
	
	if(!IsEmpty(vfrmWelcometxaAboutMe, 'textarea')) {
		if ( InLengthRange(vfrmWelcometxaAboutMe.value, 0, 2000) == false) {
			ModalBox(false,"Introduce Yourself: must be between 0 and 2000 characters");
			vfrmWelcometxaAboutMe.focus();
			return false;
		}
	}
}
</script>


</body>
</html>