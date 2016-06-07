<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%!
	String PREFIX = "session.login";
	String ACCOUNT = PREFIX + ".account";
%>
<%
		try
		{
			Object accountObject = session.getValue(ACCOUNT);
			
			// If no account object was put in the session, or
			// if one exists but it is not a hashtable, then
			// redirect the user to the original login page
			
			if (accountObject == null)
				throw new RuntimeException("You need to log in to use this service!");
				
			if (!(accountObject instanceof Hashtable))
				throw new RuntimeException("You need to log in to use this service!");
				
			Hashtable account = (Hashtable) accountObject;
			
			String userName = (String) account.get("name");
%>

	<HTML>
	<head><TITLE>User Messages for <%=userName%></TITLE>
	<link rel="stylesheet" href="css/loggedin.css">
<link href="css/common.css" rel="stylesheet">
	<meta charset="utf-8">
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script>
  function addToContacts(){
	  var message = "hey " +document.getElementById("hiddenUser").value; ;
	  var hiddenUser = document.getElementById("hiddenUser").value;
createXMLHttpRequest();

var queryString = "message="+message+"&hiddenUser="+hiddenUser;
xmlHttp.open("POST", "addContactProcess.jsp", true);
xmlHttp.onreadystatechange = handleStateChangeForContact;
xmlHttp.setRequestHeader("Content-Type",
"application/x-www-form-urlencoded;");
xmlHttp.send(queryString);
  }
  function handleStateChangeForContact(){
			if(xmlHttp.readyState == 4) {
if(xmlHttp.status == 200) {
	document.getElementById("addContBtn").innerHTML = "<span>Added as a Contact<span>";

}
}
		}
  
function advancedSearch(evt){
var SeekingGenderID = document.getElementById("SeekingGenderID").value;
var GenderID = document.getElementById("GenderID").value;
var MinAge = document.getElementById("MinAge").value;
var MaxAge = document.getElementById("MaxAge").value;
var CountryRegionID = document.getElementById("CountryRegionID").value;
var stateName = document.getElementById("stateName").value;
var cityName = document.getElementById("cityName").value;
createXMLHttpRequest();

var queryString = "SeekingGenderID="+SeekingGenderID+"GenderID="+GenderID+"MinAge="+MinAge+"MaxAge="+MaxAge+"CountryRegionID="+CountryRegionID+"stateName="+stateName+"cityName="+cityName;
xmlHttp.open("POST", "searchUsers.jsp", true);
xmlHttp.onreadystatechange = handleStateChangeForLookup;
xmlHttp.setRequestHeader("Content-Type",
"application/x-www-form-urlencoded;");
xmlHttp.send(queryString);
evt.preventDefault();

		}
 function sendMessage(){
var message = document.getElementById("inputBody").value;
createXMLHttpRequest();

var queryString = "message="+message+"&toUser="+replyTo;
xmlHttp.open("POST", "sendMessage.jsp", true);
xmlHttp.onreadystatechange = handleStateChangeForMessage;
xmlHttp.setRequestHeader("Content-Type",
"application/x-www-form-urlencoded;");
xmlHttp.send(queryString);
		}
function searchByNumber(evt){
var number = document.getElementById("LookupMemberID").value;
createXMLHttpRequest();

var queryString = "LookupMemberID="+number;
xmlHttp.open("POST", "lookupByNumber.jsp", true);
xmlHttp.onreadystatechange = handleStateChangeForLookup;
xmlHttp.setRequestHeader("Content-Type",
"application/x-www-form-urlencoded;");
xmlHttp.send(queryString);
evt.preventDefault();

		}
function searchByName(evt){
var name = document.getElementById("LookupMemberName").value;
createXMLHttpRequest();

var queryString = "LookupMemberName="+name;
xmlHttp.open("POST", "lookupByName.jsp", true);
xmlHttp.onreadystatechange = handleStateChangeForLookup;
xmlHttp.setRequestHeader("Content-Type",
"application/x-www-form-urlencoded;");
xmlHttp.send(queryString);
evt.preventDefault();

		}
function handleStateChangeForLookup(){
if(xmlHttp.readyState == 4) {
if(xmlHttp.status == 200) {
var newdiv = document.createElement("div");
newdiv.innerHTML = xmlHttp.responseText;
var container = document.getElementById("searchResult");
container.appendChild(newdiv);
	document.getElementById("searchResult").innerHTML = xmlHttp.responseText;

}
}
		}
function handleStateChangeForMessage(){
			if(xmlHttp.readyState == 4) {
if(xmlHttp.status == 200) {
	alert("message Sent");

}
}
		}
  
    function makeTableScroll() {
            // Constant retrieved from server-side via JSP
            var maxRows = 3;

            var table = document.getElementById('messagesTable');
            var wrapper = table.parentNode;
            var rowsInTable = table.rows.length;
			if(rowsInTable < 2){
				wrapper.style.display = 'none';
			}
            var height = 0;
            if (rowsInTable > maxRows) {
                for (var i = 0; i < maxRows; i++) {
                    height += table.rows[i].clientHeight;
                }
                wrapper.style.height = height + "px";
            }
        }
function deleteMessages(){
	rows = [];
createXMLHttpRequest();
var url = "GetAndPostExample?timeStamp=" + new Date().getTime();
var queryString = createQueryString();
xmlHttp.open("POST", "delete.jsp", true);
xmlHttp.onreadystatechange = handleStateChange;
xmlHttp.setRequestHeader("Content-Type",
"application/x-www-form-urlencoded;");
xmlHttp.send(queryString);
		}
var selected = [];
var rows = [];
function createQueryString() {
	var checkboxes = document.getElementsByName('msgList');

for (var i=0; i<checkboxes.length; i++) {
    if (checkboxes[i].checked) {
        selected.push(checkboxes[i].value);
		rows.push(checkboxes[i].parentNode.parentNode)
    }
}



var queryString = "msgList=" + selected;
return queryString;
}
var xmlHttp;
function createXMLHttpRequest() {
if (window.ActiveXObject) {
xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest) {
xmlHttp = new XMLHttpRequest();
}
}


function handleStateChange() {
if(xmlHttp.readyState == 4) {
if(xmlHttp.status == 200) {
	for (var i=0; i<rows.length; i++) {
    
       var table =  document.getElementsByName("messagesTable");
	   document.getElementById("messagesTable").deleteRow(rows[i].rowIndex);
    }


}
}
}
var replyTo;
function saveRec(element){
	replyTo = element.parentNode.parentNode.cells[0].innerHTML;
}

		</script>
  </head>
	<body onload="makeTableScroll()">
	<nav class="navbar navbar-inverse">
	 
  <div class="navbar-toggleable-xs" id="exCollapsingNavbar2">
    <a class="navbar-brand" href="#">Welcome <%=userName%></a>
    <ul class="nav navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href='details.jsp?type=2&data=<%=userName%>'>View My Details</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href='update.jsp'>Update My Information</a></li>
		 <li class="nav-item">
        <a class="nav-link" href='viewContacts.jsp'>View My Contacts</a></li>
		<li class="nav-item">
        <a class="nav-link" href='change.jsp'>Change my password</a>
      </li>
      <li class="nav-item pull-xs-right">
        <a class="nav-link" href='logout.jsp'>Logout</a>
      </li>
    </ul>
	</div>
</nav>
	<div class="container">
	
<!-- /.modal compose message -->
    <div class="modal fade"  id="myModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">Reply</h4>
          </div>
          <div class="modal-body">
            <form role="form" class="form-horizontal">
                
                <div class="form-group">
                  <label class="col-sm-12" for="inputBody">Message</label>
                  <div class="col-sm-12"><textarea class="form-control" id="inputBody" rows="5"></textarea></div>
                </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button> 
            
            <button type="button" class="btn btn-primary " onClick="sendMessage()" data-dismiss="modal">Send <i class="fa fa-arrow-circle-right fa-lg"></i></button>
            
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal compose message -->
	<!-- /Add to contacts modal -->
  

<%
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
         
         stmt = con.createStatement();
         rs = stmt.executeQuery("SELECT * FROM messages WHERE userName='" + userName + "' ORDER BY messageID");
    %>
         <div class="row">
		<form class="messageForm" name='deleteMessagesForm' method='post' action='delete.jsp'>
		
		<table class="table table-bordered table-hover table-condensed" id="messagesTable">
		<thead>
		<tr>
		
		<th>From User</th>
		<th>Message</th>
		<th>Date</th>
		<th>Reply to User</th>
		<th><span class='glyphicon glyphicon-remove' onClick='deleteMessages()'></span>Delete</th>
		</tr></thead>
    
	<%
         int nRows = 0;
         while (rs.next()) {
            nRows++;
            String messageID = rs.getString("messageID");
            String fromUser = rs.getString("fromUser");
            String message = rs.getString("message");
            String messageDate = rs.getString("messageDate");
    %>   
            <tr>
				
				<td><%=fromUser%></td>
				<td><%=message%></td>
				<td><%=messageDate%></td>
				<td><button type="button" id="replyButton" class="btn btn-info btn-md" data-toggle="modal" onClick="saveRec(this)" data-target="#myModal">Send Reply</button></A></td>
				<td><input type='checkbox' name='msgList' value='<%=messageID%>'> Delete</td>
				</tr>
	<%
         } %>
         
        
         
         
         </table>
		 
        </FORM>         
         </div>
         <BR><BR>
		 <div>
<div class="row">
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">Search by member number</a></li>
    <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">Search by Name</a></li>
    <li role="presentation"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">Advanced Search</a></li>
    
  </ul>
</div>
  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="tab1">
	<form name='lookupByNumberForm'>
        
        
            <p>Please enter the member number of the person you want to look up.</p>
        
         
         <input type='text' id="LookupMemberID" name='LookupMemberID' value='' size='18' maxlength='20' placeholder="Member Number"></td>
            <input type='submit' onClick="searchByNumber(event)" class="btn btn-primary" name='cmdGo1' value='Search'>
        
         </form> 
	</div>
    <div role="tabpanel" class="tab-pane" id="tab2">
	<form name='lookupByNameForm'>
         <p>Please enter the username of the person you want to look up.</p>
         
            <input type='text' id="LookupMemberName" name='LookupMemberName' value='' size='18' maxlength='20'>
            <input type='submit' onClick="searchByName(event)" class="btn btn-primary" name='cmdGo2' value='See Profile'>
			
         </form>
	</div>
    <div role="tabpanel" class="tab-pane" id="tab3">
	<form class="form-horizontal" role="form" name='searchUserForm' method='post' action='searchUsers.jsp'>
  <div class="form-group">
    <label class="control-label col-sm-2" for="email">Show Me:</label>
    <div class="col-sm-4">
      <select id="SeekingGenderID" name='SeekingGenderID' value=''>
               <option value='Male'>Male</option>
               <option value='Female' selected>Female</option>
               </select>
    </div>
	
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="GenderID">Seeking:</label>
    <div class="col-sm-10">
      <select id="GenderID" name='GenderID' value=''>
               <option value='Male' selected>Male</option>
               <option value='Female'>Female</option>
               </select>
    </div>

  </div>
  <div class="form-group"> 
   
      <label class="control-label col-sm-2" for="MinAge">Minumum Age:</label>
    
	 <div class="col-sm-10">
	<input type='text' id="MinAge" name='MinAge' value='' size='2' maxlength='2'>
	</div>
  </div>
   <div class="form-group"> 
    
      <label class="control-label col-sm-2" for="MaxAge">Maximum Age:</label>
   
	 <div class="col-sm-10">
	<input type='text' id="MaxAge" name='MaxAge' value='' size='2' maxlength='2'>
	</div>
  </div>
  <div class="form-group"> 
    
      <label class="control-label col-sm-2" for="location">Search Location:</label>
     
	 <div class="col-sm-3">
	 <select id="CountryRegionID" name='CountryRegionID'>
         	<option value='Afghanistan'         >Afghanistan</option>
         	<option value='Albania'             >Albania</option>
         	<option value='Algeria'             >Algeria</option>
         	<option value='Andorra'             >Andorra</option>
         	<option value='Angola'              >Angola</option>
         	<option value='Anguilla'            >Anguilla</option>
         	<option value='Antarctica'          >Antarctica</option>
         	<option value='Antigua & Barbuda'   >Antigua & Barbuda</option>
         	<option value='Argentina'           >Argentina</option>
         	<option value='Armenia'             >Armenia</option>
         	<option value='Aruba'               >Aruba</option>
         	<option value='Australia'           >Australia</option>
         	<option value='Austria'             >Austria</option>
         	<option value='Azerbaijan'          >Azerbaijan</option>
         	<option value='Bahamas'             >Bahamas</option>
         	<option value='Bahrain'             >Bahrain</option>
         	<option value='Bangladesh'          >Bangladesh</option>
         	<option value='Barbados'            >Barbados</option>
         	<option value='Belarus'             >Belarus</option>
         	<option value='Belgium'             >Belgium</option>
         	<option value='Belize'              >Belize</option>
         	<option value='Benin'               >Benin</option>
         	<option value='Bermuda'             >Bermuda</option>
         	<option value='Bhutan'              >Bhutan</option>
         	<option value='Bolivia'             >Bolivia</option>
         	<option value='Bosnia-Herzgna'      >Bosnia-Herzgna</option>
         	<option value='Botswana'            >Botswana</option>
         	<option value='Brazil'              >Brazil</option>
         	<option value='Brunei'              >Brunei</option>
         	<option value='Bulgaria'            >Bulgaria</option>
         	<option value='Burkina Faso'        >Burkina Faso</option>
         	<option value='Burundi'             >Burundi</option>
         	<option value='Cambodia'            >Cambodia</option>
         	<option value='Cameroon'            >Cameroon</option>
         	<option value='Canada'              >Canada</option>
         	<option value='Cape Verde'          >Cape Verde</option>
         	<option value='Cayman Islands'      >Cayman Islands</option>
         	<option value='Central African Rep.'>Central African Rep.</option>
         	<option value='Chad'                >Chad</option>
         	<option value='Chile'               >Chile</option>
         	<option value='China'               >China</option>
         	<option value='Christmas Island'    >Christmas Island</option>
         	<option value='Cocos (Keeling) Is.' >Cocos (Keeling) Is.</option>
         	<option value='Colombia'            >Colombia</option>
         	<option value='Comoros'             >Comoros</option>
         	<option value='Congo'               >Congo</option>
         	<option value='Congo (Zaire)'       >Congo (Zaire)</option>
         	<option value='Cook Islands'        >Cook Islands</option>
         	<option value='Costa Rica'          >Costa Rica</option>
         	<option value='Cote DIvoire'        >Cote D'Ivoire</option>
         	<option value='Croatia (Hrvatska)'  >Croatia (Hrvatska)</option>
         	<option value='Cyprus'              >Cyprus</option>
         	<option value='Czech Rep.'          >Czech Rep.</option>
         	<option value='Denmark'             >Denmark</option>
         	<option value='Djibouti'            >Djibouti</option>
         	<option value='Dominica'            >Dominica</option>
         	<option value='Dominican Rep.'      >Dominican Rep.</option>
         	<option value='East Timor'          >East Timor</option>
         	<option value='Ecuador'             >Ecuador</option>
         	<option value='Egypt'               >Egypt</option>
         	<option value='El Salvador'         >El Salvador</option>
         	<option value='Equatorial Guinea'   >Equatorial Guinea</option>
         	<option value='Eritrea'             >Eritrea</option>
	       <option value='Estonia'             >Estonia</option>
         	<option value='Ethiopia'            >Ethiopia</option>
         	<option value='Falkland Is.'        >Falkland Is.</option>
         	<option value='Faroe Islands'       >Faroe Islands</option>
         	<option value='Fiji Islands'        >Fiji Islands</option>
         	<option value='Finland'             >Finland</option>
         	<option value='France'              >France</option>
         	<option value='French Guiana'       >French Guiana</option>
         	<option value='French Polynesia'    >French Polynesia</option>
         	<option value='French So. Terr.'    >French So. Terr.</option>
         	<option value='Gabon'               >Gabon</option>
         	<option value='Gambia'              >Gambia</option>
         	<option value='Georgia'             >Georgia</option>
         	<option value='Germany'             >Germany</option>
         	<option value='Ghana'               >Ghana</option>
         	<option value='Gibraltar'           >Gibraltar</option>
         	<option value='Greece'              >Greece</option>
         	<option value='Greenland'           >Greenland</option>
         	<option value='Grenada'             >Grenada</option>
         	<option value='Guadeloupe'          >Guadeloupe</option>
         	<option value='Guam'                >Guam</option>
         	<option value='Guatemala'           >Guatemala</option>
         	<option value='Guinea'              >Guinea</option>
         	<option value='Guinea-Bissau'       >Guinea-Bissau</option>
         	<option value='Guyana'              >Guyana</option>
         	<option value='Haiti'               >Haiti</option>
         	<option value='Honduras'            >Honduras</option>
         	<option value='Hong Kong (China)'   >Hong Kong (China)</option>
         	<option value='Hungary'             >Hungary</option>
         	<option value='Iceland'             >Iceland</option>
         	<option value='India'               >India</option>
         	<option value='Indonesia'           >Indonesia</option>
         	<option value='Ireland'             >Ireland</option>
         	<option value='Israel'              >Israel</option>
         	<option value='Italy'               >Italy</option>
         	<option value='Jamaica'             >Jamaica</option>
         	<option value='Japan'               >Japan</option>
         	<option value='Jordan'              >Jordan</option>
         	<option value='Kazakhstan'          >Kazakhstan</option>
         	<option value='Kenya'               >Kenya</option>
         	<option value='Kiribati'            >Kiribati</option>
         	<option value='Korea, South'        >Korea, South</option>
         	<option value='Kuwait'              >Kuwait</option>
         	<option value='Kyrgyzstan'          >Kyrgyzstan</option>
         	<option value='Laos'                >Laos</option>
         	<option value='Latvia'              >Latvia</option>
         	<option value='Lebanon'             >Lebanon</option>
         	<option value='Lesotho'             >Lesotho</option>
         	<option value='Liechtenstein'       >Liechtenstein</option>
         	<option value='Lithuania'           >Lithuania</option>
         	<option value='Luxembourg'          >Luxembourg</option>
         	<option value='Macau (China)'       >Macau (China)</option>
         	<option value='Macedonia'           >Macedonia</option>
         	<option value='Madagascar'          >Madagascar</option>
         	<option value='Malawi'              >Malawi</option>
         	<option value='Malaysia'            >Malaysia</option>
         	<option value='Maldives'            >Maldives</option>
         	<option value='Mali'                >Mali</option>
         	<option value='Malta'               >Malta</option>
         	<option value='Martinique'          >Martinique</option>
         	<option value='Mauritania'          >Mauritania</option>
         	<option value='Mauritius'           >Mauritius</option>
         	<option value='Mayotte'             >Mayotte</option>
         	<option value='Mexico'              >Mexico</option>
         	<option value='Moldova'             >Moldova</option>
         	<option value='Monaco'              >Monaco</option>
         	<option value='Mongolia'            >Mongolia</option>
         	<option value='Montserrat'          >Montserrat</option>
         	<option value='Morocco'             >Morocco</option>
         	<option value='Mozambique'          >Mozambique</option>
         	<option value='Myanmar'             >Myanmar</option>
         	<option value='Namibia'             >Namibia</option>
         	<option value='Nauru'               >Nauru</option>
         	<option value='Nepal'               >Nepal</option>
         	<option value='Netherlands'         >Netherlands</option>
         	<option value='Netherlands Ant.'    >Netherlands Ant.</option>
         	<option value='New Caledonia'       >New Caledonia</option>
         	<option value='New Zealand'         >New Zealand</option>
         	<option value='Nicaragua'           >Nicaragua</option>
         	<option value='Niger'               >Niger</option>
         	<option value='Nigeria'             >Nigeria</option>
         	<option value='Niue'                >Niue</option>
         	<option value='No. Mariana Is.'     >No. Mariana Is.</option>
         	<option value='Norfolk Island'      >Norfolk Island</option>
         	<option value='Norway'              >Norway</option>
         	<option value='Oman'                >Oman</option>
         	<option value='Pakistan'            >Pakistan</option>
         	<option value='Palau'               >Palau</option>
         	<option value='Panama'              >Panama</option>
         	<option value='Papua new Guinea'    >Papua new Guinea</option>
         	<option value='Paraguay'            >Paraguay</option>
         	<option value='Peru'                >Peru</option>
         	<option value='Philippines'         >Philippines</option>
         	<option value='Pitcairn Is.'        >Pitcairn Is.</option>
         	<option value='Poland'              >Poland</option>
         	<option value='Portugal'            >Portugal</option>
         	<option value='Puerto Rico'         >Puerto Rico</option>
         	<option value='Qatar'               >Qatar</option>
         	<option value='Reunion'             >Reunion</option>
         	<option value='Romania'             >Romania</option>
         	<option value='Russia'              >Russia</option>
         	<option value='Rwanda'              >Rwanda</option>
         	<option value='Saint Helena'        >Saint Helena</option>
         	<option value='Saint Kitts & Nevis' >Saint Kitts & Nevis</option>
         	<option value='Saint Lucia'         >Saint Lucia</option>
         	<option value='Samoa'               >Samoa</option>
         	<option value='San Marino'          >San Marino</option>
         	<option value='Sandwich Islands'    >Sandwich Islands</option>
         	<option value='Sao Tome & Prin.'    >Sao Tome & Prin.</option>
         	<option value='Saudi Arabia'        >Saudi Arabia</option>
         	<option value='Senegal'             >Senegal</option>
         	<option value='Seychelles'          >Seychelles</option>
         	<option value='Sierra Leone'        >Sierra Leone</option>
         	<option value='Singapore'           >Singapore</option>
         	<option value='Slovakia'            >Slovakia</option>
         	<option value='Slovenia'            >Slovenia</option>
         	<option value='Solomon Islands'     >Solomon Islands</option>
         	<option value='Somalia'             >Somalia</option>
         	<option value='South Africa'        >South Africa</option>
         	<option value='Spain'               >Spain</option>
         	<option value='Sri Lanka'           >Sri Lanka</option>
         	<option value='St. Pierre & Miqn'   >St. Pierre & Miq'n</option>
         	<option value='St. Vincent & Greds.'>St. Vincent & Gred's.</option>
         	<option value='Sudan'               >Sudan</option>
         	<option value='Suriname'            >Suriname</option>
         	<option value='Svalbard & J. Mayn'  >Svalbard & J. May'n</option>
         	<option value='Swaziland'           >Swaziland</option>
         	<option value='Sweden'              >Sweden</option>
         	<option value='Switzerland'         >Switzerland</option>
         	<option value='Syria'               >Syria</option>
         	<option value='Taiwan'              >Taiwan</option>
         	<option value='Tajikistan'          >Tajikistan</option>
         	<option value='Tanzania'            >Tanzania</option>
         	<option value='Thailand'            >Thailand</option>
         	<option value='The Vatican'         >The Vatican</option>
         	<option value='Togo'                >Togo</option>
         	<option value='Tokelau'             >Tokelau</option>
         	<option value='Tonga'               >Tonga</option>
         	<option value='Trinidad & Tobago'   >Trinidad & Tobago</option>
         	<option value='Tunisia'             >Tunisia</option>
         	<option value='Turkey'              >Turkey</option>
         	<option value='Turkmenistan'        >Turkmenistan</option>
         	<option value='Turks & Caicos Is.'  >Turks & Caicos Is.</option>
         	<option value='Tuvalu'              >Tuvalu</option>
         	<option value='UAE'                 >UAE</option>
         	<option value='Uganda'              >Uganda</option>
         	<option value='Ukraine'             >Ukraine</option>
	       <option value='United Kingdom'      >United Kingdom</option>
         	<option value='Uruguay'             >Uruguay</option>
         	<option value='USA'  selected       >USA</option>
         	<option value='Uzbekistan'          >Uzbekistan</option>
         	<option value='Vanuatu'             >Vanuatu</option>
         	<option value='Venezuela'           >Venezuela</option>
	       <option value='Vietnam'             >Vietnam</option>
	       <option value='Virgin Islands (UK)' >Virgin Islands (UK)</option>
         	<option value='Virgin Islands (USA)'>Virgin Islands (USA)</option>
           <option value='Wallis & Futuna Is.' >Wallis & Futuna Is.</option>
         	<option value='Western Sahara'      >Western Sahara</option>
	       <option value='Yemen'               >Yemen</option>
           <option value='Yugoslavia'          >Yugoslavia</option>
         	<option value='Zambia'              >Zambia</option>
         	<option value='Zimbabwe'            >Zimbabwe</option>
         </select>
	</div>
	
  </div>
   <div class="form-group"> 
    
      <label class="control-label col-sm-2" for="stateName">State:</label>
   
	 <div class="col-sm-10">
	<input type='text' name='stateName' id="stateName" value='' size='18' maxlength='40'>
	</div>
  </div>
  <div class="form-group"> 
    
      <label class="control-label col-sm-2" for="cityName">City:</label>
    
	 <div class="col-sm-10">
	<input type='text' name='cityName' id="cityName" value='' size='18' maxlength='40'>
	</div>
  </div>
  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-primary" onClick="advancedSearch(event)">Submit</button>
    </div>
  </div>
</form>
	 
	</div>
   <div id="searchResult">
   
   </div>
  </div>

</div>
         
   </div>  
         


        
		 <%
      }
      catch (Exception e) {
         out.println
         ("Could not connect to the users database.<P>");
         out.println("The error message was");
         out.println("<PRE>");
         out.println(e.getMessage());
         out.println("</PRE>");
      }
      finally {
         if (rs != null) {
            try { rs.close(); }
            catch (SQLException ignore) {}
         }
         if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException ignore) {}
         }
         if (con != null) {
            try { con.close(); }
            catch (SQLException ignore) {}
         }
      }

	  
      out.println("</BODY>");
      out.println("</HTML>");
			
	}
		catch (RuntimeException e)
		{
			out.println("<script language=\"javascript\">");
			out.println("alert(\"You need to log in to use this service!\");");
			out.println("</script>");
			
			out.println("<a href='index.html'>Click Here</a> to go to the main page.<br><br>");
			
			out.println("Or Click on the button to exit<FORM><INPUT onClick=\"javascipt:window.close()\" TYPE=\"BUTTON\" VALUE=\"Close Browser\" TITLE=\"Click here to close window\" NAME=\"CloseWindow\" STYLE=\"font-family:Verdana, Arial, Helvetica; font-size:smaller; font-weight:bold\"></FORM>");
			
			log(e.getMessage());
			return;
		}
%>