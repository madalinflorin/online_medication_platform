import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Button,
    Card,
    CardHeader,
    Col, Modal, ModalBody, ModalHeader,
    Row
} from 'reactstrap';

import * as API_USERS from "./api/person-api"
import PersonTable from "./components/person-table-1";
import ViewPatientForm from "./components/view-patient-form";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

class PersonContainer2 extends React.Component {



    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reload = this.reload.bind(this);
        this.state = {
            selected: false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };



    }

    componentDidMount() {


        this.fetchPersons();
        //use your link here

        var socket = new SockJS('https://springs-demo-ds2020.herokuapp.com/websocket-chat');
        var client = Stomp.over(socket);
        client.connect({}, function() {

          //  client.send("/app/user", {}, JSON.stringify({
          //  }));

            client.subscribe('/topic/user',function(message) {

                alert(message.body);
            });
        });

    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }


    fetchPersons() {
        return API_USERS.getPatientsUsersForCaregiver((result, status, err) => {

            if (result !== null && status === 200) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }



    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchPersons();

    }

    render() {
        return (
            <div>
                <CardHeader>
                    <strong> Listing pacients for caregiver</strong>
                </CardHeader>
                <Card>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                    <Button color="primary" onClick={this.toggleForm}>View medication plans for patient </Button>
                    </Col>
                    </Row>
                    <br>
                    </br>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <PersonTable tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                                            errorStatus={this.state.errorStatus}
                                                            error={this.state.error}
                                                        />   }
                        </Col>
                    </Row>



                </Card>



                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={"View medical plans for patient"} size="lg">
                    <ModalHeader toggle={this.toggleForm1}> Select patient: </ModalHeader>
                    <ModalBody>
                        <ViewPatientForm reloadHandler={this.reload1}/>
                    </ModalBody>
                </Modal>


            </div>
        )

    }
}


export default PersonContainer2;
