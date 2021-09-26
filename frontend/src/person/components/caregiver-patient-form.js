import React from 'react';
import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/person-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';


class CaregiverPatientForm extends React.Component {

 constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {

                username_caregiver: {
                                    value: '',
                                    placeholder: '',
                                    valid: false,
                                    touched: false,
                                    validationRules: {
                                        minLength: 3,
                                        isRequired: true
                                    }
                },

                username_patient: {
                                                    value: '',
                                                    placeholder: '',
                                                    valid: false,
                                                    touched: false,
                                                    validationRules: {
                                                        minLength: 3,
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

    registerPerson(person) {
        return API_USERS.postCaregiverPatient(person, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                this.setState({
                                    tableData: result,
                                    isLoaded: true
                                });
                console.log("Successfully inserted patient with id: " + result);
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleSubmit() {

        let person = {
                    username_caregiver: this.state.formControls.username_caregiver.value,
                    username_patient: this.state.formControls.username_patient.value
                };

                console.log(person);
                this.registerPerson(person);
                this.toggleForm();
    }



    render() {
        return (
            <div>
                  <FormGroup id='username_caregiver'>
                                <Label for='username_caregiverField'> Username_caregiver: </Label>
                                <Input name='username_caregiver' id='username_caregiverField' placeholder={this.state.formControls.username_caregiver.placeholder}
                                       onChange={this.handleChange}
                                       defaultValue={this.state.formControls.username_caregiver.value}
                                       touched={this.state.formControls.username_caregiver.touched? 1 : 0}
                                       valid={this.state.formControls.username_caregiver.valid}
                                       required
                                />
                                {this.state.formControls.username_caregiver.touched && !this.state.formControls.username_caregiver.valid &&
                                <div className={"error-message row"}> * Username caregiver must have at least 3 characters </div>}
                            </FormGroup>


                  <FormGroup id='username_patient'>
                                                  <Label for='username_patientField'> Username_patient: </Label>
                                                  <Input name='username_patient' id='username_patientField' placeholder={this.state.formControls.username_patient.placeholder}
                                                         onChange={this.handleChange}
                                                         defaultValue={this.state.formControls.username_patient.value}
                                                         touched={this.state.formControls.username_patient.touched? 1 : 0}
                                                         valid={this.state.formControls.username_patient.valid}
                                                         required
                                                  />
                                                  {this.state.formControls.username_patient.touched && !this.state.formControls.username_patient.valid &&
                                                  <div className={"error-message row"}> * Username patient must have at least 3 characters </div>}
                                              </FormGroup>





                        <div>

                                </div>


                    <Row>
                        <Col sm={{size: '4', offset: 8}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}>Create link</Button>
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

export default CaregiverPatientForm;
