import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Card,
    CardHeader,
    Col,
    Row
} from 'reactstrap';

import * as API_USERS from "./api/person-api"
import MedicalPlanTable1 from "./components/medical-plan-table1";



class MedicalPlansContainer extends React.Component {

    constructor(props) {
        super(props);
        this.reload = this.reload.bind(this);
        this.state = {
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchPersons();
    }

    fetchPersons() {
        return API_USERS.getMedicalPlans((result, status, err) => {

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
        this.fetchPersons();
    }

    render() {
        return (
            <div>
                <CardHeader>
                    <strong> Listing medical plans </strong>
                </CardHeader>
                <Card>

                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <MedicalPlanTable1 tableData = {this.state.tableData}/>}
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


export default MedicalPlansContainer;
