import React from 'react';
import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/person-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';



class DeleteCaregiverForm extends React.Component {


    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

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
                }
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

    registerPerson(username) {
        return API_USERS.deleteCaregiver(username, (result, status, error) => {
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
            username: this.state.formControls.username.value
        };

        console.log(person.username);
        this.registerPerson(person.username);
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

export default DeleteCaregiverForm;
