<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>CSE220 Final Project</title>
	


    <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <!--link href="css/details.css" rel="stylesheet"-->
	<link href="css/common.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    

</head>

<body id="page-top" class="index">
<%
   int id = 0;
   String memberName = "";

   /* 1 means lookup by member id, 2 means lookup by name*/
   int lookupType = Integer.parseInt(request.getParameter("type"));
   
   if (lookupType == 1)
      id = Integer.parseInt(request.getParameter("data"));

   if (lookupType == 2)
      memberName = request.getParameter("data");


    String userName = "";
   int dobMonth = 0;
   int dobDay = 0;
   int dobYear = 0;
   int age = 0;
   String lastLogin = "";
   String email = "";
   String gender = "";
   String seekingGender = "";
   String country = "";
   String state = "";
   String city = "";
   String zip = "";
   String picture = "";
   String aboutMe1 = "";

   int height = 0;
   int weight = 0;

   String study = "";
   String relocate = "";
   String headline = "";
   String occupation ="";
   String occupationDescription="";
   String grewUpIn="";
   String zodiacSign="";
   String languages="";
   String relationType="";
   String maritalStatus="";
   String hairColor="";
   String income="";
   String education="";
   String bodyType="";
   String appearanceImportance="";
   String religion="";
   String eyes="";
   String ethnicity="";
   String politics="";
   String intelligenceImportance="";
   String smoking="";
   String activity="";
   String hasChildren="";
   String drinking="";
   String wantsChildren="";
   String aboutMe2="";
   String personalityTrait="";
   String leisure="";
   String aboutMatch="";
   String cuisineTypes="";
   String musicTypes="";
   String readingWhat="";
   String firstDate="";
   String entertainmentLocation="";
   String phyActivity="";
   String idealRelationship="";
   String learnFromThePast="";
   String sql="";



   if (lookupType == 1)
      sql = "SELECT * FROM userstable WHERE UserID=" + id;

   if (lookupType == 2)
      sql = "SELECT * FROM userstable WHERE UserName='" + memberName + "'";
  
   try
   {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
      Statement s = con.createStatement();      
      ResultSet rs = s.executeQuery(sql);

      while (rs.next())
      {
       userName = rs.getString( "UserName" );
       dobMonth = Integer.parseInt(rs.getString( "DOBmonth" ));
       dobDay = Integer.parseInt(rs.getString( "DOBday" ));
       dobYear = Integer.parseInt(rs.getString( "DOByear" ));
       age = 2003 - dobYear;


       lastLogin = rs.getString("lastLoginDate");
     email = rs.getString( "Email" );
          gender = rs.getString( "Gender" );
          seekingGender = rs.getString( "SeekingGender" );
          country = rs.getString( "Country" );
          state = rs.getString( "State" );
          city = rs.getString( "City" );
          zip = rs.getString( "PostalCode" );
  picture = rs.getString("FileLocation");
          aboutMe1 = rs.getString( "AboutMe1" );

       height = Integer.parseInt( rs.getString( "Height" ) );
       weight = Integer.parseInt(rs.getString( "Weight" ));

         study = rs.getString( "StudiesEmphasis" );
          relocate = rs.getString( "RelocateFlag" );
          headline = rs.getString( "Headline" );
          occupation = rs.getString( "Occupation" );
          occupationDescription = rs.getString( "OccupationDescription" );
          grewUpIn = rs.getString( "GrewUpIn" );
          zodiacSign = rs.getString( "ZodiacSign" );
          languages = rs.getString( "Languages" );
          relationType = rs.getString( "RelationshipType" );
          maritalStatus = rs.getString( "MaritalStatus" );
          hairColor = rs.getString( "HairColor" );
          income = rs.getString( "IncomeLevel" );
          education = rs.getString( "EducationLevel" );
          bodyType = rs.getString( "BodyType" );
          appearanceImportance = rs.getString( "AppearanceImportance" );
          religion = rs.getString( "Religion" );
          eyes = rs.getString( "EyeColor" );
          ethnicity = rs.getString( "Ethnicity" );
          politics = rs.getString( "PoliticalOrientation" );
          intelligenceImportance = rs.getString( "IntelligenceImportance" );
          smoking = rs.getString( "SmokingHabits" );
          activity = rs.getString( "ActivityLevel" );
          hasChildren = rs.getString( "Custody" );
          drinking = rs.getString( "DrinkingHabits" );
          wantsChildren = rs.getString( "MoreChildrenFlag" );
          aboutMe2 = rs.getString( "AboutMe2" );
          personalityTrait = rs.getString( "PersonalityTrait" );
          leisure = rs.getString( "LeisureActivity" );
          aboutMatch = rs.getString( "PerfectMatchEssay" );
          cuisineTypes = rs.getString( "Cuisine" );
          musicTypes = rs.getString( "Music" );
          readingWhat = rs.getString( "Reading" );
          firstDate = rs.getString( "PerfectFirstDateEssay" );
          entertainmentLocation = rs.getString("EntertainmentLocation");
          phyActivity = rs.getString("PhysicalActivity");
          idealRelationship = rs.getString( "IdealRelationshipEssay" );
          learnFromThePast = rs.getString( "LearnFromThePastEssay" );

      }

      s.close();

     con.close();
   }
   catch (SQLException e)
   {
         out.println("<H2>ERROR: </H2>");
         out.println(e.getMessage());
   }
   catch (Exception e)
   {
         out.println("<H2>ERROR: </H2>");
         out.println(e.getMessage());
   }
