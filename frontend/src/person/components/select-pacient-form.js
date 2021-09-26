import React from 'react';
import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/person-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label, Card} from 'reactstrap';

import PersonTable from "./person-table";
class SelectPacientForm extends React.Component {

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

                username: {
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
        return API_USERS.getPatient(person, (result, status, error) => {
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

        console.log(this.state.formControls.username.value);
        this.registerPerson(this.state.formControls.username.value);
    }

    reload() {
            this.setState({
                isLoaded: false
            });
        }


    render() {
        return (
            <div>
                  <FormGroup id='username'>
                                <Label for='usernameField'> Username: </Label>
                                <Input name='username' id='usernameField' placeholder={this.state.formControls.username.placeholder}
                                       onChange={this.handleChange}
                                       defaultValue={this.state.formControls.username.value}
                                       touched={this.state.formControls.username.touched? 1 : 0}
                                       valid={this.state.formControls.username.valid}
                                       required
                                />
                                {this.state.formControls.username.touched && !this.state.formControls.username.valid &&
                                <div className={"error-message row"}> * Username must have at least 3 characters </div>}
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
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}>List pacient</Button>
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

export default SelectPacientForm;
