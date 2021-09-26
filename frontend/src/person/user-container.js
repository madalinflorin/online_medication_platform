import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Card,
    CardHeader,
    Col,
    Row
} from 'reactstrap';

import * as API_USERS from "./api/person-api"
import MedicalPlanTable from "./components/medical-plan-table";
import PersonTable from "./components/person-table";



class UserContainer extends React.Component {

    constructor(props) {
        super(props);
        this.reload = this.reload.bind(this);
        this.state = {
            collapseForm: false,
            tableData: [],
            tableData1: [],
            isLoaded: false,
            isLoaded1: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchPersons();
        this.fetchPersons1();
    }

    fetchPersons() {
        return API_USERS.getMedicalPlans1((result, status, err) => {

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

    fetchPersons1() {
        return API_USERS.getCurrentPatient((result, status, err) => {

            if (result !== null && status === 200) {
                this.setState({
                    tableData1: result,
                    isLoaded1: true
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
            isLoaded: false,
            isLoaded1: false
        });
        this.fetchPersons();
        this.fetchPersons1();
    }

    render() {
        return (
            <div>
                <CardHeader>
                    <strong> Listing medical plans for a patient </strong>
                </CardHeader>
                <Card>

                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <MedicalPlanTable tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                                            errorStatus={this.state.errorStatus}
                                                            error={this.state.error}
                                                        />   }
                        </Col>
                    </Row>

                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded1 && <PersonTable tableData = {this.state.tableData1}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>
                    </Row>

                </Card>


            </div>
        )

    }
}


export default UserContainer;