%>
    <!-- Navigation -->
    <nav class="navbar navbar-default">
         <div class="navbar-toggleable-xs" id="exCollapsingNavbar2">
    <a class="navbar-brand" href="loggedin.jsp	">Welcome <%=userName%></a>
    <ul class="nav navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href='details.jsp?type=2&data=<%=userName%>'>View My Details</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href='update.jsp'>Update My Information</a></li>
		 <li class="nav-item">
        <a class="nav-link" href='viewContacts.jsp'>View My Contacts</a></li>
		
      <li class="nav-item pull-xs-right">
        <a class="nav-link" href='logout.jsp'>Logout</a>
      </li>
    </ul>
	</div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <img class="img-responsive" src=<%= picture %> alt=""></div>
                    <div class="col-lg-8">
                        <h4><%= userName %></h4>
						<p class="skills"><%= maritalStatus %>&nbsp;<%= gender %> seeking <%= seekingGender %><BR>was born in <%= dobYear %><br>from <%= city %>, <%= state %>, <%= country %></p>
                        
                        <span class="skills"><%= aboutMe1 %></span>
                    </div>
                
            </div>
        </div>
    </header>

    <!-- Portfolio Grid Section -->
    <section id="portfolio">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Details</h2>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 details-item">
                    <a href="#detailsModal1"  data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <span>About Me</span>
                            </div>
                        </div>
                        
                    </a>
                </div>
                <div class="col-sm-4 details-item">
                    <a href="#detailsModal2"  data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <span class="skills">PersonalInfo</span>
                            </div>
                        </div>
                        
                    </a>
                </div>
                <div class="col-sm-4 details-item">
                    <a href="#detailsModal3"  data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <span class="skills">Basics</span>
                            </div>
                        </div>
                        
                    </a>
                </div>
                <div class="col-sm-4 details-item">
                    <a href="#detailsModal4"  data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <span class="skills">Personality</span>
                            </div>
                        </div>
                       
                    </a>
                </div>
                <div class="col-sm-4 details-item">
                    <a href="#detailsModal5"  data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <span class="skills">Interests</span>
                            </div>
                        </div>
                        
                    </a>
                </div>
                <div class="col-sm-4 details-item">
                    <a href="#detailsModal6"  data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                               <span class="skills">Leisure and Physical Activities</span>
                            </div>
                        </div>
                        
                    </a>
                </div>
            </div>
        </div>
    </section>

   

  

   

    <!-- Portfolio Modals -->
    <div class="details-modal modal fade" id="detailsModal1" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Personal Details</h2>
                            <hr>
                            <img src=<%= picture %> class="img-responsive img-centered" alt="">
                            
                            <ul class="list-inline item-details">
                                <li>About Me:
                                    <strong><%= aboutMe2 %>
                                    </strong>
                                </li>
                                
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="details-modal modal fade" id="detailsModal2" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Personal Info</h2>
                            <hr>
                            
                            
                            <ul class="list-inline item-details">
                                <li>Username:
                                    <strong><%= userName %>
                                    </strong>
                                </li>
                                <li>Gender:
                                    <strong><%= gender %> seeking <%= seekingGender %>
                                    </strong>
                                </li>
                                <li>Age:
                                    <strong><%= age %>
                                    </strong>
                                </li>
								<li>From:
                                    <strong><%= city %>, <%= state %>, <%= country %>
                                    </strong>
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="details-modal modal fade" id="detailsModal3" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Basics</h2>
                            <hr class="star-primary">
                            
                            <div class="row">
								<div class="col-md-4">
                            <ul class="list-inline item-details">
                                <li>Hair:
                                    <strong><%= hairColor %>
                                    </strong>
                                </li>
                                <li>Eyes
                                    <strong><%= eyes %>
                                    </strong>
                                </li>
                                <li>Height:
                                    <%= height %>
                                    </strong>
                                </li>
								<li>Weight:
                                   <strong> <%= weight %>
                                    </strong>
                                </li>
								<li>Body Style:
                                    <strong><%= bodyType %>
                                    </strong>
                                </li>
                            </ul>
						</div>
														<div class="col-md-4">
                            <ul class="list-inline item-details">
                                <li>Smoking:
                                    <strong><%= smoking %>
                                    </strong>
                                </li>
                                <li>Drinking
                                    <strong><%= drinking %>
                                    </strong>
                                </li>
                                <li>Marrital Status:
                                    <strong><%= maritalStatus %>
                                    </strong>
                                </li>
								<li>Children::
                                    <strong><%= hasChildren %>
                                    </strong>
                                </li>
								<li>Zodiac Sign::
                                    <strong><%= zodiacSign %>
                                    </strong>
                                </li>
                            </ul>
						</div>
														<div class="col-md-4">
                            <ul class="list-inline item-details">
                                <li>Languages I speak::
                                    <strong><%= languages %>
                                    </strong>
                                </li>
                                <li>Ethnicity:
                                    <strong><%= ethnicity %>
                                    </strong>
                                </li>
                                <li>Religion:
                                    <strong><%= religion %>
                                    </strong>
                                </li>
								<li>Education:
                                    <strong><%= education %>
                                    </strong>
                                </li>
								<li>Relocate
                                    <strong><%= relocate %>
                                    </strong>
                                </li>
                            </ul>
						</div>
					</div>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="details-modal modal fade" id="detailsModal4" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Personality</h2>
                            <hr>
                            
                            
                            <ul class="list-unstyled item-details">
                                <li>Occupation Description:
                                    <strong><%= occupationDescription %>
                                    </strong>
                                </li>
                                <li>Seeking:
                                    <strong><%= relationType %>
                                    </strong>
                                </li>
                                <li>Headline:
                                    <strong><%= headline %>
                                    </strong>
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="details-modal modal fade" id="detailsModal5" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Interests</h2>
                            <hr>
                            
                            
                            <ul class="list-unstyled item-details">
                                <li>My personality traits:
                                    <strong><%= personalityTrait %>
                                    </strong>
                                </li>
                                <li>My favorite activities:
                                    <strong><%= leisure %>
                                    </strong>
                                </li>
                                <li>My favorite activities:
                                    <strong><%= aboutMatch %>
                                    </strong>
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="details-modal modal fade" id="detailsModal6" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Leisure and Physical Activities</h2>
                            <hr>
                            
                            
                            <ul class="list-unstyled item-details">
                                <li>I like going out to:
                                    <strong><%= entertainmentLocation %>
                                    </strong>
                                </li>
                                <li>My favorite physical activities:
                                    <strong><%= phyActivity %>
                                    </strong>
                                </li>
                               <li>What I've learned from my past relationships:
                                    <strong><%= learnFromThePast %>
                                    </strong>
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>


    
</body>

</html>
