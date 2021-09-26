import React from 'react';
import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/person-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label, Card} from 'reactstrap';

import PersonTable from "./medication-table";

class SelectMedicationForm extends React.Component {

 constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,
            tableData: [],
            isLoaded: false,

            formControls: {

                id: {
                                    value: '',
                                    placeholder: '',
                                    valid: false,
                                    touched: false,
                                    validationRules: {
                                        isRequired: true
                                    }
                },




            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }




    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = this.state.formControls;

        const updatedFormElement = updatedControls[name];

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);
        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    };

    registerPerson(medication) {
        return API_USERS.getMedication(medication, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                this.setState({
                                    tableData: result,
                                    isLoaded: true
                                });
                console.log("Successfully inserted medication with id: " + result);
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleSubmit() {

        console.log(this.state.formControls.id.value);
        this.registerPerson(this.state.formControls.id.value);
    }

    reload() {
            this.setState({
                isLoaded: false
            });
        }


    render() {
        return (
            <div>
                  <FormGroup id='id'>
                                <Label for='idField'> ID: </Label>
                                <Input name='id' id='idField' placeholder={this.state.formControls.id.placeholder}
                                       onChange={this.handleChange}
                                       defaultValue={this.state.formControls.id.value}
                                       touched={this.state.formControls.id.touched? 1 : 0}
                                       valid={this.state.formControls.id.valid}
                                       required
                                />
                                {this.state.formControls.id.touched && !this.state.formControls.id.valid &&
                                <div className={"error-message row"}> * ID must have at least 3 character </div>}
                            </FormGroup>


                        <Card>

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


                        <div>

                                </div>


                    <Row>
                        <Col sm={{size: '4', offset: 8}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}>List medication</Button>
                        </Col>

                    </Row>



                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default SelectMedicationForm;
