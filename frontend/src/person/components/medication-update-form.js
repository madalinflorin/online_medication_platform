import React from 'react';
import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/person-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';



class MedicationUpdateForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

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


                name: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },
                dosage: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    validationRules: {
                        number: true
                    }
                },
                list_of_effects: {
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

    registerPerson(person) {
        return API_USERS.updateMedication(person, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted patient with id: " + result);
                this.reloadHandler();
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
            id: this.state.formControls.id.value,
            name: this.state.formControls.name.value,
            dosage: this.state.formControls.dosage.value,
            list_of_effects: this.state.formControls.list_of_effects.value
        };

        console.log(person);
        this.registerPerson(person);
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
                                <div className={"error-message row"}> * Id must have at least 1 character </div>}
                            </FormGroup>

                <FormGroup id='name'>
                    <Label for='nameField'> Name: </Label>
                    <Input name='name' id='nameField' placeholder={this.state.formControls.name.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.name.value}
                           touched={this.state.formControls.name.touched? 1 : 0}
                           valid={this.state.formControls.name.valid}
                           required
                    />
                    {this.state.formControls.name.touched && !this.state.formControls.name.valid &&
                    <div className={"error-message row"}> * Name must have at least 3 characters </div>}
                </FormGroup>

                <FormGroup id='dosage'>
                    <Label for='dosageField'> Dosage: </Label>
                    <Input name='dosage' id='dosageField' placeholder={this.state.formControls.dosage.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.dosage.value}
                           touched={this.state.formControls.dosage.touched? 1 : 0}
                           valid={this.state.formControls.dosage.valid}
                           required
                    />
                    {this.state.formControls.dosage.touched && !this.state.formControls.dosage.valid &&
                    <div className={"error-message"}> * Dosage must have a valid format</div>}
                </FormGroup>

                <FormGroup id='list_of_effects'>
                    <Label for='list_of_effectsField'> List of effects (,) : </Label>
                    <Input name='list_of_effects' id='list_of_effectsField' placeholder={this.state.formControls.list_of_effects.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.list_of_effects.value}
                           touched={this.state.formControls.list_of_effects.touched? 1 : 0}
                           valid={this.state.formControls.list_of_effects.valid}
                           required
                    />
                </FormGroup>



                    <Row>
                        <Col sm={{size: '4', offset: 8}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}>  Submit </Button>
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

export default MedicationUpdateForm;
