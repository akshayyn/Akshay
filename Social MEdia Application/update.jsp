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
			
			String user = (String) account.get("name");
			
			//////////////////////////////////////////////
			// Display Messages for the user who logged in
			//////////////////////////////////////////////
%>

		<HTML>
		<HEAD>
		<TITLE>Updating Information for <%=user%></TITLE>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
function createXMLHttpRequest() {
if (window.ActiveXObject) {
xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest) {
xmlHttp = new XMLHttpRequest();
}
}

function update(evt){
	rows = [];
createXMLHttpRequest();

var queryString = createQueryString();
xmlHttp.open("POST", "updated.jsp", true);
xmlHttp.onreadystatechange = handleStateChange;
xmlHttp.setRequestHeader("Content-Type",
"application/x-www-form-urlencoded;");
xmlHttp.send(queryString);
evt.preventDefault();
		}

function createQueryString() {
	var queryString;
queryString=
      	 
          "Email="+document.getElementById("Email").value+
          "Country="+document.getElementById("Country").value+
          "State="+document.getElementById("State").value+
          "City="+document.getElementById("City").value+
          "PostalCode="+document.getElementById("PostalCode").value+
          "AboutMe1="+document.getElementById("AboutMe1").value+
          "StudiesEmphasis="+document.getElementById("StudiesEmphasis").value+
          "RelocateFlag="+document.getElementById("RelocateFlag").value+
          "Headline="+document.getElementById("Headline").value+
          "Occupation="+document.getElementById("Occupation").value+
          "OccupationDescription="+document.getElementById("OccupationDescription").value+
          "GrewUpIn="+document.getElementById("GrewUpIn").value+
          "ZodiacSign="+document.getElementById("ZodiacSign").value+
          "Languages="+document.getElementById("Languages").value+
          "RelationshipType="+document.getElementById("RelationshipType").value+
          "MaritalStatus="+document.getElementById("MaritalStatus").value+
          "HairColor="+document.getElementById("HairColor").value+
          "IncomeLevel="+document.getElementById("IncomeLevel").value+
          "EducationLevel="+document.getElementById("EducationLevel").value+
          "BodyType="+document.getElementById("BodyType").value+
          
          "Religion="+document.getElementById("Religion").value+
          "EyeColor="+document.getElementById("EyeColor").value+
          "Ethnicity="+document.getElementById("Ethnicity").value+
          "PoliticalOrientation="+document.getElementById("PoliticalOrientation").value+
          "IntelligenceImportance="+document.getElementById("IntelligenceImportance").value+
          "SmokingHabits="+document.getElementById("SmokingHabits").value+
          "ActivityLevel="+document.getElementById("ActivityLevel").value+
          "Custody="+document.getElementById("Custody").value+
          "DrinkingHabits="+document.getElementById("DrinkingHabits").value+
          "MoreChildrenFlag="+document.getElementById("MoreChildrenFlag").value+
          "AboutMe2="+document.getElementById("AboutMe2").value+
          "PersonalityTrait="+document.getElementById("PersonalityTrait").value+
          "LeisureActivity="+document.getElementById("LeisureActivity").value+
          "PerfectMatchEssay="+document.getElementById("PerfectMatchEssay").value+
          "Cuisine="+document.getElementById("Cuisine").value+
          "Music="+document.getElementById("Music").value+
          "Reading="+document.getElementById("Reading").value+
          "PerfectFirstDateEssay="+document.getElementById("PerfectFirstDateEssay").value+
          "EntertainmentLocation="+document.getElementById("EntertainmentLocation").value+
          "PhysicalActivity="+document.getElementById("PhysicalActivity").value+
          "IdealRelationshipEssay="+document.getElementById("IdealRelationshipEssay").value+
          "LearnFromThePastEssay="+document.getElementById("LearnFromThePastEssay").value;
return queryString;
}
function handleStateChange() {
if(xmlHttp.readyState == 4) {
if(xmlHttp.status == 200) {
	
    
      alert("Updated");
   


}
}
}
var selected = [];
var rows = [];
</script>
		</HEAD>
		<BODY>
<nav class="navbar navbar-default">
	 
  <div class="navbar-toggleable-xs" id="exCollapsingNavbar2">
    <a class="navbar-brand" href="loggedin.jsp">Welcome <%=user%></a>
    <ul class="nav navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href='details.jsp?type=2&data=<%=user%>'>View My Details</a>
      </li>
      <li class="nav-item active">
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
<div class="row">
<div class="col-lg-12">
		
