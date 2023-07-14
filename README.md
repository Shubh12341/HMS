
-Hospital Management Application
This Hospital Management application is designed to streamline the management of hospital operations, including patient admission, tracking, and discharge.

-Technologies Used
Java
Spring Boot
Spring Security
Hibernate
MySQL Database
Setup and Installation


-To run the application locally, follow these steps:

Install JDK 1.8 or higher.
Install MySQL database and create a new database.
Clone the repository: git clone https://github.com/Shubh12341/HMS.git
Configure the database connection in the application.properties file.
Build the application using Maven: mvn clean install
Run the application: java -jar target/hospital-management.jar


-Usage
Sign up as a staff member using the provided API endpoint- /api/auth/signup.
Log in using the credentials./api/auth/signin
Admit a new patient by making a POST request to /api/patients/admit with the patient details.
Retrieve a list of admitted patients by making a GET request to /api/patients/admitted.
Discharge a patient by making a PUT request to /api/patients/discharge/{patientId} with the patient ID.


