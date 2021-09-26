import RestApiClient from "../../commons/api/rest-client";
import authHeader from '../../services/auth-header';
import AuthService from "../../services/auth.service";

const BACKEND = 'https://springs-demo-ds2020.herokuapp.com';

const endpoint = {
    person: '/patient'
};



function getPatients(callback) {
    let request = new Request(endpoint.person, {
        method: 'GET', headers: authHeader()
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function getPatient(username, callback) {
    let request = new Request('/api/patient/select/' + username, {
        method: 'GET',
        headers: authHeader(),
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function getMedicalPlans(callback) {


    let request = new Request('/api/medical_plan' , {
        method: 'GET',
        headers: authHeader(),
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function getMedicalPlans1(callback) {

    const currentUser = AuthService.getCurrentUser();

    let request = new Request('/api/medical_plan/' + currentUser.username, {
        method: 'GET',
        headers: authHeader(),
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function getMedicalPlans2(username,callback) {

    const currentUser = AuthService.getCurrentUser();

    let request = new Request('/api/medical_plan/caregiver/' + username + '/' + currentUser.username, {
        method: 'GET',
        headers: authHeader(),
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}




function getCurrentPatient(callback) {

    const currentUser = AuthService.getCurrentUser();

    let request = new Request('/api/patient/select/' + currentUser.username, {
        method: 'GET',
        headers: authHeader(),
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}





function getCaregiver(username, callback) {
    let request = new Request('/api/caregiver/select/' + username, {
        method: 'GET',
        headers: authHeader(),
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function getMedication(med, callback) {
    let request = new Request('/api/medications/' + med, {
        method: 'GET',
        headers: authHeader(),
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPatientsUsers(callback) {
    let request = new Request('/api/patient/user', {
        method: 'GET', headers: authHeader()
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPatientsUsersForCaregiver(callback) {
    const currentUser = AuthService.getCurrentUser();
    let request = new Request('/api/patient/caregiver/' + currentUser.username, {
        method: 'GET', headers: authHeader()
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getCaregiversUsers(callback) {
    let request = new Request('/api/caregiver/user', {
        method: 'GET', headers: authHeader()
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getMedications(callback) {
    let request = new Request('/api/medications', {
        method: 'GET', headers: authHeader()
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPatientById(params, callback){
    let request = new Request('/api' + endpoint.person + params.id, {
       method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function postPatient(user, callback){
    let request = new Request("/api/auth/signup" , {
        method: 'POST',
        headers : {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}


function postMedicalPlan(username, callback){
    let request = new Request("/api/medical_plan" , {
        method: 'POST',
        headers : authHeader(),
        body: JSON.stringify(username)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function postMedicalPlan1(medical_plan, callback){
    let request = new Request("/api/medical_plan/insert" , {
        method: 'POST',
        headers : authHeader(),
        body: JSON.stringify(medical_plan)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}



function postCaregiverPatient(user, callback){
    let request = new Request('/api/insert'  , {
        method: 'POST',
        headers : authHeader(),
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function deletePatient(username, callback){
    let request = new Request("/api/patient/delete/" + username , {
        method: 'DELETE',
        headers: authHeader(),
        body: JSON.stringify(username)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function deleteCaregiver(username, callback){
    let request = new Request("/api/caregiver/delete/" + username , {
        method: 'DELETE',
        headers: authHeader(),
        body: JSON.stringify(username)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}


function deleteMedication(id, callback){
    let request = new Request("/api/medications/" + id , {
        method: 'DELETE',
        headers: authHeader(),
        body: JSON.stringify(id)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}


function postCaregiver(user, callback){
    let request = new Request("/api/auth/signup" , {
        method: 'POST',
        headers : {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}



function postMedication(user, callback){
    let request = new Request("/api/medications/add" , {
        method: 'POST',
        headers : authHeader(),
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function updatePatient(user, callback){
    let request = new Request("/api/patient" , {
        method: 'PUT',
        headers : authHeader(),
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}


function updateCaregiver(user, callback){
    let request = new Request("/api/caregiver" , {
        method: 'PUT',
        headers : authHeader(),
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}


function updateMedication(user, callback){
    let request = new Request("/api/medications" , {
        method: 'PUT',
        headers : authHeader(),
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}




export {
    getPatients,
    getPatient,
    getCaregiver,
    getMedication,
    getMedicalPlans,
    getMedicalPlans1,
    getPatientsUsers,
    getPatientsUsersForCaregiver,
    getCaregiversUsers,
    getCurrentPatient,
    getMedicalPlans2,
    getMedications,
    getPatientById,
    postPatient,
    postMedicalPlan,
    postMedicalPlan1,
    deletePatient,
    deleteCaregiver,
    deleteMedication,
    postCaregiver,
    updatePatient,
    updateCaregiver,
    updateMedication,
    postMedication,
    postCaregiverPatient
};