<%	
		try
		{
		  Connection con = null;
		  Statement stmt = null;
		  ResultSet rs = null;
		  
         Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
         
         stmt = con.createStatement();
         rs = stmt.executeQuery("SELECT * FROM userstable WHERE UserName='" + user + "'");
         
         out.println("<FORM NAME='updateForm'>");
         out.println("<TABLE class='table'>");
         
         String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
         String FileLocation;
         int UserID;
         int DOBmonth;
         int DOBday;
         int DOByear;
         int age;
         String Email;
         String Country;
         String State;
         String City;
         String PostalCode;
         String AboutMe1;
         String StudiesEmphasis;
         String RelocateFlag;
         String Headline;
         String Occupation;
         String OccupationDescription;
         String GrewUpIn;
         String ZodiacSign;
         String Languages;
         String RelationshipType;
         String MaritalStatus;
         String HairColor;
         String IncomeLevel;
         String EducationLevel;
         String BodyType;
         String AppearanceImportance;
         String Religion;
         String EyeColor;
         String Ethnicity;
         String PoliticalOrientation;
         String IntelligenceImportance;
         String SmokingHabits;
         String ActivityLevel;
         String Custody;
         String DrinkingHabits;
         String MoreChildrenFlag;
         String AboutMe2;
         String PersonalityTrait;
         String LeisureActivity;
         String PerfectMatchEssay;
         String Cuisine;
         String Music;
         String Reading;
         String PerfectFirstDateEssay;
         String EntertainmentLocation;
         String PhysicalActivity;
         String IdealRelationshipEssay;
         String LearnFromThePastEssay;
         
		out.println("<TR BGCOLOR='#D6DFFF'>");
		out.println("<TD ALIGN='center' colspan='2'><H2><U>Information Update Form</U></H2></TD>");
		out.println("</TR>");
         
         while (rs.next())
         {
         	FileLocation = rs.getString("FileLocation");
            UserID = Integer.parseInt( rs.getString("UserID") );
            DOBmonth = Integer.parseInt( rs.getString("DOBmonth") );
            DOBday = Integer.parseInt( rs.getString("DOBday") );
            DOByear = Integer.parseInt( rs.getString("DOByear") );
            age = Integer.parseInt( rs.getString("age") );
            Email = rs.getString("Email");
            Country = rs.getString("Country");
            State = rs.getString("State");
            City = rs.getString("City");
            PostalCode = rs.getString("PostalCode");
            AboutMe1 = rs.getString("AboutMe1");
            StudiesEmphasis = rs.getString("StudiesEmphasis");
            RelocateFlag = rs.getString("RelocateFlag");
            Headline = rs.getString("Headline");
            Occupation = rs.getString("Occupation");
            OccupationDescription = rs.getString("OccupationDescription");
            GrewUpIn = rs.getString("GrewUpIn");
            ZodiacSign = rs.getString("ZodiacSign");
            Languages = rs.getString("Languages");
            RelationshipType = rs.getString("RelationshipType");
            MaritalStatus = rs.getString("MaritalStatus");
            HairColor = rs.getString("HairColor");
            IncomeLevel = rs.getString("IncomeLevel");
            EducationLevel = rs.getString("EducationLevel");
            BodyType = rs.getString("BodyType");
            AppearanceImportance = rs.getString("AppearanceImportance");
            Religion = rs.getString("Religion");
            EyeColor = rs.getString("EyeColor");
            Ethnicity = rs.getString("Ethnicity");
            PoliticalOrientation = rs.getString("PoliticalOrientation");
            IntelligenceImportance = rs.getString("IntelligenceImportance");
            SmokingHabits = rs.getString("SmokingHabits");
            ActivityLevel = rs.getString("ActivityLevel");
            Custody = rs.getString("Custody");
            DrinkingHabits = rs.getString("DrinkingHabits");
            MoreChildrenFlag = rs.getString("MoreChildrenFlag");
            AboutMe2 = rs.getString("AboutMe2");
            PersonalityTrait = rs.getString("PersonalityTrait");
            LeisureActivity = rs.getString("LeisureActivity");
            PerfectMatchEssay = rs.getString("PerfectMatchEssay");
            Cuisine = rs.getString("Cuisine");
            Music = rs.getString("Music");
            Reading = rs.getString("Reading");
            PerfectFirstDateEssay = rs.getString("PerfectFirstDateEssay");
            EntertainmentLocation = rs.getString("EntertainmentLocation");
            PhysicalActivity = rs.getString("PhysicalActivity");
            IdealRelationshipEssay = rs.getString("IdealRelationshipEssay");
            LearnFromThePastEssay = rs.getString("LearnFromThePastEssay");
            
            out.println("<TR>");
            out.println("<TD ALIGN='center'><img src='" + FileLocation + "'></TD>");
            out.println("<TD ALIGN='left'><b>Member ID: </b>" + UserID + "<BR><b>Member Name: </b>" + user + "</TD>");
			out.println("</TR>");

			/* I think the user does not need to change his/her birthdate - doesnt make sense to me - Yusuf
            out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Date of Birth:</b></td>");
            
            out.println("<TD ALIGN='left'>");
            out.println("<select name=\"DOBmonth\">");
	            out.println("<option value=\"1\" selected>Jan</option>");
	            out.println("<option value=\"2\">Feb</option>");
	            out.println("<option value=\"3\">Mar</option>");
	            out.println("<option value=\"4\">Apr</option>");
	            out.println("<option value=\"5\">May</option>");
	            out.println("<option value=\"6\">Jun</option>");
	            out.println("<option value=\"7\">Jul</option>");
	            out.println("<option value=\"8\">Aug</option>");
	            out.println("<option value=\"9\">Sep</option>");
	            out.println("<option value=\"10\">Oct</option>");
	            out.println("<option value=\"11\">Nov</option>");
	            out.println("<option value=\"12\">Dec</option>");
            out.println("</select>");

            out.println("<select name=\"DOBday\">");
	            out.println("<option value=\"1\" selected>1</option>");
	            out.println("<option value=\"2\">2</option>");
	            out.println("<option value=\"3\">3</option>");
	            out.println("<option value=\"4\">4</option>");
	            out.println("<option value=\"5\">5</option>");
	            out.println("<option value=\"6\">6</option>");
	            out.println("<option value=\"7\">7</option>");
	            out.println("<option value=\"8\">8</option>");
	            out.println("<option value=\"9\">9</option>");
	            out.println("<option value=\"10\">10</option>");
	            out.println("<option value=\"11\">11</option>");
	            out.println("<option value=\"12\">12</option>");
	            out.println("<option value=\"13\">13</option>");
	            out.println("<option value=\"14\">14</option>");
	            out.println("<option value=\"15\">15</option>");
	            out.println("<option value=\"16\">16</option>");
	            out.println("<option value=\"17\">17</option>");
	            out.println("<option value=\"18\">18</option>");
	            out.println("<option value=\"19\">19</option>");
	            out.println("<option value=\"20\">20</option>");
	            out.println("<option value=\"21\">21</option>");
	            out.println("<option value=\"22\">22</option>");
	            out.println("<option value=\"23\">23</option>");
	            out.println("<option value=\"24\">24</option>");
	            out.println("<option value=\"25\">25</option>");
	            out.println("<option value=\"26\">26</option>");
	            out.println("<option value=\"27\">27</option>");
	            out.println("<option value=\"28\">28</option>");
	            out.println("<option value=\"29\">29</option>");
	            out.println("<option value=\"30\">30</option>");
	            out.println("<option value=\"31\">31</option>");
            out.println("</select>");

            out.println("<select name=\"DOByear\">");
	            out.println("<option value=\"1986\" selected>1986</option>");
	            out.println("<option value=\"1985\">1985</option>");
	            out.println("<option value=\"1984\">1984</option>");
	            out.println("<option value=\"1983\">1983</option>");
	            out.println("<option value=\"1982\">1982</option>");
	            out.println("<option value=\"1981\">1981</option>");
	            out.println("<option value=\"1980\">1980</option>");
	            out.println("<option value=\"1979\">1979</option>");
	            out.println("<option value=\"1978\">1978</option>");
	            out.println("<option value=\"1977\">1977</option>");
	            out.println("<option value=\"1976\">1976</option>");
	            out.println("<option value=\"1975\">1975</option>");
	            out.println("<option value=\"1974\">1974</option>");
	            out.println("<option value=\"1973\">1973</option>");
	            out.println("<option value=\"1972\">1972</option>");
	            out.println("<option value=\"1971\">1971</option>");
	            out.println("<option value=\"1970\">1970</option>");
	            out.println("<option value=\"1969\">1969</option>");
	            out.println("<option value=\"1968\">1968</option>");
	            out.println("<option value=\"1967\">1967</option>");
	            out.println("<option value=\"1966\">1966</option>");
	            out.println("<option value=\"1965\">1965</option>");
	            out.println("<option value=\"1964\">1964</option>");
	            out.println("<option value=\"1963\">1963</option>");
	            out.println("<option value=\"1962\">1962</option>");
	            out.println("<option value=\"1961\">1961</option>");
	            out.println("<option value=\"1960\">1960</option>");
	            out.println("<option value=\"1959\">1959</option>");
	            out.println("<option value=\"1958\">1958</option>");
	            out.println("<option value=\"1957\">1957</option>");
	            out.println("<option value=\"1956\">1956</option>");
	            out.println("<option value=\"1955\">1955</option>");
	            out.println("<option value=\"1954\">1954</option>");
	            out.println("<option value=\"1953\">1953</option>");
	            out.println("<option value=\"1952\">1952</option>");
	            out.println("<option value=\"1951\">1951</option>");
	            out.println("<option value=\"1950\">1950</option>");
	            out.println("<option value=\"1949\">1949</option>");
	            out.println("<option value=\"1948\">1948</option>");
	            out.println("<option value=\"1947\">1947</option>");
	            out.println("<option value=\"1946\">1946</option>");
	            out.println("<option value=\"1945\">1945</option>");
	            out.println("<option value=\"1944\">1944</option>");
	            out.println("<option value=\"1943\">1943</option>");
	            out.println("<option value=\"1942\">1942</option>");
	            out.println("<option value=\"1941\">1941</option>");
	            out.println("<option value=\"1940\">1940</option>");
	            out.println("<option value=\"1939\">1939</option>");
	            out.println("<option value=\"1938\">1938</option>");
	            out.println("<option value=\"1937\">1937</option>");
	            out.println("<option value=\"1936\">1936</option>");
	            out.println("<option value=\"1935\">1935</option>");
	            out.println("<option value=\"1934\">1934</option>");
	            out.println("<option value=\"1933\">1933</option>");
	            out.println("<option value=\"1932\">1932</option>");
	            out.println("<option value=\"1931\">1931</option>");
	            out.println("<option value=\"1930\">1930</option>");
	            out.println("<option value=\"1929\">1929</option>");
	            out.println("<option value=\"1928\">1928</option>");
	            out.println("<option value=\"1927\">1927</option>");
	            out.println("<option value=\"1926\">1926</option>");
	            out.println("<option value=\"1925\">1925</option>");
	            out.println("<option value=\"1924\">1924</option>");
	            out.println("<option value=\"1923\">1923</option>");
	            out.println("<option value=\"1922\">1922</option>");
	            out.println("<option value=\"1921\">1921</option>");
	            out.println("<option value=\"1920\">1920</option>");
	            out.println("<option value=\"1919\">1919</option>");
	            out.println("<option value=\"1918\">1918</option>");
	            out.println("<option value=\"1917\">1917</option>");
	            out.println("<option value=\"1916\">1916</option>");
	            out.println("<option value=\"1915\">1915</option>");
	            out.println("<option value=\"1914\">1914</option>");
	            out.println("<option value=\"1913\">1913</option>");
	            out.println("<option value=\"1912\">1912</option>");
	            out.println("<option value=\"1911\">1911</option>");
	            out.println("<option value=\"1910\">1910</option>");
	            out.println("<option value=\"1909\">1909</option>");
	            out.println("<option value=\"1908\">1908</option>");
	            out.println("<option value=\"1907\">1907</option>");
	            out.println("<option value=\"1906\">1906</option>");
	            out.println("<option value=\"1905\">1905</option>");
	            out.println("<option value=\"1904\">1904</option>");
            out.println("</select>");
            out.println("</TD>");
            out.println("</TR>");
            */
            
            out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Email: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Email' name='Email' value='" + Email + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Country: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Country' name='Country' value='" + Country + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>State: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='State' name='State' value='" + State + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>City: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='City' name='City' value='" + City + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Postal Code: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='PostalCode' name='PostalCode' value='" + PostalCode + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>What is the first thing you want people to know about you?</b></TD>");
            out.println("<TD ALIGN='left'><textarea name='AboutMe1' id='AboutMe1' rows=4 cols=60 maxlength=2000>" + AboutMe1 + "</textarea></TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Picture URL: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='FileLocation' name='FileLocation' value='" + FileLocation + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Studies Emphasis: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='StudiesEmphasis' name='StudiesEmphasis' value='" + StudiesEmphasis + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Willing to RelocateFlag for the right person: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='RelocateFlag' name='RelocateFlag' value='" + RelocateFlag + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Headline: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Headline' name='Headline' value='" + Headline + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Occupation: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Occupation' name='Occupation' value='" + Occupation + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Occupation Description: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='OccupationDescription' name='OccupationDescription' value='" + OccupationDescription + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Grew up in: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='GrewUpIn' name='GrewUpIn' value='" + GrewUpIn + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Zodiac Sign: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='ZodiacSign' name='ZodiacSign' value='" + ZodiacSign + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Languages I speak: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Languages' name='Languages' value='" + Languages + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Type of RelationshipType seeking: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='RelationshipType' name='RelationshipType' value='" + RelationshipType + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Marital status: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='MaritalStatus' name='MaritalStatus' value='" + MaritalStatus + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Hair Color: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='HairColor' name='HairColor' value='" + HairColor + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Income Level: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='IncomeLevel' name='IncomeLevel' value='" + IncomeLevel + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Education Level: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='EducationLevel' name='EducationLevel' value='" + EducationLevel + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Body Type: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='BodyType' name='BodyType' value='" + BodyType + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Rate appearance: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='AppearanceImportance' name='AppearanceImportance' value='" + AppearanceImportance + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Religion: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Religion' name='Religion' value='" + Religion + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Eye Color: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='EyeColor' name='EyeColor' value='" + EyeColor + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Ethnicity: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Ethnicity' name='Ethnicity' value='" + Ethnicity + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Political Orientation: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='PoliticalOrientation' name='PoliticalOrientation' value='" + PoliticalOrientation + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Rate Intelligence: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='IntelligenceImportance' name='IntelligenceImportance' value='" + IntelligenceImportance + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Smoking Habits: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='SmokingHabits' name='SmokingHabits' value='" + SmokingHabits + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Activity Level: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='ActivityLevel'  name='ActivityLevel' value='" + ActivityLevel + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Custody: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Custody' name='Custody' value='" + Custody + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Drinking Habits: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='DrinkingHabits' name='DrinkingHabits' value='" + DrinkingHabits + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>Want more children: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='MoreChildrenFlag' name='MoreChildrenFlag' value='" + MoreChildrenFlag + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>About Me: </b></TD>");
            out.println("<TD ALIGN='left'><textarea name='AboutMe2' id='AboutMe2' rows=4 cols=60 maxlength=2000>" + AboutMe2 + "</textarea></TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>My personality traits: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='PersonalityTrait' name='PersonalityTrait' value='" + PersonalityTrait + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>My favorite activities: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='LeisureActivity' name='LeisureActivity' value='" + LeisureActivity + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>About the one I'am looking for: </b></TD>");
            out.println("<TD ALIGN='left'><textarea name='PerfectMatchEssay' id='PerfectMatchEssay' rows=4 cols=60 maxlength=2000>" + PerfectMatchEssay + "</textarea></TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>My favorite cuisine: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Cuisine' name='Cuisine' value='" + Cuisine + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>My favorite music: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Music' name='Music' value='" + Music + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>I like to read: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='Reading' name='Reading' value='" + Reading + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>My idea of the perfect first date: </b></TD>");
            out.println("<TD ALIGN='left'><textarea name='PerfectFirstDateEssay' id='PerfectFirstDateEssay' rows=4 cols=60 maxlength=2000>" + PerfectFirstDateEssay + "</textarea></TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>I like going out to: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='EntertainmentLocation' name='EntertainmentLocation' value='" + EntertainmentLocation + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>My favorite physical activities: </b></TD>");
            out.println("<TD ALIGN='left'><input type='text' size='50' id='PhysicalActivity' name='PhysicalActivity' value='" + PhysicalActivity + "'</TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>My perception of an ideal relationship: </b></TD>");
            out.println("<TD ALIGN='left'><textarea name='IdealRelationshipEssay' id='IdealRelationshipEssay' rows=4 cols=60 maxlength=2000>" + IdealRelationshipEssay + "</textarea></TD>");
			out.println("</TR>");
			
			out.println("<TR>");
            out.println("<TD ALIGN='right'><b>What I've learned from my past relationships: </b></TD>");
            out.println("<TD ALIGN='left'><textarea name='LearnFromThePastEssay' id='LearnFromThePastEssay' rows=4 cols=60 maxlength=2000>" + LearnFromThePastEssay + "</textarea></TD>");
			out.println("</TR>");
         }
         
         out.println("<TR BGCOLOR='#D6DFFF'>");
		 out.println("<TD ALIGN='center' colspan='2'><input type='submit' name='updateButton' onClick='update(event)' value='Update My Information'></TD>");
		 out.println("</TR>");
		
         out.println("</TABLE>");
         out.println("</FORM>");
      }
      catch (Exception e) {
         out.println("Could not connect to the users database.<P>");
         out.println("The error message was");
         out.println("<PRE>");
         out.println(e.getMessage());
         out.println("</PRE>");
      }

	  out.println("</div>");
  out.println("</div>");
  out.println("</div>");
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
			return;
		}
%>